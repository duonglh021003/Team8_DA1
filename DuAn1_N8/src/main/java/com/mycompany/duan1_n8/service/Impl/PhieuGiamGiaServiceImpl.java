/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.PhieuGiamGia;
import com.mycompany.duan1_n8.repository.PhieuGiamGiaRepository;
import com.mycompany.duan1_n8.service.PhieuGiamGiaService;
import java.util.Date;
import java.util.List;
import org.modelmapper.ModelMapper;

/**
 *
 * @author minhb
 */
public class PhieuGiamGiaServiceImpl implements PhieuGiamGiaService {

    private ModelMapper modelMapper = new ModelMapper();
    public PhieuGiamGiaRepository repository = new PhieuGiamGiaRepository();

    public PhieuGiamGiaServiceImpl() {

    }

    @Override
    public List<PhieuGiamGia> getAllPGG() {
        return repository.getAllPGG();
    }

    @Override
    public String add(PhieuGiamGia phieuGiamGia) {
        boolean isAdd = repository.addPhieuGiamGia(phieuGiamGia);
        return isAdd ? "Add Thanh Cong" : "Add That Bai";
    }

    @Override
    public String update(PhieuGiamGia phieuGiamGia) {
        boolean isUpdate = repository.updatePhieuGiamGia(phieuGiamGia);
        return isUpdate ? "Update Thanh Cong" : "Update That Bai";
    }

    @Override
    public String checkDaKetThuc(PhieuGiamGia phieuGiamGia) {
        boolean isDelete = repository.daKetThuc(phieuGiamGia);
        return isDelete ? "Cap Nhat Thanh Cong" : "Cap Nhat That Bai";
    }

    @Override
    public List<PhieuGiamGia> locTrangThai(Integer trangThai) {
        return repository.searchTrangThai(trangThai);
    }

    @Override
    public List<PhieuGiamGia> locNgay(Date ngayBatDau, Date ngayKetThuc) {
        return repository.searchNgay(ngayBatDau, ngayKetThuc);
    }

    @Override
    public List<PhieuGiamGia> locTen(String tenPhieu) {
        return repository.searchTen(tenPhieu);
    }

    @Override
    public String checkSapDienRa(PhieuGiamGia phieuGiamGia) {
        boolean isCheckUpdate = repository.sapBatDau(phieuGiamGia);
        return isCheckUpdate ? "Cap Nhat Thanh Cong" : "Cap Nhat That Bai";
    }

    @Override
    public String checkDangBatDau(PhieuGiamGia phieuGiamGia) {
        boolean isChecConSuDung = repository.dangBatDau(phieuGiamGia);
        return isChecConSuDung ? "Cap Nhat Thanh Cong" : "Cap Nhat That Bai";
    }
}
