/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.duan1_n8.service;

import com.mycompany.duan1_n8.entity.CheckIn;
import com.mycompany.duan1_n8.entity.DanhSachLamViec;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author BXT
 */
public interface CheckInService {
    
    List<CheckIn> getAll();
    
    String add(CheckIn checkIn);
    
    List<CheckIn> locNgay(Date ngayTao);
}
