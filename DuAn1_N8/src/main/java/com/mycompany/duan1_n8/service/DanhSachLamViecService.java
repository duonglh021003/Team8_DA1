/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.duan1_n8.service;

import com.mycompany.duan1_n8.View.FormDanhSachLamViec;
import com.mycompany.duan1_n8.entity.DanhSachLamViec;
import com.mycompany.duan1_n8.entity.NhanVien;
import java.util.List;

/**
 *
 * @author BXT
 */
public interface DanhSachLamViecService {
    
    List<DanhSachLamViec> getAll();
    
    String add(DanhSachLamViec danhSachLamViec);
    
}
