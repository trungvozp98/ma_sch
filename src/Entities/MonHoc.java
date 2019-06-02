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
public class MonHoc {
    private String maMH;
    private String tenMH;
    private String soTC;
    private String maKhoa;

    public MonHoc() {
    }

    public MonHoc(String maMH, String tenMH, String soTC, String maKhoa) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.soTC = soTC;
        this.maKhoa = maKhoa;
    }
    

    public String getMaKhoa() {
        return maKhoa;
    }

    public String getMaMH() {
        return maMH;
    }

    public String getSoTC() {
        return soTC;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public void setSoTC(String soTC) {
        this.soTC = soTC;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }
  
}
