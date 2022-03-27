/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.THAMSO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class THAMSOMod {
        public static ArrayList<THAMSO> findAll(){
        ArrayList<THAMSO> ListTS = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "select * from THAMSO";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                THAMSO ts = new THAMSO(resultSet.getString("MATS"), resultSet.getString("TENTS"), resultSet.getFloat("GIATRI"));
                ListTS.add(ts);
            }
        } catch (SQLException e) {
            Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return ListTS;
        }
        
        public static String HDD(){
        String hdd = "";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "SELECT TOP 1 MAHDDIEN FROM QLDIEN ORDER BY MAHDDIEN DESC";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                hdd = resultSet.getString("MAHDDIEN");
            }
        } catch (SQLException e) {
            Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return hdd;
        }
        
         public static String HDN(){
        String hdn = "";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "SELECT TOP 1 MAHDNUOC FROM QLNUOC ORDER BY MAHDNUOC DESC";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                hdn = resultSet.getString("MAHDNUOC");
            }
        } catch (SQLException e) {
            Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return hdn;
        }
         
         public static String DVC(){
        String dvc = "";
        Connection connection = null;
        Statement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "SELECT TOP 1 MAHDDVC FROM DICHVUCONG ORDER BY MAHDDVC DESC";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                dvc = resultSet.getString("MAHDDVC");
            }
        } catch (SQLException e) {
            Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return dvc;
        }
         
         public static void update(float gt, String mts)
      {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "update THAMSO set GIATRI = ? where MATS = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1,String.valueOf(gt));
            statement.setString(2,mts);
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(THAMSOMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
      }
}
