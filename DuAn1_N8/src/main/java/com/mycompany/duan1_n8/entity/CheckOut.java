/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author BXT
 */

@Table(name = "CheckOut")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CheckOut {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    
    @Column(name = "Ma")
    private String ma;
    
    @Column(name = "NgayTao")
    private Date ngayTao;
    
    @Column(name = "GioRa")
    private Integer gioRa;
    
    @Column(name = "PhutRa")
    private Integer phutRa;
    
    @Column(name = "BaoCao")
    private String baoCao;
    
    @Column(name = "GhiChu")
    private String ghiChu;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaQRNhanVien", referencedColumnName = "MaQR")
    private NhanVien nhanVien;
    
}
