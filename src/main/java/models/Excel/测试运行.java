package models.Excel;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;


public class 测试运行 {
    public static void main(String[] args) throws Exception {
        测试运行.Read();
    }

    public static void Read() throws InvalidFormatException, IOException {
        File file = new File("C:\\JavaDev\\src\\models\\Excel\\测试.xlsx");
        UserXlsxReader reader = new UserXlsxReader();
        List<User> users = reader.read(file);
        System.out.println(users);
    }

}