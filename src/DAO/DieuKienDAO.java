/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connect.DBConnect;
import Entities.DieuKien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTMLDocument;

/**
 *
 * @author trung98
 */
public class DieuKienDAO {

    public boolean themDieuKien(DieuKien dk) {
        PreparedStatement ps = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("INSERT INTO DIEUKIEN VALUES(?,?)");
                ps.setString(1, dk.getMamh());
                ps.setString(2, dk.getMamh_truoc());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DieuKienDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    public boolean suaDieuKien(DieuKien dk) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("UPDATE DIEUKIEN SET MAMH_TRUOC=? WHERE MAMH=?");
                ps.setString(1, dk.getMamh_truoc());
                ps.setString(2, dk.getMamh());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DieuKienDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    public boolean xoaDieuKien(String mamh) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE DIEUKIEN WHERE MAMH=?");
                ps.setString(1, mamh);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DieuKienDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

}
