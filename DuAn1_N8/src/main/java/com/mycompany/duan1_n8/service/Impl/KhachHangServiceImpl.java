/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.KhachHang;
import com.mycompany.duan1_n8.repository.KhachHangRepository;
import com.mycompany.duan1_n8.service.KhachHangService;
import java.util.List;
import org.modelmapper.ModelMapper;

/**
 *
 * @author minhb
 */
public class KhachHangServiceImpl implements KhachHangService {

    private final KhachHangRepository repository = new KhachHangRepository();

    public KhachHangServiceImpl() {

    }

    @Override
    public List<KhachHang> getAllKH() {
        return repository.getAllKH();
    }

    @Override
    public String add(KhachHang khachHang) {
        if (repository.add(khachHang)) {
            return "Thêm Thành Công";
        } else {
            return "Thêm Thất Bại";
        }
    }

    @Override
    public String Update(KhachHang khachHang) {
        if (repository.update(khachHang)) {
            return "Sửa Thành Công";
        } else {
            return "Sửa Thất Bại";
        }
    }
}
