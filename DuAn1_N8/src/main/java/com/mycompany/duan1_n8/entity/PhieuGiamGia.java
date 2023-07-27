/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "PhieuGiamGia")
public class PhieuGiamGia {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPhieuGiamGia;

    @Column(name = "Ma")
    private String maPhieuGiamGia;

    @Column(name = "Ten")
    private String tenPhieuGiamGia;

    @Column(name = "NgayBD")
    private Date ngayBatDau;

    @Column(name = "NgayKT")
    private Date ngayKetThuc;

    @Column(name = "HinhThucGiamGia")
    private String hinhThucGiamGia;

    @Column(name = "GiaTriGiam")
    private Float giaTriGiam;

    @Column(name = "TrangThai")
    private Integer trangThai;

    public String layTrangThai() {
        if (this.trangThai == 1) {
            return "Dang Bat Dau";
        } else if (this.trangThai == 2) {
            return "Sap Dien Ra";
        } else {
            return "Da Ket Thuc";
        }
    }
}
