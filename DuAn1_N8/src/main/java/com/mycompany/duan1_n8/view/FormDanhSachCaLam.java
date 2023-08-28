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
import com.mycompany.duan1_n8.entity.ChuNhat;
import com.mycompany.duan1_n8.entity.DanhSachLamViec;
import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.entity.Thu2;
import com.mycompany.duan1_n8.entity.Thu3;
import com.mycompany.duan1_n8.entity.Thu4;
import com.mycompany.duan1_n8.entity.Thu5;
import com.mycompany.duan1_n8.entity.Thu6;
import com.mycompany.duan1_n8.entity.Thu7;
import com.mycompany.duan1_n8.repository.ChuNhatRepository;
import com.mycompany.duan1_n8.repository.Thu2Repository;
import com.mycompany.duan1_n8.repository.Thu3Repository;
import com.mycompany.duan1_n8.repository.Thu4Repository;
import com.mycompany.duan1_n8.repository.Thu5Repository;
import com.mycompany.duan1_n8.repository.Thu6Repository;
import com.mycompany.duan1_n8.repository.Thu7Repository;
import com.mycompany.duan1_n8.service.DanhSachLamViecService;
import com.mycompany.duan1_n8.service.Impl.DanhSachLamViecServiceImpl;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BXT
 */
public class FormDanhSachCaLam extends javax.swing.JFrame {

    private Thu2Repository thu2Repository = new Thu2Repository();
    private Thu3Repository thu3Repository = new Thu3Repository();
    private Thu4Repository thu4Repository = new Thu4Repository();
    private Thu5Repository thu5Repository = new Thu5Repository();
    private Thu6Repository thu6Repository = new Thu6Repository();
    private Thu7Repository thu7Repository = new Thu7Repository();
    private ChuNhatRepository chuNhatRepository = new ChuNhatRepository();
    private final DanhSachLamViecService danhSachLamViecService = new DanhSachLamViecServiceImpl();

    private DefaultTableModel model;
    private DefaultComboBoxModel defaultComboBoxModel;
    private Webcam webcam;
    private WebcamPanel webcamPanel;
    private Thread captureThread;
    private boolean isCameraClosed = false;

    /**
     * Creates new form FormDanhSachCaLam
     */
    public FormDanhSachCaLam() {
        initComponents();
        setLocationRelativeTo(null);
        loadData();
        cbbT2();
        cbbT3();
        cbbT4();
        cbbT5();
        cbbT6();
        cbbT7();
        cbbCN();
        txt_ngayTao.disable();
        txt_ngayTao.setText(java.time.LocalDate.now().toString());
        txt_ngaySua.disable();
        txt_ngaySua.setText(java.time.LocalDate.now().toString());
    }

    void cbbT2() {
        defaultComboBoxModel = (DefaultComboBoxModel) cbo_t2.getModel();
        for (Thu2 t2 : thu2Repository.getAll()) {
            defaultComboBoxModel.addElement(t2);
        }
    }

    void cbbT3() {
        defaultComboBoxModel = (DefaultComboBoxModel) cbo_t3.getModel();
        for (Thu3 t3 : thu3Repository.getAll()) {
            defaultComboBoxModel.addElement(t3);
        }
    }

    void cbbT4() {
        defaultComboBoxModel = (DefaultComboBoxModel) cbo_t4.getModel();
        for (Thu4 t4 : thu4Repository.getAll()) {
            defaultComboBoxModel.addElement(t4);
        }
    }

    void cbbT5() {
        defaultComboBoxModel = (DefaultComboBoxModel) cbo_t5.getModel();
        for (Thu5 t5 : thu5Repository.getAll()) {
            defaultComboBoxModel.addElement(t5);
        }
    }

    void cbbT6() {
        defaultComboBoxModel = (DefaultComboBoxModel) cbo_t6.getModel();
        for (Thu6 t6 : thu6Repository.getAll()) {
            defaultComboBoxModel.addElement(t6);
        }
    }

    void cbbT7() {
        defaultComboBoxModel = (DefaultComboBoxModel) cbo_t7.getModel();
        for (Thu7 t7 : thu7Repository.getAll()) {
            defaultComboBoxModel.addElement(t7);
        }
    }

    void cbbCN() {
        defaultComboBoxModel = (DefaultComboBoxModel) cbo_t8.getModel();
        for (ChuNhat chuNhat : chuNhatRepository.getAll()) {
            defaultComboBoxModel.addElement(chuNhat);
        }
    }

    public void loadData() {
        List<DanhSachLamViec> listDS = danhSachLamViecService.getAll();
        model = (DefaultTableModel) tbl_LamViec.getModel();
        model.setRowCount(0);
        for (DanhSachLamViec p : listDS) {
            model.addRow(new Object[]{
                p.getMa(),
                p.getNgayTao(),
                p.getNgaySua(),
                p.getThu2(),
                p.getThu3(),
                p.getThu4(),
                p.getThu5(),
                p.getThu6(),
                p.getThu7(),
                p.getChuNhat(),
                p.getNhanVien().getMaQr(),});
        }
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
                        txtMaQR1.setText(result.getText());
                    }

//                    count++;
//                    if (count >= 100) {
//                        break;
//                    }
                    if (isCameraClosed) {
                        break;
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
        }
    }

    private DanhSachLamViec getData() {
        DanhSachLamViec p = new DanhSachLamViec();

        String ma = txt_ma01.getText().trim();
        p.setMa(ma);
        String NgayTao = txt_ngayTao.getText().trim();
        Date ngayTao = (Date.valueOf(NgayTao));
        p.setNgayTao(ngayTao);
        String NgaySua = txt_ngaySua.getText().trim();
        Date ngaySua = (Date.valueOf(java.time.LocalDate.now()));
        p.setNgaySua(ngaySua);
        Thu2 t2 = (Thu2) cbo_t2.getSelectedItem();
        p.setThu2(t2);
        Thu3 t3 = (Thu3) cbo_t3.getSelectedItem();
        p.setThu3(t3);
        Thu4 t4 = (Thu4) cbo_t4.getSelectedItem();
        p.setThu4(t4);
        Thu5 t5 = (Thu5) cbo_t5.getSelectedItem();
        p.setThu5(t5);
        Thu6 t6 = (Thu6) cbo_t6.getSelectedItem();
        p.setThu6(t6);
        Thu7 t7 = (Thu7) cbo_t7.getSelectedItem();
        p.setThu7(t7);
        ChuNhat cn = (ChuNhat) cbo_t8.getSelectedItem();
        p.setChuNhat(cn);

        Integer nv = Integer.valueOf(txtMaQR1.getText().trim());
        NhanVien nhanVien = NhanVien.builder().maQr(nv).build();
        p.setNhanVien(nhanVien);

        return p;

    }

    private void clear() {
        txt_ma01.setText("");
        txtMaQR1.setText("");
        txt_ngaySua.setText("");
        txt_ngayTao.setText("");
        cbo_t2.setSelectedIndex(0);
        cbo_t3.setSelectedIndex(0);
        cbo_t4.setSelectedIndex(0);
        cbo_t5.setSelectedIndex(0);
        cbo_t6.setSelectedIndex(0);
        cbo_t7.setSelectedIndex(0);
        cbo_t8.setSelectedIndex(0);
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
                        txt_searchMaQr.setText(result.getText());
                    }

                } while (true);
            }
        };
        captureThread.setDaemon(true);
        captureThread.start();
    }

    private void searchMaQr() {
        List<DanhSachLamViec> list = danhSachLamViecService.getAll();
        Integer maQr = Integer.valueOf(txt_searchMaQr.getText());

        model = (DefaultTableModel) tbl_LamViec.getModel();
        model.setRowCount(0);
        for (DanhSachLamViec p : list) {
            if (p.getNhanVien().getMaQr().equals(maQr)) {
                model.addRow(new Object[]{
                    p.getMa(),
                    p.getNgayTao(),
                    p.getNgaySua(),
                    p.getThu2(),
                    p.getThu3(),
                    p.getThu4(),
                    p.getThu5(),
                    p.getThu6(),
                    p.getThu7(),
                    p.getChuNhat(),
                    p.getNhanVien().getMaQr(),});
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

        pan_ds1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        btn_them2 = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_qr1 = new javax.swing.JButton();
        btn_thoat = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        cbo_t2 = new javax.swing.JComboBox<>();
        cbo_t3 = new javax.swing.JComboBox<>();
        cbo_t4 = new javax.swing.JComboBox<>();
        cbo_t5 = new javax.swing.JComboBox<>();
        cbo_t6 = new javax.swing.JComboBox<>();
        cbo_t7 = new javax.swing.JComboBox<>();
        cbo_t8 = new javax.swing.JComboBox<>();
        txt_ma01 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txt_ngayTao = new javax.swing.JTextField();
        txt_ngaySua = new javax.swing.JTextField();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_LamViec = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        showPanel1 = new javax.swing.JPanel();
        txtMaQR1 = new javax.swing.JTextField();
        showPanel = new javax.swing.JPanel();
        btn_stop = new javax.swing.JButton();
        btn_qr = new javax.swing.JButton();
        lbl_tenNV = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        txt_searchMaQr = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        btn_qr01 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pan_ds1.setBackground(new java.awt.Color(255, 255, 255));
        pan_ds1.setPreferredSize(new java.awt.Dimension(1060, 631));
        pan_ds1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pan_ds1MouseClicked(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "chọn ca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel13.setText("thứ 2");

        jLabel15.setText("thứ 4");

        jLabel21.setText("thứ 6");

        jLabel22.setText("chủ nhật");

        jLabel23.setText("mã");

        jLabel24.setText("thứ 3");

        jLabel25.setText("thứ 5");

        jLabel26.setText("thứ 7");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_them2.setBackground(new java.awt.Color(153, 255, 204));
        btn_them2.setText("Thêm ca");
        btn_them2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them2ActionPerformed(evt);
            }
        });

        btn_sua.setBackground(new java.awt.Color(153, 255, 204));
        btn_sua.setText("sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_qr1.setBackground(new java.awt.Color(153, 255, 204));
        btn_qr1.setText("làm mới");
        btn_qr1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_qr1ActionPerformed(evt);
            }
        });

        btn_thoat.setBackground(new java.awt.Color(153, 255, 204));
        btn_thoat.setText("thoát");
        btn_thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_sua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_them2, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(btn_qr1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_thoat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btn_them2)
                .addGap(18, 18, 18)
                .addComponent(btn_sua)
                .addGap(18, 18, 18)
                .addComponent(btn_qr1)
                .addGap(18, 18, 18)
                .addComponent(btn_thoat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel27.setText("ngày tạo");

        jLabel28.setText("ngày sửa");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ma01))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_t8, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_t6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_t4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_t2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(61, 61, 61)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbo_t3, 0, 267, Short.MAX_VALUE)
                    .addComponent(cbo_t5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_t7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_ngayTao)
                    .addComponent(txt_ngaySua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jLabel24)
                            .addComponent(cbo_t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_t3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel25)
                            .addComponent(cbo_t4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_t5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jLabel26)
                            .addComponent(cbo_t6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_t7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(cbo_t8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27)
                            .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel23)
                                    .addComponent(txt_ma01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel28)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(txt_ngaySua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel12.setPreferredSize(new java.awt.Dimension(1034, 200));

        tbl_LamViec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "mã", "ngày tạo", "ngày sửa", "thứ 2", "thứ 3", "thứ 4", "thứ 5", "thứ 6", "thứ 7", "chủ nhật", "mã QR"
            }
        ));
        tbl_LamViec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_LamViecMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_LamViec);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Đang làm việc", jPanel12);

        jLabel30.setText("mã QR");

        showPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaQR1.setEditable(false);
        txtMaQR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaQR1ActionPerformed(evt);
            }
        });

        showPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_stop.setText("Stop QR");
        btn_stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stopActionPerformed(evt);
            }
        });

        btn_qr.setText("quét QR");
        btn_qr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_qrActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txt_searchMaQr.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_searchMaQrCaretUpdate(evt);
            }
        });
        txt_searchMaQr.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_searchMaQrInputMethodTextChanged(evt);
            }
        });
        txt_searchMaQr.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchMaQrKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchMaQrKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_searchMaQrKeyTyped(evt);
            }
        });

        jLabel31.setText("Tìm theo mã qr");

        btn_qr01.setText("quét QR");
        btn_qr01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_qr01ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txt_searchMaQr, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_qr01)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_searchMaQr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_qr01))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout pan_ds1Layout = new javax.swing.GroupLayout(pan_ds1);
        pan_ds1.setLayout(pan_ds1Layout);
        pan_ds1Layout.setHorizontalGroup(
            pan_ds1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_ds1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_ds1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pan_ds1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(showPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pan_ds1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pan_ds1Layout.createSequentialGroup()
                                .addComponent(btn_stop)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_qr)
                                .addGap(49, 49, 49))
                            .addComponent(jLabel30)
                            .addComponent(txtMaQR1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_tenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pan_ds1Layout.setVerticalGroup(
            pan_ds1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_ds1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pan_ds1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pan_ds1Layout.createSequentialGroup()
                        .addGroup(pan_ds1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(showPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pan_ds1Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaQR1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_tenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(pan_ds1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btn_stop)
                                    .addComponent(btn_qr))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pan_ds1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1060, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pan_ds1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 619, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pan_ds1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_them2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them2ActionPerformed
        // TODO add your handling code here:
        DanhSachLamViec ds = getData();
        String query = danhSachLamViecService.add(ds);
        JOptionPane.showMessageDialog(this, query);
        loadData();
    }//GEN-LAST:event_btn_them2ActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_qr1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qr1ActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btn_qr1ActionPerformed

    private void tbl_LamViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LamViecMouseClicked
        // TODO add your handling code here:
        Integer row = tbl_LamViec.getSelectedRow();
        txt_ma01.setText(tbl_LamViec.getValueAt(row, 0).toString());
        txt_ngayTao.setText(tbl_LamViec.getValueAt(row, 1).toString());
        txt_ngaySua.setText(tbl_LamViec.getValueAt(row, 2).toString());
        String t2 = tbl_LamViec.getValueAt(row, 3).toString();
        cbo_t2.addItem(t2);
        cbo_t2.setSelectedItem(t2);
        String t3 = tbl_LamViec.getValueAt(row, 4).toString();
        cbo_t3.addItem(t3);
        cbo_t3.setSelectedItem(t3);
        String t4 = tbl_LamViec.getValueAt(row, 5).toString();
        cbo_t4.addItem(t4);
        cbo_t4.setSelectedItem(t4);
        String t5 = tbl_LamViec.getValueAt(row, 6).toString();
        cbo_t5.addItem(t5);
        cbo_t5.setSelectedItem(t5);
        String t6 = tbl_LamViec.getValueAt(row, 7).toString();
        cbo_t6.addItem(t6);
        cbo_t6.setSelectedItem(t6);
        String t7 = tbl_LamViec.getValueAt(row, 8).toString();
        cbo_t7.addItem(t7);
        cbo_t7.setSelectedItem(t7);
        String t8 = tbl_LamViec.getValueAt(row, 9).toString();
        cbo_t8.addItem(t8);
        cbo_t8.setSelectedItem(t8);
        txtMaQR1.setText(tbl_LamViec.getValueAt(row, 10).toString());

    }//GEN-LAST:event_tbl_LamViecMouseClicked

    private void txtMaQR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaQR1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaQR1ActionPerformed

    private void btn_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stopActionPerformed
        // TODO add your handling code here:
        stopCapture();
    }//GEN-LAST:event_btn_stopActionPerformed

    private void btn_qrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qrActionPerformed
        // TODO add your handling code here:
        initWebcam();
        captureThread();
    }//GEN-LAST:event_btn_qrActionPerformed

    private void pan_ds1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pan_ds1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pan_ds1MouseClicked

    private void btn_thoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thoatActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btn_thoatActionPerformed

    private void txt_searchMaQrCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_searchMaQrCaretUpdate
        // TODO add your handling code here:
        searchMaQr();
    }//GEN-LAST:event_txt_searchMaQrCaretUpdate

    private void txt_searchMaQrInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_searchMaQrInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchMaQrInputMethodTextChanged

    private void txt_searchMaQrKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchMaQrKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchMaQrKeyPressed

    private void txt_searchMaQrKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchMaQrKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchMaQrKeyReleased

    private void txt_searchMaQrKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchMaQrKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_searchMaQrKeyTyped

    private void btn_qr01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qr01ActionPerformed
        // TODO add your handling code here:
        initWebcam();
        captureThreadSearch();
        
    }//GEN-LAST:event_btn_qr01ActionPerformed

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
            java.util.logging.Logger.getLogger(FormDanhSachCaLam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDanhSachCaLam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDanhSachCaLam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDanhSachCaLam.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDanhSachCaLam().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_qr;
    private javax.swing.JButton btn_qr01;
    private javax.swing.JButton btn_qr1;
    private javax.swing.JButton btn_stop;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them1;
    private javax.swing.JButton btn_them2;
    private javax.swing.JButton btn_thoat;
    private javax.swing.JComboBox<String> cbo_t2;
    private javax.swing.JComboBox<String> cbo_t3;
    private javax.swing.JComboBox<String> cbo_t4;
    private javax.swing.JComboBox<String> cbo_t5;
    private javax.swing.JComboBox<String> cbo_t6;
    private javax.swing.JComboBox<String> cbo_t7;
    private javax.swing.JComboBox<String> cbo_t8;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel lbl_tenNV;
    private javax.swing.JPanel pan_ds;
    private javax.swing.JPanel pan_ds1;
    private javax.swing.JPanel showPanel;
    private javax.swing.JPanel showPanel1;
    private javax.swing.JTable tbl_LamViec;
    private javax.swing.JTextField txtMaQR1;
    private javax.swing.JTextField txt_ma01;
    private javax.swing.JTextField txt_ngaySua;
    private javax.swing.JTextField txt_ngayTao;
    private javax.swing.JTextField txt_searchMaQr;
    // End of variables declaration//GEN-END:variables
}
