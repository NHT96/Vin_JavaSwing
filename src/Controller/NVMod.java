/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.NHANVIEN;
import static Viewer.frmDangNhap.IDht;
import Viewer.frmTrangChu;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class NVMod {
    public static ArrayList<NHANVIEN> findMANV(String manv){
        ArrayList<NHANVIEN> ListNV = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "select * from NHANVIEN where MANV like ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "%" + manv + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                NHANVIEN nv = new NHANVIEN(resultSet.getString("MANV"), resultSet.getString("HOTENNV"), resultSet.getString("NS"), resultSet.getString("PHAI"),
                        resultSet.getString("CHUCVU"), resultSet.getString("MAIL"));
                ListNV.add(nv);
            }
        } catch (SQLException e) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return ListNV;
    }
    
    public static ArrayList<NHANVIEN> findTenNV(String ht){
        ArrayList<NHANVIEN> ListNV = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "select * from NHANVIEN where HOTENNV like ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "%" + ht + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                NHANVIEN nv = new NHANVIEN(resultSet.getString("MANV"), resultSet.getString("HOTENNV"), resultSet.getString("NS"), resultSet.getString("PHAI"),
                        resultSet.getString("CHUCVU"), resultSet.getString("MAIL"));
                ListNV.add(nv);
            }
        } catch (SQLException e) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return ListNV;
    }
     public static String TNV(String id) throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        String name = "";
            connection = Connect.getConnection();
            String sql = "select  HOTENNV from NHANVIEN where MANV =  ?";
            statement = connection.prepareCall(sql);
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            
             while (resultSet.next()) {
               name = resultSet.getNString(1);
            }
         return name;
     }   
     
     public static String CV(String id) throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
        String chucvu = "";
            connection = Connect.getConnection();
            String sql = "select  CHUCVU from NHANVIEN where MANV =  ?";
            statement = connection.prepareCall(sql);
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            
             while (resultSet.next()) {
               chucvu = resultSet.getNString(1);
            }
         return chucvu;
     }   
     
     public static boolean MNV(String id) throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
            connection = Connect.getConnection();
            String sql = "select  MANV from NHANVIEN where MANV =  ?";
            statement = connection.prepareCall(sql);
            statement.setString(1,id);
            ResultSet resultSet = statement.executeQuery();
            
             if (resultSet.next()) {
               return true;
            }
             else
                 return false;
     }   
     
     public static ArrayList<NHANVIEN> findAll() {
        ArrayList<NHANVIEN> ListNV = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "select * from NHANVIEN";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                NHANVIEN nv = new NHANVIEN(resultSet.getString("MANV"), resultSet.getString("HOTENNV"), resultSet.getString("NS"), resultSet.getString("PHAI"),
                        resultSet.getString("CHUCVU"), resultSet.getString("MAIL"));
                ListNV.add(nv);
            }
        } catch (SQLException e) {
            Logger.getLogger(NVMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NVMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(NVMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return ListNV;
    }
     
      public static void insert(NHANVIEN nv) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "insert into NHANVIEN(MANV, HOTENNV, NS, PHAI, CHUCVU, MAIL ) values(?,?,?,?,?,?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, nv.getMANV());
            statement.setString(2,nv.getHOTENNV());
            statement.setString(3, nv.getNS());
            statement.setString(4, nv.getPHAI());
            statement.setString(5, nv.getCHUCVU());
            statement.setString(6, nv.getMAIL());

            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(NVMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NVMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(NVMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
      
      public static void delete(String mnv) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "delete from NHANVIEN where MANV = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, mnv);
            statement.execute();
            
            JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Không thể xóa nhân viên này!");          
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NVMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(NVMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
      
      public static void update(String mnv, String ht, Date ns, String phai, String cv, String mail)
      {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "update NHANVIEN set HOTENNV = ?, NS = ?, PHAI = ?, CHUCVU = ?, MAIL = ? where MANV = ?";
            statement = connection.prepareCall(sql);

            statement.setString(1,ht);
            statement.setString(2,ns.toString());
            statement.setString(3, phai);
            statement.setString(4,cv);
            statement.setString(5,mail);
            statement.setString(6, mnv);
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(NVMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NVMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(NVMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
      }
}
