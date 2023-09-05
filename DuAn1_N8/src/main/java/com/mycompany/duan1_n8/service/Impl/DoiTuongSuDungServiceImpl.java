/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.DoiTuongSuDung;
import com.mycompany.duan1_n8.repository.DoiTuongSuDungRepository;
import com.mycompany.duan1_n8.service.DoiTuongSuDungService;
import java.util.List;
import org.modelmapper.ModelMapper;

/**
 *
 * @author BXT
 */
public class DoiTuongSuDungServiceImpl implements DoiTuongSuDungService{
    private final ModelMapper modelMapper = new ModelMapper();
    public DoiTuongSuDungRepository repository = new DoiTuongSuDungRepository();

    public DoiTuongSuDungServiceImpl() {
    }

    @Override
    public List<DoiTuongSuDung> getAllDTSD() {
        return repository.getAllDTSD();
    }

    @Override
    public String addDTSD(DoiTuongSuDung dtsd) {
        boolean isAdd = repository.addDTSD(dtsd);
        return isAdd ? "Add Du Lieu Thanh Cong" : "Add Du Lieu That Bai";
    }

    @Override
    public String updateDTSD(DoiTuongSuDung dtsd) {
        boolean isUpdate = repository.updateDTSD(dtsd);
        return isUpdate ? "Update Du Lieu Thanh Cong" : "Update Du Lieu That Bai";
    }

    @Override
    public String deleteDTSD(DoiTuongSuDung dtsd) {
        boolean isDelete = repository.deleteDTSD(dtsd);
        return isDelete ? "Delete Du Lieu Thanh Cong" : "Delete Du Lieu That Bai";
    }

}
