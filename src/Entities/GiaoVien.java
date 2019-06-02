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
public class GiaoVien {

    private String maGV;
    private String tenGV;
    private String ngsinh;
    private String gioitinh;
    private String ngvl;
    private String hocvi;
    private String mucluong;
    private String maKhoa;
    private String maukhau = "123456";
    private String sodt;
    private String diachi;

    public String getDiachi() {
        return diachi;
    }

    public String getSodt() {
        return sodt;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public String getHocvi() {
        return hocvi;
    }

    public String getMaGV() {
        return maGV;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public String getMucluong() {
        return mucluong;
    }

    public String getNgsinh() {
        return ngsinh;
    }

    public String getNgvl() {
        return ngvl;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setHocvi(String hocvi) {
        this.hocvi = hocvi;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public void setMucluong(String mucluong) {
        this.mucluong = mucluong;
    }

    public void setNgsinh(String ngsinh) {
        this.ngsinh = ngsinh;
    }

    public void setNgvl(String ngvl) {
        this.ngvl = ngvl;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getMaukhau() {
        return maukhau;
    }

    public void setMaukhau(String maukhau) {
        this.maukhau = maukhau;
    }

    public GiaoVien() {
    }

    public GiaoVien(String maGV, String tenGV, String ngsinh, String gioitinh, String ngvl, String hocvi, String mucluong, String maKhoa, String sdt, String diachi) {
        this.maGV = maGV;
        this.tenGV = tenGV;
        this.ngsinh = ngsinh;
        this.gioitinh = gioitinh;
        this.ngvl = ngvl;
        this.hocvi = hocvi;
        this.mucluong = mucluong;
        this.maKhoa = maKhoa;
        this.sodt = sodt;
        this.diachi = diachi;

    }

}
