package models;
import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;

public class 路径 {
    public static void main(String[] args) {
        File directory = new File("");//设定为当前文件夹
//        System.out.println(directory.getCanonicalFile());//返回类型为File
//        System.out.println(directory.getCanonicalPath());//获取标准的路径  ，返回类型为String
        System.out.println(directory.getAbsoluteFile());//返回类型为File
        System.out.println(directory.getAbsolutePath());//获取绝对路径，返回类型为String


//定义基本目录
        Path base = Paths.get(directory.getAbsolutePath());

//resolve Business.java file
        Path path1 = base.resolve("Business.java");
        System.out.println(path1);

//resolve Server.java file
        Path path2 = base.resolve("Server.java");
        System.out.println(path2.toAbsolutePath());

        // 路径 拼接
        Path p = Paths.get(directory.getAbsolutePath(),"src","main","java","models","PDF","生成表格.pdf");
        System.out.println(p);

    }


}
