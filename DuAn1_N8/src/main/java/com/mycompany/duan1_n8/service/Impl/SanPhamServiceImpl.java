/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.SanPham;
import com.mycompany.duan1_n8.repository.SanPhamRepository;
import com.mycompany.duan1_n8.service.SanPhamService;
import java.util.List;
import org.modelmapper.ModelMapper;

/**
 *
 * @author BXT
 */
public class SanPhamServiceImpl implements SanPhamService{
    private final ModelMapper modelMapper = new ModelMapper();
    public SanPhamRepository repository = new SanPhamRepository();

    public SanPhamServiceImpl() {
    }

    @Override
    public List<SanPham> getAllSP() {
        return repository.getAllSP();
    }

    @Override
    public String addSanPham(SanPham sanPham) {
        boolean isAdd = repository.addSanPham(sanPham);
        return isAdd ? "Add Du Lieu Thanh Cong" : "Add Du Lieu That Bai";
    }

    @Override
    public String updateSanPham(SanPham sanPham) {
        boolean isUpdate = repository.updateSanPham(sanPham);
        return isUpdate ? "Update Du Lieu Thanh Cong" : "Update Du Lieu That Bai";
    }

    @Override
    public String deleteSanPham(SanPham sanPham) {
        boolean isDelete = repository.deleteSanPham(sanPham);
        return isDelete ? "Delete Du Lieu Thanh Cong" : "Delete Du Lieu That Bai";
    }
}
