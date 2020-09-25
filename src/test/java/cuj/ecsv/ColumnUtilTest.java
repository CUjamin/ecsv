package cuj.ecsv;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author cujamin
 * @date 2020/9/25
 */
public class ColumnUtilTest {

    @Test
    public void getObjectFields() {
        Field[] fields = ColumnUtil.getObjectFields(Student.class);
        for (Field f :fields) {
            System.out.println(f.getName());
        }
    }
}