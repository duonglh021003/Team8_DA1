/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.NhanVien;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author BXT
 */
public class LoginRepository {

    public List<String> list = new ArrayList<>();

    private NhanVienRepository nhanVienRepository = new NhanVienRepository();

    public List<String> login(String taiKhoan, String matKhau) {
       
        for (NhanVien nhanVien : nhanVienRepository.getAll()) {
            if (nhanVien.getSdt().equalsIgnoreCase(taiKhoan) && nhanVien.getMatKhau().equalsIgnoreCase(matKhau)) {
                list.add(String.valueOf(nhanVien));
            }
        }
        return list;
    }

}
