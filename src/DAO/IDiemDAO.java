/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Diem;
import java.util.ArrayList;

/**
 *
 * @author trung98
 */
public interface IDiemDAO {

    public ArrayList<Diem> findByMaSV(String maSV, String hocky, String namhoc);

    public boolean updateDiem(Diem d);

    public float calDiemTB(String masv, String hocky, String namhoc);

}
