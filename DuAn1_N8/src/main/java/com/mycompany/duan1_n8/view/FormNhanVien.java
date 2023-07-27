/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.duan1_n8.view;

import com.mycompany.duan1_n8.utilities.Until;
import com.mycompany.duan1_n8.entity.ChucVu;
import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.repository.ChucVuRepository;
import com.mycompany.duan1_n8.service.Impl.NhanVienServiceImpl;
import com.mycompany.duan1_n8.service.NhanVienService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import com.github.sarxos.webcam.WebcamResolution;

import com.google.zxing.BinaryBitmap;

import com.google.zxing.LuminanceSource;

import com.google.zxing.MultiFormatReader;

import com.google.zxing.NotFoundException;

import com.google.zxing.Result;

import java.awt.Dimension;

import java.awt.image.BufferedImage;

import java.util.logging.Level;

import java.util.logging.Logger;

import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

import com.google.zxing.common.HybridBinarizer;

/**
 *
 * @author anhtu
 */
public class FormNhanVien extends javax.swing.JPanel {

    private final NhanVienService nhanVienService = new NhanVienServiceImpl();
    private ChucVuRepository chucVuRepository = new ChucVuRepository();

    private DefaultTableModel model;
    private DefaultComboBoxModel defaultComboBoxModel;

    private Webcam webcam;
    private WebcamPanel webcamPanel;
    private Thread captureThread;

    public FormNhanVien() {
        initComponents();
        for (NhanVien nv : nhanVienService.getAll()) {
            if (nv.getTrangThai().equals(0) == true) {
                loadData();

            } else {
                loadData1();
            }

        }

        cbbChucVu();
        txtResult.setText(java.time.LocalDate.now().toString());
    }

   
    

    public void loadData() {
        List<NhanVien> listNV = nhanVienService.getAll();
        model = (DefaultTableModel) tbl_nhanvien_lamviec.getModel();
        model.setRowCount(0);
        for (NhanVien p : listNV) {
            model.addRow(new Object[]{
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

    public void loadData1() {
        List<NhanVien> listNV = nhanVienService.getAll();
        model = (DefaultTableModel) tbl_nhanvien_NgungHD.getModel();
        model.setRowCount(0);
        for (NhanVien p : listNV) {
            model.addRow(new Object[]{
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

    void cbbChucVu() {
        defaultComboBoxModel = (DefaultComboBoxModel) cbo_vaitro.getModel();
        for (ChucVu chucVu : chucVuRepository.getAll()) {
            defaultComboBoxModel.addElement(chucVu);
        }
    }

    private NhanVien getData() {
        NhanVien nv = new NhanVien();
        String ma = txt_ma.getText().trim();
        nv.setMa(ma);
        String ten = txt_ten.getText().trim();
        nv.setTen(ten);

        SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
        try {
            nv.setNgaySinh(dfm.parse(txt_ngaySinh.getText().trim()));
        } catch (ParseException ex) {
            Logger.getLogger(FormNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }

        boolean gioiTinh;
        if (rdo_nam.isSelected()) {
            gioiTinh = true;
        } else {
            gioiTinh = false;
        }
        nv.setGioiTinh(gioiTinh);
        String diaChi = txt_diachi.getText().trim();
        nv.setQueQuan(diaChi);
        Integer sdt = Integer.valueOf(txt_dienthoai.getText().trim());
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
        buttonGroup3 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
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
        btn_excel = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_qr = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        txt_mk = new javax.swing.JPasswordField();
        lbl_show = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rdo_dhd = new javax.swing.JRadioButton();
        txt_ngaySinh = new javax.swing.JTextField();
        rdo_nhd = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        txt_timsdt = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_nhanvien_lamviec = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_nhanvien_NgungHD = new javax.swing.JTable();
        showPanel = new javax.swing.JPanel();
        txtResult = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1060, 631));

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
        rdo_nam.setSelected(true);
        rdo_nam.setText("Nam");

        rdo_nu.setBackground(new java.awt.Color(255, 255, 255));
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

        btn_excel.setBackground(new java.awt.Color(153, 255, 204));
        btn_excel.setText("excel");
        btn_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excelActionPerformed(evt);
            }
        });

        btn_sua.setBackground(new java.awt.Color(153, 255, 204));
        btn_sua.setText("Sửa nhân viên");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });

        btn_qr.setBackground(new java.awt.Color(153, 255, 204));
        btn_qr.setText("quét QR");
        btn_qr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_qrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_excel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_sua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_them, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(btn_qr, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(btn_excel)
                .addGap(18, 18, 18)
                .addComponent(btn_qr)
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

        rdo_dhd.setText("đang hoạt động");

        txt_ngaySinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ngaySinhActionPerformed(evt);
            }
        });

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
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        txt_timsdt.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txt_timsdtInputMethodTextChanged(evt);
            }
        });
        txt_timsdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_timsdtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timsdtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_timsdtKeyTyped(evt);
            }
        });

        jLabel13.setText("Tìm theo số điện thoại");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 304, Short.MAX_VALUE))
                    .addComponent(txt_timsdt))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_timsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jTabbedPane3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tbl_nhanvien_lamviec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "Ngày sinh", "Giới tính", "Địa chỉ", "SĐT", "Email", "Mật khẩu", "Vai trò", "Trạng thái"
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
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Đang làm việc", jPanel5);

        tbl_nhanvien_NgungHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "Ngày sinh", "Giới tính", "Địa chỉ", "SĐT", "Email", "Mật khẩu", "Vai trò", "Trạng thái"
            }
        ));
        tbl_nhanvien_NgungHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nhanvien_NgungHDMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_nhanvien_NgungHD);

        jTabbedPane3.addTab("Ngừng hoạt động", jScrollPane4);

        showPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setText("mã QR");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(showPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtResult, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel15)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTabbedPane3))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(jLabel15)
                            .addGap(18, 18, 18)
                            .addComponent(txtResult, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Thông Tin Nhân Viên", jPanel9);

        jButton5.setText("jButton5");
        jTabbedPane1.addTab("Ca làm", jButton5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdo_nuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdo_nuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdo_nuActionPerformed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        // TODO add your handling code here:
        NhanVien nv = getData();
        String query = nhanVienService.add(nv);
        JOptionPane.showMessageDialog(this, query);
        loadData();
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excelActionPerformed
        try {
            // TODO add your handling code here:

            final List<NhanVien> nhanVien = getBooks();
            final String excelFilePath = "C:/Users/BXT/Desktop/excel/thucTapL0.xlsx";
            writeExcel(nhanVien, excelFilePath);
        } catch (IOException ex) {
            Logger.getLogger(FormNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_excelActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        Integer row = tbl_nhanvien_lamviec.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "vui lòng chọn dòng trên table");
            return;

        }
        NhanVien nv = getData();
        nv.setId(nhanVienService.getAll().get(row).getId());
        String query = nhanVienService.Update(nv);
        JOptionPane.showMessageDialog(this, query);
        loadData();
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_qrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_qrActionPerformed
        // TODO add your handling code here:

        
    }//GEN-LAST:event_btn_qrActionPerformed

    private void lbl_showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_showMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbl_showMouseClicked

    private void txt_ngaySinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ngaySinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ngaySinhActionPerformed

    private void txt_timsdtInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txt_timsdtInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timsdtInputMethodTextChanged

    private void txt_timsdtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timsdtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timsdtKeyPressed

    private void txt_timsdtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timsdtKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timsdtKeyReleased

    private void txt_timsdtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timsdtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_timsdtKeyTyped

    private void tbl_nhanvien_lamviecMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nhanvien_lamviecMouseClicked
        Integer row = tbl_nhanvien_lamviec.getSelectedRow();
        txt_ma.setText(tbl_nhanvien_lamviec.getValueAt(row, 0).toString());
        txt_ten.setText(tbl_nhanvien_lamviec.getValueAt(row, 1).toString());
        txt_ngaySinh.setText(tbl_nhanvien_lamviec.getValueAt(row, 2).toString());
        if (tbl_nhanvien_lamviec.getValueAt(row, 3).equals("Nam")) {
            rdo_nam.setSelected(true);
        } else {
            rdo_nu.setSelected(true);
        }
        txt_diachi.setText(tbl_nhanvien_lamviec.getValueAt(row, 4).toString());
        txt_dienthoai.setText(tbl_nhanvien_lamviec.getValueAt(row, 5).toString());
        txt_email.setText(tbl_nhanvien_lamviec.getValueAt(row, 6).toString());
        txt_mk.setText(tbl_nhanvien_lamviec.getValueAt(row, 7).toString());
        cbo_vaitro.setSelectedItem(tbl_nhanvien_lamviec.getValueAt(row, 8).toString());
        if (tbl_nhanvien_lamviec.getValueAt(row, 9).equals("đang hoạt động")) {
            rdo_dhd.setSelected(true);
        } else {
            rdo_nhd.setSelected(true);
        }
    }//GEN-LAST:event_tbl_nhanvien_lamviecMouseClicked

    private void tbl_nhanvien_NgungHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nhanvien_NgungHDMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_nhanvien_NgungHDMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_excel;
    private javax.swing.JButton btn_qr;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbo_vaitro;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lbl_show;
    private javax.swing.JRadioButton rdo_dhd;
    private javax.swing.JRadioButton rdo_nam;
    private javax.swing.JRadioButton rdo_nhd;
    private javax.swing.JRadioButton rdo_nu;
    private javax.swing.JPanel showPanel;
    private javax.swing.JTable tbl_nhanvien_NgungHD;
    private javax.swing.JTable tbl_nhanvien_lamviec;
    private javax.swing.JLabel txtResult;
    private javax.swing.JTextField txt_diachi;
    private javax.swing.JTextField txt_dienthoai;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_ma;
    private javax.swing.JPasswordField txt_mk;
    private javax.swing.JTextField txt_ngaySinh;
    private javax.swing.JTextField txt_ten;
    private javax.swing.JTextField txt_timsdt;
    // End of variables declaration//GEN-END:variables

    public void writeExcel(List<NhanVien> nhanVien, String excelFilePath) throws IOException {

        Workbook workbook = getWorkbook(excelFilePath);

        Sheet sheet = workbook.createSheet("Books"); // Create sheet with sheet name

        int rowIndex = 0;

        // Write header
        writeHeader(sheet, rowIndex);

        // Write data
        rowIndex++;
        for (NhanVien student1 : nhanVienService.getAll()) {
            Row row = sheet.createRow(rowIndex);
            writeBook(student1, row);
            rowIndex++;
        }

        // Write footer
        writeFooter(sheet, rowIndex);

        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }

    // Create dummy data
    private List<NhanVien> getBooks() {
        List<NhanVien> listBook = new ArrayList<>();

        for (int i = 1; i <= 11; i++) {
            for (NhanVien nhanVien : nhanVienService.getAll()) {
                listBook.add(nhanVien);
            }
        }
        return listBook;
    }

    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(Until.COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("id");

        cell = row.createCell(Until.COLUMN_INDEX_MA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ma");

        cell = row.createCell(Until.COLUMN_INDEX_TEN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ten");

        cell = row.createCell(Until.COLUMN_INDEX_GIOITINH);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("gioiTinh");

        cell = row.createCell(Until.COLUMN_INDEX_EMAIL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("email");

        cell = row.createCell(Until.COLUMN_INDEX_QUEQUAN);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("queQuan");

        cell = row.createCell(Until.COLUMN_INDEX_NGAYSINH);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("ngaySinh");

        cell = row.createCell(Until.COLUMN_INDEX_SDT);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("sdt");

        cell = row.createCell(Until.COLUMN_INDEX_MATKHAU);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("matKhau");

        cell = row.createCell(Until.COLUMN_INDEX_TRANGTHAI);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("trangThai");

        cell = row.createCell(Until.COLUMN_INDEX_CHUCVU);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("chucVu");

    }

    // Write data
    private static void writeBook(NhanVien nhanVien, Row row) {
        if (Until.cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            //Create CellStyle
            Workbook workbook = row.getSheet().getWorkbook();
            Until.cellStyleFormatNumber = workbook.createCellStyle();
            Until.cellStyleFormatNumber.setDataFormat(format);
        }

        Cell cell = row.createCell(Until.COLUMN_INDEX_ID);
        cell.setCellValue(nhanVien.getId());

        cell = row.createCell(Until.COLUMN_INDEX_MA);
        cell.setCellValue(nhanVien.getMa());

        cell = row.createCell(Until.COLUMN_INDEX_TEN);
        cell.setCellValue(nhanVien.getTen());
        cell.setCellStyle(Until.cellStyleFormatNumber);

        cell = row.createCell(Until.COLUMN_INDEX_GIOITINH);
        cell.setCellValue(nhanVien.getGioiTinh());

        cell = row.createCell(Until.COLUMN_INDEX_EMAIL);
        cell.setCellValue(nhanVien.getEmail());

        cell = row.createCell(Until.COLUMN_INDEX_QUEQUAN);
        cell.setCellValue(nhanVien.getQueQuan());

        cell = row.createCell(Until.COLUMN_INDEX_NGAYSINH);
        cell.setCellValue(nhanVien.getNgaySinh());

        cell = row.createCell(Until.COLUMN_INDEX_SDT);
        cell.setCellValue(nhanVien.getSdt());

        cell = row.createCell(Until.COLUMN_INDEX_MATKHAU);
        cell.setCellValue(nhanVien.getTrangThai());

        cell = row.createCell(Until.COLUMN_INDEX_TRANGTHAI);
        cell.setCellValue(nhanVien.getTrangThai());

        // Create cell formula
        // totalMoney = price * quantity
        cell = row.createCell(Until.COLUMN_INDEX_CHUCVU, CellType.FORMULA);
        cell.setCellStyle(Until.cellStyleFormatNumber);
    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    // Write footer
    private static void writeFooter(Sheet sheet, int rowIndex) {
        // Create row
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(Until.COLUMN_INDEX_CHUCVU, CellType.FORMULA);
        cell.setCellFormula("SUM(E2:E6)");
    }

    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try ( OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
