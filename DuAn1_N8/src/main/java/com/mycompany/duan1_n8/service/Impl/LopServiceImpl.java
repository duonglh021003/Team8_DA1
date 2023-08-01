/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.Lop;
import com.mycompany.duan1_n8.repository.LopRepository;
import com.mycompany.duan1_n8.service.LopService;
import java.util.List;

/**
 *
 * @author BXT
 */
public class LopServiceImpl implements LopService{

    private final LopRepository lopRepository = new LopRepository();

    
    @Override
    public List<Lop> getAll() {
        return lopRepository.getAll();
    }
    
}
