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
public class CANHO {
    private String MACH;
    private String TRANGTHAI;
    private String MALOAI;
    private String MANV;
    private String MABLOCK;
    private String TANG;

    public String getMACH() {
        return MACH;
    }

    public void setMACH(String MACH) {
        this.MACH = MACH;
    }

    public String getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(String TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }

    public String getMALOAI() {
        return MALOAI;
    }

    public void setMALOAI(String MALOAI) {
        this.MALOAI = MALOAI;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getMABLOCK() {
        return MABLOCK;
    }

    public void setMABLOCK(String MABLOCK) {
        this.MABLOCK = MABLOCK;
    }

    public String getTANG() {
        return TANG;
    }

    public void setTANG(String TANG) {
        this.TANG = TANG;
    }

    public CANHO(String MACH, String TRANGTHAI, String MALOAI, String MANV, String MABLOCK, String TANG) {
        this.MACH = MACH;
        this.TRANGTHAI = TRANGTHAI;
        this.MALOAI = MALOAI;
        this.MANV = MANV;
        this.MABLOCK = MABLOCK;
        this.TANG = TANG;
    }

    public CANHO() {
    }
}
