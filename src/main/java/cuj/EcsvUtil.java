package cuj;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author cujamin
 * @date 2020/1/11
 */
public class EcsvUtil {
    private static final String SET = "set";

    public static <T> List<T> parseFile2ObjectList(String filePath, Class<T> clazz)throws Exception{
        List<String[]> contentList = CsvFileUtil.readCsvFile(filePath);
        return parse2ObjectList(contentList,clazz);
    }

    public static <T> List<T> parseFile2ObjectList(File csvFile, Class<T> clazz)throws Exception{
        List<String[]> contentList = CsvFileUtil.readCsvFile(csvFile);
        return parse2ObjectList(contentList,clazz);
    }
    public static <T> List<T> parse2ObjectList(List<String[]> contentList,Class<T> clazz)throws Exception{
        List<T> objectList = new LinkedList<>();

        if(1>=contentList.size()){
            return objectList;
        }
        Map<String,Method> fieldToMethodMap = fieldToMethodMap(clazz);

        Iterator<String[]> contentIterator = contentList.iterator();
        String[] header = contentIterator.next();
        Map<String,Integer> csvHeaderMap = csvHeaderMap(header,clazz);
        while (contentIterator.hasNext()){
            String[] content = contentIterator.next();
            T object = clazz.getConstructor().newInstance();
            for(Map.Entry<String,Method> fieldToMethodMapEntry:fieldToMethodMap.entrySet()){
                try {
                    String contentStr = content[csvHeaderMap.get(fieldToMethodMapEntry.getKey())];
                    fieldToMethodMapEntry.getValue().invoke(object,DataParseUtil.parseDate(fieldToMethodMapEntry,contentStr));
                }catch (Exception e){
                    System.out.println("ERROR:"+fieldToMethodMapEntry.getKey());
                    // TODO: 2020/1/14
                    e.printStackTrace();
                }
            }
            objectList.add(object);
        }
        return objectList;
    }
    private static Map<String,Method> fieldToMethodMap(Class clazz){
        Map<String,Method> fieldToMethodMap = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method:methods){
            String methodName = method.getName();
            if (methodName.contains(SET)) {
                String fieldName = methodName.replace(SET, "").toLowerCase();
                fieldToMethodMap.put(fieldName.toLowerCase(),method);
            }
        }

        return fieldToMethodMap;
    }

    private static Map<String,Integer> csvHeaderMap(String[] header , Class c){
        Map<String,Integer> csvHeaderMap = new HashMap<>();
        Field[] fields = c.getDeclaredFields();
        for(int i=0;i<header.length;++i) {
            for (Field field : fields) {
                String name = field.getName();
                if(name.equalsIgnoreCase(header[i])){
                    csvHeaderMap.put(name.toLowerCase(),i);
                }
            }
        }
        return csvHeaderMap;
    }
}
