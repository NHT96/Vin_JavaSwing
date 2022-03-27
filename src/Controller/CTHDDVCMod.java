/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CTHDDVC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class CTHDDVCMod {
    public static void insert(CTHDDVC dvc) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "insert into CTHDDVC(MACH, MAHDDVC, PHIANNINH, PHIMOITRUONG, PHIGIUXE, THANHTIEN) values(?,?,?,?,?,?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, dvc.getMACH());
            statement.setString(2, dvc.getMAHDDVC());
            statement.setString(3, String.valueOf(dvc.getPHIANNINH()));
            statement.setString(4, String.valueOf(dvc.getPHIMOITRUONG()));
            statement.setString(5, String.valueOf(dvc.getPHIGIUXE()));
            statement.setString(6, String.valueOf(dvc.getTHANHTIEN()));
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(CTHDDVCMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CTHDDVCMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(CTHDDVCMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
