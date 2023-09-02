/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.duan1_n8.service;

import com.mycompany.duan1_n8.entity.PhieuGiamGia;
import java.util.Date;
import java.util.List;

/**
 *
 * @author minhb
 */
public interface PhieuGiamGiaService {

    List<PhieuGiamGia> getAllPGG();

    String add(PhieuGiamGia phieuGiamGia);

    String update(PhieuGiamGia phieuGiamGia);

    String checkDaKetThuc(PhieuGiamGia phieuGiamGia);

    List<PhieuGiamGia> locTrangThai(Integer trangThai);

    List<PhieuGiamGia> locNgay(Date ngayBatDau, Date ngayKetThuc);

    List<PhieuGiamGia> locTen(String tenPhieu);

    String checkSapDienRa(PhieuGiamGia phieuGiamGia);

    String checkDangBatDau(PhieuGiamGia phieuGiamGia);
}
