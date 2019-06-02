/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author trung98
 */
public class DangKyHoc {
    
    private String maSV;
    private String maLHP;
    private String hocKy;
    private String namHoc;
    private String ngayDangKy;

    public String getHocKy() {
        return hocKy;
    }

    public String getMaLHP() {
        return maLHP;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public String getNgayDangKy() {
        return ngayDangKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    public void setMaLHP(String maLHP) {
        this.maLHP = maLHP;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public void setNgayDangKy(String ngayDangKy) {
        this.ngayDangKy = ngayDangKy;
    }

    public DangKyHoc() {
    }

    public DangKyHoc(String maSV, String maLHP, String hocKy, String namHoc, String ngayDangKy) {
        this.maSV = maSV;
        this.maLHP = maLHP;
        this.hocKy = hocKy;
        this.namHoc = namHoc;
        this.ngayDangKy = ngayDangKy;
    }
    
    

}
