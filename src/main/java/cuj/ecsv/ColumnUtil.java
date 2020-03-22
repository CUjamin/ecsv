package cuj.ecsv;

import cuj.ecsv.annotation.Column;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author cujamin
 * @date 2020/3/22
 */
public class ColumnUtil {

    public static String readColumeName(Method method) {
        Column column = method.getAnnotation(Column.class);
        if (null == column) {
            return method.getName();
        }
        return column.name();
    }

    public static String fieldName(Field field){
        Column column = field.getAnnotation(Column.class);
        if(null==column){
            return field.getName();
        }

        return column.name();
    }
}
