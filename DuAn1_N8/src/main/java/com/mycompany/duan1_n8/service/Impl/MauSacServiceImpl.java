/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.MauSac;
import com.mycompany.duan1_n8.repository.MauSacRepository;
import com.mycompany.duan1_n8.service.MauSacService;
import java.util.List;

/**
 *
 * @author BXT
 */
public class MauSacServiceImpl implements MauSacService{
    
    private final MauSacRepository mauSacRepository = new MauSacRepository();


    @Override
    public List<MauSac> getAll() {
        return mauSacRepository.getAll();
    }
    
}
