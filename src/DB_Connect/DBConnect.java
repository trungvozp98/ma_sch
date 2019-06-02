/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /* 
    *INTRODUCE
    - Sử dụng công nghệ JDBC (JAVA DATABASE CONNECTIVITY)
    - Hỗ trợ trong việc truy cập dữ liệu để thực hiện các tác vụ xử lý 
    - JDBC API cung cấp các phương thức để truy cập CSDL thông qua các cú pháp của JAVA
    - Có 2 gói hỗ trợ khi lập trình (java.sql.*  và javax.sql.*)
    * 
     -JDBC  có 2 thành phần 
      + JDBC API 
      + JDBC driver manager
     -JDBC có 4 loại driver 
      + JDBC ODBC(*)
      + Native API
      + Network Protocol
      + Native Protocol(*)(Kết nối trực tiếp đến cơ sở dữ liệu) 
 */
package DB_Connect;

import java.sql.Connection;
import java.sql.DriverManager; // quản lý các driver cho việc kết nối đến cơ sở dữ liệu
import java.sql.SQLException;  
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {

    public static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=QLGV";
    public static String user = "sa";
    public static String pass = "123456";
    public static Connection con;

    public static boolean open()  {
        try {
            if (con == null || con.isClosed()) {
                Class.forName(driver);  
                con = DriverManager.getConnection(url, user, pass);
                return true;
            }

        } catch (SQLException e) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
    }

    public static void close() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
