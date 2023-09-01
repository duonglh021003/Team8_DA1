/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author BuiDucMinh
 */
public class HoaDon {
     @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHoaDon;

    @Column(name = "Ma")
    private String maHoaDon;

    @Column(name = "NgayTao")
    private Date ngayTao;
    
    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "TongTienHoaDon")
    private BigDecimal tongTienHoaDon;
    
    @Column(name = "ThanhTien")
    private BigDecimal thanhTien;
    
    @Column(name = "TienKhachDua")
    private BigDecimal tienKhachDua;
    
    @Column(name = "TienThuaTraKhach")
    private BigDecimal tienThua;
    
    @Column(name = "TrangThai")
    private Integer trangThai;

    @ManyToOne()
    @JoinColumn(name = "MaQRKhachHang", referencedColumnName = "MaQR")
    private KhachHang khachHang;

    @ManyToOne()
    @JoinColumn(name = "MaQRNhanVien", referencedColumnName = "MaQR")
    private NhanVien nhanVien;

    @ManyToOne()
    @JoinColumn(name = "IdPhieuGiamGia", referencedColumnName = "Id")
    private PhieuGiamGia phieuGiamGia;

    public String layTrangThaiHD() {
        if (this.trangThai == 0) {
            return "Dang Cho Thanh Toan";
        } else if (this.trangThai == 1) {
            return "Da Thanh Toan";
        } else {
            return "Da Huy";
        }
    }
}
