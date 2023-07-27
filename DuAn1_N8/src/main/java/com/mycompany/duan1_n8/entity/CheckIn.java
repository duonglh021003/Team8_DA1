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

@Table(name = "CheckIn")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    
    @Column(name = "Ma")
    private String ma;
    
    @Column(name = "NgayTao")
    private Date ngayTao;
    
    @Column(name = "GioVao")
    private Integer gioVao;
    
    @Column(name = "PhutVao")
    private Integer phutVao;
    
    @Column(name = "PhutCoDinh")
    private Integer phutCoDinh;
    
    @Column(name = "TrangThai")
    private Integer trangThai;
    
    @Column(name = "BaoCao")
    private String baoCao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaQRNhanVien", referencedColumnName = "MaQR")
    private NhanVien nhanVien;
    
    public Integer phutMuon(){
        return this.phutVao - this.phutCoDinh;
    }
    
    public enum status{
        LATE1,
        LATE2,
        LATE3,
        LATE4,
        LATE5,
        LATE6,
        LATE7,
        LATE8,
        LATE9,
        LATE10,
        LATE11,
        LATE12,
        LATE13,
        LATE14,
        LATE15,
        ABSENT,
        PRESENT
    }
    
    public status getStatus() {
        status statusEnum = null ;
        if (gioVao == 7 && phutMuon() >= 45 && phutMuon() <= 59 ) {
            statusEnum = status.PRESENT;
        }else if (gioVao == 12 && phutMuon() >= 45 && phutMuon() <= 59) {
            statusEnum = status.PRESENT;
        } else if (gioVao == 8 && phutMuon() == 0 ) {
            statusEnum = status.PRESENT;
        } else if (gioVao == 8 && phutMuon() == 1 ) {
            statusEnum = status.LATE1;
        } else if (gioVao == 8 && phutMuon() == 2 ) {
            statusEnum = status.LATE2;
        } else if (gioVao == 8 && phutMuon() == 3 ) {
            statusEnum = status.LATE3;
        } else if (gioVao == 8 && phutMuon() == 4 ) {
            statusEnum = status.LATE4;
        } else if (gioVao == 8 && phutMuon() == 5 ) {
            statusEnum = status.LATE5;
        }else if (gioVao == 8 && phutMuon() == 6 ) {
            statusEnum = status.LATE6;
        } else if (gioVao == 8 && phutMuon() == 7 ) {
            statusEnum = status.LATE7;
        } else if (gioVao == 8 && phutMuon() == 8 ) {
            statusEnum = status.LATE8;
        } else if (gioVao == 8 && phutMuon() == 9 ) {
            statusEnum = status.LATE9;
        } else if (gioVao == 8 && phutMuon() == 10 ) {
            statusEnum = status.LATE10;
        } else if (gioVao == 8 && phutMuon() == 11 ) {
            statusEnum = status.LATE11;
        } else if (gioVao == 8 && phutMuon() == 12 ) {
            statusEnum = status.LATE12;
        } else if (gioVao == 8 && phutMuon() == 13 ) {
            statusEnum = status.LATE13;
        } else if (gioVao == 8 && phutMuon() == 14 ) {
            statusEnum = status.LATE14;
        } else if (gioVao == 8 && phutMuon() == 15 ) {
            statusEnum = status.LATE15;
        } else if (gioVao == 13 && phutMuon() == 0 ) {
            statusEnum = status.PRESENT;
        } else if (gioVao == 13 && phutMuon() == 1 ) {
            statusEnum = status.LATE1;
        } else if (gioVao == 13 && phutMuon() == 2 ) {
            statusEnum = status.LATE2;
        } else if (gioVao == 13 && phutMuon() == 3 ) {
            statusEnum = status.LATE3;
        } else if (gioVao == 13 && phutMuon() == 4 ) {
            statusEnum = status.LATE4;
        } else if (gioVao == 13 && phutMuon() == 5 ) {
            statusEnum = status.LATE5;
        } else if (gioVao == 13 && phutMuon() == 6 ) {
            statusEnum = status.LATE6;
        } else if (gioVao == 13 && phutMuon() == 7 ) {
            statusEnum = status.LATE7;
        } else if (gioVao == 13 && phutMuon() == 8 ) {
            statusEnum = status.LATE8;
        } else if (gioVao == 13 && phutMuon() == 9 ) {
            statusEnum = status.LATE9;
        } else if (gioVao == 13 && phutMuon() == 10 ) {
            statusEnum = status.LATE10;
        } else if (gioVao == 13 && phutMuon() == 11 ) {
            statusEnum = status.LATE11;
        } else if (gioVao == 13 && phutMuon() == 12 ) {
            statusEnum = status.LATE12;
        } else if (gioVao == 13 && phutMuon() == 13 ) {
            statusEnum = status.LATE13;
        } else if (gioVao == 13 && phutMuon() == 14 ) {
            statusEnum = status.LATE14;
        } else if (gioVao == 13 && phutMuon() == 15 ) {
            statusEnum = status.LATE15;
        }else{
            statusEnum = status.ABSENT;
        }
        
        return statusEnum;
    }
    
    
}
