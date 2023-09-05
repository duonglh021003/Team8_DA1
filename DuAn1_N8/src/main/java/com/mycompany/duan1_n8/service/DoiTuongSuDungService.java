/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.duan1_n8.service;

import com.mycompany.duan1_n8.entity.DoiTuongSuDung;
import java.util.List;

/**
 *
 * @author BXT
 */
public interface DoiTuongSuDungService {
    List<DoiTuongSuDung> getAllDTSD();

    String addDTSD(DoiTuongSuDung dtsd);

    String updateDTSD(DoiTuongSuDung dtsd);

    String deleteDTSD(DoiTuongSuDung dtsd); 
}
