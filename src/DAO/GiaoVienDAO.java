/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connect.DBConnect;
import Entities.GiaoVien;
import Entities.SinhVien;
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
public class GiaoVienDAO implements IGiaoVienDAO {

    @Override
    public ArrayList<GiaoVien> getAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<GiaoVien> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM GIAOVIEN");
                rs = ps.executeQuery();
                list = new ArrayList<GiaoVien>();
                while (rs.next()) {
                    GiaoVien gv = new GiaoVien();
                    gv.setMaGV(rs.getString(1));
                    gv.setTenGV(rs.getString(2));
                    gv.setHocvi(rs.getString(3));
                    gv.setGioitinh(rs.getString(4));
                    gv.setNgsinh(rs.getString(5));
                    gv.setNgvl(rs.getString(6));
                    gv.setMucluong(rs.getString(7));
                    gv.setMaKhoa(rs.getString(8));
                    gv.setSodt(rs.getString(10));
                    gv.setDiachi(rs.getString(11));
                    list.add(gv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(GiaoVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    @Override
    public GiaoVien findByMaGV(String magv) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        GiaoVien gv = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM GIAOVIEN WHERE MAGV=?");
                ps.setString(1, magv);
                rs = ps.executeQuery();
                gv = new GiaoVien();
                rs.next();
                gv.setMaGV(rs.getString(1));
                gv.setTenGV(rs.getString(2));
                gv.setHocvi(rs.getString(3));
                gv.setGioitinh(rs.getString(4));
                gv.setNgsinh(rs.getString(5));
                gv.setNgvl(rs.getString(6));
                gv.setMucluong(rs.getString(7));
                gv.setMaKhoa(rs.getString(8));
                gv.setMaukhau(rs.getString(9));
                gv.setSodt(rs.getString(10));
                gv.setDiachi(rs.getString(11));
            } catch (SQLException ex) {
                Logger.getLogger(GiaoVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return gv;
    }

    public GiaoVien findByMalop(String tenlop) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        GiaoVien gv = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM GIAOVIEN WHERE MAGV IN (SELECT MAGVCN FROM LOP WHERE TENLOP=?)");
                ps.setString(1, tenlop);
                rs = ps.executeQuery();
                gv = new GiaoVien();
                rs.next();
                gv.setMaGV(rs.getString(1));
                gv.setTenGV(rs.getString(2));
            } catch (SQLException ex) {
                Logger.getLogger(GiaoVienDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return gv;

    }

    @Override
    public ArrayList<GiaoVien> findByMaKhoa(String makhoa) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<GiaoVien> list = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM GIAOVIEN WHERE MAKHOA=? ");
                ps.setString(1, makhoa);
                rs = ps.executeQuery();
                list = new ArrayList<GiaoVien>();
                while (rs.next()) {
                    GiaoVien gv = new GiaoVien();
                    gv.setMaGV(rs.getString(1));
                    gv.setTenGV(rs.getString(2));
                    gv.setHocvi(rs.getString(3));
                    gv.setGioitinh(rs.getString(4));
                    gv.setNgsinh(rs.getString(5));
                    gv.setNgvl(rs.getString(6));
                    gv.setMucluong(rs.getString(7));
                    gv.setMaKhoa(rs.getString(8));
                    list.add(gv);

                }
            } catch (SQLException ex) {
                Logger.getLogger(GiaoVienDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }

        }
        return list;
    }

    @Override
    public ArrayList<GiaoVien> findByTenKhoa(String tenkhoa) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<GiaoVien> list = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM GIAOVIEN WHERE MAKHOA IN (SELECT MAKHOA FROM KHOA WHERE TENKHOA=?) ");
                ps.setString(1, tenkhoa);
                rs = ps.executeQuery();
                list = new ArrayList<GiaoVien>();
                while (rs.next()) {
                    GiaoVien gv = new GiaoVien();
                    gv.setMaGV(rs.getString(1));
                    gv.setTenGV(rs.getString(2));
                    gv.setHocvi(rs.getString(3));
                    gv.setGioitinh(rs.getString(4));
                    gv.setNgsinh(rs.getString(5));
                    gv.setNgvl(rs.getString(6));
                    gv.setMucluong(rs.getString(7));
                    gv.setMaKhoa(rs.getString(8));
                    list.add(gv);

                }
            } catch (SQLException ex) {
                Logger.getLogger(GiaoVienDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }

        }
        return list;
    }

    @Override
    public boolean themGV(GiaoVien gv) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("INSERT INTO GIAOVIEN(MAGV,HOTEN,HOCVI,GIOITINH,NGSINH,NGVL,MUCLUONG,MAKHOA) VALUES(?,?,?,?,?,?,?,?)");
                ps.setString(1, gv.getMaGV());
                ps.setString(2, gv.getTenGV());
                ps.setString(3, gv.getHocvi());
                ps.setString(4, gv.getGioitinh());
                ps.setString(5, gv.getNgsinh());
                ps.setString(6, gv.getNgvl());
                ps.setString(7, gv.getMucluong());
                ps.setString(8, gv.getMaKhoa());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(GiaoVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    public boolean xoaGV(GiaoVien gv) {
        PreparedStatement ps = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM GIAOVIEN WHERE MAGV=?");
                ps.setString(1, gv.getMaGV());
                ps.executeUpdate();
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(GiaoVienDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;

    }

    @Override
    public boolean suaGV(GiaoVien gv) {
        PreparedStatement ps = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("UPDATE GIAOVIEN SET HOTEN=?,HOCVI=?,GIOITINH=?,NGSINH=?,NGVL=?,MUCLUONG=?,MAKHOA=? WHERE MAGV=?");

                ps.setString(1, gv.getTenGV());
                ps.setString(2, gv.getHocvi());
                ps.setString(3, gv.getGioitinh());
                ps.setString(4, gv.getNgsinh());
                ps.setString(5, gv.getNgvl());
                ps.setString(6, gv.getMucluong());
                ps.setString(7, gv.getMaKhoa());
                ps.setString(8, gv.getMaGV());
                ps.executeUpdate();
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(GiaoVienDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    @Override
    public boolean updateThongtinLienlan(String sdt, String diachi, String magv) {
        PreparedStatement ps = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("UPDATE GIAOVIEN SET SODT=?,DIACHI=? WHERE MAGV=?");
                ps.setString(1, sdt);
                ps.setString(2, diachi);
                ps.setString(3, magv);
                ps.executeUpdate();
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(GiaoVienDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    @Override
    public boolean checkMaGV(String magv) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT COUNT(*) FROM GIAOVIEN WHERE MAGV=?");
                ps.setString(1, magv);
                rs = ps.executeQuery();
                rs.next();
                int i = rs.getInt(1);
                if (i != 0) {
                    return false;
                } else {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    @Override
    public ArrayList<GiaoVien> findSVByName(String name) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<GiaoVien> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM GIAOVIEN WHERE HOTEN LIKE '%'+? + '%' ");
                ps.setString(1, name);
                rs = ps.executeQuery();
                list = new ArrayList<GiaoVien>();
                while (rs.next()) {
                    GiaoVien gv = new GiaoVien();
                    gv.setMaGV(rs.getString(1));
                    gv.setTenGV(rs.getString(2));
                    gv.setHocvi(rs.getString(3));
                    gv.setGioitinh(rs.getString(4));
                    gv.setNgsinh(rs.getString(5));
                    gv.setNgvl(rs.getString(6));
                    gv.setMucluong(rs.getString(7));
                    gv.setMaKhoa(rs.getString(8));
                    list.add(gv);

                }
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    public int countGVByKhoa(String makhoa) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int i = 0;
        if (DBConnect.open()) {

            try {
                ps = DBConnect.con.prepareStatement("SELECT COUNT(*) FROM GIAOVIEN WHERE MAKHOA=?");
                ps.setString(1, makhoa);
                rs = ps.executeQuery();
                rs.next();
                i = rs.getInt(1);

            } catch (SQLException ex) {
                Logger.getLogger(GiaoVienDAO.class
                        .getName()).log(Level.SEVERE, null, ex);

            } finally {
                DBConnect.close();
            }

        }
        return i;
    }

}
