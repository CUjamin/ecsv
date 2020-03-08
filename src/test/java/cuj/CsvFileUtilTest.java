package cuj;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author cujamin
 * @date 2020/3/8
 */
public class CsvFileUtilTest {

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
        String[] header = {"name", "age", "id"};
        String[] xiaoming = {"xiaoming", "20", "a1s2"};
        stringList.add(xiaoming);
        String[] xiaozzhang = {"xiaozhang", "21", "wwwww"};
        stringList.add(xiaozzhang);
        CsvFileUtil.writerCsvFile(header, stringList, "test.csv");
    }
}