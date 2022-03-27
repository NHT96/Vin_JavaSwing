/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import Controller.CHMod;
import Controller.CTHDDMod;
import Controller.CTHDDVCMod;
import Controller.CTHDNMod;
import Controller.Connect;
import Controller.DVCMod;
import Controller.NKMod;
import Controller.NVMod;
import Controller.QLDIENMod;
import Controller.QLNUOCMod;
import Controller.THAMSOMod;
import Controller.TKMod;
import Model.CANHO;
import Model.CTHDD;
import Model.CTHDDVC;
import Model.CTHDN;
import Model.DICHVUCONG;
import Model.NHANKHAU;
import Model.NHANVIEN;
import Model.QLDIEN;
import Model.QLNUOC;
import Model.THAMSO;
import static Viewer.frmDangNhap.IDht;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Admin
 */
public class frmTrangChu extends javax.swing.JFrame {

    DefaultTableModel modelnv;
    DefaultTableModel modelnk;
    DefaultTableModel hdd;
    DefaultTableModel hdn;
    DefaultTableModel dvc;
    ArrayList<NHANVIEN> ListNV = new ArrayList<>();
    ArrayList<NHANKHAU> ListNK = new ArrayList<>();
    ArrayList<CANHO> ListCH = new ArrayList<>();
     ArrayList<QLDIEN> ListHDD = new ArrayList<>();
     ArrayList<QLNUOC> ListHDN = new ArrayList<>();
     ArrayList<DICHVUCONG> ListDVC = new ArrayList<>();
     ArrayList<THAMSO> ListTS = new ArrayList<>();
    static String bl ="";
    static String ta ="";
    static String ch ="";
    
    /**
     * Creates new form TrangChu
     */
    public frmTrangChu() throws Exception {
        //Form chính
         initComponents();
         setLocationRelativeTo(null);
         btnDoiMK.setText(IDht);
         txtCHlc.setEditable(false);
         setdisable(false);
         //Hiển thị nhân viên và chức năng
            modelnv = (DefaultTableModel) tbQLNV.getModel();
            ShowNV();
            setButtonNV(true);
            setKhoaNV(false);
            setNullNV();
         //Hiển thị nhân khẩu
            modelnk = (DefaultTableModel) tbQLNK.getModel();
            LoadCBO();
            setButtonNK(true);
            setKhoaNK(false);
            setNullNK();
         //Hiển thị các thông số dịch vụ
            LoadDG();
            setdisDG();
            setEditDV();
            btnLuuDG.setVisible(false);
            PhanQuyen();
    }
    
    private void setdisDG(){
        txtDGD.setEditable(false);
        txtDGN.setEditable(false);
        txtPAN.setEditable(false);
        txtPMT.setEditable(false);
        txtPSH.setEditable(false);
    }
    
    private void LoadDG()
    {
        ListTS = THAMSOMod.findAll();
        txtDGD.setText(String.valueOf(ListTS.get(1).getGIATRI()));
        txtDGN.setText(String.valueOf(ListTS.get(3).getGIATRI()));
        txtPAN.setText(String.valueOf(ListTS.get(0).getGIATRI()));
        txtPMT.setText(String.valueOf(ListTS.get(4).getGIATRI()));
        txtPSH.setText(String.valueOf(ListTS.get(2).getGIATRI()));
        txtThangD.setText(java.time.LocalDate.now().toString());
        txtThangN.setText(java.time.LocalDate.now().toString());
        txtThangDVC.setText(java.time.LocalDate.now().toString());
    }
    
    private void LoadSHD(){
        String hdd = THAMSOMod.HDD();
        int tmp = Integer.valueOf(hdd.substring(1)) + 1;
        txtHDD.setText("D"+String.valueOf(tmp));
        
        String hdn = THAMSOMod.HDN();
        int tmpn = Integer.valueOf(hdn.substring(1)) + 1;
        txtHDN.setText("N"+String.valueOf(tmpn));
        
        String dvc = THAMSOMod.DVC();
        int tmpdvc = Integer.valueOf(dvc.substring(1)) + 1;
        txtHDdvc.setText("C"+String.valueOf(tmpdvc));
    }
    
    private void PhanQuyen() throws Exception
    {
        String cv = NVMod.CV(frmDangNhap.mnv);
        if(cv.contains("Nhân viên kinh doanh")==true)
        {
               enSDG(false);
               btnQLTK.setEnabled(false);
               btnThemNV.setEnabled(false);
               btnSuaNv.setEnabled(false);
               btnXoaNV.setEnabled(false);
        }
        if(cv.contains("Nhân viên giám sát")==true)
        {
               enSDG(false);
               btnQLTK.setEnabled(false);
               btnThemNV.setEnabled(false);
               btnSuaNv.setEnabled(false);
               btnXoaNV.setEnabled(false);
               btnCNch.setEnabled(false);
        }
        if(cv.contains("Nhân viên an ninh")==true)
        {
               enSDG(false);
               btnQLTK.setEnabled(false);
               btnThemNV.setEnabled(false);
               btnSuaNv.setEnabled(false);
               btnXoaNV.setEnabled(false);
               btnCNch.setEnabled(false);
        }
        if(cv.contains("Giám đốc")==true)
        {
               enSDG(true);
        }
        if(cv.contains("Phó giám đốc")==true)
        {
               enSDG(true);
        }
    }
    
    private void setEditDV(){
        txtHDD.setEditable(false);
        txtHDN.setEditable(false);
        txtHDdvc.setEditable(false);
        txtThangD.setEditable(false);
        txtThangN.setEditable(false);
        txtThangDVC.setEditable(false);
        txtLDTT.setEditable(false);
        txtLNTT.setEditable(false);
        txtTD.setEditable(false);
        txtTN.setEditable(false);
    }
    
    private void setdisable(boolean  a)
    {
        rdnCB.setEnabled(a);
        rdnDB.setEnabled(a);
        btnLUUch.setEnabled(a);
        btnHUYch.setEnabled(a);
        btnCNch.setEnabled(!a);
    }
    
     private void ShowNV() {
        ListNV = NVMod.findAll();
        modelnv.setRowCount(0);
        ListNV.forEach((NHANVIEN) -> {
            modelnv.addRow(new Object[]{
                NHANVIEN.getMANV(), NHANVIEN.getHOTENNV(), NHANVIEN.getNS(), NHANVIEN.getPHAI(), NHANVIEN.getCHUCVU(),NHANVIEN.getMAIL()});
        });
    }
     
     private void ShowNK() {
        ListNK = NKMod.findNK(cboMCH.getSelectedItem().toString());
        modelnk.setRowCount(0);
        ListNK.forEach((NHANKHAU) -> {
            modelnk.addRow(new Object[]{
                NHANKHAU.getSTT(), NHANKHAU.getHOTENNK(), NHANKHAU.getNTNS(), NHANKHAU.getPHAINK(), NHANKHAU.getQUEQUAN(),NHANKHAU.getSDT()});
        });
    }
     
     public void LoadCBO()
     {
         Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = Connect.getConnection();

            String sql = "select MACH from CANHO where TRANGTHAI = 1";
            statement = connection.prepareCall(sql);
            ResultSet resultSet = statement.executeQuery();
            Vector data = new Vector();
            while (resultSet.next()) {
                data.add(resultSet.getString(1));
            }
            DefaultComboBoxModel cmbModel = new DefaultComboBoxModel(data);
            cboMCH.setModel(cmbModel);
            cboMCHdv.setModel(cmbModel);
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
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpnTC = new javax.swing.JTabbedPane();
        pnCanHo = new javax.swing.JPanel();
        pnTang = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnT02 = new javax.swing.JButton();
        btnT01 = new javax.swing.JButton();
        btnT04 = new javax.swing.JButton();
        btnT03 = new javax.swing.JButton();
        btnT05 = new javax.swing.JButton();
        btnT06 = new javax.swing.JButton();
        btnT07 = new javax.swing.JButton();
        btnT08 = new javax.swing.JButton();
        btnT09 = new javax.swing.JButton();
        btnT10 = new javax.swing.JButton();
        btnT11 = new javax.swing.JButton();
        btnT12 = new javax.swing.JButton();
        btnT13 = new javax.swing.JButton();
        btnT14 = new javax.swing.JButton();
        btnT15 = new javax.swing.JButton();
        btnT16 = new javax.swing.JButton();
        btnT17 = new javax.swing.JButton();
        btnT18 = new javax.swing.JButton();
        btnT19 = new javax.swing.JButton();
        btnT20 = new javax.swing.JButton();
        btnT21 = new javax.swing.JButton();
        btnT22 = new javax.swing.JButton();
        btnT23 = new javax.swing.JButton();
        btnT24 = new javax.swing.JButton();
        pnPhong = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btnCH2 = new javax.swing.JButton();
        btnCH1 = new javax.swing.JButton();
        btnCH4 = new javax.swing.JButton();
        btnCH3 = new javax.swing.JButton();
        btnCH5 = new javax.swing.JButton();
        btnCH6 = new javax.swing.JButton();
        pnTTCH = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCHlc = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cboLch = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        rdnDB = new javax.swing.JRadioButton();
        rdnCB = new javax.swing.JRadioButton();
        btnCNch = new javax.swing.JButton();
        btnLUUch = new javax.swing.JButton();
        btnHUYch = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        pnBlock = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        btnBLa = new javax.swing.JButton();
        btnBLb = new javax.swing.JButton();
        btnBLc = new javax.swing.JButton();
        btnBLd = new javax.swing.JButton();
        btnBLe = new javax.swing.JButton();
        btnBLf = new javax.swing.JButton();
        btnBLg = new javax.swing.JButton();
        btnBLh = new javax.swing.JButton();
        btnBLi = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnNhanVien = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtQLNsnv = new javax.swing.JTextField();
        txtPhaiNV = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cboChucVuNV = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtMailNV = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtQLMnv = new javax.swing.JTextField();
        txtQLHtnv = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbQLNV = new javax.swing.JTable();
        btnThemNV = new javax.swing.JButton();
        btnXoaNV = new javax.swing.JButton();
        btnSuaNv = new javax.swing.JButton();
        btnLuuNV = new javax.swing.JButton();
        btnKLNV = new javax.swing.JButton();
        btnThoatNV = new javax.swing.JButton();
        btnQLTK = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        pnNhanKhau = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtNTNSnk = new javax.swing.JTextField();
        txtPHAInk = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtSDTnk = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtSTTnk = new javax.swing.JTextField();
        txtHTnk = new javax.swing.JTextField();
        txtQUEQUANnk = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbQLNK = new javax.swing.JTable();
        btnTHEMnk = new javax.swing.JButton();
        btnXOAnk = new javax.swing.JButton();
        btnSUAnk = new javax.swing.JButton();
        btnLUUnk = new javax.swing.JButton();
        btnKLnk = new javax.swing.JButton();
        btnTHOATnk = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        cboMCH = new javax.swing.JComboBox<>();
        btnXEMTTnk = new javax.swing.JButton();
        pnDichVu = new javax.swing.JPanel();
        btnSuaDGdvc = new javax.swing.JButton();
        btnHDdvc = new javax.swing.JButton();
        txtHDdvc = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtThangDVC = new javax.swing.JTextField();
        btnXTTdv = new javax.swing.JButton();
        txtTdvc = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtPSH = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtPMT = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtCSD = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtHDD = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txtThangD = new javax.swing.JTextField();
        txtTD = new javax.swing.JTextField();
        txtDGD = new javax.swing.JTextField();
        txtLDTT = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHDD = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbDVC = new javax.swing.JTable();
        btnCNd = new javax.swing.JButton();
        btnCNdvc = new javax.swing.JButton();
        rdnPAN = new javax.swing.JRadioButton();
        rdnPMT = new javax.swing.JRadioButton();
        rdnPSH = new javax.swing.JRadioButton();
        btnSuaDGd = new javax.swing.JButton();
        btnHDD = new javax.swing.JButton();
        jLabel54 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tbHDN = new javax.swing.JTable();
        btnCNn = new javax.swing.JButton();
        txtCSN = new javax.swing.JTextField();
        btnSuaDGn = new javax.swing.JButton();
        btnHDN = new javax.swing.JButton();
        txtHDN = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        txtThangN = new javax.swing.JTextField();
        txtTN = new javax.swing.JTextField();
        txtDGN = new javax.swing.JTextField();
        txtLNTT = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        txtPAN = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        cboMCHdv = new javax.swing.JComboBox<>();
        btnLuuDG = new javax.swing.JButton();
        pnThongKe = new javax.swing.JPanel();
        jLabel61 = new javax.swing.JLabel();
        pnTimKiem = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbTKNV = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbTKnk = new javax.swing.JTable();
        jLabel34 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        btnTKnk = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        txtTKTennk = new javax.swing.JTextField();
        txtTKDCnk = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        btnTKnv = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtTenNVtk = new javax.swing.JTextField();
        txtMANVtk = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnDX = new javax.swing.JButton();
        btnDoiMK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PHẦN MỀM QUẢN LÝ CHUNG CƯ VINHOME");
        setResizable(false);

        tpnTC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        pnTang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel5.setText("TẦNG");

        btnT02.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT02.setText("Tầng 02");
        btnT02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT02ActionPerformed(evt);
            }
        });

        btnT01.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT01.setText("Tầng 01");
        btnT01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT01ActionPerformed(evt);
            }
        });

        btnT04.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT04.setText("Tầng 04");
        btnT04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT04ActionPerformed(evt);
            }
        });

        btnT03.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT03.setText("Tầng 03");
        btnT03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT03ActionPerformed(evt);
            }
        });

        btnT05.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT05.setText("Tầng 05");
        btnT05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT05ActionPerformed(evt);
            }
        });

        btnT06.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT06.setText("Tầng 06");
        btnT06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT06ActionPerformed(evt);
            }
        });

        btnT07.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT07.setText("Tầng 07");
        btnT07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT07ActionPerformed(evt);
            }
        });

        btnT08.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT08.setText("Tầng 08");
        btnT08.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT08ActionPerformed(evt);
            }
        });

        btnT09.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT09.setText("Tầng 09");
        btnT09.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT09ActionPerformed(evt);
            }
        });

        btnT10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT10.setText("Tầng 10");
        btnT10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT10ActionPerformed(evt);
            }
        });

        btnT11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT11.setText("Tầng 11");
        btnT11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT11ActionPerformed(evt);
            }
        });

        btnT12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT12.setText("Tầng 12");
        btnT12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT12ActionPerformed(evt);
            }
        });

        btnT13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT13.setText("Tầng 13");
        btnT13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT13ActionPerformed(evt);
            }
        });

        btnT14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT14.setText("Tầng 14");
        btnT14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT14ActionPerformed(evt);
            }
        });

        btnT15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT15.setText("Tầng 15");
        btnT15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT15ActionPerformed(evt);
            }
        });

        btnT16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT16.setText("Tầng 16");
        btnT16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT16ActionPerformed(evt);
            }
        });

        btnT17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT17.setText("Tầng 17");
        btnT17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT17ActionPerformed(evt);
            }
        });

        btnT18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT18.setText("Tầng 18");
        btnT18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT18ActionPerformed(evt);
            }
        });

        btnT19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT19.setText("Tầng 19");
        btnT19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT19ActionPerformed(evt);
            }
        });

        btnT20.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT20.setText("Tầng 20");
        btnT20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT20ActionPerformed(evt);
            }
        });

        btnT21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT21.setText("Tầng 21");
        btnT21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT21ActionPerformed(evt);
            }
        });

        btnT22.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT22.setText("Tầng 22");
        btnT22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT22ActionPerformed(evt);
            }
        });

        btnT23.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT23.setText("Tầng 23");
        btnT23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT23ActionPerformed(evt);
            }
        });

        btnT24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnT24.setText("Tầng 24");
        btnT24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnT24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTangLayout = new javax.swing.GroupLayout(pnTang);
        pnTang.setLayout(pnTangLayout);
        pnTangLayout.setHorizontalGroup(
            pnTangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTangLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(187, 187, 187))
            .addGroup(pnTangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnTangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTangLayout.createSequentialGroup()
                        .addComponent(btnT01)
                        .addGap(26, 26, 26)
                        .addComponent(btnT02)
                        .addGap(27, 27, 27)
                        .addComponent(btnT03)
                        .addGap(31, 31, 31)
                        .addComponent(btnT04))
                    .addGroup(pnTangLayout.createSequentialGroup()
                        .addComponent(btnT05)
                        .addGap(26, 26, 26)
                        .addComponent(btnT06)
                        .addGap(27, 27, 27)
                        .addComponent(btnT07)
                        .addGap(31, 31, 31)
                        .addComponent(btnT08))
                    .addGroup(pnTangLayout.createSequentialGroup()
                        .addComponent(btnT09)
                        .addGap(26, 26, 26)
                        .addComponent(btnT10)
                        .addGap(27, 27, 27)
                        .addComponent(btnT11)
                        .addGap(31, 31, 31)
                        .addComponent(btnT12))
                    .addGroup(pnTangLayout.createSequentialGroup()
                        .addComponent(btnT13)
                        .addGap(26, 26, 26)
                        .addComponent(btnT14)
                        .addGap(27, 27, 27)
                        .addComponent(btnT15)
                        .addGap(31, 31, 31)
                        .addComponent(btnT16))
                    .addGroup(pnTangLayout.createSequentialGroup()
                        .addComponent(btnT17)
                        .addGap(26, 26, 26)
                        .addComponent(btnT18)
                        .addGap(27, 27, 27)
                        .addComponent(btnT19)
                        .addGap(31, 31, 31)
                        .addComponent(btnT20))
                    .addGroup(pnTangLayout.createSequentialGroup()
                        .addComponent(btnT21)
                        .addGap(26, 26, 26)
                        .addComponent(btnT22)
                        .addGap(27, 27, 27)
                        .addComponent(btnT23)
                        .addGap(31, 31, 31)
                        .addComponent(btnT24)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        pnTangLayout.setVerticalGroup(
            pnTangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTangLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(pnTangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnT02)
                    .addComponent(btnT01)
                    .addComponent(btnT04)
                    .addComponent(btnT03))
                .addGap(18, 18, 18)
                .addGroup(pnTangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnT06)
                    .addComponent(btnT05)
                    .addComponent(btnT08)
                    .addComponent(btnT07))
                .addGap(18, 18, 18)
                .addGroup(pnTangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnT10)
                    .addComponent(btnT09)
                    .addComponent(btnT12)
                    .addComponent(btnT11))
                .addGap(18, 18, 18)
                .addGroup(pnTangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnT14)
                    .addComponent(btnT13)
                    .addComponent(btnT16)
                    .addComponent(btnT15))
                .addGap(18, 18, 18)
                .addGroup(pnTangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnT18)
                    .addComponent(btnT17)
                    .addComponent(btnT20)
                    .addComponent(btnT19))
                .addGap(18, 18, 18)
                .addGroup(pnTangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnT22)
                    .addComponent(btnT21)
                    .addComponent(btnT24)
                    .addComponent(btnT23))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnPhong.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel14.setText("CĂN HỘ");

        btnCH2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCH2.setText("CH 02");
        btnCH2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCH2ActionPerformed(evt);
            }
        });

        btnCH1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCH1.setText("CH 01");
        btnCH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCH1ActionPerformed(evt);
            }
        });

        btnCH4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCH4.setText("CH 04");
        btnCH4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCH4ActionPerformed(evt);
            }
        });

        btnCH3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCH3.setText("CH 03");
        btnCH3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCH3ActionPerformed(evt);
            }
        });

        btnCH5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCH5.setText("CH 05");
        btnCH5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCH5ActionPerformed(evt);
            }
        });

        btnCH6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCH6.setText("CH 06");
        btnCH6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCH6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnPhongLayout = new javax.swing.GroupLayout(pnPhong);
        pnPhong.setLayout(pnPhongLayout);
        pnPhongLayout.setHorizontalGroup(
            pnPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPhongLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(113, 113, 113))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnPhongLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(pnPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnCH3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCH1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCH5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(pnPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCH2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCH4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCH6, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                .addGap(29, 29, 29))
        );
        pnPhongLayout.setVerticalGroup(
            pnPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnPhongLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel14)
                .addGap(18, 18, 18)
                .addGroup(pnPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCH1)
                    .addComponent(btnCH2))
                .addGap(57, 57, 57)
                .addGroup(pnPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCH4)
                    .addComponent(btnCH3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCH5)
                    .addComponent(btnCH6))
                .addGap(57, 57, 57))
        );

        pnTTCH.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("THÔNG TIN CĂN HỘ");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setText("Mã Căn Hộ");

        txtCHlc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtCHlc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCHlc.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCHlcCaretUpdate(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setText("Loại Căn Hộ");

        cboLch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cboLch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Loại 01", "Loại 02", "Loại 03" }));
        cboLch.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel17.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel17.setText("Trạng Thái");

        rdnDB.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rdnDB.setText("Đã bán");

        rdnCB.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rdnCB.setText("Chưa bán");

        btnCNch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCNch.setText("Cập Nhật");
        btnCNch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCNchActionPerformed(evt);
            }
        });

        btnLUUch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLUUch.setText("Lưu");
        btnLUUch.setPreferredSize(new java.awt.Dimension(89, 25));
        btnLUUch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLUUchActionPerformed(evt);
            }
        });

        btnHUYch.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnHUYch.setText("Hủy");
        btnHUYch.setPreferredSize(new java.awt.Dimension(89, 25));
        btnHUYch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHUYchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnTTCHLayout = new javax.swing.GroupLayout(pnTTCH);
        pnTTCH.setLayout(pnTTCHLayout);
        pnTTCHLayout.setHorizontalGroup(
            pnTTCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTTCHLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(250, 250, 250))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTTCHLayout.createSequentialGroup()
                .addGroup(pnTTCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTTCHLayout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel15))
                    .addGroup(pnTTCHLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(txtCHlc, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnTTCHLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnCNch)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addGroup(pnTTCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnTTCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnTTCHLayout.createSequentialGroup()
                            .addComponent(btnLUUch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(144, 144, 144)
                            .addComponent(btnHUYch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(44, 44, 44))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnTTCHLayout.createSequentialGroup()
                            .addComponent(jLabel16)
                            .addGap(83, 83, 83)))
                    .addGroup(pnTTCHLayout.createSequentialGroup()
                        .addComponent(cboLch, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
            .addGroup(pnTTCHLayout.createSequentialGroup()
                .addGroup(pnTTCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnTTCHLayout.createSequentialGroup()
                        .addGap(204, 204, 204)
                        .addComponent(rdnDB)
                        .addGap(92, 92, 92)
                        .addComponent(rdnCB))
                    .addGroup(pnTTCHLayout.createSequentialGroup()
                        .addGap(287, 287, 287)
                        .addComponent(jLabel17)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnTTCHLayout.setVerticalGroup(
            pnTTCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTTCHLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(31, 31, 31)
                .addGroup(pnTTCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTTCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCHlc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnTTCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdnDB)
                    .addComponent(rdnCB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(pnTTCHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCNch)
                    .addComponent(btnLUUch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHUYch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jLabel18.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel18.setText("Loại 1: Có 3 phòng ngủ, diện tích 80m2");

        jLabel19.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel19.setText("Loại 2: Có 2 phòng ngủ, diện tích 60m2");

        jLabel20.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel20.setText("Loại 3: Có 1 phòng ngủ, diện tích 40m2");

        jLabel21.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel21.setText("Tầng Lẻ: 3 căn hộ loại 1, 2 căn hộ loại 2, 1 căn hộ loại 3");

        jLabel22.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel22.setText("Tầng Chẵn: 1 căn hộ loại 1, 2 căn hộ loại 2, 3 căn hộ loại 3");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel23.setText("Thông tin loại căn hộ và số căn hộ thuộc tầng");

        jToolBar1.setRollover(true);

        pnBlock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("BLOCK");

        btnBLa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnBLa.setText("Block A");
        btnBLa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBLaActionPerformed(evt);
            }
        });

        btnBLb.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnBLb.setText("Block B");
        btnBLb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBLbActionPerformed(evt);
            }
        });

        btnBLc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnBLc.setText("Block C");
        btnBLc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBLcActionPerformed(evt);
            }
        });

        btnBLd.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnBLd.setText("Block D");
        btnBLd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBLdActionPerformed(evt);
            }
        });

        btnBLe.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnBLe.setText("Block E");
        btnBLe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBLeActionPerformed(evt);
            }
        });

        btnBLf.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnBLf.setText("Block F");
        btnBLf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBLfActionPerformed(evt);
            }
        });

        btnBLg.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnBLg.setText("Block G");
        btnBLg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBLgActionPerformed(evt);
            }
        });

        btnBLh.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnBLh.setText("Block H");
        btnBLh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBLhActionPerformed(evt);
            }
        });

        btnBLi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnBLi.setText("Block I");
        btnBLi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBLiActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel3.setText("Bấm chọn Block, Tầng, Căn hộ cần tìm");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel4.setText("Bấm lại lần nữa để bỏ chọn mới chọn lại Block, Tầng, Căn hộ khác");

        javax.swing.GroupLayout pnBlockLayout = new javax.swing.GroupLayout(pnBlock);
        pnBlock.setLayout(pnBlockLayout);
        pnBlockLayout.setHorizontalGroup(
            pnBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBlockLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBLd)
                    .addComponent(btnBLa)
                    .addComponent(btnBLg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBLb)
                    .addComponent(btnBLe)
                    .addComponent(btnBLh))
                .addGap(20, 20, 20)
                .addGroup(pnBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBlockLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnBLi))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBlockLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(pnBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBLf, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBLc, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(22, 22, 22))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBlockLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
            .addGroup(pnBlockLayout.createSequentialGroup()
                .addGroup(pnBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnBlockLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(jLabel2))
                    .addGroup(pnBlockLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnBlockLayout.setVerticalGroup(
            pnBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnBlockLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(pnBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBLa)
                    .addComponent(btnBLb)
                    .addComponent(btnBLc))
                .addGap(27, 27, 27)
                .addGroup(pnBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBLd)
                    .addComponent(btnBLe)
                    .addComponent(btnBLf))
                .addGap(29, 29, 29)
                .addGroup(pnBlockLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBLg)
                    .addComponent(btnBLh)
                    .addComponent(btnBLi))
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jToolBar1.add(pnBlock);

        javax.swing.GroupLayout pnCanHoLayout = new javax.swing.GroupLayout(pnCanHo);
        pnCanHo.setLayout(pnCanHoLayout);
        pnCanHoLayout.setHorizontalGroup(
            pnCanHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCanHoLayout.createSequentialGroup()
                .addGroup(pnCanHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnCanHoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnTang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnCanHoLayout.createSequentialGroup()
                        .addGroup(pnCanHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnCanHoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnCanHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22)))
                            .addGroup(pnCanHoLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jLabel23)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pnTTCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnCanHoLayout.setVerticalGroup(
            pnCanHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCanHoLayout.createSequentialGroup()
                .addGroup(pnCanHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnPhong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnTang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnCanHoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnCanHoLayout.createSequentialGroup()
                        .addComponent(pnTTCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnCanHoLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel21)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel22)
                        .addGap(46, 46, 46))))
        );

        tpnTC.addTab("CĂN HỘ", pnCanHo);

        pnNhanVien.setPreferredSize(new java.awt.Dimension(80, 30));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtQLNsnv.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtPhaiNV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Mã NV: ");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Họ và Tên:");

        cboChucVuNV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cboChucVuNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giám đốc", "Phó giám đốc", "Nhân viên giám sát", "Nhân viên kinh doanh", "Nhân viên an ninh" }));
        cboChucVuNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboChucVuNVMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("NTNS:");

        txtMailNV.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Phái:");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Chức Vụ:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Mail:");

        txtQLMnv.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtQLHtnv.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel32.setFont(new java.awt.Font("Times New Roman", 2, 11)); // NOI18N
        jLabel32.setText("Thông tin nhân viên");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtQLMnv)
                    .addComponent(txtQLHtnv)
                    .addComponent(txtQLNsnv)
                    .addComponent(txtPhaiNV)
                    .addComponent(cboChucVuNV, 0, 216, Short.MAX_VALUE)
                    .addComponent(txtMailNV))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel32)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel32)
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtQLMnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtQLHtnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtQLNsnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtPhaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cboChucVuNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtMailNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbQLNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tbQLNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Họ Tên", "NTNS", "Phái", "Chức Vụ", "Mail"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Byte.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbQLNV.getTableHeader().setReorderingAllowed(false);
        tbQLNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLNVMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbQLNV);

        btnThemNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThemNV.setText("Thêm");
        btnThemNV.setMaximumSize(new java.awt.Dimension(60, 25));
        btnThemNV.setMinimumSize(new java.awt.Dimension(60, 25));
        btnThemNV.setPreferredSize(new java.awt.Dimension(100, 30));
        btnThemNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNVActionPerformed(evt);
            }
        });

        btnXoaNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXoaNV.setText("Xóa");
        btnXoaNV.setMaximumSize(new java.awt.Dimension(70, 30));
        btnXoaNV.setMinimumSize(new java.awt.Dimension(70, 30));
        btnXoaNV.setPreferredSize(new java.awt.Dimension(100, 30));
        btnXoaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaNVActionPerformed(evt);
            }
        });

        btnSuaNv.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSuaNv.setText("Sửa");
        btnSuaNv.setPreferredSize(new java.awt.Dimension(100, 30));
        btnSuaNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaNvActionPerformed(evt);
            }
        });

        btnLuuNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLuuNV.setText("Lưu");
        btnLuuNV.setPreferredSize(new java.awt.Dimension(100, 30));
        btnLuuNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuNVActionPerformed(evt);
            }
        });

        btnKLNV.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnKLNV.setText("Không Lưu");
        btnKLNV.setPreferredSize(new java.awt.Dimension(80, 30));
        btnKLNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKLNVActionPerformed(evt);
            }
        });

        btnThoatNV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThoatNV.setText("Thoát");
        btnThoatNV.setPreferredSize(new java.awt.Dimension(100, 30));

        btnQLTK.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnQLTK.setText("Quản Lí TK");
        btnQLTK.setPreferredSize(new java.awt.Dimension(80, 30));
        btnQLTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLTKActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("QUẢN LÍ NHÂN VIÊN");

        javax.swing.GroupLayout pnNhanVienLayout = new javax.swing.GroupLayout(pnNhanVien);
        pnNhanVien.setLayout(pnNhanVienLayout);
        pnNhanVienLayout.setHorizontalGroup(
            pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhanVienLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(323, 323, 323))
            .addGroup(pnNhanVienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(pnNhanVienLayout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addComponent(btnQLTK, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSuaNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLuuNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnKLNV, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThoatNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );
        pnNhanVienLayout.setVerticalGroup(
            pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhanVienLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnNhanVienLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhanVienLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addGroup(pnNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnQLTK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuuNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKLNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoatNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(102, Short.MAX_VALUE))
        );

        tpnTC.addTab("NHÂN VIÊN", pnNhanVien);

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel24.setText("QUẢN LÍ NHÂN KHẨU");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtNTNSnk.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtPHAInk.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("STT:");

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setText("Họ và Tên:");

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("NTNS:");

        txtSDTnk.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setText("Phái:");

        jLabel29.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel29.setText("Quê Quán:");

        jLabel30.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel30.setText("SĐT:");

        txtSTTnk.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtHTnk.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtQUEQUANnk.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel31.setFont(new java.awt.Font("Times New Roman", 2, 11)); // NOI18N
        jLabel31.setText("Thông tin nhân khẩu");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSTTnk, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                            .addComponent(txtHTnk)
                            .addComponent(txtNTNSnk)
                            .addComponent(txtPHAInk)
                            .addComponent(txtSDTnk)
                            .addComponent(txtQUEQUANnk)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel31)
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtSTTnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtHTnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtNTNSnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(txtPHAInk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtQUEQUANnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtSDTnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tbQLNK.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbQLNK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tbQLNK.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Họ Tên", "NTNS", "Phái", "Quê Quán", "SĐT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Byte.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbQLNK.getTableHeader().setReorderingAllowed(false);
        tbQLNK.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbQLNKMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbQLNK);

        btnTHEMnk.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTHEMnk.setText("Thêm");
        btnTHEMnk.setMaximumSize(new java.awt.Dimension(60, 25));
        btnTHEMnk.setMinimumSize(new java.awt.Dimension(60, 25));
        btnTHEMnk.setPreferredSize(new java.awt.Dimension(100, 30));
        btnTHEMnk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTHEMnkActionPerformed(evt);
            }
        });

        btnXOAnk.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXOAnk.setText("Xóa");
        btnXOAnk.setMaximumSize(new java.awt.Dimension(70, 30));
        btnXOAnk.setMinimumSize(new java.awt.Dimension(70, 30));
        btnXOAnk.setPreferredSize(new java.awt.Dimension(100, 30));
        btnXOAnk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXOAnkActionPerformed(evt);
            }
        });

        btnSUAnk.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnSUAnk.setText("Sửa");
        btnSUAnk.setPreferredSize(new java.awt.Dimension(100, 30));
        btnSUAnk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSUAnkActionPerformed(evt);
            }
        });

        btnLUUnk.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLUUnk.setText("Lưu");
        btnLUUnk.setPreferredSize(new java.awt.Dimension(100, 30));
        btnLUUnk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLUUnkActionPerformed(evt);
            }
        });

        btnKLnk.setFont(new java.awt.Font("Times New Roman", 1, 13)); // NOI18N
        btnKLnk.setText("Không Lưu");
        btnKLnk.setPreferredSize(new java.awt.Dimension(80, 30));
        btnKLnk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKLnkActionPerformed(evt);
            }
        });

        btnTHOATnk.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTHOATnk.setText("Thoát");
        btnTHOATnk.setPreferredSize(new java.awt.Dimension(100, 30));

        jLabel33.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel33.setText("Chọn Căn Hộ");

        cboMCH.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        btnXEMTTnk.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXEMTTnk.setText("Xem Thông Tin");
        btnXEMTTnk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXEMTTnkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnNhanKhauLayout = new javax.swing.GroupLayout(pnNhanKhau);
        pnNhanKhau.setLayout(pnNhanKhauLayout);
        pnNhanKhauLayout.setHorizontalGroup(
            pnNhanKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhanKhauLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnNhanKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnNhanKhauLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhanKhauLayout.createSequentialGroup()
                        .addComponent(cboMCH, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhanKhauLayout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(127, 127, 127))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhanKhauLayout.createSequentialGroup()
                        .addComponent(btnXEMTTnk)
                        .addGap(94, 94, 94)))
                .addGroup(pnNhanKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnNhanKhauLayout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addContainerGap())
                    .addGroup(pnNhanKhauLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(pnNhanKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhanKhauLayout.createSequentialGroup()
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(323, 323, 323))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhanKhauLayout.createSequentialGroup()
                                .addComponent(btnTHEMnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnXOAnk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSUAnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnLUUnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnKLnk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTHOATnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60))))))
        );
        pnNhanKhauLayout.setVerticalGroup(
            pnNhanKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnNhanKhauLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnNhanKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnNhanKhauLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboMCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXEMTTnk)
                        .addGap(13, 13, 13)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnNhanKhauLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnNhanKhauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTHEMnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXOAnk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSUAnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLUUnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKLnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTHOATnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );

        tpnTC.addTab("NHÂN KHẨU", pnNhanKhau);

        btnSuaDGdvc.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSuaDGdvc.setText("Sửa ĐG");
        btnSuaDGdvc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDGdvcActionPerformed(evt);
            }
        });

        btnHDdvc.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnHDdvc.setText("Hóa Đơn");
        btnHDdvc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHDdvcActionPerformed(evt);
            }
        });

        txtHDdvc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel41.setText("Số Hóa Đơn:");

        jLabel42.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel42.setText("Mã căn hộ:");

        txtThangDVC.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        btnXTTdv.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        btnXTTdv.setText("XEM");
        btnXTTdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXTTdvActionPerformed(evt);
            }
        });

        txtTdvc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTdvc.setText("0.0");

        jLabel43.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel43.setText("Thông tin về Điện");

        txtPSH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel44.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel44.setText("Thông tin về Nước");

        txtPMT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel45.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel45.setText("Thông tin về Dịch Vụ Công");

        jLabel46.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel46.setText("Tháng:");

        txtCSD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCSD.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCSDCaretUpdate(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel47.setText("Thành Tiền:");

        txtHDD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel48.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel48.setText("Số Hóa Đơn:");

        jLabel49.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel49.setText("Chỉ số Điện:");

        txtThangD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtTD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTD.setText("0.0");

        txtDGD.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtLDTT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel50.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel50.setText("Tháng:");

        jLabel51.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel51.setText("Lượng Điện tiêu thụ:");

        jLabel52.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel52.setText("Đơn giá Điện:");

        jLabel53.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel53.setText("Thành Tiền:");

        tbHDD.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbHDD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Hóa đơn", "Tháng", "Chỉ số"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbHDD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHDDMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbHDD);

        tbDVC.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbDVC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Hóa đơn", "Tháng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbDVC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDVCMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tbDVC);

        btnCNd.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnCNd.setText("Cập Nhật");
        btnCNd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCNdActionPerformed(evt);
            }
        });

        btnCNdvc.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnCNdvc.setText("Cập Nhật");
        btnCNdvc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCNdvcActionPerformed(evt);
            }
        });

        rdnPAN.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rdnPAN.setText("Phí An Ninh");
        rdnPAN.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdnPANItemStateChanged(evt);
            }
        });

        rdnPMT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rdnPMT.setText("Phí Môi Trường");
        rdnPMT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdnPMTItemStateChanged(evt);
            }
        });

        rdnPSH.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        rdnPSH.setText("Phí Sinh Hoạt");
        rdnPSH.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                rdnPSHItemStateChanged(evt);
            }
        });

        btnSuaDGd.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSuaDGd.setText("Sửa ĐG");
        btnSuaDGd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDGdActionPerformed(evt);
            }
        });

        btnHDD.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnHDD.setText("Hóa Đơn");
        btnHDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHDDActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel54.setText("Thành Tiền:");

        tbHDN.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tbHDN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Hóa đơn", "Tháng", "Chỉ số"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbHDN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbHDNMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tbHDN);

        btnCNn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnCNn.setText("Cập Nhật");
        btnCNn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCNnActionPerformed(evt);
            }
        });

        txtCSN.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtCSN.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtCSNCaretUpdate(evt);
            }
        });

        btnSuaDGn.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnSuaDGn.setText("Sửa ĐG");
        btnSuaDGn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaDGnActionPerformed(evt);
            }
        });

        btnHDN.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnHDN.setText("Hóa Đơn");
        btnHDN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHDNActionPerformed(evt);
            }
        });

        txtHDN.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel55.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel55.setText("Số Hóa Đơn:");

        jLabel56.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel56.setText("Chỉ số Nước:");

        txtThangN.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtTN.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtTN.setText("0.0");

        txtDGN.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtLNTT.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel57.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel57.setText("Tháng:");

        jLabel58.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel58.setText("Lượng Nước tiêu thụ:");

        jLabel59.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel59.setText("Đơn giá Nước:");

        txtPAN.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel60.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel60.setText("BIỂU PHÍ DỊCH VỤ SINH HOẠT HÀNG THÁNG");

        cboMCHdv.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N

        btnLuuDG.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLuuDG.setText("Lưu DG");
        btnLuuDG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuDGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnDichVuLayout = new javax.swing.GroupLayout(pnDichVu);
        pnDichVu.setLayout(pnDichVuLayout);
        pnDichVuLayout.setHorizontalGroup(
            pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDichVuLayout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addComponent(jLabel60)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDichVuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboMCHdv, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXTTdv)
                .addGap(493, 493, 493))
            .addGroup(pnDichVuLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDichVuLayout.createSequentialGroup()
                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDichVuLayout.createSequentialGroup()
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDGD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel52))
                                .addGap(18, 18, 18)
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel53)))
                            .addGroup(pnDichVuLayout.createSequentialGroup()
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtHDD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel48))
                                .addGap(18, 18, 18)
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtThangD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel50)))
                            .addGroup(pnDichVuLayout.createSequentialGroup()
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCSD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel49))
                                .addGap(18, 18, 18)
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel51)
                                    .addComponent(txtLDTT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnDichVuLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel43)))
                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDichVuLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel44)
                                .addGap(198, 198, 198))
                            .addGroup(pnDichVuLayout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnDichVuLayout.createSequentialGroup()
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtHDN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel55))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtThangN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel57)))
                                    .addGroup(pnDichVuLayout.createSequentialGroup()
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCSN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel56))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel58)
                                            .addComponent(txtLNTT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pnDichVuLayout.createSequentialGroup()
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDGN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel59))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtTN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel54)))
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pnDichVuLayout.createSequentialGroup()
                                        .addGap(99, 99, 99)
                                        .addComponent(btnLuuDG)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)))
                        .addGap(42, 42, 42))
                    .addGroup(pnDichVuLayout.createSequentialGroup()
                        .addComponent(btnCNd, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSuaDGd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHDD)
                        .addGap(109, 109, 109)
                        .addComponent(btnCNn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSuaDGn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnHDN)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDichVuLayout.createSequentialGroup()
                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPSH, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdnPSH))
                        .addGap(18, 18, 18)
                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTdvc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel47)))
                    .addGroup(pnDichVuLayout.createSequentialGroup()
                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHDdvc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))
                        .addGap(18, 18, 18)
                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtThangDVC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46)))
                    .addGroup(pnDichVuLayout.createSequentialGroup()
                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPAN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdnPAN))
                        .addGap(18, 18, 18)
                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPMT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdnPMT)))
                    .addGroup(pnDichVuLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel45))
                    .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pnDichVuLayout.createSequentialGroup()
                            .addComponent(btnCNdvc, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSuaDGdvc)
                            .addGap(18, 18, 18)
                            .addComponent(btnHDdvc))
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );
        pnDichVuLayout.setVerticalGroup(
            pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnDichVuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel60)
                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDichVuLayout.createSequentialGroup()
                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDichVuLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel45))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDichVuLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel43)))
                        .addGap(18, 18, 18)
                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnDichVuLayout.createSequentialGroup()
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnDichVuLayout.createSequentialGroup()
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel48)
                                            .addComponent(jLabel50))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtHDD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtThangD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27))
                                    .addGroup(pnDichVuLayout.createSequentialGroup()
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel55)
                                            .addComponent(jLabel57))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtHDN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtThangN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pnDichVuLayout.createSequentialGroup()
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel56)
                                            .addComponent(jLabel58))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtCSN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtLNTT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnDichVuLayout.createSequentialGroup()
                                                .addComponent(jLabel54)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnDichVuLayout.createSequentialGroup()
                                                .addComponent(jLabel59)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDGN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnDichVuLayout.createSequentialGroup()
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel49)
                                            .addComponent(jLabel51))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtCSD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtLDTT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnDichVuLayout.createSequentialGroup()
                                                .addComponent(jLabel53)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnDichVuLayout.createSequentialGroup()
                                                .addComponent(jLabel52)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDGD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(13, 13, 13))
                            .addGroup(pnDichVuLayout.createSequentialGroup()
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel46))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtHDdvc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtThangDVC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rdnPAN)
                                    .addComponent(rdnPMT))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPAN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPMT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pnDichVuLayout.createSequentialGroup()
                                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel47)
                                            .addComponent(rdnPSH))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTdvc, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtPSH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnDichVuLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(cboMCHdv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXTTdv))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel44)))
                .addGap(19, 19, 19)
                .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCNd)
                        .addComponent(btnSuaDGd)
                        .addComponent(btnHDD)
                        .addComponent(btnCNn)
                        .addComponent(btnSuaDGn)
                        .addComponent(btnHDN))
                    .addGroup(pnDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCNdvc)
                        .addComponent(btnSuaDGdvc)
                        .addComponent(btnHDdvc)))
                .addGap(41, 41, 41)
                .addComponent(btnLuuDG)
                .addGap(59, 59, 59))
        );

        tpnTC.addTab("DỊCH VỤ", pnDichVu);

        jLabel61.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel61.setText("CHỨC NĂNG NÀY ĐANG TRONG QUÁ TRÌNH PHÁT TRIỂN");

        javax.swing.GroupLayout pnThongKeLayout = new javax.swing.GroupLayout(pnThongKe);
        pnThongKe.setLayout(pnThongKeLayout);
        pnThongKeLayout.setHorizontalGroup(
            pnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongKeLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel61)
                .addContainerGap(46, Short.MAX_VALUE))
        );
        pnThongKeLayout.setVerticalGroup(
            pnThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnThongKeLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jLabel61)
                .addContainerGap(403, Short.MAX_VALUE))
        );

        tpnTC.addTab("THỐNG KÊ", pnThongKe);

        tbTKNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tbTKNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã NV", "Họ Tên", "NTNS", "Phái", "Chức Vụ", "Mail"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Byte.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTKNV.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(tbTKNV);

        tbTKnk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbTKnk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tbTKnk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Họ Tên", "NTNS", "Phái", "Quê Quán", "SĐT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Byte.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbTKnk.getTableHeader().setReorderingAllowed(false);
        jScrollPane5.setViewportView(tbTKnk);

        jLabel34.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("TÌM KIẾM");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel37.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel37.setText("Tìm kiếm nhân khẩu");

        btnTKnk.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTKnk.setText("Tìm Kiếm");
        btnTKnk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKnkActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel35.setText("Nhập địa chỉ cần tìm");

        jLabel36.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel36.setText("Nhập tên NK cần tìm");

        txtTKTennk.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtTKDCnk.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(txtTKDCnk, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(txtTKTennk, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel36)
                .addGap(49, 49, 49))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(153, 153, 153))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTKnk)
                        .addGap(168, 168, 168))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel37)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTKTennk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTKDCnk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnTKnk))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel38.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        jLabel38.setText("Tìm kiếm nhân viên");

        btnTKnv.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTKnv.setText("Tìm Kiếm");
        btnTKnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKnvActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel39.setText("Nhập mã NV cần tìm");

        jLabel40.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel40.setText("Nhập tên NV cần tìm");

        txtTenNVtk.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtMANVtk.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(txtMANVtk, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111, Short.MAX_VALUE)
                .addComponent(txtTenNVtk, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel40)
                .addGap(49, 49, 49))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel38)
                        .addGap(153, 153, 153))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(btnTKnv)
                        .addGap(168, 168, 168))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addGap(8, 8, 8)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenNVtk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMANVtk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btnTKnv))
        );

        javax.swing.GroupLayout pnTimKiemLayout = new javax.swing.GroupLayout(pnTimKiem);
        pnTimKiem.setLayout(pnTimKiemLayout);
        pnTimKiemLayout.setHorizontalGroup(
            pnTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTimKiemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
            .addGroup(pnTimKiemLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnTimKiemLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 594, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnTimKiemLayout.setVerticalGroup(
            pnTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTimKiemLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(pnTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnTimKiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(115, Short.MAX_VALUE))
        );

        tpnTC.addTab("TÌM KIẾM", pnTimKiem);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("PHẦN MỀM QUẢN LÝ CHUNG CƯ VINHOME");

        btnDX.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        btnDX.setText("Đăng Xuẩt");
        btnDX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDXActionPerformed(evt);
            }
        });

        btnDoiMK.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnDoiMK.setText("Hiện tên người dùng");
        btnDoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoiMKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpnTC)
            .addGroup(layout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDoiMK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDX)
                .addGap(4, 4, 4))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDX)
                        .addComponent(btnDoiMK)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpnTC, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void setButtonNV(boolean a)
        {
            btnThemNV.setEnabled(a);
            btnXoaNV.setEnabled(a);
            btnSuaNv.setEnabled(a);
            btnThoatNV.setEnabled(a);
            btnLuuNV.setEnabled(!a);
            btnKLNV.setEnabled(!a);
        }

        private void setKhoaNV(boolean a)
        {
            txtQLMnv.setEditable(a);
            txtQLHtnv.setEditable(a);
            txtQLNsnv.setEditable(a);
            txtPhaiNV.setEditable(a);
            txtMailNV.setEditable(a);
        }

        private void setNullNV()
        {
            txtQLMnv.setText("");
            txtQLHtnv.setText("");
            txtQLNsnv.setText("");
            txtPhaiNV.setText("");
            txtMailNV.setText("");
        }
        
        private void setButtonNK(boolean a)
        {
            btnTHEMnk.setEnabled(a);
            btnXOAnk.setEnabled(a);
            btnSUAnk.setEnabled(a);
            btnTHOATnk.setEnabled(a);
            btnLUUnk.setEnabled(!a);
            btnKLnk.setEnabled(!a);
        }

        private void setKhoaNK(boolean a)
        {
            txtSTTnk.setEditable(a);
            txtHTnk.setEditable(a);
            txtNTNSnk.setEditable(a);
            txtPHAInk.setEditable(a);
            txtQUEQUANnk.setEditable(a);
            txtSDTnk.setEditable(a);
        }

        private void setNullNK()
        {
            txtSTTnk.setText("");
            txtHTnk.setText("");
            txtNTNSnk.setText("");
            txtPHAInk.setText("");
            txtQUEQUANnk.setText("");
            txtSDTnk.setText("");
        }
    private void btnQLTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLTKActionPerformed
        // TODO add your handling code here:
        new frmQLTK().setVisible(true);
    }//GEN-LAST:event_btnQLTKActionPerformed

    private void btnDoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoiMKActionPerformed
        // TODO add your handling code here:
        new frmDoiMK().setVisible(true);
    }//GEN-LAST:event_btnDoiMKActionPerformed

    private void btnTKnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKnvActionPerformed
        // TODO add your handling code here:
        if(txtMANVtk.getText().length()==0 && txtTenNVtk.getText().length()==0){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm!");
        }
        else  if(txtMANVtk.getText().length()!=0){
            modelnv = (DefaultTableModel) tbTKNV.getModel();
            ListNV = NVMod.findMANV(txtMANVtk.getText());
            modelnv.setRowCount(0);
            ListNV.forEach((NHANVIEN) -> {
                modelnv.addRow(new Object[]{
                    NHANVIEN.getMANV(), NHANVIEN.getHOTENNV(), NHANVIEN.getNS(), NHANVIEN.getPHAI(), NHANVIEN.getCHUCVU(), NHANVIEN.getMAIL()});
            });
        } else if(txtTenNVtk.getText().length()!=0){
            modelnv = (DefaultTableModel) tbTKNV.getModel();
            ListNV = NVMod.findTenNV(txtTenNVtk.getText());
            modelnv.setRowCount(0);
            ListNV.forEach((NHANVIEN) -> {
                modelnv.addRow(new Object[]{
                    NHANVIEN.getMANV(), NHANVIEN.getHOTENNV(), NHANVIEN.getNS(), NHANVIEN.getPHAI(), NHANVIEN.getCHUCVU(), NHANVIEN.getMAIL()});
            });
        }
    }//GEN-LAST:event_btnTKnvActionPerformed

    private void btnTKnkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKnkActionPerformed
        // TODO add your handling code here:
        if(txtTKTennk.getText().length()==0 && txtTKDCnk.getText().length()==0){
                JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm!");
        }
        else  if(txtTKTennk.getText().length()!=0){
            modelnk = (DefaultTableModel) tbTKnk.getModel();
            ListNK = NKMod.findTenNK(txtTKTennk.getText());
            modelnk.setRowCount(0);
            ListNK.forEach((NHANKHAU) -> {
                modelnk.addRow(new Object[]{
                    NHANKHAU.getSTT(), NHANKHAU.getHOTENNK(), NHANKHAU.getNTNS(), NHANKHAU.getPHAINK(), NHANKHAU.getQUEQUAN(), NHANKHAU.getSDT()});
            });
        }else  if(txtTKDCnk.getText().length()!=0){
            modelnk = (DefaultTableModel) tbTKnk.getModel();
            ListNK = NKMod.findDCNK(txtTKDCnk.getText());
            modelnk.setRowCount(0);
            ListNK.forEach((NHANKHAU) -> {
                modelnk.addRow(new Object[]{
                    NHANKHAU.getSTT(), NHANKHAU.getHOTENNK(), NHANKHAU.getNTNS(), NHANKHAU.getPHAINK(), NHANKHAU.getQUEQUAN(), NHANKHAU.getSDT()});
            });
        }
    }//GEN-LAST:event_btnTKnkActionPerformed

    private void btnDXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDXActionPerformed
        // TODO add your handling code here:
         if(JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn đăng xuất?") == JOptionPane.YES_OPTION)
         { this.dispose();
         new frmDangNhap().setVisible(true);
         }
        else
            return;
    }//GEN-LAST:event_btnDXActionPerformed

    private void btnThemNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNVActionPerformed
        // TODO add your handling code here:
            setButtonNV(false);
            setKhoaNV(true);
            txtQLMnv.requestFocus();
            setNullNV();
    }//GEN-LAST:event_btnThemNVActionPerformed

    private void btnSuaNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaNvActionPerformed
        // TODO add your handling code here:
            setButtonNV(false);
            setKhoaNV(true);
            txtQLHtnv.requestFocus();
            txtQLMnv.setEditable(false);
    }//GEN-LAST:event_btnSuaNvActionPerformed

    private void btnKLNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKLNVActionPerformed
        // TODO add your handling code here:
            setButtonNV(true);
            setKhoaNV(true);
            setNullNV();
    }//GEN-LAST:event_btnKLNVActionPerformed

    private void btnXoaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaNVActionPerformed
         int selextedIndex = tbQLNV.getSelectedRow();
         if (selextedIndex >= 0) {
            NHANVIEN nv = ListNV.get(selextedIndex);
            int option = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa nhân viên này không?");
            if (option == 0) {
                NVMod.delete(nv.getMANV());
                ShowNV();
            }
            else{
                  return;
            }
        }
    }//GEN-LAST:event_btnXoaNVActionPerformed

    private void btnXEMTTnkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXEMTTnkActionPerformed
        // TODO add your handling code here:
        ShowNK();
    }//GEN-LAST:event_btnXEMTTnkActionPerformed
    private void CHlc(){
        txtCHlc.setText((bl+"-"+ta+"-"+ch).replace("\\s",""));
    }
    private void btnBLaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBLaActionPerformed
        // TODO add your handling code here:
        bl = btnBLa.getText().substring(5).replaceAll("\\s","");
        CHlc();//Lấy ký tự cuối cùng của tên simplebutton
    }//GEN-LAST:event_btnBLaActionPerformed

    private void btnT01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT01ActionPerformed
        // TODO add your handling code here:
        ta = btnT01.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT01ActionPerformed

    private void btnCH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCH1ActionPerformed
        // TODO add your handling code here:
        ch = btnCH1.getText().substring(3).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnCH1ActionPerformed

    private void btnBLbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBLbActionPerformed
        // TODO add your handling code here:
         bl = btnBLb.getText().substring(5).replaceAll("\\s","");
        CHlc();//Lấy ký tự cuối cùng của tên simplebutton
    }//GEN-LAST:event_btnBLbActionPerformed

    private void btnBLcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBLcActionPerformed
        // TODO add your handling code here:
         bl = btnBLc.getText().substring(5).replaceAll("\\s","");
        CHlc();//Lấy ký tự cuối cùng của tên simplebutton
    }//GEN-LAST:event_btnBLcActionPerformed

    private void btnBLdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBLdActionPerformed
        // TODO add your handling code here:
         bl = btnBLd.getText().substring(5).replaceAll("\\s","");
        CHlc();//Lấy ký tự cuối cùng của tên simplebutton
    }//GEN-LAST:event_btnBLdActionPerformed

    private void btnBLeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBLeActionPerformed
        // TODO add your handling code here:
         bl = btnBLe.getText().substring(5).replaceAll("\\s","");
        CHlc();//Lấy ký tự cuối cùng của tên simplebutton
    }//GEN-LAST:event_btnBLeActionPerformed

    private void btnBLfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBLfActionPerformed
        // TODO add your handling code here:
         bl = btnBLf.getText().substring(5).replaceAll("\\s","");
        CHlc();//Lấy ký tự cuối cùng của tên simplebutton
    }//GEN-LAST:event_btnBLfActionPerformed

    private void btnBLgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBLgActionPerformed
        // TODO add your handling code here:
         bl = btnBLg.getText().substring(5).replaceAll("\\s","");
        CHlc();//Lấy ký tự cuối cùng của tên simplebutton
    }//GEN-LAST:event_btnBLgActionPerformed

    private void btnBLhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBLhActionPerformed
        // TODO add your handling code here:
         bl = btnBLh.getText().substring(5).replaceAll("\\s","");
        CHlc();//Lấy ký tự cuối cùng của tên simplebutton
    }//GEN-LAST:event_btnBLhActionPerformed

    private void btnBLiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBLiActionPerformed
        // TODO add your handling code here:
         bl = btnBLi.getText().substring(5).replaceAll("\\s","");
        CHlc();//Lấy ký tự cuối cùng của tên simplebutton
    }//GEN-LAST:event_btnBLiActionPerformed

    private void btnCH2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCH2ActionPerformed
        // TODO add your handling code here:
        ch = btnCH2.getText().substring(3).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnCH2ActionPerformed

    private void btnCH3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCH3ActionPerformed
        // TODO add your handling code here:
        ch = btnCH3.getText().substring(3).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnCH3ActionPerformed

    private void btnCH4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCH4ActionPerformed
        // TODO add your handling code here:
        ch = btnCH4.getText().substring(3).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnCH4ActionPerformed

    private void btnCH5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCH5ActionPerformed
        // TODO add your handling code here:
        ch = btnCH5.getText().substring(3).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnCH5ActionPerformed

    private void btnCH6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCH6ActionPerformed
        // TODO add your handling code here:
        ch = btnCH6.getText().substring(3).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnCH6ActionPerformed

    private void btnT02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT02ActionPerformed
        // TODO add your handling code here:
         ta = btnT02.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT02ActionPerformed

    private void btnT03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT03ActionPerformed
        // TODO add your handling code here:
         ta = btnT03.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT03ActionPerformed

    private void btnT04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT04ActionPerformed
        // TODO add your handling code here:
         ta = btnT04.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT04ActionPerformed

    private void btnT05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT05ActionPerformed
        // TODO add your handling code here:
         ta = btnT05.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT05ActionPerformed

    private void btnT06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT06ActionPerformed
        // TODO add your handling code here:
         ta = btnT06.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT06ActionPerformed

    private void btnT07ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT07ActionPerformed
        // TODO add your handling code here:
         ta = btnT07.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT07ActionPerformed

    private void btnT08ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT08ActionPerformed
        // TODO add your handling code here:
         ta = btnT08.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT08ActionPerformed

    private void btnT09ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT09ActionPerformed
        // TODO add your handling code here:
         ta = btnT09.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT09ActionPerformed

    private void btnT10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT10ActionPerformed
        // TODO add your handling code here:
         ta = btnT10.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT10ActionPerformed

    private void btnT11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT11ActionPerformed
        // TODO add your handling code here:
        ta = btnT11.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT11ActionPerformed

    private void btnT12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT12ActionPerformed
        // TODO add your handling code here:
        ta = btnT12.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT12ActionPerformed

    private void btnT13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT13ActionPerformed
        // TODO add your handling code here:
        ta = btnT13.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT13ActionPerformed

    private void btnT14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT14ActionPerformed
        // TODO add your handling code here:
        ta = btnT14.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT14ActionPerformed

    private void btnT15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT15ActionPerformed
        // TODO add your handling code here:
        ta = btnT15.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT15ActionPerformed

    private void btnT16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT16ActionPerformed
        // TODO add your handling code here:
        ta = btnT16.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT16ActionPerformed

    private void btnT17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT17ActionPerformed
        // TODO add your handling code here:
        ta = btnT17.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT17ActionPerformed

    private void btnT18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT18ActionPerformed
        // TODO add your handling code here:
        ta = btnT18.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT18ActionPerformed

    private void btnT19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT19ActionPerformed
        // TODO add your handling code here:
        ta = btnT19.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT19ActionPerformed

    private void btnT20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT20ActionPerformed
        // TODO add your handling code here:
        ta = btnT20.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT20ActionPerformed

    private void btnT21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT21ActionPerformed
        // TODO add your handling code here:
        ta = btnT21.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT21ActionPerformed

    private void btnT22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT22ActionPerformed
        // TODO add your handling code here:
        ta = btnT22.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT22ActionPerformed

    private void btnT23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT23ActionPerformed
        // TODO add your handling code here:
        ta = btnT23.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT23ActionPerformed

    private void btnT24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnT24ActionPerformed
        // TODO add your handling code here:
        ta = btnT24.getText().substring(4).replaceAll("\\s","");
        CHlc();
    }//GEN-LAST:event_btnT24ActionPerformed

    private void txtCHlcCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCHlcCaretUpdate
        // TODO add your handling code here:
         if (txtCHlc.getText().length() == 7)
            {
                String tt = CHMod.TTch(txtCHlc.getText());
                String lch = CHMod.LCH(txtCHlc.getText());
                   if (tt.contains("1") == true)
                    {
                        rdnDB.setSelected(true);
                        rdnCB.setSelected(false);
                    }
                    else
                    {
                        rdnDB.setSelected(false);
                        rdnCB.setSelected(true);
                    }

                    if (lch.contains("LOAI1")==true)
                    {
                        cboLch.setSelectedIndex(0);
                    }
                    else if (lch.contains("LOAI2")==true)
                    {
                        cboLch.setSelectedIndex(1);
                    }
                    else
                    {
                        cboLch.setSelectedIndex(2);
                    }
                }
    }//GEN-LAST:event_txtCHlcCaretUpdate

    private void btnCNchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCNchActionPerformed
        // TODO add your handling code here:
        setdisable(true);
    }//GEN-LAST:event_btnCNchActionPerformed

    private void btnHUYchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHUYchActionPerformed
        // TODO add your handling code here:
        setdisable(false);
    }//GEN-LAST:event_btnHUYchActionPerformed

    private void btnLUUchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLUUchActionPerformed
        // TODO add your handling code here:
            setdisable(false);
            if (rdnDB.isSelected()){
                Connection connection = null;
                PreparedStatement statement = null;
                try {
                     connection = Connect.getConnection();
                    String sql = "UPDATE CANHO  SET TRANGTHAI = 1, MANV = ? where MACH= ?";
                    statement = connection.prepareCall(sql);
                    statement.setString(1, IDht);
                    statement.setString(2,txtCHlc.getText());
                    int resultSet = statement.executeUpdate();
                    if(resultSet==1)
                        JOptionPane.showMessageDialog(null, "Đổi trạng thái thành công!");
                    else
                        JOptionPane.showMessageDialog(null, "Đổi trạng thái thất bại!");
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

            else
            {
                int option = JOptionPane.showConfirmDialog(rootPane, "Thao tác này sẽ xóa nhân khẩu thuộc căn hộ. Bạn đã chắc chắn muốn chuyển đổi trạng thái căn hộ?");
                     if (option == 0) {
                         Connection connection = null;
                PreparedStatement statement = null;
                try {
                     connection = Connect.getConnection();
                    String sql = "UPDATE CANHO  SET TRANGTHAI = 0, MANV = ? where MACH= ?";
                    statement = connection.prepareCall(sql);
                    statement.setString(1, IDht);
                    statement.setString(2,txtCHlc.getText());
                    int resultSet = statement.executeUpdate();
                    if(resultSet==1)
                        JOptionPane.showMessageDialog(null, "Đổi trạng thái thành công!");
                    else
                        JOptionPane.showMessageDialog(null, "Đổi trạng thái thất bại!");
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
    }//GEN-LAST:event_btnLUUchActionPerformed

    private void btnXTTdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXTTdvActionPerformed
        // Hiển thị hóa đơn điện
        loadHDD();
        loadHDN();
        loadDVC();
            //Load lại ngày tháng hiện tại
            txtThangD.setText(java.time.LocalDate.now().toString());
            txtThangN.setText(java.time.LocalDate.now().toString());
            txtThangDVC.setText(java.time.LocalDate.now().toString());
            LoadSHD();
    }//GEN-LAST:event_btnXTTdvActionPerformed
    
    private void loadHDD(){
            hdd = (DefaultTableModel) tbHDD.getModel();
            ListHDD = QLDIENMod.HDD(cboMCHdv.getSelectedItem().toString());
            hdd.setRowCount(0);
            ListHDD.forEach((QLDIEN) -> {
            hdd.addRow(new Object[]{QLDIEN.getMHDDIEN(), QLDIEN.getNGAYKT(), QLDIEN.getCHISODIEN()});});
    }
    
    private void loadHDN()
    {
         hdn = (DefaultTableModel) tbHDN.getModel();
            ListHDN = QLNUOCMod.HDN(cboMCHdv.getSelectedItem().toString());
            hdn.setRowCount(0);
            ListHDN.forEach((QLNUOC) -> {
            hdn.addRow(new Object[]{QLNUOC.getMHDNUOC(), QLNUOC.getNGAYCHOT(), QLNUOC.getCHISONUOC()});});
    }
    
    private void loadDVC(){
            dvc = (DefaultTableModel) tbDVC.getModel();
            ListDVC = DVCMod.DVC(cboMCHdv.getSelectedItem().toString());
            dvc.setRowCount(0);
            ListDVC.forEach((DICHVUCONG) -> {
            dvc.addRow(new Object[]{DICHVUCONG.getMHDDVC(), DICHVUCONG.getNGAYTB()});});
    }
    
    private void tbQLNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLNVMouseClicked
        // TODO add your handling code here:
        int index=tbQLNV.getSelectedRow();
        txtQLMnv.setText((String)(modelnv.getValueAt(index, 0)));
        txtQLHtnv.setText((String)(modelnv.getValueAt(index, 1)));
        txtQLNsnv.setText((String)(modelnv.getValueAt(index, 2)));
        txtPhaiNV.setText((String)(modelnv.getValueAt(index, 3)));
        txtMailNV.setText((String)(modelnv.getValueAt(index, 5)));
        if(modelnv.getValueAt(index, 4).toString().contains("Giám đốc")==true)
        {
            cboChucVuNV.setSelectedIndex(0);
        }else  if(modelnv.getValueAt(index, 4).toString().contains("Phó giám đốc")==true)
        {
            cboChucVuNV.setSelectedIndex(1);
        }else  if(modelnv.getValueAt(index, 4).toString().contains("Nhân viên giám sát")==true)
        {
            cboChucVuNV.setSelectedIndex(2);
        }else  if(modelnv.getValueAt(index, 4).toString().contains("Nhân viên kinh doanh")==true)
        {
            cboChucVuNV.setSelectedIndex(3);
        }else  if(modelnv.getValueAt(index, 4).toString().contains("Nhân viên an ninh")==true)
        {
            cboChucVuNV.setSelectedIndex(4);
        }else  if(modelnv.getValueAt(index, 4).toString().contains("Khách")==true)
        {
            cboChucVuNV.setSelectedIndex(5);
        }
        
    }//GEN-LAST:event_tbQLNVMouseClicked

    private void tbQLNKMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbQLNKMouseClicked
        // TODO add your handling code here:
         int index=tbQLNK.getSelectedRow();
        txtSTTnk.setText((String)(modelnk.getValueAt(index, 0)).toString());
        txtHTnk.setText((String)(modelnk.getValueAt(index, 1)));
        txtNTNSnk.setText((String)(modelnk.getValueAt(index, 2)));
        txtPHAInk.setText((String)(modelnk.getValueAt(index, 3)));
        txtQUEQUANnk.setText((String)(modelnk.getValueAt(index, 4)));
        txtSDTnk.setText((String)(modelnk.getValueAt(index, 5)));
    }//GEN-LAST:event_tbQLNKMouseClicked

    private void btnXOAnkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXOAnkActionPerformed
        // TODO add your handling code here:
         int selextedIndex = tbQLNK.getSelectedRow();
         if (selextedIndex >= 0) {
            NHANKHAU nk = ListNK.get(selextedIndex);
            int option = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa nhân khẩu này không?");
            if (option == 0) {
                NKMod.delete(cboMCH.getSelectedItem().toString(),nk.getSTT());
                JOptionPane.showMessageDialog(null, "Xóa nhân khẩu thành công");
                ShowNK();
            }
            else{
                  return;
            }
        }
    }//GEN-LAST:event_btnXOAnkActionPerformed

    private void btnLuuNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuNVActionPerformed
        try {
            boolean a = NVMod.MNV(txtQLMnv.getText());
            if(a==true){
                NVMod.update(txtQLMnv.getText(), txtQLHtnv.getText(), Date.valueOf(txtQLNsnv.getText()), txtPhaiNV.getText(), cboChucVuNV.getSelectedItem().toString(), txtMailNV.getText());
                JOptionPane.showMessageDialog(null, "Cập nhật thông tin nhân viên thành công!");
                ShowNV();
                setButtonNV(true);
                setKhoaNV(false);
                setNullNV();
            }
            else
            {
                NHANVIEN nv = new NHANVIEN();
                nv.setMANV(txtQLMnv.getText());
                nv.setHOTENNV(txtQLHtnv.getText());
                nv.setNS(txtQLNsnv.getText());
                nv.setPHAI(txtPhaiNV.getText()); // nên thay bằng combobox
                nv.setCHUCVU(cboChucVuNV.getSelectedItem().toString());
                nv.setMAIL(txtMailNV.getText()); //thiếu hàm check @
                NVMod.insert(nv);
                JOptionPane.showMessageDialog(rootPane, "Thêm nhân viên mới thành công");
                ShowNV();
                setButtonNV(true);
                setKhoaNV(false);
                setNullNV();
            }
        } catch (Exception ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLuuNVActionPerformed

    private void btnTHEMnkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTHEMnkActionPerformed
        // TODO add your handling code here:
            setButtonNK(false);
            setKhoaNK(true);
            txtSTTnk.setEditable(false);
            txtHTnk.requestFocus();
            setNullNK();
    }//GEN-LAST:event_btnTHEMnkActionPerformed

    private void btnKLnkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKLnkActionPerformed
        // TODO add your handling code here:
         setButtonNK(true);
         setKhoaNK(false);
         setNullNK();
    }//GEN-LAST:event_btnKLnkActionPerformed

    private void btnSUAnkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSUAnkActionPerformed
        // TODO add your handling code here:
         setButtonNK(false);
         setKhoaNK(true);
         txtHTnk.requestFocus();
         txtSTTnk.setEditable(false);
    }//GEN-LAST:event_btnSUAnkActionPerformed

    private void btnLUUnkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLUUnkActionPerformed
        // TODO add your handling code here:
            try {
            if(txtSTTnk.getText().length()>0)
            {
                NKMod.update(txtHTnk.getText(), Date.valueOf(txtNTNSnk.getText()), txtPHAInk.getText(), txtQUEQUANnk.getText(), txtSDTnk.getText(),frmDangNhap.mnv, cboMCH.getSelectedItem().toString(), Integer.valueOf(txtSTTnk.getText()));
                JOptionPane.showMessageDialog(null, "Cập nhật nhân khẩu thành công!");
                ShowNK();
                setButtonNK(true);
                setKhoaNK(false);
                setNullNK();
            }
            else{
                NHANKHAU nk = new NHANKHAU();
                nk.setSTT(0);
                nk.setMACH(cboMCH.getSelectedItem().toString());
                nk.setHOTENNK(txtHTnk.getText());
                nk.setNTNS(txtNTNSnk.getText());
                nk.setPHAINK(txtPHAInk.getText());
                nk.setQUEQUAN(txtQUEQUANnk.getText());
                nk.setSDT(txtSDTnk.getText());
                nk.setMANV(frmDangNhap.mnv);
                NKMod.insert(nk);
                JOptionPane.showMessageDialog(null, "Thêm nhân khẩu mới thành công");
                ShowNK();
                setButtonNK(true);
                setKhoaNK(false);
                setNullNK();
            }
            } catch (Exception ex) {
            Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLUUnkActionPerformed

    private void tbHDDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHDDMouseClicked
        // TODO add your handling code here:
        int index=tbHDD.getSelectedRow();
        txtHDD.setText((String)(hdd.getValueAt(index, 0)));
        txtThangD.setText((String)(hdd.getValueAt(index, 1)));
        txtCSD.setText((String)(hdd.getValueAt(index, 2)).toString());
    }//GEN-LAST:event_tbHDDMouseClicked

    private void tbHDNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbHDNMouseClicked
        // TODO add your handling code here:
        int index=tbHDN.getSelectedRow();
        txtHDN.setText((String)(hdn.getValueAt(index, 0)));
        txtThangN.setText((String)(hdn.getValueAt(index, 1)));
        txtCSN.setText((String)(hdn.getValueAt(index, 2)).toString());
    }//GEN-LAST:event_tbHDNMouseClicked

    private void tbDVCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDVCMouseClicked
        // TODO add your handling code here:
        int index=tbDVC.getSelectedRow();
        txtHDdvc.setText((String)(dvc.getValueAt(index, 0)));
        txtThangDVC.setText((String)(dvc.getValueAt(index, 1)));
    }//GEN-LAST:event_tbDVCMouseClicked

    private void txtCSDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCSDCaretUpdate
        // TODO add your handling code here:
        float csdc = QLDIENMod.csdm(cboMCHdv.getSelectedItem().toString());
        if(txtCSD.getText().length()>0){
            txtLDTT.setText(String.valueOf(Float.valueOf(txtCSD.getText()) - csdc));
            txtTD.setText(String.valueOf(Float.valueOf(txtLDTT.getText())*Float.valueOf(txtDGD.getText())));
        }
    }//GEN-LAST:event_txtCSDCaretUpdate

    private void txtCSNCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtCSNCaretUpdate
        // TODO add your handling code here:
        float csnc = QLNUOCMod.csnm(cboMCHdv.getSelectedItem().toString());
        if(txtCSN.getText().length()>0){
            txtLNTT.setText(String.valueOf(Float.valueOf(txtCSN.getText()) - csnc));
            txtTN.setText(String.valueOf(Float.valueOf(txtLNTT.getText())*Float.valueOf(txtDGN.getText())));
        }
    }//GEN-LAST:event_txtCSNCaretUpdate

    private void rdnPANItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdnPANItemStateChanged
        // TODO add your handling code here:
        if(rdnPAN.isSelected())
            txtTdvc.setText(String.valueOf(Float.valueOf(txtTdvc.getText())+Float.valueOf(txtPAN.getText())));
        else
            txtTdvc.setText(String.valueOf(Float.valueOf(txtTdvc.getText())-Float.valueOf(txtPAN.getText())));
    }//GEN-LAST:event_rdnPANItemStateChanged

    private void rdnPMTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdnPMTItemStateChanged
        // TODO add your handling code here:
        if(rdnPMT.isSelected())
            txtTdvc.setText(String.valueOf(Float.valueOf(txtTdvc.getText())+Float.valueOf(txtPMT.getText())));
        else
            txtTdvc.setText(String.valueOf(Float.valueOf(txtTdvc.getText())-Float.valueOf(txtPMT.getText())));
    }//GEN-LAST:event_rdnPMTItemStateChanged

    private void rdnPSHItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_rdnPSHItemStateChanged
        // TODO add your handling code here:
         if(rdnPSH.isSelected())
            txtTdvc.setText(String.valueOf(Float.valueOf(txtTdvc.getText())+Float.valueOf(txtPSH.getText())));
        else
            txtTdvc.setText(String.valueOf(Float.valueOf(txtTdvc.getText())-Float.valueOf(txtPSH.getText())));
    }//GEN-LAST:event_rdnPSHItemStateChanged

    private void btnCNdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCNdActionPerformed
        // TODO add your handling code here:
        QLDIEN d = new QLDIEN();
        d.setMHDDIEN(txtHDD.getText());
        d.setNGAYKT(txtThangD.getText());
        d.setCHISODIEN(Float.valueOf(txtCSD.getText()));
        d.setMANV(frmDangNhap.mnv);
        QLDIENMod.insert(d);

        CTHDD ctd = new CTHDD();
        ctd.setMACH(cboMCHdv.getSelectedItem().toString());
        ctd.setMAHDDIEN(txtHDD.getText());
        ctd.setDONGIADIEN(Double.parseDouble(txtDGD.getText()));
        ctd.setTONGLDTT((int)Float.parseFloat(txtLDTT.getText()));
        ctd.setTHANHTIEN(Double.parseDouble(txtTD.getText()));
        CTHDDMod.insert(ctd);
        loadHDD();
        JOptionPane.showMessageDialog(null, "Thêm hóa đơn điện thành công!");
    }//GEN-LAST:event_btnCNdActionPerformed

    private void btnCNnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCNnActionPerformed
        // TODO add your handling code here:
        QLNUOC n = new QLNUOC();
        n.setMHDNUOC(txtHDN.getText());
        n.setNGAYCHOT(txtThangN.getText());
        n.setCHISONUOC(Float.valueOf(txtCSN.getText()));
        n.setMANV(frmDangNhap.mnv);
        QLNUOCMod.insert(n);

        CTHDN ctn = new CTHDN();
        ctn.setMACH(cboMCHdv.getSelectedItem().toString());
        ctn.setMAHDNUOC(txtHDN.getText());
        ctn.setDONGIANUOC(Double.parseDouble(txtDGN.getText()));
        ctn.setTONGLNTT((int)Float.parseFloat(txtLNTT.getText()));
        ctn.setTHANHTIEN(Double.parseDouble(txtTN.getText()));
        CTHDNMod.insert(ctn);
        loadHDN();
        JOptionPane.showMessageDialog(null, "Thêm hóa đơn nước thành công!");
    }//GEN-LAST:event_btnCNnActionPerformed

    private void btnCNdvcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCNdvcActionPerformed
        // TODO add your handling code here:
        DICHVUCONG c = new DICHVUCONG();
        c.setMHDDVC(txtHDdvc.getText());
        c.setMANV(frmDangNhap.mnv);
        c.setNGAYTB(txtThangDVC.getText());
        DVCMod.insert(c);
        
        CTHDDVC ctdvc = new CTHDDVC();
        ctdvc.setMACH(cboMCHdv.getSelectedItem().toString());
        ctdvc.setMAHDDVC(txtHDdvc.getText());
        if(rdnPAN.isSelected()){
            ctdvc.setPHIANNINH(Double.parseDouble(txtPAN.getText()));}
        else{
            ctdvc.setPHIANNINH(Double.parseDouble("0.0"));}
         if(rdnPMT.isSelected()){
            ctdvc.setPHIMOITRUONG(Double.parseDouble(txtPMT.getText()));}
         else{
            ctdvc.setPHIMOITRUONG(Double.parseDouble("0.0"));}
         if(rdnPSH.isSelected()){
            ctdvc.setPHIGIUXE(Double.parseDouble(txtPSH.getText()));}
         else{
            ctdvc.setPHIGIUXE(Double.parseDouble("0.0"));}
         ctdvc.setTHANHTIEN(Double.parseDouble(txtTdvc.getText()));
         CTHDDVCMod.insert(ctdvc);
         loadDVC();
         JOptionPane.showMessageDialog(null, "Thêm hóa đơn dịch vụ công thành công!");
    }//GEN-LAST:event_btnCNdvcActionPerformed

    private void enSDG(boolean a)
    {
        btnSuaDGd.setEnabled(a);
        btnSuaDGn.setEnabled(a);
        btnSuaDGdvc.setEnabled(a);
    }
    
    private void btnHDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDDActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Chức năng hóa đơn đang trong quá trình cập nhật!");
    }//GEN-LAST:event_btnHDDActionPerformed

    private void btnHDNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDNActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Chức năng hóa đơn đang trong quá trình cập nhật!");
    }//GEN-LAST:event_btnHDNActionPerformed

    private void btnHDdvcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDdvcActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Chức năng hóa đơn đang trong quá trình cập nhật!");
    }//GEN-LAST:event_btnHDdvcActionPerformed

    private void cboChucVuNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboChucVuNVMouseClicked
        // TODO add your handling code here:
        try {
         String cv = NVMod.CV(frmDangNhap.mnv);
         if(cv.contains("Phó giám đốc")==true)
        {
            JOptionPane.showMessageDialog(null, "Không thể thêm chức vụ giám đốc hoặc phó giám đốc!");
        }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cboChucVuNVMouseClicked

    private void btnSuaDGdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDGdActionPerformed
        // TODO add your handling code here:
        txtDGD.setEditable(true);
        btnLuuDG.setVisible(true);
    }//GEN-LAST:event_btnSuaDGdActionPerformed

    private void btnLuuDGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuDGActionPerformed
        // TODO add your handling code here:
        setdisDG();
        btnLuuDG.setVisible(false);
        THAMSOMod.update(Float.parseFloat(txtDGD.getText()), "D01");
        THAMSOMod.update(Float.parseFloat(txtDGN.getText()), "N02");
        THAMSOMod.update(Float.parseFloat(txtPMT.getText()), "R03");
        THAMSOMod.update(Float.parseFloat(txtPAN.getText()), "A04");
        THAMSOMod.update(Float.parseFloat(txtPSH.getText()), "G05");
        JOptionPane.showMessageDialog(null, "Cập nhật đơn giá thành công!");
        LoadDG();
    }//GEN-LAST:event_btnLuuDGActionPerformed

    private void btnSuaDGnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDGnActionPerformed
        // TODO add your handling code here:
        txtDGN.setEditable(true);
        btnLuuDG.setVisible(true);
    }//GEN-LAST:event_btnSuaDGnActionPerformed

    private void btnSuaDGdvcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaDGdvcActionPerformed
        // TODO add your handling code here:
        txtPAN.setEditable(true);
        txtPMT.setEditable(true);
        txtPSH.setEditable(true);
        btnLuuDG.setVisible(true);
    }//GEN-LAST:event_btnSuaDGdvcActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmTrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTrangChu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmTrangChu().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(frmTrangChu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBLa;
    private javax.swing.JButton btnBLb;
    private javax.swing.JButton btnBLc;
    private javax.swing.JButton btnBLd;
    private javax.swing.JButton btnBLe;
    private javax.swing.JButton btnBLf;
    private javax.swing.JButton btnBLg;
    private javax.swing.JButton btnBLh;
    private javax.swing.JButton btnBLi;
    private javax.swing.JButton btnCH1;
    private javax.swing.JButton btnCH2;
    private javax.swing.JButton btnCH3;
    private javax.swing.JButton btnCH4;
    private javax.swing.JButton btnCH5;
    private javax.swing.JButton btnCH6;
    private javax.swing.JButton btnCNch;
    private javax.swing.JButton btnCNd;
    private javax.swing.JButton btnCNdvc;
    private javax.swing.JButton btnCNn;
    private javax.swing.JButton btnDX;
    private javax.swing.JButton btnDoiMK;
    private javax.swing.JButton btnHDD;
    private javax.swing.JButton btnHDN;
    private javax.swing.JButton btnHDdvc;
    private javax.swing.JButton btnHUYch;
    private javax.swing.JButton btnKLNV;
    private javax.swing.JButton btnKLnk;
    private javax.swing.JButton btnLUUch;
    private javax.swing.JButton btnLUUnk;
    private javax.swing.JButton btnLuuDG;
    private javax.swing.JButton btnLuuNV;
    private javax.swing.JButton btnQLTK;
    private javax.swing.JButton btnSUAnk;
    private javax.swing.JButton btnSuaDGd;
    private javax.swing.JButton btnSuaDGdvc;
    private javax.swing.JButton btnSuaDGn;
    private javax.swing.JButton btnSuaNv;
    private javax.swing.JButton btnT01;
    private javax.swing.JButton btnT02;
    private javax.swing.JButton btnT03;
    private javax.swing.JButton btnT04;
    private javax.swing.JButton btnT05;
    private javax.swing.JButton btnT06;
    private javax.swing.JButton btnT07;
    private javax.swing.JButton btnT08;
    private javax.swing.JButton btnT09;
    private javax.swing.JButton btnT10;
    private javax.swing.JButton btnT11;
    private javax.swing.JButton btnT12;
    private javax.swing.JButton btnT13;
    private javax.swing.JButton btnT14;
    private javax.swing.JButton btnT15;
    private javax.swing.JButton btnT16;
    private javax.swing.JButton btnT17;
    private javax.swing.JButton btnT18;
    private javax.swing.JButton btnT19;
    private javax.swing.JButton btnT20;
    private javax.swing.JButton btnT21;
    private javax.swing.JButton btnT22;
    private javax.swing.JButton btnT23;
    private javax.swing.JButton btnT24;
    private javax.swing.JButton btnTHEMnk;
    private javax.swing.JButton btnTHOATnk;
    private javax.swing.JButton btnTKnk;
    private javax.swing.JButton btnTKnv;
    private javax.swing.JButton btnThemNV;
    private javax.swing.JButton btnThoatNV;
    private javax.swing.JButton btnXEMTTnk;
    private javax.swing.JButton btnXOAnk;
    private javax.swing.JButton btnXTTdv;
    private javax.swing.JButton btnXoaNV;
    private javax.swing.JComboBox<String> cboChucVuNV;
    private javax.swing.JComboBox<String> cboLch;
    private javax.swing.JComboBox<String> cboMCH;
    private javax.swing.JComboBox<String> cboMCHdv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel pnBlock;
    private javax.swing.JPanel pnCanHo;
    private javax.swing.JPanel pnDichVu;
    private javax.swing.JPanel pnNhanKhau;
    private javax.swing.JPanel pnNhanVien;
    private javax.swing.JPanel pnPhong;
    private javax.swing.JPanel pnTTCH;
    private javax.swing.JPanel pnTang;
    private javax.swing.JPanel pnThongKe;
    private javax.swing.JPanel pnTimKiem;
    private javax.swing.JRadioButton rdnCB;
    private javax.swing.JRadioButton rdnDB;
    private javax.swing.JRadioButton rdnPAN;
    private javax.swing.JRadioButton rdnPMT;
    private javax.swing.JRadioButton rdnPSH;
    private javax.swing.JTable tbDVC;
    private javax.swing.JTable tbHDD;
    private javax.swing.JTable tbHDN;
    private javax.swing.JTable tbQLNK;
    private javax.swing.JTable tbQLNV;
    private javax.swing.JTable tbTKNV;
    private javax.swing.JTable tbTKnk;
    private javax.swing.JTabbedPane tpnTC;
    private javax.swing.JTextField txtCHlc;
    private javax.swing.JTextField txtCSD;
    private javax.swing.JTextField txtCSN;
    private javax.swing.JTextField txtDGD;
    private javax.swing.JTextField txtDGN;
    private javax.swing.JTextField txtHDD;
    private javax.swing.JTextField txtHDN;
    private javax.swing.JTextField txtHDdvc;
    private javax.swing.JTextField txtHTnk;
    private javax.swing.JTextField txtLDTT;
    private javax.swing.JTextField txtLNTT;
    private javax.swing.JTextField txtMANVtk;
    private javax.swing.JTextField txtMailNV;
    private javax.swing.JTextField txtNTNSnk;
    private javax.swing.JTextField txtPAN;
    private javax.swing.JTextField txtPHAInk;
    private javax.swing.JTextField txtPMT;
    private javax.swing.JTextField txtPSH;
    private javax.swing.JTextField txtPhaiNV;
    private javax.swing.JTextField txtQLHtnv;
    private javax.swing.JTextField txtQLMnv;
    private javax.swing.JTextField txtQLNsnv;
    private javax.swing.JTextField txtQUEQUANnk;
    private javax.swing.JTextField txtSDTnk;
    private javax.swing.JTextField txtSTTnk;
    private javax.swing.JTextField txtTD;
    private javax.swing.JTextField txtTKDCnk;
    private javax.swing.JTextField txtTKTennk;
    private javax.swing.JTextField txtTN;
    private javax.swing.JTextField txtTdvc;
    private javax.swing.JTextField txtTenNVtk;
    private javax.swing.JTextField txtThangD;
    private javax.swing.JTextField txtThangDVC;
    private javax.swing.JTextField txtThangN;
    // End of variables declaration//GEN-END:variables
}
