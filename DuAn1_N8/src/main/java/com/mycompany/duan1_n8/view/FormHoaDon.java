/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.duan1_n8.view;

import com.mycompany.duan1_n8.entity.HoaDon;
import com.mycompany.duan1_n8.entity.HoaDonChiTiet;
import com.mycompany.duan1_n8.repository.HoaDonChiTietRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BuiDucMinh
 */
public class FormHoaDon extends javax.swing.JPanel {

    private HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();

    private DefaultTableModel tableModel = new DefaultTableModel();

    private int indexHoaDon = 0;
    private int indexSanPham = 0;
    private int indexHoaDonChiTiet = 0;

    public FormHoaDon() {
        initComponents();
        loadDataHD();

        if (tbl_hoadon.getRowCount() > 0) {
            tbl_hoadon.setRowSelectionInterval(0, 0);
            int selectedRowIndex = tbl_hoadon.getSelectedRow();
            Object value = tbl_hoadon.getValueAt(selectedRowIndex, 0);
            loadHoaDonChiTiet();
        }
    }

    private void loadDataHD() {

        List<HoaDon> listHD = hoaDonChiTietRepository.getAllHoaDon();

        Collections.sort(listHD, Comparator.comparingLong(HoaDon::getIdHoaDon).reversed());
        tableModel = (DefaultTableModel) tbl_hoadon.getModel();
        tableModel.setRowCount(0);

        for (HoaDon hd : listHD) {
            tableModel.addRow(new Object[]{
                hd.getIdHoaDon(),
                hd.getMaHoaDon(),
                hd.getNgayTao(),
                hd.getTongTienHoaDon(),
                hd.getThanhTien(),
                hd.getTienKhachDua(),
                hd.getTienThua(),
                hd.getNhanVien().getMa(),
                hd.getNhanVien().getTen(),
                hd.getKhachHang().getMaKH(),
                hd.getKhachHang().getTenKH(),
                hd.getMoTa(),
                hd.layTrangThaiHD()

            });
        }

    }

    private void loadHoaDonChiTiet() {
        int rowHD = this.tbl_hoadon.getSelectedRow();

        if (rowHD == -1) {
            return;
        }
        Long hoaDon = Long.valueOf(this.tbl_hoadon.getValueAt(rowHD, 0).toString());

        tableModel = (DefaultTableModel) this.tbl_hdct.getModel();
        tableModel.setRowCount(0);
        for (HoaDonChiTiet hdct : hoaDonChiTietRepository.getAll()) {
            if (hdct.getIdHoaDonChiTiet().getHoaDon().getIdHoaDon().equals(hoaDon)) {
                tableModel.addRow(new Object[]{
                    hdct.getIdHoaDonChiTiet().getHoaDon().getIdHoaDon(),
                    hdct.getIdHoaDonChiTiet().getHoaDon().getMaHoaDon(),
                    hdct.getIdHoaDonChiTiet().getChiTietSP().getMaQr(),
                    hdct.getIdHoaDonChiTiet().getChiTietSP().getSanPham().getTen(),
                    hdct.getSoLuong(),
                    hdct.getDonGia(),
                    hdct.getTongTien(),});
            }
        }
    }

    private void locTrangThai() {
        List<HoaDon> list = new ArrayList<>();
        String cbo = cbo_timtrangthai.getSelectedItem().toString();
        System.out.println("ccccccccccccccc     " + cbo);
        for (HoaDon p : hoaDonChiTietRepository.getAllHoaDon()) {
            if (String.valueOf(p.layTrangThaiHD()).equals(cbo)) {
                list.add(p);
            }
        }

        tableModel = (DefaultTableModel) tbl_hoadon.getModel();
        tableModel.setRowCount(0);
        for (HoaDon hd : list) {
            tableModel.addRow(new Object[]{
                hd.getIdHoaDon(),
                hd.getMaHoaDon(),
                hd.getNgayTao(),
                hd.getTongTienHoaDon(),
                hd.getThanhTien(),
                hd.getTienKhachDua(),
                hd.getTienThua(),
                hd.getNhanVien().getMa(),
                hd.getNhanVien().getTen(),
                hd.getKhachHang().getMaKH(),
                hd.getKhachHang().getTenKH(),
                hd.getMoTa(),
                hd.layTrangThaiHD()});
        }

    }

    private void searchTrangThai() {
        if (cbo_timtrangthai.getSelectedItem().equals("Chon Trang Thai")) {
            JOptionPane.showMessageDialog(this, "Ban Da Khong Chon Trang Thai");
            loadDataHD();
        } else if (cbo_timtrangthai.getSelectedItem().equals("Da Thanh Toan")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai Da Thanh Toan");
            locTrangThai();
        } else if (cbo_timtrangthai.getSelectedItem().equals("Da Huy")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai Da Huy");
            locTrangThai();
        }else if (cbo_timtrangthai.getSelectedItem().equals("Dang Cho Thanh Toan")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai Dang Cho Thanh Toan");
            locTrangThai();
        }
    }

    private void searchMaHoaDon() {

        List<HoaDon> list = hoaDonChiTietRepository.getAllHoaDon();

        String maHD = txt_timMaHD.getText();
        tableModel = (DefaultTableModel) tbl_hoadon.getModel();
        tableModel.setRowCount(0);

        for (HoaDon hd : list) {
            if (hd.getMaHoaDon().equals(maHD)) {
                tableModel.addRow(new Object[]{
                    hd.getIdHoaDon(),
                    hd.getMaHoaDon(),
                    hd.getNgayTao(),
                    hd.getTongTienHoaDon(),
                    hd.getThanhTien(),
                    hd.getTienKhachDua(),
                    hd.getTienThua(),
                    hd.getNhanVien().getMa(),
                    hd.getNhanVien().getTen(),
                    hd.getKhachHang().getMaKH(),
                    hd.getKhachHang().getTenKH(),
                    hd.getMoTa(),
                    hd.layTrangThaiHD()});
            }
        }

        if (maHD.isEmpty()) {
            loadDataHD();
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

        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbo_timtrangthai = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_hoadon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txt_timMaHD = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_hdct = new javax.swing.JTable();

        jTextField1.setText("jTextField1");

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setText("Trạng thái thanh toán");

        cbo_timtrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chon Trang Thai", "Da Thanh Toan", "Da Huy", "Dang Cho Thanh Toan" }));
        cbo_timtrangthai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_timtrangthaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(cbo_timtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jLabel2)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_timtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tbl_hoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Mã", "Ngày tạo", "Tổng tiền", "Thành tiền", "tiền khách đưa", "tiền thừa", "Mã NV", "Tên NV", "Mã KH", "Tên KH", "ghi chú", "trạng thái"
            }
        ));
        tbl_hoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoadonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_hoadon);

        jLabel1.setText("Tìm kiếm hóa đơn");

        txt_timMaHD.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_timMaHDCaretUpdate(evt);
            }
        });
        txt_timMaHD.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_timMaHDInputMethodTextChanged(evt);
            }
        });
        txt_timMaHD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_timMaHDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timMaHDKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_timMaHDKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(185, 185, 185)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_timMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_timMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tbl_hdct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id hoá đơn", "Mã hóa đơn chi tiết", "Mã sản phẩm", "Tên sản phẩm", "Số lượng ", "Đơn giá", "tổng tiền"
            }
        ));
        jScrollPane3.setViewportView(tbl_hdct);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 981, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 969, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 206, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cbo_timtrangthaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_timtrangthaiActionPerformed
        // TODO add your handling code here:
        searchTrangThai();
    }//GEN-LAST:event_cbo_timtrangthaiActionPerformed

    private void tbl_hoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoadonMouseClicked
        // TODO add your handling code here:
        loadHoaDonChiTiet();
    }//GEN-LAST:event_tbl_hoadonMouseClicked

    private void txt_timMaHDInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_timMaHDInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timMaHDInputMethodTextChanged

    private void txt_timMaHDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timMaHDKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timMaHDKeyPressed

    private void txt_timMaHDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timMaHDKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timMaHDKeyReleased

    private void txt_timMaHDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timMaHDKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timMaHDKeyTyped

    private void txt_timMaHDCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_timMaHDCaretUpdate
        // TODO add your handling code here:
        searchMaHoaDon();
    }//GEN-LAST:event_txt_timMaHDCaretUpdate

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbo_timtrangthai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tbl_hdct;
    private javax.swing.JTable tbl_hoadon;
    private javax.swing.JTextField txt_timMaHD;
    // End of variables declaration//GEN-END:variables
}
