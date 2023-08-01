/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.DoiTuongSuDung;
import com.mycompany.duan1_n8.repository.DoiTuongSuDungRepository;
import com.mycompany.duan1_n8.service.DoiTuongSuDungService;
import java.util.List;

/**
 *
 * @author BXT
 */
public class DoiTuongSuDungServiceImpl implements DoiTuongSuDungService{

    private final DoiTuongSuDungRepository doiTuongSuDungRepository = new DoiTuongSuDungRepository();

    @Override
    public List<DoiTuongSuDung> getAll() {
        return doiTuongSuDungRepository.getAll();
    }
    
}
