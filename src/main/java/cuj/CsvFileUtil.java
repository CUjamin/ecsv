package cuj;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;

/**
 * @author cujamin
 * @date 2020/1/14
 */
public class CsvFileUtil {

    public static List<String[]> readCsvFile(String filePath) throws IOException{
        File file = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));

        CSVFormat csvFormat = CSVFormat.DEFAULT.withSkipHeaderRecord();
        CSVParser csvParser = new CSVParser(bufferedReader, csvFormat);
        List<CSVRecord> csvRecordList = csvParser.getRecords();
        List<String[]> contentList = new LinkedList<>();
        Iterator<CSVRecord> iterator = csvRecordList.iterator();
        if(iterator.hasNext()){
            CSVRecord headerRecord = iterator.next();
            Map<String,Integer> header = headerMap(headerRecord);
            while (iterator.hasNext()){
                CSVRecord csvRecord = iterator.next();
                String[] content = csvRecord2Content(header,csvRecord);
                contentList.add(content);
            }
        }
        bufferedReader.close();
        return contentList;
    }

    private static Map<String,Integer> headerMap(CSVRecord headerRecord) {
        Map<String,Integer> headerMap = new HashMap<>();
        for(int i=0;i<headerRecord.size();++i){
            headerMap.put(headerRecord.get(i),i);
        }
        return headerMap;
    }

    public static boolean writerCsvFile(String[] header, List<String[]> contentList, String filePath) throws IOException {

        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(header).withSkipHeaderRecord();

        try(Writer out = new FileWriter(filePath); CSVPrinter printer = new CSVPrinter(out, csvFormat)) {
            printer.printRecord(header);
            for (String[] student : contentList) {
                printer.printRecord(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private static String[] csvRecord2Content(Map<String,Integer> headerMap , CSVRecord csvRecord) {
        String[] content = new String[headerMap.size()];
        for(Map.Entry<String,Integer> entry:headerMap.entrySet()){
            content[entry.getValue()] = csvRecord.get(entry.getValue());
        }
        return content;
    }
}
