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
public class PhuHuynh {

    private String hotenCha;
    private String hotenMe;
    private String diachi;
    private String sodt;
    private String quoctich;
    private String masv;

    public String getDiachi() {
        return diachi;
    }

    public String getHotenCha() {
        return hotenCha;
    }

    public String getHotenMe() {
        return hotenMe;
    }

    public String getMasv() {
        return masv;
    }

    public String getQuoctich() {
        return quoctich;
    }

    public String getSodt() {
        return sodt;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public void setHotenCha(String hotenCha) {
        this.hotenCha = hotenCha;
    }

    public void setHotenMe(String hotenMe) {
        this.hotenMe = hotenMe;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public void setQuoctich(String quoctich) {
        this.quoctich = quoctich;
    }

    public void setSodt(String sodt) {
        this.sodt = sodt;
    }

    public PhuHuynh() {
    }

    public PhuHuynh(String hotenCha, String hotenMe, String diachi, String sodt, String quoctich, String masv) {
        this.hotenCha = hotenCha;
        this.hotenMe = hotenMe;
        this.diachi = diachi;
        this.sodt = sodt;
        this.quoctich = quoctich;
        this.masv = masv;
    }
    

}
