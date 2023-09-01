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

@Table(name = "NhanVien")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class NhanVien {
    
    @Id
    @Column(name = "MaQR")
    private Integer maQr;
    
    @Column(name = "Ma")
    private String ma;
    
    @Column(name = "Ten")
    private String ten;
    
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
    
    @Column(name = "MatKhau")
    private String matKhau;
    
    @Column(name = "TrangThai")
    private Integer trangThai;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdChucVu", referencedColumnName = "Id")
    private ChucVu chucVu;
  
}
