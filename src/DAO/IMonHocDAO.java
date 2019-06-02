/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.MonHoc;
import java.util.ArrayList;

/**
 *
 * @author trung98
 */
public interface IMonHocDAO {
    public ArrayList<MonHoc> getAll();
    public MonHoc findByMaMH(String maMH);
     public ArrayList<MonHoc> findByTenMH(String tenmh);
      public ArrayList<MonHoc> findMaMH_TRUOC(String maMH);
      public boolean checkMaMH(String mamh);
      public boolean themMonhoc(MonHoc mh);
      public boolean suaMonhoc(MonHoc mh);
      public ArrayList<MonHoc> findByMaGV_DK(String magv, String hocky, String namhoc);
    
}
