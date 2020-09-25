package cuj.ecsv;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cujamin
 * @date 2020/3/22
 */
public class ColumnUtil {

    public static Field[] getObjectFields(Class clazz){
        return clazz.getDeclaredFields();
    }

    public static <T> String[] content(T object, Map<String, Integer> csvHeaderMap, Class clazz)
            throws IllegalAccessException, IllegalArgumentException {
        Field[] fields = ColumnUtil.getObjectFields(clazz);
        String[] content = new String[fields.length];
        for (Field field: fields) {
            String dataStr = FieldUtil.getFieldValueStr(object,field);
            content[csvHeaderMap.get(field.getName())] = dataStr;
        }
        return content;
    }

    public static Map<String, String> buildFieldNameToValueMap(String[] header, String[] content) {
        Map<String, String> fieldNameToValueMap = new HashMap<>();
        for (int columnIndex = 0; columnIndex < header.length; ++columnIndex) {
            fieldNameToValueMap.put(header[columnIndex], content[columnIndex]);
        }
        return fieldNameToValueMap;
    }
}
