/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.TAIKHOAN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class TKMod {
    public boolean findTK(String id){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Kết nối trực tiếp bằng lệnh
            //connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=JVVinHome;", "sa", "123456");
            
            connection = Connect.getConnection();        //Gọi lại thư viện
            String sql = "select * from TAIKHOAN where ID = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    public static void insert(String id, String pass, String mnv) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "insert into TAIKHOAN(ID, PASS, MANV) values(?,?,?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, id);
            statement.setString(2,pass);
            statement.setString(3, mnv);

            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(TKMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TKMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(TKMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
     public static void delete(String id, String mnv) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "delete from TAIKHOAN where ID = ? and MANV = ?";
            statement = connection.prepareCall(sql);

            statement.setString(1, id);
            statement.setString(2, mnv);

            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(TKMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(TKMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(TKMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
