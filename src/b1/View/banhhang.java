/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package b1.View;

import ViewModelSP.sanphamchitietviewmodel;
import b1.View.chucnang.khachhangbanhang;
import b1.View.chucnang.quetmaqr;
import b1.View.intefacee.interfacesp;
import b1.View.intefacee.iterface2;
import b1.entity.Chatlieusp;
import b1.entity.Daysp;
import b1.entity.GiamGia1;
import b1.entity.HDChiTiet;
import b1.entity.HoaDonBH;
import b1.entity.LichSuHoaDon;
import b1.entity.MauSanPham;
import b1.entity.PTTT;
import b1.entity.hangsanxuat;
import b1.services.GiamGiaService;
import b1.services.HDCTService;
import b1.services.HoaDonBHService;
import b1.services.PTTTservuces;
import b1.services.chatlieuservices;
import b1.services.chitietsanphamp2services;
import b1.services.dayservices;
import b1.services.hangsxservices;
import b1.services.tenmauservices;
import b1.View.chucnang.quetmaqr.QRCodeListener;
import com.itextpdf.text.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import net.sf.jasperreports.engine.JRException;
import print.ReportManager;
import print_model.FieldReportPayment;
import print_model.ParametReportPayment;

/**
 *
 * @author DELL
 */
public class banhhang extends javax.swing.JInternalFrame implements QRCodeListener {
//Table

    private DefaultTableModel dtmHoaDon = new DefaultTableModel();
    private DefaultTableModel bangsanpham = new DefaultTableModel();
    private DefaultTableModel dtmGiohang = new DefaultTableModel();

//Combobox
    private DefaultComboBoxModel comboHang = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbbGiamgia = new DefaultComboBoxModel();
    private DefaultComboBoxModel cbbGiamgia2 = new DefaultComboBoxModel();
    private DefaultComboBoxModel comboPTTT = new DefaultComboBoxModel();
    private DefaultComboBoxModel comboPTTT2 = new DefaultComboBoxModel();
    private DefaultComboBoxModel combomau = new DefaultComboBoxModel();
    private DefaultComboBoxModel comboday = new DefaultComboBoxModel();
    private DefaultComboBoxModel combochatlieu = new DefaultComboBoxModel();
//list
    private List<sanphamchitietviewmodel> listsp = new ArrayList<>();
    private List<FieldReportPayment> listFieldReportPayment = new ArrayList<>();
    private List<hangsanxuat> listhang = new ArrayList<>();
    private List<HoaDonBH> listBH = new ArrayList<>();
    private List<GiamGia1> listVCH = new ArrayList<>();
    private List<GiamGia1> listVCH2 = new ArrayList<>();
    private List<PTTT> listPTTT = new ArrayList<>();
    private List<PTTT> listPTTT2 = new ArrayList<>();
    private List<Chatlieusp> listchatlieu = new ArrayList<>();
    private List<Daysp> listday = new ArrayList<>();
    private List<MauSanPham> listmau = new ArrayList<>();

//ITF
    interfacesp itf = new iterface2() {
    };
//Services
    private HDCTService srhdct = new HDCTService();
    private chitietsanphamp2services sps = new chitietsanphamp2services();
    private hangsxservices hsxs = new hangsxservices();
    private HoaDonBHService srhd = new HoaDonBHService();
    private GiamGiaService srvch = new GiamGiaService();
    private PTTTservuces pttts = new PTTTservuces();
    private chatlieuservices cls = new chatlieuservices();
    private dayservices ds = new dayservices();
    private tenmauservices ms = new tenmauservices();
    DecimalFormat VND = new DecimalFormat("#,##0 đ");

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//Random
    private Random random = new Random();
    
    private ParametReportPayment createParametReportPayment() {
        String maHD = txtMaHD.getText().trim();
        String maNV = txtmaNV.getText().trim();
        int tongTien = Integer.parseInt(txttongtien.getText().replaceAll("[, đ]", ""));
        InputStream maqr = null; // Cần thêm logic để lấy InputStream cho QR Code
        List<FieldReportPayment> fileds = new ArrayList<>();

        // Ví dụ: Tạo dữ liệu cho FieldReportPayment
        // for (int i = 0; i < tableModel.getRowCount(); i++) {
        //     int stt = i + 1;
        //     String name = (String) tableModel.getValueAt(i, 0);
        //     int price = (int) tableModel.getValueAt(i, 1);
        //     int total = (int) tableModel.getValueAt(i, 2);
        //     FieldReportPayment field = new FieldReportPayment(stt, name, price, total);
        //     fileds.add(field);
        // }

        return new ParametReportPayment(maHD, maNV, tongTien, maqr, fileds);
    }

    /**
     * Creates new form gd1
     */
    public banhhang() {
        int a = 0;
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        //Combobox PTTT
        comboPTTT = (DefaultComboBoxModel) cbbPTTT.getModel();
        comboPTTT2 = (DefaultComboBoxModel) cbbPTTTONL.getModel();
        listPTTT = pttts.getall();
        listPTTT2 = pttts.getall();
        showcombobxPTTT(listPTTT);
        showcombobxPTTT2(listPTTT2);

        //bang hoa don
        dtmHoaDon = (DefaultTableModel) tblhoadon.getModel();
        listBH = srhd.getAll();
        showHoaDonBH(listBH);
        //bangsanpham
        bangsanpham = (DefaultTableModel) tbldanhsachsanpham.getModel();
        listsp = sps.getall();
        showdata(listsp);
        //
        dtmGiohang = (DefaultTableModel) tblgiohang.getModel();
        listsp = sps.getall();
        tbldanhsachsanpham.setDefaultEditor(Object.class, null);
        //comboGiamgia
        cbbGiamgia = (DefaultComboBoxModel) cbbgiamrgia.getModel();
        cbbGiamgia2 = (DefaultComboBoxModel) cbbphieugiamgiaONL.getModel();
        listVCH = srvch.cbbBH();
        listVCH2 = srvch.cbbBH();
        showCbbVCH(listVCH);
        showCbbVCH2(listVCH2);
        //combohang
        comboHang = (DefaultComboBoxModel) cbbhang.getModel();
        listhang = hsxs.getall();
        Combobox(listhang);
        //ComboCL
        combochatlieu = (DefaultComboBoxModel) cbbchatlieu.getModel();
        listchatlieu = cls.getall();
        showcomboxCL(listchatlieu);
        //MauSP
        combomau = (DefaultComboBoxModel) cbbmau.getModel();
        listmau = ms.getall();
        showcomboboxMau(listmau);
        //dAYsp
        comboday = (DefaultComboBoxModel) cbbday.getModel();
        listday = ds.getall();
        showcomboboxDay(listday);
        txtSearchHD.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                srhd.searchHDBH(txtSearchHD.getText() + "");
                listBH = srhd.searchHDBH(txtSearchHD.getText());
                showHoaDonBH(listBH);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                srhd.searchHDBH(txtSearchHD.getText() + "");
                listBH = srhd.searchHDBH(txtSearchHD.getText());
                showHoaDonBH(listBH);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                srhd.searchHDBH(txtSearchHD.getText() + "");
                listBH = srhd.searchHDBH(txtSearchHD.getText());
                showHoaDonBH(listBH);
            }

        });

        txtsearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SearchBanhang();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SearchBanhang();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SearchBanhang();
            }

        });
        //searchhang
        cbbhang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timkiem = cbbhang.getSelectedItem().toString();
                if (!timkiem.isEmpty()) {
                    List<sanphamchitietviewmodel> searchedList = searchHang(timkiem);
                    showdata(searchedList);
                } else {
                    // If no manufacturer is selected, reload all products                 
                    sanphamshow();

                }
            }

        });
        cbbday.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timkiem = cbbday.getSelectedItem().toString();
                if (!timkiem.isEmpty()) {
                    List<sanphamchitietviewmodel> searchedList = searchDay(timkiem);
                    showdata(searchedList);
                } else {
                    // If no manufacturer is selected, reload all products                 
                    sanphamshow();

                }
            }

        });
        cbbmau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timkiem = cbbmau.getSelectedItem().toString();
                if (!timkiem.isEmpty()) {
                    List<sanphamchitietviewmodel> searchedList = searchMau(timkiem);
                    showdata(searchedList);
                } else {
                    // If no manufacturer is selected, reload all products                 
                    sanphamshow();

                }
            }

        });
        cbbchatlieu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timkiem = cbbchatlieu.getSelectedItem().toString();
                if (!timkiem.isEmpty()) {
                    List<sanphamchitietviewmodel> searchedList = searchCL(timkiem);
                    showdata(searchedList);
                } else {
                    // If no manufacturer is selected, reload all products                 
                    sanphamshow();

                }
            }

        });
        getkh(txtTenKH.getText(), txtSdtKH.getText());
        getkh(txtTenKhachHangONL.getText(), txtSDTKH.getText());

        cbbgiamrgia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chonphieugiamgia = (String) cbbgiamrgia.getSelectedItem();

                double tongTien = Double.parseDouble(txttongtien.getText().replaceAll("[^0-9]", ""));

                int TienTong = Integer.parseInt(txttong.getText().replaceAll("[^0-9]", ""));
                double giamGia = 0;

                if (TienTong <= 400000 && chonphieugiamgia.equals("NGHÈO")) {
                    JOptionPane.showMessageDialog(null, "Khi chọn sản phẩm giá trên 40000000 mới được PGG này ");
                    return;
                }
                if (TienTong > 400000 && chonphieugiamgia.equals("NGHÈO")) {
                    giamGia = tongTien * 0.2;
                    dudkphieugiamgia();
                }
                if (TienTong <= 700000 && chonphieugiamgia.equals("THƯỜNG")) {
                    JOptionPane.showMessageDialog(null, "Khi chọn sản phẩm giá trên 70000000 mới được PGG này ");
                    return;
                }
                if (TienTong > 700000 && chonphieugiamgia.equals("THƯỜNG")) {
                    giamGia = tongTien * 0.5;
                    dudkphieugiamgia();
                }
                if (TienTong <= 1200000 && chonphieugiamgia.equals("VIP")) {
                    JOptionPane.showMessageDialog(null, "Khi chọn sản phẩm giá trên 90000000 mới được PGG này ");
                    return;
                }
                if (TienTong > 1200000 && chonphieugiamgia.equals("VIP")) {
                    giamGia = tongTien * 0.8;
                    dudkphieugiamgia();
                }

                int tongTienSauGiamGia = (int) (tongTien - giamGia);

                txttongtien.setText(VND.format(tongTienSauGiamGia));
                cbbPTTT.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            String selectedValue = (String) cbbPTTT.getSelectedItem();
                            if (selectedValue.equals("Tiền Mặt")) {
                                txtchuyenkhoan.setEditable(false);
                                txtchuyenkhoan.setText("");
                                txtkhachdua.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        int khachDua = Integer.valueOf(txtkhachdua.getText().trim());
                                        int tongTien = tongTienSauGiamGia;
                                        int thoiLai = khachDua - tongTienSauGiamGia;
                                        txttienthua1.setText(VND.format(thoiLai));

                                    }

                                });
                            } else {
                                txtchuyenkhoan.setEditable(true);
                            }
                        }
                    }
                });

                cbbPTTT.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            String selectedValue = (String) cbbPTTT.getSelectedItem();
                            if (selectedValue.equals("Chuyển khoản")) {
                                txtkhachdua.setEditable(false);
                                txtkhachdua.setText("");
                                txtchuyenkhoan.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        int khachDua = Integer.valueOf(txtchuyenkhoan.getText());
                                        int tongTien = tongTienSauGiamGia;
                                        int thoiLai = khachDua - tongTienSauGiamGia;
                                        txttienthua1.setText(VND.format(thoiLai));

                                    }
                                });
                            } else {
                                txtkhachdua.setEditable(true);
                            }
                        }
                    }
                });
                cbbPTTT.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            String selectedValue = (String) cbbPTTT.getSelectedItem();
                            if (selectedValue.equals("Cả Hai")) {
                                txtkhachdua.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        int chuyenkhoan = Integer.valueOf(txtchuyenkhoan.getText());
                                        int khachDua = Integer.valueOf(txtkhachdua.getText());
                                        int tongTien = tongTienSauGiamGia;
                                        int congDon = chuyenkhoan + khachDua;
                                        int thoiLai = tongTienSauGiamGia - congDon;
                                        txttienthua1.setText(VND.format(thoiLai));
                                    }
                                });
                            } else {
                                txtkhachdua.setEditable(true);
                            }
                        }
                    }
                });
            }

        });

        cbbphieugiamgiaONL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chonphieugiamgia = (String) cbbphieugiamgiaONL.getSelectedItem();

                double tongTien = Double.parseDouble(txttongtien1.getText().replaceAll("[^0-9]", ""));

                int TienTong = Integer.parseInt(txttong1.getText().replaceAll("[^0-9]", ""));
                double giamGia = 0;

                if (TienTong <= 400000 && chonphieugiamgia.equals("NGHÈO")) {
                    JOptionPane.showMessageDialog(null, "Khi chọn sản phẩm giá trên 40000000 mới được PGG này ");
                    return;
                }
                if (TienTong > 400000 && chonphieugiamgia.equals("NGHÈO")) {
                    giamGia = tongTien * 0.2;
                    dudkphieugiamgia();
                }
                if (TienTong <= 700000 && chonphieugiamgia.equals("THƯỜNG")) {
                    JOptionPane.showMessageDialog(null, "Khi chọn sản phẩm giá trên 70000000 mới được PGG này ");
                    return;
                }
                if (TienTong > 700000 && chonphieugiamgia.equals("THƯỜNG")) {
                    giamGia = tongTien * 0.5;
                    dudkphieugiamgia();
                }
                if (TienTong <= 900000 && chonphieugiamgia.equals("VIP")) {
                    JOptionPane.showMessageDialog(null, "Khi chọn sản phẩm giá trên 90000000 mới được PGG này ");
                    return;
                }
                if (TienTong > 900000 && chonphieugiamgia.equals("VIP")) {
                    giamGia = tongTien * 1.0;
                    dudkphieugiamgia();
                }

                int tongTienSauGiamGia = (int) (tongTien - giamGia);

                cbbPTTTONL.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            String selectedValue = (String) cbbPTTTONL.getSelectedItem();
                            if (selectedValue.equals("Tiền Mặt")) {
                                txtchuyenkhoan1.setEditable(false);
                                txtchuyenkhoan1.setText("");
                                txttienmat.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        int khachDua = Integer.valueOf(txttienmat.getText());
                                        int tongTien = tongTienSauGiamGia;
                                        int thoiLai = khachDua - tongTienSauGiamGia;
                                        txttienthua.setText(VND.format(thoiLai));
//                                        int tinhphiship = tongTienSauGiamGia +  Integer.valueOf(txtphiship.getText());

                                    }
                                });
                            } else {
                                txtchuyenkhoan1.setEditable(true);
                            }
                        }
                    }
                });
                txtphiship.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int phiShip = Integer.parseInt(txtphiship.getText().replaceAll("[^0-9]", ""));
                        int tongTienSauGiamGia = Integer.parseInt(txttongtien1.getText().replaceAll("[^0-9]", ""));
                        int tongTienMoi = tongTienSauGiamGia + phiShip;
                        txttongtien1.setText(VND.format(tongTienMoi));
                    }
                });

                cbbPTTTONL.addItemListener(new ItemListener() {
                    public void itemStateChanged(ItemEvent e) {
                        if (e.getStateChange() == ItemEvent.SELECTED) {
                            String selectedValue = (String) cbbPTTTONL.getSelectedItem();
                            if (selectedValue.equals("Chuyển khoản")) {
                                txttienmat.setEditable(false);
                                txttienmat.setText("");
                                txtchuyenkhoan1.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        int khachDua = Integer.valueOf(txtchuyenkhoan1.getText());
                                        int tongTien = tongTienSauGiamGia;
                                        int thoiLai = khachDua - tongTienSauGiamGia;
                                        txttienthua.setText(VND.format(thoiLai));

                                    }
                                });
                            } else {
                                txttienmat.setEditable(true);
                            }
                        }
                    }
                });
            }

        });

        tbldanhsachsanpham.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tbldanhsachsanpham.getSelectedRow();
                    if (selectedRow != -1) {
                        String maSP = tbldanhsachsanpham.getValueAt(selectedRow, 1).toString();
                        String tenSanPham = tbldanhsachsanpham.getValueAt(selectedRow, 2).toString();
                        String hang = tbldanhsachsanpham.getValueAt(selectedRow, 3).toString();
                        String mau = tbldanhsachsanpham.getValueAt(selectedRow, 4).toString();
                        String size = tbldanhsachsanpham.getValueAt(selectedRow, 5).toString();
                        String chatlieu = tbldanhsachsanpham.getValueAt(selectedRow, 6).toString();
                        String day = tbldanhsachsanpham.getValueAt(selectedRow, 7).toString();
                        String giaBan = tbldanhsachsanpham.getValueAt(selectedRow, 9).toString();
                        int soLuongTrongBang = Integer.parseInt(tbldanhsachsanpham.getValueAt(selectedRow, 8).toString());

                        if (soLuongTrongBang > 0) {
                            int soLuong = nhapSoLuong();

                            if (soLuong > 0 && soLuong <= soLuongTrongBang) {
                                for (sanphamchitietviewmodel object : listsp) {
                                    if (object.getMctsp().equals(maSP)) {
                                        object.setSoluong(object.getSoluong() - soLuong);
                                    }
                                }
                                // Thêm sản phẩm vào giỏ hàng
                                addToCart(maSP, tenSanPham, hang, mau, size, chatlieu, day, soLuong, giaBan);
                                showdata(listsp);
                                rowCount++;
                                updateTotalAmount();
                            } else if (soLuong > soLuongTrongBang) {
                                JOptionPane.showMessageDialog(null, "Không đủ hàng.");
                            } else {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên dương lớn hơn 0.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Số lượng sản phẩm đã hết.");
                        }
                    }
                }
            }
        });

    }

    @Override
    public void onQRCodeScanned(String result) {
        // Lấy thông tin sản phẩm từ mã QR
        List<sanphamchitietviewmodel> listSP = sps.searchQR(result);
        if (!listSP.isEmpty()) {
            sanphamchitietviewmodel sp = listSP.get(0); // Giả sử chỉ có một sản phẩm trả về
            int soLuongTrongBang = sp.getSoluong();

            int soLuong = nhapSoLuong();

            if (soLuongTrongBang > 0) {
                if (soLuong > 0 && soLuong <= soLuongTrongBang) {
                    // Trừ số lượng sản phẩm
                    sp.setSoluong(soLuongTrongBang - soLuong);

                    updateTotalAmount(); // Cập nhật tổng tiền

                    // Hiển thị giỏ hàng
                    showGioHang(listSP);
                } else if (soLuong > soLuongTrongBang) {
                    JOptionPane.showMessageDialog(null, "Không đủ hàng.");
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên dương lớn hơn 0.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Số lượng sản phẩm đã hết.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm.");
        }
    }

    public void showGioHang(List<sanphamchitietviewmodel> ListSP) {
        dtmGiohang.setRowCount(0);
        int i = 0;
        Locale lc = new Locale("vi", "VN");
        NumberFormat currentFormater = NumberFormat.getCurrencyInstance(lc);

        for (sanphamchitietviewmodel sp : ListSP) {
            i++;
            int soLuong = sp.getSoluong();
            double thanhTien = soLuong * sp.getGiaban(); // Tính toán thành tiền

            dtmGiohang.addRow(new Object[]{
                i,
                sp.getMctsp(),
                sp.getIddongsp(),
                sp.getIdhangsx(),
                sp.getIdphoimau(),
                sp.getIdsize(),
                sp.getIdchatlieu(),
                sp.getIdday(),
                soLuong,
                currentFormater.format(sp.getGiaban()), // Định dạng giá bán
                currentFormater.format(thanhTien) // Định dạng thành tiền
            });
            txttong.setText(VND.format(thanhTien));
        }

    }

    private void updateTotalAmount() {
        int totalAmount = 0;
        for (int i = 0; i < tblgiohang.getRowCount(); i++) {
            int thanhTien = Integer.parseInt(tblgiohang.getValueAt(i, 10).toString().replaceAll("[^\\d]", ""));
            totalAmount += thanhTien;
        }
        txttongtien.setText(String.valueOf(VND.format(totalAmount)));
        txttong.setText(String.valueOf(VND.format(totalAmount)));
        txttongtien1.setText(String.valueOf(VND.format(totalAmount)));
        txttong1.setText(String.valueOf(VND.format(totalAmount)));
    }

    //ComboboxPTTT
    public void showcombobxPTTT(List<PTTT> list) {
        comboPTTT.removeAllElements();
        for (PTTT pttt : list) {
            comboPTTT.addElement(pttt.getKieuthanhtoan());
        }
    }

    public void showcombobxPTTT2(List<PTTT> list) {
        comboPTTT2.removeAllElements();
        for (PTTT pttt : list) {
            comboPTTT2.addElement(pttt.getKieuthanhtoan());
        }
    }

    public void dkphieugiamgia() {
        JOptionPane.showMessageDialog(this, "Số tiền không đủ để áp dụng phiếu giảm giá này");
        return;
    }

    public void dudkphieugiamgia() {
        JOptionPane.showMessageDialog(this, "Đã giảm giá thành công");
        return;
    }

    public void showHoaDonBH(List<HoaDonBH> listHDBH) {
        dtmHoaDon.setRowCount(0);
        int i = 0;
        String trangthai = "";

        for (HoaDonBH hoaDonBH : listHDBH) {
            i++;
            if (hoaDonBH.getTrangthai2() == 0) {
                trangthai = "Đã thanh toán";
            } else if (hoaDonBH.getTrangthai2() == 1) {
                trangthai = "Chờ thanh toán";
            } else if (hoaDonBH.getTrangthai2() == 2) {
                trangthai = "Đang giao";
            } else if (hoaDonBH.getTrangthai2() == 3) {
                trangthai = "Đã hủy";
            }
            dtmHoaDon.addRow(new Object[]{
                i,
                hoaDonBH.getMaHD(),
                hoaDonBH.getMaNV(),
                hoaDonBH.getTenKH(),
                hoaDonBH.getSoluong(),
                trangthai,
                hoaDonBH.getSdt()
            });
        }

    }

    private void tuhienhoadon() {
        showHoaDonBH(listBH);
    }

    public void getkh(String ten, String makh) {
        txtTenKH.setText(ten);
        txtSdtKH.setText(makh);
        txtTenKhachHangONL.setText(ten);
        txtSDTKH.setText(makh);
    }

    public void showCbbVCH(List<GiamGia1> ListGG) {
        cbbGiamgia.removeAllElements();

        for (GiamGia1 gg : ListGG) {
            cbbGiamgia.addElement(gg.getMaGiamGia());
        }
    }

    public void showCbbVCH2(List<GiamGia1> ListGG) {
        cbbGiamgia2.removeAllElements();

        for (GiamGia1 gg : ListGG) {
            cbbGiamgia2.addElement(gg.getMaGiamGia());
        }
    }
    private int generatedCount = 0;

    private String generateMaHD() {
        if (generatedCount < 6) {
            generatedCount++;
            String maHD = "HD-00" + String.format("%03d", random.nextInt(1000));
            highlightNewRow(maHD);
            return maHD;
        } else {
            JOptionPane.showMessageDialog(this, "Đã quá giới hạn tạo hóa đơn");
            return null;
        }
    }

    private void highlightNewRow(String maHD) {
        // Duyệt qua từng hàng trong bảng
        for (int i = 0; i < tblhoadon.getRowCount(); i++) {
            // Lấy giá trị trong cột mã hóa đơn của hàng hiện tại
            String maHDRow = tblhoadon.getValueAt(i, 0).toString();

            // So sánh giá trị của cột mã hóa đơn với mã hóa đơn được tạo mới
            if (maHDRow.equals(maHD)) {
                // Chọn hàng đơn và thiết lập kiểu chữ in đậm
                tblhoadon.setRowSelectionInterval(i, i);
                tblhoadon.setFont(tblhoadon.getFont().deriveFont(Font.BOLD)); // In đậm chữ
                break; // Dừng vòng lặp khi tìm thấy hàng đơn tương ứng
            }
        }
    }

    public HoaDonBH getFormData() {
        String maHD = generateMaHD(); // Generate maHD only once
        String maNV = "NV-001";
        String tenKH = "Khách lẻ";
        String sdt = "0000000000";
        int soluongSP = 0;
        float trangThai = 0;
        if (DHOFF.isVisible()) {
            trangThai = 1;
        } else if (DHONL.isVisible()) {
            trangThai = 2;
        }

        try {
            ByteArrayOutputStream out = QRCode.from(maHD)
                    .to(ImageType.PNG).stream();

            String fileName = "C:\\QRCode\\" + maHD + ".png"; // Tên file ảnh QRCode

            // Ghi ảnh QR vào file
            FileOutputStream fout = new FileOutputStream(new File(fileName));
            fout.write(out.toByteArray());
            fout.flush();
        } catch (IOException ex) {
            Logger.getLogger(hoadon.class.getName()).log(Level.SEVERE, null, ex);
        }

        HoaDonBH hd = new HoaDonBH(maHD, maNV, tenKH, soluongSP, trangThai, 1000000000);
        txtMaHD.setText(maHD);
        txthoadonONL.setText(maHD);

        return hd;
    }

    public HDChiTiet getformdataAdd() {
        String maHD = txtMaHD.getText();
        String maHDCT = "HDCT-" + maHD;

        HDChiTiet hdct = new HDChiTiet(maHDCT, maHD);
        return hdct;
    }

    public LichSuHoaDon getFormdataAddLSHD() {
        String maHD = txtMaHD.getText();
        String maLSHD = "LSHD-" + maHD;

        LichSuHoaDon lshd = new LichSuHoaDon(maLSHD, maHD, "NV-001");
        return lshd;
    }

    public void sanphamshow() {
        listsp = sps.getall();
        showdata(listsp);
        JOptionPane.showMessageDialog(this, "Không có sản phẩm nào được tìm thấy giống ?");
    }

    private void SearchBanhang() {
        listsp = sps.Searchbanhang(txtsearch.getText());
        showdata(listsp);
    }

    //showdata
    public void showdata(List<sanphamchitietviewmodel> sanpham) {
        bangsanpham.setRowCount(0);
        int i = 0;
        Locale lc = new Locale("vi", "VN");
        NumberFormat currentFormater = NumberFormat.getCurrencyInstance(lc);
        for (sanphamchitietviewmodel sp : sanpham) {
            i++;
            bangsanpham.addRow(new Object[]{i, sp.getMctsp(), sp.getIddongsp(), sp.getIdhangsx(), sp.getIdphoimau(), sp.getIdsize(), sp.getIdchatlieu(), sp.getIdday(), sp.getSoluong(), currentFormater.format(sp.getGiaban())});
        }
    }

    public void showcomboboxMau(List<MauSanPham> list) {
        combomau.removeAllElements();
        for (MauSanPham mauSanPham : list) {
            combomau.addElement(mauSanPham.getTenmau());
        }
    }

    public void showcomboboxDay(List<Daysp> list) {
        comboday.removeAllElements();;
        for (Daysp daysp : list) {
            comboday.addElement(daysp.getDaysp());
        }
    }

    public void showcomboxCL(List<Chatlieusp> list) {
        combochatlieu.removeAllElements();
        for (Chatlieusp chatlieusp : list) {
            combochatlieu.addElement(chatlieusp.getChatlieusp());
        }
    }

    private int rowCount = 0;

    private void addToCart(String maSP, String tenSanPham, String hang, String mau, String size, String chatlieu, String day, int soLuong, String giaBan) {
        boolean productExists = false;
        Locale lc = new Locale("vi", "VN");
        NumberFormat currentFormater = NumberFormat.getCurrencyInstance(lc);
        for (int i = 0; i < dtmGiohang.getRowCount(); i++) {
            if (dtmGiohang.getValueAt(i, 1).equals(maSP)) {
                // Sản phẩm đã tồn tại trong giỏ hàng, cập nhật số lượng của nó
                int oldQuantity = (int) dtmGiohang.getValueAt(i, 8);
                int newQuantity = oldQuantity + soLuong;
                dtmGiohang.setValueAt(newQuantity, i, 8); // Cập nhật số lượng

                // Tính lại thành tiền của sản phẩm
                int giaBanValue = parseCurrencyString(giaBan); // Parse giaBan to integer
                int thanhTien = newQuantity * giaBanValue;
                dtmGiohang.setValueAt(currentFormater.format(giaBanValue), i, 9); // Cập nhật giá bán
                dtmGiohang.setValueAt(currentFormater.format(thanhTien), i, 10); // Cập nhật thành tiền

                productExists = true;
                break;
            }
        }

        if (!productExists) {
            int giaBanValue = parseCurrencyString(giaBan); // Parse giaBan to integer
            int thanhTien = soLuong * giaBanValue;
            dtmGiohang.addRow(new Object[]{rowCount + 1, maSP, tenSanPham, hang, mau, size, chatlieu, day, soLuong, currentFormater.format(giaBanValue), currentFormater.format(thanhTien)});

            rowCount++;

        }

    }
// Method to parse currency string to integer

    private int parseCurrencyString(String currencyString) {
        // Remove non-numeric characters from the currency string
        String cleanCurrencyString = currencyString.replaceAll("[^\\d]", "");
        return Integer.parseInt(cleanCurrencyString);
    }

    //showcombboxGiải
    public void Combobox(List<hangsanxuat> hsx) {
        comboHang.removeAllElements();
        for (hangsanxuat object : hsx) {
            comboHang.addElement(object.getTenhang());
        }
    }

    //searchcombobox
    private List<sanphamchitietviewmodel> searchHang(String manufacturerName) {
        List<sanphamchitietviewmodel> filteredList = new ArrayList<>();

        for (sanphamchitietviewmodel product : listsp) {
            if (product.getIdhangsx().equalsIgnoreCase(manufacturerName)) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    private List<sanphamchitietviewmodel> searchMau(String manufacturerName) {
        List<sanphamchitietviewmodel> filteredList = new ArrayList<>();

        for (sanphamchitietviewmodel product : listsp) {
            if (product.getIdphoimau().equalsIgnoreCase(manufacturerName)) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    private List<sanphamchitietviewmodel> searchDay(String manufacturerName) {
        List<sanphamchitietviewmodel> filteredList = new ArrayList<>();

        for (sanphamchitietviewmodel product : listsp) {
            if (product.getIdday().equalsIgnoreCase(manufacturerName)) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    private List<sanphamchitietviewmodel> searchCL(String manufacturerName) {
        List<sanphamchitietviewmodel> filteredList = new ArrayList<>();

        for (sanphamchitietviewmodel product : listsp) {
            if (product.getIdchatlieu().equalsIgnoreCase(manufacturerName)) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }

    private int nhapSoLuong() {
        String input = JOptionPane.showInputDialog("Nhập số lượng:");

        if (input != null && !input.isEmpty()) {
            try {
                int soLuong = Integer.parseInt(input);
                return soLuong;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập số nguyên dương.");
            }
        }
        return 0; // Trả về 0 nếu không nhập hoặc nhập không hợp lệ
    }

    public HoaDonBH getformdatabanhang() throws ParseException {
        SimpleDateFormat spx = new SimpleDateFormat("yyyy-MM-dd");
        String MaHD = txtMaHD.getText().trim();
        String TT = txttongtien.getText().replaceAll("[, đ]", "");
        String sdtKH = txtSdtKH.getText().trim();
        String tenKH = txtTenKH.getText().trim();
        String MaNV = txtmaNV.getText().trim();
        Date NT = dateFormat.parse(dcNgayTao.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() + "");
        String MaKH = txtTenKH.getText().trim();
        Date NTT = dateFormat.parse(dcNgayThanhToan.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() + "");

        // Chuyển đổi chuỗi TT thành một số nguyên
        String loaiThanhToan = (String) cbbgiamrgia.getSelectedItem();

        int tongTien = Integer.valueOf(TT);
        HoaDonBH hd = new HoaDonBH(MaHD, MaKH, tenKH, Integer.parseInt(sdtKH), tongTien, "FPT", NT, NTT, MaNV, 0, "HTTT-001", 0);
        return hd;
    }

    public HDChiTiet getformdataUpdateHDCT() {
        String maHD = txtMaHD.getText().trim();
        String maHDCT = txtMaHD.getText().trim();
        String maCTSP = txtMaSP.getText().trim();
        String donGia = txttongtien.getText().replaceAll("[, đ]", "").trim();

        HDChiTiet hdct = new HDChiTiet(maHDCT, maHD, maCTSP, Integer.parseInt(donGia), Integer.parseInt(donGia));
        return hdct;
    }

    public LichSuHoaDon getformdataUpdateLSHD() throws ParseException {
        String maHD = txtMaHD.getText().trim();
        String maLSHD = txtMaHD.getText().trim();
        String maNV = txtmaNV.getText().trim();
        String hanhDong = txtMaSP.getText().trim();
        Date ngayTao = dateFormat.parse(dcNgayTao.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate() + "");

        LichSuHoaDon lshd = new LichSuHoaDon(maLSHD, maHD, maNV, "Tạo hóa đơn", ngayTao);
        return lshd;
    }

    public HoaDonBH getformdataONL() throws ParseException {
        String MNV = txtmaNV.getText();
        LocalDate ngaybatdau = txtngaynhan.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String SDT = txtsdt.getText();
        String DC = txtdiachi.getText();
        String tt = txttongtien1.getText().replaceAll("[, đ]", "");
        String sdtKH = txtSdtKH.getText();
        String tenKH = txtTenKH.getText();

        HoaDonBH hd = new HoaDonBH(tenKH, tenKH, Integer.valueOf(tt), Integer.parseInt(SDT), DC, Date.from(ngaybatdau.atStartOfDay(ZoneId.systemDefault()).toInstant()), MNV, 2, "HTTT-001");
        return hd;
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
        mahinhbanhang = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        panel4 = new b1.View.chucnang.Panel();
        DHOFF = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtTenKH = new b1.View.chucnang.TextField();
        txtSdtKH = new b1.View.chucnang.TextField();
        buttonGradient9 = new b1.View.chucnang.ButtonGradient();
        jLabel4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        txtMaHD = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        txttongtien = new javax.swing.JTextField();
        btnReset = new b1.View.chucnang.ButtonGradient();
        btnUpdateHD = new b1.View.chucnang.ButtonGradient();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtmaNV = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        txtkhachdua = new javax.swing.JTextField();
        cbbPTTT = new b1.View.chucnang.Combobox();
        jLabel16 = new javax.swing.JLabel();
        txttong = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        cbbgiamrgia = new b1.View.chucnang.Combobox();
        jLabel14 = new javax.swing.JLabel();
        txtchuyenkhoan = new javax.swing.JTextField();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        txttienthua1 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        jSeparator13 = new javax.swing.JSeparator();
        dcNgayThanhToan = new com.toedter.calendar.JDateChooser();
        dcNgayTao = new com.toedter.calendar.JDateChooser();
        DHONL = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        txtTenKhachHangONL = new b1.View.chucnang.TextField();
        txtSDTKH = new b1.View.chucnang.TextField();
        buttonGradient12 = new b1.View.chucnang.ButtonGradient();
        jLabel17 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator12 = new javax.swing.JSeparator();
        txthoadonONL = new javax.swing.JTextField();
        txttongtien1 = new javax.swing.JTextField();
        btnReset1 = new b1.View.chucnang.ButtonGradient();
        btnUpdateHD1 = new b1.View.chucnang.ButtonGradient();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtmanvONL = new javax.swing.JTextField();
        jSeparator15 = new javax.swing.JSeparator();
        cbbPTTTONL = new b1.View.chucnang.Combobox();
        jLabel25 = new javax.swing.JLabel();
        txttong1 = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        cbbphieugiamgiaONL = new b1.View.chucnang.Combobox();
        jLabel27 = new javax.swing.JLabel();
        txtchuyenkhoan1 = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        txttienthua = new javax.swing.JTextField();
        jSeparator19 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        txttienmat = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        txtdiachi = new javax.swing.JTextField();
        jSeparator20 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        jSeparator21 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        txtngaynhan = new com.toedter.calendar.JDateChooser();
        jLabel30 = new javax.swing.JLabel();
        txtphiship = new javax.swing.JTextField();
        jSeparator22 = new javax.swing.JSeparator();
        panel1 = new b1.View.chucnang.Panel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        buttonGradient4 = new b1.View.chucnang.ButtonGradient();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblhoadon = new javax.swing.JTable();
        btnAddHoaDon = new b1.View.chucnang.ButtonGradient();
        buttonGradient7 = new b1.View.chucnang.ButtonGradient();
        txtSearchHD = new b1.View.chucnang.TextField();
        btnDeleteHD = new b1.View.chucnang.ButtonGradient();
        panel5 = new b1.View.chucnang.Panel();
        panel2 = new b1.View.chucnang.Panel();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblgiohang = new javax.swing.JTable();
        buttonGradient10 = new b1.View.chucnang.ButtonGradient();
        txtSearchGH = new b1.View.chucnang.TextField();
        deleteGH = new b1.View.chucnang.ButtonGradient();
        panel3 = new b1.View.chucnang.Panel();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbldanhsachsanpham = new javax.swing.JTable();
        txtsearch = new b1.View.chucnang.TextField();
        cbbhang = new b1.View.Combobox();
        buttonGradient8 = new b1.View.chucnang.ButtonGradient();
        cbbday = new b1.View.Combobox();
        cbbmau = new b1.View.Combobox();
        cbbchatlieu = new b1.View.Combobox();

        setBackground(new java.awt.Color(255, 255, 255));

        mahinhbanhang.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 204, 204));
        jLabel10.setText("BÁN HÀNG");

        DHOFF.setBackground(new java.awt.Color(255, 255, 255));
        DHOFF.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Thông Tin Khách Hàng");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtTenKH.setText("Khách lẻ");
        txtTenKH.setDisabledTextColor(new java.awt.Color(0, 204, 204));
        txtTenKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTenKH.setLabelText("Tên khách hàng");

        txtSdtKH.setDisabledTextColor(new java.awt.Color(0, 204, 204));
        txtSdtKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSdtKH.setLabelText("SDT");

        buttonGradient9.setBackground(new java.awt.Color(153, 255, 255));
        buttonGradient9.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonGradient9.setForeground(new java.awt.Color(0, 0, 0));
        buttonGradient9.setText("Chọn");
        buttonGradient9.setColor1(new java.awt.Color(204, 204, 255));
        buttonGradient9.setColor2(new java.awt.Color(255, 255, 255));
        buttonGradient9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addComponent(txtSdtKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(buttonGradient9, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonGradient9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Thông Tin Hóa Đơn");

        jLabel5.setText("Mã HĐ");

        jLabel6.setText("Ngày Tạo");

        jLabel7.setText("Ngày Thanh Toán");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("Tổng Tiền:");

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));

        txtMaHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaHD.setText("HD-001");
        txtMaHD.setBorder(null);

        jSeparator5.setBackground(new java.awt.Color(0, 0, 0));

        jSeparator6.setBackground(new java.awt.Color(0, 0, 0));

        txttongtien.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txttongtien.setForeground(new java.awt.Color(255, 51, 51));
        txttongtien.setBorder(null);
        txttongtien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttongtienActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(153, 255, 255));
        btnReset.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/delete.png"))); // NOI18N
        btnReset.setText("Hủy hóa đơn");
        btnReset.setColor1(new java.awt.Color(255, 51, 51));
        btnReset.setColor2(new java.awt.Color(255, 0, 0));
        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnUpdateHD.setBackground(new java.awt.Color(153, 255, 255));
        btnUpdateHD.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnUpdateHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/bill.png"))); // NOI18N
        btnUpdateHD.setText("Thanh toán");
        btnUpdateHD.setColor1(new java.awt.Color(51, 255, 0));
        btnUpdateHD.setColor2(new java.awt.Color(51, 255, 0));
        btnUpdateHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdateHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateHDActionPerformed(evt);
            }
        });

        jLabel11.setText("Phiếu giảm giá");

        jLabel13.setText("Mã NV");

        txtmaNV.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtmaNV.setText("NV-001");
        txtmaNV.setBorder(null);

        jSeparator9.setBackground(new java.awt.Color(0, 0, 0));

        jLabel15.setText("Tiền Khách Đưa");

        jSeparator10.setBackground(new java.awt.Color(0, 0, 0));

        txtkhachdua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtkhachdua.setBorder(null);
        txtkhachdua.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        cbbPTTT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbPTTT.setLabeText("PTTT");

        jLabel16.setText("Tổng ");

        txttong.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txttong.setForeground(new java.awt.Color(255, 0, 51));
        txttong.setBorder(null);

        jSeparator7.setBackground(new java.awt.Color(0, 0, 0));

        jLabel12.setText("PTTT");

        cbbgiamrgia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbgiamrgia.setLabeText("Chọn phiếu giảm giá");

        jLabel14.setText("Chuyển Khoản");

        txtchuyenkhoan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtchuyenkhoan.setBorder(null);
        txtchuyenkhoan.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jSeparator11.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setText("Tiền Thừa");

        txttienthua1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txttienthua1.setBorder(null);

        jSeparator2.setAlignmentX(1.0F);
        jSeparator2.setAlignmentY(1.0F);
        jSeparator2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jSeparator2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel24.setText("Mã Sản Phẩm");

        txtMaSP.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtMaSP.setForeground(new java.awt.Color(255, 0, 51));
        txtMaSP.setBorder(null);

        jSeparator13.setBackground(new java.awt.Color(0, 0, 0));

        dcNgayThanhToan.setDateFormatString("dd-MM-yyyy");

        dcNgayTao.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout DHOFFLayout = new javax.swing.GroupLayout(DHOFF);
        DHOFF.setLayout(DHOFFLayout);
        DHOFFLayout.setHorizontalGroup(
            DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DHOFFLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
            .addGroup(DHOFFLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DHOFFLayout.createSequentialGroup()
                        .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(95, 95, 95)
                        .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaHD)
                            .addComponent(jSeparator5)
                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dcNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DHOFFLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(52, 52, 52)
                        .addComponent(dcNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(DHOFFLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(DHOFFLayout.createSequentialGroup()
                                .addGap(193, 193, 193)
                                .addComponent(btnUpdateHD, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnReset, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DHOFFLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(33, 33, 33)
                        .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(DHOFFLayout.createSequentialGroup()
                        .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel24))
                        .addGap(72, 72, 72)
                        .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DHOFFLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator13)
                                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator7)
                                .addComponent(txttong, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DHOFFLayout.createSequentialGroup()
                            .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(DHOFFLayout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSeparator10)
                                        .addComponent(txtkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(DHOFFLayout.createSequentialGroup()
                                    .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DHOFFLayout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addGap(109, 109, 109))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DHOFFLayout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addGap(94, 94, 94)))
                                    .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jSeparator9)
                                        .addComponent(txtmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(43, 43, 43))
                        .addGroup(DHOFFLayout.createSequentialGroup()
                            .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12))
                            .addGap(66, 66, 66)
                            .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbbgiamrgia, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbbPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, DHOFFLayout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(67, 67, 67)
                                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator11)
                                    .addComponent(txtchuyenkhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txttienthua1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        DHOFFLayout.setVerticalGroup(
            DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DHOFFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(DHOFFLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(dcNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(dcNgayThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(0, 0, 0)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(0, 0, 0)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DHOFFLayout.createSequentialGroup()
                        .addComponent(cbbgiamrgia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbPTTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(DHOFFLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addGap(18, 18, 18)
                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(DHOFFLayout.createSequentialGroup()
                        .addComponent(txtchuyenkhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txttienthua1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addGroup(DHOFFLayout.createSequentialGroup()
                        .addComponent(txtmaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtkhachdua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(0, 0, 0)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txttongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DHOFFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdateHD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );

        cbbPTTT.getAccessibleContext().setAccessibleName("");
        cbbPTTT.getAccessibleContext().setAccessibleDescription("");

        panel4.addTab("     Tại Quầy", DHOFF);

        DHONL.setBackground(new java.awt.Color(255, 255, 255));
        DHONL.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Thông Tin Khách Hàng");

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        txtTenKhachHangONL.setText("Khách lẻ");
        txtTenKhachHangONL.setDisabledTextColor(new java.awt.Color(0, 204, 204));
        txtTenKhachHangONL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTenKhachHangONL.setLabelText("Tên khách hàng");

        txtSDTKH.setDisabledTextColor(new java.awt.Color(0, 204, 204));
        txtSDTKH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtSDTKH.setLabelText("SDT");

        buttonGradient12.setBackground(new java.awt.Color(153, 255, 255));
        buttonGradient12.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonGradient12.setForeground(new java.awt.Color(0, 0, 0));
        buttonGradient12.setText("Chọn");
        buttonGradient12.setColor1(new java.awt.Color(204, 204, 255));
        buttonGradient12.setColor2(new java.awt.Color(255, 255, 255));
        buttonGradient12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtTenKhachHangONL, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(buttonGradient12, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(buttonGradient12, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(txtTenKhachHangONL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Thông Tin Hóa Đơn");

        jLabel18.setText("Mã HĐ");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 51, 51));
        jLabel21.setText("Tổng Tiền:");

        jSeparator12.setBackground(new java.awt.Color(0, 0, 0));

        txthoadonONL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txthoadonONL.setText("HD-001");
        txthoadonONL.setBorder(null);

        txttongtien1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txttongtien1.setForeground(new java.awt.Color(255, 51, 51));
        txttongtien1.setBorder(null);
        txttongtien1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttongtien1ActionPerformed(evt);
            }
        });

        btnReset1.setBackground(new java.awt.Color(153, 255, 255));
        btnReset1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnReset1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/delete.png"))); // NOI18N
        btnReset1.setColor1(new java.awt.Color(255, 51, 51));
        btnReset1.setColor2(new java.awt.Color(255, 0, 0));
        btnReset1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset1ActionPerformed(evt);
            }
        });

        btnUpdateHD1.setBackground(new java.awt.Color(153, 255, 255));
        btnUpdateHD1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnUpdateHD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/bill.png"))); // NOI18N
        btnUpdateHD1.setColor1(new java.awt.Color(51, 255, 0));
        btnUpdateHD1.setColor2(new java.awt.Color(51, 255, 0));
        btnUpdateHD1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpdateHD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateHD1ActionPerformed(evt);
            }
        });

        jLabel22.setText("Phiếu giảm giá");

        jLabel23.setText("Mã NV");

        txtmanvONL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtmanvONL.setText("NV-001");
        txtmanvONL.setBorder(null);

        jSeparator15.setBackground(new java.awt.Color(0, 0, 0));

        cbbPTTTONL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbPTTTONL.setLabeText("PTTT");

        jLabel25.setText("Tổng ");

        txttong1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txttong1.setForeground(new java.awt.Color(255, 0, 51));
        txttong1.setBorder(null);

        jSeparator17.setBackground(new java.awt.Color(0, 0, 0));

        jLabel26.setText("PTTT");

        cbbphieugiamgiaONL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbbphieugiamgiaONL.setLabeText("Chọn phiếu giảm giá");

        jLabel27.setText("Chuyển Khoản");

        txtchuyenkhoan1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtchuyenkhoan1.setBorder(null);
        txtchuyenkhoan1.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jSeparator18.setBackground(new java.awt.Color(0, 0, 0));

        jLabel28.setText("Tiền Thừa");

        txttienthua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txttienthua.setBorder(null);
        txttienthua.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jSeparator19.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel29.setText("Tiền Mặt");

        txttienmat.setBorder(null);

        jSeparator1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel2.setText("Địa Chỉ:");

        txtdiachi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtdiachi.setForeground(new java.awt.Color(255, 0, 51));
        txtdiachi.setBorder(null);

        jSeparator20.setBackground(new java.awt.Color(0, 0, 0));

        jLabel19.setText("Số ĐT");

        txtsdt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtsdt.setForeground(new java.awt.Color(255, 0, 51));
        txtsdt.setBorder(null);

        jSeparator21.setBackground(new java.awt.Color(0, 0, 0));

        jLabel20.setText("Ngày Nhận");

        txtngaynhan.setDateFormatString("dd-MM-YYYY");

        jLabel30.setText("Phí ship");

        txtphiship.setBorder(null);

        jSeparator22.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout DHONLLayout = new javax.swing.GroupLayout(DHONL);
        DHONL.setLayout(DHONLLayout);
        DHONLLayout.setHorizontalGroup(
            DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DHONLLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
            .addGroup(DHONLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator8)
                    .addGroup(DHONLLayout.createSequentialGroup()
                        .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(DHONLLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel23)))
                            .addComponent(jLabel8)
                            .addGroup(DHONLLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txttongtien1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(DHONLLayout.createSequentialGroup()
                                        .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel26))
                                        .addGap(85, 85, 85)
                                        .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbbPTTTONL, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jSeparator20, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtdiachi, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtngaynhan, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txthoadonONL)
                                                    .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jSeparator15)
                                                    .addComponent(txtmanvONL, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DHONLLayout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jSeparator21, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtsdt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                        .addGap(43, 43, 43))
                                    .addGroup(DHONLLayout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addGap(66, 66, 66)
                                        .addComponent(cbbphieugiamgiaONL, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(DHONLLayout.createSequentialGroup()
                                                .addGap(145, 145, 145)
                                                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jSeparator18)
                                                    .addComponent(txtchuyenkhoan1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addComponent(jLabel27)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel29)
                                                .addGroup(DHONLLayout.createSequentialGroup()
                                                    .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel28)
                                                        .addComponent(jLabel30))
                                                    .addGap(94, 94, 94)
                                                    .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(jSeparator1)
                                                        .addComponent(txttienmat, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                                                        .addComponent(txttienthua)
                                                        .addComponent(jSeparator19)
                                                        .addComponent(txtphiship)
                                                        .addComponent(jSeparator22)))))
                                        .addGroup(DHONLLayout.createSequentialGroup()
                                            .addComponent(jLabel25)
                                            .addGap(115, 115, 115)
                                            .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jSeparator17)
                                                .addComponent(txttong1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGroup(DHONLLayout.createSequentialGroup()
                                    .addComponent(btnReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(67, 67, 67)
                                    .addComponent(btnUpdateHD1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel17))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        DHONLLayout.setVerticalGroup(
            DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DHONLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txthoadonONL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(0, 0, 0)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(DHONLLayout.createSequentialGroup()
                        .addComponent(txtmanvONL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5)
                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtngaynhan, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(5, 5, 5)
                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(0, 0, 0)
                .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtdiachi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(0, 0, 0)
                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DHONLLayout.createSequentialGroup()
                        .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbphieugiamgiaONL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbbPTTTONL, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addGroup(DHONLLayout.createSequentialGroup()
                                .addComponent(txttong1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addGroup(DHONLLayout.createSequentialGroup()
                                .addComponent(txtchuyenkhoan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addGroup(DHONLLayout.createSequentialGroup()
                                .addComponent(txttienmat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addComponent(txttienthua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(0, 0, 0)
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtphiship, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30))
                .addGap(0, 0, 0)
                .addComponent(jSeparator22, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txttongtien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(DHONLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUpdateHD1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        panel4.addTab("Đặt Hàng", DHONL);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        buttonGradient4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonGradient4.setForeground(new java.awt.Color(51, 51, 51));
        buttonGradient4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/qr-code.png"))); // NOI18N
        buttonGradient4.setText("Quét mã");
        buttonGradient4.setColor1(new java.awt.Color(51, 255, 51));
        buttonGradient4.setColor2(new java.awt.Color(102, 255, 102));
        buttonGradient4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient4ActionPerformed(evt);
            }
        });

        tblhoadon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Mã đơn hàng", "Mã NV", "Tên Khách hàng", "Tổng SP", "Trạng Thái", "Số điện thoại"
            }
        ));
        tblhoadon.setGridColor(new java.awt.Color(255, 255, 255));
        tblhoadon.setRowHeight(30);
        tblhoadon.setSelectionBackground(new java.awt.Color(153, 153, 255));
        tblhoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblhoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblhoadon);

        btnAddHoaDon.setBackground(new java.awt.Color(153, 255, 255));
        btnAddHoaDon.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnAddHoaDon.setForeground(new java.awt.Color(0, 0, 0));
        btnAddHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/icons8-bill-26.png"))); // NOI18N
        btnAddHoaDon.setText("Tạo hóa đơn");
        btnAddHoaDon.setColor1(new java.awt.Color(204, 204, 255));
        btnAddHoaDon.setColor2(new java.awt.Color(255, 255, 255));
        btnAddHoaDon.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAddHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddHoaDonActionPerformed(evt);
            }
        });

        buttonGradient7.setBackground(new java.awt.Color(153, 255, 255));
        buttonGradient7.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonGradient7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/undo.png"))); // NOI18N
        buttonGradient7.setColor1(new java.awt.Color(204, 204, 255));
        buttonGradient7.setColor2(new java.awt.Color(255, 255, 255));
        buttonGradient7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient7ActionPerformed(evt);
            }
        });

        txtSearchHD.setLabelText("Tìm kiếm");

        btnDeleteHD.setBackground(new java.awt.Color(153, 255, 255));
        btnDeleteHD.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnDeleteHD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/delete.png"))); // NOI18N
        btnDeleteHD.setColor1(new java.awt.Color(255, 51, 51));
        btnDeleteHD.setColor2(new java.awt.Color(255, 0, 0));
        btnDeleteHD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDeleteHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteHDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtSearchHD, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeleteHD, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonGradient7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonGradient4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSearchHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnDeleteHD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonGradient7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        panel1.addTab("Hóa Đơn", jPanel4);
        panel1.addTab("", panel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tblgiohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Mã SPCT", "Tên", "Hãng", "Màu", "Size", "Chất Liệu", "Dây", "Số Lượng", "Giá", "Thành Tiền"
            }
        ));
        tblgiohang.setGridColor(new java.awt.Color(255, 255, 255));
        tblgiohang.setRowHeight(30);
        tblgiohang.setSelectionBackground(new java.awt.Color(153, 153, 255));
        tblgiohang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblgiohangMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblgiohang);

        buttonGradient10.setBackground(new java.awt.Color(153, 255, 255));
        buttonGradient10.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonGradient10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/undo.png"))); // NOI18N
        buttonGradient10.setColor1(new java.awt.Color(204, 204, 255));
        buttonGradient10.setColor2(new java.awt.Color(255, 255, 255));
        buttonGradient10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient10ActionPerformed(evt);
            }
        });

        txtSearchGH.setLabelText("Tìm kiếm");

        deleteGH.setBackground(new java.awt.Color(153, 255, 255));
        deleteGH.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        deleteGH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/delete.png"))); // NOI18N
        deleteGH.setColor1(new java.awt.Color(255, 51, 51));
        deleteGH.setColor2(new java.awt.Color(255, 0, 0));
        deleteGH.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteGHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(txtSearchGH, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteGH, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonGradient10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteGH, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonGradient10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtSearchGH, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 44, Short.MAX_VALUE))
        );

        panel2.addTab("Giỏ Hàng", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        tbldanhsachsanpham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Mã SPCT", "Tên SP", "Hãng", "Màu", "Size", "Chất liệu", "Dây", "Số lượng", "Tổng tiền"
            }
        ));
        tbldanhsachsanpham.setGridColor(new java.awt.Color(255, 255, 255));
        tbldanhsachsanpham.setRowHeight(30);
        tbldanhsachsanpham.setSelectionBackground(new java.awt.Color(153, 153, 255));
        jScrollPane2.setViewportView(tbldanhsachsanpham);

        txtsearch.setLabelText("Tìm kiếm");

        cbbhang.setLabeText("Hãng");

        buttonGradient8.setBackground(new java.awt.Color(153, 255, 255));
        buttonGradient8.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        buttonGradient8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/b1/khoanh/undo.png"))); // NOI18N
        buttonGradient8.setColor1(new java.awt.Color(204, 204, 255));
        buttonGradient8.setColor2(new java.awt.Color(255, 255, 255));
        buttonGradient8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        buttonGradient8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGradient8ActionPerformed(evt);
            }
        });

        cbbday.setMaximumRowCount(90);
        cbbday.setLabeText("Dây");

        cbbmau.setLabeText("Màu");

        cbbchatlieu.setLabeText("Chất Liệu");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbbmau, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbday, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbhang, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonGradient8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 822, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonGradient8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbbhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbday, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbchatlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbmau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panel3.addTab("Danh Sách Sản Phẩm", jPanel7);

        javax.swing.GroupLayout mahinhbanhangLayout = new javax.swing.GroupLayout(mahinhbanhang);
        mahinhbanhang.setLayout(mahinhbanhangLayout);
        mahinhbanhangLayout.setHorizontalGroup(
            mahinhbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mahinhbanhangLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(mahinhbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mahinhbanhangLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(106, 106, 106))
                    .addGroup(mahinhbanhangLayout.createSequentialGroup()
                        .addGroup(mahinhbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(panel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)))
                .addComponent(panel4, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
        );
        mahinhbanhangLayout.setVerticalGroup(
            mahinhbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mahinhbanhangLayout.createSequentialGroup()
                .addGroup(mahinhbanhangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, mahinhbanhangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(panel2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mahinhbanhang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mahinhbanhang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonGradient8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient8ActionPerformed
        // TODO add your handling code here:
        cbbhang.setSelectedIndex(0);
        showdata(listsp);
    }//GEN-LAST:event_buttonGradient8ActionPerformed


    private void buttonGradient10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient10ActionPerformed
        // TODO add your handling code here:

        for (int i = 0; i < dtmGiohang.getRowCount(); i++) {
            String maSP = dtmGiohang.getValueAt(i, 1).toString();
            int soLuong = Integer.parseInt(dtmGiohang.getValueAt(i, 8).toString());
            for (sanphamchitietviewmodel sp : listsp) {
                if (sp.getMctsp().equals(maSP)) {
                    sp.setSoluong(sp.getSoluong() + soLuong);
                    break;
                }
            }
        }

        showdata(listsp);

        dtmGiohang.setRowCount(0);
    }//GEN-LAST:event_buttonGradient10ActionPerformed

    private void btnUpdateHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateHDActionPerformed
        String MVC = (String) cbbgiamrgia.getSelectedItem();
        String MKH = txtTenKH.getText();
        if (txttong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng hãy chọn sản phẩm.");
            return;
        }
        if (cbbPTTT.getSelectedItem().equals("Tiền Mặt")) {
            if (txtkhachdua.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền khách đưa.");
                return;
            }
            if (txtkhachdua.getText().trim().matches(".*[A-Za-z]+.*")) {
                JOptionPane.showMessageDialog(this, "Vui lòng hãy nhập số.");
                return;
            }
        }
        if (cbbPTTT.getSelectedItem().equals("Chuyển khoản")) {
            if (txtchuyenkhoan.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền khách vừa chuyển khoản.");
                return;
            }
            if (txtchuyenkhoan.getText().matches(".*[A-Za-z]+.*")) {
                JOptionPane.showMessageDialog(this, "Vui lòng hãy nhập số.");
                return;
            }
        }
        if (cbbPTTT.getSelectedItem().equals("Cả Hai")) {
            if (txtchuyenkhoan.getText().trim().isEmpty() || txtkhachdua.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền khách vừa chuyển khoản và số tiền khách vừa đưa.");
                return;
            }
            if (txtchuyenkhoan.getText().matches(".*[A-Za-z]+.*") || txtkhachdua.getText().matches(".*[A-Za-z]+.*")) {
                JOptionPane.showMessageDialog(this, "Vui lòng hãy nhập số.");
                return;
            }
        }
        ParametReportPayment param = createParametReportPayment();
        try {
            
            srhd.UpdateBanhang(getformdatabanhang(), getformdataUpdateHDCT(), getformdataUpdateLSHD(), MVC, txtMaHD.getText(), txtMaHD.getText(), txtMaHD.getText(), MKH);
            listBH = srhd.getAll();
            showHoaDonBH(listBH);
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            int check = JOptionPane.showConfirmDialog(this, "Ban co muon in hoa don khong");
            if(check == JOptionPane.YES_OPTION) {
                ReportManager.getInstance().printReportManager(param);
            }
        } catch (ParseException ex) {
            Logger.getLogger(banhhang.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Lỗi xảy ra khi thêm dữ liệu. Vui lòng thử lại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        } catch (JRException ex) {
            Logger.getLogger(banhhang.class.getName()).log(Level.SEVERE, null, ex);
        }
        dtmGiohang.setRowCount(0);
    }//GEN-LAST:event_btnUpdateHDActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        int dk = JOptionPane.showConfirmDialog(this, "Bạn chắc muốn reset?");
        if (dk == JOptionPane.YES_OPTION) {
            txtSdtKH.setText("");
            txtTenKH.setText("");
            txtMaHD.setText("");
            txtkhachdua.setText("");
            txtmaNV.setText("");
            txttongtien.setText("");
            txttong.setText("");
        }
        if (dk == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn không");
            return;
        }
        if (dk == JOptionPane.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Bạn đã chọn Cancel");
            return;
        }

    }//GEN-LAST:event_btnResetActionPerformed

    private void txttongtienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttongtienActionPerformed
        cbbhang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String timkiem = cbbhang.getSelectedItem().toString();
                if (!timkiem.isEmpty()) {
                    List<sanphamchitietviewmodel> searchedList = searchHang(timkiem);
                    showdata(searchedList);
                } else {
                    // If no manufacturer is selected, reload all products
                    sanphamshow();

                }
            }

        });        // TODO add your handling code here:
    }//GEN-LAST:event_txttongtienActionPerformed

    private void buttonGradient9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient9ActionPerformed
        // TODO add your handling code here:
        khachhangbanhang kh = new khachhangbanhang(this);
        kh.setVisible(true);
    }//GEN-LAST:event_buttonGradient9ActionPerformed

    private void deleteGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteGHActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_deleteGHActionPerformed

    private void buttonGradient12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient12ActionPerformed
        khachhangbanhang kh = new khachhangbanhang(this);
        kh.setVisible(true);    }//GEN-LAST:event_buttonGradient12ActionPerformed

    private void txttongtien1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttongtien1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttongtien1ActionPerformed

    private void btnReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReset1ActionPerformed

    private void btnUpdateHD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateHD1ActionPerformed
        // TODO add your handling code here:
//        String MVC = (String) cbbgiamrgia.getSelectedItem();
//        String MKH = txtTenKH.getText();
//        try {
//            srhd.UpdateBanhang(getformdataONL(), MVC, txtMaHD.getText(), MKH);
//            listBH = srhd.getAll();
//            showHoaDonBH(listBH);
//        } catch (ParseException ex) {
//            Logger.getLogger(banhhang.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_btnUpdateHD1ActionPerformed

    private void btnDeleteHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteHDActionPerformed
        // TODO add your handling code here:
        int index = tblhoadon.getSelectedRow();
        HoaDonBH hd = listBH.get(index);
        srhd.Delete(hd.getMaHD(), hd.getMaHD(), hd.getMaHD());
        listBH = srhd.getAll();
        showHoaDonBH(listBH);
    }//GEN-LAST:event_btnDeleteHDActionPerformed

    private void buttonGradient7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient7ActionPerformed
        // TODO add your handling code here:
        dtmGiohang.setRowCount(0);
    }//GEN-LAST:event_buttonGradient7ActionPerformed

    private void btnAddHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddHoaDonActionPerformed
        // TODO add your handling code here:
        
        srhd.Add(getFormData(), getformdataAdd(), getFormdataAddLSHD());
        listBH = srhd.getAll();
        showHoaDonBH(listBH);
    }//GEN-LAST:event_btnAddHoaDonActionPerformed

    private void tblhoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblhoadonMouseClicked
        // TODO add your handling code here:
        int index = tblhoadon.getSelectedRow();

        HoaDonBH hd = listBH.get(index);

        txtMaHD.setText(hd.getMaHD());

        dcNgayTao.setDate(hd.getNgaytao());
    }//GEN-LAST:event_tblhoadonMouseClicked

    private void buttonGradient4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGradient4ActionPerformed
        // Make QR code
        quetmaqr qr = new quetmaqr();
        qr.setQRCodeListener(this);
        qr.setVisible(true);
        return;
    }//GEN-LAST:event_buttonGradient4ActionPerformed

    private void tblgiohangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblgiohangMouseClicked
        // TODO add your handling code here:
        int index = tblgiohang.getSelectedRow();
        sanphamchitietviewmodel ctsp = listsp.get(index);

        txtMaSP.setText(ctsp.getMctsp());
    }//GEN-LAST:event_tblgiohangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DHOFF;
    private javax.swing.JPanel DHONL;
    private b1.View.chucnang.ButtonGradient btnAddHoaDon;
    private b1.View.chucnang.ButtonGradient btnDeleteHD;
    private b1.View.chucnang.ButtonGradient btnReset;
    private b1.View.chucnang.ButtonGradient btnReset1;
    private b1.View.chucnang.ButtonGradient btnUpdateHD;
    private b1.View.chucnang.ButtonGradient btnUpdateHD1;
    private b1.View.chucnang.ButtonGradient buttonGradient10;
    private b1.View.chucnang.ButtonGradient buttonGradient12;
    private b1.View.chucnang.ButtonGradient buttonGradient4;
    private b1.View.chucnang.ButtonGradient buttonGradient7;
    private b1.View.chucnang.ButtonGradient buttonGradient8;
    private b1.View.chucnang.ButtonGradient buttonGradient9;
    private javax.swing.ButtonGroup buttonGroup1;
    private b1.View.chucnang.Combobox cbbPTTT;
    private b1.View.chucnang.Combobox cbbPTTTONL;
    private b1.View.Combobox cbbchatlieu;
    private b1.View.Combobox cbbday;
    private b1.View.chucnang.Combobox cbbgiamrgia;
    private b1.View.Combobox cbbhang;
    private b1.View.Combobox cbbmau;
    private b1.View.chucnang.Combobox cbbphieugiamgiaONL;
    private com.toedter.calendar.JDateChooser dcNgayTao;
    private com.toedter.calendar.JDateChooser dcNgayThanhToan;
    private b1.View.chucnang.ButtonGradient deleteGH;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JPanel mahinhbanhang;
    private b1.View.chucnang.Panel panel1;
    private b1.View.chucnang.Panel panel2;
    private b1.View.chucnang.Panel panel3;
    private b1.View.chucnang.Panel panel4;
    private b1.View.chucnang.Panel panel5;
    private javax.swing.JTable tbldanhsachsanpham;
    private javax.swing.JTable tblgiohang;
    private javax.swing.JTable tblhoadon;
    private javax.swing.JTextField txtMaHD;
    private javax.swing.JTextField txtMaSP;
    private b1.View.chucnang.TextField txtSDTKH;
    private b1.View.chucnang.TextField txtSdtKH;
    private b1.View.chucnang.TextField txtSearchGH;
    private b1.View.chucnang.TextField txtSearchHD;
    private b1.View.chucnang.TextField txtTenKH;
    private b1.View.chucnang.TextField txtTenKhachHangONL;
    private javax.swing.JTextField txtchuyenkhoan;
    private javax.swing.JTextField txtchuyenkhoan1;
    private javax.swing.JTextField txtdiachi;
    private javax.swing.JTextField txthoadonONL;
    private javax.swing.JTextField txtkhachdua;
    private javax.swing.JTextField txtmaNV;
    private javax.swing.JTextField txtmanvONL;
    private com.toedter.calendar.JDateChooser txtngaynhan;
    private javax.swing.JTextField txtphiship;
    private javax.swing.JTextField txtsdt;
    private b1.View.chucnang.TextField txtsearch;
    private javax.swing.JTextField txttienmat;
    private javax.swing.JTextField txttienthua;
    private javax.swing.JTextField txttienthua1;
    private javax.swing.JTextField txttong;
    private javax.swing.JTextField txttong1;
    private javax.swing.JTextField txttongtien;
    private javax.swing.JTextField txttongtien1;
    // End of variables declaration//GEN-END:variables

}
