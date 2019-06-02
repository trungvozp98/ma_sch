/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entities.Khoa;
import java.util.ArrayList;

/**
 *
 * @author trung98
 */
public interface IKhoaDAO {

    public ArrayList<Khoa> getAll();

    public Khoa findByMaKhoa(String makhoa);

    public Khoa findByTenKhoa(String tenKhoa);

    public boolean themKhoa(Khoa k);

    public boolean suaKhoa(Khoa k);
     public boolean checkMakhoa(String makhoa);
}
