/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.CheckOut;
import com.mycompany.duan1_n8.repository.CheckOutRepository;

import com.mycompany.duan1_n8.service.CheckOutService;
import java.util.List;

/**
 *
 * @author BXT
 */
public class CheckOutServiceImpl implements CheckOutService{

    private final CheckOutRepository checkOutRepository = new CheckOutRepository();
    
    @Override
    public List<CheckOut> getAll() {
        return checkOutRepository.getAll();
    }

    @Override
    public String add(CheckOut checkOut) {
        if (checkOutRepository.add(checkOut)) {
            return "Thêm Thành Công";
        } else {
            return "Thêm Thất Bại";
        }
    }
    
}
