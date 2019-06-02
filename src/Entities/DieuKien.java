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
public class DieuKien {

    private String mamh;
    private String mamh_truoc;

    public String getMamh() {
        return mamh;
    }

    public String getMamh_truoc() {
        return mamh_truoc;
    }

    public void setMamh(String mamh) {
        this.mamh = mamh;
    }

    public void setMamh_truoc(String mamh_truoc) {
        this.mamh_truoc = mamh_truoc;
    }

    public DieuKien() {
    }

    public DieuKien(String mamh, String mamh_truoc) {
        this.mamh = mamh;
        this.mamh_truoc = mamh_truoc;
    }

}
