/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.ThietKe;
import com.mycompany.duan1_n8.repository.ThietKeRepository;
import com.mycompany.duan1_n8.service.ThietKeService;
import java.util.List;
import org.modelmapper.ModelMapper;

/**
 *
 * @author BXT
 */
public class ThietKeServiceImpl implements ThietKeService{
    private final ModelMapper modelMapper = new ModelMapper();
    public ThietKeRepository repository = new ThietKeRepository();

    public ThietKeServiceImpl() {
    }

    @Override
    public List<ThietKe> getAllTK() {
        return repository.getAllTK();
    }

    @Override
    public String addThietKe(ThietKe thietKe) {
        boolean isAdd = repository.addThietKe(thietKe);
        return isAdd ? "Add Du Lieu Thanh Cong" : "Add Du Lieu That Bai";
    }

    @Override
    public String updateThietKe(ThietKe thietKe) {
        boolean isUpdate = repository.updateThietKe(thietKe);
        return isUpdate ? "Update Du Lieu Thanh Cong" : "Update Du Lieu That Bai";
    }

    @Override
    public String deleteThietKe(ThietKe thietKe) {
        boolean isDelete = repository.deleteThietKe(thietKe);
        return isDelete ? "Delete Du Lieu Thanh Cong" : "Delete Du Lieu That Bai";
    }

}
