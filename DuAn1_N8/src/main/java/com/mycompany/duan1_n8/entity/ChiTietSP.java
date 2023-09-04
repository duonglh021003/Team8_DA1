/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
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
import lombok.ToString;

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
public class ChiTietSP implements Serializable{

    @Id
    @Column(name = "MaQR")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maQr;

    @Column(name = "NgaySanXuat")
    private Date ngaySanXuat;

    @Column(name = "HanSuDung")
    private Date hanSuDung;

    @Column(name = "Gia")
    private BigDecimal gia;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @ManyToOne()
    @JoinColumn(name = "IdTK", referencedColumnName = "Id")
    private ThietKe thietKe;

    @ManyToOne()
    @JoinColumn(name = "IdDTSD", referencedColumnName = "Id")
    private DoiTuongSuDung doiTuongSuDung;

    @ManyToOne()
    @JoinColumn(name = "IdNSX", referencedColumnName = "Id")
    private NSX nsx;

    @ManyToOne()
    @JoinColumn(name = "IdMS", referencedColumnName = "Id")
    private MauSac mauSac;

    @ManyToOne()
    @JoinColumn(name = "IdSP", referencedColumnName = "Id")
    private SanPham sanPham;

    @ManyToOne()
    @JoinColumn(name = "IdLop", referencedColumnName = "Id")
    private Lop lop;

    public ChiTietSP(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String layThongTin() {
        if (this.moTa.equals("")) {
            return "Khong Co Thong Tin";
        } else {
            return moTa;
        }

    }

    public String layTrangThai() {
        if (this.trangThai == 1) {
            return "Con Hang";
        } else {
            return "Het Hang";
        }
    }
}
