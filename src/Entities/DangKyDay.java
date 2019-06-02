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
public class DangKyDay {
    
    private String  magv ;
    private String  malhp ;
    private String  hocky ;
    private String  namhoc ;
    private String  ngdk ;

    public String getHocky() {
        return hocky;
    }

    public String getMagv() {
        return magv;
    }

    public String getMalhp() {
        return malhp;
    }

    public String getNamhoc() {
        return namhoc;
    }

    public String getNgdk() {
        return ngdk;
    }

    public void setHocky(String hocky) {
        this.hocky = hocky;
    }

    public void setMagv(String magv) {
        this.magv = magv;
    }

    public void setMalhp(String malhp) {
        this.malhp = malhp;
    }

    public void setNamhoc(String namhoc) {
        this.namhoc = namhoc;
    }

    public void setNgdk(String ngdk) {
        this.ngdk = ngdk;
    }

    public DangKyDay() {
    }

    public DangKyDay(String magv, String malhp, String hocky, String namhoc, String ngdk) {
        this.magv = magv;
        this.malhp = malhp;
        this.hocky = hocky;
        this.namhoc = namhoc;
        this.ngdk = ngdk;
    }
    
    
    
    
    
}
