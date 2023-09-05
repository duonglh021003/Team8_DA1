/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.DanhSachLamViec;
import com.mycompany.duan1_n8.repository.DanhSachLamViecRepository;
import com.mycompany.duan1_n8.service.DanhSachLamViecService;
import java.util.List;

/**
 *
 * @author BXT
 */
public class DanhSachLamViecServiceImpl implements DanhSachLamViecService{
    
    private final DanhSachLamViecRepository danhSachLamViecRepository = new DanhSachLamViecRepository();

    @Override
    public List<DanhSachLamViec> getAll() {
        return danhSachLamViecRepository.getAll();
    }

    @Override
    public String add(DanhSachLamViec danhSachLamViec) {
        if (danhSachLamViecRepository.add(danhSachLamViec)) {
            return "Thêm Thành Công";
        } else {
            return "Thêm Thất Bại";
        }
    }
    
}
