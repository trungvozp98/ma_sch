/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.GiaoVien;
import java.util.ArrayList;

/**
 *
 * @author trung98
 */
public interface IGiaoVienDAO {

    public ArrayList<GiaoVien> getAll();

    public GiaoVien findByMaGV(String maGV);

    public ArrayList<GiaoVien> findByMaKhoa(String makhoa);

    public ArrayList<GiaoVien> findByTenKhoa(String tenkhoa);

    public boolean themGV(GiaoVien gv);

    public boolean suaGV(GiaoVien gv);

    public boolean checkMaGV(String magv);

    public ArrayList<GiaoVien> findSVByName(String name);

    public boolean updateThongtinLienlan(String sdt, String diachi, String magv);

}
