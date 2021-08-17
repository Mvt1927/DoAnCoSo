/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LMP.Control;

import java.sql.DriverManager;
import java.sql.Connection;

/**
 *
 * @author DELL
 */
public class ConnectData {
// MYSQL

    public static String driverName_MYSQL = "com.mysql.cj.jdbc.Driver";
    public static String dbURL_MYSQL = "jdbc:mysql://localhost:3306/lmp";
    public static String userDB_MYSQL = "root";
    public static String passDB_MYSQL = "1927";
// SQLSERVER    
    public static String driverName_SQLSERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String dbURL_SQLSERVER = "jdbc:sqlserver://localhost:1433;databaseName=lmp";
    public static String userDB_SQLSERVER = "sa";
    public static String passDB_SQLSERVER = "1927";

    public static Connection conn = null;
    public static int count = 0;

    @SuppressWarnings("CallToPrintStackTrace")
    public static Connection getConnection() {
        try {
            Class.forName(driverName_MYSQL);
            conn = DriverManager.getConnection(dbURL_MYSQL, userDB_MYSQL, passDB_MYSQL);//Xác thực bằng sql server
            //conn = DriverManager.getConnection(dbURL);//Xác thực bằng windows - không cần user và pass
            if (count == 0) {
                System.out.println("Connect mysql successfully!");
                count = count + 1;
            }

        } catch (Exception ex) {
            System.out.println("Connect mysql failure!");
            try {
                Class.forName(driverName_SQLSERVER);
                conn = DriverManager.getConnection(dbURL_SQLSERVER, userDB_SQLSERVER, passDB_SQLSERVER);
                if (count == 0) {
                    System.out.println("Connect sqlserver successfully!");
                    count = count + 1;
                }

            } catch (Exception e) {
                System.out.println("Connect sqlserver failure!");
                System.out.println("Mysql Exception ");
                ex.printStackTrace();
                System.out.println("SqlServer Exception ");
                e.printStackTrace();
            }
        }
        return conn;
    }
}
