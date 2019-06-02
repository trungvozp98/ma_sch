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
public class SinhVien {

    private String maSV;
    private String hoTen;
    private String ngSinh;
    private String gioitinh;
    private String noisinh;
    private String maLop;
    private String sdt;
    private String  maukhau="123456";

    public String getGioitinh() {
        return gioitinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getMaLop() {
        return maLop;
    }

    public String getMaSV() {
        return maSV;
    }

    public String getNgSinh() {
        return ngSinh;
    }

    public String getNoisinh() {
        return noisinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public void setNgSinh(String ngSinh) {
        this.ngSinh = ngSinh;
    }

    public void setNoisinh(String noisinh) {
        this.noisinh = noisinh;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMaukhau() {
        return maukhau;
    }

    public void setMaukhau(String maukhau) {
        this.maukhau = maukhau;
    }

    public SinhVien() {
    }

    public SinhVien(String maSV, String hoTen, String ngSinh, String gioitinh, String noisinh, String maLop, String sdt) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.ngSinh = ngSinh;
        this.gioitinh = gioitinh;
        this.noisinh = noisinh;
        this.maLop = maLop;
        this.sdt = sdt;
    }

}
