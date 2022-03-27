/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.DICHVUCONG;
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
public class DVCMod {
    public static ArrayList<DICHVUCONG> DVC(String mch){
        ArrayList<DICHVUCONG> ListDVC = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "select d.MAHDDVC, d.NGAYTB from DICHVUCONG d, CTHDDVC c where MACH = ? and c.MAHDDVC = d.MAHDDVC";
            statement = connection.prepareCall(sql);
            statement.setString(1,mch);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DICHVUCONG dvc = new DICHVUCONG(resultSet.getString("MAHDDVC"), resultSet.getString("NGAYTB"));
                ListDVC.add(dvc);
            }
        } catch (SQLException e) {
            Logger.getLogger(DVCMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DVCMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(DVCMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return ListDVC;
    }
    
     public static void insert(DICHVUCONG c) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "insert into DICHVUCONG(MAHDDVC, MANV, NGAYTB) values(?,?,?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, c.getMHDDVC());
            statement.setString(2, c.getMANV());
            statement.setString(3, c.getNGAYTB());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(DVCMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DVCMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(DVCMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
