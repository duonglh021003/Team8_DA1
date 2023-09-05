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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
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
    private JFrame frame;

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

    }

    public void initWebcam() {

        frame = new JFrame();
        frame.setBounds(100, 100, 400, 300); 
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

    public void captureThread() {
        System.out.println("aaaaaaaaaaa");
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
                        txtMaQR.setText(result.getText());
                    }
                } while (true);
            }
        };
        captureThread.setDaemon(true);
        captureThread.start();

    }

    public void captureThreadSearch() {

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
                        txt_searchMaQr2.setText(result.getText());
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
        Collections.sort(listCheckIn, Comparator.comparingInt(CheckIn::getId).reversed());

        for (CheckIn p : listCheckIn) {

            model.addRow(new Object[]{
                p.getMa(),
                p.getNgayTao(),
                p.getGioVao() + ":" + p.getPhutVao(),
                p.getBaoCao(),
                p.getNhanVien().getMaQr(),
                p.getStatus(),});
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
            frame.setVisible(false);
        }
    }

    private void clear() {
        txt_ma.setText("");
        txtMaQR.setText("");
        txt_baoCao.setText("");

    }

    private void searchMaQr() {
        List<CheckIn> list = checkInService.getAll();

        Integer maQr = Integer.valueOf(txt_searchMaQr2.getText());

        model = (DefaultTableModel) tbl_checkIn.getModel();
        model.setRowCount(0);

        for (CheckIn p : list) {
            if (p.getNhanVien().getMaQr().equals(maQr)) {
                model.addRow(new Object[]{
                    p.getMa(),
                    p.getNgayTao(),
                    p.getGioVao() + ":" + p.getPhutVao(),
                    p.getBaoCao(),
                    p.getNhanVien().getMaQr(),
                    p.getStatus(),});

            }
        }
    }
    

    private void locTrangThai() {
        List<CheckIn> list = new ArrayList<>();
        String cbo = cbo_trangThai.getSelectedItem().toString();
        for (CheckIn p : checkInService.getAll()) {
            if (String.valueOf(p.getStatus()).equals(cbo)) {
                list.add(p);
            }
        }
        
        model = (DefaultTableModel) tbl_checkIn.getModel();
            model.setRowCount(0);
            for (CheckIn p : list) {
                model.addRow(new Object[]{
                    p.getMa(),
                    p.getNgayTao(),
                    p.getGioVao() + ":" + p.getPhutVao(),
                    p.getBaoCao(),
                    p.getNhanVien().getMaQr(),
                    p.getStatus(),});
            }
       
    }

    private void searchTrangThai() {
        if (cbo_trangThai.getSelectedItem().equals("Chon Trang Thai")) {
            JOptionPane.showMessageDialog(this, "Ban Da Khong Chon Trang Thai");
            loadData();
        } else if (cbo_trangThai.getSelectedItem().equals("PRESENT")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai PRESENT");
            locTrangThai();
        } else if (cbo_trangThai.getSelectedItem().equals("ABSENT")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai ABSENT");
            locTrangThai();
        } else if (cbo_trangThai.getSelectedItem().equals("LATE1")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE1");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE2")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE2");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE3")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE3");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE4")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE4");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE5")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE5");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE6")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE6");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE7")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE7");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE8")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE8");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE9")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE9");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE10")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE10");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE11")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE11");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE12")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE12");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE13")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE13");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE14")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE14");
            locTrangThai();
        }else if (cbo_trangThai.getSelectedItem().equals("LATE15")) {
            JOptionPane.showMessageDialog(this, "Ban Da Chon Trang Thai LATE15");
            locTrangThai();
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
        jPanel7 = new javax.swing.JPanel();
        txt_searchMaQr2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btn_qr01 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        cbo_trangThai = new javax.swing.JComboBox<>();

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
        btn_them1.setText("check in");
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
                .addContainerGap(29, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
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
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("check in", jPanel12);

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

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txt_searchMaQr2.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_searchMaQr2CaretUpdate(evt);
            }
        });
        txt_searchMaQr2.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_searchMaQr2InputMethodTextChanged(evt);
            }
        });
        txt_searchMaQr2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchMaQr2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchMaQr2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_searchMaQr2KeyTyped(evt);
            }
        });

        jLabel14.setText("Tìm theo mã qr");

        btn_qr01.setText("quét QR");
        btn_qr01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_qr01ActionPerformed(evt);
            }
        });

        jLabel4.setText("trạng thái");

        cbo_trangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chon Trang Thai", "ABSENT", "PRESENT", "LATE1", "LATE2", "LATE3", "LATE4", "LATE5", "LATE6", "LATE7", "LATE8", "LATE9", "LATE10", "LATE11", "LATE12", "LATE13", "LATE14", "LATE15", " " }));
        cbo_trangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_trangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btn_qr01))
                    .addComponent(txt_searchMaQr2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(110, 110, 110)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cbo_trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_searchMaQr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_trangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_qr01)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pan_dsLayout = new javax.swing.GroupLayout(pan_ds);
        pan_ds.setLayout(pan_dsLayout);
        pan_dsLayout.setHorizontalGroup(
            pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_dsLayout.createSequentialGroup()
                .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(pan_dsLayout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(pan_dsLayout.createSequentialGroup()
                                    .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel24))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(pan_dsLayout.createSequentialGroup()
                                    .addComponent(btn_stop)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_qr)
                                    .addGap(66, 66, 66)))
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12))
                        .addGroup(pan_dsLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pan_dsLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pan_dsLayout.setVerticalGroup(
            pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_dsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_dsLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_stop)
                            .addComponent(btn_qr)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan_ds, javax.swing.GroupLayout.DEFAULT_SIZE, 1072, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pan_ds, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
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
        clear();
        loadData();
    }//GEN-LAST:event_btn_qr1ActionPerformed

    private void tbl_checkInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_checkInMouseClicked
        // TODO add your handling code here:
        int row = tbl_checkIn.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) tbl_checkIn.getModel();
        txt_ma.setText(model.getValueAt(row, 0).toString());
        txt_baoCao.setText(model.getValueAt(row, 3).toString());
        txtMaQR.setText(model.getValueAt(row, 4).toString());
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
        captureThread();
        initWebcam();


    }//GEN-LAST:event_btn_qrActionPerformed

    private void pan_dsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pan_dsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pan_dsMouseClicked

    private void txt_searchMaQr2CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_searchMaQr2CaretUpdate
        // TODO add your handling code here:
        searchMaQr();
    }//GEN-LAST:event_txt_searchMaQr2CaretUpdate

    private void txt_searchMaQr2InputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_searchMaQr2InputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchMaQr2InputMethodTextChanged

    private void txt_searchMaQr2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchMaQr2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchMaQr2KeyPressed

    private void txt_searchMaQr2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchMaQr2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchMaQr2KeyReleased

    private void txt_searchMaQr2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchMaQr2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchMaQr2KeyTyped

    private void btn_qr01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qr01ActionPerformed
        // TODO add your handling code here:
        initWebcam();
        captureThreadSearch();

    }//GEN-LAST:event_btn_qr01ActionPerformed

    private void cbo_trangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_trangThaiActionPerformed
        // TODO add your handling code here:
        searchTrangThai();
    }//GEN-LAST:event_cbo_trangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_checkOut;
    private javax.swing.JButton btn_qr;
    private javax.swing.JButton btn_qr01;
    private javax.swing.JButton btn_qr1;
    private javax.swing.JButton btn_stop;
    private javax.swing.JButton btn_them1;
    private javax.swing.JComboBox<String> cbo_trangThai;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JPanel pan_ds;
    private javax.swing.JTable tbl_checkIn;
    private javax.swing.JTextField txtMaQR;
    private javax.swing.JTextField txt_baoCao;
    private javax.swing.JTextField txt_gio;
    private javax.swing.JTextField txt_ma;
    private javax.swing.JLabel txt_moTa;
    private javax.swing.JTextField txt_ngayTao;
    private javax.swing.JTextField txt_phut;
    private javax.swing.JTextField txt_searchMaQr;
    private javax.swing.JTextField txt_searchMaQr1;
    private javax.swing.JTextField txt_searchMaQr2;
    // End of variables declaration//GEN-END:variables
}
