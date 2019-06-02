/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.SinhVien;
import java.util.ArrayList;

/**
 *
 * @author trung98
 */
public interface ISinhVienDAO {

    public ArrayList<SinhVien> getAll();

    public SinhVien findByMaSV(String maSV);

    public ArrayList<SinhVien> findByMaLop(String malop);

    public ArrayList<SinhVien> findByTenKhoa(String maKhoa);

    public boolean themSV(SinhVien sv);

    public boolean suaSV(SinhVien sv);

    public ArrayList<SinhVien> findByTenLop(String tenlop);

    public boolean checkMaSV(String masv);

    public ArrayList<SinhVien> findSVByName(String name);

}
