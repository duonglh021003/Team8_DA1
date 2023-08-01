/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.SanPham;
import com.mycompany.duan1_n8.repository.SanPhamRepository;
import com.mycompany.duan1_n8.service.SanPhamService;
import java.util.List;

/**
 *
 * @author BXT
 */
public class SanPhamServiceImpl implements SanPhamService{
    
    private final SanPhamRepository sanPhamRepository = new SanPhamRepository();


    @Override
    public List<SanPham> getAll() {
        return sanPhamRepository.getAll();
    }
    
}
