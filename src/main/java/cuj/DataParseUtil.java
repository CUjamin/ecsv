package cuj;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author cujamin
 * @date 2020/3/13
 */
public class DataParseUtil {
    private static final String INT = "int";
    private static final String DOUBLE = "double";
    private static final String LONG = "long";
    private static final String FLOAT = "float";
    private static final String BOOLEAN = "boolean";


    public static Object parseDate(Map.Entry<String, Method> fieldToMethodMapEntry, String contentStr) {
        String parameterType = fieldToMethodMapEntry.getValue().getParameterTypes()[0].toString();
        Object data;
        switch (parameterType) {
            case INT:
                data = Integer.valueOf(contentStr);
                break;
            case LONG:
                data = Long.valueOf(contentStr);
                break;
            case DOUBLE:
                data = Double.valueOf(contentStr);
                break;
            case FLOAT:
                data = Float.valueOf(contentStr);
                break;
            case BOOLEAN:
                data = Boolean.valueOf(contentStr);
                break;
            default:
                data = contentStr;
        }
        return data;
    }
}
