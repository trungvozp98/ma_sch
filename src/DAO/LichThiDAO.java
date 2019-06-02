/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connect.DBConnect;
import Entities.LichThi;
import GUI.Main;
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
public class LichThiDAO implements ILichThiDAO {

    @Override
    public ArrayList<LichThi> findLichthiByMaSV(String maSV, String hocKy, String namHoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<LichThi> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT THU,THI.MALHP,TIET,PHONGTHI,NGAYTHI FROM THI , DANGKYHOC WHERE THI.MALHP =DANGKYHOC.MALHP AND MASV=? AND THI.HOCKY=? AND THI.NAMHOC=? ");
                ps.setString(1, maSV);
                ps.setString(2, hocKy);
                ps.setString(3, namHoc);
                rs = ps.executeQuery();
                list = new ArrayList<LichThi>();
                while (rs.next()) {
                    LichThi lt = new LichThi();
                    lt.setThu(rs.getString(1));
                    lt.setMaLHP(rs.getString(2));
                    lt.setTiet(rs.getString(3));
                    lt.setPhong(rs.getString(4));
                    lt.setNgThi(rs.getString(5));
                    list.add(lt);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LichThiDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    @Override
    public ArrayList<LichThi> findLichthiByMaLHP(String maLHP, String hocky, String namhoc) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<LichThi> list = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT MALHP,THU,CA,PHONGTHI,NGAYTHI FROM THI WHERE MALHP = ? AND HOCKY=? AND NAMHOC=? ");
                ps.setString(1, maLHP);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                list = new ArrayList<LichThi>();
                while (rs.next()) {
                    LichThi lt = new LichThi();
                    lt.setMaLHP(rs.getString(1));
                    lt.setThu(rs.getString(2));
                    lt.setTiet(rs.getString(3));
                    lt.setPhong(rs.getString(4));
                    lt.setNgThi(rs.getString(5));
                    list.add(lt);
                }

            } catch (SQLException ex) {
                Logger.getLogger(LichThiDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();;
            }
        }
        return list;

    }


    @Override
    public ArrayList<LichThi> findByNamhoc(String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<LichThi> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM THI WHERE HOCKY=? AND NAMHOC=?");
                ps.setString(1, hocky);
                ps.setString(2, namhoc);
                rs = ps.executeQuery();
                list = new ArrayList<LichThi>();
                while (rs.next()) {
                    LichThi lt = new LichThi();
                    lt.setMaLHP(rs.getString(1));
                    lt.setHocky(rs.getString(2));
                    lt.setNamhoc(rs.getString(3));
                    lt.setThu(rs.getString(4));
                    lt.setTiet(rs.getString(5));
                    lt.setPhong(rs.getString(6));
                    lt.setMagvct(rs.getString(7));
                    lt.setNgThi(rs.getString(8));
                    list.add(lt);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LichThiDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }

        }
        return list;

    }

    @Override
    public boolean suaLichthi(LichThi lt) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("UPDATE THI SET THU=? , CA=?,PHONGTHI=? ,GVCOITHI=?,NGAYTHI=? WHERE MALHP=?");
                ps.setString(1, lt.getThu());
                ps.setString(2, lt.getTiet());
                ps.setString(3, lt.getPhong());
                ps.setString(4, lt.getMagvct());
                ps.setString(5, lt.getNgThi());
                ps.setString(6, lt.getMaLHP());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(LichThiDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    public boolean xoaLichthi(LichThi lt) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM THI WHERE MALHP=? AND HOCKY=? AND NAMHOC=?");
                ps.setString(1, lt.getMaLHP());
                ps.setString(2, lt.getHocky());
                ps.setString(3, lt.getNamhoc());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(LichThiDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    @Override
    public ArrayList<LichThi> findByTenMH(String mh, String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<LichThi> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("   SELECT T.MALHP,T.HOCKY,T.NAMHOC,T.THU,CA,PHONGTHI,GVCOITHI,NGAYTHI\n"
                        + "    FROM THI T,LOPHOCPHAN LHP , MONHOC MH WHERE T.MALHP=LHP.MALHP AND T.HOCKY=LHP.HOCKY AND T.NAMHOC=LHP.NAMHOC \n"
                        + "                                                        AND LHP.MAMH=MH.MAMH AND TENMH LIKE '%'+?+'%' AND T.HOCKY=? AND T.NAMHOC=?");
                ps.setString(1, mh);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                list = new ArrayList<LichThi>();
                while (rs.next()) {
                    LichThi lt = new LichThi();
                    lt.setMaLHP(rs.getString(1));
                    lt.setHocky(rs.getString(2));
                    lt.setNamhoc(rs.getString(3));
                    lt.setThu(rs.getString(4));
                    lt.setTiet(rs.getString(5));
                    lt.setPhong(rs.getString(6));
                    lt.setMagvct(rs.getString(7));
                    lt.setNgThi(rs.getString(8));
                    list.add(lt);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LichThiDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;

    }

    public boolean updateByMon(String thu, String ca, String ngaythi, String mamh) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("UPDATE THI SET THU = ? , CA=? , NGAYTHI=? WHERE MALHP \n"
                        + "           IN( SELECT T.MALHP FROM LOPHOCPHAN LHP , THI T  WHERE LHP.MALHP=T.MALHP AND LHP.HOCKY=T.HOCKY\n"
                        + "		    AND LHP.NAMHOC=T.NAMHOC AND MAMH=? )");

                ps.setString(1, thu);
                ps.setString(2, ca);
                ps.setString(3, ngaythi);
                ps.setString(4, mamh);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(LichThiDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;

    }

}
