/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.CheckIn;
import com.mycompany.duan1_n8.repository.CheckInRepository;
import com.mycompany.duan1_n8.repository.DanhSachLamViecRepository;
import com.mycompany.duan1_n8.service.CheckInService;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author BXT
 */
public class CheckInServiceImpl implements CheckInService {

    private final CheckInRepository checkInRepository = new CheckInRepository();

    @Override
    public List<CheckIn> getAll() {
        return checkInRepository.getAll();
    }

    @Override
    public String add(CheckIn checkIn) {
        if (checkInRepository.add(checkIn)) {
            return "Thêm Thành Công";
        } else {
            return "Thêm Thất Bại";
        }
    }

    @Override
    public List<CheckIn> locNgay(Date ngayTao) {
        return checkInRepository.searchNgay(ngayTao);
    }

}
