package cuj.ecsv.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author cujamin
 * @date 2020/3/21
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER,ElementType.METHOD,ElementType.FIELD})
public @interface Parameter {
    String column();

    String readColumn();

    String writeColumn();
}
