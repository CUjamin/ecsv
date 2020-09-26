package cuj.ecsv;

import cuj.ecsv.annotation.CsvField;
import cuj.ecsv.annotation.Csv;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author cujamin
 * @date 2020/9/26
 */
public class FieldUtil {
    private static final String INT = "int";
    private static final String DOUBLE = "double";
    private static final String LONG = "long";
    private static final String FLOAT = "float";
    private static final String BOOLEAN = "boolean";

    public static <T> void setValueToField(T object, Field field, Map<String, String> fieldNameToValueMap) throws IllegalAccessException {
        if (fieldNameToValueMap.containsKey(field.getName())) {
            boolean isAccessible = field.isAccessible();
            if (!isAccessible) {
                field.setAccessible(true);
            }
            String valueStr = fieldNameToValueMap.get(field.getName());
            String fieldType = field.getGenericType().toString();
            Object valueObj = FieldUtil.buildFieldValueObj(fieldType, valueStr);
            field.set(object, valueObj);
            field.setAccessible(isAccessible);
        }
    }

    public static Object buildFieldValueObj(String fieldType, String valueStr) {
        Object data;
        switch (fieldType) {
            case INT:
                data = Integer.valueOf(valueStr);
                break;
            case LONG:
                data = Long.valueOf(valueStr);
                break;
            case DOUBLE:
                data = Double.valueOf(valueStr);
                break;
            case FLOAT:
                data = Float.valueOf(valueStr);
                break;
            case BOOLEAN:
                data = Boolean.valueOf(valueStr);
                break;
            default:
                data = valueStr;
        }
        return data;
    }

    public static <T> String getFieldValueStr(T object, Field field) throws IllegalAccessException {
        boolean isAccessible = field.isAccessible();
        if (!isAccessible) {
            field.setAccessible(true);
        }
        Object value = field.get(object);
        String fieldType = field.getGenericType().toString();
        field.setAccessible(isAccessible);
        return parseDate2Str(fieldType, value);
    }

    public static String parseDate2Str(String fieldType, Object valueObj) {
        if (null == valueObj) {
            return "";
        }
        String valueStr;
        switch (fieldType) {
            case INT:
                valueStr = Integer.toString((Integer) valueObj);
                break;
            case LONG:
                valueStr = Long.toString((Long) valueObj);
                break;
            case DOUBLE:
                valueStr = Double.toString((Double) valueObj);
                break;
            case FLOAT:
                valueStr = Float.toString((Float) valueObj);
                break;
            case BOOLEAN:
                valueStr = Boolean.toString((Boolean) valueObj);
                break;
            default:
                valueStr = valueObj.toString();
        }
        return valueStr;
    }

    public static List<Field> getObjectFieldList(Class clazz) {
        if(clazz.isAnnotationPresent(Csv.class)){
            return getObjectFields(clazz);
        }
        return getObjectAnnotationFields(clazz);
    }

    private static List<Field> getObjectFields(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return Arrays.asList(fields.clone());
    }

    private static List<Field> getObjectAnnotationFields(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<Field> fieldList = new LinkedList<>();
        for (Field field : fields) {
            if(field.isAnnotationPresent(CsvField.class)){
                fieldList.add(field);
            }
        }
        return fieldList;
    }
}
