package cuj;

import org.junit.Test;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * @author cujamin
 * @date 2020/1/14
 */
public class EcsvUtilTest {

    @Test
    public void parse2Object() throws Exception{
        List<String[]> stringList = new LinkedList<>();
        String[] header = {"name","isStudent","age","id","score","dt"};
        stringList.add(header);
        String[] xiaoming = {"xiaoming","true","20","100000000000","0.1","0.01"};
        stringList.add(xiaoming);
        String[] xiaozzhang = {"xiaozhang","false","21","100000000001","0.2","0.02"};
        stringList.add(xiaozzhang);

        List<Student> studentList = EcsvUtil.parse2ObjectList(stringList,Student.class);
        System.out.println(studentList.size());
        for(Student student:studentList){
            System.out.println(student);
        }
    }

    @Test
    public void parseFile2ObjectList() throws Exception{

        List<Student> studentList = EcsvUtil.parseFile2ObjectList(System.getProperty("user.dir") + "/test.csv",Student.class);
        System.out.println(studentList.size());
        for(Student student:studentList){
            System.out.println(student);
        }
    }

    @Test
    public void parseFile2ObjectList1() throws Exception{

        List<Student> studentList = EcsvUtil.parseFile2ObjectList(new File(System.getProperty("user.dir") + "/test.csv"),Student.class);
        System.out.println(studentList.size());
        for(Student student:studentList){
            System.out.println(student);
        }
    }
}