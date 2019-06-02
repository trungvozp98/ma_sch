/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.LichThi;
import java.util.ArrayList;

/**
 *
 * @author trung98
 */
public interface ILichThiDAO {

    public ArrayList<LichThi> findLichthiByMaSV(String maSV, String hocKy, String namHoc);

    public ArrayList<LichThi> findLichthiByMaLHP(String maLHP, String hocky, String namhoc);

    public boolean suaLichthi(LichThi lt);

    public ArrayList<LichThi> findByNamhoc(String hocky, String namhoc);

    public ArrayList<LichThi> findByTenMH(String mh, String hocky, String namhoc);

    public boolean updateByMon(String thu, String ca, String ngaythi, String mamh);

}
