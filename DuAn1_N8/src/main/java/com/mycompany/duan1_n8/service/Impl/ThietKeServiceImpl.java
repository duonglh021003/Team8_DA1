/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.ThietKe;
import com.mycompany.duan1_n8.repository.NhanVienRepository;
import com.mycompany.duan1_n8.repository.ThietKeRepository;
import com.mycompany.duan1_n8.service.ThietKeService;
import java.util.List;

/**
 *
 * @author BXT
 */
public class ThietKeServiceImpl implements ThietKeService{

    private final ThietKeRepository thietKeRepository = new ThietKeRepository();

    @Override
    public List<ThietKe> getAll() {
        return thietKeRepository.getAll();
    }
    
}
