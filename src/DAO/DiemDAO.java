/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connect.DBConnect;
import Entities.DangKyHoc;
import Entities.Diem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trung98
 */
public class DiemDAO implements IDiemDAO{
    
    @Override
    public ArrayList<Diem> findByMaSV(String maSV, String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Diem> list = null;
        
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT *  FROM DIEM WHERE MASV=? AND HOCKY=? AND NAMHOC=? ");
                ps.setString(1, maSV);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                list = new ArrayList<Diem>();
                while (rs.next()) {
                    Diem d = new Diem();
                    d.setMaSV(rs.getString(1));
                    d.setMaLHP(rs.getString(2));
                    d.setHocky(rs.getString(3));
                    d.setNamhoc(rs.getString(4));
                    d.setDiemQT(rs.getFloat(5));
                    d.setDiemGK(rs.getFloat(6));
                    d.setDiemCK(rs.getFloat(7));
                    d.setDiemTB(rs.getFloat(8));
                    list.add(d);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DiemDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
        
    }
    
    public Diem findDiemSV_LHP(String masv, String malhp, String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Diem diem = null;
        
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT *  FROM DIEM WHERE MASV=? AND MALHP=? AND HOCKY=? AND NAMHOC=? ");
                ps.setString(1, masv);
                ps.setString(2, malhp);
                ps.setString(3, hocky);
                ps.setString(4, namhoc);
                rs = ps.executeQuery();
                while (rs.next()) {
                    diem = new Diem();
                    diem.setMaSV(rs.getString(1));
                    diem.setMaLHP(rs.getString(2));
                    diem.setHocky(rs.getString(3));
                    diem.setNamhoc(rs.getString(4));
                    diem.setDiemQT(rs.getFloat(5));
                    diem.setDiemGK(rs.getFloat(6));
                    diem.setDiemCK(rs.getFloat(7));
                    diem.setDiemTB(rs.getFloat(8));
                    diem.setGhichu(rs.getString(9));
                }
            } catch (SQLException ex) {
                Logger.getLogger(DiemDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return diem;
        
    }
    
    public boolean huyByMaSV(String masv) {
        
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM DIEM WHERE MASV=?");
                ps.setString(1, masv);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DiemDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
        
    }
    
    public boolean huyByMaSV(DangKyHoc dkh) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM DIEM WHERE MASV=? AND MALHP=? AND HOCKY=? AND NAMHOC=?");
                ps.setString(1, dkh.getMaSV());
                ps.setString(2, dkh.getMaLHP());
                ps.setString(3, dkh.getHocKy());
                ps.setString(4, dkh.getNamHoc());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DiemDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
        
    }
    
    @Override
    public boolean updateDiem(Diem d) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("UPDATE DIEM SET DIEMQT=? ,DIEMGK=?,DIEMCK=? WHERE MASV=? AND MALHP=? AND HOCKY=? AND NAMHOC=? ");
                ps.setFloat(1, d.getDiemQT());
                ps.setFloat(2, d.getDiemGK());
                ps.setFloat(3, d.getDiemCK());
                ps.setString(4, d.getMaSV());
                ps.setString(5, d.getMaLHP());
                ps.setString(6, d.getHocky());
                ps.setString(7, d.getNamhoc());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DiemDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
        
    }
    
    public boolean insertDiem(Diem d) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("INSERT INTO DIEM(MASV,MALHP,HOCKY,NAMHOC,DIEMQT,DIEMGK,DIEMCK) VALUES(?,?,?,?,?,?,?) ");
                ps.setString(1, d.getMaSV());
                ps.setString(2, d.getMaLHP());
                ps.setString(3, d.getHocky());
                ps.setString(4, d.getNamhoc());
                ps.setFloat(5, d.getDiemQT());
                ps.setFloat(6, d.getDiemGK());
                ps.setFloat(7, d.getDiemCK());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DiemDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
        
    }
    
    @Override
    public float calDiemTB(String masv, String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT AVG(DIEMTB) AS DIEMTB FROM DIEM WHERE MASV=? AND HOCKY=? AND NAMHOC=? ");
                ps.setString(1, masv);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                rs.next();
                return rs.getFloat(1);
            } catch (SQLException ex) {
                Logger.getLogger(DiemDAO.class.getName()).log(Level.SEVERE, null, ex);
                
            } finally {
                DBConnect.close();
            }
        }
        return 0;
        
    }
    
    
}
