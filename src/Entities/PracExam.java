/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import DB_Connect.DBConnect;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author trung98
 */
public class PracExam {

    JComboBox cb;
    JButton btn;
    JTextField txtsoxe;
    JTextField txthangxe;
    JTextField txtnamsx;

    PreparedStatement ps;
    ResultSet rs;
    String hoten[];
    
    
    

    public PracExam() {
        try {
            this.ps = DBConnect.con.prepareStatement("SELECT HOTEN FROM KHACHHANG");
            rs = ps.executeQuery();
            rs.last();
            int row=rs.getRow();
            hoten = new String[row];
            int i = 0;
            while (rs.next()) {

                hoten[i] = rs.getString(1);
                i++;
            }
            cb = new JComboBox(hoten);

            //btn_Them.addAction( new Acctionlisner){}
            // public ......
            String hoten = (String) cb.getSelectedItem();
            ps = DBConnect.con.prepareStatement("INSERT INTO  XE VALUES(?,?,?,?)");
            ps.setString(1, txtsoxe.getText());
            ps.setString(2, txthangxe.getText());
            ps.setString(3, txtnamsx.getText());
            ps.setString(4, hoten);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PracExam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //public addBaoduongxe(){    
    }
    
  

