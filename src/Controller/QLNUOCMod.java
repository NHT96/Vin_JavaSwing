/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.QLNUOC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class QLNUOCMod {
    public static ArrayList<QLNUOC> HDN(String mch){
        ArrayList<QLNUOC> ListHDN = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "select c.MAHDNUOC, q.NGAYCHOT, q.CHISONUOC, q.MANV from CTHDN c, QLNUOC q where c.MACH = ? and c.MAHDNUOC = q.MAHDNUOC";
            statement = connection.prepareCall(sql);
            statement.setString(1,mch);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                QLNUOC qln = new QLNUOC(resultSet.getString("MAHDNUOC"), resultSet.getString("NGAYCHOT"), resultSet.getFloat("CHISONUOC"), resultSet.getString("MANV"));
                ListHDN.add(qln);
            }
        } catch (SQLException e) {
            Logger.getLogger(QLNUOCMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QLNUOCMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(QLNUOCMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return ListHDN;
    }
    
    public static float csnm(String mch){
        float csn = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "SELECT TOP 1 q.CHISONUOC FROM QLNUOC q, CTHDN c where q.MAHDNUOC = c.MAHDNUOC and c.MACH = ? ORDER BY CHISONUOC desc";
            statement = connection.prepareCall(sql);
            statement.setString(1,mch);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                csn = resultSet.getShort("CHISONUOC");
            }
        } catch (SQLException e) {
            Logger.getLogger(QLNUOCMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QLNUOCMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(QLNUOCMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return csn;
    }

    public static void insert(QLNUOC n) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "insert into QLNUOC(MAHDNUOC, NGAYCHOT, CHISONUOC, MANV) values(?,?,?,?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, n.getMHDNUOC());
            statement.setString(2,n.getNGAYCHOT());
            statement.setString(3, String.valueOf(n.getCHISONUOC()));
            statement.setString(4, n.getMANV());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(QLNUOCMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QLNUOCMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(QLNUOCMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
