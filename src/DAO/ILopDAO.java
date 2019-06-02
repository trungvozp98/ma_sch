/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Lop;
import java.util.ArrayList;

/**
 *
 * @author trung98
 */
public interface ILopDAO {

    public ArrayList<Lop> getAll();

    public Lop findByMalop(String malop);

    public ArrayList<Lop> findByMaKhoa(String maKhoa);

    public ArrayList<Lop> findByTenKhoa(String tenKhoa);

    public boolean themLop(Lop l);

    public boolean suaLop(Lop l);

    public boolean checkTenlop(String tenlop);
     public ArrayList<Lop> findByTenlop(String tenlop);

}
