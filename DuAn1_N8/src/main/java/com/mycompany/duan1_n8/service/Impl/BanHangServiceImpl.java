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
import com.mycompany.duan1_n8.repository.BanHangRepository;
import com.mycompany.duan1_n8.repository.ChiTietSPRepository;
import com.mycompany.duan1_n8.repository.HoaDonChiTietRepository;
import com.mycompany.duan1_n8.repository.HoaDonRepository;
import com.mycompany.duan1_n8.repository.KhachHangRepository;
import com.mycompany.duan1_n8.repository.NhanVienRepository;
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
    private final ChiTietSPRepository chiTietSPRepository;
    private final HoaDonRepository hoaDonRepository;
    private final HoaDonChiTietRepository chiTietRepository;
    private final NhanVienRepository nhanVienRepository;
    private final KhachHangRepository khachHangRepository;
    private final BanHangRepository banHangRepository;

    private final Date dateNow = new Date();
    private final Random random;
    private String i = null;

    public BanHangServiceImpl() {
        this.modelMapper = new ModelMapper();
        this.chiTietSPRepository = new ChiTietSPRepository();
        this.hoaDonRepository = new HoaDonRepository();
        this.chiTietRepository = new HoaDonChiTietRepository();
        this.nhanVienRepository = new NhanVienRepository();
        this.khachHangRepository = new KhachHangRepository();
        this.banHangRepository = new BanHangRepository();

        this.random = new Random();
    }

    @Override
    public List<HoaDon> getAllHD() {
        List<HoaDon> listAllHD = new ArrayList<>();
        for (HoaDon hoaDon : hoaDonRepository.getAllHD()) {
            if (hoaDon.getTrangThai() == 0) {
                listAllHD.add(new HoaDon(hoaDon.getIdHoaDon(), hoaDon.getMaHoaDon(), hoaDon.getNgayTao(), hoaDon.getNgayThanhToan(), hoaDon.getNgayHuy(),
                        hoaDon.getMoTa(), hoaDon.getTienKhachDua(), hoaDon.getTienThua(), hoaDon.getTrangThai(), hoaDon.getKhachHang(), hoaDon.getNhanVien(), hoaDon.getPhieuGiamGia()));
            }
        }
        return listAllHD;
    }

    @Override
    public boolean addHoaDon(HoaDon hoaDon, String maNhanVien, String maKhachHang) {
        NhanVien nhanVienIndex = this.nhanVienRepository.getOne(maNhanVien);
        KhachHang khachHangIndex = this.khachHangRepository.getOne(maKhachHang);
        System.out.println(khachHangIndex);
        i = "HD" + random.nextInt();
        HoaDon taoHoaDon = new HoaDon(null, i, dateNow, null, null,
                null, null, null, 0, khachHangIndex, nhanVienIndex, null);
        return this.hoaDonRepository.taoHoaDon(taoHoaDon);
    }

    @Override
    public boolean updateHoaDon(HoaDon hoaDon, String maNhanVien, String maKhachHang) {
//        KhachHang khachHang 
        return true;
    }

    @Override
    public List<ChiTietSP> getAllSanPhamBan() {
        List<ChiTietSP> listSanPham = new ArrayList<>();
        for (ChiTietSP chiTietSP : chiTietSPRepository.getAllCTSP()) {
            if (chiTietSP.getTrangThai() == 1) {
                listSanPham.add(new ChiTietSP(chiTietSP.getMaQr(), chiTietSP.getNgaySanXuat(), chiTietSP.getHanSuDung(),
                        chiTietSP.getGia(), chiTietSP.getMoTa(), chiTietSP.getSoLuong(), chiTietSP.getTrangThai(),
                        chiTietSP.getThietKe(), chiTietSP.getDoiTuongSuDung(), chiTietSP.getNsx(),
                        chiTietSP.getMauSac(), chiTietSP.getSanPham(), chiTietSP.getLop()));
            }
        }
        return listSanPham;
    }

    @Override
    public ChiTietSP getOneSP(Long idSanPham) {
        ChiTietSP ctsp = chiTietSPRepository.getOne(idSanPham);
        return new ChiTietSP(ctsp.getMaQr(), ctsp.getNgaySanXuat(), ctsp.getHanSuDung(), ctsp.getGia(),
                ctsp.getMoTa(), ctsp.getSoLuong(), ctsp.getTrangThai(), ctsp.getThietKe(), ctsp.getDoiTuongSuDung(),
                ctsp.getNsx(), ctsp.getMauSac(), ctsp.getSanPham(), ctsp.getLop());
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
        Boolean isAdd = chiTietRepository.addGioHang(hdct);
        return isAdd;
    }

    @Override
    public boolean updateSoLuong(ChiTietSP chiTietSP) {
        Boolean isQuantity = banHangRepository.updateQuantity(chiTietSP);
        return isQuantity;
    }

    @Override
    public String deleteOrder(IdHoaDonChiTiet idHoaDonChiTiet) {
        Boolean isDeleteOrder = banHangRepository.deleteOrder(idHoaDonChiTiet);
        return isDeleteOrder ? "Thanh Cong" : "That Bai";
    }

    @Override
    public String thanhToan(HoaDon hoaDon) {
        Boolean isThanhToan = banHangRepository.updateThanhToan(hoaDon);
        return isThanhToan ? "Thanh Cong" : "That Bai";
    }

    @Override
    public boolean huyHoaDon(HoaDon hoaDon) {
        Boolean huyHoaDon = banHangRepository.huyHoaDon(hoaDon);
        return huyHoaDon;
    }
}
