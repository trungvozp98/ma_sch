/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

public class Diem {

    private String maSV;
    private String malhp;
    private String hocky;
    private String namhoc;
    private float diemQT;
    private float diemGK;
    private float diemCK;
    private float diemTB;
    private String ghichu;

    public Diem() {
    }

    public Diem(String maSV, String MaLHP, String hocky, String namhoc,float diemQT, float diemGK, float diemCK, float diemTB) {
        this.maSV = maSV;
        this.malhp = MaLHP;
        this.hocky = hocky;
        this.namhoc = namhoc;
        this.diemQT = diemQT;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
        this.diemTB = diemTB;
    }

    public Diem(String masv, String malhp, String hocky, String namhoc, float diemqt, float diemgk, float diemck) {
        this.maSV = masv;
        this.malhp = malhp;
        this.hocky = hocky;
        this.namhoc = namhoc;
        this.diemQT = diemqt;
        this.diemGK = diemgk;
        this.diemCK = diemck;

    }

    public float getDiemCK() {
        return diemCK;
    }

    public float getDiemGK() {
        return diemGK;
    }

    public float getDiemQT() {
        return diemQT;
    }

    public float getDiemTB() {
        return diemTB;
    }

    public String getHocky() {
        return hocky;
    }

    public String getMaLHP() {
        return malhp;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getNamhoc() {
        return namhoc;
    }

    public void setDiemCK(float diemCK) {
        this.diemCK = diemCK;
    }

    public void setDiemGK(float diemGK) {
        this.diemGK = diemGK;
    }

    public void setDiemQT(float diemQT) {
        this.diemQT = diemQT;
    }

    public void setDiemTB(float diemTB) {
        this.diemTB = diemTB;
    }

    public void setHocky(String hocky) {
        this.hocky = hocky;
    }

    public void setMaLHP(String MaLHP) {
        this.malhp = MaLHP;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setNamhoc(String namhoc) {
        this.namhoc = namhoc;
    }

    public String getGhichu() {
        return ghichu;
    }

    public String getMalhp() {
        return malhp;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public void setMalhp(String malhp) {
        this.malhp = malhp;
    }
    

}
