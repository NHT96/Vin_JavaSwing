/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CANHO;
import Viewer.frmTrangChu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class CHMod {
    public static String TTch(String mch){
        String tt = "";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "select TRANGTHAI from CANHO where MACH = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, mch);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
               tt = resultSet.getString("TRANGTHAI");
            }
        } catch (SQLException e) {
            Logger.getLogger(CHMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CHMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(CHMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return tt;
    }
    public static String LCH(String mch){
        String lch = "";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "select MALOAI from CANHO where MACH = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, mch);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
               lch = resultSet.getString("MALOAI");
            }
        } catch (SQLException e) {
            Logger.getLogger(CHMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CHMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(CHMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return lch;
    }
    
    public void SetDaBan(String mch, String id)
        {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();
            String sql = "UPDATE CANHO  SET TRANGTHAI = 1, MANV = ? where MACH= ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, id);
            statement.setString(2,mch);
            int resultSet = statement.executeUpdate();
            if(resultSet==1)
                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
            else
                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại!");
        } catch (SQLException e) {
            Logger.getLogger(CHMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CHMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(CHMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        }

        public void SetChuaBan(String mch, String id)
        {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();
            String sql = "UPDATE CANHO  SET TRANGTHAI = 0, MANV = ? where MACH= ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, id);
            statement.setString(2,mch);
            int resultSet = statement.executeUpdate();
            if(resultSet==1)
                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
            else
                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thất bại!");
        } catch (SQLException e) {
            Logger.getLogger(CHMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CHMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(CHMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }       
        }
        }
}
