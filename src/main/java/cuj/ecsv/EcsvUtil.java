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
        Map<String, Method> columnToSetMethodMap = ColumnUtil.columnToSetMethodMap(clazz);

        Iterator<String[]> contentIterator = contentList.iterator();
        String[] header = contentIterator.next();
        Map<String, Integer> csvHeaderMap = CsvFileUtil.csvHeaderMap(header, clazz);
        while (contentIterator.hasNext()) {
            String[] content = contentIterator.next();
            T object = clazz.getConstructor().newInstance();
            for (Map.Entry<String, Method> columnToSetMethodMapEntry : columnToSetMethodMap.entrySet()) {
                try {
                    String contentStr = content[csvHeaderMap.get(columnToSetMethodMapEntry.getKey())];
                    columnToSetMethodMapEntry.getValue().invoke(object, DataParseUtil.parseDate(columnToSetMethodMapEntry, contentStr));
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
        Map<String, Method> columnToGetMethodMap = ColumnUtil.columnToGetMethodMap(clazz);
        for (T object : objectList) {
            contentList.add(content(object, csvHeaderMap, columnToGetMethodMap));
        }
        CsvFileUtil.writerCsvFile(header, contentList, filePath);
    }

    private static <T> String[] content(T object, Map<String, Integer> csvHeaderMap, Map<String, Method> columnToGetMethodMap)
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String[] content = new String[columnToGetMethodMap.size()];
        for (Map.Entry<String, Method> methodEntry : columnToGetMethodMap.entrySet()) {
            Object data = methodEntry.getValue().invoke(object);
            String dataStr = DataParseUtil.parseDate2Str(methodEntry, data);
            content[csvHeaderMap.get(methodEntry.getKey())] = dataStr;
        }
        return content;
    }
}
