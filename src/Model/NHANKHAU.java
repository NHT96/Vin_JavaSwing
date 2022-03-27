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
public class NHANKHAU {
    private String MACH;
    private int STT;
    private String HOTENNK;
    private String NTNS;
    private String PHAINK;
    private String QUEQUAN;
    private String SDT;
    private String MANV;

    public NHANKHAU(int STT, String HOTENNK, String NTNS, String PHAINK, String QUEQUAN, String SDT) {
        this.STT = STT;
        this.HOTENNK = HOTENNK;
        this.NTNS = NTNS;
        this.PHAINK = PHAINK;
        this.QUEQUAN = QUEQUAN;
        this.SDT = SDT;
    }

    public String getMACH() {
        return MACH;
    }

    public void setMACH(String MACH) {
        this.MACH = MACH;
    }

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }

    public String getHOTENNK() {
        return HOTENNK;
    }

    public void setHOTENNK(String HOTENNK) {
        this.HOTENNK = HOTENNK;
    }

    public String getNTNS() {
        return NTNS;
    }

    public void setNTNS(String NTNS) {
        this.NTNS = NTNS;
    }

    public String getPHAINK() {
        return PHAINK;
    }

    public void setPHAINK(String PHAINK) {
        this.PHAINK = PHAINK;
    }

    public String getQUEQUAN() {
        return QUEQUAN;
    }

    public void setQUEQUAN(String QUEQUAN) {
        this.QUEQUAN = QUEQUAN;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getMANV() {
        return MANV;
    }

    public void setMANV(String MANV) {
        this.MANV = MANV;
    }

    public NHANKHAU(String MACH, int STT, String HOTENNK, String NTNS, String PHAINK, String QUEQUAN, String SDT, String MANV) {
        this.MACH = MACH;
        this.STT = STT;
        this.HOTENNK = HOTENNK;
        this.NTNS = NTNS;
        this.PHAINK = PHAINK;
        this.QUEQUAN = QUEQUAN;
        this.SDT = SDT;
        this.MANV = MANV;
    }

    public NHANKHAU() {
    }
}
