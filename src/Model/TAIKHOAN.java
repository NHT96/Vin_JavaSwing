/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Admin
 */
public class TAIKHOAN extends NHANVIEN{
    private String ID;
    private String PASS;
    private String MANV;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPASS() {
        return PASS;
    }

    public void setPASS(String PASS) {
        this.PASS = PASS;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public TAIKHOAN(String ID, String PASS, String MANV) {
        this.ID = ID;
        this.PASS = PASS;
        this.MANV = MANV;
    }

    public TAIKHOAN() {
    }
}
