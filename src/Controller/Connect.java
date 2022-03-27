/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class Connect {
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433; databaseName=JVVinHome;";
            String user = "sa";
            String pass = "123456";
            connection = DriverManager.getConnection(url,user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
}
    public static void closeConnection(Connection Conn){
        if(Conn != null){
            try {
                Conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}
