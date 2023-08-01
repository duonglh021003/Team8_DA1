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
import com.mycompany.duan1_n8.entity.CheckIn;
import com.mycompany.duan1_n8.entity.ChucVu;
import com.mycompany.duan1_n8.entity.DanhSachLamViec;
import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.service.CheckInService;
import com.mycompany.duan1_n8.service.DanhSachLamViecService;
import com.mycompany.duan1_n8.service.Impl.CheckInServiceImpl;
import com.mycompany.duan1_n8.service.Impl.DanhSachLamViecServiceImpl;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BXT
 */
public class FormCheckIn extends javax.swing.JPanel {

    private final CheckInService checkInService = new CheckInServiceImpl();

    private DefaultTableModel model;
    private DefaultComboBoxModel defaultComboBoxModel;
    private Webcam webcam;
    private WebcamPanel webcamPanel;
    private Thread captureThread;
    private boolean isCameraClosed = false;

    /**
     * Creates new form FormCheckIn
     */
    public FormCheckIn() {
        initComponents();

        loadData();
        txt_ngayTao.disable();
        txt_ngayTao.setText(java.time.LocalDate.now().toString());

        LocalDateTime now = LocalDateTime.now();
        Integer hour = now.getHour();
        txt_gio.disable();
        txt_gio.setText(Integer.valueOf(hour).toString());

        Integer minute = now.getMinute();
        txt_phut.disable();
        txt_phut.setText(Integer.valueOf(minute).toString());

//        Integer phutCD = 0;
//        txt_phutCD.disable();
//        txt_phutCD.setText(Integer.valueOf(phutCD).toString());
        initWebcam();
        captureThread();
    }

    public void initWebcam() {

        webcam = Webcam.getWebcams().get(0);
        Dimension size = WebcamResolution.VGA.getSize();
        webcam.setViewSize(size);
        webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setPreferredSize(size);
        webcamPanel.setFPSDisplayed(true);
        showPanel.add(webcamPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 150));

    }

    public void captureThread() {

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
                        txtMaQR.setText(result.getText());
                    }

                    count++;
                    if (count >= 100) {
                        break;
                    }
                    if (isCameraClosed) {
                        break;
                    }
                } while (true);
            }
        };
        captureThread.setDaemon(true);
        captureThread.start();
    }

    public void loadData() {
        List<CheckIn> listCheckIn = checkInService.getAll();
        model = (DefaultTableModel) tbl_checkIn.getModel();
        model.setRowCount(0);
        for (CheckIn p : listCheckIn) {
            model.addRow(new Object[]{
                p.getMa(),
                p.getNgayTao(),
                p.getGioVao() + ":" + p.getPhutVao(),
                p.getBaoCao(),
                p.getNhanVien().getMaQr(),
                p.getStatus()
            });
        }
    }

    private CheckIn getData() {
        CheckIn p = new CheckIn();

        String ma = txt_ma.getText().trim();
        p.setMa(ma);
        String NgayTao = txt_ngayTao.getText().trim();
        Date ngayTao = (Date.valueOf(NgayTao));
        p.setNgayTao(ngayTao);
        Integer gioVao = Integer.valueOf(txt_gio.getText().trim());
        p.setGioVao(gioVao);
        Integer phutVao = Integer.valueOf(txt_phut.getText().trim());
        p.setPhutVao(phutVao);
        Integer phutCoDinh = 0;
        p.setPhutCoDinh(phutCoDinh);
        String baoCao = txt_baoCao.getText().trim();
        p.setBaoCao(baoCao);
        Integer nv = Integer.valueOf(txtMaQR.getText().trim());
        NhanVien nhanVien = NhanVien.builder().maQr(nv).build();
        p.setNhanVien(nhanVien);

        return p;

    }

    public void stopCapture() {
        isCameraClosed = true;
        if (webcam != null && webcam.isOpen()) {
            webcam.close();
        }
    }

//        private void clear() {
//        txt_ma.setText("");
//        txtMaQR.setText("");
//        txt_baoCao.setText("");
//        
//        txt_gio.setText("");
//        txt_ngaySinh.setText("");
//        txt_ten.setText("");
//        txt_dienthoai.setText("");
//        txt_mk.setText("");
//
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pan_ds = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txt_moTa = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btn_them1 = new javax.swing.JButton();
        btn_checkOut = new javax.swing.JButton();
        btn_qr1 = new javax.swing.JButton();
        txt_ngayTao = new javax.swing.JTextField();
        txt_gio = new javax.swing.JTextField();
        txt_phut = new javax.swing.JTextField();
        txt_baoCao = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_ma = new javax.swing.JTextField();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_checkIn = new javax.swing.JTable();
        txtMaQR = new javax.swing.JTextField();
        btn_qr = new javax.swing.JButton();
        btn_stop = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        showPanel = new javax.swing.JPanel();

        pan_ds.setBackground(new java.awt.Color(255, 255, 255));
        pan_ds.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        pan_ds.setPreferredSize(new java.awt.Dimension(1060, 631));
        pan_ds.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pan_dsMouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Check In", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel11.setText("ngày tạo");

        jLabel12.setText("phút ");

        jLabel18.setText("giờ ");

        txt_moTa.setText("báo cáo");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_them1.setBackground(new java.awt.Color(153, 255, 204));
        btn_them1.setText("Thêm ca");
        btn_them1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them1ActionPerformed(evt);
            }
        });

        btn_checkOut.setBackground(new java.awt.Color(153, 255, 204));
        btn_checkOut.setText("Check out");
        btn_checkOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_checkOutActionPerformed(evt);
            }
        });

        btn_qr1.setBackground(new java.awt.Color(153, 255, 204));
        btn_qr1.setText("làm mới");
        btn_qr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_qr1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_checkOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_them1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(btn_qr1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btn_them1)
                .addGap(18, 18, 18)
                .addComponent(btn_qr1)
                .addGap(18, 18, 18)
                .addComponent(btn_checkOut)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jLabel13.setText("mã");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_phut, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txt_moTa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_gio, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_baoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel18)
                    .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_gio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_moTa)
                    .addComponent(txt_phut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_baoCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 92, Short.MAX_VALUE))
        );

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.setPreferredSize(new java.awt.Dimension(1034, 200));

        tbl_checkIn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ma", "ngày tạo", "giờ vào", "báo cáo", "mã qr", "trạng thái"
            }
        ));
        tbl_checkIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_checkInMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_checkIn);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("check in", jPanel12);

        txtMaQR.setEditable(false);
        txtMaQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaQRActionPerformed(evt);
            }
        });

        btn_qr.setText("quét QR");
        btn_qr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_qrActionPerformed(evt);
            }
        });

        btn_stop.setText("Stop QR");
        btn_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stopActionPerformed(evt);
            }
        });

        jLabel24.setText("mã QR");

        showPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout pan_dsLayout = new javax.swing.GroupLayout(pan_ds);
        pan_ds.setLayout(pan_dsLayout);
        pan_dsLayout.setHorizontalGroup(
            pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_dsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1036, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(pan_dsLayout.createSequentialGroup()
                .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_dsLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(showPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(226, 226, 226)
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_dsLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_stop)
                        .addGap(49, 49, 49)))
                .addComponent(btn_qr)
                .addGap(558, 558, 558))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_dsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(539, 539, 539))
        );
        pan_dsLayout.setVerticalGroup(
            pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_dsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_dsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_stop)
                            .addComponent(btn_qr)))
                    .addGroup(pan_dsLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(showPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 49, Short.MAX_VALUE)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan_ds, javax.swing.GroupLayout.DEFAULT_SIZE, 1072, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan_ds, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_them1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them1ActionPerformed
        // TODO add your handling code here:
        CheckIn p = getData();
        String query = checkInService.add(p);
        JOptionPane.showMessageDialog(this, query);
        loadData();
    }//GEN-LAST:event_btn_them1ActionPerformed

    private void btn_checkOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_checkOutActionPerformed
        // TODO add your handling code here:
        new ViewCheckOut().setVisible(true);
    }//GEN-LAST:event_btn_checkOutActionPerformed

    private void btn_qr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qr1ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_qr1ActionPerformed

    private void tbl_checkInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_checkInMouseClicked
        // TODO add your handling code here:
        int row = tbl_checkIn.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tbl_checkIn.getModel();
        txt_ngayTao.setText(model.getValueAt(row, 0).toString());
    }//GEN-LAST:event_tbl_checkInMouseClicked

    private void txtMaQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaQRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaQRActionPerformed

    private void btn_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stopActionPerformed
        // TODO add your handling code here:
        stopCapture();
    }//GEN-LAST:event_btn_stopActionPerformed

    private void btn_qrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qrActionPerformed
        // TODO add your handling code here:
        initWebcam();
        captureThread();

    }//GEN-LAST:event_btn_qrActionPerformed

    private void pan_dsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pan_dsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pan_dsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_checkOut;
    private javax.swing.JButton btn_qr;
    private javax.swing.JButton btn_qr1;
    private javax.swing.JButton btn_stop;
    private javax.swing.JButton btn_them1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JPanel pan_ds;
    private javax.swing.JPanel showPanel;
    private javax.swing.JTable tbl_checkIn;
    private javax.swing.JTextField txtMaQR;
    private javax.swing.JTextField txt_baoCao;
    private javax.swing.JTextField txt_gio;
    private javax.swing.JTextField txt_ma;
    private javax.swing.JLabel txt_moTa;
    private javax.swing.JTextField txt_ngayTao;
    private javax.swing.JTextField txt_phut;
    // End of variables declaration//GEN-END:variables
}
