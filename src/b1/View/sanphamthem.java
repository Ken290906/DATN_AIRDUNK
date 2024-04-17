/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package b1.View;

import b1.View.intefacee.interfacesp;
import b1.View.intefacee.iterface2;
import ViewModelSP.sanphamchitietviewmodel;
import b1.entity.Chatlieusp;
import b1.entity.Daysp;
import b1.entity.Dodaysp;
import b1.entity.DongSanPham;
import b1.entity.Matdesanpham;
import b1.entity.MauSanPham;
import b1.entity.SizeSanPham;
import b1.entity.chitietsanpham;
import b1.entity.hangsanxuat;
import b1.services.chatlieuservices;
import b1.services.chitietsanphamp2services;
import b1.services.dayservices;
import b1.services.dodayservices;
import b1.services.dspservices;
import b1.services.hangsxservices;
import b1.services.matdeservices;
import b1.services.sizeservices;
import b1.services.tenmauservices;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 *
 * @author DELL
 */
public class sanphamthem extends javax.swing.JFrame {

    interfacesp itf = new iterface2() {

    };

//    private void somemothod() {
//        sanpham sp = new sanpham();
//        sp.setOtherFrame(this);
//        List<sanphamchitietviewmodel> listspvm = new ArrayList<>();
//        sp.showdata2(listspvm);
//    }
    private chitietsanphamp2services ctsps = new chitietsanphamp2services();
    private List<sanphamchitietviewmodel> listviewmodel = new ArrayList<>();
    private List<hangsanxuat> listhsx = new ArrayList<>();
    private hangsxservices hsxs = new hangsxservices();
    private List<DongSanPham> listdsp = new ArrayList<>();
    private dspservices dsps = new dspservices();
    private List<MauSanPham> listmsp = new ArrayList<>();
    private tenmauservices ms = new tenmauservices();
    private List<Dodaysp> listddsp = new ArrayList<>();
    private dodayservices dds = new dodayservices();
    private List<Chatlieusp> clsp = new ArrayList<>();
    private chatlieuservices cls = new chatlieuservices();
    private List<Matdesanpham> listmdsp = new ArrayList<>();
    private matdeservices mds = new matdeservices();
    private List<Daysp> listdaysp = new ArrayList<>();
    private dayservices ds = new dayservices();
    private List<SizeSanPham> listsize = new ArrayList<>();
    private sizeservices ss = new sizeservices();
    private DefaultComboBoxModel combo1 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo2 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo3 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo4 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo5 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo6 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo7 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo8 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combo9 = new DefaultComboBoxModel();
    private sanpham sp = new sanpham();

    /**
     * Creates new form sanphamthem
     */
    public sanphamthem() {
        initComponents();
        setLocation(361, 200);
        combo1 = (DefaultComboBoxModel) cbbhang.getModel();
        listhsx = hsxs.getall();
        showcombobox1(listhsx);
        combo2 = (DefaultComboBoxModel) cbbten.getModel();
        listdsp = dsps.getall();
        showcombo2(listdsp);
        combo3 = (DefaultComboBoxModel) cbbphoimau.getModel();
        listmsp = ms.getall();
        showcombo3(listmsp);

        combo5 = (DefaultComboBoxModel) cbbdoday.getModel();
        listddsp = dds.getall();
        showcombo5(listddsp);
        combo6 = (DefaultComboBoxModel) cbbchatlieu.getModel();
        clsp = cls.getall();
        showcombo6(clsp);
        combo7 = (DefaultComboBoxModel) cbbmatde.getModel();
        listmdsp = mds.getall();
        showcombo7(listmdsp);
        combo8 = (DefaultComboBoxModel) cbbday.getModel();
        listdaysp = ds.getall();
        showcombo8(listdaysp);

        sanpham sp = new sanpham();
        sp.setVisible(true);
        sp.showdata2(listviewmodel);
//        somemothod();
    }

    public void showcombobox1(List<hangsanxuat> listhsx) {
        combo1.removeAllElements();
        for (hangsanxuat object : listhsx) {
            combo1.addElement(object.getTenhang());
        }

    }

    public void showcombo2(List<DongSanPham> listdsp) {
        combo2.removeAllElements();
        for (DongSanPham dongSanPham : listdsp) {
            combo2.addElement(dongSanPham.getTendsp());
        }

    }

    public void showcombo3(List<MauSanPham> listmau) {
        combo3.removeAllElements();
        for (MauSanPham mauSanPham : listmau) {
            combo3.addElement(mauSanPham.getTenmau());
        }

    }

    public void showcombo4(List<SizeSanPham> listsize) {
        combo4.removeAllElements();
        for (SizeSanPham sizeSanPham : listsize) {
            combo4.addElement(sizeSanPham.getMasize());
        }

    }

    public void showcombo5(List<Dodaysp> listdoday) {
        combo5.removeAllElements();
        for (Dodaysp dodaysp : listdoday) {
            combo5.addElement(dodaysp.getDoday());
        }
    }

    public void showcombo6(List<Chatlieusp> listchatlieu) {
        combo6.removeAllElements();
        for (Chatlieusp chatlieusp : listchatlieu) {
            combo6.addElement(chatlieusp.getChatlieusp());
        }

    }

    public void showcombo7(List<Matdesanpham> listmd) {
        combo7.removeAllElements();
        for (Matdesanpham matdesanpham : listmd) {
            combo7.addElement(matdesanpham.getMatde());
        }

    }

    public void showcombo8(List<Daysp> listday) {
        combo8.removeAllElements();
        for (Daysp daysp : listday) {
            combo8.addElement(daysp.getDaysp());
        }

    }

    public void showcombo9(List<SizeSanPham> listSIZE) {
        combo9.removeAllElements();
        for (SizeSanPham sizeSanPham : listSIZE) {
            combo9.addElement(sizeSanPham.getMasize());
        }

    }

    private String generateRandomCTSP() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(1000) + 1;
        return "CTSP" + randomNumber;
    }

    public chitietsanpham getformdata() {
        String CTSP = generateRandomCTSP();
        String HSX = (String) cbbhang.getSelectedItem();
        String IDDSP = (String) cbbten.getSelectedItem();
        String IDPM = (String) cbbphoimau.getSelectedItem();
        String IDS = txtsize.getText();
        String IDDD = (String) cbbdoday.getSelectedItem();
        String IDCL = (String) cbbchatlieu.getSelectedItem();
        String IDMD = (String) cbbmatde.getSelectedItem();
        String IDD = (String) cbbday.getSelectedItem();
        String GB = txtgb.getText();
        String SL = txtsoluong.getText();

        if (txtgb.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng hãy nhập giá mà bạn muốn vào?");
            return null;
        }
        if (GB.matches("[A-Z a-z]+")) {
            JOptionPane.showMessageDialog(this, "Không thể đặt giá bằng chữ");
            return null;
        }
        double gb = Double.parseDouble(txtgb.getText());
        if (Double.parseDouble(txtgb.getText()) <= 0) {
            JOptionPane.showMessageDialog(this, "Gía tiền không thể âm hoặc không thể = 0");
            return null;
        }
        if (IDS.matches("[A-Z a-z]+")) {
            JOptionPane.showMessageDialog(this, "Không thể đặt Size bằng chữ");
            return null;
        }
        if (txtsize.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng hãy nhập size mà bạn muốn vào?");
            return null;
        }
        double size = Double.parseDouble(txtsize.getText());
        if (Double.parseDouble(txtsize.getText()) >= 50) {
            JOptionPane.showMessageDialog(this, "Kích cỡ quả lớn đối với ngườXzi Châu Á");
            return null;
        }
        if (Double.parseDouble(txtsize.getText()) <= 30) {
            JOptionPane.showMessageDialog(this, "Không có loại size nào bé như thế");
            return null;
        }
        
        try {
            ByteArrayOutputStream out = QRCode.from(CTSP)
                    .to(ImageType.PNG).stream();

            String fileName = "C:\\QRSanPham\\" + CTSP + ".png"; // Tên file ảnh QRCode

            // Ghi ảnh QR vào file
            FileOutputStream fout = new FileOutputStream(new File(fileName));
            fout.write(out.toByteArray());
            fout.flush();
        } catch (IOException ex) {
            Logger.getLogger(hoadon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        chitietsanpham ctsp1 = new chitietsanpham(CTSP, HSX, IDDSP, IDPM, IDS, IDDD, IDCL, IDMD, IDD, Integer.valueOf(GB), 0, Integer.valueOf(SL));
        return ctsp1;
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
        txtgb = new b1.View.chucnang.TextField();
        cbbten = new b1.View.Combobox();
        cbbphoimau = new b1.View.Combobox();
        cbbchatlieu = new b1.View.Combobox();
        cbbdoday = new b1.View.Combobox();
        cbbhang = new b1.View.Combobox();
        cbbmatde = new b1.View.Combobox();
        cbbday = new b1.View.Combobox();
        btnthem1 = new b1.View.chucnang.ButtonGradient();
        btnback = new b1.View.chucnang.ButtonGradient();
        txtsize = new b1.View.chucnang.TextField();
        btnreseat = new b1.View.chucnang.ButtonGradient();
        txtsoluong = new b1.View.chucnang.TextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        txtgb.setForeground(new java.awt.Color(255, 51, 51));
        txtgb.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtgb.setLabelText("Gía bán");
        txtgb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtgbActionPerformed(evt);
            }
        });

        cbbten.setLabeText("tên");

        cbbphoimau.setLabeText("phối màu");
        cbbphoimau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbphoimauActionPerformed(evt);
            }
        });

        cbbchatlieu.setLabeText("chất liệu");

        cbbdoday.setLabeText("độ dày");

        cbbhang.setLabeText("hãng");

        cbbmatde.setLabeText("mặt đế");

        cbbday.setLabeText("dây");

        btnthem1.setBackground(new java.awt.Color(153, 255, 255));
        btnthem1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnthem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/add-user.png"))); // NOI18N
        btnthem1.setColor1(new java.awt.Color(204, 204, 255));
        btnthem1.setColor2(new java.awt.Color(255, 255, 255));
        btnthem1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnthem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnthem1MouseClicked(evt);
            }
        });
        btnthem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnthem1ActionPerformed(evt);
            }
        });

        btnback.setBackground(new java.awt.Color(153, 255, 255));
        btnback.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnback.setForeground(new java.awt.Color(255, 51, 51));
        btnback.setText("BACK");
        btnback.setColor1(new java.awt.Color(204, 204, 255));
        btnback.setColor2(new java.awt.Color(255, 255, 255));
        btnback.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnback.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnbackMouseClicked(evt);
            }
        });
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        txtsize.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtsize.setLabelText("Size ");
        txtsize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtsizeActionPerformed(evt);
            }
        });

        btnreseat.setBackground(new java.awt.Color(153, 255, 255));
        btnreseat.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnreseat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/undo.png"))); // NOI18N
        btnreseat.setColor1(new java.awt.Color(204, 204, 255));
        btnreseat.setColor2(new java.awt.Color(255, 255, 255));
        btnreseat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnreseat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnreseatMouseClicked(evt);
            }
        });
        btnreseat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreseatActionPerformed(evt);
            }
        });

        txtsoluong.setLabelText("Số lượng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(95, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnreseat, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnthem1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtsoluong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbbphoimau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtgb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                            .addComponent(cbbten, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(370, 370, 370)
                                .addComponent(cbbchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(372, 372, 372)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtsize, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbbdoday, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbbday, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbbhang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbbmatde, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(149, 149, 149))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtgb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtsize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(152, 152, 152)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbdoday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(150, 150, 150)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbphoimau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbbhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)
                        .addComponent(cbbmatde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)
                        .addComponent(cbbday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addComponent(txtsoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnback, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnthem1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnreseat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
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

    private void cbbphoimauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbphoimauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbphoimauActionPerformed

    private void txtgbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtgbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtgbActionPerformed

    private void btnthem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnthem1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnthem1MouseClicked

    private void btnthem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnthem1ActionPerformed
        // TODO add your handling code here:
        int dk = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thêm?");
        if (dk == JOptionPane.YES_OPTION) {
            itf.Add(getformdata());
            listviewmodel = itf.getall();
            sanpham sp = new sanpham();
            sp.setVisible(true);
            sp.showdata2(listviewmodel);

        }

        if (dk == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn xóa");
            return;
        }
        if (dk == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn Sửa");
            return;
        }

    }//GEN-LAST:event_btnthem1ActionPerformed

    private void btnbackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbackMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbackMouseClicked

    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        // TODO add your handling code here:
        sanpham sp = new sanpham();
        JPanel panelCTSP = sp.getPanelSPCT(); // Sử dụng instance hiện tại của ViewSanPham
        JPanel viewThemThuocTinhSP = jPanel1;
        viewThemThuocTinhSP.removeAll();
        viewThemThuocTinhSP.add(panelCTSP);
        viewThemThuocTinhSP.setLayout(new BorderLayout());
        viewThemThuocTinhSP.add(panelCTSP, BorderLayout.CENTER);
        viewThemThuocTinhSP.revalidate();
        viewThemThuocTinhSP.repaint();

        sp.setVisible(true);
        sp.showdata2(listviewmodel);
    }//GEN-LAST:event_btnbackActionPerformed

    private void txtsizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtsizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtsizeActionPerformed

    private void btnreseatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnreseatMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnreseatMouseClicked

    private void btnreseatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreseatActionPerformed
        // TODO add your handling code here:
        cbbhang.setSelectedIndex(0);
        cbbten.setSelectedIndex(0);
        cbbphoimau.setSelectedIndex(0);
        txtsize.getText();
        cbbdoday.setSelectedIndex(0);
        cbbchatlieu.setSelectedIndex(0);
        cbbmatde.setSelectedIndex(0);
        cbbday.setSelectedIndex(0);
        txtgb.setText("");

    }//GEN-LAST:event_btnreseatActionPerformed

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
            java.util.logging.Logger.getLogger(sanphamthem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sanphamthem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sanphamthem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sanphamthem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sanphamthem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private b1.View.chucnang.ButtonGradient btnback;
    private b1.View.chucnang.ButtonGradient btnreseat;
    private b1.View.chucnang.ButtonGradient btnthem1;
    private b1.View.Combobox cbbchatlieu;
    private b1.View.Combobox cbbday;
    private b1.View.Combobox cbbdoday;
    private b1.View.Combobox cbbhang;
    private b1.View.Combobox cbbmatde;
    b1.View.Combobox cbbphoimau;
    private b1.View.Combobox cbbten;
    private javax.swing.JPanel jPanel1;
    private b1.View.chucnang.TextField txtgb;
    private b1.View.chucnang.TextField txtsize;
    private b1.View.chucnang.TextField txtsoluong;
    // End of variables declaration//GEN-END:variables
}
