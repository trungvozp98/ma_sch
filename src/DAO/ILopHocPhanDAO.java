/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.LopHocPhan;
import java.util.ArrayList;

public interface ILopHocPhanDAO {

    public ArrayList<LopHocPhan> getAll(String hocKy, String namHoc);

    public LopHocPhan findByMaLHP(String hocKy, String namHoc, String maLHP);

    public ArrayList<LopHocPhan> findByMaLHP(String malhp);

    public ArrayList<LopHocPhan> findByTenMH(String tenmh, String hocky, String namhoc);

    public ArrayList<LopHocPhan> findByMaMH(String mamh, String hocky, String namhoc);

    public boolean themLHP(LopHocPhan lhp);

    public String findNgktByMaLHP(String malhp, String hocky, String namhoc);

    public boolean themLHPnoGV(LopHocPhan lhp);

    public boolean suaLHP(LopHocPhan lhp);

    public boolean suaLHPnoGV(LopHocPhan lhp);

    public boolean updatemagv(String malhp, String hocky, String namhoc);

    public boolean checkMaLHP(String malhp, String hocky, String namhoc);
}
