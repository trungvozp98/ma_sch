/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connect.DBConnect;
import Entities.LopHocPhan;
import Entities.MonHoc;
import GUI.Main;
import GUI.MainSV;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author trung98
 */
public class LopHocPhanDAO implements ILopHocPhanDAO {

    @Override
    public ArrayList<LopHocPhan> getAll(String hocKy, String namHoc) {

        ArrayList<LopHocPhan> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM LOPHOCPHAN WHERE HOCKY=? AND NAMHOC=? ");
                ps.setString(1, hocKy);
                ps.setString(2, namHoc);
                rs = ps.executeQuery();
                list = new ArrayList<LopHocPhan>();
                while (rs.next()) {
                    LopHocPhan lhp = new LopHocPhan();

                    lhp.setMaLHP(rs.getString(1));
                    lhp.setHocky(rs.getString(2));
                    lhp.setNamhoc(rs.getString(3));
                    lhp.setMaMH(rs.getString(4));
                    lhp.setThu(rs.getString(5));
                    lhp.setTiet(rs.getString(6));
                    lhp.setPhonghoc(rs.getString(7));
                    lhp.setMaGV(rs.getString(8));
                    lhp.setSiso(rs.getString(9));
                    lhp.setNgayBD(rs.getString(10));
                    lhp.setNgayKT(rs.getString(11));
                    list.add(lhp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    @Override
    public LopHocPhan findByMaLHP(String malhp, String hocky, String namhoc) {
        ResultSet rs = null;
        PreparedStatement ps = null;
        LopHocPhan lhp = null;
        if (DBConnect.open()) {
            try {

                ps = DBConnect.con.prepareStatement("SELECT * FROM LOPHOCPHAN WHERE MALHP=? AND HOCKY=? AND NAMHOC=?  ");
                ps.setString(1, malhp);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                while (rs.next()) {
                    lhp = new LopHocPhan();
                    lhp.setMaLHP(rs.getString(1));
                    lhp.setHocky(rs.getString(2));
                    lhp.setNamhoc(rs.getString(3));
                    lhp.setMaMH(rs.getString(4));
                    lhp.setThu(rs.getString(5));
                    lhp.setTiet(rs.getString(6));
                    lhp.setPhonghoc(rs.getString(7));
                    lhp.setMaGV(rs.getString(8));
                    lhp.setSiso(rs.getString(9));
                    lhp.setNgayBD(rs.getString(10));
                    lhp.setNgayKT(rs.getString(11));
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return lhp;
    }

    @Override
    public ArrayList<LopHocPhan> findByMaLHP(String malhp) {
        ArrayList<LopHocPhan> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {

                ps = DBConnect.con.prepareStatement("SELECT * FROM LOPHOCPHAN WHERE MALHP=?  ");
                ps.setString(1, malhp);
                rs = ps.executeQuery();
                list = new ArrayList<LopHocPhan>();
                while (rs.next()) {
                    LopHocPhan lhp = new LopHocPhan();
                    lhp.setMaLHP(rs.getString(1));

                    lhp.setMaMH(rs.getString(4));
                    lhp.setThu(rs.getString(5));
                    lhp.setTiet(rs.getString(6));
                    lhp.setPhonghoc(rs.getString(7));
                    lhp.setMaGV(rs.getString(8));
                    lhp.setSiso(rs.getString(9));
                    lhp.setNgayBD(rs.getString(10));
                    lhp.setNgayKT(rs.getString(11));
                    list.add(lhp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    @Override
    public ArrayList<LopHocPhan> findByTenMH(String tenmh, String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<LopHocPhan> listLHP = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM LOPHOCPHAN , MONHOC WHERE LOPHOCPHAN.MAMH=MONHOC.MAMH AND TENMH LIKE '%'+?+'%' AND HOCKY=? AND NAMHOC=?");
                ps.setString(1, tenmh);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                listLHP = new ArrayList<LopHocPhan>();
                while (rs.next()) {
                    LopHocPhan lhp = new LopHocPhan();
                    lhp.setMaLHP(rs.getString(1));
                    lhp.setHocky(rs.getString(2));
                    lhp.setNamhoc(rs.getString(3));
                    lhp.setMaMH(rs.getString(4));
                    lhp.setThu(rs.getString(5));
                    lhp.setTiet(rs.getString(6));
                    lhp.setPhonghoc(rs.getString(7));
                    lhp.setMaGV(rs.getString(8));
                    lhp.setNgayBD(rs.getString(10));
                    lhp.setNgayKT(rs.getString(11));
                    lhp.setSiso(rs.getString(9));
                    listLHP.add(lhp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }

        }
        return listLHP;
    }

    @Override
    public ArrayList<LopHocPhan> findByMaMH(String mamh, String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<LopHocPhan> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT MALHP FROM LOPHOCPHAN WHERE MAMH=? AND HOCKY=? AND NAMHOC=? ");
                ps.setString(1, mamh);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                list = new ArrayList<LopHocPhan>();
                while (rs.next()) {
                    LopHocPhan lhp = new LopHocPhan();
                    lhp.setMaLHP(rs.getString(1));
                    list.add(lhp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }

        }
        return list;
    }

    @Override
    public String findNgktByMaLHP(String malhp, String hocky, String namhoc) {
        String ngkt = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {

                ps = DBConnect.con.prepareStatement("SELECT NGAYKT FROM LOPHOCPHAN WHERE MALHP=? AND HOCKY=? AND NAMHOC=? ");
                ps.setString(1, malhp);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ngkt = rs.getString(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
                ngkt = null;
            } finally {
                DBConnect.close();
            }
        }
        return ngkt;
    }

    @Override
    public boolean themLHP(LopHocPhan lhp) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("INSERT INTO LOPHOCPHAN VALUES(?,?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, lhp.getMaLHP());
                ps.setString(2, lhp.getHocky());
                ps.setString(3, lhp.getNamhoc());
                ps.setString(4, lhp.getMaMH());
                ps.setString(5, lhp.getThu());
                ps.setString(6, lhp.getTiet());
                ps.setString(7, lhp.getPhonghoc());
                ps.setString(8, lhp.getMaGV());
                ps.setString(9, lhp.getSiso());
                ps.setString(10, lhp.getNgayBD());
                ps.setString(11, lhp.getNgayKT());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    @Override
    public boolean themLHPnoGV(LopHocPhan lhp) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("INSERT INTO LOPHOCPHAN(MALHP,HOCKY,NAMHOC,MAMH,THU,TIETHOC,PHONG,SISO,NGAYBD,NGAYKT) VALUES(?,?,?,?,?,?,?,?,?,?)");
                ps.setString(1, lhp.getMaLHP());
                ps.setString(2, lhp.getHocky());
                ps.setString(3, lhp.getNamhoc());
                ps.setString(4, lhp.getMaMH());
                ps.setString(5, lhp.getThu());
                ps.setString(6, lhp.getTiet());
                ps.setString(7, lhp.getPhonghoc());
                ps.setString(8, lhp.getSiso());
                ps.setString(9, lhp.getNgayBD());
                ps.setString(10, lhp.getNgayKT());
                ps.executeUpdate();
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    public boolean xoaLHP(LopHocPhan lhp) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM LOPHOCPHAN WHERE MALHP=? AND HOCKY=? AND NAMHOC=?");
                ps.setString(1, lhp.getMaLHP());
                ps.setString(2, lhp.getHocky());
                ps.setString(3, lhp.getNamhoc());
                int row = ps.executeUpdate();
                if (row == 1) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    @Override
    public boolean suaLHP(LopHocPhan lhp) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("UPDATE LOPHOCPHAN SET MAMH=? ,THU=?,TIETHOC=?,PHONG=?,MAGV=?,SISO=? ,NGAYBD=?,NGAYKT=? WHERE MALHP=? AND HOCKY=? AND NAMHOC=? ");
                ps.setString(1, lhp.getMaMH());
                ps.setString(2, lhp.getThu());
                ps.setString(3, lhp.getTiet());
                ps.setString(4, lhp.getPhonghoc());
                ps.setString(5, lhp.getMaGV());
                ps.setString(6, lhp.getSiso());
                ps.setString(7, lhp.getNgayBD());
                ps.setString(8, lhp.getNgayKT());
                ps.setString(9, lhp.getMaLHP());
                ps.setString(10, lhp.getHocky());
                ps.setString(11, lhp.getNamhoc());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;

    }

    @Override
    public boolean suaLHPnoGV(LopHocPhan lhp) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("UPDATE LOPHOCPHAN SET MAMH=? ,THU=?,TIETHOC=?,PHONG=?,SISO=? ,NGAYBD=?,NGAYKT=? WHERE MALHP=? AND HOCKY=? AND NAMHOC=? ");
                ps.setString(1, lhp.getMaMH());
                ps.setString(2, lhp.getThu());
                ps.setString(3, lhp.getTiet());
                ps.setString(4, lhp.getPhonghoc());
                ps.setString(5, lhp.getSiso());
                ps.setString(6, lhp.getNgayBD());
                ps.setString(7, lhp.getNgayKT());
                ps.setString(8, lhp.getMaLHP());
                ps.setString(9, lhp.getHocky());
                ps.setString(10, lhp.getNamhoc());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;

    }

    @Override
    public boolean updatemagv(String malhp, String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("UPDATE LOPHOCPHAN SET MAGV=NULL WHERE MALHP=? AND HOCKY=? AND NAMHOC=? ");

                ps.setString(1, malhp);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;

    }

    @Override
    public boolean checkMaLHP(String malhp, String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        DBConnect.close();
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT COUNT(*) FROM LOPHOCPHAN WHERE MALHP=? AND HOCKY=? AND NAMHOC=?");
                ps.setString(1, malhp);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                rs.next();
                if (rs.getInt(1) != 0) {
                    return false;
                } else {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    public ArrayList<LopHocPhan> deleteByMaMH(String mamh) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<LopHocPhan> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM LOPHOCPHAN WHERE MAMH=?  ");
                ps.setString(1, mamh);
                rs = ps.executeQuery();
                list = new ArrayList<LopHocPhan>();
                while (rs.next()) {
                    LopHocPhan lhp = new LopHocPhan();
                    lhp.setMaLHP(rs.getString(1));
                    lhp.setHocky(rs.getString(2));
                    lhp.setNamhoc(rs.getString(3));
                    list.add(lhp);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopHocPhanDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }

        }
        return list;
    }

    public ArrayList<LopHocPhan> findByMaGV_DK(String magv, String mamh, String hocky, String namhoc) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<LopHocPhan> list = null;
        if (DBConnect.open()) {
            try {

                ps = DBConnect.con.prepareStatement("SELECT DKD.MALHP FROM DANGKYDAY DKD, LOPHOCPHAN LHP WHERE DKD.MALHP=LHP.MALHP AND DKD.HOCKY=LHP.HOCKY AND DKD.NAMHOC=LHP.NAMHOC \n"
                        + "                                                  AND DKD.MAGV=? AND MAMH=? AND DKD.HOCKY=? AND DKD.NAMHOC=?");

                ps.setString(1, magv);
                ps.setString(2, mamh);
                ps.setString(3, hocky);
                ps.setString(4, namhoc);
                rs = ps.executeQuery();
                list = new ArrayList<LopHocPhan>();
                while (rs.next()) {
                    LopHocPhan lhp = new LopHocPhan();
                    lhp.setMaLHP(rs.getString(1));
                    list.add(lhp);
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
