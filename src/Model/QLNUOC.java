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
public class QLNUOC {
    private String MHDNUOC;
    private String NGAYCHOT;
    private float CHISONUOC;
    private String MANV;

    public String getMHDNUOC() {
        return MHDNUOC;
    }

    public void setMHDNUOC(String MHDNUOC) {
        this.MHDNUOC = MHDNUOC;
    }

    public String getNGAYCHOT() {
        return NGAYCHOT;
    }

    public void setNGAYCHOT(String NGAYCHOT) {
        this.NGAYCHOT = NGAYCHOT;
    }

    public float getCHISONUOC() {
        return CHISONUOC;
    }

    public void setCHISONUOC(float CHISONUOC) {
        this.CHISONUOC = CHISONUOC;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public QLNUOC(String MHDNUOC, String NGAYCHOT, float CHISONUOC, String MANV) {
        this.MHDNUOC = MHDNUOC;
        this.NGAYCHOT = NGAYCHOT;
        this.CHISONUOC = CHISONUOC;
        this.MANV = MANV;
    }

    public QLNUOC() {
    }
}
