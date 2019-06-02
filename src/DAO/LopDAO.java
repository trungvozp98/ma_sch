/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connect.DBConnect;
import Entities.Lop;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Võ Đại Nhật Trung
 */
public class LopDAO implements ILopDAO {

    @Override
    public ArrayList<Lop> getAll() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Lop> list = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM LOP");
                rs = ps.executeQuery();
                list = new ArrayList<Lop>();
                while (rs.next()) {
                    Lop l = new Lop();
                    l.setMalop(rs.getString(1));
                    l.setTenlop(rs.getString(2));
                    l.setSiso(rs.getString(3));
                    l.setMagvcn(rs.getString(4));
                    l.setMakhoa(rs.getString(5));
                    list.add(l);

                }
            } catch (SQLException ex) {
                Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    @Override
    public Lop findByMalop(String malop) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Lop l = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM LOP WHERE MALOP=?");
                ps.setString(1, malop);
                rs = ps.executeQuery();
                while (rs.next()) {
                    l = new Lop();
                    l.setMalop(rs.getString(1));
                    l.setTenlop(rs.getString(2));
                    l.setSiso(rs.getString(3));
                    l.setMagvcn(rs.getString(4));
                    l.setMakhoa(rs.getString(5));
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return l;
    }

    @Override
    public ArrayList<Lop> findByMaKhoa(String maKhoa) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Lop> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM LOP WHERE MAKHOA=?");
                ps.setString(1, maKhoa);
                rs = ps.executeQuery();
                list = new ArrayList<Lop>();
                while (rs.next()) {
                    Lop l = new Lop();
                    l.setMalop(rs.getString(1));
                    l.setTenlop(rs.getString(2));
                    l.setSiso(rs.getString(3));
                    l.setMagvcn(rs.getString(4));
                    l.setMakhoa(rs.getString(5));
                    list.add(l);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    @Override
    public ArrayList<Lop> findByTenKhoa(String tenKhoa) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Lop> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM LOP WHERE MAKHOA IN (SELECT MAKHOA FROM KHOA WHERE TENKHOA=?)");
                ps.setString(1, tenKhoa);
                rs = ps.executeQuery();
                list = new ArrayList<Lop>();
                while (rs.next()) {
                    Lop l = new Lop();
                    l.setMalop(rs.getString(1));
                    l.setTenlop(rs.getString(2));
                    l.setSiso(rs.getString(3));
                    l.setMagvcn(rs.getString(4));
                    l.setMakhoa(rs.getString(5));
                    list.add(l);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;
    }

    @Override
    public boolean themLop(Lop l) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("INSERT INTO LOP VALUES(?,?,?,?,?)");
                ps.setString(1, l.getMalop());
                ps.setString(2, l.getTenlop());
                ps.setString(3, l.getSiso());
                ps.setString(4, l.getMagvcn());
                ps.setString(5, l.getMakhoa());
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
    public boolean suaLop(Lop l) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("UPDATE LOP SET TENLOP=?,SISO=?,MAGVCN=?,MAKHOA=? WHERE MALOP=?");
                ps.setString(1, l.getTenlop());
                ps.setString(2, l.getSiso());
                ps.setString(3, l.getMagvcn());
                ps.setString(4, l.getMakhoa());
                ps.setString(5, l.getMalop());
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

    public boolean xoaLop(String malop) {
        PreparedStatement ps = null;
        DBConnect.close();
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM LOP WHERE MALOP=?");
                ps.setString(1, malop);
                int row = ps.executeUpdate();
                if (row == 1) {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                DBConnect.close();
            }
        }
        return false;
    }

    public int takeSisoByLop(String malop) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT SISO FROM LOP WHERE MALOP=?");
                ps.setString(1, malop);
                rs = ps.executeQuery();
                rs.next();
                return rs.getInt(1);
            } catch (SQLException ex) {
                Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return 0;
    }

    @Override
    public boolean checkTenlop(String tenlop) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT COUNT(*)  FROM LOP WHERE TENLOP=?");
                ps.setString(1, tenlop);
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

    @Override
    public ArrayList<Lop> findByTenlop(String tenlop) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Lop> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM LOP WHERE TENLOP LIKE '%'+?+'%'");
                ps.setString(1, tenlop);
                rs = ps.executeQuery();
                list = new ArrayList<Lop>();
                while (rs.next()) {
                    Lop l = new Lop();
                    l.setMalop(rs.getString(1));
                    l.setTenlop(rs.getString(2));
                    l.setSiso(rs.getString(3));
                    l.setMagvcn(rs.getString(4));
                    l.setMakhoa(rs.getString(5));
                    list.add(l);
                }
            } catch (SQLException ex) {
                Logger.getLogger(LopDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }

        }
        return list;
    }
}
