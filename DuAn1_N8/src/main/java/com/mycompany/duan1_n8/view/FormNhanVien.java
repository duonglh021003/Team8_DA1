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
import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.repository.ChucVuRepository;
import com.mycompany.duan1_n8.repository.Thu2Repository;
import com.mycompany.duan1_n8.service.Impl.NhanVienServiceImpl;
import com.mycompany.duan1_n8.service.NhanVienService;
import com.mycompany.duan1_n8.untils.Until;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BXT
 */
public class FormNhanVien extends javax.swing.JPanel {

    private final NhanVienService nhanVienService = new NhanVienServiceImpl();
    private ChucVuRepository chucVuRepository = new ChucVuRepository();
    private Thu2Repository thu2 = new Thu2Repository();

    private DefaultTableModel model;
    private DefaultComboBoxModel defaultComboBoxModel;
    private Webcam webcam;
    private WebcamPanel webcamPanel;
    private Thread captureThread;
    private boolean isCameraClosed = false;
    private String selectedTable = "";

    /**
     * Creates new form Form
     */
    public FormNhanVien() {
        initComponents();
        initComponents();

        cbbChucVu();
//        initWebcam();
//        captureThread();
        loadDataNHD();
        loadDataDangHd();

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

//                    count++;
//                    if (count >= 1000) {
//                        break;
//                    }
//                    if (isCameraClosed) {
//                        break;
//                    }
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
                        txt_searchMaQr.setText(result.getText());
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

    public void loadDataDangHd() {
        List<NhanVien> listNV = nhanVienService.getAll();
        model = (DefaultTableModel) tbl_nhanvien_lamviec.getModel();
        model.setRowCount(0);

        for (NhanVien p : listNV) {
            if (p.getTrangThai() == 0) {
                model.addRow(new Object[]{
                    p.getMaQr(),
                    p.getMa(),
                    p.getTen(),
                    p.getNgaySinh(),
                    p.getGioiTinh() == true ? "Nam" : "Nữ",
                    p.getQueQuan(),
                    p.getSdt(),
                    p.getEmail(),
                    p.getMatKhau(),
                    p.getChucVu(),
                    p.getTrangThai() == 0 ? "đang hoạt động" : "ngừng hoạt động",});
            }
        }
    }

    public void loadDataNHD() {
        List<NhanVien> listNV = nhanVienService.getAll();
        model = (DefaultTableModel) tbl_nhanvien_NLV.getModel();
        model.setRowCount(0);

        for (NhanVien p : listNV) {
            if (p.getTrangThai() == 1) {
                model.addRow(new Object[]{
                    p.getMaQr(),
                    p.getMa(),
                    p.getTen(),
                    p.getNgaySinh(),
                    p.getGioiTinh() == true ? "Nam" : "Nữ",
                    p.getQueQuan(),
                    p.getSdt(),
                    p.getEmail(),
                    p.getMatKhau(),
                    p.getChucVu(),
                    p.getTrangThai() == 0 ? "đang hoạt động" : "ngừng hoạt động",});
            }
        }
    }

    void cbbChucVu() {
        defaultComboBoxModel = (DefaultComboBoxModel) cbo_vaitro.getModel();
        for (ChucVu chucVu : chucVuRepository.getAll()) {
            defaultComboBoxModel.addElement(chucVu);
        }
    }

    private NhanVien getData() {
        NhanVien nv = new NhanVien();
        Integer maQr = Integer.valueOf(txtMaQR.getText().trim());
        nv.setMaQr(maQr);
        String ma = txt_ma.getText().trim();
        nv.setMa(ma);
        String ten = txt_ten.getText().trim();
        nv.setTen(ten);

        String NgaySinh = txt_ngaySinh.getText().trim();
        Date ngaySinh = (Date.valueOf(NgaySinh));
        nv.setNgaySinh(ngaySinh);
        boolean gioiTinh;
        if (rdo_nam.isSelected()) {
            gioiTinh = true;
        } else {
            gioiTinh = false;
        }
        nv.setGioiTinh(gioiTinh);
        String diaChi = txt_diachi.getText().trim();
        nv.setQueQuan(diaChi);
        String sdt = txt_dienthoai.getText().trim();
        nv.setSdt(sdt);
        String email = txt_email.getText().trim();
        nv.setEmail(email);
        String matKhau = txt_mk.getText().trim();
        nv.setMatKhau(matKhau);
        ChucVu chucVu = (ChucVu) cbo_vaitro.getSelectedItem();
        nv.setChucVu(chucVu);
        Integer trangThai;
        if (rdo_dhd.isSelected()) {
            trangThai = 0;
        } else {
            trangThai = 1;
        }
        nv.setTrangThai(trangThai);
        return nv;

    }

    public boolean checkValidate() {
        if (txt_ma.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã không được để trống!");
            return false;
        } else if (txt_ten.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống!");
            return false;
        } else if (String.valueOf(txt_ten).matches(Until.TEXT)) {
            JOptionPane.showMessageDialog(this, "Tên phải là chữ!");
            return false;
        } else if (txt_email.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "email không được để trống!");
            return false;
        } else if (String.valueOf(txt_email).matches(Until.EMAIL)) {
            JOptionPane.showMessageDialog(this, "email không đúng định dạng!");
            return false;
        } else if (txt_mk.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "mật khẩu không được để trống!");
            return false;
        } else if (txt_ngaySinh.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "ngày sinh không được để trống!");
            return false;
        } else if (String.valueOf(txt_ngaySinh).matches(Until.EMAIL)) {
            JOptionPane.showMessageDialog(this, "ngày sinh không đúng định dạng!");
            return false;
        } else if (txt_dienthoai.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "số điện thoại không được để trống!");
            return false;
        } else if (String.valueOf(txt_dienthoai).matches(Until.NUMBER)) {
            JOptionPane.showMessageDialog(this, "số điện thoại không đúng định dạng!");
            return false;
        } else if (txt_dienthoai.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "địa chỉ không được để trống!");
            return false;
        }

        return true;

    }

    public boolean checkTrungMa() {
        String ma = txt_ma.getText();
        for (NhanVien nv : nhanVienService.getAll()) {
            if (nv.getMa().equals(ma)) {
                JOptionPane.showMessageDialog(this, "mã không được trùng");
                return false;
            }
        }
        return true;
    }

    public boolean checkTrungMaQR() {
        Integer ma = Integer.valueOf(txtMaQR.getText().trim());
        for (NhanVien nv : nhanVienService.getAll()) {
            if (nv.getMaQr().equals(ma)) {
                JOptionPane.showMessageDialog(this, "mã qr không được trùng");
                return false;
            }
        }
        return true;
    }

    private void clear() {
        txt_ma.setText("");
        txtMaQR.setText("");
        txt_diachi.setText("");
        cbo_vaitro.setSelectedIndex(0);
        txt_email.setText("");
        txt_ngaySinh.setText("");
        txt_ten.setText("");
        txt_dienthoai.setText("");
        txt_mk.setText("");

    }

    private void searchMaQr() {
        List<NhanVien> list = nhanVienService.getAll();
        Integer maQr = Integer.valueOf(txt_searchMaQr.getText());
        nhanVienService.getByMaQr(maQr);

        model = (DefaultTableModel) tbl_nhanvien_lamviec.getModel();
        model.setRowCount(0);
        for (NhanVien p : list) {
            if (p.getMaQr().equals(maQr)) {
                model.addRow(new Object[]{
                    p.getMaQr(),
                    p.getMa(),
                    p.getTen(),
                    p.getNgaySinh(),
                    p.getGioiTinh() == true ? "Nam" : "Nữ",
                    p.getQueQuan(),
                    p.getSdt(),
                    p.getEmail(),
                    p.getMatKhau(),
                    p.getChucVu(),
                    p.getTrangThai() == 0 ? "đang hoạt động" : "ngừng hoạt động",});
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
        jPanel9 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_ma = new javax.swing.JTextField();
        txt_ten = new javax.swing.JTextField();
        txt_dienthoai = new javax.swing.JTextField();
        txt_diachi = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        cbo_vaitro = new javax.swing.JComboBox<>();
        rdo_nam = new javax.swing.JRadioButton();
        rdo_nu = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        btn_them = new javax.swing.JButton();
        btn_clear = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_chonCa = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txt_mk = new javax.swing.JPasswordField();
        lbl_show = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rdo_dhd = new javax.swing.JRadioButton();
        txt_ngaySinh = new javax.swing.JTextField();
        rdo_nhd = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        txt_searchMaQr = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btn_qr01 = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_nhanvien_lamviec = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbl_nhanvien_NLV = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        txtMaQR = new javax.swing.JTextField();
        btn_stop = new javax.swing.JButton();
        btn_qr = new javax.swing.JButton();
        showPanel = new javax.swing.JPanel();

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(1060, 631));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thiết lập thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Mã nhân viên");

        jLabel2.setText("Họ tên");

        jLabel3.setText("Ngày sinh");

        jLabel4.setText("Điện thoại");

        jLabel5.setText("Địa chỉ");

        jLabel6.setText("Email");

        jLabel7.setText("Mật khẩu");

        jLabel8.setText("Vai trò");

        jLabel9.setText("Giới tính");

        cbo_vaitro.setName(""); // NOI18N

        rdo_nam.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdo_nam);
        rdo_nam.setSelected(true);
        rdo_nam.setText("Nam");

        rdo_nu.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rdo_nu);
        rdo_nu.setText("Nữ");
        rdo_nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdo_nuActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_them.setBackground(new java.awt.Color(153, 255, 204));
        btn_them.setText("Thêm nhân viên");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });

        btn_clear.setBackground(new java.awt.Color(153, 255, 204));
        btn_clear.setText("làm mới");
        btn_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clearActionPerformed(evt);
            }
        });

        btn_sua.setBackground(new java.awt.Color(153, 255, 204));
        btn_sua.setText("Sửa nhân viên");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_chonCa.setBackground(new java.awt.Color(153, 255, 204));
        btn_chonCa.setText("chọn ca");
        btn_chonCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonCaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_clear, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_sua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_them, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(btn_chonCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btn_them)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_sua)
                .addGap(18, 18, 18)
                .addComponent(btn_clear)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_chonCa)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txt_mk.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lbl_show.setIcon(new javax.swing.ImageIcon("C:\\Users\\BXT\\Desktop\\Team8\\DuAn1_N8\\src\\main\\java\\com\\mycompany\\duan1_n8\\Images\\show.png")); // NOI18N
        lbl_show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_showMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(txt_mk, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_show, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(txt_mk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lbl_show, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel10.setText("trạng thái");

        buttonGroup2.add(rdo_dhd);
        rdo_dhd.setSelected(true);
        rdo_dhd.setText("đang hoạt động");

        txt_ngaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ngaySinhActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdo_nhd);
        rdo_nhd.setText("ngừng hoạt động");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_diachi))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dienthoai))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ngaySinh))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ten))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(rdo_nam)
                            .addGap(67, 67, 67)
                            .addComponent(rdo_nu)
                            .addGap(33, 33, 33))
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbo_vaitro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_email))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdo_dhd)
                        .addGap(32, 32, 32)
                        .addComponent(rdo_nhd)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(cbo_vaitro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txt_dienthoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(rdo_nam)
                            .addComponent(rdo_nu))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_diachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(rdo_dhd)
                            .addComponent(rdo_nhd)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

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

        jLabel13.setText("Tìm theo mã qr");

        btn_qr01.setText("quét QR");
        btn_qr01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_qr01ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txt_searchMaQr, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_qr01)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_searchMaQr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_qr01))
                .addGap(14, 14, 14))
        );

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(1034, 200));

        tbl_nhanvien_lamviec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "maQR", "Mã NV", "Tên NV", "Ngày sinh", "Giới tính", "Địa chỉ", "SĐT", "Email", "Mật khẩu", "Vai trò", "Trạng thái"
            }
        ));
        tbl_nhanvien_lamviec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nhanvien_lamviecMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_nhanvien_lamviec);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Đang làm việc", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.setPreferredSize(new java.awt.Dimension(1034, 200));

        tbl_nhanvien_NLV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "maQR", "Mã NV", "Tên NV", "Ngày sinh", "Giới tính", "Địa chỉ", "SĐT", "Email", "Mật khẩu", "Vai trò", "Trạng thái"
            }
        ));
        tbl_nhanvien_NLV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nhanvien_NLVMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbl_nhanvien_NLV);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 997, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Ngừng làm việc", jPanel6);

        jLabel15.setText("mã QR");

        txtMaQR.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtMaQRCaretUpdate(evt);
            }
        });
        txtMaQR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaQRActionPerformed(evt);
            }
        });

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

        showPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(showPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(btn_stop)
                                .addGap(33, 33, 33)
                                .addComponent(btn_qr))
                            .addComponent(txtMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_stop)
                            .addComponent(btn_qr)))
                    .addComponent(showPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1062, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 1062, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdo_nuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_nuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_nuActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        if (checkValidate()) {
            if (checkTrungMa()) {
                if (checkTrungMaQR()) {
                    NhanVien nv = getData();
                    String query = nhanVienService.add(nv);
                    JOptionPane.showMessageDialog(this, query);
                    loadDataDangHd();
                }
            }
        }
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clearActionPerformed
        clear();
    }//GEN-LAST:event_btn_clearActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed

        if (selectedTable.equals("tbl_nhanvien_NLV")) {

            Integer row = tbl_nhanvien_NLV.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng trên table 'tbl_nhanvien_lamviec'");
                return;
            }

            NhanVien nv = getData();
            nv.setMaQr(nhanVienService.getAll().get(row).getMaQr());

            String query = nhanVienService.Update(nv);
            JOptionPane.showMessageDialog(this, query);

            loadDataNHD();
            loadDataDangHd();
        } else if (selectedTable.equals("tbl_nhanvien_lamviec")) {

            Integer row = tbl_nhanvien_lamviec.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng trên table 'tbl_nhanvien_NLV'");
                return;
            }

            NhanVien nv = getData();
            nv.setMaQr(nhanVienService.getAll().get(row).getMaQr());

            String query = nhanVienService.Update(nv);
            JOptionPane.showMessageDialog(this, query);

            loadDataNHD();
            loadDataDangHd();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn bảng để cập nhật thông tin");
        }


    }//GEN-LAST:event_btn_suaActionPerformed

    private void lbl_showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_showMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_showMouseClicked

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

    private void tbl_nhanvien_lamviecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nhanvien_lamviecMouseClicked
        selectedTable = "tbl_nhanvien_lamviec";
        Integer row = tbl_nhanvien_lamviec.getSelectedRow();
        txtMaQR.setText(tbl_nhanvien_lamviec.getValueAt(row, 0).toString());
        txt_ma.setText(tbl_nhanvien_lamviec.getValueAt(row, 1).toString());
        txt_ten.setText(tbl_nhanvien_lamviec.getValueAt(row, 2).toString());
        txt_ngaySinh.setText(tbl_nhanvien_lamviec.getValueAt(row, 3).toString());
        if (tbl_nhanvien_lamviec.getValueAt(row, 4).equals("Nam")) {
            rdo_nam.setSelected(true);
        } else {
            rdo_nu.setSelected(true);
        }
        txt_diachi.setText(tbl_nhanvien_lamviec.getValueAt(row, 5).toString());
        txt_dienthoai.setText(tbl_nhanvien_lamviec.getValueAt(row, 6).toString());
        txt_email.setText(tbl_nhanvien_lamviec.getValueAt(row, 7).toString());
        txt_mk.setText(tbl_nhanvien_lamviec.getValueAt(row, 8).toString());
        cbo_vaitro.setSelectedItem(tbl_nhanvien_lamviec.getValueAt(row, 9).toString());
        if (tbl_nhanvien_lamviec.getValueAt(row, 10).equals("đang hoạt động")) {
            rdo_dhd.setSelected(true);
        } else {
            rdo_nhd.setSelected(true);
        }
    }//GEN-LAST:event_tbl_nhanvien_lamviecMouseClicked

    private void btn_stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_stopActionPerformed
        // TODO add your handling code here:
        stopCapture();
    }//GEN-LAST:event_btn_stopActionPerformed

    private void btn_qrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qrActionPerformed
        // TODO add your handling code here:
        initWebcam();
        captureThread();
        loadDataDangHd();
    }//GEN-LAST:event_btn_qrActionPerformed

    private void tbl_nhanvien_NLVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nhanvien_NLVMouseClicked
        // TODO add your handling code here:
        selectedTable = "tbl_nhanvien_NLV";
        Integer row = tbl_nhanvien_NLV.getSelectedRow();
        txtMaQR.setText(tbl_nhanvien_NLV.getValueAt(row, 0).toString());
        txt_ma.setText(tbl_nhanvien_NLV.getValueAt(row, 1).toString());
        txt_ten.setText(tbl_nhanvien_NLV.getValueAt(row, 2).toString());
        txt_ngaySinh.setText(tbl_nhanvien_NLV.getValueAt(row, 3).toString());
        if (tbl_nhanvien_NLV.getValueAt(row, 4).equals("Nam")) {
            rdo_nam.setSelected(true);
        } else {
            rdo_nu.setSelected(true);
        }
        txt_diachi.setText(tbl_nhanvien_NLV.getValueAt(row, 5).toString());
        txt_dienthoai.setText(tbl_nhanvien_NLV.getValueAt(row, 6).toString());
        txt_email.setText(tbl_nhanvien_NLV.getValueAt(row, 7).toString());
        txt_mk.setText(tbl_nhanvien_NLV.getValueAt(row, 8).toString());
        cbo_vaitro.setSelectedItem(tbl_nhanvien_NLV.getValueAt(row, 9).toString());
        if (tbl_nhanvien_NLV.getValueAt(row, 10).equals("đang hoạt động")) {
            rdo_dhd.setSelected(true);
        } else {
            rdo_nhd.setSelected(true);
        }
    }//GEN-LAST:event_tbl_nhanvien_NLVMouseClicked

    private void txtMaQRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaQRActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtMaQRActionPerformed

    private void txtMaQRCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtMaQRCaretUpdate
        // TODO add your handling code here:

    }//GEN-LAST:event_txtMaQRCaretUpdate

    private void btn_chonCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonCaActionPerformed
        // TODO add your handling code here:
        new FormDanhSachLamViec().setVisible(true);
    }//GEN-LAST:event_btn_chonCaActionPerformed

    private void txt_ngaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ngaySinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ngaySinhActionPerformed

    private void btn_qr01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qr01ActionPerformed
        // TODO add your handling code here:
        initWebcam();
        captureThreadSearch();
        loadDataDangHd();
    }//GEN-LAST:event_btn_qr01ActionPerformed

    private void txt_searchMaQrCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_searchMaQrCaretUpdate
        // TODO add your handling code here:
        searchMaQr();
    }//GEN-LAST:event_txt_searchMaQrCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_chonCa;
    private javax.swing.JButton btn_clear;
    private javax.swing.JButton btn_qr;
    private javax.swing.JButton btn_qr01;
    private javax.swing.JButton btn_stop;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbo_vaitro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lbl_show;
    private javax.swing.JRadioButton rdo_dhd;
    private javax.swing.JRadioButton rdo_nam;
    private javax.swing.JRadioButton rdo_nhd;
    private javax.swing.JRadioButton rdo_nu;
    private javax.swing.JPanel showPanel;
    private javax.swing.JTable tbl_nhanvien_NLV;
    private javax.swing.JTable tbl_nhanvien_lamviec;
    private javax.swing.JTextField txtMaQR;
    private javax.swing.JTextField txt_diachi;
    private javax.swing.JTextField txt_dienthoai;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_ma;
    private javax.swing.JPasswordField txt_mk;
    private javax.swing.JTextField txt_ngaySinh;
    private javax.swing.JTextField txt_searchMaQr;
    private javax.swing.JTextField txt_ten;
    // End of variables declaration//GEN-END:variables
}
