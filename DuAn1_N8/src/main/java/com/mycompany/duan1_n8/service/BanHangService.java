/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.duan1_n8.service;

import com.mycompany.duan1_n8.entity.ChiTietSP;
import com.mycompany.duan1_n8.entity.HoaDon;
import com.mycompany.duan1_n8.entity.HoaDonChiTiet;
import com.mycompany.duan1_n8.entity.IdHoaDonChiTiet;
import java.util.List;

/**
 *
 * @author minhb
 */
public interface BanHangService {

    List<HoaDon> getAllHD();

    boolean addHoaDon(HoaDon hoaDon, String maNhanVien, String maKhachHang);

    boolean updateHoaDon(HoaDon hoaDon, String maNhanVien, String maKhachHang);

    List<ChiTietSP> getAllSanPhamBan();

    ChiTietSP getOneSP(Long idSanPham);

    List<HoaDonChiTiet> getListHDCTById(Long idHoaDon);

    String checkSP(Long idHoaDon, Long idSanPham);

    boolean AddCTHD(Long idHoaDon, HoaDonChiTiet hoaDonChiTiet);

    boolean addGioHang(HoaDonChiTiet hdct);

    boolean updateSoLuong(ChiTietSP chiTietSP);

    String deleteOrder(IdHoaDonChiTiet idHoaDonChiTiet);

    String thanhToan(HoaDon hoaDon);
    
    boolean huyHoaDon(HoaDon hoaDon);
}
