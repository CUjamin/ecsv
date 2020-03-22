package cuj.ecsv;

import cuj.ecsv.annotation.Column;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cujamin
 * @date 2020/3/22
 */
public class ColumnUtil {
    public static final String SET = "set";

    public static Map<String, Method> columnToSetMethodMap(Class clazz) {
        return columnToMethodMap(clazz, SET);
    }

    public static final String GET = "get";

    public static Map<String, Method> columnToGetMethodMap(Class clazz) {
        return columnToMethodMap(clazz, GET);
    }

    private static Map<String, Method> columnToMethodMap(Class clazz, String mothodType) {
        Map<String, Method> fieldToMethodMap = new HashMap<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.contains(mothodType)) {
                methodName = ColumnUtil.readColumnName(method);
                String fieldName = methodName.replace(mothodType, "").toLowerCase();
                fieldToMethodMap.put(fieldName.toLowerCase(), method);
            }
        }
        return fieldToMethodMap;
    }

    public static String readColumnName(Method method) {
        Column column = method.getAnnotation(Column.class);
        if (null == column) {
            return method.getName();
        }
        return column.name();
    }
    public static String readColumnName(Method method,String mothodType ) {
        Column column = method.getAnnotation(Column.class);
        if (null == column) {
            return method.getName().replace(mothodType, "").toLowerCase();
        }
        return column.name();
    }

    public static String columnName(Field field){
        Column column = field.getAnnotation(Column.class);
        if(null==column){
            return field.getName();
        }

        return column.name();
    }
}
