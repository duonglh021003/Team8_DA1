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
import com.mycompany.duan1_n8.entity.ChucVu;
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
import com.mycompany.duan1_n8.service.Impl.NhanVienServiceImpl;
import com.mycompany.duan1_n8.service.NhanVienService;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BXT
 */
public class FormDanhSachLamViec extends javax.swing.JFrame {

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
     * Creates new form DanhSachLamViec
     */
    public FormDanhSachLamViec() {
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
        initWebcam();
        captureThread();
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
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btn_them1 = new javax.swing.JButton();
        btn_excel1 = new javax.swing.JButton();
        btn_qr1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        cbo_t2 = new javax.swing.JComboBox<>();
        cbo_t3 = new javax.swing.JComboBox<>();
        cbo_t4 = new javax.swing.JComboBox<>();
        cbo_t5 = new javax.swing.JComboBox<>();
        cbo_t6 = new javax.swing.JComboBox<>();
        cbo_t7 = new javax.swing.JComboBox<>();
        cbo_t8 = new javax.swing.JComboBox<>();
        txt_ma01 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txt_ngayTao = new javax.swing.JTextField();
        txt_ngaySua = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        txt_timsdt1 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_LamViec = new javax.swing.JTable();
        jLabel24 = new javax.swing.JLabel();
        showPanel1 = new javax.swing.JPanel();
        txtMaQR1 = new javax.swing.JTextField();
        showPanel = new javax.swing.JPanel();
        btn_stop = new javax.swing.JButton();
        btn_qr = new javax.swing.JButton();
        lbl_tenNV = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pan_ds.setBackground(new java.awt.Color(255, 255, 255));
        pan_ds.setPreferredSize(new java.awt.Dimension(1060, 631));
        pan_ds.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pan_dsMouseClicked(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "chọn ca", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel11.setText("thứ 2");

        jLabel12.setText("thứ 4");

        jLabel14.setText("thứ 6");

        jLabel16.setText("chủ nhật");

        jLabel17.setText("mã");

        jLabel18.setText("thứ 3");

        jLabel19.setText("thứ 5");

        jLabel20.setText("thứ 7");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_them1.setBackground(new java.awt.Color(153, 255, 204));
        btn_them1.setText("Thêm ca");
        btn_them1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_them1ActionPerformed(evt);
            }
        });

        btn_excel1.setBackground(new java.awt.Color(153, 255, 204));
        btn_excel1.setText("excel");
        btn_excel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excel1ActionPerformed(evt);
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
                    .addComponent(btn_excel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(btn_excel1)
                .addGap(18, 18, 18)
                .addComponent(btn_qr1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel22.setText("ngày tạo");

        jLabel25.setText("ngày sửa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ma01))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_t8, 0, 263, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_t6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_t4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbo_t2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(61, 61, 61)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbo_t3, 0, 267, Short.MAX_VALUE)
                    .addComponent(cbo_t5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbo_t7, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_ngayTao)
                    .addComponent(txt_ngaySua))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel18)
                            .addComponent(cbo_t2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_t3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel19)
                            .addComponent(cbo_t4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_t5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel20)
                            .addComponent(cbo_t6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_t7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(cbo_t8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(txt_ngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17)
                                    .addComponent(txt_ma01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(txt_ngaySua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Đang làm việc", jPanel12);

        jLabel24.setText("mã QR");

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

        javax.swing.GroupLayout pan_dsLayout = new javax.swing.GroupLayout(pan_ds);
        pan_ds.setLayout(pan_dsLayout);
        pan_dsLayout.setHorizontalGroup(
            pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_dsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pan_dsLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(showPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pan_dsLayout.createSequentialGroup()
                                .addComponent(btn_stop)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn_qr)
                                .addGap(55, 55, 55))
                            .addGroup(pan_dsLayout.createSequentialGroup()
                                .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(txtMaQR1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_tenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pan_dsLayout.setVerticalGroup(
            pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pan_dsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pan_dsLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaQR1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_tenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pan_dsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_stop)
                            .addComponent(btn_qr))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane4)
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
                    .addComponent(pan_ds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 619, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(pan_ds, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_them1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_them1ActionPerformed
        // TODO add your handling code here:
        DanhSachLamViec ds = getData();
        String query = danhSachLamViecService.add(ds);
        JOptionPane.showMessageDialog(this, query);
        loadData();
    }//GEN-LAST:event_btn_them1ActionPerformed

    private void btn_excel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_excel1ActionPerformed

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

    private void tbl_LamViecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_LamViecMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_LamViecMouseClicked

    private void pan_dsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pan_dsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pan_dsMouseClicked

    private void btn_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stopActionPerformed
        // TODO add your handling code here:
//        stopCapture();
    }//GEN-LAST:event_btn_stopActionPerformed

    private void btn_qrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qrActionPerformed
        // TODO add your handling code here:
        initWebcam();
        captureThread();
    }//GEN-LAST:event_btn_qrActionPerformed

    private void txtMaQR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaQR1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaQR1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormDanhSachLamViec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormDanhSachLamViec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormDanhSachLamViec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormDanhSachLamViec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormDanhSachLamViec().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_excel1;
    private javax.swing.JButton btn_qr;
    private javax.swing.JButton btn_qr1;
    private javax.swing.JButton btn_stop;
    private javax.swing.JButton btn_them1;
    private javax.swing.JComboBox<String> cbo_t2;
    private javax.swing.JComboBox<String> cbo_t3;
    private javax.swing.JComboBox<String> cbo_t4;
    private javax.swing.JComboBox<String> cbo_t5;
    private javax.swing.JComboBox<String> cbo_t6;
    private javax.swing.JComboBox<String> cbo_t7;
    private javax.swing.JComboBox<String> cbo_t8;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JLabel lbl_tenNV;
    private javax.swing.JPanel pan_ds;
    private javax.swing.JPanel showPanel;
    private javax.swing.JPanel showPanel1;
    private javax.swing.JTable tbl_LamViec;
    private javax.swing.JTextField txtMaQR1;
    private javax.swing.JTextField txt_ma01;
    private javax.swing.JTextField txt_ngaySua;
    private javax.swing.JTextField txt_ngayTao;
    private javax.swing.JTextField txt_timsdt1;
    // End of variables declaration//GEN-END:variables

}
