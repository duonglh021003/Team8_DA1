/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.duan1_n8.View;

import com.mycompany.duan1_n8.entity.PhieuGiamGia;
import com.mycompany.duan1_n8.service.Impl.PhieuGiamGiaServiceImpl;
import com.mycompany.duan1_n8.service.PhieuGiamGiaService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BuiDucMinh
 */
public class FormKhuyenMai extends javax.swing.JPanel {

    private final PhieuGiamGiaService service;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private final long date = System.currentTimeMillis();
    private final Date dateNow = new Date(date);
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private final Random random = new Random();
    private int rowSelect = 0;
    List<PhieuGiamGia> list = new ArrayList<>();

    public FormKhuyenMai() {
        initComponents();
        service = new PhieuGiamGiaServiceImpl();
        list = service.getAllPGG();
        loadData(list);
        checkData();
    }

    private void loadData(List<PhieuGiamGia> list) {
        tableModel = (DefaultTableModel) tbl_khuyenmai.getModel();
        tableModel.setRowCount(0);
        for (PhieuGiamGia pgg : list) {
            tableModel.addRow(new Object[]{
                pgg.getIdPhieuGiamGia(), pgg.getMaPhieuGiamGia(), pgg.getTenPhieuGiamGia(), pgg.getHinhThucGiamGia(), pgg.getGiaTriGiam(),
                sdf.format(pgg.getNgayBatDau()), sdf.format(pgg.getNgayKetThuc()), pgg.layTrangThai()
            });
        }
    }

    private boolean checkValidate(String check) {
        if (txt_ten.getText().trim().isEmpty() || txt_mucgiam.getText().trim().isEmpty()
                || date_batdau.getDateFormatString().trim().isEmpty() || date_ketthuc.getDateFormatString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong Duoc De Trong Thong Tin Can Them");
            return false;
        }
        try {
            Float giaTriGiam = Float.parseFloat(txt_mucgiam.getText().trim());
            if (giaTriGiam < 0) {
                JOptionPane.showMessageDialog(this, "Gia Tri Giam Phai Lon Hon 0");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Gia Tri Giam Phai La So");
            return false;
        }
        if (date_batdau.getDate().compareTo(date_ketthuc.getDate()) > 0) {
            JOptionPane.showMessageDialog(this, "Ngay Bat Dau Phai Nho Hon Ngay Ket Thuc");
            return false;
        }
        if (date_ketthuc.getDate().compareTo(dateNow) <= 0) {
            JOptionPane.showMessageDialog(this, "Ngay Ket Thuc Khong The Nam Trong Qua Khu");
            return false;
        }
        return true;
    }

    private Boolean checkTrung(String maPhieuGiamGia) {
        for (PhieuGiamGia check : service.getAllPGG()) {
            if (check.getMaPhieuGiamGia().equals(maPhieuGiamGia)) {
                return true;
            }
        }
        return false;
    }

    private PhieuGiamGia addFrom() {
        PhieuGiamGia newPhieuGiamGia = PhieuGiamGia.builder()
                .maPhieuGiamGia("KM" + random.nextInt())
                .tenPhieuGiamGia(txt_ten.getText().trim())
                .hinhThucGiamGia(cbo_hinhthuc.getSelectedItem().toString())
                .giaTriGiam(Float.parseFloat(txt_mucgiam.getText().trim()))
                .ngayBatDau(date_batdau.getDate())
                .ngayKetThuc(date_ketthuc.getDate())
                .trangThai(1)
                .build();
        System.out.println("Trang Thai: " + newPhieuGiamGia.getTrangThai());
        return newPhieuGiamGia;
    }

    private PhieuGiamGia updateFrom() {
        PhieuGiamGia updatePhieuGiamGia = PhieuGiamGia.builder()
                .idPhieuGiamGia(Long.parseLong(txt_id.getText().trim()))
                .maPhieuGiamGia(txt_makm.getText().trim())
                .tenPhieuGiamGia(txt_ten.getText().trim())
                .hinhThucGiamGia(cbo_hinhthuc.getSelectedItem().toString())
                .giaTriGiam(Float.parseFloat(txt_mucgiam.getText().trim()))
                .ngayBatDau(date_batdau.getDate())
                .ngayKetThuc(date_ketthuc.getDate())
                .trangThai(Integer.parseInt(txttrangthai.getText().trim()))
                .build();
        System.out.println("Trang Thai: " + updatePhieuGiamGia.getTrangThai());
        return updatePhieuGiamGia;
    }

    private void addData() {
        if (checkTrung(txt_makm.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Khuyen Mai Nay Da Ton Tai!!!");
            int index = JOptionPane.showConfirmDialog(this, "Ban Co Muon Them Vao Chu ?", "Thong Bao", JOptionPane.YES_NO_OPTION);
            if (index == JOptionPane.YES_OPTION) {
                String add = service.add(addFrom());
                list = service.getAllPGG();
                loadData(list);
                lamMoi();
                JOptionPane.showMessageDialog(this, add);
            } else {
                lamMoi();
            }
        } else {
            if (!checkValidate("Add")) {
                return;
            }
            String add = service.add(addFrom());
            list = service.getAllPGG();
            loadData(list);
            lamMoi();
            JOptionPane.showMessageDialog(this, add);
        }
    }

    private void lamMoi() {
        txt_id.setText("");
        txt_makm.setText("");
        txt_ten.setText("");
        cbo_hinhthuc.setSelectedIndex(0);
        txt_mucgiam.setText("");
        date_batdau.setCalendar(null);
        date_ketthuc.setCalendar(null);
        txttrangthai.setText("");
    }

    private void mouseClick() {
        rowSelect = tbl_khuyenmai.getSelectedRow();
        PhieuGiamGia indexSelect = service.getAllPGG().get(rowSelect);
        txt_id.setText(tbl_khuyenmai.getValueAt(rowSelect, 0).toString());
        txt_makm.setText(indexSelect.getMaPhieuGiamGia());
        txt_ten.setText(indexSelect.getTenPhieuGiamGia());
        cbo_hinhthuc.setSelectedItem(indexSelect.getHinhThucGiamGia());
        txt_mucgiam.setText(indexSelect.getGiaTriGiam().toString());
        date_batdau.setDate(indexSelect.getNgayBatDau());
        date_ketthuc.setDate(indexSelect.getNgayKetThuc());
        txttrangthai.setText(indexSelect.getTrangThai().toString());
    }

    private void updateData() {
        rowSelect = tbl_khuyenmai.getSelectedRow();
        if (rowSelect < 0) {
            JOptionPane.showMessageDialog(this, "Vui Long Chon Dong De Cap Nhat");
        } else {
            if (!checkValidate("update")) {
                return;
            }
            PhieuGiamGia giamGia = updateFrom();
            giamGia.setIdPhieuGiamGia(service.getAllPGG().get(rowSelect).getIdPhieuGiamGia());
            String update = service.update(giamGia);
            list = service.getAllPGG();
            loadData(list);
            lamMoi();
            JOptionPane.showMessageDialog(this, update);
            checkData();
            loadData(list);
        }
    }

    private void searchTT() {
        if (cbb_locTT.getSelectedItem().equals("Chon Trang Thai")) {
            JOptionPane.showMessageDialog(this, "Ban Da Khong Chon Trang Thai");
            list = service.getAllPGG();
            loadData(list);
        } else if (cbb_locTT.getSelectedItem().equals("Dang Bat Dau")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai Dang Bat Dau");
            loadData(service.locTrangThai(1));
        } else if (cbb_locTT.getSelectedItem().equals("Sap Dien Ra")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai Sap Dien Ra");
            loadData(service.locTrangThai(2));
        } else {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai Da Ket Thuc");
            loadData(service.locTrangThai(0));
        }
    }

    private void searchDay() {
        Date ngayBatDau = searchNgayBatDau.getDate();
        Date ngayKetThuc = searchNgayKetThuc.getDate();
        if (ngayBatDau == null && ngayKetThuc == null) {
            list = service.getAllPGG();
            loadData(list);
        } else {
            loadData(service.locNgay(ngayBatDau, ngayKetThuc));
        }
    }

    private void searchTen() {
        if (list == null) {
            return;
        } else {
            String tenPhieu = txt_searchTen.getText().trim();
            if (tenPhieu.isEmpty()) {
                list = service.getAllPGG();
            } else {
                list = service.locTen(tenPhieu);
            }
            loadData(list);
        }
    }

    private boolean checkData() {
        for (PhieuGiamGia phieuGiamGia : service.getAllPGG()) {
            // check Dang Bat Dau
            if (dateNow.compareTo(phieuGiamGia.getNgayKetThuc()) < 0 && phieuGiamGia.getTrangThai() == 0) {
                JOptionPane.showMessageDialog(this, "Da Co Khuyen Mai Nao Do Chua Den Ngay Ket Thuc");
                int luaChon = JOptionPane.showConfirmDialog(this, "Ban Co Muon Cap Nhat No Lai Chu ??? ", "Thong Bao", JOptionPane.YES_NO_OPTION);
                if (luaChon == JOptionPane.YES_OPTION) {
                    String dangBatDau = service.checkDangBatDau(phieuGiamGia);
                    loadData(list);
                    JOptionPane.showMessageDialog(this, dangBatDau);
                    return false;
                } else {
                    JOptionPane.showMessageDialog(this, "Cap Nhat That Bai Yeu Cau Thoat Chuong Trinh");
                    System.exit(0);
                }
            }
            // check Da Ket Thuc
            if (phieuGiamGia.getNgayKetThuc().compareTo(dateNow) < 0 && phieuGiamGia.getTrangThai() == 1) {
                JOptionPane.showMessageDialog(this, "Co Khuyen Mai Nao Do Da Het Han");
                int luaChon = JOptionPane.showConfirmDialog(this, "Ban Co Muon Cap Nhat No Lai Chu ??? ", "Thong Bao", JOptionPane.YES_NO_OPTION);
                if (luaChon == JOptionPane.YES_OPTION) {
                    String daKetThuc = service.checkDaKetThuc(phieuGiamGia);
                    loadData(list);
                    JOptionPane.showMessageDialog(this, daKetThuc);
                    return false;
                } else {
                    JOptionPane.showMessageDialog(this, "Cap Nhat That Bai Yeu Cau Thoat Chuong Trinh");
                    System.exit(0);
                }
            }
            // check Sap Dien Ra
            if (phieuGiamGia.getNgayBatDau().compareTo(dateNow) > 0 && phieuGiamGia.getNgayKetThuc().compareTo(dateNow) > 0) {
                JOptionPane.showMessageDialog(this, "Da Co Khuyen Mai Nao Do Hien Dang Chu Den Ngay !!!");
                int luaChon = JOptionPane.showConfirmDialog(this, "Ban Co Muon Cap Nhat No Lai Chu ??? ", "Thong Bao", JOptionPane.YES_NO_OPTION);
                if (luaChon == JOptionPane.YES_OPTION) {
                    String sapDienRa = service.checkSapDienRa(phieuGiamGia);
                    loadData(list);
                    JOptionPane.showMessageDialog(this, sapDienRa);
                    return false;
                } else {
                    JOptionPane.showMessageDialog(this, "Cap Nhat That Bai Yeu Cau Thoat Chuong Trinh");
                    System.exit(0);
                }
            }
        }
        return true;
    }

    private void lamMoiBoLoc() {
        txt_searchTen.setText("");
        searchNgayBatDau.setCalendar(null);
        searchNgayKetThuc.setCalendar(null);
        cbb_locTT.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txt_ten = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbo_hinhthuc = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txt_mucgiam = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_id = new javax.swing.JLabel();
        txt_makm = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        date_ketthuc = new com.toedter.calendar.JDateChooser();
        date_batdau = new com.toedter.calendar.JDateChooser();
        btn_lammoi = new javax.swing.JButton();
        btn_luu = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_searchTen = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        searchNgayBatDau = new com.toedter.calendar.JDateChooser();
        searchNgayKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cbb_locTT = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txttrangthai = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_khuyenmai = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chương trình khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Tạo chương trình khuyến mại");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Hình thức giảm giá");

        cbo_hinhthuc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giam gia theo %", "Giam gia theo so tien" }));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Mức giảm giá");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Mã khuyến mãi");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Id khuyến mãi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbo_hinhthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(txt_mucgiam))
                    .addComponent(txt_ten)
                    .addComponent(txt_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(204, 204, 204)
                                .addComponent(jLabel5))
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txt_makm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_makm, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbo_hinhthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_mucgiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Thời gian sử dụng");

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Thời gian bắt đầu");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Thời gian kết thúc");

        date_ketthuc.setDateFormatString("dd/MM/yyyy");

        date_batdau.setDateFormatString("dd/MM/yyyy");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(date_ketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_batdau, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_batdau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(date_ketthuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        btn_lammoi.setBackground(new java.awt.Color(153, 255, 204));
        btn_lammoi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_lammoi.setText("Làm mới");
        btn_lammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoiActionPerformed(evt);
            }
        });

        btn_luu.setBackground(new java.awt.Color(153, 255, 204));
        btn_luu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_luu.setText("Lưu");
        btn_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_luuActionPerformed(evt);
            }
        });

        btn_sua.setBackground(new java.awt.Color(153, 255, 204));
        btn_sua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Loc Du Lieu", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        jLabel1.setText("Ten Chuong Trinh:");

        txt_searchTen.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_searchTenCaretUpdate(evt);
            }
        });

        jLabel10.setText("Loc Theo Ngay");

        searchNgayBatDau.setDateFormatString("dd/MM/yyyy");

        searchNgayKetThuc.setDateFormatString("dd/MM/yyyy");
        searchNgayKetThuc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                searchNgayKetThucPropertyChange(evt);
            }
        });

        jLabel11.setText("Den");

        jLabel12.setText("Trang Thai:");

        cbb_locTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chon Trang Thai", "Dang Bat Dau", "Sap Dien Ra", "Da Ket Thuc" }));
        cbb_locTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_locTTActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("LOC DU LIEU");

        jButton1.setBackground(new java.awt.Color(51, 102, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Lam Moi Bo Loc");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel12)
                                .addGap(42, 42, 42)
                                .addComponent(cbb_locTT, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_searchTen, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(searchNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(searchNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel13)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_searchTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(searchNgayBatDau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10)
                            .addComponent(searchNgayKetThuc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(cbb_locTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_lammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(txttrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_sua, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_luu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_lammoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(54, 54, 54)
                        .addComponent(txttrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_khuyenmai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID khuyến mãi", "Mã khuyến mãi", "Tên chương trình", "Hình thức giảm giá", "Giảm giá", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ));
        tbl_khuyenmai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_khuyenmaiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_khuyenmai);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_lammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lammoiActionPerformed
        // TODO add your handling code here:
        lamMoi();
    }//GEN-LAST:event_btn_lammoiActionPerformed

    private void btn_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_luuActionPerformed
        // TODO add your handling code here:
        addData();
    }//GEN-LAST:event_btn_luuActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
        updateData();
    }//GEN-LAST:event_btn_suaActionPerformed

    private void txt_searchTenCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_searchTenCaretUpdate
        // TODO add your handling code here:
        searchTen();
    }//GEN-LAST:event_txt_searchTenCaretUpdate

    private void searchNgayKetThucPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_searchNgayKetThucPropertyChange
        // TODO add your handling code here:
        searchDay();
    }//GEN-LAST:event_searchNgayKetThucPropertyChange

    private void cbb_locTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_locTTActionPerformed
        // TODO add your handling code here:
        searchTT();
    }//GEN-LAST:event_cbb_locTTActionPerformed

    private void tbl_khuyenmaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_khuyenmaiMouseClicked
        // TODO add your handling code here:
        mouseClick();
    }//GEN-LAST:event_tbl_khuyenmaiMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        lamMoiBoLoc();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_lammoi;
    private javax.swing.JButton btn_luu;
    private javax.swing.JButton btn_sua;
    private javax.swing.JComboBox<String> cbb_locTT;
    private javax.swing.JComboBox<String> cbo_hinhthuc;
    private com.toedter.calendar.JDateChooser date_batdau;
    private com.toedter.calendar.JDateChooser date_ketthuc;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser searchNgayBatDau;
    private com.toedter.calendar.JDateChooser searchNgayKetThuc;
    private javax.swing.JTable tbl_khuyenmai;
    private javax.swing.JLabel txt_id;
    private javax.swing.JLabel txt_makm;
    private javax.swing.JTextField txt_mucgiam;
    private javax.swing.JTextField txt_searchTen;
    private javax.swing.JTextField txt_ten;
    private javax.swing.JTextField txttrangthai;
    // End of variables declaration//GEN-END:variables

}
