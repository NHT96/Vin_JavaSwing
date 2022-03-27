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
public class QLDIEN {
    private String MHDDIEN;
    private String NGAYKT;
    private float CHISODIEN;
    private String MANV;

    public String getMHDDIEN() {
        return MHDDIEN;
    }

    public void setMHDDIEN(String MHDDIEN) {
        this.MHDDIEN = MHDDIEN;
    }

    public String getNGAYKT() {
        return NGAYKT;
    }

    public void setNGAYKT(String NGAYKT) {
        this.NGAYKT = NGAYKT;
    }

    public float getCHISODIEN() {
        return CHISODIEN;
    }

    public void setCHISODIEN(float CHISODIEN) {
        this.CHISODIEN = CHISODIEN;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public QLDIEN(String MHDDIEN, String NGAYKT, float CHISODIEN, String MANV) {
        this.MHDDIEN = MHDDIEN;
        this.NGAYKT = NGAYKT;
        this.CHISODIEN = CHISODIEN;
        this.MANV = MANV;
    }

    public QLDIEN() {
    }
}
