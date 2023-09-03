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
import com.mycompany.duan1_n8.entity.ChucVu;
import com.mycompany.duan1_n8.entity.KhachHang;
import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.repository.KhachHangRepository;
import com.mycompany.duan1_n8.service.Impl.KhachHangServiceImpl;
import com.mycompany.duan1_n8.service.KhachHangService;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BuiDucMinh
 */
public class FormKhachHang extends javax.swing.JPanel {

    private final KhachHangService khachHangService = new KhachHangServiceImpl();

    private DefaultTableModel model;
    private DefaultComboBoxModel defaultComboBoxModel;
    private Webcam webcam;
    private WebcamPanel webcamPanel;
    private Thread captureThread;
    private boolean isCameraClosed = false;
    private String selectedTable = "";
    private JFrame frame;

    public FormKhachHang() {
        initComponents();
        loadData();
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

    public void captureThread() {

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
                        txt_seaerchMaQR.setText(result.getText());
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

    public void loadData() {
        List<KhachHang> listKH = khachHangService.getAllKH();
        model = (DefaultTableModel) tbl_khachhang.getModel();
        model.setRowCount(0);

        for (KhachHang p : listKH) {
            model.addRow(new Object[]{
                p.getMaQR(),
                p.getMaKH(),
                p.getTenKH(),
                p.getGioiTinh() == true ? "Nam" : "Nữ",
                p.getEmail(),
                p.getQueQuan(),
                p.getNgaySinh(),
                p.getSdt(),
                p.getTrangThai() == 0 ? "đang hoạt động" : "ngừng hoạt động",});

        }
    }

    private KhachHang getData() {
        KhachHang kh = new KhachHang();
        Integer maQr = Integer.valueOf(txtMaQR.getText().trim());
        kh.setMaQR(maQr);
        String ma = txt_makh.getText().trim();
        kh.setMaKH(ma);
        String ten = txt_tenkh.getText().trim();
        kh.setTenKH(ten);
        boolean gioiTinh;
        if (rdo_nam.isSelected()) {
            gioiTinh = true;
        } else {
            gioiTinh = false;
        }
        kh.setGioiTinh(gioiTinh);
        String email = txt_email.getText().trim();
        kh.setEmail(email);
        String diaChi = txt_diaChi.getText().trim();
        kh.setQueQuan(diaChi);
        String NgaySinh = txt_ngaySinh.getText().trim();
        Date ngaySinh = (Date.valueOf(NgaySinh));
        kh.setNgaySinh(ngaySinh);
        String sdt = txt_sdt.getText().trim();
        kh.setSdt(sdt);

        Integer trangThai;
        if (rdo_nhd.isSelected()) {
            trangThai = 0;
        } else {
            trangThai = 1;
        }
        kh.setTrangThai(trangThai);
        return kh;
    }

    private void clear() {
        txtMaQR.setText("");
        txt_diaChi.setText("");
        txt_email.setText("");
        txt_makh.setText("");
        txt_ngaySinh.setText("");
        txt_sdt.setText("");
        txt_tenkh.setText("");

    }

    public boolean checkTrungMa() {
        String ma = txt_makh.getText();
        for (KhachHang nv : khachHangService.getAllKH()) {
                if (nv.getMaKH().equals(ma)) {
                JOptionPane.showMessageDialog(this, "mã không được trùng");
                return false;
            }
        }
        return true;
    }

    public boolean checkTrungMaQR() {
        Integer ma = Integer.valueOf(txtMaQR.getText().trim());
        for (KhachHang nv : khachHangService.getAllKH()) {
            if (nv.getMaQR().equals(ma)) {
                JOptionPane.showMessageDialog(this, "mã qr không được trùng");
                return false;
            }
        }
        return true;
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_makh = new javax.swing.JTextField();
        txt_tenkh = new javax.swing.JTextField();
        txt_sdt = new javax.swing.JTextField();
        rdo_nam = new javax.swing.JRadioButton();
        rdo_nu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_lammoi = new javax.swing.JButton();
        txt_diaChi = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtMaQR = new javax.swing.JTextField();
        btn_quetQR = new javax.swing.JButton();
        btn_stopQR = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txt_ngaySinh = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        rdo_dhd = new javax.swing.JRadioButton();
        rdo_nhd = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txt_seaerchMaQR = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_khachhang = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Mã khách hàng");

        jLabel2.setText("Tên khách hàng");

        jLabel3.setText("Giới tính");

        jLabel4.setText("Điện thoại");

        rdo_nam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdo_nam);
        rdo_nam.setText("Nam");
        rdo_nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_namActionPerformed(evt);
            }
        });

        rdo_nu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdo_nu);
        rdo_nu.setText("Nữ");
        rdo_nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_nuActionPerformed(evt);
            }
        });

        jLabel5.setText("Email");

        jLabel7.setText("Địa chỉ");

        btn_them.setBackground(new java.awt.Color(153, 255, 204));
        btn_them.setText("Thêm khách hàng");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_sua.setBackground(new java.awt.Color(153, 255, 204));
        btn_sua.setText("Sửa khách hàng");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_lammoi.setBackground(new java.awt.Color(153, 255, 204));
        btn_lammoi.setText("Làm mới");
        btn_lammoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lammoiActionPerformed(evt);
            }
        });

        jLabel10.setText("ma QR");

        btn_quetQR.setBackground(new java.awt.Color(153, 255, 204));
        btn_quetQR.setText("quét qr");
        btn_quetQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quetQRActionPerformed(evt);
            }
        });

        btn_stopQR.setBackground(new java.awt.Color(153, 255, 204));
        btn_stopQR.setText("stop qr");
        btn_stopQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_stopQRActionPerformed(evt);
            }
        });

        jLabel11.setText("ngày sinh");

        jLabel12.setText("trạng thái");

        buttonGroup2.add(rdo_dhd);
        rdo_dhd.setText("đang hoạt động");
        rdo_dhd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_dhdActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdo_nhd);
        rdo_nhd.setText("ngừng hoạt động");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdo_dhd)
                        .addGap(45, 45, 45)
                        .addComponent(rdo_nhd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_them)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_sua)
                        .addGap(18, 18, 18)
                        .addComponent(btn_lammoi, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(139, 139, 139))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txt_ngaySinh)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(rdo_nam)
                                .addGap(27, 27, 27)
                                .addComponent(rdo_nu))
                            .addComponent(txt_makh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(txt_tenkh, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_sdt, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 61, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_diaChi)
                                    .addComponent(txtMaQR)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btn_quetQR, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_stopQR, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addGap(76, 76, 76))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_makh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_tenkh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rdo_nam)
                    .addComponent(rdo_nu)
                    .addComponent(jLabel10)
                    .addComponent(txtMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_quetQR)
                    .addComponent(btn_stopQR))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_them)
                            .addComponent(btn_sua)
                            .addComponent(btn_lammoi))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(rdo_dhd)
                            .addComponent(rdo_nhd))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setText("Tìm kiếm");

        txt_seaerchMaQR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_seaerchMaQRKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_seaerchMaQRKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_seaerchMaQRKeyTyped(evt);
            }
        });

        tbl_khachhang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "mã qr", "Mã KH", "Tên KH", "Giới tính", "Email", "Địa chỉ", "ngày sinh", "SDT", "trạng thái"
            }
        ));
        tbl_khachhang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_khachhangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_khachhang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 932, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_seaerchMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txt_seaerchMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thông tin cá nhân", jPanel3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdo_namActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_namActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_namActionPerformed

    private void rdo_nuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_nuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_nuActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        if (checkTrungMaQR()) {
            if (checkTrungMa()) {
                KhachHang khachHang = getData();
                String query = khachHangService.add(khachHang);
                JOptionPane.showMessageDialog(this, query);
                loadData();
            }
        }

    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        // TODO add your handling code here:

        Integer row = tbl_khachhang.getSelectedRow();
        KhachHang kh = getData();
        kh.setMaQR(khachHangService.getAllKH().get(row).getMaQR());
        System.out.println("aaaaaaaaaaaaa       " + khachHangService.getAllKH().get(row).getMaQR());
        String query = khachHangService.Update(kh);
        JOptionPane.showMessageDialog(this, query);
        loadData();

    }//GEN-LAST:event_btn_suaActionPerformed

    private void tbl_khachhangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_khachhangMouseClicked
        // TODO add your handling code here:
        int row = tbl_khachhang.getSelectedRow();
        txtMaQR.setText(tbl_khachhang.getValueAt(row, 0).toString());
        txt_makh.setText(tbl_khachhang.getValueAt(row, 1).toString());
        txt_tenkh.setText(tbl_khachhang.getValueAt(row, 2).toString());
        if (tbl_khachhang.getValueAt(row, 3).equals("Nam")) {
            rdo_nam.setSelected(true);
        } else {
            rdo_nu.setSelected(true);
        }
        txt_email.setText(tbl_khachhang.getValueAt(row, 4).toString());
        txt_diaChi.setText(tbl_khachhang.getValueAt(row, 5).toString());
        txt_ngaySinh.setText(tbl_khachhang.getValueAt(row, 6).toString());
        txt_sdt.setText(tbl_khachhang.getValueAt(row, 7).toString());
        if (tbl_khachhang.getValueAt(row, 8).equals("đang hoạt động")) {
            rdo_dhd.setSelected(true);
        } else {
            rdo_nhd.setSelected(true);
        }
    }//GEN-LAST:event_tbl_khachhangMouseClicked

    private void btn_lammoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lammoiActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btn_lammoiActionPerformed

    private void txt_seaerchMaQRKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_seaerchMaQRKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_seaerchMaQRKeyPressed

    private void txt_seaerchMaQRKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_seaerchMaQRKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_seaerchMaQRKeyReleased

    private void txt_seaerchMaQRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_seaerchMaQRKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_seaerchMaQRKeyTyped

    private void btn_quetQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quetQRActionPerformed
        // TODO add your handling code here:
        initWebcam();
        captureThread();
    }//GEN-LAST:event_btn_quetQRActionPerformed

    private void btn_stopQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stopQRActionPerformed
        // TODO add your handling code here:
        stopCapture();
    }//GEN-LAST:event_btn_stopQRActionPerformed

    private void rdo_dhdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_dhdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_dhdActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_lammoi;
    private javax.swing.JButton btn_quetQR;
    private javax.swing.JButton btn_stopQR;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rdo_dhd;
    private javax.swing.JRadioButton rdo_nam;
    private javax.swing.JRadioButton rdo_nhd;
    private javax.swing.JRadioButton rdo_nu;
    private javax.swing.JTable tbl_khachhang;
    private javax.swing.JTextField txtMaQR;
    private javax.swing.JTextField txt_diaChi;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_makh;
    private javax.swing.JTextField txt_ngaySinh;
    private javax.swing.JTextField txt_sdt;
    private javax.swing.JTextField txt_seaerchMaQR;
    private javax.swing.JTextField txt_tenkh;
    // End of variables declaration//GEN-END:variables
}
