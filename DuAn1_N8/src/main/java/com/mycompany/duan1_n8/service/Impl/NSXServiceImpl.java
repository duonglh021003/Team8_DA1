/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.NSX;
import com.mycompany.duan1_n8.repository.NSXRepository;
import com.mycompany.duan1_n8.service.NSXService;
import java.util.List;

/**
 *
 * @author BXT
 */
public class NSXServiceImpl implements NSXService{
    
    private final NSXRepository nSXRepository = new NSXRepository();


    @Override
    public List<NSX> getAll() {
        return nSXRepository.getAll();
    }
    
}
