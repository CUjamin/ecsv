# ecsv

csv文件解析库    
可将csv文件解析为java对象列表  
可将java对象列表解析为csv文件  

开发中...

将csv文件解析为java对象

    List<Student> studentList = EcsvUtil.parseFile2ObjectList(System.getProperty("user.dir") + "/test.csv",Student.class);
    
    List<Student> studentList = EcsvUtil.parseFile2ObjectList(new File(System.getProperty("user.dir") + "/test.csv"),Student.class);

将java对象写成csv文件

    EcsvUtil.writeCSVFile(studentList, "test1.csv",Student.class);
    
## 2020.3.22 支持通过注解get、set方法，实现读写文件时分别对应不同的列名

## 2020.3.21 增加注解功能，需要进一步完善

## 2020.3.15 增加支持 Object List  转 csv文件 方法

EcsvUtil.writeCSVFile(List<T> objectList,String filePath,Class clazz)

## 2020.3.14 增加支持 csv文件 转 Object List 方法

EcsvUtil.parseFile2ObjectList(String filePath, Class<T> clazz);
EcsvUtil.parseFile2ObjectList(File csvFile, Class<T> clazz)

## 2020.3.13 增加基本类型的支持

DataParseUtil

支持int、double、long、float、boolean、String

## 2020.3.9 完成CSV文件的读写

CsvFileUtil