/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Manager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trung98
 */
public class ManagerDAO {

    public Manager findByMaDN(String madn) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Manager ma = null;
        if (DB_Connect.DBConnect.open()) {
            try {
                ps = DB_Connect.DBConnect.con.prepareStatement("SELECT * FROM MANAGER WHERE users=?");
                ps.setString(1, madn);
                rs = ps.executeQuery();
                ma = new Manager();
                rs.next();
                ma.setMadn(rs.getString(1));
                ma.setHoten(rs.getString(3));
                ma.setNgsinh(rs.getString(4));
                ma.setDiachi(rs.getString(5));
                ma.setMatkhau(rs.getString(2));
            } catch (SQLException ex) {
                Logger.getLogger(ManagerDAO.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                DB_Connect.DBConnect.close();
            }

        }
        return ma;
    }

}
