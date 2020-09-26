package cuj.ecsv;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author cujamin
 * @date 2020/9/26
 */
public class ObjectBuilder {
    public static <T> T parse2Object(Map<String, String> fieldNameToValueMap, Class<T> clazz)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        T object = clazz.getConstructor().newInstance();
        List<Field> fieldList = FieldUtil.getObjectFieldList(clazz);
        for (Field field : fieldList) {
            FieldUtil.setValueToField(object, field, fieldNameToValueMap);
        }
        return object;
    }
}
