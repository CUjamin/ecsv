package cuj.ecsv;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author cujamin
 * @date 2020/9/26
 */
public class FieldUtilTest {

    @Test
    public void getObjectFieldsByCsv() {
        List<Field> fieldList = FieldUtil.getObjectFieldList(StudentCsv.class);
        Set<String> csvFieldSet = new HashSet<>();
        for (Field f :fieldList) {
            csvFieldSet.add(f.getName());
        }
        Assert.assertTrue(csvFieldSet.contains("name"));
        Assert.assertTrue(csvFieldSet.contains("isStudent"));
        Assert.assertTrue(csvFieldSet.contains("age"));
        Assert.assertTrue(csvFieldSet.contains("id"));
        Assert.assertTrue(csvFieldSet.contains("score"));
        Assert.assertTrue(csvFieldSet.contains("dt"));
        Assert.assertTrue(csvFieldSet.contains("aaa"));
    }

    @Test
    public void getObjectFieldsByCsvField() {
        List<Field> fieldList = FieldUtil.getObjectFieldList(StudentCsvField.class);
        Set<String> csvFieldSet = new HashSet<>();
        for (Field f :fieldList) {
            csvFieldSet.add(f.getName());
        }

        Assert.assertTrue(csvFieldSet.contains("name"));
        Assert.assertTrue(csvFieldSet.contains("aaa"));
        Assert.assertFalse(csvFieldSet.contains("age"));
    }
}

