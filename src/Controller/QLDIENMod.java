/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.QLDIEN;
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
public class QLDIENMod {
        public static ArrayList<QLDIEN> HDD(String mch){
        ArrayList<QLDIEN> ListHDD = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "select c.MAHDDIEN, q.NGAYKT, q.CHISODIEN, q.MANV from CTHDD c, QLDIEN q where c.MACH = ? and c.MAHDDIEN = q.MAHDDIEN";
            statement = connection.prepareCall(sql);
            statement.setString(1,mch);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                QLDIEN qld = new QLDIEN(resultSet.getString("MAHDDIEN"), resultSet.getString("NGAYKT"), resultSet.getFloat("CHISODIEN"), resultSet.getString("MANV"));
                ListHDD.add(qld);
            }
        } catch (SQLException e) {
            Logger.getLogger(QLDIENMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QLDIENMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(QLDIENMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return ListHDD;
    }
    
    public static float csdm(String mch){
        float csd = 0;
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "SELECT TOP 1 q.CHISODIEN FROM QLDIEN q, CTHDD c where q.MAHDDIEN = c.MAHDDIEN and c.MACH = ? ORDER BY CHISODIEN desc";
            statement = connection.prepareCall(sql);
            statement.setString(1,mch);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                csd = resultSet.getShort("CHISODIEN");
            }
        } catch (SQLException e) {
            Logger.getLogger(QLDIENMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QLDIENMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(QLDIENMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return csd;
    }
    
    public static void insert(QLDIEN d) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "insert into QLDIEN(MAHDDIEN, NGAYKT, CHISODIEN, MANV) values(?,?,?,?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, d.getMHDDIEN());
            statement.setString(2, d.getNGAYKT());
            statement.setString(3, String.valueOf(d.getCHISODIEN()));
            statement.setString(4, d.getMANV());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(QLDIENMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(QLDIENMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(QLDIENMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
