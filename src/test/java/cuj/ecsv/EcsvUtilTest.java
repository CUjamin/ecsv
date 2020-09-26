package cuj.ecsv;

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
    public void writeStudentCsvFile() throws Exception {
        List<StudentCsv> studentList = new LinkedList<>();
        StudentCsv student1 = new StudentCsv();
        student1.setName("xiaoming");
        student1.setAge(11);
        student1.setDt(0.01);
        student1.setId(1000000000);
        student1.setIsStudent(true);
        student1.setScore(0.1F);
        studentList.add(student1);

        StudentCsv student2 = new StudentCsv();
        student2.setName("xiaozhang");
        student2.setAge(12);
        student2.setDt(0.02);
        student2.setId(2000000000);
        student2.setIsStudent(false);
        student2.setScore(0.2F);
        studentList.add(student2);

        EcsvUtil.writeCsvFile(studentList, "StudentCsv.csv", StudentCsv.class);
    }

    @Test
    public void writeStudentCsvFieldFile() throws Exception {
        List<StudentCsvField> studentList = new LinkedList<>();
        StudentCsvField student1 = new StudentCsvField();
        student1.setName("xiaoming");
        student1.setAge(11);
        student1.setDt(0.01);
        student1.setId(1000000000);
        student1.setIsStudent(true);
        student1.setScore(0.1F);
        studentList.add(student1);

        StudentCsvField student2 = new StudentCsvField();
        student2.setName("xiaozhang");
        student2.setAge(12);
        student2.setDt(0.02);
        student2.setId(2000000000);
        student2.setIsStudent(false);
        student2.setScore(0.2F);
        student2.setAaa("abc");
        studentList.add(student2);

        EcsvUtil.writeCsvFile(studentList, "StudentCsvField.csv", StudentCsvField.class);
    }

    @Test
    public void parseStrList2StudentCsv() throws Exception {
        List<String[]> stringList = new LinkedList<>();
        String[] header = {"name", "isStudent", "age", "id", "score", "dt"};
        stringList.add(header);
        String[] xiaoming = {"xiaoming", "true", "20", "100000000000", "0.1", "0.01"};
        stringList.add(xiaoming);
        String[] xiaozzhang = {"xiaozhang", "false", "21", "100000000001", "0.2", "0.02"};
        stringList.add(xiaozzhang);

        List<StudentCsv> studentList = EcsvUtil.parse2ObjectList(stringList, StudentCsv.class);
        System.out.println(studentList.size());
        for (StudentCsv student : studentList) {
            System.out.println(student);
        }
    }

    @Test
    public void parseStudentCsvFile2ObjectList() throws Exception {
        List<StudentCsv> studentList = EcsvUtil.readFile2ObjectList(System.getProperty("user.dir") + "/StudentCsv.csv", StudentCsv.class);
        System.out.println(studentList.size());
        for (StudentCsv student : studentList) {
            System.out.println(student);
        }
    }

    @Test
    public void parseStudentCsvFile2ObjectList1() throws Exception {
        List<StudentCsv> studentList = EcsvUtil.readFile2ObjectList(new File(System.getProperty("user.dir") + "/StudentCsv.csv"), StudentCsv.class);
        System.out.println(studentList.size());
        for (StudentCsv student : studentList) {
            System.out.println(student);
        }
    }


    @Test
    public void parseStrList2StudentCsvField() throws Exception {
        List<String[]> stringList = new LinkedList<>();
        String[] header = {"name", "isStudent", "age", "id", "score", "dt"};
        stringList.add(header);
        String[] xiaoming = {"xiaoming", "true", "20", "100000000000", "0.1", "0.01"};
        stringList.add(xiaoming);
        String[] xiaozzhang = {"xiaozhang", "false", "21", "100000000001", "0.2", "0.02"};
        stringList.add(xiaozzhang);

        List<StudentCsvField> studentList = EcsvUtil.parse2ObjectList(stringList, StudentCsvField.class);
        System.out.println(studentList.size());
        for (StudentCsvField student : studentList) {
            System.out.println(student);
        }
    }

    @Test
    public void parseStudentCsvFieldFile2ObjectList() throws Exception {
        List<StudentCsvField> studentList = EcsvUtil.readFile2ObjectList(System.getProperty("user.dir") + "/StudentCsvField.csv", StudentCsvField.class);
        System.out.println(studentList.size());
        for (StudentCsvField student : studentList) {
            System.out.println(student);
        }
    }

    @Test
    public void parseStudentCsvFieldFile2ObjectList1() throws Exception {
        List<StudentCsvField> studentList = EcsvUtil.readFile2ObjectList(new File(System.getProperty("user.dir") + "/StudentCsvField.csv"), StudentCsvField.class);
        System.out.println(studentList.size());
        for (StudentCsvField student : studentList) {
            System.out.println(student);
        }
    }
}