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
public class THAMSO {
    private String MATS;
    private String TENTS;
    private float GIATRI;

    public String getMATS() {
        return MATS;
    }

    public void setMATS(String MATS) {
        this.MATS = MATS;
    }

    public String getTENTS() {
        return TENTS;
    }

    public void setTENTS(String TENTS) {
        this.TENTS = TENTS;
    }

    public float getGIATRI() {
        return GIATRI;
    }

    public void setGIATRI(float GIATRI) {
        this.GIATRI = GIATRI;
    }

    public THAMSO(String MATS, String TENTS, float GIATRI) {
        this.MATS = MATS;
        this.TENTS = TENTS;
        this.GIATRI = GIATRI;
    }

    public THAMSO() {
    }
}
