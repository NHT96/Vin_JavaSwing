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
public class DICHVUCONG {
    private String MHDDVC;
    private String MANV;
    private String NGAYTB;    

    public DICHVUCONG(String MHDDVC, String NGAYTB) {
        this.MHDDVC = MHDDVC;
        this.NGAYTB = NGAYTB;
    }

    public String getMHDDVC() {
        return MHDDVC;
    }

    public void setMHDDVC(String MHDDVC) {
        this.MHDDVC = MHDDVC;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public String getNGAYTB() {
        return NGAYTB;
    }

    public void setNGAYTB(String NGAYTB) {
        this.NGAYTB = NGAYTB;
    }

    public DICHVUCONG(String MHDDVC, String MANV, String NGAYTB) {
        this.MHDDVC = MHDDVC;
        this.MANV = MANV;
        this.NGAYTB = NGAYTB;
    }

    public DICHVUCONG() {
    }
}
