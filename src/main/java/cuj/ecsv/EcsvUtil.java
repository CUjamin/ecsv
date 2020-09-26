package cuj.ecsv;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author cujamin
 * @date 2020/1/11
 */
public class EcsvUtil {

    public static <T> List<T> readFile2ObjectList(String filePath, Class<T> clazz)
            throws IOException,
            NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<String[]> contentList = CsvFileUtil.readCsvFile(filePath);
        return parse2ObjectList(contentList, clazz);
    }

    public static <T> List<T> readFile2ObjectList(File csvFile, Class<T> clazz)
            throws IOException,
            NoSuchMethodException, SecurityException,
            InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<String[]> contentList = CsvFileUtil.readCsvFile(csvFile);
        return parse2ObjectList(contentList, clazz);
    }

    public static <T> List<T> parse2ObjectList(List<String[]> contentList, Class<T> clazz)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<T> objectList = new LinkedList<>();
        if (1 >= contentList.size()) {
            return objectList;
        }
        Iterator<String[]> contentIterator = contentList.iterator();
        String[] header = contentIterator.next();
        while (contentIterator.hasNext()) {
            String[] content = contentIterator.next();
            Map<String, String> fieldNameToValueMap = ColumnUtil.buildFieldNameToValueMap(header, content);
            T object = ObjectBuilder.parse2Object(fieldNameToValueMap, clazz);
            objectList.add(object);
        }
        return objectList;
    }

    public static <T> void writeCsvFile(List<T> objectList, String filePath, Class clazz)
            throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        List<String[]> contentList = new ArrayList<>(objectList.size() + 1);
        String[] header = CsvFileUtil.csvHeaderContent(clazz);
        contentList.add(header);
        Map<String, Integer> csvHeaderMap = CsvFileUtil.csvHeaderMap(clazz);
        for (T object : objectList) {
            contentList.add(ColumnUtil.content(object, csvHeaderMap, clazz));
        }
        CsvFileUtil.writerCsvFile(header, contentList, filePath);
    }
}
