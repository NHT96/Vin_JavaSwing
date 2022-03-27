package Viewer;

import Controller.Connect;
import Controller.NVMod;
import Model.NHANVIEN;
import Model.TAIKHOAN;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class frmDangNhap extends javax.swing.JFrame{
    
    ArrayList<TAIKHOAN> ListTK;
    public static String IDht;
    public static String mnv;

    public static String getMnv() {
        return mnv;
    }

    public static void setTnv(String tnv) {
        frmDangNhap.mnv = tnv;
    }

    public static String getIDht() {
        return IDht;
    }

    public static void setIDht(String ID) {
        frmDangNhap.IDht = IDht;
    }

   
    public frmDangNhap() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtPASS = new javax.swing.JPasswordField();
        btnDN = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        chkHMK = new javax.swing.JCheckBox();
        btnQMK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ ĐĂNG NHẬP");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PHẦN MỀM QUẢN LÝ CHUNG CƯ VINHOME");

        txtID.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtPASS.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPASS.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        btnDN.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnDN.setText("Đăng Nhập");
        btnDN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDNActionPerformed(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.setMaximumSize(new java.awt.Dimension(87, 23));
        btnThoat.setMinimumSize(new java.awt.Dimension(87, 23));
        btnThoat.setPreferredSize(new java.awt.Dimension(93, 23));
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 2, 11)); // NOI18N
        jLabel2.setText("Nhập ID:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 2, 11)); // NOI18N
        jLabel3.setText("Nhập PassWord:");

        chkHMK.setFont(new java.awt.Font("Times New Roman", 2, 11)); // NOI18N
        chkHMK.setText("Hiện Mật Khẩu");
        chkHMK.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        chkHMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkHMKActionPerformed(evt);
            }
        });

        btnQMK.setFont(new java.awt.Font("Times New Roman", 2, 11)); // NOI18N
        btnQMK.setText("Quên Mật Khẩu ?");
        btnQMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQMKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chkHMK)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnDN)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtID)
                            .addComponent(txtPASS)
                            .addComponent(btnQMK, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPASS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkHMK)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDN)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(btnQMK)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        if(JOptionPane.showConfirmDialog(null,"Bạn có chắc chắn muốn thoát?") == JOptionPane.YES_OPTION)
            System.exit(0);
        else
            return;
    }//GEN-LAST:event_btnThoatActionPerformed

    private void btnDNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDNActionPerformed
        // TODO add your handling code here:
        if(txtID.getText().length()!=10)
        {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng 10 ký tự cho ID!");
            return;
        }
        else if(txtPASS.getText().length()<6)
        {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tối thiểu 6 ký tự cho PASS!");
            return;
        }
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //Kết nối trực tiếp bằng lệnh
            //connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName=JVVinHome;", "sa", "123456");
            
            connection = Connect.getConnection();        //Gọi lại thư viện
            String sql = "select * from TAIKHOAN where ID = ? and PASS = ?";
            statement = connection.prepareCall(sql);
            statement.setString(1,txtID.getText());
            statement.setString(2,txtPASS.getText());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
                mnv = resultSet.getString(3);
                IDht = NVMod.TNV(mnv);
                 new frmTrangChu().setVisible(true);
                this.dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Đăng nhập thất bại!");
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnDNActionPerformed

    private void chkHMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkHMKActionPerformed
        // TODO add your handling code here:
        if(chkHMK.isSelected())
            txtPASS.setEchoChar((char)0);
        else
            txtPASS.setEchoChar('*');
    }//GEN-LAST:event_chkHMKActionPerformed

    private void btnQMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQMKActionPerformed
        // TODO add your handling code here:
        new frmKPMK().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnQMKActionPerformed

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
            java.util.logging.Logger.getLogger(frmDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmDangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDN;
    private javax.swing.JButton btnQMK;
    private javax.swing.JButton btnThoat;
    private javax.swing.JCheckBox chkHMK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtID;
    private javax.swing.JPasswordField txtPASS;
    // End of variables declaration//GEN-END:variables
}
