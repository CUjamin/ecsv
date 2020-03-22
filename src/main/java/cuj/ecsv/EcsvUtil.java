package cuj.ecsv;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author cujamin
 * @date 2020/1/11
 */
public class EcsvUtil {

    public static <T> List<T> parseFile2ObjectList(String filePath, Class<T> clazz)
            throws IOException,
            NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<String[]> contentList = CsvFileUtil.readCsvFile(filePath);
        return parse2ObjectList(contentList, clazz);
    }

    public static <T> List<T> parseFile2ObjectList(File csvFile, Class<T> clazz)
            throws IOException,
            NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<String[]> contentList = CsvFileUtil.readCsvFile(csvFile);
        return parse2ObjectList(contentList, clazz);
    }

    public static <T> List<T> parse2ObjectList(List<String[]> contentList, Class<T> clazz)
            throws NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<T> objectList = new LinkedList<>();

        if (1 >= contentList.size()) {
            return objectList;
        }
        Map<String, Method> fieldToSetMethodMap = fieldToSetMethodMap(clazz);

        Iterator<String[]> contentIterator = contentList.iterator();
        String[] header = contentIterator.next();
        Map<String, Integer> csvHeaderMap = CsvFileUtil.csvHeaderMap(header, clazz);
        while (contentIterator.hasNext()) {
            String[] content = contentIterator.next();
            T object = clazz.getConstructor().newInstance();
            for (Map.Entry<String, Method> fieldToMethodMapEntry : fieldToSetMethodMap.entrySet()) {
                try {
                    String contentStr = content[csvHeaderMap.get(fieldToMethodMapEntry.getKey())];
                    fieldToMethodMapEntry.getValue().invoke(object, DataParseUtil.parseDate(fieldToMethodMapEntry, contentStr));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            objectList.add(object);
        }
        return objectList;
    }

    public static <T> void writeCSVFile(List<T> objectList, String filePath, Class clazz)
            throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<String[]> contentList = new ArrayList<>(objectList.size() + 1);
        String[] header = CsvFileUtil.csvHeaderContent(clazz);
        contentList.add(header);
        Map<String, Integer> csvHeaderMap = CsvFileUtil.csvHeaderMap(clazz);
        Map<String, Method> fieldToGetMethodMap = fieldToGetMethodMap(clazz);
        for (T object : objectList) {
            contentList.add(content(object, csvHeaderMap, fieldToGetMethodMap));
        }
        CsvFileUtil.writerCsvFile(header, contentList, filePath);
    }

    private static <T> String[] content(T object, Map<String, Integer> csvHeaderMap, Map<String, Method> fieldToGetMethodMap)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String[] content = new String[fieldToGetMethodMap.size()];
        for (Map.Entry<String, Method> methodEntry : fieldToGetMethodMap.entrySet()) {
            Object data = methodEntry.getValue().invoke(object);
            String dataStr = DataParseUtil.parseDate2Str(methodEntry, data);
            content[csvHeaderMap.get(methodEntry.getKey())] = dataStr;
        }
        return content;
    }


    private static final String SET = "set";

    private static Map<String, Method> fieldToSetMethodMap(Class clazz) {
        return fieldToMethodMap(clazz, SET);
    }

    private static final String GET = "get";

    private static Map<String, Method> fieldToGetMethodMap(Class clazz) {
        return fieldToMethodMap(clazz, GET);
    }

    private static Map<String, Method> fieldToMethodMap(Class clazz, String mothodType) {
        Map<String, Method> fieldToMethodMap = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.contains(mothodType)) {
                methodName = ColumnUtil.readColumeName(method);
                String fieldName = methodName.replace(mothodType, "").toLowerCase();
                fieldToMethodMap.put(fieldName.toLowerCase(), method);
            }
        }
        return fieldToMethodMap;
    }

}
