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
public class CTHDDVC {
    private String MACH;
    private String MAHDDVC;
    private double PHIANNINH;
    private double PHIMOITRUONG;
    private double PHIGIUXE;
    private double THANHTIEN;

    public String getMACH() {
        return MACH;
    }

    public void setMACH(String MACH) {
        this.MACH = MACH;
    }

    public String getMAHDDVC() {
        return MAHDDVC;
    }

    public void setMAHDDVC(String MAHDDVC) {
        this.MAHDDVC = MAHDDVC;
    }

    public double getPHIANNINH() {
        return PHIANNINH;
    }

    public void setPHIANNINH(double PHIANNINH) {
        this.PHIANNINH = PHIANNINH;
    }

    public double getPHIMOITRUONG() {
        return PHIMOITRUONG;
    }

    public void setPHIMOITRUONG(double PHIMOITRUONG) {
        this.PHIMOITRUONG = PHIMOITRUONG;
    }

    public double getPHIGIUXE() {
        return PHIGIUXE;
    }

    public void setPHIGIUXE(double PHIGIUXE) {
        this.PHIGIUXE = PHIGIUXE;
    }

    public double getTHANHTIEN() {
        return THANHTIEN;
    }

    public void setTHANHTIEN(double THANHTIEN) {
        this.THANHTIEN = THANHTIEN;
    }

    public CTHDDVC(String MACH, String MAHDDVC, double PHIANNINH, double PHIMOITRUONG, double PHIGIUXE, double THANHTIEN) {
        this.MACH = MACH;
        this.MAHDDVC = MAHDDVC;
        this.PHIANNINH = PHIANNINH;
        this.PHIMOITRUONG = PHIMOITRUONG;
        this.PHIGIUXE = PHIGIUXE;
        this.THANHTIEN = THANHTIEN;
    }

    public CTHDDVC() {
    }
}
