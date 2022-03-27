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
public class CTHDN {
     private String MACH;
    private String MAHDNUOC;
    private double DONGIANUOC;
    private int TONGLNTT;
    private double THANHTIEN;

    public String getMACH() {
        return MACH;
    }

    public void setMACH(String MACH) {
        this.MACH = MACH;
    }

    public String getMAHDNUOC() {
        return MAHDNUOC;
    }

    public void setMAHDNUOC(String MAHDNUOC) {
        this.MAHDNUOC = MAHDNUOC;
    }

    public double getDONGIANUOC() {
        return DONGIANUOC;
    }

    public void setDONGIANUOC(double DONGIANUOC) {
        this.DONGIANUOC = DONGIANUOC;
    }

    public int getTONGLNTT() {
        return TONGLNTT;
    }

    public void setTONGLNTT(int TONGLNTT) {
        this.TONGLNTT = TONGLNTT;
    }

    public double getTHANHTIEN() {
        return THANHTIEN;
    }

    public void setTHANHTIEN(double THANHTIEN) {
        this.THANHTIEN = THANHTIEN;
    }

    public CTHDN(String MACH, String MAHDNUOC, double DONGIANUOC, int TONGLNTT, double THANHTIEN) {
        this.MACH = MACH;
        this.MAHDNUOC = MAHDNUOC;
        this.DONGIANUOC = DONGIANUOC;
        this.TONGLNTT = TONGLNTT;
        this.THANHTIEN = THANHTIEN;
    }

    public CTHDN() {
    }
}
