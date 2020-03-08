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
        String[] xiaoming = {"xiaoming","20","a1s2"};
        stringList.add(xiaoming);
        String[] xiaozzhang = {"xiaozhang","21","wwwww"};
        stringList.add(xiaozzhang);

        List<Student> studentList = EcsvUtil.parse2Object(stringList,Student.class);
        System.out.println(studentList.size());
        for(Student student:studentList){
            System.out.println(student);
        }

    }
}