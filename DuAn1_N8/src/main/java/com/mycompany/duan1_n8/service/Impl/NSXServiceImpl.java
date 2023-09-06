/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.NSX;
import com.mycompany.duan1_n8.repository.NSXRepository;
import com.mycompany.duan1_n8.service.NSXService;
import java.util.List;
import org.modelmapper.ModelMapper;

/**
 *
 * @author BXT
 */
public class NSXServiceImpl implements NSXService{
    private final ModelMapper modelMapper = new ModelMapper();
    public NSXRepository repository = new NSXRepository();

    public NSXServiceImpl() {
    }

    @Override
    public List<NSX> getAllNSX() {
        return repository.getAllNSX();
    }

    @Override
    public String addNSX(NSX nsx) {
        boolean isAdd = repository.addNsx(nsx);
        return isAdd ? "Add Du Lieu Thanh Cong" : "Add Du Lieu That Bai";
    }

    @Override
    public String updateNSX(NSX nsx) {
        boolean isUpdate = repository.updateNsx(nsx);
        return isUpdate ? "Update Du Lieu Thanh Cong" : "Update Du Lieu That Bai";
    }

    @Override
    public String deleteNSX(NSX nsx) {
        boolean isDelete = repository.deleteNsx(nsx);
        return isDelete ? "Delete Du Lieu Thanh Cong" : "Delete Du Lieu That Bai";
    }

}
