package cuj;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.lang.reflect.Field;
import java.rmi.MarshalledObject;
import java.util.*;

/**
 * @author cujamin
 * @date 2020/1/14
 */
public class CsvFileUtil {
    public static Map<String, Integer> csvHeaderMap(String filePath, Class c) throws IOException {
        return csvHeaderMap(new File(filePath), c);
    }

    public static Map<String, Integer> csvHeaderMap(File csvfile, Class c) throws IOException {
        String[] contentHeader = null;
        try (BufferedReader bufferedReader = bufferedReader(csvfile)) {
            List<CSVRecord> csvRecordList = csvRecordList(bufferedReader);
            Iterator<CSVRecord> iterator = csvRecordList.iterator();
            if (iterator.hasNext()) {
                CSVRecord headerRecord = iterator.next();
                contentHeader = new String[headerRecord.size()];
                for (int i = 0; i < headerRecord.size(); ++i) {
                    contentHeader[i] = headerRecord.get(i);
                }
            }
        }
        return csvHeaderMap(contentHeader, c);
    }

    public static Map<String, Integer> csvHeaderMap(String[] contentHeader, Class c) {
        Map<String, Integer> csvHeaderMap = new HashMap<>();
        if (null == contentHeader) {
            return csvHeaderMap;
        }
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < contentHeader.length; ++i) {
            for (Field field : fields) {
                String name = field.getName();
                if (name.equalsIgnoreCase(contentHeader[i])) {
                    csvHeaderMap.put(name.toLowerCase(), i);
                }
            }
        }
        return csvHeaderMap;
    }
    public static String[] csvHeaderContent(Class c) {
        Map<String, Integer> csvHeaderMap = csvHeaderMap(c);
        String[] csvHeaderContent = new String[csvHeaderMap.size()];
        for (Map.Entry<String, Integer> csvHeaderMapEntry:csvHeaderMap.entrySet()) {
            csvHeaderContent[csvHeaderMapEntry.getValue()] = csvHeaderMapEntry.getKey();
        }
        return csvHeaderContent;
    }

    public static Map<String, Integer> csvHeaderMap(Class c) {
        Map<String, Integer> csvHeaderMap = new HashMap<>();

        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            String name = fields[i].getName();
            csvHeaderMap.put(name.toLowerCase(), i);
        }
        return csvHeaderMap;
    }

    public static Map<String, Integer> csvHeaderMap(String filePath) throws IOException {
        File csvfile = new File(filePath);
        return csvHeaderMap(csvfile);
    }

    public static Map<String, Integer> csvHeaderMap(File csvfile) throws IOException {
        Map<String, Integer> headerMap = new HashMap<>();

        try (BufferedReader bufferedReader = bufferedReader(csvfile)) {

            List<CSVRecord> csvRecordList = csvRecordList(bufferedReader);
            Iterator<CSVRecord> iterator = csvRecordList.iterator();
            if (iterator.hasNext()) {
                CSVRecord headerRecord = iterator.next();
                headerMap = headerMap(headerRecord);
            }
        }
        return headerMap;
    }

    public static List<String[]> readCsvFile(String filePath) throws IOException {
        File csvfile = new File(filePath);
        return readCsvFile(csvfile);
    }

    public static List<String[]> readCsvFile(File csvfile) throws IOException {
        List<String[]> contentList;
        try (BufferedReader bufferedReader = bufferedReader(csvfile)) {
            List<CSVRecord> csvRecordList = csvRecordList(bufferedReader);
            contentList = new LinkedList<>();
            Iterator<CSVRecord> iterator = csvRecordList.iterator();
            if (iterator.hasNext()) {
                CSVRecord headerRecord = iterator.next();
                Map<String, Integer> header = headerMap(headerRecord);
                contentList.add(csvRecord2Content(headerRecord));
                while (iterator.hasNext()) {
                    CSVRecord csvRecord = iterator.next();
                    String[] content = csvRecord2Content(header, csvRecord);
                    contentList.add(content);
                }
            }
        }
        return contentList;
    }

    private static BufferedReader bufferedReader(File csvfile) throws IOException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(csvfile), "utf-8"));
    }

    private static List<CSVRecord> csvRecordList(BufferedReader bufferedReader) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withSkipHeaderRecord();
        CSVParser csvParser = new CSVParser(bufferedReader, csvFormat);
        return csvParser.getRecords();
    }

    private static Map<String, Integer> headerMap(CSVRecord headerRecord) {
        Map<String, Integer> headerMap = new HashMap<>();
        for (int i = 0; i < headerRecord.size(); ++i) {
            headerMap.put(headerRecord.get(i), i);
        }
        return headerMap;
    }

    public static void writerCsvFile(String[] header, List<String[]> contentList, String filePath) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT.withHeader(header).withSkipHeaderRecord();
        try (Writer out = new FileWriter(filePath); CSVPrinter printer = new CSVPrinter(out, csvFormat)) {
            for (String[] content : contentList) {
                printer.printRecord(content);
            }
        }
    }

    private static String[] csvRecord2Content(Map<String, Integer> headerMap, CSVRecord csvRecord) {
        String[] content = new String[headerMap.size()];
        for (Map.Entry<String, Integer> entry : headerMap.entrySet()) {
            content[entry.getValue()] = csvRecord.get(entry.getValue());
        }
        return content;
    }

    private static String[] csvRecord2Content(CSVRecord csvRecord) {
        String[] content = new String[csvRecord.size()];
        for (int i = 0; i < csvRecord.size(); ++i) {
            content[i] = csvRecord.get(i);
        }
        return content;
    }
}
