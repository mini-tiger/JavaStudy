package models.mysql;

import javax.sql.rowset.JdbcRowSet;
import java.sql.*;
import java.util.*;

public class connect {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://auto.itma.com.cn:3306/toolbox?characterEncoding=utf8&useSSL=false&autoReconnect=true";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "huaji2019!@#";

    public static void main(String[] args) {

//        for (int i = 0; i < 10; i++) //这里也可以改写为  for(String str:strArray) 这种形式

        System.out.println("this is ");
        MysqlThreadDemo T1 = new MysqlThreadDemo("aa", JDBC_DRIVER, DB_URL, USER, PASS);
        T1.start();


    }

}


class MysqlThreadDemo extends Thread {
    private Thread t;
    private String threadName;
    private String drive;
    private String dburl;
    private String user;
    private String pass;

    MysqlThreadDemo(String name, String drive1, String dburl1, String user1, String pass1) {
        drive = drive1;
        dburl = dburl1;
        user = user1;
        pass = pass1;
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    public void run() {

        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            Class.forName(drive);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(dburl, user, pass);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT `type`,label FROM crud_dict";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
//                int id  = rs.getInt("");
                String name = rs.getString("type");
                String url = rs.getString("label");

                // 输出数据
//                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
                Thread.sleep(5000);
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            System.out.print("sql\n");
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            System.out.print("class \n");
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");

    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}