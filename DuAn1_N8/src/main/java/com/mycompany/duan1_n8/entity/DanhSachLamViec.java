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
import lombok.ToString;

/**
 *
 * @author BXT
 */
@Table(name = "DanhSachLamViec")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class DanhSachLamViec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;

    @Column(name = "Ma")
    private String ma;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgaySua")
    private Date ngaySua;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdT2", referencedColumnName = "Id")
    private Thu2 thu2;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdT3", referencedColumnName = "Id")
    private Thu3 thu3;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdT4", referencedColumnName = "Id")
    private Thu4 thu4;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdT5", referencedColumnName = "Id")
    private Thu5 thu5;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdT6", referencedColumnName = "Id")
    private Thu6 thu6;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdT7", referencedColumnName = "Id")
    private Thu7 thu7;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdCN", referencedColumnName = "Id")
    private ChuNhat chuNhat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaQRNhanVien", referencedColumnName = "MaQR")
    private NhanVien nhanVien;

    
}
