/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.duan1_n8.service;

import com.mycompany.duan1_n8.entity.ThietKe;
import java.util.List;

/**
 *
 * @author BXT
 */
public interface ThietKeService {

    List<ThietKe> getAllTK();

    String addThietKe(ThietKe thietKe);

    String updateThietKe(ThietKe thietKe);

    String deleteThietKe(ThietKe thietKe);
}
