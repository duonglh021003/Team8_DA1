/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.duan1_n8.service;

import com.mycompany.duan1_n8.entity.MauSac;
import java.util.List;

/**
 *
 * @author BXT
 */
public interface MauSacService {

    List<MauSac> getAllMS();

    String addMauSac(MauSac mauSac);

    String updateMauSac(MauSac mauSac);

    String deleteMauSac(MauSac mauSac);
}
