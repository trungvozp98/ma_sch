/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connect.DBConnect;
import Entities.Khoa;
import java.math.BigDecimal;
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
public class KhoaDAO implements IKhoaDAO {

    @Override
    public ArrayList<Khoa> getAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Khoa> list = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM KHOA");
                rs = ps.executeQuery();
                list = new ArrayList<Khoa>();
                while (rs.next()) {
                    Khoa k = new Khoa();
                    k.setMakhoa(rs.getString(1));
                    k.setTenkhoa(rs.getString(2));
                    k.setNgtl(rs.getString(3));
                    k.setTrgkhoa(rs.getString(4));
                    list.add(k);
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;

    }

    @Override
    public Khoa findByMaKhoa(String makhoa) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Khoa khoa = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM KHOA WHERE MAKHOA=?");
                ps.setString(1, makhoa);
                rs = ps.executeQuery();
                khoa = new Khoa();
                rs.next();
                khoa.setMakhoa(rs.getString(1));
                khoa.setTenkhoa(rs.getString(2));
                khoa.setNgtl(rs.getString(3));
                khoa.setTrgkhoa(rs.getString(4));

            } catch (SQLException ex) {
                Logger.getLogger(KhoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }

        }
        return khoa;

    }

    @Override
    public Khoa findByTenKhoa(String tenKhoa) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Khoa k = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM KHOA WHERE TENKHOA=?");
                ps.setString(1, tenKhoa);
                while (rs.next()) {
                    k = new Khoa();
                    k.setMakhoa(rs.getString(1));
                    k.setTenkhoa(rs.getString(2));
                    k.setNgtl(rs.getString(3));
                    k.setTrgkhoa(rs.getString(4));
                }
            } catch (SQLException ex) {
                Logger.getLogger(KhoaDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return k;
    }

    @Override
    public boolean themKhoa(Khoa k) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {

            try {
                ps = DBConnect.con.prepareStatement("INSERT INTO KHOA VALUES(?,?,?,?)");
                ps.setString(1, k.getMakhoa());
                ps.setString(2, k.getTenkhoa());
                ps.setString(3, k.getNgtl());
                ps.setString(4, k.getTrgkhoa());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(KhoaDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;

    }

    @Override
    public boolean suaKhoa(Khoa k) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {

            try {
                ps = DBConnect.con.prepareStatement("UPDATE KHOA SET TENKHOA=?,NGTLAP=?,TRGKHOA=? WHERE MAKHOA=?");
                ps.setString(1, k.getTenkhoa());
                ps.setString(2, k.getNgtl());
                ps.setString(3, k.getTrgkhoa());
                ps.setString(4, k.getMakhoa());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(KhoaDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    public boolean xoaKhoa(String makhoa) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM KHOA WHERE MAKHOA=?");
                ps.setString(1, makhoa);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(KhoaDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    @Override
    public boolean checkMakhoa(String makhoa) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT COUNT(*)  FROM KHOA WHERE MAKHOA=?");
                ps.setString(1, makhoa);
                rs = ps.executeQuery();
                rs.next();
                int i = rs.getInt(1);
                if (i != 0) {
                    return false;
                } else {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

}
