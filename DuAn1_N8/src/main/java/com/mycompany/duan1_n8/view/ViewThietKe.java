/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.duan1_n8.view;

import com.mycompany.duan1_n8.entity.ThietKe;
import com.mycompany.duan1_n8.service.Impl.ThietKeServiceImpl;
import com.mycompany.duan1_n8.service.ThietKeService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author BXT
 */
public class ViewThietKe extends javax.swing.JPanel {

    /**
     * Creates new form ViewThietKe
     */
    private final ThietKeService service;
    private DefaultTableModel tableModel;
    private Random random = new Random();
    private final long date = System.currentTimeMillis();
    private final Date dateNow = new Date(date);
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private int rowSelect = 0;
    private List<ThietKe> list = new ArrayList<>();

    public ViewThietKe() {
        initComponents();
        service = new ThietKeServiceImpl();
        list = service.getAllTK();
        loadData(list);
    }

    private void loadData(List<ThietKe> list1) {
        tableModel = (DefaultTableModel) tableThietKe.getModel();
        tableModel.setRowCount(0);
        for (ThietKe thietKe : list1) {
            tableModel.addRow(new Object[]{
                thietKe.getId(), thietKe.getMa(), thietKe.getTen(), sdf.format(thietKe.getNgayTao()), sdf.format(thietKe.getNgaySua())
            });
        }
    }

    private boolean checkValidate(String check) {
        if (txt_ten.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Khong Duoc De Trong");
        }
        return true;
    }

    private boolean checkTrung(String maThietKe) {
        for (ThietKe thietKe : service.getAllTK()) {
            if (thietKe.getMa().equals(maThietKe)) {
                return true;
            }
        }
        return false;
    }

    private ThietKe addForm() {
        ThietKe newThietKe = ThietKe.builder()
                .ma("TK" + random.nextInt())
                .ten(txt_ten.getText().trim())
                .ngayTao(dateNow)
                .ngaySua(dateNow)
                .build();
        System.out.println("Add Form Thanh Cong");
        return newThietKe;
    }

    private ThietKe updateForm() {
        for (ThietKe thietKe : service.getAllTK()) {
            ThietKe updateThietKe = ThietKe.builder()
                    .id(Long.parseLong(txt_id.getText().trim()))
                    .ma(txt_ma.getText().trim())
                    .ten(txt_ten.getText().trim())
                    .ngayTao(thietKe.getNgayTao())
                    .ngaySua(dateNow)
                    .build();
            System.out.println("Update Form Thanh Cong");
            return updateThietKe;
        }
        return null;
    }

    private void addData() {
        if (checkTrung(txt_ma.getText().trim())) {
            JOptionPane.showMessageDialog(this, "Thiet Ke Nay Da Ton Tai");
            int index = JOptionPane.showConfirmDialog(this, "Ban Co Muon Them Vao Chu ?", "Thong Bao", JOptionPane.YES_NO_OPTION);
            if (index == JOptionPane.YES_OPTION) {
                String add = service.addThietKe(addForm());
                list = service.getAllTK();
                loadData(list);
                lamMoi();
                JOptionPane.showMessageDialog(this, add);
            } else {
                lamMoi();
            }
        } else {
            if (!checkValidate("Add")) {
                return;
            } else {
                String add = service.addThietKe(addForm());
                list = service.getAllTK();
                loadData(list);
                lamMoi();
                JOptionPane.showMessageDialog(this, add);
            }
        }
    }

    private void lamMoi() {
        txt_id.setText("");
        txt_ma.setText("");
        txt_ten.setText("");
        txtngayTao.setText("");
        txtNgaySua.setText("");
    }

    private void mouseClick() {
        rowSelect = tableThietKe.getSelectedRow();
        ThietKe indexSelect = service.getAllTK().get(rowSelect);
        txt_id.setText(tableThietKe.getValueAt(rowSelect, 0).toString());
        txt_ma.setText(indexSelect.getMa());
        txt_ten.setText(indexSelect.getTen());
        txtngayTao.setText(sdf.format(indexSelect.getNgayTao()));
        txtNgaySua.setText(sdf.format(indexSelect.getNgaySua()));
    }

    private void updateData() {
        rowSelect = tableThietKe.getSelectedRow();
        if (rowSelect < 0) {
            JOptionPane.showMessageDialog(this, "Vui Long Chon Dong De Cap Nhat");
        } else {
            if (!checkValidate("update")) {
                return;
            }
            ThietKe tk = updateForm();
            tk.setId(service.getAllTK().get(rowSelect).getId());
            String update = service.updateThietKe(tk);
            list = service.getAllTK();
            loadData(list);
            lamMoi();
            JOptionPane.showMessageDialog(this, update);
        }
    }

    private void deleteData() {
        rowSelect = tableThietKe.getSelectedRow();
        if (rowSelect < 0) {
            JOptionPane.showMessageDialog(this, "Vui Long Chon Dong De Xoa");
        } else {
            int index = JOptionPane.showConfirmDialog(this, "Ban Chac Chan Xoa Chu ?", "Thong Bao", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (index == JOptionPane.YES_OPTION) {
                ThietKe indexDelete = new ThietKe();
                indexDelete.setId(service.getAllTK().get(rowSelect).getId());
                String delete = service.deleteThietKe(indexDelete);
                list = service.getAllTK();
                loadData(list);
                lamMoi();
                JOptionPane.showMessageDialog(this, delete);
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

        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_ten = new javax.swing.JTextField();
        msgTen = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_id = new javax.swing.JLabel();
        txt_ma = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtngayTao = new javax.swing.JLabel();
        txtNgaySua = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableThietKe = new javax.swing.JTable();

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Tên Thiet Ke");

        msgTen.setText(" ");

        jLabel1.setText("ID Thiet Ke:");

        jLabel2.setText("Ma Thiet Ke");

        jLabel3.setText("Ngay Tao:");

        jLabel4.setText("Ngay Sua");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(msgTen, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(31, 31, 31)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ma, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtngayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNgaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 44, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_ma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(msgTen)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtngayTao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtNgaySua, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(18, Short.MAX_VALUE))))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setBackground(new java.awt.Color(153, 255, 204));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(153, 255, 204));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(153, 255, 204));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setBackground(new java.awt.Color(153, 255, 204));
        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSua, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(16, 16, 16))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnThem)
                .addGap(18, 18, 18)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(btnXoa)
                .addGap(18, 18, 18)
                .addComponent(btnLamMoi)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        tableThietKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Mã Thiet Ke", "Tên Thiet Ke", "Ngay Tao", "Ngay Sua"
            }
        ));
        tableThietKe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableThietKeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableThietKe);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        addData();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        updateData();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        deleteData();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        lamMoi();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tableThietKeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableThietKeMouseClicked
        // TODO add your handling code here:
        mouseClick();
    }//GEN-LAST:event_tableThietKeMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel msgTen;
    private javax.swing.JTable tableThietKe;
    private javax.swing.JLabel txtNgaySua;
    private javax.swing.JLabel txt_id;
    private javax.swing.JLabel txt_ma;
    private javax.swing.JTextField txt_ten;
    private javax.swing.JLabel txtngayTao;
    // End of variables declaration//GEN-END:variables
}
