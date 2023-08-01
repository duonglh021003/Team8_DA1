/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.CheckIn;
import com.mycompany.duan1_n8.entity.ChiTietSP;
import com.mycompany.duan1_n8.repository.CheckInRepository;
import com.mycompany.duan1_n8.repository.ChiTietSPRepository;
import com.mycompany.duan1_n8.service.ChiTietSPService;
import java.util.List;

/**
 *
 * @author BXT
 */
public class ChiTietSPSerivceImpl implements ChiTietSPService{
    
    private final ChiTietSPRepository chiTietSPRepository = new ChiTietSPRepository();

    @Override
    public List<ChiTietSP> getAll() {
        return chiTietSPRepository.getAll();
    }

    
}
