/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.duan1_n8.service;

import com.mycompany.duan1_n8.entity.ChiTietSP;
import com.mycompany.duan1_n8.entity.SanPham;
import java.util.List;

/**
 *
 * @author minhb
 */
public interface ChiTietSPService {

    List<ChiTietSP> getAllCTSP();

    String addSanPham(ChiTietSP ctsp);
    
    String updateSanPham(ChiTietSP chiTietSP);
    
    String deleteSanPham(ChiTietSP chiTietSP);
}
