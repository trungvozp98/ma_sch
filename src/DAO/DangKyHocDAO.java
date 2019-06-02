/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connect.DBConnect;
import Entities.DangKyHoc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DangKyHocDAO {

    public ArrayList<DangKyHoc> getAll() {
        ArrayList<DangKyHoc> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM DANGKYHOC");
                rs = ps.executeQuery();
                list = new ArrayList<DangKyHoc>();
                while (rs.next()) {
                    DangKyHoc dkh = new DangKyHoc();
                    dkh.setMaSV(rs.getString(1));
                    dkh.setMaLHP(rs.getString(2));
                    dkh.setHocKy(rs.getString(3));
                    dkh.setNamHoc(rs.getString(4));
                    dkh.setNgayDangKy(rs.getString(5));
                    list.add(dkh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();;
            }
        }
        return list;
    }

    public boolean dangkyhoc(DangKyHoc dkh) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("INSERT INTO DANGKYHOC VALUES(?,?,?,?,?)");
                ps.setString(1, dkh.getMaSV());
                ps.setString(2, dkh.getMaLHP());
                ps.setString(3, dkh.getHocKy());
                ps.setString(4, dkh.getNamHoc());
                ps.setString(5, dkh.getNgayDangKy());
                int row = ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    public ArrayList<DangKyHoc> findByMaSV(String MaSV, String hocKy, String namHoc) {
        ArrayList<DangKyHoc> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM DANGKYHOC WHERE MASV=? AND HOCKY=? AND NAMHOC=?");
                ps.setString(1, MaSV);
                ps.setString(2, hocKy);
                ps.setString(3, namHoc);
                rs = ps.executeQuery();
                list = new ArrayList<DangKyHoc>();
                while (rs.next()) {
                    DangKyHoc dkh = new DangKyHoc();
                    dkh.setMaSV(rs.getString(1));
                    dkh.setMaLHP(rs.getString(2));
                    dkh.setHocKy(rs.getString(3));
                    dkh.setNamHoc(rs.getString(4));
                    dkh.setNgayDangKy(rs.getString(5));
                    list.add(dkh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;

    }

    public ArrayList<DangKyHoc> checkMaLHP(String maLHP, String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DangKyHoc> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM DANGKYHOC WHERE MALHP=? AND HOCKY=? AND NAMHOC=? ");
                ps.setString(1, maLHP);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                list = new ArrayList<DangKyHoc>();
                while (rs.next()) {
                    DangKyHoc dkh = new DangKyHoc();
                    dkh.setMaLHP(rs.getString(2));
                    dkh.setHocKy(rs.getString(3));
                    dkh.setNamHoc(rs.getString(4));
                    list.add(dkh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;

    }

    public DangKyHoc huydangky(DangKyHoc dkh) {
        PreparedStatement ps = null;
        DangKyHoc dk = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM DANGKYHOC WHERE MASV =? AND MALHP=? AND HOCKY=? AND NAMHOC=? ");
                ps.setString(1, dkh.getMaSV());
                ps.setString(2, dkh.getMaLHP());
                ps.setString(3, dkh.getHocKy());
                ps.setString(4, dkh.getNamHoc());

                int row = ps.executeUpdate();
                if (row < 1) {
                    dk = null;
                }
                dk = new DangKyHoc();
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return dk;
    }

    public int tongSoMon(String maSV, String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int sum = 0;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT COUNT(*) FROM DANGKYHOC WHERE MASV =? AND HOCKY=? AND NAMHOC=?");
                ps.setString(1, maSV);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                rs.next();
                sum = rs.getInt(1);

            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return sum;
    }

    public ArrayList<DangKyHoc> findByMaSV(String maSV) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DangKyHoc> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM DANGKYHOC WHERE MASV =?");
                ps.setString(1, maSV);
                rs = ps.executeQuery();
                list = new ArrayList<DangKyHoc>();
                while (rs.next()) {
                    DangKyHoc dkh = new DangKyHoc();
                    dkh.setMaSV(rs.getString(1));
                    dkh.setMaLHP(rs.getString(2));
                    dkh.setHocKy(rs.getString(3));
                    dkh.setNamHoc(rs.getString(4));
                    dkh.setNgayDangKy(rs.getString(5));
                    list.add(dkh);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }

        }
        return list;

    }

    public void deleteByMaLHP(String maLHP, String hocky, String namhoc) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM DANGKYHOC WHERE MALHP=? AND HOCKY=? AND NAMHOC=?");
                ps.setString(1, maLHP);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                int row = ps.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }

    }

    public boolean huyByMaSV(String maSV) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM DANGKYHOC WHERE MASV=?");
                ps.setString(1, maSV);
                ps.executeUpdate();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }

        }
        return false;

    }

    public boolean checkMaLHP_DKD(DangKyHoc dkh) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement(" SELECT COUNT(*) FROM DANGKYHOC WHERE MASV=? AND MALHP=? AND HOCKY=? AND NAMHOC=?");
                ps.setString(1, dkh.getMaSV());
                ps.setString(2, dkh.getMaLHP());
                ps.setString(3, dkh.getHocKy());
                ps.setString(4, dkh.getNamHoc());
                rs = ps.executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }

        }
        return false;

    }

    public boolean checkMaMH_TRUOC(String mamh, String masv, String hocky, String namhoc) {
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        if (DBConnect.open()) {
            try {
                ps1 = DBConnect.con.prepareStatement(" SELECT MAMH_TRUOC FROM DIEUKIEN WHERE MAMH=?");
                ps1.setString(1, mamh);
                rs = ps1.executeQuery();
                if (rs == null) {
                    return true;
                } else {
                    while (rs.next()) {
                        ps2 = DBConnect.con.prepareStatement("SELECT COUNT(*) FROM DANGKYHOC DKH , LOPHOCPHAN LHP WHERE DKH.MALHP=LHP.MALHP AND DKH.HOCKY=LHP.HOCKY AND DKH.NAMHOC=LHP.NAMHOC\n"
                                + "                                              AND  MASV=? AND  (DKH.NAMHOC != ? OR DKH.HOCKY !=?)\n"
                                + "											  AND MAMH IN  (SELECT MAMH_TRUOC FROM DIEUKIEN WHERE MAMH=?)");
                        ps2.setString(1, masv);
                        ps2.setString(2, namhoc);
                        ps2.setString(3, hocky);
                        ps2.setString(4, mamh);
                        rs2 = ps2.executeQuery();
                        rs2.next();
                        if (rs2.getInt(1) == 0) {
                            return false;
                        }
                    }
                    return true;
                }

            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }

        }
        return false;

    }

    public boolean checkMH(DangKyHoc dkh, String mamh) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT COUNT(*) FROM LOPHOCPHAN LHP,DANGKYHOC DKH\n"
                        + "          WHERE LHP.MALHP=DKH.MALHP AND LHP.HOCKY=DKH.HOCKY AND LHP.NAMHOC=DKH.NAMHOC AND MASV=? AND MAMH=? AND DKH.HOCKY=? AND DKH.NAMHOC=?");
                ps.setString(1, dkh.getMaSV());
                ps.setString(2, mamh);
                ps.setString(3, dkh.getHocKy());
                ps.setString(4, dkh.getNamHoc());
                rs = ps.executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) {
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;

    }

}
