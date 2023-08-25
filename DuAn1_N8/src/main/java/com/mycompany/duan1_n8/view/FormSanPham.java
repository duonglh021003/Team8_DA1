/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.duan1_n8.view;

import com.mycompany.duan1_n8.entity.ChiTietSP;
import com.mycompany.duan1_n8.entity.DoiTuongSuDung;
import com.mycompany.duan1_n8.entity.Lop;
import com.mycompany.duan1_n8.entity.MauSac;
import com.mycompany.duan1_n8.entity.NSX;
import com.mycompany.duan1_n8.entity.SanPham;
import com.mycompany.duan1_n8.entity.ThietKe;
import com.mycompany.duan1_n8.service.ChiTietSPService;
import com.mycompany.duan1_n8.service.DoiTuongSuDungService;
import com.mycompany.duan1_n8.service.Impl.ChiTietSPServiceImpl;
import com.mycompany.duan1_n8.service.Impl.DoiTuongSuDungServiceImpl;
import com.mycompany.duan1_n8.service.Impl.LopServiceImpl;
import com.mycompany.duan1_n8.service.Impl.MauSacServiceImpl;
import com.mycompany.duan1_n8.service.Impl.NSXServiceImpl;
import com.mycompany.duan1_n8.service.Impl.SanPhamServiceImpl;
import com.mycompany.duan1_n8.service.Impl.ThietKeServiceImpl;
import com.mycompany.duan1_n8.service.LopService;
import com.mycompany.duan1_n8.service.MauSacService;
import com.mycompany.duan1_n8.service.NSXService;
import com.mycompany.duan1_n8.service.SanPhamService;
import com.mycompany.duan1_n8.service.ThietKeService;
import java.awt.Color;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author anhtu
 */
public class FormSanPham extends javax.swing.JPanel {

    /**
     * Creates new form FormSanPham
     */
    private JPanel paneTC;
    private final SanPhamService sanPhamService;
    private final NSXService nSXService;
    private final MauSacService mauSacService;
    private final LopService lopService;
    private final ThietKeService thietKeService;
    private final DoiTuongSuDungService dTSDService;
    private final ChiTietSPService chiTietSPService;
    private List<SanPham> listSanPham = new ArrayList<>();
    private List<NSX> listNSX = new ArrayList<>();
    private List<MauSac> listMauSac = new ArrayList<>();
    private List<Lop> listLop = new ArrayList<>();
    private List<ThietKe> listThietKe = new ArrayList<>();
    private List<DoiTuongSuDung> listDTSD = new ArrayList<>();
    private List<ChiTietSP> listCTSP = new ArrayList<>();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
    private int rowSelect = 0;
    private final DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    private final Random random = new Random();
    private final long date = System.currentTimeMillis();
    private final Date dateNow = new Date(date);
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public FormSanPham() {
        initComponents();
        sanPhamService = new SanPhamServiceImpl();
        nSXService = new NSXServiceImpl();
        mauSacService = new MauSacServiceImpl();
        lopService = new LopServiceImpl();
        thietKeService = new ThietKeServiceImpl();
        dTSDService = new DoiTuongSuDungServiceImpl();
        chiTietSPService = new ChiTietSPServiceImpl();

        listSanPham = sanPhamService.getAllSP();
        listNSX = nSXService.getAllNSX();
        listMauSac = mauSacService.getAllMS();
        listLop = lopService.getAllLop();
        listThietKe = thietKeService.getAllTK();
        listDTSD = dTSDService.getAllDTSD();
        listCTSP = chiTietSPService.getAllCTSP();

        addCBB();
        loadData(listCTSP);
    }

    private void addCBB() {
        cbbSanPham.removeAllItems();
        for (SanPham sanPham : sanPhamService.getAllSP()) {
            cbbSanPham.addItem(sanPham.getTen());
        }
        cbbNSX.removeAllItems();
        for (NSX nsx : nSXService.getAllNSX()) {
            cbbNSX.addItem(nsx.getTen());
        }
        cbbMauSac.removeAllItems();
        for (MauSac mauSac : mauSacService.getAllMS()) {
            cbbMauSac.addItem(mauSac.getTenMauSac());
        }
        cbbLop.removeAllItems();
        for (Lop lop : lopService.getAllLop()) {
            cbbLop.addItem(lop.getSoLuong().toString());
        }
        cbbThietKe.removeAllItems();
        for (ThietKe thietKe : thietKeService.getAllTK()) {
            cbbThietKe.addItem(thietKe.getTen());
        }
        cbbDTSD.removeAllItems();
        for (DoiTuongSuDung doiTuongSuDung : dTSDService.getAllDTSD()) {
            cbbDTSD.addItem(doiTuongSuDung.getTen());
        }
    }

    private void loadData(List<ChiTietSP> listCTSP1) {
        tableModel = (DefaultTableModel) tableCTSP.getModel();
        tableModel.setRowCount(0);
        for (ChiTietSP chiTietSP : listCTSP1) {
            tableModel.addRow(new Object[]{
                chiTietSP.getMaQr(),
                chiTietSP.getSanPham().getTen(),
                chiTietSP.getNsx().getTen(),
                chiTietSP.getMauSac().getTenMauSac(),
                chiTietSP.getLop().getSoLuong(),
                chiTietSP.getThietKe().getTen(),
                chiTietSP.getDoiTuongSuDung().getTen(),
                sdf.format(chiTietSP.getNgaySanXuat()),
                sdf.format(chiTietSP.getHanSuDung()),
                chiTietSP.layThongTin(),
                chiTietSP.getSoLuong(),
                decimalFormat.format(chiTietSP.getGia()),
                chiTietSP.layTrangThai()
            });
        }
    }

    private void mouseClick() {
        rowSelect = tableCTSP.getSelectedRow();
        ChiTietSP indexSelect = chiTietSPService.getAllCTSP().get(rowSelect);
        txtId.setText(tableCTSP.getValueAt(rowSelect, 0).toString());
        cbbSanPham.setSelectedItem(indexSelect.getSanPham().getTen());
        cbbNSX.setSelectedItem(indexSelect.getNsx().getTen());
        cbbMauSac.setSelectedItem(indexSelect.getMauSac().getTenMauSac());
        cbbLop.setSelectedItem(indexSelect.getLop().getSoLuong());
        cbbThietKe.setSelectedItem(indexSelect.getThietKe().getTen());
        cbbDTSD.setSelectedItem(indexSelect.getDoiTuongSuDung().getTen());
        dateNgaySanXuat.setDate(indexSelect.getNgaySanXuat());
        dateHanSuDung.setDate(indexSelect.getHanSuDung());
        txtMoTa.setText(indexSelect.getMoTa());
        txtSoLuong.setText(indexSelect.getSoLuong().toString());
        txtGia.setText(indexSelect.getGia().toString());
        int trangThai = indexSelect.getTrangThai();
        if (trangThai == 1) {
            radConHang.setSelected(true);
        } else {
            radHetHang.setSelected(true);
        }
    }

    private boolean checkValidate(String check) {
        if (dateNgaySanXuat.getDateFormatString().trim().isEmpty() || dateHanSuDung.getDateFormatString().trim().isEmpty()
                || txtSoLuong.getText().trim().isEmpty() || txtGia.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Thong Tin Khong Duoc De Trong");
            return false;
        }

        if (dateNgaySanXuat.getDate().compareTo(dateHanSuDung.getDate()) > 0) {
            JOptionPane.showMessageDialog(this, "Ngay San Xuat Phai Lon Hon Han Su Dung");
            return false;
        }

        try {
            int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
            if (soLuong < 0) {
                JOptionPane.showMessageDialog(this, "So Luong Khong The Nho Hon 0");
                return false;
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "So Luong Phai La So");
            return false;
        }

        try {
            Float gia = Float.parseFloat(txtGia.getText().trim());
            if (gia < 0) {
                JOptionPane.showMessageDialog(this, "Gia Phai Lon Hon 0");
                return false;
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, "Gia San Pham Phai La So");
            return false;
        }
        return true;
    }

    private boolean checkTrung(String maQR) {
        for (ChiTietSP chiTietSP : chiTietSPService.getAllCTSP()) {
            if (Objects.equals(chiTietSP.getMaQr(), maQR)) {
                return true;
            }
        }
        return false;
    }

    private ChiTietSP docForm() {
        ChiTietSP newCTSP = ChiTietSP.builder()
                .maQr(random.nextLong())
                .sanPham(sanPhamService.getAllSP().get(cbbSanPham.getSelectedIndex()))
                .nsx(nSXService.getAllNSX().get(cbbNSX.getSelectedIndex()))
                .mauSac(mauSacService.getAllMS().get(cbbMauSac.getSelectedIndex()))
                .lop(lopService.getAllLop().get(cbbLop.getSelectedIndex()))
                .thietKe(thietKeService.getAllTK().get(cbbThietKe.getSelectedIndex()))
                .doiTuongSuDung(dTSDService.getAllDTSD().get(cbbDTSD.getSelectedIndex()))
                .ngaySanXuat(dateNgaySanXuat.getDate())
                .hanSuDung(dateHanSuDung.getDate())
                .moTa(txtMoTa.getText().trim())
                .soLuong(Integer.parseInt(txtSoLuong.getText().trim()))
                .gia(new BigDecimal(txtGia.getText().trim()))
                .trangThai(1)
                .build();
        System.out.println("Add Form Thanh Cong");
        return newCTSP;
    }

    private ChiTietSP updateForm() {
        ChiTietSP updateCTSP = ChiTietSP.builder()
                .maQr(Long.parseLong(txtId.getText().trim()))
                .sanPham(sanPhamService.getAllSP().get(cbbSanPham.getSelectedIndex()))
                .nsx(nSXService.getAllNSX().get(cbbNSX.getSelectedIndex()))
                .mauSac(mauSacService.getAllMS().get(cbbMauSac.getSelectedIndex()))
                .lop(lopService.getAllLop().get(cbbLop.getSelectedIndex()))
                .thietKe(thietKeService.getAllTK().get(cbbThietKe.getSelectedIndex()))
                .doiTuongSuDung(dTSDService.getAllDTSD().get(cbbDTSD.getSelectedIndex()))
                .ngaySanXuat(dateNgaySanXuat.getDate())
                .hanSuDung(dateHanSuDung.getDate())
                .moTa(txtMoTa.getText().trim())
                .soLuong(Integer.parseInt(txtSoLuong.getText().trim()))
                .gia(new BigDecimal(txtGia.getText().trim()))
                .trangThai(radConHang.isSelected() ? 1 : 0)
                .build();
        System.out.println("Update Form Thanh Cong");
        return updateCTSP;
    }

    private void lamMoi() {
        txtId.setText("");
        cbbSanPham.setSelectedIndex(0);
        cbbNSX.setSelectedIndex(0);
        cbbMauSac.setSelectedIndex(0);
        cbbLop.setSelectedIndex(0);
        cbbThietKe.setSelectedIndex(0);
        cbbDTSD.setSelectedIndex(0);
        dateNgaySanXuat.setCalendar(null);
        dateHanSuDung.setCalendar(null);
        txtMoTa.setText("");
        txtSoLuong.setText("");
        txtGia.setText("");
        radConHang.isSelected();
    }

    private void addData() {
        if (checkTrung(txtId.getText().trim())) {
            JOptionPane.showMessageDialog(this, "San Pham Nay Da Ton Tai");
            int index = JOptionPane.showConfirmDialog(this, "Ban Co Muon Them San Pham Vao?", "Thong Bao", JOptionPane.YES_NO_OPTION);
            if (index == JOptionPane.YES_OPTION) {
                String add = chiTietSPService.addSanPham(docForm());
                listCTSP = chiTietSPService.getAllCTSP();
                loadData(listCTSP);
                lamMoi();
                JOptionPane.showMessageDialog(this, add);
            } else {

            }
        } else {
            if (!checkValidate("add")) {
                return;
            } else {
                String add = chiTietSPService.addSanPham(docForm());
                listCTSP = chiTietSPService.getAllCTSP();
                loadData(listCTSP);
                lamMoi();
                JOptionPane.showMessageDialog(this, add);
            }
        }
    }

    private void updateData() {
        rowSelect = tableCTSP.getSelectedRow();
        if (rowSelect < 0) {
            JOptionPane.showMessageDialog(this, "Vui Long Chon Dong Cap Nhat O Bang Con Hang");
            return;
        } else {
            ChiTietSP ctsp = updateForm();
            ctsp.setMaQr(chiTietSPService.getAllCTSP().get(rowSelect).getMaQr());
            String update = chiTietSPService.updateSanPham(ctsp);
            listCTSP = chiTietSPService.getAllCTSP();
            loadData(listCTSP);
            lamMoi();
            JOptionPane.showMessageDialog(this, update);
        }
    }

    private void deleteData() {
        rowSelect = tableCTSP.getSelectedRow();
        if (rowSelect < 0) {
            JOptionPane.showMessageDialog(this, "Vui Long Chon Dong De Xoa");
            return;
        } else {
            int index = JOptionPane.showConfirmDialog(this, "Ban Co Muon Xoa Di Khong", "Thong Bao", JOptionPane.YES_NO_OPTION);
            if (index == JOptionPane.YES_OPTION) {
                ChiTietSP indexDelete = new ChiTietSP();
                indexDelete.setMaQr(chiTietSPService.getAllCTSP().get(rowSelect).getMaQr());
                String delete = chiTietSPService.deleteSanPham(indexDelete);
                listCTSP = chiTietSPService.getAllCTSP();
                loadData(listCTSP);
                lamMoi();
                JOptionPane.showMessageDialog(this, delete);
            } else {
                lamMoi();
            }
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        msgTen = new javax.swing.JLabel();
        radConHang = new javax.swing.JRadioButton();
        radHetHang = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        txtSoLuong = new javax.swing.JTextField();
        dateNgaySanXuat = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cbbDTSD = new javax.swing.JComboBox<>();
        cbbThietKe = new javax.swing.JComboBox<>();
        cbbLop = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbNSX = new javax.swing.JComboBox<>();
        dateHanSuDung = new com.toedter.calendar.JDateChooser();
        cbbSanPham = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtId = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        cbxHDH = new javax.swing.JComboBox<>();
        txtTimKiem = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cbxMauSac = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableCTSP = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        formThuocTinh = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        rdo_sanPham = new javax.swing.JRadioButton();
        rdo_lop = new javax.swing.JRadioButton();
        rdo_nsx = new javax.swing.JRadioButton();
        rdo_mauSac = new javax.swing.JRadioButton();
        rdo_dtsd = new javax.swing.JRadioButton();
        rdo_thietKe = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1050, 650));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("San Pham");

        jLabel2.setText("Nha San Xuat");

        jLabel6.setText("So Luong");

        jLabel8.setText("Ngay San Xuat");

        jLabel11.setText("Han Su Dung");

        jLabel12.setText("Mo Ta");

        msgTen.setText(" ");

        buttonGroup2.add(radConHang);
        radConHang.setSelected(true);
        radConHang.setText("Còn hàng");

        buttonGroup2.add(radHetHang);
        radHetHang.setText("Hết hàng");

        jLabel9.setText("Mau Sac");

        jLabel3.setText("Lop");

        jLabel5.setText("Thiet Ke");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane3.setViewportView(txtMoTa);

        jLabel4.setText("Doi Tuong Su Dung");

        jLabel7.setText("Gia");

        jLabel10.setText("Trang Thai");

        dateNgaySanXuat.setDateFormatString("dd/MM/yyyy");

        jLabel14.setText("Chiec");

        jLabel15.setText("VND");

        dateHanSuDung.setDateFormatString("dd/MM/yyyy");

        jLabel13.setText("QR Code");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(msgTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(264, 264, 264))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbThietKe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbDTSD, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(52, 52, 52)
                                .addComponent(cbbLop, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(42, 42, 42))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(37, 37, 37)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbbNSX, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(42, 42, 42))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbbMauSac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(42, 42, 42))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbbSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(42, 42, 42))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(40, 40, 40)))))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel14))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(radConHang)
                                        .addGap(39, 39, 39)
                                        .addComponent(radHetHang)
                                        .addGap(15, 15, 15))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addComponent(jLabel15)))
                        .addGap(4, 4, 4))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateHanSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateNgaySanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(dateNgaySanXuat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(dateHanSuDung, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel12)))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radConHang)
                    .addComponent(radHetHang)
                    .addComponent(jLabel10)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(21, 21, 21)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbNSX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(msgTen)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbbThietKe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbbDTSD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc sản phẩm"));

        jLabel20.setText("Hệ điều hành");

        cbxHDH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cbxHDH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxHDHActionPerformed(evt);
            }
        });

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });
        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyTyped(evt);
            }
        });

        jLabel21.setText("Tìm kiếm sản phẩm");

        jLabel22.setText("Màu Sắc");

        cbxMauSac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cbxMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxMauSacActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(38, 38, 38)
                .addComponent(cbxHDH, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(37, 37, 37)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jLabel22)
                .addGap(26, 26, 26)
                .addComponent(cbxMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxHDH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel22)
                    .addComponent(cbxMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setBackground(new java.awt.Color(153, 255, 204));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(153, 255, 204));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(153, 255, 204));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(153, 255, 204));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        tableCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "QR Code", "San Pham", "Nha San Xuat", "Mau Sac", "Lop", "Thiet Ke", "Doi Tuong Su Dung", "Ngay San Xuat", "Han Su Dung", "Mo Ta", "So Luong", "Gia", "Trang Thai"
            }
        ));
        tableCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCTSPMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableCTSP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 966, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(21, 21, 21))))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sản phẩm", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        formThuocTinh.setBackground(new java.awt.Color(255, 255, 255));
        formThuocTinh.setLayout(new java.awt.BorderLayout());

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rdo_sanPham.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(rdo_sanPham);
        rdo_sanPham.setText("San Pham");
        rdo_sanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_sanPhamActionPerformed(evt);
            }
        });

        rdo_lop.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(rdo_lop);
        rdo_lop.setText("Lop");
        rdo_lop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_lopActionPerformed(evt);
            }
        });

        rdo_nsx.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(rdo_nsx);
        rdo_nsx.setText("NSX");
        rdo_nsx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_nsxActionPerformed(evt);
            }
        });

        rdo_mauSac.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(rdo_mauSac);
        rdo_mauSac.setText("Màu Sac");
        rdo_mauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_mauSacActionPerformed(evt);
            }
        });

        rdo_dtsd.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup3.add(rdo_dtsd);
        rdo_dtsd.setText("Doi Tuong Su Dung");
        rdo_dtsd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_dtsdActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdo_thietKe);
        rdo_thietKe.setText("Thiet Ke");
        rdo_thietKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_thietKeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdo_sanPham)
                    .addComponent(rdo_nsx)
                    .addComponent(rdo_dtsd))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdo_mauSac)
                    .addComponent(rdo_lop)
                    .addComponent(rdo_thietKe))
                .addGap(16, 16, 16))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_sanPham)
                    .addComponent(rdo_lop))
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_nsx)
                    .addComponent(rdo_mauSac))
                .addGap(31, 31, 31)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdo_dtsd)
                    .addComponent(rdo_thietKe))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 747, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(formThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(381, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc tính sản phẩm", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKeyTyped

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        lamMoi();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        deleteData();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        updateData();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        addData();
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKeyPressed

    private void txtTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemKeyReleased

    private void cbxHDHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxHDHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxHDHActionPerformed

    private void cbxMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxMauSacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMauSacActionPerformed

    private void rdo_sanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_sanPhamActionPerformed
        // TODO add your handling code here:
        setpanal(new ViewSanPham());
    }//GEN-LAST:event_rdo_sanPhamActionPerformed

    private void rdo_lopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_lopActionPerformed
        // TODO add your handling code here:
        setpanal(new ViewLop());
    }//GEN-LAST:event_rdo_lopActionPerformed

    private void rdo_nsxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_nsxActionPerformed
        // TODO add your handling code here:
        setpanal(new ViewNSX());
    }//GEN-LAST:event_rdo_nsxActionPerformed

    private void rdo_mauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_mauSacActionPerformed
        // TODO add your handling code here:
        setpanal(new ViewMauSac());
    }//GEN-LAST:event_rdo_mauSacActionPerformed

    private void rdo_dtsdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_dtsdActionPerformed
        // TODO add your handling code here:
        setpanal(new ViewDoiTuongSuDung());
    }//GEN-LAST:event_rdo_dtsdActionPerformed

    private void rdo_thietKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_thietKeActionPerformed
        // TODO add your handling code here:
        setpanal(new ViewThietKe());
    }//GEN-LAST:event_rdo_thietKeActionPerformed

    private void tableCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCTSPMouseClicked
        // TODO add your handling code here:
        mouseClick();
    }//GEN-LAST:event_tableCTSPMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbbDTSD;
    private javax.swing.JComboBox<String> cbbLop;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbNSX;
    private javax.swing.JComboBox<String> cbbSanPham;
    private javax.swing.JComboBox<String> cbbThietKe;
    private javax.swing.JComboBox<String> cbxHDH;
    private javax.swing.JComboBox<String> cbxMauSac;
    private com.toedter.calendar.JDateChooser dateHanSuDung;
    private com.toedter.calendar.JDateChooser dateNgaySanXuat;
    private javax.swing.JPanel formThuocTinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel msgTen;
    private javax.swing.JRadioButton radConHang;
    private javax.swing.JRadioButton radHetHang;
    private javax.swing.JRadioButton rdo_dtsd;
    private javax.swing.JRadioButton rdo_lop;
    private javax.swing.JRadioButton rdo_mauSac;
    private javax.swing.JRadioButton rdo_nsx;
    private javax.swing.JRadioButton rdo_sanPham;
    private javax.swing.JRadioButton rdo_thietKe;
    private javax.swing.JTable tableCTSP;
    private javax.swing.JTextField txtGia;
    private javax.swing.JLabel txtId;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
    private void setColor(JPanel pane) {
        pane.setBackground(new Color(204, 204, 204));
    }

    private void resetColor(JPanel[] pane) {
        for (JPanel pane1 : pane) {
            pane1.setBackground(new Color(153, 255, 204));
        }
    }

    private void setpanal(JPanel panel) {
        paneTC = panel;
        formThuocTinh.removeAll();
        formThuocTinh.add(paneTC);
        formThuocTinh.validate();
    }
}
