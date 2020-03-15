package cuj;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * @author cujamin
 * @date 2020/3/8
 */
public class CsvFileUtilTest {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown(){
    }

    @Test
    public void csvHeaderMap() throws IOException {
        writerCsvFile();
        String filePath = System.getProperty("user.dir") + "/test.csv";
        Map<String, Integer> csvHeaderMap = CsvFileUtil.csvHeaderMap(filePath,Student.class);
        System.out.println(csvHeaderMap);
    }

    @Test
    public void csvHeaderMap1() {
    }

    @Test
    public void csvHeaderMap2() {
    }

    @Test
    public void readCsvFile() throws IOException {
        writerCsvFile();
        String filePath = System.getProperty("user.dir") + "/test.csv";
        List<String[]> lineList = CsvFileUtil.readCsvFile(filePath);
        for (String[] line : lineList) {
            System.out.println(line[0]);
        }
    }

    @Test
    public void writerCsvFile() throws IOException {
        List<String[]> stringList = new LinkedList<>();
        String[] header = {"name","isStudent","age","id","score","dt"};

        String[] xiaoming = {"xiaoming","true","20","100000000000","0.1","0.01"};
        stringList.add(xiaoming);
        String[] xiaozzhang = {"xiaozhang","false","21","100000000001","0.2","0.02"};
        stringList.add(xiaozzhang);
        CsvFileUtil.writerCsvFile(header, stringList, "test.csv");
    }

}