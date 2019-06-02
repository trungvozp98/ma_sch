/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author trung98
 */
public class Lop {

    private String malop;
    private String tenlop;
    private String siso;
    private String magvcn;
    private String makhoa;

    public String getMagvcn() {
        return magvcn;
    }

    public String getMakhoa() {
        return makhoa;
    }

    public String getMalop() {
        return malop;
    }

    public String getSiso() {
        return siso;
    }

    public String getTenlop() {
        return tenlop;
    }

    public void setMagvcn(String magvcn) {
        this.magvcn = magvcn;
    }

    public void setMakhoa(String makhoa) {
        this.makhoa = makhoa;
    }

    public void setMalop(String malop) {
        this.malop = malop;
    }

    public void setSiso(String siso) {
        this.siso = siso;
    }

    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    public Lop() {
    }

    public Lop(String malop, String tenlop, String siso, String magvcn, String makhoa) {
        this.malop = malop;
        this.tenlop = tenlop;
        this.siso = siso;
        this.magvcn = magvcn;
        this.makhoa = makhoa;
    }
    

}
