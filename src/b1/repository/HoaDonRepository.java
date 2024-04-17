/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package b1.repository;

import ViewModelHD.HoaDon;
import ViewModelHD.HoaDonChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 *
 * @author Huanh
 */
public class HoaDonRepository {

    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();

        String sql = """
                     SELECT        dbo.HoaDon.MaHD, dbo.HoaDon.NgayTaoHoaDon, dbo.HoaDon.Tongtien, dbo.HTTT.MaHTTT, dbo.HoaDon.TenKH, dbo.HoaDon.DiachiKH, dbo.HoaDon.SdtKH, dbo.HoaDon.Deleted
                                                                                                FROM            dbo.HoaDon FULL JOIN
                                                          dbo.NhanVien ON dbo.HoaDon.MaNV = dbo.NhanVien.MaNV FULL JOIN
                                                           dbo.KhachHang ON dbo.HoaDon.MaKH = dbo.KhachHang.MaKH LEFT JOIN
                                                            dbo.HTTT ON dbo.HoaDon.MaHTTT = dbo.HTTT.MaHTTT LEFT JOIN
                                                             dbo.VCH ON dbo.HoaDon.MaVCH = dbo.VCH.MaVCH
                     WHERE dbo.HoaDon.Deleted = 0 OR 
                     dbo.HoaDon.Deleted = 1 OR
                     dbo.HoaDon.Deleted = 3
                     """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(1));
                hd.setNgaytaoHD(rs.getDate(2));
                hd.setTongTien(rs.getFloat(3));
                hd.setMaHTTT(rs.getString(4));
                hd.setTenKH(rs.getString(5));
                hd.setDiaChi(rs.getString(6));
                hd.setSdtKH(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));

                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    


    public List<HoaDon> searchHoaDon(String maHD) {
        List<HoaDon> list = new ArrayList<>();

        String sql = """
                     
             SELECT dbo.HoaDon.MaHD
                     , dbo.HoaDon.NgayTaoHoaDon
                     , dbo.HoaDon.Tongtien
                     , dbo.HTTT.MaHTTT
                     , dbo.HoaDon.TenKH
                     , dbo.HoaDon.DiachiKH
                     , dbo.HoaDon.SdtKH
                     , dbo.HoaDon.Deleted
             FROM dbo.HoaDon
             INNER JOIN dbo.HoaDonChiTiet ON dbo.HoaDon.MaHD = dbo.HoaDonChiTiet.MaHoaDon
             INNER JOIN dbo.LichsuHD ON dbo.HoaDon.MaHD = dbo.LichsuHD.IDHoaDon
             INNER JOIN dbo.HTTT ON dbo.HoaDon.MaHTTT = dbo.HTTT.MaHTTT
             WHERE dbo.HoaDon.MaHD LIKE ? 
                     OR dbo.HoaDon.TenKH LIKE ? 
                     OR dbo.HoaDon.Tongtien LIKE ? 
                     OR dbo.HoaDon.DiachiKH LIKE ? 
                     OR dbo.HoaDon.NgayTaoHoaDon LIKE ? 
                     OR dbo.HTTT.MaHTTT LIKE ?
             """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, '%' + maHD + '%');
            ps.setString(2, '%' + maHD + '%');
            ps.setString(3, '%' + maHD + '%');
            ps.setString(4, '%' + maHD + '%');
            ps.setString(5, '%' + maHD + '%');
            ps.setString(6, '%' + maHD + '%');

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(1));
                hd.setNgaytaoHD(rs.getDate(2));
                hd.setTongTien(rs.getFloat(3));
                hd.setMaHTTT(rs.getString(4));
                hd.setTenKH(rs.getString(5));
                hd.setDiaChi(rs.getString(6));
                hd.setSdtKH(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));

                list.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    public boolean exportToExcel(List<HoaDon> hoaDonList, List<HoaDonChiTiet> hoaDonChiTietList, String filePath) {
        try ( Workbook workbook = new XSSFWorkbook()) {
            Sheet mainSheet = workbook.createSheet("HoaDonData");

            // Tạo tiêu đề cho các cột
            Row headerRow = mainSheet.createRow(0);
            String[] columns = {"Mã HD", "Tổng Tiền", "Tên KH", "Địa Chỉ KH", "SĐT KH"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Tạo danh sách các sheet chi tiết hóa đơn
            List<Sheet> detailSheets = new ArrayList<>();

            // Đổ dữ liệu từ danh sách HoaDon vào các dòng trong tệp Excel
            int rowNum = 1;
            for (HoaDon hoaDon : hoaDonList) {
                Row row = mainSheet.createRow(rowNum++);
                row.createCell(0).setCellValue(hoaDon.getMaHD());
                row.createCell(1).setCellValue(hoaDon.getTongTien());
                row.createCell(2).setCellValue(hoaDon.getTenKH());
                row.createCell(3).setCellValue(hoaDon.getDiaChi());
                row.createCell(4).setCellValue(hoaDon.getSdtKH());

                // Tạo sheet chi tiết cho mỗi hóa đơn
                Sheet detailSheet = workbook.createSheet("ChiTietHoaDon_" + hoaDon.getMaHD());
                detailSheets.add(detailSheet);

                // Tạo tiêu đề cho sheet chi tiết
                Row detailHeaderRow = detailSheet.createRow(0);
                String[] detailColumns = {"Mã Chi tiết sản phẩm", "Đơn giá", "Thành tiền"};
                for (int i = 0; i < detailColumns.length; i++) {
                    Cell detailCell = detailHeaderRow.createCell(i);
                    detailCell.setCellValue(detailColumns[i]);
                }

                // Thêm dữ liệu chi tiết của các hóa đơn vào sheet chi tiết tương ứng
                int detailSheetRowIndex = 1;
                for (HoaDonChiTiet hd : hoaDonChiTietList) {
                    if (hd.getMaHD().equals(hoaDon.getMaHD())) {
                        Row detailRow = detailSheet.createRow(detailSheetRowIndex++);
                        detailRow.createCell(0).setCellValue(hd.getIdCTSP());
                        detailRow.createCell(1).setCellValue(hd.getDonGia());
                    }
                }

                // Tạo hyperlink từ cột ID trên sheet chính đến các sheet chi tiết tương ứng
                mainSheet.getRow(rowNum - 1).getCell(0)
                        .setCellFormula("HYPERLINK(\"#" + detailSheet.getSheetName() + "!A1\", \"" + hoaDon.getMaHD() + "\")");
            }

            // Ghi Workbook vào một tệp
            try ( FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                return true;
            } catch (IOException e) {
                System.err.println("Không thể ghi vào tệp: " + e.getMessage());
                return false;
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi tạo Workbook: " + e.getMessage());
            return false;
        }
    }
 
    public List<HoaDon> searchGia(int min, int max) {
        List<HoaDon> list = new ArrayList<>();

        String sql = """
                 SELECT 
                     dbo.HoaDon.MaHD, 
                     dbo.HoaDon.NgayTaoHoaDon,  
                     dbo.HoaDon.Tongtien, 
                     dbo.HTTT.MaHTTT, 
                     dbo.HoaDon.TenKH, 
                     dbo.HoaDon.DiachiKH, 
                     dbo.HoaDon.SdtKH, 
                     dbo.HoaDon.Deleted
                 FROM 
                     dbo.HoaDon
                 INNER JOIN 
                     dbo.HoaDonChiTiet ON dbo.HoaDon.MaHD = dbo.HoaDonChiTiet.MaHoaDon
                 INNER JOIN 
                     dbo.LichsuHD ON dbo.HoaDon.MaHD = dbo.LichsuHD.IDHoaDon
                 INNER JOIN 
                     dbo.HTTT ON dbo.HoaDon.MaHTTT = dbo.HTTT.MaHTTT
                 WHERE 
                     dbo.HoaDon.Tongtien >= ? AND dbo.HoaDon.Tongtien <= ?
                 """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, min);
            ps.setObject(2, max);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(1));
                hd.setNgaytaoHD(rs.getDate(2));
                hd.setTongTien(rs.getFloat(3));
                hd.setMaHTTT(rs.getString(4));
                hd.setTenKH(rs.getString(5));
                hd.setDiaChi(rs.getString(6));
                hd.setSdtKH(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));

                list.add(hd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HoaDon> searchQR(String qrCode) {
        List<HoaDon> list = new ArrayList<>();

        String sql = """
                     SELECT dbo.HoaDon.MaHD
                                          , dbo.HoaDon.NgayTaoHoaDon
                                          , dbo.HoaDon.Tongtien
                                          , dbo.HTTT.MaHTTT
                                          , dbo.HoaDon.TenKH
                                          , dbo.HoaDon.DiachiKH
                                          , dbo.HoaDon.SdtKH
                                          , dbo.HoaDon.Deleted
                                  FROM dbo.HoaDon
                                  INNER JOIN dbo.HoaDonChiTiet ON dbo.HoaDon.MaHD = dbo.HoaDonChiTiet.MaHoaDon
                                  INNER JOIN dbo.LichsuHD ON dbo.HoaDon.MaHD = dbo.LichsuHD.IDHoaDon
                                  INNER JOIN dbo.HTTT ON dbo.HoaDon.MaHTTT = dbo.HTTT.MaHTTT
                                  WHERE dbo.HoaDon.MaHD = ?
                     """;

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, qrCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(1));
                hd.setNgaytaoHD(rs.getDate(2));
                hd.setTongTien(rs.getFloat(3));
                hd.setMaHTTT(rs.getString(4));
                hd.setTenKH(rs.getString(5));
                hd.setDiaChi(rs.getString(6));
                hd.setSdtKH(rs.getInt(7));
                hd.setTrangThai(rs.getInt(8));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public static void main(String[] args) {
        List<HoaDon> list = new HoaDonRepository().getAll();

        for (HoaDon hoaDon : list) {
            System.out.println(hoaDon.toString());
        }
    }

}
