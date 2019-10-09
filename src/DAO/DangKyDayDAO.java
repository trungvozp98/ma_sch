/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB_Connect.DBConnect;
import Entities.DangKyDay;
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
public class DangKyDayDAO {

    public boolean huyByMaGV(String magv) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM DANGKYDAY WHERE MAGV=?");
                ps.setString(1, magv);
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

    public int summh(String magv, String hocky, String namhoc) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int sum = 0;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT COUNT(*) FROM DANGKYDAY WHERE MAGV =? AND HOCKY=? AND NAMHOC=?");
                ps.setString(1, magv);
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

    public ArrayList<DangKyDay> findByMaGV(String magv, String hocky, String namhoc) {
        ArrayList<DangKyDay> list = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT * FROM DANGKYDAY WHERE MAGV=? AND HOCKY=? AND NAMHOC=?");
                ps.setString(1, magv);
                ps.setString(2, hocky);
                ps.setString(3, namhoc);
                rs = ps.executeQuery();
                list = new ArrayList<DangKyDay>();
                while (rs.next()) {
                    DangKyDay dkd = new DangKyDay();
                    dkd.setMagv(rs.getString(1));
                    dkd.setMalhp(rs.getString(2));
                    dkd.setHocky(rs.getString(3));
                    dkd.setNamhoc(rs.getString(4));
                    dkd.setNgdk(rs.getString(5));
                    list.add(dkd);
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return list;

    }

    public boolean checkMaLHP(DangKyDay dkd) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<DangKyDay> list = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("SELECT COUNT(*) FROM DANGKYDAY WHERE MAGV=? AND MALHP=? AND HOCKY=? AND NAMHOC=? ");
                ps.setString(1, dkd.getMagv());
                ps.setString(2, dkd.getMalhp());
                ps.setString(3, dkd.getHocky());
                ps.setString(4, dkd.getNamhoc());
                rs = ps.executeQuery();
                rs.next();
                if (rs.getInt(1) != 0) {
                    return false;
                } else {
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DBConnect.close();
            }
        }
        return false;

    }

    public DangKyDay dangkyday(DangKyDay dkd) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("INSERT INTO DANGKYDAY VALUES(?,?,?,?,?)");

                ps.setString(1, dkd.getMagv());
                ps.setString(2, dkd.getMalhp());
                ps.setString(3, dkd.getHocky());
                ps.setString(4, dkd.getNamhoc());
                ps.setString(5, dkd.getNgdk());

                int row = ps.executeUpdate();
                if (row < 1) {
                    dkd = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
                dkd = null;
            } finally {
                DBConnect.close();
            }
        }
        return dkd;
    }

    public DangKyDay huydangky(DangKyDay dkd) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM DANGKYDAY WHERE MAGV =? AND MALHP=? AND HOCKY=? AND NAMHOC=? ");
                ps.setString(1, dkd.getMagv());
                ps.setString(2, dkd.getMalhp());
                ps.setString(3, dkd.getHocky());
                ps.setString(4, dkd.getNamhoc());
                int row = ps.executeUpdate();
                if (row < 1) {
                    dkd = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
                dkd = null;
            } finally {
                DBConnect.close();
            }
        }
        return dkd;
    }
 public DangKyDay themmoi(DangKyDay dkd) {
        PreparedStatement ps = null;
        if (DBConnect.open()) {
            try {
                ps = DBConnect.con.prepareStatement("DELETE FROM DANGKYDAY WHERE MAGV =? AND MALHP=? AND HOCKY=? AND NAMHOC=? ");
                ps.setString(1, dkd.getMagv());
                ps.setString(2, dkd.getMalhp());
                ps.setString(3, dkd.getHocky());
                ps.setString(4, dkd.getNamhoc());
                int row = ps.executeUpdate();
                if (row < 1) {
                    dkd = null;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DangKyHocDAO.class.getName()).log(Level.SEVERE, null, ex);
                dkd = null;
            } finally {
                DBConnect.close();
            }
        }
        return dkd;
    }


}
