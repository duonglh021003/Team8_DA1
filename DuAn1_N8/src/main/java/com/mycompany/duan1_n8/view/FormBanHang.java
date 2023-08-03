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
import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.entity.SanPham;
import com.mycompany.duan1_n8.entity.ThietKe;
import com.mycompany.duan1_n8.service.ChiTietSPService;
import com.mycompany.duan1_n8.service.DoiTuongSuDungService;
import com.mycompany.duan1_n8.service.Impl.ChiTietSPServiceImpl;
import com.mycompany.duan1_n8.service.Impl.DoiTuongSuDungServiceImpl;
import com.mycompany.duan1_n8.service.Impl.LopServiceImpl;
import com.mycompany.duan1_n8.service.Impl.MauSacServiceImpl;
import com.mycompany.duan1_n8.service.Impl.NSXServiceImpl;
import com.mycompany.duan1_n8.service.Impl.NhanVienServiceImpl;
import com.mycompany.duan1_n8.service.Impl.SanPhamServiceImpl;
import com.mycompany.duan1_n8.service.Impl.ThietKeServiceImpl;
import com.mycompany.duan1_n8.service.LopService;
import com.mycompany.duan1_n8.service.MauSacService;
import com.mycompany.duan1_n8.service.NSXService;
import com.mycompany.duan1_n8.service.NhanVienService;
import com.mycompany.duan1_n8.service.SanPhamService;
import com.mycompany.duan1_n8.service.ThietKeService;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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

    private List<SanPham> listSanPham = new ArrayList<>();
    private List<NSX> listNSX = new ArrayList<>();
    private List<MauSac> listMauSac = new ArrayList<>();
    private List<Lop> listLop = new ArrayList<>();
    private List<ThietKe> listThietKe = new ArrayList<>();
    private List<DoiTuongSuDung> listDTSD = new ArrayList<>();
    private List<ChiTietSP> listCTSP = new ArrayList<>();
    private List<NhanVien> listNhanVien = new ArrayList<>();

    private DefaultTableModel tableModel = new DefaultTableModel();
    private final DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
    private final DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
    private final String maHoaDon = null;

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

        listSanPham = sanPhamService.getAllSP();
        listNSX = nSXService.getAllNSX();
        listMauSac = mauSacService.getAllMS();
        listLop = lopService.getAllLop();
        listThietKe = thietKeService.getAllTK();
        listDTSD = dTSDService.getAllDTSD();
        listCTSP = chiTietSPService.getAllCTSP();
        listNhanVien = nhanVienService.getAll();

        loadCBB();

        for (ChiTietSP chiTietSP : chiTietSPService.getAllCTSP()) {
            if (chiTietSP.getTrangThai().equals(1) == true) {
                loadDataSP(listCTSP);
            }
        }
    }

    // load CBB
    private void loadCBB() {
        cb_khachHang.removeAllItems();

        cb_NhanVien.removeAllItems();
        for (NhanVien nhanVien : nhanVienService.getAll()) {
            cb_NhanVien.addItem(nhanVien.getTen());
        }
    }

    // loadData San Pham
    private void loadDataSP(List<ChiTietSP> listCTSP1) {
        tableModel = (DefaultTableModel) tbl_bangSanPham.getModel();
        tableModel.setRowCount(0);
        for (ChiTietSP chiTietSP : listCTSP1) {
            tableModel.addRow(new Object[]{
                chiTietSP.getSanPham().getTen(),
                decimalFormat.format(chiTietSP.getGia()),
                chiTietSP.getSoLuong(),
                chiTietSP.getMauSac().getTenMauSac(),
                chiTietSP.getNsx().getTen(),
                chiTietSP.getThietKe().getTen(),
                chiTietSP.layThongTin()
            });
        }
    }

    // Tao Hoa Don
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
        btn_xoa = new javax.swing.JButton();
        btn_xoaTat = new javax.swing.JButton();
        btn_themSL = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_bangSanPham = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_tenSP = new javax.swing.JTextField();
        cb_mau = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cb_NhanVien = new javax.swing.JComboBox<>();
        cb_khachHang = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btn_taoHoaDon = new javax.swing.JButton();
        lbl_tongThanhTien = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbl_giamGia = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbl_thanhToan = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txt_tienKhachDua = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        lbl_TienThua = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btn_huy = new javax.swing.JButton();
        btn_lamMoi = new javax.swing.JButton();
        btn_ThanhToan = new javax.swing.JButton();
        txt_maHoaDon1 = new javax.swing.JTextField();
        lbl_tienkhachdua = new javax.swing.JLabel();

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
                "Mã Hoá Đơn", "Ngày Tạo", "Mã Khách Hàng", "Mã Nhân Viên", "Tình Trạng"
            }
        ));
        tbl_hoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoadonMouseClicked(evt);
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
                "Tên Sản Phẩm", "Số Lượng", "Đơn Giá", "Giảm Giá", "Thành Tiền"
            }
        ));
        jScrollPane2.setViewportView(tbl_gioHang);

        btn_xoa.setBackground(new java.awt.Color(153, 255, 204));
        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_xoaTat.setBackground(new java.awt.Color(153, 255, 204));
        btn_xoaTat.setText("Xóa tất cả");
        btn_xoaTat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaTatActionPerformed(evt);
            }
        });

        btn_themSL.setBackground(new java.awt.Color(153, 255, 204));
        btn_themSL.setText("Thay Đổi SL");
        btn_themSL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themSLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_xoaTat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_xoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btn_themSL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btn_xoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_xoaTat)
                .addGap(18, 18, 18)
                .addComponent(btn_themSL)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_bangSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Sản Phẩm", "Đơn Giá", "Số Lượng", "Màu Sắc", "NSX", "Kích Thước", "Mo Ta"
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
        });
        jScrollPane4.setViewportView(tbl_bangSanPham);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Tìm kiếm sản phẩm:");

        txt_tenSP.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_tenSPFocusGained(evt);
            }
        });
        txt_tenSP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tenSPKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tenSPKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_tenSPKeyTyped(evt);
            }
        });

        cb_mau.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All" }));
        cb_mau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_mauActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Lọc màu sắc:");

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
                            .addComponent(txt_tenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 258, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cb_mau, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cb_mau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setText("Mã khách hàng");

        jLabel8.setText("Mã Nhân Viên");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cb_khachHang, 0, 176, Short.MAX_VALUE)
                    .addComponent(cb_NhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cb_khachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cb_NhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jLabel2.setText("Mã hóa đơn:");

        jLabel3.setText("Tổng tiền:");

        jLabel4.setText("Giảm giá:");

        jLabel10.setText("Thanh toán:");

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

        lbl_giamGia.setText("0");

        jLabel17.setText("VNĐ");

        lbl_thanhToan.setText("0");

        jLabel19.setText("VNĐ");

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

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane5.setViewportView(jTextArea1);

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
                                        .addComponent(lbl_giamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                    .addComponent(txt_maHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(btn_taoHoaDon)
                    .addComponent(txt_maHoaDon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbl_tongThanhTien)
                    .addComponent(jLabel15))
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbl_giamGia)
                    .addComponent(jLabel17))
                .addGap(34, 34, 34)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cb_mauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_mauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_mauActionPerformed

    private void btn_ThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThanhToanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThanhToanActionPerformed

    private void txt_tenSPFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_tenSPFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenSPFocusGained

    private void tbl_bangSanPhamFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tbl_bangSanPhamFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_bangSanPhamFocusGained

    private void txt_tenSPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tenSPKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenSPKeyPressed

    private void txt_tenSPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tenSPKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenSPKeyReleased

    private void txt_tenSPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tenSPKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tenSPKeyTyped

    private void tbl_bangSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bangSanPhamMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_bangSanPhamMouseClicked

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_xoaTatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaTatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_xoaTatActionPerformed

    private void btn_themSLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themSLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_themSLActionPerformed

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
    }//GEN-LAST:event_btn_taoHoaDonActionPerformed

    private void tbl_hoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoadonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_hoadonMouseClicked

    private void btn_huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_huyActionPerformed

    private void btn_lamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lamMoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_lamMoiActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ThanhToan;
    private javax.swing.JButton btn_huy;
    private javax.swing.JButton btn_lamMoi;
    private javax.swing.JButton btn_taoHoaDon;
    private javax.swing.JButton btn_themSL;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JButton btn_xoaTat;
    private javax.swing.JComboBox<String> cb_NhanVien;
    private javax.swing.JComboBox<String> cb_khachHang;
    private javax.swing.JComboBox<String> cb_mau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
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
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lbl_TienThua;
    private javax.swing.JLabel lbl_giamGia;
    private javax.swing.JLabel lbl_thanhToan;
    private javax.swing.JLabel lbl_tienkhachdua;
    private javax.swing.JLabel lbl_tongThanhTien;
    private javax.swing.JTable tbl_bangSanPham;
    private javax.swing.JTable tbl_gioHang;
    private javax.swing.JTable tbl_hoadon;
    private javax.swing.JTextField txt_maHoaDon1;
    private javax.swing.JTextField txt_tenSP;
    private javax.swing.JTextField txt_tienKhachDua;
    // End of variables declaration//GEN-END:variables
}
