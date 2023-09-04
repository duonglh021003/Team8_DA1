/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.duan1_n8.service;

import com.mycompany.duan1_n8.entity.SanPham;
import java.util.List;

/**
 *
 * @author BXT
 */
public interface SanPhamService {

    List<SanPham> getAllSP();

    String addSanPham(SanPham sanPham);

    String updateSanPham(SanPham sanPham);

    String deleteSanPham(SanPham sanPham);
}
