/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connect.DBConnect;
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
public class SinhVienDAO implements ISinhVienDAO {

    @Override
    public ArrayList<SinhVien> getAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SinhVien> list = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM SINHVIEN");
                rs = ps.executeQuery();
                list = new ArrayList<SinhVien>();
                while (rs.next()) {
                    SinhVien sv = new SinhVien();
                    sv.setMaSV(rs.getString(1));
                    sv.setHoTen(rs.getString(2));
                    sv.setNgSinh(rs.getString(3));
                    sv.setGioitinh(rs.getString(4));
                    sv.setNoisinh(rs.getString(5));
                    sv.setMaLop(rs.getString(6));
                    sv.setSdt(rs.getString(7));
                    list.add(sv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;

    }

    @Override
    public SinhVien findByMaSV(String maSV) {

        PreparedStatement ps = null;
        ResultSet rs = null;
        SinhVien sv = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM SINHVIEN WHERE MASV=?");
                ps.setString(1, maSV);
                rs = ps.executeQuery();
                sv = new SinhVien();
                while (rs.next()) {
                    sv.setMaSV(rs.getString(1));
                    sv.setHoTen(rs.getString(2));
                    sv.setNgSinh(rs.getString(3));
                    sv.setGioitinh(rs.getString(4));
                    sv.setNoisinh(rs.getString(5));
                    sv.setMaLop(rs.getString(6));
                    sv.setSdt(rs.getString(7));
                }
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return sv;
    }

    @Override
    public ArrayList<SinhVien> findByMaLop(String malop) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SinhVien> list = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM SINHVIEN WHERE MALOP=?");
                ps.setString(1, malop);
                rs = ps.executeQuery();
                list = new ArrayList<SinhVien>();
                while (rs.next()) {

                    SinhVien sv = new SinhVien();
                    sv.setMaSV(rs.getString(1));
                    sv.setHoTen(rs.getString(2));
                    sv.setNgSinh(rs.getString(3));
                    sv.setGioitinh(rs.getString(4));
                    sv.setNgSinh(rs.getString(5));
                    sv.setMaLop(rs.getString(6));
                    sv.setSdt(rs.getString(7));
                    list.add(sv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }

        }
        return list;
    }

    @Override
    public ArrayList<SinhVien> findByTenKhoa(String tenKhoa) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SinhVien> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM SINHVIEN \n"
                        + "WHERE MALOP IN( SELECT MALOP FROM LOP ,KHOA WHERE LOP.MAKHOA=KHOA.MAKHOA AND TENKHOA=?) ");
                ps.setString(1, tenKhoa);
                rs = ps.executeQuery();
                list = new ArrayList<SinhVien>();
                while (rs.next()) {
                    SinhVien sv = new SinhVien();
                    sv.setMaSV(rs.getString(1));
                    sv.setHoTen(rs.getString(2));
                    sv.setNgSinh(rs.getString(3));
                    sv.setGioitinh(rs.getString(4));
                    sv.setNoisinh(rs.getString(5));
                    sv.setMaLop(rs.getString(6));
                    sv.setSdt(rs.getString(7));
                    list.add(sv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }

        }
        return list;
    }

    @Override
    public ArrayList<SinhVien> findByTenLop(String tenlop) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SinhVien> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM SINHVIEN WHERE MALOP IN (SELECT MALOP FROM LOP WHERE TENLOP=?) ");
                ps.setString(1, tenlop);
                rs = ps.executeQuery();
                list = new ArrayList<SinhVien>();
                while (rs.next()) {
                    SinhVien sv = new SinhVien();
                    sv.setMaSV(rs.getString(1));
                    sv.setHoTen(rs.getString(2));
                    sv.setNgSinh(rs.getString(3));
                    sv.setGioitinh(rs.getString(4));
                    sv.setNoisinh(rs.getString(5));
                    sv.setMaLop(rs.getString(6));
                    sv.setSdt(rs.getString(7));
                    list.add(sv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }

        }
        return list;

    }

    @Override
    public boolean themSV(SinhVien sv) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("INSERT INTO SINHVIEN VALUES(?,?,?,?,?,?,?,?)");
                ps.setString(1, sv.getMaSV());
                ps.setString(2, sv.getHoTen());
                ps.setString(3, sv.getNgSinh());
                ps.setString(4, sv.getGioitinh());
                ps.setString(5, sv.getNoisinh());
                ps.setString(6, sv.getMaLop());
                ps.setString(7, sv.getSdt());
                ps.setString(8, sv.getMaukhau());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    @Override
    public boolean suaSV(SinhVien sv) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("UPDATE SINHVIEN SET HOTEN=?,NGSINH=?,GIOITINH=?,NOISINH=?,MALOP=?,SODT=? WHERE MASV=? ");
                ps.setString(1, sv.getHoTen());
                ps.setString(2, sv.getNgSinh());
                ps.setString(3, sv.getGioitinh());
                ps.setString(4, sv.getNoisinh());
                ps.setString(5, sv.getMaLop());
                ps.setString(6, sv.getSdt());
                ps.setString(7, sv.getMaSV());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    public boolean xoaSV(SinhVien sv) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM SINHVIEN WHERE MASV=?");
                ps.setString(1, sv.getMaSV());
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    public boolean xoaSVByMalop(String malop) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM SINHVIEN WHERE MALOP=?");
                ps.setString(1, malop);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }

        }
        return false;

    }

    public int countSVbyMalop(String malop) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT COUNT (*) FROM SINHVIEN WHERE MALOP=?");
                ps.setString(1, malop);
                rs = ps.executeQuery();
                rs.next();
                count = rs.getInt(1);

            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return count;

    }

    @Override
    public boolean checkMaSV(String masv) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT COUNT(*)  FROM SINHVIEN WHERE MASV=?");
                ps.setString(1, masv);
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

    public ArrayList<SinhVien> findSVByLHP(String malhp, String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SinhVien> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM SINHVIEN WHERE MASV IN( SELECT MASV FROM DANGKYHOC WHERE MALHP=? AND HOCKY=? AND NAMHOC=?) ");
                ps.setString(1, malhp);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                list = new ArrayList<SinhVien>();
                while (rs.next()) {
                    SinhVien sv = new SinhVien();
                    sv.setMaSV(rs.getString(1));
                    sv.setHoTen(rs.getString(2));
                    list.add(sv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    @Override
    public ArrayList<SinhVien> findSVByName(String name) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<SinhVien> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement(" SELECT * FROM SINHVIEN WHERE HOTEN LIKE '%'+?+'%' ");
                ps.setString(1, name);
                rs = ps.executeQuery();
                list = new ArrayList<SinhVien>();
                while (rs.next()) {
                    SinhVien sv = new SinhVien();
                    sv.setMaSV(rs.getString(1));
                    sv.setHoTen(rs.getString(2));
                    sv.setNgSinh(rs.getString(3));
                    sv.setGioitinh(rs.getString(4));
                    sv.setNoisinh(rs.getString(5));
                    sv.setMaLop(rs.getString(6));
                    sv.setSdt(rs.getString(7));

                    list.add(sv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    public ArrayList<SinhVien> findByDKH(String tenlop, String hocky, String namhoc) {
        ArrayList<SinhVien> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM SINHVIEN SV,LOP L WHERE SV.MALOP=L.MALOP AND TENLOP=? AND MASV IN(SELECT MASV FROM DANGKYHOC WHERE HOCKY=? AND NAMHOC=?)");
                ps.setString(1, tenlop);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                list = new ArrayList<SinhVien>();
                while (rs.next()) {
                    SinhVien sv = new SinhVien();
                    sv.setMaSV(rs.getString(1));
                    sv.setHoTen(rs.getString(2));
                    sv.setMaLop(rs.getString(6));
                    list.add(sv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    public ArrayList<SinhVien> findByMaLHP(String malhp, String hocky, String namhoc) {
        ArrayList<SinhVien> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT DKH.MASV ,  HOTEN FROM DANGKYHOC DKH, SINHVIEN SV WHERE DKH.MASV=SV.MASV AND MALHP=? AND HOCKY=? AND NAMHOC=?");
                ps.setString(1, malhp);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                list = new ArrayList<SinhVien>();
                while (rs.next()) {
                    SinhVien sv = new SinhVien();
                    sv.setMaSV(rs.getString(1));
                    sv.setHoTen(rs.getString(2));
                    list.add(sv);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SinhVienDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

}
