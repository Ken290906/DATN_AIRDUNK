/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package b1.View;

import b1.entity.GiamGia1;
import b1.services.GiamGiaService;
import java.awt.Color;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.Date;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author DELL
 */
public class giamgia extends javax.swing.JInternalFrame {

    /**
     * Creates new form giamgia
     */
    private List<GiamGia1> lists = new ArrayList<>();
    private List<GiamGia1> lists1 = new ArrayList<>();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private GiamGiaService service = new GiamGiaService();
    private DefaultComboBoxModel cbbModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbbModel2 = new DefaultComboBoxModel();
    DateFormat datetime = new SimpleDateFormat("yyyy-mm-dd");

    public giamgia() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        getContentPane().setBackground(Color.WHITE);
        lists = service.getAll();
        tableModel = (DefaultTableModel) tblHienThi.getModel();
        showDataTable(lists);

        txtTim.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }

        });
    }

    public void timTrangThai() throws ParseException {
        int tt = 0;
        if (cbbTrangThai.getSelectedItem().equals("Đã kết thúc")) {
            tt = 2;
            lists1 = service.timTrangThai(tt);

            showDataTable(lists1);
        } else if (cbbTrangThai.getSelectedItem().equals("Đang diễn ra")) {
            tt = 1;
            lists1 = service.timTrangThai(tt);
            showDataTable(lists1);

        } else if (cbbTrangThai.getSelectedItem().equals("Sắp diễn ra")) {
            tt = 0;
            lists1 = service.timTrangThai(tt);
            showDataTable(lists1);
        } else if (cbbTrangThai.getSelectedItem().equals("Tất cả")) {
            lists1.clear();
            lists = service.getAll();
            showDataTable(lists);
        }
        else {
            showDataTable(lists);
    }
    }

    public void showDataTable(List<GiamGia1> listGiamGia) {
        int i = 0;
        String trangTHai = "";
        tableModel.setRowCount(0);
        for (GiamGia1 gg1 : listGiamGia) {
            i++;
            if (gg1.getTrangThai() == 2) {
                trangTHai = "Het han";
            } else if (gg1.getTrangThai() == 1) {
                trangTHai = "Dang dien ra";
            } else if (gg1.getTrangThai() == 0) {
                trangTHai = "Sap dien ra";
            }
            tableModel.addRow(new Object[]{i, gg1.getMaVCH(), gg1.getMaGiamGia(), gg1.getSoLuong(), gg1.getGiaTri(), gg1.getHanMuc(), gg1.getNgayBatDau(), gg1.getNgayKetThuc(), trangTHai});
        }
    }

    public void showDataCombobox1(List<GiamGia1> listGiamGia) {
        cbbModel2.removeAllElements();
        for (GiamGia1 gg1 : listGiamGia) {
            cbbModel2.addElement(gg1.getNgayKetThuc());

        }
    }

    public void showDataCombobox2(List<GiamGia1> listGiamGia) {
        cbbModel.removeAllElements();
        for (GiamGia1 gg1 : listGiamGia) {
            cbbModel.addElement(gg1.getNgayBatDau());

        }
    }

    private void search() {
        lists = service.Search(txtTim.getText());
        showDataTable(lists);
    }

    public GiamGia1 getFromData() throws ParseException {
        SimpleDateFormat spx = new SimpleDateFormat("yyyy-MM-dd");
        String maVCH = txtVCH.getText();
        String maGiamGia = txtMa.getText();
        String soLuong = txtSoLuong.getText() + "";
        Date currentDateTime = new Date();
        String giaTri = txtGiaTri1.getText() + "";
        String giaTriToiDa = txtGiaTriToiDa1.getText() + "";
        LocalDate ngaybatdau = dcNgayBatDau.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ngayketthuc = dcNgayKetThuc.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int trangThai = 10;

        LocalDate localDate = LocalDate.now();

        if (ngayketthuc.isBefore(localDate)) {
            trangThai = 0;
        } else if (ngaybatdau.isBefore(localDate) && ngayketthuc.isAfter(localDate)) {
            trangThai = 1;
        } else if (ngaybatdau.isBefore(localDate) && ngayketthuc.isBefore(localDate)) {
            trangThai = 2;
        }

        GiamGia1 gg = new GiamGia1(maVCH, maGiamGia, Integer.valueOf(soLuong), Integer.valueOf(giaTri), Float.valueOf(giaTriToiDa), Date.from(ngaybatdau.atStartOfDay(ZoneId.systemDefault()).toInstant()), Date.from(ngayketthuc.atStartOfDay(ZoneId.systemDefault()).toInstant()), true, currentDateTime, "nv001", currentDateTime, "nv001", trangThai);
        return gg;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtVCH = new b1.View.chucnang.TextField();
        txtGiaTriToiDa1 = new b1.View.chucnang.TextField();
        txtSoLuong = new b1.View.chucnang.TextField();
        txtMa = new b1.View.chucnang.TextField();
        btnThem = new b1.View.chucnang.ButtonGradient();
        btnXoa = new b1.View.chucnang.ButtonGradient();
        btnEdit = new b1.View.chucnang.ButtonGradient();
        panel2 = new b1.View.chucnang.Panel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHienThi = new javax.swing.JTable();
        txtTim = new b1.View.chucnang.TextField();
        cbbTrangThai = new b1.View.chucnang.Combobox();
        txtGiaTri1 = new b1.View.chucnang.TextField();
        btnEdit1 = new b1.View.chucnang.ButtonGradient();
        dcNgayBatDau = new com.toedter.calendar.JDateChooser();
        dcNgayKetThuc = new com.toedter.calendar.JDateChooser();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setText("Phiếu Giảm Giá");

        txtVCH.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtVCH.setLabelText("% giảm giá");

        txtGiaTriToiDa1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGiaTriToiDa1.setLabelText("Giá trị tối đa");

        txtSoLuong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtSoLuong.setLabelText("Số Lượng Phiếu giảm giá");

        txtMa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtMa.setLabelText("Mã Phiếu Gỉam");

        btnThem.setForeground(new java.awt.Color(0, 0, 0));
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/plus.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setColor1(new java.awt.Color(0, 255, 255));
        btnThem.setColor2(new java.awt.Color(255, 255, 255));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setForeground(new java.awt.Color(0, 0, 0));
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/icons8-close-30.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setColor1(new java.awt.Color(0, 255, 255));
        btnXoa.setColor2(new java.awt.Color(255, 255, 255));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnEdit.setForeground(new java.awt.Color(0, 0, 0));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/undo.png"))); // NOI18N
        btnEdit.setText("Sửa");
        btnEdit.setColor1(new java.awt.Color(0, 255, 255));
        btnEdit.setColor2(new java.awt.Color(255, 255, 255));
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        tblHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã VCH", "Mã Giảm giá", "Số lượng", "Giá trị", "Hạn mức tối đa", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ));
        tblHienThi.setGridColor(new java.awt.Color(255, 255, 255));
        tblHienThi.setRowHeight(30);
        tblHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHienThiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHienThi);

        txtTim.setLabelText("Tìm kiếm phiếu giảm giá");
        txtTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimActionPerformed(evt);
            }
        });

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Đã kết thúc", "Đang diễn ra", "Sắp diễn ra", "Tất cả" }));
        cbbTrangThai.setLabeText("Trạng thái");
        cbbTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTrangThaiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1371, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel2.addTab("Bảng Phiếu Giảm Gía", jPanel4);

        txtGiaTri1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtGiaTri1.setLabelText("Giá trị");

        btnEdit1.setForeground(new java.awt.Color(0, 0, 0));
        btnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/undo.png"))); // NOI18N
        btnEdit1.setText("Reset");
        btnEdit1.setColor1(new java.awt.Color(0, 255, 255));
        btnEdit1.setColor2(new java.awt.Color(255, 255, 255));
        btnEdit1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdit1ActionPerformed(evt);
            }
        });

        dcNgayBatDau.setDateFormatString("dd-MM-yyyy");

        dcNgayKetThuc.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(177, 177, 177)
                .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(568, 568, 568))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtGiaTriToiDa1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtVCH, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(183, 183, 183))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1388, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(217, 217, 217)
                        .addComponent(dcNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(369, 369, 369)
                        .addComponent(txtGiaTri1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(430, 430, 430)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dcNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVCH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(dcNgayBatDau, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaTriToiDa1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaTri1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dcNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (txtVCH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long ko de trong");
            return;
        }
        if (txtMa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long ko de trong");
            return;
        }

        if (dcNgayBatDau.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui long ko de trong");
            return;
        }
        if (dcNgayKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui long ko de trong");
            return;
        }
        if (txtSoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long ko de trong");
            return;
        }
        if (txtGiaTri1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long ko de trong");
            return;
        }
        int check = JOptionPane.showConfirmDialog(this, "Ban co muon them ko ?");
        if (check == JOptionPane.YES_OPTION) {
            try {
                service.add(getFromData());
            } catch (ParseException ex) {
                Logger.getLogger(giamgia.class.getName()).log(Level.SEVERE, null, ex);
            }
            lists = service.getAll();
            showDataTable(lists);
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        int check = JOptionPane.showConfirmDialog(this, "Ban co muon Xoa ko ?");
        if (check == JOptionPane.YES_OPTION) {
            String xoa = txtVCH.getText();
            service.remove(xoa);
            lists = service.getAll();
            showDataTable(lists);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        if (txtVCH.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long ko de trong");
            return;
        }
        if (txtMa.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long ko de trong");
            return;
        }

        if (dcNgayBatDau.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui long ko de trong");
            return;
        }
        if (dcNgayKetThuc.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui long ko de trong");
            return;
        }
        if (txtSoLuong.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long ko de trong");
            return;
        }
        if (txtGiaTri1.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui long ko de trong");
            return;
        }
        int index = tblHienThi.getSelectedRow();
        GiamGia1 gg = lists.get(index);
        int lc = JOptionPane.showConfirmDialog(this, "Bạn có sửa dữ liệu không?", "Notification", JOptionPane.YES_NO_OPTION);
        if (lc == JOptionPane.YES_OPTION) {
            try {
                service.updateData(getFromData(), gg.getMaVCH());
            } catch (ParseException ex) {
                Logger.getLogger(giamgia.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn NO");
        }
        lists = service.getAll();
        showDataTable(lists);
    }//GEN-LAST:event_btnEditActionPerformed

    private void tblHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHienThiMouseClicked
        // TODO add your handling code here:
        int row = tblHienThi.getSelectedRow();
        GiamGia1 gg = lists.get(row);
        txtVCH.setText(gg.getMaVCH());
        txtMa.setText(gg.getMaGiamGia());
        txtSoLuong.setText(String.valueOf(gg.getSoLuong()));
        txtGiaTri1.setText(String.valueOf(gg.getGiaTri()));
        dcNgayKetThuc.setDate(gg.getNgayKetThuc());
        dcNgayBatDau.setDate(gg.getNgayBatDau());
        txtGiaTriToiDa1.setText(String.valueOf(gg.getHanMuc()));

    }//GEN-LAST:event_tblHienThiMouseClicked

    private void txtTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimActionPerformed

    private void btnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdit1ActionPerformed
        txtGiaTri1.setText("");
        txtGiaTriToiDa1.setText("");
        txtMa.setText("");
        dcNgayKetThuc.setDate(null);
        dcNgayBatDau.setDate(null);
        txtSoLuong.setText("");
        txtTim.setText("");
        txtVCH.setText("");
    }//GEN-LAST:event_btnEdit1ActionPerformed

    private void cbbTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTrangThaiActionPerformed
        try {
            timTrangThai();
        } catch (ParseException ex) {
            Logger.getLogger(giamgia.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_cbbTrangThaiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private b1.View.chucnang.ButtonGradient btnEdit;
    private b1.View.chucnang.ButtonGradient btnEdit1;
    private b1.View.chucnang.ButtonGradient btnThem;
    private b1.View.chucnang.ButtonGradient btnXoa;
    private b1.View.chucnang.Combobox cbbTrangThai;
    private com.toedter.calendar.JDateChooser dcNgayBatDau;
    private com.toedter.calendar.JDateChooser dcNgayKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private b1.View.chucnang.Panel panel2;
    private javax.swing.JTable tblHienThi;
    private b1.View.chucnang.TextField txtGiaTri1;
    private b1.View.chucnang.TextField txtGiaTriToiDa1;
    private b1.View.chucnang.TextField txtMa;
    private b1.View.chucnang.TextField txtSoLuong;
    private b1.View.chucnang.TextField txtTim;
    private b1.View.chucnang.TextField txtVCH;
    // End of variables declaration//GEN-END:variables
}
