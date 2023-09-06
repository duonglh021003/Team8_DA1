/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.duan1_n8.service;

import com.mycompany.duan1_n8.entity.NSX;
import java.util.List;

/**
 *
 * @author BXT
 */
public interface NSXService {

    List<NSX> getAllNSX();

    String addNSX(NSX nsx);

    String updateNSX(NSX nsx);

    String deleteNSX(NSX nsx);
}
