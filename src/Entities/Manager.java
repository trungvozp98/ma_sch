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
public class Manager {

    private String madn;
    private String hoten;
    private String ngsinh;
    private String diachi;
    private String matkhau;

    public String getDiachi() {
        return diachi;
    }

    public String getHoten() {
        return hoten;
    }

    public String getMadn() {
        return madn;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public String getNgsinh() {
        return ngsinh;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setMadn(String madn) {
        this.madn = madn;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public void setNgsinh(String ngsinh) {
        this.ngsinh = ngsinh;
    }

    public Manager() {
    }

    public Manager(String madn, String hoten, String ngsinh, String diachi, String matkhau) {
        this.madn = madn;
        this.hoten = hoten;
        this.ngsinh = ngsinh;
        this.diachi = diachi;
        this.matkhau = matkhau;
    }

}
