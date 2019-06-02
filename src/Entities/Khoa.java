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
public class Khoa {

    private String makhoa;
    private String tenkhoa;
    private String ngtl;
    private String trgkhoa;

    public String getMakhoa() {
        return makhoa;
    }

    public String getNgtl() {
        return ngtl;
    }

    public String getTenkhoa() {
        return tenkhoa;
    }

    public String getTrgkhoa() {
        return trgkhoa;
    }

    public void setMakhoa(String makhoa) {
        this.makhoa = makhoa;
    }

    public void setNgtl(String ngtl) {
        this.ngtl = ngtl;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }

    public void setTrgkhoa(String trgkhoa) {
        this.trgkhoa = trgkhoa;
    }

    public Khoa() {
    }

    public Khoa(String makhoa, String tenkhoa, String ngtl, String trgkhoa) {
        this.makhoa = makhoa;
        this.tenkhoa = tenkhoa;
        this.ngtl = ngtl;
        this.trgkhoa = trgkhoa;
    }

}
