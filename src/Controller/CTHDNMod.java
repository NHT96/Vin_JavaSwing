/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CTHDN;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class CTHDNMod {
    public static void insert(CTHDN ctn) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "insert into CTHDN(MACH, MAHDNUOC, DONGIANUOC, TONGLNTT, THANHTIEN) values(?,?,?,?,?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, ctn.getMACH());
            statement.setString(2, ctn.getMAHDNUOC());
            statement.setString(3, String.valueOf(ctn.getDONGIANUOC()));
            statement.setString(4, String.valueOf(ctn.getTONGLNTT()));
            statement.setString(5, String.valueOf(ctn.getTHANHTIEN()));
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(CTHDNMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CTHDNMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(CTHDNMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
