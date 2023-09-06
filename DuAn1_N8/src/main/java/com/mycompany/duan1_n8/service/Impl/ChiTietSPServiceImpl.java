/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.ChiTietSP;
import com.mycompany.duan1_n8.repository.ChiTietSPRepository;
import com.mycompany.duan1_n8.service.ChiTietSPService;
import java.util.List;
import org.modelmapper.ModelMapper;

/**
 *
 * @author minhb
 */
public class ChiTietSPServiceImpl implements ChiTietSPService {

    private final ModelMapper modelMapper = new ModelMapper();
    public ChiTietSPRepository repository = new ChiTietSPRepository();

    public ChiTietSPServiceImpl() {
    }

    @Override
    public List<ChiTietSP> getAllCTSP() {
        return repository.getAllCTSP();
    }

    @Override
    public String addSanPham(ChiTietSP ctsp) {
        boolean isAdd = repository.addCTSP(ctsp);
        return isAdd ? "Add Thanh Cong" : "Add That Bai";
    }

    @Override
    public String updateSanPham(ChiTietSP chiTietSP) {
        boolean isUpdate = repository.updateCTSP(chiTietSP);
        return isUpdate ? "Update Thanh Cong" : "Update That Bai";
    }

    @Override
    public String deleteSanPham(ChiTietSP chiTietSP) {
        boolean isDelete = repository.deleteCTSP(chiTietSP);
        return isDelete ? "Thanh Cong" : "That Bai";
    }

}
