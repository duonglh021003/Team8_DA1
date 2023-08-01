/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author BuiDucMinh
 */

@Table(name = "ChiTietSP")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ChiTietSP {
    
    @Id
    @Column(name = "MaQR")
    private Integer maQr;
    
    @Column(name = "NgaySanXuat")
    private Date ngaySanXuat;
    
    @Column(name = "HanSuDung")
    private Date hanSuDung;
    
    @Column(name = "Gia")
    private Double gia;
    
    @Column(name = "MoTa")
    private String moTa;
    
    @Column(name = "SoLuong")
    private Integer soLuong;
    
    @Column(name = "TrangThai")
    private Integer trangThai;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTK", referencedColumnName = "Id")
    private ThietKe thietKe;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdDTSD", referencedColumnName = "Id")
    private DoiTuongSuDung doiTuongSuDung;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdNSX", referencedColumnName = "Id")
    private NSX nsx;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdMS", referencedColumnName = "Id")
    private MauSac mauSac;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdSP", referencedColumnName = "Id")
    private SanPham sanPham;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdLop", referencedColumnName = "Id")
    private Lop lop;
    
}
