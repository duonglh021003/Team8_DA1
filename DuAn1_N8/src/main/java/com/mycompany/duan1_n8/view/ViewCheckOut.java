/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
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
import com.mycompany.duan1_n8.entity.CheckOut;
import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.repository.CheckOutRepository;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BXT
 */
public class ViewCheckOut extends javax.swing.JFrame {

    private final CheckOutRepository checkOutRepository = new CheckOutRepository();

    /**
     * Creates new form ViewCheckOut
     */
    private DefaultTableModel model;
    private DefaultComboBoxModel defaultComboBoxModel;
    private Webcam webcam;
    private WebcamPanel webcamPanel;
    private Thread captureThread;
    private boolean isCameraClosed = false;
    
    public ViewCheckOut() {
        initComponents();
        txt_ngayTao.disable();
        txt_ngayTao.setText(java.time.LocalDate.now().toString());
        LocalDateTime now = LocalDateTime.now();
        Integer hour = now.getHour();
        txt_gio.disable();
        txt_gio.setText(Integer.valueOf(hour).toString());

        Integer minute = now.getMinute();
        txt_phutRa.disable();
        txt_phutRa.setText(Integer.valueOf(minute).toString());
        loadData();
        setLocationRelativeTo(null);
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
        List<CheckOut> listCheckOut = checkOutRepository.getAll();
        model = (DefaultTableModel) tbl_checkIn.getModel();
        model.setRowCount(0);
        for (CheckOut p : listCheckOut) {
            model.addRow(new Object[]{
                p.getMa(),
                p.getNgayTao(),
                p.getGioRa() + ":" + p.getPhutRa(),
                p.getBaoCao(),
                p.getNhanVien().getMaQr(),
                p.getGhiChu()
            });
        }
    }

    private CheckOut getData() {
        CheckOut p = new CheckOut();

        String ma = txt_ma.getText().trim();
        p.setMa(ma);
        String NgayTao = txt_ngayTao.getText().trim();
        Date ngayTao = (Date.valueOf(NgayTao));
        p.setNgayTao(ngayTao);
        Integer gioRa = Integer.valueOf(txt_gio.getText().trim());
        p.setGioRa(gioRa);
        Integer phutRa = Integer.valueOf(txt_phutRa.getText().trim());
        p.setPhutRa(phutRa);
        String baoCao = txt_baoCao.getText().trim();
        p.setBaoCao(baoCao);
        String ghiChu = txt_ghiChu.getText().trim();
        p.setGhiChu(ghiChu);
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
        jLabel18 = new javax.swing.JLabel();
        txt_moTa = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btn_them1 = new javax.swing.JButton();
        btn_qr1 = new javax.swing.JButton();
        txt_ngayTao = new javax.swing.JTextField();
        txt_gio = new javax.swing.JTextField();
        txt_baoCao = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_ma = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_ghiChu = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_phutRa = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        txt_timsdt1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_checkIn = new javax.swing.JTable();
        txtMaQR = new javax.swing.JTextField();
        btn_qr = new javax.swing.JButton();
        btn_stop = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        showPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jLabel18.setText("giờ ra");

        txt_moTa.setText("báo cáo");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_them1.setBackground(new java.awt.Color(153, 255, 204));
        btn_them1.setText("check out");
        btn_them1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them1ActionPerformed(evt);
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
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_them1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(btn_qr1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btn_them1)
                .addGap(18, 18, 18)
                .addComponent(btn_qr1)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        txt_ngayTao.setEditable(false);

        txt_gio.setEditable(false);

        jLabel13.setText("mã");

        jLabel14.setText("ghi chú");

        jLabel19.setText("phút ra");

        txt_phutRa.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_phutRa, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txt_moTa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_ghiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_gio, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_baoCao, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel18)
                            .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_gio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_moTa)
                            .addComponent(txt_baoCao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(txt_phutRa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txt_ghiChu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txt_timsdt1.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_timsdt1InputMethodTextChanged(evt);
            }
        });
        txt_timsdt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_timsdt1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timsdt1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_timsdt1KeyTyped(evt);
            }
        });

        jLabel23.setText("Tìm theo số điện thoại");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(0, 304, Short.MAX_VALUE))
                    .addComponent(txt_timsdt1))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_timsdt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
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
                "ma", "ngày tạo", "giờ vào", "báo cáo", "mã qr", "ghi chú"
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
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Đang làm việc", jPanel12);

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
                    .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_dsLayout.createSequentialGroup()
                .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(65, 65, 65))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_dsLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)))
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        pan_dsLayout.setVerticalGroup(
            pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_dsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_dsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_dsLayout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(18, 18, 18)
                                .addComponent(txtMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_stop)
                                    .addComponent(btn_qr)))))
                    .addGroup(pan_dsLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(showPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan_ds, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan_ds, javax.swing.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_them1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them1ActionPerformed
        // TODO add your handling code here:
//        CheckIn p = getData();
//        String query = checkInService.add(p);
//        JOptionPane.showMessageDialog(this, query);
//        loadData();
    }//GEN-LAST:event_btn_them1ActionPerformed

    private void btn_qr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qr1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_qr1ActionPerformed

    private void txt_timsdt1InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_timsdt1InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timsdt1InputMethodTextChanged

    private void txt_timsdt1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timsdt1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timsdt1KeyPressed

    private void txt_timsdt1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timsdt1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timsdt1KeyReleased

    private void txt_timsdt1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timsdt1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timsdt1KeyTyped

    private void tbl_checkInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_checkInMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_checkInMouseClicked

    private void txtMaQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaQRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaQRActionPerformed

    private void btn_qrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qrActionPerformed
        // TODO add your handling code here:
        initWebcam();
        captureThread();
    }//GEN-LAST:event_btn_qrActionPerformed

    private void btn_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stopActionPerformed
        // TODO add your handling code here:
        stopCapture();
    }//GEN-LAST:event_btn_stopActionPerformed

    private void pan_dsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pan_dsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pan_dsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCheckOut.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewCheckOut().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_qr;
    private javax.swing.JButton btn_qr1;
    private javax.swing.JButton btn_stop;
    private javax.swing.JButton btn_them1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel11;
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
    private javax.swing.JTextField txt_ghiChu;
    private javax.swing.JTextField txt_gio;
    private javax.swing.JTextField txt_ma;
    private javax.swing.JLabel txt_moTa;
    private javax.swing.JTextField txt_ngayTao;
    private javax.swing.JTextField txt_phutRa;
    private javax.swing.JTextField txt_timsdt1;
    // End of variables declaration//GEN-END:variables
}
