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
public class LichThi {

    private String thu;
    private String maLHP;
    private String hocky;
    private String namhoc;
    private String tiet;
    private String phong;
    private String ngThi;
    private String magvct;

    public LichThi() {
    }

    public LichThi(String maLHP, String hocky, String namhoc, String thu, String tiet, String phong, String gvcoithi ,String ngThi) {
        this.thu = thu;
        this.maLHP = maLHP;
        this.hocky = hocky;
        this.namhoc = namhoc;
        this.tiet = tiet;
        this.phong = phong;
        this.magvct = gvcoithi;
        this.ngThi = ngThi;

    }

    public LichThi(String maLHP, String hocky, String namhoc) {
        this.maLHP = maLHP;
        this.hocky = hocky;
        this.namhoc = namhoc;
    }

    public String getMagvct() {
        return magvct;
    }

    public void setMagvct(String magvct) {
        this.magvct = magvct;
    }

    public String getMaLHP() {
        return maLHP;
    }

    public String getNgThi() {
        return ngThi;
    }

    public String getPhong() {
        return phong;
    }

    public String getThu() {
        return thu;
    }

    public String getTiet() {
        return tiet;
    }

    public void setMaLHP(String maLHP) {
        this.maLHP = maLHP;
    }

    public void setNgThi(String ngThi) {
        this.ngThi = ngThi;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }

    public void setTiet(String tiet) {
        this.tiet = tiet;
    }

    public String getHocky() {
        return hocky;
    }

    public String getNamhoc() {
        return namhoc;
    }

    public void setHocky(String hocky) {
        this.hocky = hocky;
    }

    public void setNamhoc(String namhoc) {
        this.namhoc = namhoc;
    }

}
