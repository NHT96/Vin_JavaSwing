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
public class NHANVIEN {
    
    private String MANV;
    private String HOTENNV;

    public NHANVIEN(String HOTENNV) {
        this.HOTENNV = HOTENNV;
    }
    private String NS;
    private String PHAI;
    private String CHUCVU;
    private String MAIL;

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getHOTENNV() {
        return HOTENNV;
    }

    public void setHOTENNV(String HOTENNV) {
        this.HOTENNV = HOTENNV;
    }

    public String getNS() {
        return NS;
    }

    public void setNS(String NS) {
        this.NS = NS;
    }

    public String getPHAI() {
        return PHAI;
    }

    public void setPHAI(String PHAI) {
        this.PHAI = PHAI;
    }

    public String getCHUCVU() {
        return CHUCVU;
    }

    public void setCHUCVU(String CHUCVU) {
        this.CHUCVU = CHUCVU;
    }

    public String getMAIL() {
        return MAIL;
    }

    public void setMAIL(String MAIL) {
        this.MAIL = MAIL;
    }

    public NHANVIEN(String MANV, String HOTENNV, String NS, String PHAI, String CHUCVU, String MAIL) {
        this.MANV = MANV;
        this.HOTENNV = HOTENNV;
        this.NS = NS;
        this.PHAI = PHAI;
        this.CHUCVU = CHUCVU;
        this.MAIL = MAIL;
    }

    public NHANVIEN() {
    }
}
