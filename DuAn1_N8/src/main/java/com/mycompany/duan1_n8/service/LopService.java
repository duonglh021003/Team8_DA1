/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.duan1_n8.service;

import com.mycompany.duan1_n8.entity.Lop;
import java.util.List;

/**
 *
 * @author BXT
 */
public interface LopService {
    List<Lop> getAllLop();

    String addLop(Lop lop);

    String updateLop(Lop lop);

    String deleteLop(Lop lop); 
}
