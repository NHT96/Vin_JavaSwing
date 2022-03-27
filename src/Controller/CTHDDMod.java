/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.CTHDD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class CTHDDMod {
    public static void insert(CTHDD ctd) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "insert into CTHDD(MACH, MAHDDIEN, DONGIADIEN, TONGLDTT, THANHTIEN) values(?,?,?,?,?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, ctd.getMACH());
            statement.setString(2, ctd.getMAHDDIEN());
            statement.setString(3, String.valueOf(ctd.getDONGIADIEN()));
            statement.setString(4, String.valueOf(ctd.getTONGLDTT()));
            statement.setString(5, String.valueOf(ctd.getTHANHTIEN()));
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(CTHDDMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CTHDDMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(CTHDDMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
}
