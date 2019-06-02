/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connect.DBConnect;
import Entities.Khoa;
import Entities.LopHocPhan;
import Entities.MonHoc;
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
public class MonHocDAO implements IMonHocDAO {

    @Override
    public ArrayList<MonHoc> getAll() {
        ArrayList<MonHoc> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {

            try {
                list = new ArrayList<MonHoc>();
                ps = DBConnect.con.prepareStatement("SELECT * FROM MONHOC");
                rs = ps.executeQuery();

                while (rs.next()) {
                    MonHoc mh = new MonHoc();
                    mh.setMaMH(rs.getString(1));
                    mh.setTenMH(rs.getString(2));
                    mh.setSoTC(rs.getString(3));
                    mh.setMaKhoa(rs.getString(4));
                    list.add(mh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MonHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    @Override
    public MonHoc findByMaMH(String mamh) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        MonHoc mh = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM MONHOC WHERE MAMH=?");
                ps.setString(1, mamh);
                rs = ps.executeQuery();
                while (rs.next()) {
                    mh = new MonHoc();
                    mh.setMaMH(rs.getString(1));
                    mh.setTenMH(rs.getString(2));
                    mh.setSoTC(rs.getString(3));
                    mh.setMaKhoa(rs.getString(4));
                }
            } catch (SQLException ex) {
                Logger.getLogger(MonHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return mh;
    }

    @Override
    public ArrayList<MonHoc> findByTenMH(String tenmh) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<MonHoc> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM MONHOC WHERE TENMH LIKE '%'+?+'%'");
                ps.setString(1, tenmh);
                rs = ps.executeQuery();
                list = new ArrayList<MonHoc>();
                while (rs.next()) {
                    MonHoc mh = new MonHoc();
                    mh.setMaMH(rs.getString(1));
                    mh.setTenMH(rs.getString(2));
                    mh.setSoTC(rs.getString(3));
                    mh.setMaKhoa(rs.getString(4));
                    list.add(mh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MonHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    @Override
    public ArrayList<MonHoc> findMaMH_TRUOC(String maMH) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<MonHoc> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT MAMH_TRUOC FROM DIEUKIEN WHERE MAMH=?");
                ps.setString(1, maMH);
                rs = ps.executeQuery();
                list = new ArrayList<MonHoc>();
                while (rs.next()) {
                    MonHoc mh = new MonHoc();
                    mh.setMaMH(rs.getString(1));
                    list.add(mh);

                }
            } catch (SQLException ex) {
                Logger.getLogger(MonHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    @Override
    public boolean checkMaMH(String mamh) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT COUNT(*) FROM MONHOC WHERE MAMH=?");
                ps.setString(1, mamh);
                rs = ps.executeQuery();
                rs.next();
                if (rs.getInt(1) != 0) {
                    return false;
                } else {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(MonHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    @Override
    public boolean themMonhoc(MonHoc mh) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("INSERT INTO MonHoc VALUES(?,?,?,?)");
                ps.setString(1, mh.getMaMH());
                ps.setString(2, mh.getTenMH());
                ps.setString(3, mh.getSoTC());
                ps.setString(4, mh.getMaKhoa());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    @Override
    public boolean suaMonhoc(MonHoc mh) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("UPDATE MONHOC SET TENMH=?,SOTC=?,MAKHOA=? WHERE MAMH=?");
                ps.setString(1, mh.getTenMH());
                ps.setString(2, mh.getSoTC());
                ps.setString(3, mh.getMaKhoa());
                ps.setString(4, mh.getMaMH());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }

        }
        return false;
    }

    public boolean xoaMonhoc(String mamh) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM MONHOC WHERE MAMH=?");
                ps.setString(1, mamh);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    @Override
    public ArrayList<MonHoc> findByMaGV_DK(String magv, String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<MonHoc> list = null;
        if (DBConnect.open()) {
            try {

                ps = DBConnect.con.prepareStatement("SELECT DISTINCT MH.MAMH ,TENMH FROM DANGKYDAY DKD, LOPHOCPHAN LHP , MONHOC MH WHERE LHP.MAMH=MH.MAMH AND DKD.MALHP=LHP.MALHP AND DKD.HOCKY=LHP.HOCKY AND DKD.NAMHOC=LHP.NAMHOC \n"
                        + "                                                  AND DKD.MAGV=?  AND DKD.HOCKY=? AND DKD.NAMHOC=?");

                ps.setString(1, magv);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                list = new ArrayList<MonHoc>();
                while (rs.next()) {
                    MonHoc mh = new MonHoc();
                    mh.setMaMH(rs.getString(1));
                    mh.setTenMH(rs.getString(2));
                    list.add(mh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MonHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;

    }
}


