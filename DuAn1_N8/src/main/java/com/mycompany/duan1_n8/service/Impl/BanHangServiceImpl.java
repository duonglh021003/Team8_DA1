/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.service.Impl;

import com.mycompany.duan1_n8.entity.ChiTietSP;
import com.mycompany.duan1_n8.entity.HoaDon;
import com.mycompany.duan1_n8.entity.HoaDonChiTiet;
import com.mycompany.duan1_n8.entity.IdHoaDonChiTiet;
import com.mycompany.duan1_n8.entity.KhachHang;
import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.entity.PhieuGiamGia;
import com.mycompany.duan1_n8.repository.BanHangRepository;
import com.mycompany.duan1_n8.repository.NhanVienRepository;
import com.mycompany.duan1_n8.repository.PhieuGiamGiaRepository;
import com.mycompany.duan1_n8.service.BanHangService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.modelmapper.ModelMapper;

/**
 *
 * @author minhb
 */
public class BanHangServiceImpl implements BanHangService {

    private final ModelMapper modelMapper;
    private final PhieuGiamGiaRepository giaRepository;
    private final BanHangRepository banHangRepository;
    private final Date dateNow = new Date();
    private final Random random;
    private String i = null;

    public BanHangServiceImpl() {
        this.modelMapper = new ModelMapper();
        this.giaRepository = new PhieuGiamGiaRepository();
        this.banHangRepository = new BanHangRepository();
        this.random = new Random();
    }

    @Override
    public List<HoaDon> getAllHD() {
        return null;
    }

    @Override
    public boolean addHoaDon(HoaDon hoaDon, String maNhanVien, String maKhachHang) {
        return true;
    }

    @Override
    public boolean updateHoaDon(HoaDon hoaDon, String maNhanVien, String maKhachHang) {
//        KhachHang khachHang 
        return true;
    }

    @Override
    public List<ChiTietSP> getAllSanPhamBan() {
        return null;
    }

    @Override
    public ChiTietSP getOneSP(Long idSanPham) {
        return null;
    }

//    @Override
//    public List<HoaDonChiTiet> getListHDCTById(Long idHoaDon) {
//        List<HoaDonChiTiet> chiTiets = new ArrayList<>();
//        for (HoaDonChiTiet hoaDonChiTiet : chiTietRepository.getAllHDCTById(idHoaDon)) {
//            HoaDonChiTiet newHD = new HoaDonChiTiet(hoaDonChiTiet.getIdHDCT(), hoaDonChiTiet.getHoaDon(), hoaDonChiTiet.getChiTietSP(),
//                    hoaDonChiTiet.getSoLuong(), hoaDonChiTiet.getDonGia(), hoaDonChiTiet.getTrangThai(),
//                    hoaDonChiTiet.getPhieuGiamGia(), hoaDonChiTiet.getTongTien());
//            chiTiets.add(newHD);
//        }
//        return chiTiets;
//    }
//    @Override
//    public String checkSP(Long idHoaDon, Long idSanPham) {
//        for (HoaDonChiTiet hoaDonChiTiet : getListHDCTById(idHoaDon)) {
//            if (idSanPham.equals(hoaDonChiTiet.getChiTietSP().getMaQr())) {
//                return hoaDonChiTiet.getIdHDCT();
//            }
//        }
//        return null;
//    }
//    
//    @Override
//    public boolean AddCTHD(Long idHoaDon, HoaDonChiTiet hoaDonChiTiet) {
//        HoaDon hoaDon = hoaDonRepository.getOneHD(idHoaDon);
//        ChiTietSP ctsp = chiTietSPRepository.getOne(hoaDonChiTiet.getChiTietSP().getMaQr());
//        HoaDonChiTiet addHDCT = new HoaDonChiTiet(hoaDonChiTiet.getIdHDCT(), hoaDon, ctsp, hoaDonChiTiet.getSoLuong(),
//                hoaDonChiTiet.getDonGia(), hoaDonChiTiet.getTrangThai(), null, hoaDonChiTiet.getTongTien());
//        return chiTietRepository.addGioHang(addHDCT);
//    }
    @Override
    public List<HoaDonChiTiet> getListHDCTById(Long idHoaDon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String checkSP(Long idHoaDon, Long idSanPham) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean AddCTHD(Long idHoaDon, HoaDonChiTiet hoaDonChiTiet) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addGioHang(HoaDonChiTiet hdct) {
        return true;
    }

    @Override
    public boolean updateSoLuong(ChiTietSP chiTietSP) {
        return true;
    }

    @Override
    public String deleteOrder(IdHoaDonChiTiet idHoaDonChiTiet) {
        return null;
    }

    @Override
    public String thanhToan(HoaDon hoaDon) {
        return null;
    }

    @Override
    public boolean huyHoaDon(HoaDon hoaDon) {
        Boolean huyHoaDon = banHangRepository.huyHoaDon(hoaDon);
        return huyHoaDon;
    }
}
