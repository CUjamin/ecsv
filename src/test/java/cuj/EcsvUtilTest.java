package cuj;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author cujamin
 * @date 2020/1/14
 */
public class EcsvUtilTest {

    @Test
    public void parse2Object() throws Exception{
        List<String[]> stringList = new LinkedList<>();
        String[] header = {"name","age","id"};
        stringList.add(header);
        String[] strings = {"name","20","a1s2"};
        stringList.add(strings);

        Student student = EcsvUtil.parse2Object(stringList,Student.class);
        System.out.println(student);
    }
}