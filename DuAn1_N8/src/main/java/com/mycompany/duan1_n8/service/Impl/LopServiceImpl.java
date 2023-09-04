/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.Lop;
import com.mycompany.duan1_n8.repository.LopRepository;
import com.mycompany.duan1_n8.service.LopService;
import java.util.List;
import org.modelmapper.ModelMapper;

/**
 *
 * @author BXT
 */
public class LopServiceImpl implements LopService{
private final ModelMapper modelMapper = new ModelMapper();
    public LopRepository repository = new LopRepository();

    public LopServiceImpl() {
    }

    @Override
    public List<Lop> getAllLop() {
        return repository.getAllLop();
    }

    @Override
    public String addLop(Lop lop) {
        boolean isAdd = repository.addLop(lop);
        return isAdd ? "Add Du Lieu Thanh Cong" : "Add Du Lieu That Bai";
    }

    @Override
    public String updateLop(Lop lop) {
        boolean isUpdate = repository.updateLop(lop);
        return isUpdate ? "Update Du Lieu Thanh Cong" : "Update Du Lieu That Bai";
    }

    @Override
    public String deleteLop(Lop lop) {
        boolean isDelete = repository.deleteLop(lop);
        return isDelete ? "Delete Du Lieu Thanh Cong" : "Delete Du Lieu That Bai";
    }
    
}
