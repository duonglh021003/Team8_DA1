/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.duan1_n8.view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.mycompany.duan1_n8.entity.ChiTietSP;
import com.mycompany.duan1_n8.entity.ChucVu;
import com.mycompany.duan1_n8.entity.DoiTuongSuDung;
import com.mycompany.duan1_n8.entity.HoaDon;
import com.mycompany.duan1_n8.entity.HoaDonChiTiet;
import com.mycompany.duan1_n8.entity.IdHoaDonChiTiet;
import com.mycompany.duan1_n8.entity.KhachHang;
import com.mycompany.duan1_n8.entity.Lop;
import com.mycompany.duan1_n8.entity.MauSac;
import com.mycompany.duan1_n8.entity.NSX;
import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.entity.PhieuGiamGia;
import com.mycompany.duan1_n8.entity.SanPham;
import com.mycompany.duan1_n8.entity.ThietKe;
import com.mycompany.duan1_n8.repository.HoaDonChiTietRepository;
import com.mycompany.duan1_n8.service.ChiTietSPService;
import com.mycompany.duan1_n8.service.DoiTuongSuDungService;
import com.mycompany.duan1_n8.service.Impl.ChiTietSPServiceImpl;
import com.mycompany.duan1_n8.service.Impl.DoiTuongSuDungServiceImpl;
import com.mycompany.duan1_n8.service.Impl.KhachHangServiceImpl;
import com.mycompany.duan1_n8.service.Impl.LopServiceImpl;
import com.mycompany.duan1_n8.service.Impl.MauSacServiceImpl;
import com.mycompany.duan1_n8.service.Impl.NSXServiceImpl;
import com.mycompany.duan1_n8.service.Impl.NhanVienServiceImpl;
import com.mycompany.duan1_n8.service.Impl.PhieuGiamGiaServiceImpl;
import com.mycompany.duan1_n8.service.Impl.SanPhamServiceImpl;
import com.mycompany.duan1_n8.service.Impl.ThietKeServiceImpl;
import com.mycompany.duan1_n8.service.Impl.BanHangServiceImpl;
import com.mycompany.duan1_n8.service.KhachHangService;
import com.mycompany.duan1_n8.service.LopService;
import com.mycompany.duan1_n8.service.MauSacService;
import com.mycompany.duan1_n8.service.NSXService;
import com.mycompany.duan1_n8.service.NhanVienService;
import com.mycompany.duan1_n8.service.PhieuGiamGiaService;
import com.mycompany.duan1_n8.service.SanPhamService;
import com.mycompany.duan1_n8.service.ThietKeService;
import com.mycompany.duan1_n8.service.BanHangService;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BuiDucMinh
 */
public class FormBanHang extends javax.swing.JPanel {

    private final SanPhamService sanPhamService;
    private final NSXService nSXService;
    private final MauSacService mauSacService;
    private final LopService lopService;
    private final ThietKeService thietKeService;
    private final DoiTuongSuDungService dTSDService;
    private final ChiTietSPService chiTietSPService;
    private final NhanVienService nhanVienService;
    private final KhachHangService khachHangService;
    private final PhieuGiamGiaService phieuGiamGiaService;
    private final BanHangService banHangService;

    private HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();

    private List<SanPham> listSanPham = new ArrayList<>();
    private List<NSX> listNSX = new ArrayList<>();
    private List<MauSac> listMauSac = new ArrayList<>();
    private List<Lop> listLop = new ArrayList<>();
    private List<ThietKe> listThietKe = new ArrayList<>();
    private List<DoiTuongSuDung> listDTSD = new ArrayList<>();
    private List<ChiTietSP> listCTSP = new ArrayList<>();
    private List<NhanVien> listNhanVien = new ArrayList<>();
    private List<KhachHang> listKhachHang = new ArrayList<>();
    private List<PhieuGiamGia> listPhieuGiamGia = new ArrayList<>();
    private List<HoaDon> listHoaDon = new ArrayList<>();
    private List<HoaDonChiTiet> listHDCT = new ArrayList<>();

    private DefaultTableModel tableModel = new DefaultTableModel();
    private final DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
    private final DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private String maHoaDon = null;
    private final Date dateNow = new Date();
    private int indexHoaDon = 0;
    private int indexSanPham = 0;
    private int indexGioHang = 0;
    private final Long idHoaDon = 0L;
    private final float index1 = 0;
    private float index2 = 0;
    private int indexDelete = 0;

    private DefaultTableModel model;
    private DefaultComboBoxModel defaultComboBoxModel;
    private Webcam webcam;
    private WebcamPanel webcamPanel;
    private Thread captureThread;
    private boolean isCameraClosed = false;
    private JFrame frame;

    public FormBanHang() {

        initComponents();
        sanPhamService = new SanPhamServiceImpl();
        nSXService = new NSXServiceImpl();
        mauSacService = new MauSacServiceImpl();
        lopService = new LopServiceImpl();
        thietKeService = new ThietKeServiceImpl();
        dTSDService = new DoiTuongSuDungServiceImpl();
        chiTietSPService = new ChiTietSPServiceImpl();
        nhanVienService = new NhanVienServiceImpl();
        khachHangService = new KhachHangServiceImpl();
        phieuGiamGiaService = new PhieuGiamGiaServiceImpl();
        banHangService = new BanHangServiceImpl();

        listSanPham = sanPhamService.getAllSP();
        listNSX = nSXService.getAllNSX();
        listMauSac = mauSacService.getAllMS();
        listLop = lopService.getAllLop();
        listThietKe = thietKeService.getAllTK();
        listDTSD = dTSDService.getAllDTSD();
        listCTSP = banHangService.getAllSanPhamBan();
        listNhanVien = nhanVienService.getAll();
        listKhachHang = khachHangService.getAllKH();
        listPhieuGiamGia = phieuGiamGiaService.getAllPGG();
        listHoaDon = banHangService.getAllHD();

        loadDataSP(listCTSP);
        loadDataHD(listHoaDon);
        loadDonHang();
        btn_huy.setEnabled(false);
        btn_ThanhToan.setEnabled(false);
        loadCBB();

    }

    // load CBB
    private void loadCBB() {
        cbGiamGia.removeAllItems();
        cbGiamGia.addItem("Vui Long Chon Giam Gia");
        for (PhieuGiamGia pgg : phieuGiamGiaService.getAllPGG()) {
            cbGiamGia.addItem(pgg.getTenPhieuGiamGia());
        }
    }

    // load Nhan Vien
    // loadData San Pham
    private void loadDataSP(List<ChiTietSP> listCTSP1) {
        tableModel = (DefaultTableModel) tbl_bangSanPham.getModel();
        tableModel.setRowCount(0);
        for (ChiTietSP chiTietSP : listCTSP1) {
            if (!(chiTietSP.getSoLuong().equals(0))) {
                tableModel.addRow(new Object[]{
                    chiTietSP.getMaQr(),
                    chiTietSP.getSanPham().getTen(),
                    decimalFormat.format(chiTietSP.getGia()),
                    chiTietSP.getSoLuong(),
                    chiTietSP.getMauSac().getTenMauSac(),
                    chiTietSP.getNsx().getTen(),
                    chiTietSP.getThietKe().getTen(),
                    chiTietSP.layTrangThai()
                });
            }
        }
    }

    // Bang Hoa Don
    private void loadDataHD(List<HoaDon> listHoaDon1) {
        tableModel = (DefaultTableModel) tbl_hoadon.getModel();
        tableModel.setRowCount(0);

        for (HoaDon hd : listHoaDon1) {
            tableModel.addRow(new Object[]{
                hd.getIdHoaDon(),
                hd.getMaHoaDon(),
                sdf.format(hd.getNgayTao()),
                hd.getKhachHang().getTenKH(),
                hd.getNhanVien().getTen(),
                hd.layTrangThaiHD()

            });
        }

    }

    private void loadDataHD001() {
        
        List<HoaDon> listHoaDon1 = banHangService.getAllHD();
        tableModel = (DefaultTableModel) tbl_hoadon.getModel();
        tableModel.setRowCount(0);

        for (HoaDon hd : listHoaDon1) {
            tableModel.addRow(new Object[]{
                hd.getIdHoaDon(),
                hd.getMaHoaDon(),
                sdf.format(hd.getNgayTao()),
                hd.getKhachHang().getTenKH(),
                hd.getNhanVien().getTen(),
                hd.layTrangThaiHD()

            });
        }

    }
// show Hoa Don
    private void showHoaDon() {
        indexHoaDon = tbl_hoadon.getSelectedRow();
        txt_maHoaDon1.setText(tbl_hoadon.getValueAt(indexHoaDon, 1).toString());
        
    }

    // Tao Hoa Don
    private void taoHoaDon() {
        HoaDon hoaDon = new HoaDon();
        hoaDon.setNgayTao(dateNow);

        String maNhanVien = "";
        Integer idMaNV = Integer.valueOf(txt_maNV.getText().trim());
        for (NhanVien nv : nhanVienService.getAll()) {
            if (nv.getMaQr().equals(idMaNV)) {
                maNhanVien = nv.getMa();
            }
        }

        String maKhachHang = "";
        Integer idMaKH = Integer.valueOf(txt_maKH.getText().trim());
        for (KhachHang kh : khachHangService.getAllKH()) {
            if (kh.getMaQR().equals(idMaKH)) {
                maKhachHang = kh.getMaKH();
            }
        }

        if (banHangService.addHoaDon(hoaDon, maNhanVien, maKhachHang)) {
            JOptionPane.showMessageDialog(this, "Tao Hoa Don Thanh Cong");
        }
        listHoaDon = banHangService.getAllHD();
        loadDataHD(listHoaDon);
        tbl_hoadon.setRowSelectionInterval(0, 0);
        showHoaDon();
        maHoaDon = txt_maHoaDon1.getText().trim();
        cbGiamGia.removeAllItems();
        btn_huy.setEnabled(true);
        btn_ThanhToan.setEnabled(true);
    }

    // Add Gio Hang 
    private void addGioHang() {

        // lay vi tri hoa don
        indexHoaDon = tbl_hoadon.getSelectedRow();
        // lay vi tri san pham
        indexSanPham = tbl_bangSanPham.getSelectedRow();

        indexGioHang = tbl_gioHang.getSelectedRow();
        // checkin San Pham
        if (indexSanPham == -1) {
            JOptionPane.showMessageDialog(this, indexSanPham);
            return;
        }
        // checkIn Hoa Don
        if (txt_maHoaDon1.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban Can Phai Chon Hoa Don Truoc");
            return;
        } else {

            String rowSelect = JOptionPane.showInputDialog("Moi Nhap So Luong :");

            int soLuongNhap = Integer.parseInt(rowSelect);
            int soLuongTon = Integer.parseInt(tbl_bangSanPham.getValueAt(indexSanPham, 3).toString());

            System.out.println("Check So Luong Moi" + soLuongNhap);
            System.out.println("Check So Luong Ton" + soLuongTon);

            // checkIn SoLuong
            if (soLuongNhap > soLuongTon) {
                JOptionPane.showMessageDialog(this, "So Luong Khong Du");
                return;
            } else {
                Long maSanPham = Long.parseLong(tbl_bangSanPham.getValueAt(indexSanPham, 0).toString());
                Long idHoaDon1 = Long.parseLong(tbl_hoadon.getValueAt(indexHoaDon, 0).toString());

                // Lay Id Cua San Pham
                ChiTietSP addCtsp = ChiTietSP.builder()
                        .maQr(maSanPham)
                        .build();

                // Lay Id Cua Hoa Don
                HoaDon addHoaDon = HoaDon.builder()
                        .idHoaDon(idHoaDon1)
                        .build();
                System.out.println("Check Ma San Pham: " + maSanPham);
                System.out.println("Check Id Hoa Don: " + idHoaDon1);

                // neu san pham da co trong gio hang    /// CHUA FIX DUOC DOAN NÀY
                // Lay Don Gia Cua San Pham
                Double giaSanPham = Double.parseDouble(tbl_bangSanPham.getValueAt(indexSanPham, 2).toString());
                Double thanhTien2 = soLuongNhap * giaSanPham;
                System.out.println("Check Thanh Tien San Pham" + thanhTien2);

                // Doc Vao Id Hoa Don Chi Tiet
                IdHoaDonChiTiet addIdHDCT = IdHoaDonChiTiet.builder()
                        .hoaDon(addHoaDon)
                        .chiTietSP(addCtsp)
                        .build();
                System.out.println("Check Id Hoa Don: " + addIdHDCT);

                // Add Du Lieu Vao Khay Nho Tam
                HoaDonChiTiet addGioHang = HoaDonChiTiet.builder()
                        .idHoaDonChiTiet(addIdHDCT)
                        .soLuong(soLuongNhap)
                        .donGia(BigDecimal.valueOf(giaSanPham))
                        .tongTien(BigDecimal.valueOf(thanhTien2))
                        .trangThai(chiTietSPService.getAllCTSP().get(indexSanPham).getTrangThai())
                        .build();
                System.out.println("Check Gio Hang: " + addGioHang);
                banHangService.addGioHang(addGioHang);

                // Update So Luong Con Lai San Pham
                int soLuongConLai = soLuongTon - soLuongNhap;
                ChiTietSP updateQuantity = ChiTietSP.builder()
                        .maQr(maSanPham)
                        .ngaySanXuat(chiTietSPService.getAllCTSP().get(indexSanPham).getNgaySanXuat())
                        .hanSuDung(chiTietSPService.getAllCTSP().get(indexSanPham).getHanSuDung())
                        .gia(chiTietSPService.getAllCTSP().get(indexSanPham).getGia())
                        .moTa(chiTietSPService.getAllCTSP().get(indexSanPham).getMoTa())
                        .soLuong(soLuongConLai)
                        .trangThai(chiTietSPService.getAllCTSP().get(indexSanPham).getTrangThai())
                        .thietKe(chiTietSPService.getAllCTSP().get(indexSanPham).getThietKe())
                        .doiTuongSuDung(chiTietSPService.getAllCTSP().get(indexSanPham).getDoiTuongSuDung())
                        .nsx(chiTietSPService.getAllCTSP().get(indexSanPham).getNsx())
                        .mauSac(chiTietSPService.getAllCTSP().get(indexSanPham).getMauSac())
                        .sanPham(chiTietSPService.getAllCTSP().get(indexSanPham).getSanPham())
                        .lop(chiTietSPService.getAllCTSP().get(indexSanPham).getLop())
                        .build();
                System.out.println("Check So Luong Con Lai: " + soLuongConLai);
                System.out.println("Check Du Lieu So Luong Moi: " + updateQuantity);
                banHangService.updateSoLuong(updateQuantity);

                loadHoaDonChiTiet();
                listCTSP = chiTietSPService.getAllCTSP();
                loadDataSP(listCTSP);
                thanhTien();
            }
        }
    }

    private void updateSoLuongSanPham() {

        // lay vi tri hoa don
        indexHoaDon = tbl_hoadon.getSelectedRow();
        // lay vi tri san pham
        indexSanPham = tbl_bangSanPham.getSelectedRow();

        indexGioHang = tbl_gioHang.getSelectedRow();
        // checkin San Pham
        if (indexGioHang == -1) {
            JOptionPane.showMessageDialog(this, indexGioHang);
            return;
        }

        // checkIn Hoa Don
        if (txt_maHoaDon1.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Ban Can Phai Chon Hoa Don Truoc");
            return;
        } else {
            String rowSelect = JOptionPane.showInputDialog("Nhap so luong can them :");
            int soLuongNhap = Integer.parseInt(rowSelect);
            int soLuongMoi = 0;
            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTietRepository.getAll()) {
                soLuongMoi = soLuongNhap + hoaDonChiTiet.getSoLuong();
            }
            System.out.println("aaaaaaaaaaaaaaaaaaaaa       " + soLuongMoi);

            Long maSanPham = Long.parseLong(tbl_bangSanPham.getValueAt(indexSanPham, 0).toString());
            Long idHoaDon1 = Long.parseLong(tbl_hoadon.getValueAt(indexHoaDon, 0).toString());

            // Lay Id Cua San Pham
            ChiTietSP addCtsp = ChiTietSP.builder()
                    .maQr(maSanPham)
                    .build();

            // Lay Id Cua Hoa Don
            HoaDon addHoaDon = HoaDon.builder()
                    .idHoaDon(idHoaDon1)
                    .build();
            System.out.println("Check Ma San Pham: " + maSanPham);
            System.out.println("Check Id Hoa Don: " + idHoaDon1);

            // neu san pham da co trong gio hang    /// CHUA FIX DUOC DOAN NÀY
            // Lay Don Gia Cua San Pham
            Double giaSanPham = Double.parseDouble(tbl_bangSanPham.getValueAt(indexSanPham, 2).toString());
            Double thanhTien2 = soLuongNhap * giaSanPham;
            System.out.println("Check Thanh Tien San Pham" + thanhTien2);

            // Doc Vao Id Hoa Don Chi Tiet
            IdHoaDonChiTiet addIdHDCT = IdHoaDonChiTiet.builder()
                    .hoaDon(addHoaDon)
                    .chiTietSP(addCtsp)
                    .build();
            System.out.println("Check Id Hoa Don: " + addIdHDCT);

            HoaDonChiTiet updateHoaDonChiTiet = HoaDonChiTiet.builder()
                    .idHoaDonChiTiet(addIdHDCT)
                    .soLuong(soLuongMoi)
                    .donGia(BigDecimal.valueOf(giaSanPham))
                    .tongTien(BigDecimal.valueOf(thanhTien2))
                    .trangThai(chiTietSPService.getAllCTSP().get(indexSanPham).getTrangThai())
                    .build();
            hoaDonChiTietRepository.update(updateHoaDonChiTiet);

            // Update So Luong Con Lai San Pham
            int soLuongConLai = 0;
            for (ChiTietSP chiTietSP : chiTietSPService.getAllCTSP()) {
                soLuongConLai = chiTietSP.getSoLuong() - soLuongNhap;
            }
            ChiTietSP updateQuantity = ChiTietSP.builder()
                    .maQr(maSanPham)
                    .ngaySanXuat(chiTietSPService.getAllCTSP().get(indexSanPham).getNgaySanXuat())
                    .hanSuDung(chiTietSPService.getAllCTSP().get(indexSanPham).getHanSuDung())
                    .gia(chiTietSPService.getAllCTSP().get(indexSanPham).getGia())
                    .moTa(chiTietSPService.getAllCTSP().get(indexSanPham).getMoTa())
                    .soLuong(soLuongConLai)
                    .trangThai(chiTietSPService.getAllCTSP().get(indexSanPham).getTrangThai())
                    .thietKe(chiTietSPService.getAllCTSP().get(indexSanPham).getThietKe())
                    .doiTuongSuDung(chiTietSPService.getAllCTSP().get(indexSanPham).getDoiTuongSuDung())
                    .nsx(chiTietSPService.getAllCTSP().get(indexSanPham).getNsx())
                    .mauSac(chiTietSPService.getAllCTSP().get(indexSanPham).getMauSac())
                    .sanPham(chiTietSPService.getAllCTSP().get(indexSanPham).getSanPham())
                    .lop(chiTietSPService.getAllCTSP().get(indexSanPham).getLop())
                    .build();
            System.out.println("Check So Luong Con Lai: " + soLuongConLai);
            System.out.println("Check Du Lieu So Luong Moi: " + updateQuantity);
            banHangService.updateSoLuong(updateQuantity);

            loadHoaDonChiTiet();
            listCTSP = chiTietSPService.getAllCTSP();
            loadDataSP(listCTSP);
            loadDonHang();

        }
    }

    // Xoa San Pham Trong Gio
    private void deleteSanPhamGioHangOne() {
        indexHoaDon = tbl_hoadon.getSelectedRow();
        indexGioHang = tbl_gioHang.getSelectedRow();

        String tenSP = tbl_gioHang.getValueAt(indexGioHang, 0).toString();
        Long idHoaDon1 = Long.parseLong(tbl_hoadon.getValueAt(indexHoaDon, 0).toString());

        HoaDon addHoaDon = HoaDon.builder()
                .idHoaDon(idHoaDon1)
                .build();

        Long idSP = null;
        for (SanPham sp : sanPhamService.getAllSP()) {
            if (sp.getTen().equalsIgnoreCase(tenSP)) {
                idSP = sp.getId();
            }
        }

        SanPham sp = SanPham.builder()
                .id(idSP)
                .ten(tenSP)
                .build();

        Long idCTSP = null;
        for (ChiTietSP ct : chiTietSPService.getAllCTSP()) {
            if (ct.getSanPham().getId().equals(idSP)) {
                idCTSP = ct.getMaQr();
            }
        }
        int soLuongGioHang = Integer.parseInt(tbl_gioHang.getValueAt(indexGioHang, 1).toString());
        System.out.println("ccccccccccccccccccccc       " + soLuongGioHang);

        ChiTietSP ctsp = ChiTietSP.builder()
                .maQr(idCTSP)
                .gia(BigDecimal.valueOf(soLuongGioHang))
                .build();

        IdHoaDonChiTiet addIdHDCT = IdHoaDonChiTiet.builder()
                .hoaDon(addHoaDon)
                .chiTietSP(ctsp)
                .build();

        int soLuongConLai = 0;

        for (ChiTietSP ctsp01 : chiTietSPService.getAllCTSP()) {
            System.out.println("ddddddd     " + ctsp01.getMaQr());
            System.out.println("vvvvvvvvvv      " + idCTSP);
            if (ctsp01.getMaQr().equals(idCTSP)) {
                System.out.println("mmmmmmmm        " + ctsp01.getSoLuong());
                System.out.println("aaaaaaaa    " + soLuongConLai);
                soLuongConLai = ctsp01.getSoLuong() + soLuongGioHang;
                ChiTietSP updateQuantity = ChiTietSP.builder()
                        .maQr(idCTSP)
                        .ngaySanXuat(chiTietSPService.getAllCTSP().get(indexSanPham).getNgaySanXuat())
                        .hanSuDung(chiTietSPService.getAllCTSP().get(indexSanPham).getHanSuDung())
                        .gia(chiTietSPService.getAllCTSP().get(indexSanPham).getGia())
                        .moTa(chiTietSPService.getAllCTSP().get(indexSanPham).getMoTa())
                        .soLuong(soLuongConLai)
                        .trangThai(chiTietSPService.getAllCTSP().get(indexSanPham).getTrangThai())
                        .thietKe(chiTietSPService.getAllCTSP().get(indexSanPham).getThietKe())
                        .doiTuongSuDung(chiTietSPService.getAllCTSP().get(indexSanPham).getDoiTuongSuDung())
                        .nsx(chiTietSPService.getAllCTSP().get(indexSanPham).getNsx())
                        .mauSac(chiTietSPService.getAllCTSP().get(indexSanPham).getMauSac())
                        .sanPham(sp)
                        .lop(chiTietSPService.getAllCTSP().get(indexSanPham).getLop())
                        .build();
                System.out.println("Check So Luong Con Lai: " + soLuongConLai);
                System.out.println("Check Du Lieu So Luong Moi: " + updateQuantity);
                banHangService.updateSoLuong(updateQuantity);

                loadHoaDonChiTiet();
                listCTSP = chiTietSPService.getAllCTSP();
                loadDataSP(listCTSP);
                break;
            }
        }

        HoaDonChiTiet hdct = HoaDonChiTiet.builder().idHoaDonChiTiet(addIdHDCT).build();
        hoaDonChiTietRepository.delete(hdct);
        JOptionPane.showMessageDialog(this, "xoá sản phẩm thành công");
    }

    private void deleteSanPhamGioHangAll() {

        indexHoaDon = tbl_hoadon.getSelectedRow();

        Long idHoaDon1 = Long.parseLong(tbl_hoadon.getValueAt(indexHoaDon, 0).toString());

        HoaDon addHoaDon = HoaDon.builder()
                .idHoaDon(idHoaDon1)
                .build();

        Long idCTSP = null;

        for (HoaDonChiTiet hdct01 : hoaDonChiTietRepository.getAll()) {
            if (hdct01.getIdHoaDonChiTiet().getHoaDon().getIdHoaDon().equals(idHoaDon1)) {
                idCTSP = hdct01.getIdHoaDonChiTiet().getChiTietSP().getMaQr();
            }
        }

        ChiTietSP ctsp = ChiTietSP.builder()
                .maQr(idCTSP)
                .build();

        IdHoaDonChiTiet addIdHDCT = IdHoaDonChiTiet.builder()
                .hoaDon(addHoaDon)
                .chiTietSP(ctsp)
                .build();

        HoaDonChiTiet hdct = HoaDonChiTiet.builder().idHoaDonChiTiet(addIdHDCT).build();
        hoaDonChiTietRepository.deleteAll(hdct);
        JOptionPane.showMessageDialog(this, "xoá sản phẩm thành công");
    }

    // Doc Hoa Don
    private void chonHoaDon() {
        int rowHD = this.tbl_hoadon.getSelectedRow();

        if (rowHD == -1) {
            return;
        }
        Long hoaDon = Long.valueOf(this.tbl_hoadon.getValueAt(rowHD, 0).toString());

        System.out.println("Hoa Don Duoc Chon La: " + hoaDon);
        tableModel = (DefaultTableModel) this.tbl_gioHang.getModel();
        tableModel.setRowCount(0);
        for (HoaDonChiTiet hdct : hoaDonChiTietRepository.getAll()) {
            if (hdct.getIdHoaDonChiTiet().getHoaDon().getIdHoaDon().equals(hoaDon)) {
                tableModel.addRow(new Object[]{
                    hdct.getIdHoaDonChiTiet().getChiTietSP().getSanPham().getTen(),
                    hdct.getSoLuong(),
                    decimalFormat.format(hdct.getDonGia()),
                    decimalFormat.format(hdct.getTongTien())
                });
            }
        }
        btn_huy.setEnabled(true);
        btn_ThanhToan.setEnabled(true);
        loadDonHang();
        tableHoaDon();
    }

    private void thanhTien() {
        double total = 0;
        double Amount = 0;
        double thanhTien = 0;
        double TienGiamKM = 0;
        for (int i = 0; i < this.tbl_gioHang.getRowCount(); i++) {
            Amount = Double.parseDouble(this.tbl_gioHang.getValueAt(i, 3).toString());
            total = Amount + total;
        }
        String totalst = Double.toString(total);
        lbl_tongThanhTien.setText(totalst);

        String cbo = cbGiamGia.getSelectedItem().toString();

        for (PhieuGiamGia pgg : phieuGiamGiaService.getAllPGG()) {
            if (pgg.getTenPhieuGiamGia().equalsIgnoreCase(cbo)) {
                TienGiamKM = Double.valueOf(pgg.getGiaTriGiam());
            }

        }
        thanhTien = total - TienGiamKM;
        String thanhTien01 = Double.toString(thanhTien);
        lbl_thanhToan.setText(thanhTien01);

    }

    // Doc Ma Hoa Don
    private void tableHoaDon() {
        int row = tbl_hoadon.getSelectedRow();
        txt_maHoaDon1.setText(tbl_hoadon.getValueAt(row, 1).toString());
    }

    // load Gio Hang
    private void loadDonHang() {

        double total = 0;
        double Amount = 0;

        for (int i = 0; i < this.tbl_gioHang.getRowCount(); i++) {
            Amount = Double.parseDouble(this.tbl_gioHang.getValueAt(i, 3).toString());
            total = Amount + total;
        }
        String totalst = Double.toString(total);
        lbl_tongThanhTien.setText(totalst);

    }

    private void loadHoaDonChiTiet() {
        int rowHD = this.tbl_hoadon.getSelectedRow();
        rowHD = 0;
        Long hoaDon = Long.valueOf(this.tbl_hoadon.getValueAt(rowHD, 0).toString());

        tableModel = (DefaultTableModel) this.tbl_gioHang.getModel();
        tableModel.setRowCount(0);
        for (HoaDonChiTiet hdct : hoaDonChiTietRepository.getAll()) {
            if (hdct.getIdHoaDonChiTiet().getHoaDon().getIdHoaDon().equals(hoaDon)) {
                tableModel.addRow(new Object[]{
                    hdct.getIdHoaDonChiTiet().getChiTietSP().getSanPham().getTen(),
                    hdct.getSoLuong(),
                    hdct.getDonGia(),
                    hdct.getTongTien(),
                    hdct.getIdHoaDonChiTiet()
                });
            }
        }
    }

    // Tinh Tien Thua
    private void tienThua() {
        try {
            Double tongTien = Double.parseDouble(lbl_thanhToan.getText().trim());
            Double tienKhachDua = Double.parseDouble(txt_tienKhachDua.getText().trim());
            if (tienKhachDua > tongTien) {
                Double tienKhachThua = tienKhachDua - tongTien;
                String index5 = Double.toString(tienKhachThua);
                lbl_TienThua.setText(index5);
            } else if (tienKhachDua < 0) {
                System.out.println("Loi Nhap So Tien");
                lbl_TienThua.setText("NaN");
            } else {
                System.out.println("Loi Tien Khach Dua Phai Lon Hon Tong Tong Tien");
                lbl_TienThua.setText("NaN");
            }
        } catch (NumberFormatException e) {
            System.out.println("Loi Nhap Tien Phai La So");
        }
    }

    // In Hoá Ðon
    private void inHoaDon() {

    }

    // Thanh Toan
    private void thanhToan() {
        System.out.println("1");
        indexHoaDon = tbl_hoadon.getSelectedRow();
        System.out.println("2");
        Double tienKhachDua = Double.parseDouble(txt_tienKhachDua.getText().trim());
        System.out.println("3");
        Double tienThua = Double.parseDouble(lbl_TienThua.getText().trim());
        System.out.println("4");

        Double tongTien = Double.valueOf(lbl_tongThanhTien.getText().trim());
        Double thanhTien = Double.valueOf(lbl_thanhToan.getText().trim());
        HoaDon updateHoaDon = HoaDon.builder()
                .idHoaDon(banHangService.getAllHD().get(indexHoaDon).getIdHoaDon())
                .maHoaDon(banHangService.getAllHD().get(indexHoaDon).getMaHoaDon())
                .ngayTao(banHangService.getAllHD().get(indexHoaDon).getNgayTao())
                .moTa(txt_mota.getText().trim())
                .tongTienHoaDon(BigDecimal.valueOf(tongTien))
                .thanhTien(BigDecimal.valueOf(thanhTien))
                .tienKhachDua(BigDecimal.valueOf(tienKhachDua))
                .tienThua(BigDecimal.valueOf(tienThua))
                .trangThai(1)
                .khachHang(banHangService.getAllHD().get(indexHoaDon).getKhachHang())
                .nhanVien(banHangService.getAllHD().get(indexHoaDon).getNhanVien())
                .phieuGiamGia(banHangService.getAllHD().get(indexHoaDon).getPhieuGiamGia())
                .build();

        System.out.println("Hoa Don Khi Sua Lai: " + updateHoaDon);

        String update = banHangService.thanhToan(updateHoaDon);
        listHoaDon = banHangService.getAllHD();
        loadDataHD(listHoaDon);
        loadDonHang();
        loadHoaDonChiTiet();
        JOptionPane.showMessageDialog(this, update);
        btn_ThanhToan.setEnabled(false);
        btn_huy.setEnabled(false);
    }

    // Huy Hoa Don
    private void huyHoaDon() {
        indexHoaDon = tbl_hoadon.getSelectedRow();
        Long idHoaDon1 = Long.parseLong(tbl_hoadon.getValueAt(indexHoaDon, 0).toString());
        HoaDon addHoaDon = HoaDon.builder()
                .idHoaDon(idHoaDon1)
                .build();
        banHangService.huyHoaDon(addHoaDon);
        
        JOptionPane.showMessageDialog(this, "Huy Hoa Don Thanh Cong");

        
    }

    private void searchTenKH() {

        Integer idKH = Integer.valueOf(txt_maKH.getText());

        for (KhachHang kh : khachHangService.getAllKH()) {
            System.out.println("aaaaaaaaaa      " + idKH);
            System.out.println("bbbbbbbbbb      " + kh.getMaQR());
            if (kh.getMaQR().equals(idKH)) {
                System.out.println("ccccccccccc     " + kh);
                txt_tenKH.setText(kh.getTenKH());
            }
        }
    }

    public void initWebcam() {

        frame = new JFrame();
        frame.setBounds(100, 100, 400, 300); // Đặt kích thước cửa sổ JFrame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        webcam = Webcam.getWebcams().get(0);
        Dimension size = WebcamResolution.VGA.getSize();
        webcam.setViewSize(size);
        webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setPreferredSize(size);
        webcamPanel.setFPSDisplayed(true);

        frame.getContentPane().add(webcamPanel);
        frame.setVisible(true);
    }

    public void captureThreadMaNV() {

        captureThread = new Thread() {
            @Override

            public void run() {

                do {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {

                        ex.printStackTrace();
                    }
                    Result result = null;
                    BufferedImage image = null;
                    if (webcam.open()) {
                        if ((image = webcam.getImage()) == null) {
                            continue;
                        }
                    }
                    LuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    try {
                        result = new MultiFormatReader().decode(bitmap);
                    } catch (NotFoundException ex) {
                        ex.printStackTrace();
                    }
                    if (result != null) {
                        txt_maNV.setText(result.getText());
                    }
                } while (true);
            }
        };
        captureThread.setDaemon(true);
        captureThread.start();

    }

    public void captureThreadMaKH() {

        captureThread = new Thread() {
            @Override

            public void run() {
                int count = 0;
                do {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {

                        ex.printStackTrace();
                    }
                    Result result = null;
                    BufferedImage image = null;
                    if (webcam.open()) {
                        if ((image = webcam.getImage()) == null) {
                            continue;
                        }
                    }
                    LuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
                    try {
                        result = new MultiFormatReader().decode(bitmap);
                    } catch (NotFoundException ex) {
                        ex.printStackTrace();
                    }
                    if (result != null) {
                        txt_maKH.setText(result.getText());
                    }

                } while (true);
            }
        };
        captureThread.setDaemon(true);
        captureThread.start();
    }

    public void stopCapture() {
        isCameraClosed = true;
        if (webcam != null && webcam.isOpen()) {
            webcam.close();
            frame.setVisible(false);
        }
    }

    private void searchTenSP() {
        List<ChiTietSP> list = chiTietSPService.getAllCTSP();

        String tenSP = txt_searchTenSp.getText();
        
        model = (DefaultTableModel) tbl_bangSanPham.getModel();
        model.setRowCount(0);

        for (ChiTietSP chiTietSP : list) {
            if (chiTietSP.getSanPham().getTen().equalsIgnoreCase(tenSP)) {
                model.addRow(new Object[]{
                    chiTietSP.getMaQr(),
                    chiTietSP.getSanPham().getTen(),
                    decimalFormat.format(chiTietSP.getGia()),
                    chiTietSP.getSoLuong(),
                    chiTietSP.getMauSac().getTenMauSac(),
                    chiTietSP.getNsx().getTen(),
                    chiTietSP.getThietKe().getTen(),
                    chiTietSP.layTrangThai()
                });
            }
        }
        
        if(tenSP.isEmpty()){
            loadDataSP(listCTSP);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_hoadon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_gioHang = new javax.swing.JTable();
        btn_deleteOne = new javax.swing.JButton();
        btn_deleteAll = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_bangSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_searchTenSp = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_maNV = new javax.swing.JTextField();
        btn_quetQRNV = new javax.swing.JButton();
        txt_maKH = new javax.swing.JTextField();
        btn_quetQRKH = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_tenKH = new javax.swing.JTextField();
        btn_quetQRKH1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btn_taoHoaDon = new javax.swing.JButton();
        lbl_tongThanhTien = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_thanhToan = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_tienKhachDua = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        lbl_TienThua = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        txt_mota = new javax.swing.JTextArea();
        btn_huy = new javax.swing.JButton();
        btn_lamMoi = new javax.swing.JButton();
        btn_ThanhToan = new javax.swing.JButton();
        txt_maHoaDon1 = new javax.swing.JTextField();
        lbl_tienkhachdua = new javax.swing.JLabel();
        cbGiamGia = new javax.swing.JComboBox<>();

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_hoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Mã Hoá Đơn", "Ngày Tạo", "tên Khách Hàng", "tên Nhân Viên", "Tình Trạng"
            }
        ));
        tbl_hoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoadonMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tbl_hoadonMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_hoadon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_gioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Thành Tiền"
            }
        ));
        tbl_gioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_gioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_gioHang);

        btn_deleteOne.setBackground(new java.awt.Color(153, 255, 204));
        btn_deleteOne.setText("Xóa 🙂");
        btn_deleteOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteOneActionPerformed(evt);
            }
        });

        btn_deleteAll.setBackground(new java.awt.Color(153, 255, 204));
        btn_deleteAll.setText("Xoá all");
        btn_deleteAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_deleteOne, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_deleteAll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_deleteOne, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_deleteAll, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_bangSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Ma San Pham", "Tên Sản Phẩm", "Đơn Giá", "Số Lượng", "Màu Sắc", "NSX", "Kích Thước", "Trang Thai"
            }
        ));
        tbl_bangSanPham.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tbl_bangSanPhamFocusGained(evt);
            }
        });
        tbl_bangSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bangSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_bangSanPhamMouseEntered(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_bangSanPham);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tìm kiếm sản phẩm:");

        txt_searchTenSp.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_searchTenSpCaretUpdate(evt);
            }
        });
        txt_searchTenSp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchTenSpFocusGained(evt);
            }
        });
        txt_searchTenSp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchTenSpKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchTenSpKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_searchTenSpKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txt_searchTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(127, 471, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_searchTenSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setText("Mã Nhân Viên");

        jLabel8.setText("Mã khách hàng");

        btn_quetQRNV.setBackground(new java.awt.Color(153, 255, 204));
        btn_quetQRNV.setText("quét qr");
        btn_quetQRNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quetQRNVActionPerformed(evt);
            }
        });

        btn_quetQRKH.setBackground(new java.awt.Color(153, 255, 204));
        btn_quetQRKH.setText("quét qr");
        btn_quetQRKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quetQRKHActionPerformed(evt);
            }
        });

        jLabel7.setText("tên khách hàng");

        txt_tenKH.setEditable(false);

        btn_quetQRKH1.setBackground(new java.awt.Color(153, 255, 204));
        btn_quetQRKH1.setText("stop");
        btn_quetQRKH1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quetQRKH1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txt_maNV, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_quetQRNV))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txt_maKH, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_quetQRKH1)
                                    .addComponent(btn_quetQRKH)))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txt_tenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_maNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_quetQRNV))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt_maKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_quetQRKH))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_tenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_quetQRKH1))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jLabel2.setText("Mã hóa đơn:");

        jLabel3.setText("Tổng tiền:");

        jLabel4.setText("Giảm giá:");

        jLabel10.setText("thành tiền");

        jLabel11.setText("Tiền khách đưa:");

        jLabel12.setText("Tiền thừa trả khách:");

        btn_taoHoaDon.setBackground(new java.awt.Color(153, 255, 204));
        btn_taoHoaDon.setText("Tạo");
        btn_taoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taoHoaDonActionPerformed(evt);
            }
        });

        lbl_tongThanhTien.setText("0");

        jLabel15.setText("VNĐ");

        lbl_thanhToan.setText("0");

        jLabel19.setText("VNĐ");

        txt_tienKhachDua.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_tienKhachDuaCaretUpdate(evt);
            }
        });
        txt_tienKhachDua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tienKhachDuaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tienKhachDuaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_tienKhachDuaKeyTyped(evt);
            }
        });

        jLabel20.setText("VNĐ");

        lbl_TienThua.setText("0");
        lbl_TienThua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lbl_TienThuaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lbl_TienThuaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lbl_TienThuaKeyTyped(evt);
            }
        });

        jLabel22.setText("VNĐ");

        jLabel23.setText("Ghi chú:");

        txt_mota.setColumns(20);
        txt_mota.setRows(5);
        jScrollPane5.setViewportView(txt_mota);

        btn_huy.setBackground(new java.awt.Color(153, 255, 204));
        btn_huy.setText("Hủy hóa đơn");
        btn_huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyActionPerformed(evt);
            }
        });

        btn_lamMoi.setBackground(new java.awt.Color(153, 255, 204));
        btn_lamMoi.setText("Làm mới");
        btn_lamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lamMoiActionPerformed(evt);
            }
        });

        btn_ThanhToan.setBackground(new java.awt.Color(153, 255, 204));
        btn_ThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_ThanhToan.setText("Thanh toán");
        btn_ThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThanhToanActionPerformed(evt);
            }
        });

        txt_maHoaDon1.setEditable(false);

        lbl_tienkhachdua.setForeground(new java.awt.Color(255, 0, 51));
        lbl_tienkhachdua.setText(" ");

        cbGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGiamGiaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(lbl_tongThanhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addComponent(lbl_TienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel7Layout.createSequentialGroup()
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbl_thanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel7Layout.createSequentialGroup()
                                                .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(8, 8, 8))
                                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)))
                                .addGap(60, 60, 60))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_tienkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_maHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbGiamGia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(31, 31, 31))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(btn_huy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_lamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(39, 39, 39))))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_taoHoaDon)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addGap(81, 81, 81)
                            .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btn_taoHoaDon)
                    .addComponent(txt_maHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbl_tongThanhTien)
                    .addComponent(jLabel15))
                .addGap(29, 29, 29)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lbl_thanhToan)
                    .addComponent(jLabel19))
                .addGap(31, 31, 31)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_tienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(11, 11, 11)
                .addComponent(lbl_tienkhachdua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lbl_TienThua)
                    .addComponent(jLabel22))
                .addGap(27, 27, 27)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_huy)
                    .addComponent(btn_lamMoi))
                .addGap(18, 18, 18)
                .addComponent(btn_ThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThanhToanActionPerformed
        // TODO add your handling code here:
        thanhToan();
    }//GEN-LAST:event_btn_ThanhToanActionPerformed

    private void txt_searchTenSpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchTenSpFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchTenSpFocusGained

    private void tbl_bangSanPhamFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbl_bangSanPhamFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_bangSanPhamFocusGained

    private void txt_searchTenSpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchTenSpKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchTenSpKeyPressed

    private void txt_searchTenSpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchTenSpKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchTenSpKeyReleased

    private void txt_searchTenSpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchTenSpKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchTenSpKeyTyped

    private void tbl_bangSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bangSanPhamMouseClicked
        // TODO add your handling code here:
        addGioHang();
    }//GEN-LAST:event_tbl_bangSanPhamMouseClicked

    private void btn_deleteOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteOneActionPerformed
        // TODO add your handling code here:
        deleteSanPhamGioHangOne();
        loadHoaDonChiTiet();
        thanhTien();
    }//GEN-LAST:event_btn_deleteOneActionPerformed

    private void lbl_TienThuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbl_TienThuaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_TienThuaKeyPressed

    private void lbl_TienThuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbl_TienThuaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_TienThuaKeyReleased

    private void lbl_TienThuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lbl_TienThuaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_TienThuaKeyTyped

    private void txt_tienKhachDuaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tienKhachDuaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienKhachDuaKeyPressed

    private void txt_tienKhachDuaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tienKhachDuaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienKhachDuaKeyReleased

    private void txt_tienKhachDuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tienKhachDuaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tienKhachDuaKeyTyped

    private void btn_taoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taoHoaDonActionPerformed
        // TODO add your handling code here:
        taoHoaDon();
    }//GEN-LAST:event_btn_taoHoaDonActionPerformed

    private void tbl_hoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoadonMouseClicked
        // TODO add your handling code here:
        chonHoaDon();
    }//GEN-LAST:event_tbl_hoadonMouseClicked

    private void btn_huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyActionPerformed
        // TODO add your handling code here:
        huyHoaDon();
        loadDataHD001();
    }//GEN-LAST:event_btn_huyActionPerformed

    private void btn_lamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_lamMoiActionPerformed

    private void tbl_bangSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bangSanPhamMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_bangSanPhamMouseEntered

    private void tbl_hoadonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoadonMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_hoadonMouseReleased

    private void tbl_gioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_gioHangMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tbl_gioHangMouseClicked

    private void cbGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGiamGiaActionPerformed
        // TODO add your handling code here:
        thanhTien();
    }//GEN-LAST:event_cbGiamGiaActionPerformed

    private void txt_tienKhachDuaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_tienKhachDuaCaretUpdate
        // TODO add your handling code here:
        if (txt_maHoaDon1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ban Chua Chon Hoa Don");
            return;
        } else {
            tienThua();
        }
    }//GEN-LAST:event_txt_tienKhachDuaCaretUpdate

    private void btn_deleteAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteAllActionPerformed
        // TODO add your handling code here:
        deleteSanPhamGioHangAll();
        loadHoaDonChiTiet();
    }//GEN-LAST:event_btn_deleteAllActionPerformed

    private void btn_quetQRNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quetQRNVActionPerformed
        // TODO add your handling code here:
        initWebcam();
        captureThreadMaNV();
    }//GEN-LAST:event_btn_quetQRNVActionPerformed

    private void btn_quetQRKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quetQRKHActionPerformed
        // TODO add your handling code here:
        initWebcam();
        captureThreadMaKH();
    }//GEN-LAST:event_btn_quetQRKHActionPerformed

    private void btn_quetQRKH1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quetQRKH1ActionPerformed
        // TODO add your handling code here:
        stopCapture();
        searchTenKH();
    }//GEN-LAST:event_btn_quetQRKH1ActionPerformed

    private void txt_searchTenSpCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_searchTenSpCaretUpdate
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_searchTenSpCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ThanhToan;
    private javax.swing.JButton btn_deleteAll;
    private javax.swing.JButton btn_deleteOne;
    private javax.swing.JButton btn_huy;
    private javax.swing.JButton btn_lamMoi;
    private javax.swing.JButton btn_quetQRKH;
    private javax.swing.JButton btn_quetQRKH1;
    private javax.swing.JButton btn_quetQRNV;
    private javax.swing.JButton btn_taoHoaDon;
    private javax.swing.JComboBox<String> cbGiamGia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lbl_TienThua;
    private javax.swing.JLabel lbl_thanhToan;
    private javax.swing.JLabel lbl_tienkhachdua;
    private javax.swing.JLabel lbl_tongThanhTien;
    private javax.swing.JTable tbl_bangSanPham;
    private javax.swing.JTable tbl_gioHang;
    private javax.swing.JTable tbl_hoadon;
    private javax.swing.JTextField txt_maHoaDon1;
    private javax.swing.JTextField txt_maKH;
    private javax.swing.JTextField txt_maNV;
    private javax.swing.JTextArea txt_mota;
    private javax.swing.JTextField txt_searchTenSp;
    private javax.swing.JTextField txt_tenKH;
    private javax.swing.JTextField txt_tienKhachDua;
    // End of variables declaration//GEN-END:variables
}
