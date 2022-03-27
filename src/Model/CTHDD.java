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
public class CTHDD {
    private String MACH;
    private String MAHDDIEN;
    private double DONGIADIEN;
    private int TONGLDTT;
    private double THANHTIEN;

    public String getMACH() {
        return MACH;
    }

    public void setMACH(String MACH) {
        this.MACH = MACH;
    }

    public String getMAHDDIEN() {
        return MAHDDIEN;
    }

    public void setMAHDDIEN(String MAHDDIEN) {
        this.MAHDDIEN = MAHDDIEN;
    }

    public double getDONGIADIEN() {
        return DONGIADIEN;
    }

    public void setDONGIADIEN(double DONGIADIEN) {
        this.DONGIADIEN = DONGIADIEN;
    }

    public int getTONGLDTT() {
        return TONGLDTT;
    }

    public void setTONGLDTT(int TONGLDTT) {
        this.TONGLDTT = TONGLDTT;
    }

    public double getTHANHTIEN() {
        return THANHTIEN;
    }

    public void setTHANHTIEN(double THANHTIEN) {
        this.THANHTIEN = THANHTIEN;
    }

    public CTHDD(String MACH, String MAHDDIEN, double DONGIADIEN, int TONGLDTT, double THANHTIEN) {
        this.MACH = MACH;
        this.MAHDDIEN = MAHDDIEN;
        this.DONGIADIEN = DONGIADIEN;
        this.TONGLDTT = TONGLDTT;
        this.THANHTIEN = THANHTIEN;
    }

    public CTHDD() {
    }

}
