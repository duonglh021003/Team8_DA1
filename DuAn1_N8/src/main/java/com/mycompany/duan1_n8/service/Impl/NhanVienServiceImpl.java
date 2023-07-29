/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.repository.NhanVienRepository;
import com.mycompany.duan1_n8.service.NhanVienService;
import java.util.List;

/**
 *
 * @author BXT
 */
public class NhanVienServiceImpl implements NhanVienService {

    private final NhanVienRepository nhanVienRepository = new NhanVienRepository();

    @Override
    public List<NhanVien> getAll() {
        return nhanVienRepository.getAll();
    }

    @Override
    public String add(NhanVien nhanVien) {
        if (nhanVienRepository.add(nhanVien)) {
            return "Thêm Thành Công";
        } else {
            return "Thêm Thất Bại";
        }
    }

    @Override
    public String delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String Update(NhanVien nhanVien) {
        if (nhanVienRepository.update(nhanVien)) {
            return "Sửa Thành Công";
        } else {
            return "Sửa Thất Bại";
        }

    }

}
