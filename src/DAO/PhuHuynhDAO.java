/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connect.DBConnect;
import Entities.PhuHuynh;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trung98
 */
public class PhuHuynhDAO {

    public PhuHuynh findByMaSV(String maSV) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        PhuHuynh ph = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM PHUHUYNH WHERE MASV=?");
                ps.setString(1, maSV);
                rs = ps.executeQuery();
                ph = new PhuHuynh();
                rs.next();
                ph.setMasv(rs.getString(1));
                ph.setHotenCha(rs.getString(2));
                ph.setHotenMe(rs.getString(3));
                ph.setDiachi(rs.getString(4));
                ph.setSodt(rs.getString(5));
                ph.setQuoctich(rs.getString(6));
            } catch (SQLException ex) {
                Logger.getLogger(PhuHuynhDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return ph;

    }

    public boolean updatePHByMaSV(PhuHuynh ph) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("UPDATE PHUHUYNH SET HOTENCHA=?,HOTENME=?,DIACHI=?,SODT=?,QUOCTICH=? WHERE MASV=?");
                ps.setString(1, ph.getHotenCha());
                ps.setString(2, ph.getHotenMe());
                ps.setString(3, ph.getDiachi());
                ps.setString(4, ph.getSodt());
                ps.setString(5, ph.getQuoctich());
                ps.setString(6, ph.getMasv());
                int row = ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(PhuHuynhDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;

    }

    public boolean huyByMaSV(String maSV) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM PHUHUYNH WHERE MASV=?");
                ps.setString(1, maSV);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(PhuHuynhDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }

        }
        return false;

    }

}
