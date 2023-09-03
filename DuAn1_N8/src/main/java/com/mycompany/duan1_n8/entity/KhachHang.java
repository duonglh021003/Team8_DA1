/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.entity;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author BuiDucMinh
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "KhachHang")
public class KhachHang {

    @Id
    @Column(name = "MaQR")
    private Integer maQR;

    @Column(name = "Ma")
    private String maKH;

    @Column(name = "Ten")
    private String tenKH;

    @Column(name = "GioiTinh")
    private Boolean gioiTinh;

    @Column(name = "Email")
    private String email;

    @Column(name = "QueQuan")
    private String queQuan;

    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @Column(name = "Sdt")
    private String sdt;

    @Column(name = "TrangThai")
    private Integer trangThai;
}
