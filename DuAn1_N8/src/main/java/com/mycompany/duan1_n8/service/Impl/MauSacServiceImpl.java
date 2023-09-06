/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.MauSac;
import com.mycompany.duan1_n8.repository.MauSacRepository;
import com.mycompany.duan1_n8.service.MauSacService;
import java.util.List;
import org.modelmapper.ModelMapper;

/**
 *
 * @author BXT
 */
public class MauSacServiceImpl implements MauSacService {

    private final ModelMapper modelMapper = new ModelMapper();
    public MauSacRepository repository = new MauSacRepository();

    public MauSacServiceImpl() {
    }

    @Override
    public List<MauSac> getAllMS() {
        return repository.getAllMS();
    }

    @Override
    public String addMauSac(MauSac mauSac) {
        boolean isAdd = repository.addMauSac(mauSac);
        return isAdd ? "Add Du Lieu Thanh Cong" : "Add Du Lieu That Bai";
    }

    @Override
    public String updateMauSac(MauSac mauSac) {
        boolean isUpdate = repository.updateMauSac(mauSac);
        return isUpdate ? "Update Du Lieu Thanh Cong" : "Update Du Lieu That Bai";
    }

    @Override
    public String deleteMauSac(MauSac mauSac) {
        boolean isDelete = repository.deleteMauSac(mauSac);
        return isDelete ? "Delete Du Lieu Thanh Cong" : "Delete Du Lieu That Bai";
    }
}
