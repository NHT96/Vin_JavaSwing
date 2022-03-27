/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.NHANKHAU;
import Viewer.frmTrangChu;
import java.sql.Connection;
import java.sql.Date;
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
public class NKMod {
    public static ArrayList<NHANKHAU> findTenNK(String tennk){
        ArrayList<NHANKHAU> ListNK = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "select STT,HOTENNK, NTNS, PHAINK, QUEQUAN, SDT from NHANKHAU where HOTENNK like ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "%" + tennk + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                NHANKHAU nk = new NHANKHAU(resultSet.getInt("STT"), resultSet.getString("HOTENNK"), resultSet.getString("NTNS"), resultSet.getString("PHAINK"),
                        resultSet.getString("QUEQUAN"), resultSet.getString("SDT"));
                ListNK.add(nk);
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
        return ListNK;
    }
    
    public static ArrayList<NHANKHAU> findDCNK(String qq){
        ArrayList<NHANKHAU> ListNK = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "select STT,HOTENNK, NTNS, PHAINK, QUEQUAN, SDT from NHANKHAU where QUEQUAN like ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "%" + qq + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                NHANKHAU nk = new NHANKHAU(resultSet.getInt("STT"), resultSet.getString("HOTENNK"), resultSet.getString("NTNS"), resultSet.getString("PHAINK"),
                        resultSet.getString("QUEQUAN"), resultSet.getString("SDT"));
                ListNK.add(nk);
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
        return ListNK;
    }
    public static ArrayList<NHANKHAU> findNK(String mch){
        ArrayList<NHANKHAU> ListNK = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "select STT,HOTENNK, NTNS, PHAINK, QUEQUAN, SDT from NHANKHAU where MACH = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1, "" + mch + "");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                NHANKHAU nk = new NHANKHAU(resultSet.getInt("STT"), resultSet.getString("HOTENNK"), resultSet.getString("NTNS"), resultSet.getString("PHAINK"),
                        resultSet.getString("QUEQUAN"), resultSet.getString("SDT"));
                ListNK.add(nk);
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
        return ListNK;
    }
    
    public static boolean STT(int stt) throws Exception{
        Connection connection = null;
        PreparedStatement statement = null;
            connection = Connect.getConnection();
            String sql = "select  stt from NHANKHAU where STT =  ?";
            statement = connection.prepareCall(sql);
            statement.setString(1,String.valueOf(stt));
            ResultSet resultSet = statement.executeQuery();
            
             if (resultSet.getRow() == 1) {
               return true;
            }
             else
                 return false;
     }   
    
    public static void insert(NHANKHAU nk) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "insert into NHANKHAU(MACH, HOTENNK, NTNS, PHAINK, QUEQUAN, SDT, MANV) values(?,?,?,?,?,?,?)";
            statement = connection.prepareCall(sql);

            statement.setString(1, nk.getMACH());
            statement.setString(2,nk.getHOTENNK());
            statement.setString(3, nk.getNTNS());
            statement.setString(4, nk.getPHAINK());
            statement.setString(5, nk.getQUEQUAN());
            statement.setString(6, nk.getSDT());
            statement.setString(7, nk.getMANV());
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(NKMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NKMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(NKMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
    public static void delete(String mch, int stt) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "delete from NHANKHAU where MACH = ? and STT = ?";
            statement = connection.prepareCall(sql);

            statement.setString(1, mch);
            statement.setString(2, String.valueOf(stt));

            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(NKMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NKMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(NKMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    
    public static void update(String ht, Date ns, String phai, String qq, String sdt,String mnv, String mch, int stt)
      {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "UPDATE NHANKHAU SET HOTENNK = ?, NTNS = ?, PHAINK = ?, QUEQUAN = ?, SDT = ?, MANV = ? WHERE MACH = ? and STT = ?";
            statement = connection.prepareCall(sql);

            statement.setString(1,ht);
            statement.setString(2,ns.toString());
            statement.setString(3, phai);
            statement.setString(4,qq);
            statement.setString(5,sdt);
            statement.setString(6, mnv);
            statement.setString(7, mch);
            statement.setString(8, String.valueOf(stt));
            statement.execute();
        } catch (SQLException e) {
            Logger.getLogger(NKMod.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(NKMod.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    Logger.getLogger(NKMod.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
      }
}
