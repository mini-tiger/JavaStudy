package models.Excel;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//todo https://www.cnblogs.com/LiZhiW/p/4313789.html

public class 测试运行 {
    public static void main(String[] args) throws Exception {
        File directory = new File("");//设定为当前文件夹
//        System.out.println(directory.getCanonicalFile());//返回类型为File
//        System.out.println(directory.getCanonicalPath());//获取标准的路径  ，返回类型为String
//        System.out.println(directory.getAbsoluteFile());//返回类型为File
//        System.out.println(directory.getAbsolutePath());//获取绝对路径，返回类型为String

//定义基本目录
        Path base = Paths.get(directory.getAbsolutePath());
//        System.out.println(base);
        Path filepath = Paths.get(base.toString(), "src\\main\\java\\models\\Excel\\测试.xlsx");
        File file = filepath.toFile();

        测试运行.WriteTest(file); // 两种写方式
        测试运行.WriteTest2(file);
        测试运行.Read(file);// 两种读方式
        测试运行.Read2(file);
    }

    public static void Read(File file) throws InvalidFormatException, IOException {
        System.out.println("读取方式一");

//        File file = new File("C:\\JavaDev\\JavaStudy\\src\\main\\java\\models\\Excel\\测试.xlsx");
        UserXlsxReader reader = new UserXlsxReader();
        List<User> users = reader.read(file);
        System.out.println(users);
    }

    public static void Read2(File file) throws IOException, InvalidFormatException {
        System.out.println("读取方式二");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);
        //获取总行数
        int rows = sheet.getPhysicalNumberOfRows();

        Row row=sheet.getRow(0);
        int cols = row.getPhysicalNumberOfCells();
        System.out.println("列头");
        for (int j =0;j<=cols;j++){
                System.out.print(row.getCell(j) + "\t");
        }
        System.out.println();
        //去除表头，从第 1 行开始打印
        for (int i = 1; i < rows; i++) {
            row = sheet.getRow(i);
            //获取总列数
            cols = row.getPhysicalNumberOfCells();
            for (int j = 0; j < cols; j++) {
                System.out.print(row.getCell(j) + "\t");
            }
            System.out.println();
        }
    }


    public static void WriteTest(File file) throws InvalidFormatException, IOException {

        if (file.exists()) {
            file.delete();
        }
        WriteExcel writer = new WriteExcel(file);

        User user1 = new User("admin", "admin", "Administrator");
        User user2 = new User("user1", "user1", "Sally");
        User user3 = new User("user2", "zhangsan", "张三");

        writer.Write(user1);
        writer.Write(user2);
        writer.Write(user3);
        writer.Extract();


    }


    public static void WriteTest2(File file) throws InvalidFormatException, IOException {
//        File file = new File("H:/testxlsxbatch.xlsx");
        if (file.exists()) {
            file.delete();
        }
        WriteExcel writer = new WriteExcel(file);

        User user1 = new User("admin", "admin", "Administrator");
        User user2 = new User("user1", "user1", "Sally");
        User user3 = new User("user2", "zhangsan", "张三");

        List<User> users = new ArrayList<User>();

        users.add(user1);
        users.add(user2);
        users.add(user3);

        writer.Write(users);
        writer.Extract();

    }
}