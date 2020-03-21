package cuj.ecsv;

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
    public static String parseDate2Str(Map.Entry<String, Method> fieldToMethodMapEntry, Object data) {
        Method method = fieldToMethodMapEntry.getValue();
        String parameterType = method.getReturnType().toString();
        String dataStr;
        switch (parameterType) {
            case INT:
                dataStr = Integer.toString((Integer) data);
                break;
            case LONG:
                dataStr = Long.toString((Long) data);
                break;
            case DOUBLE:
                dataStr = Double.toString((Double) data);
                break;
            case FLOAT:
                dataStr = Float.toString((Float) data);
                break;
            case BOOLEAN:
                dataStr = Boolean.toString((Boolean) data);
                break;
            default:
                dataStr = data.toString();
        }
        return dataStr;
    }
}
