/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package b1.repository;

import ViewModelSP.sanphamchitietviewmodel;
import b1.entity.chitietsanpham;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Random;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DELL
 */
public class chitietsanphamviewmodelRepo {

    public List<sanphamchitietviewmodel> getall() {
        List<sanphamchitietviewmodel> listsanpham = new ArrayList<>();
        String sql = """
                SELECT        dbo.ChiTietSP.MaCTSP, dbo.HSX.TenHang, dbo.DSP.TenDSP, dbo.PhoiMau.TenMau, dbo.Size.SizeSP, dbo.DoDay.doDaySP, dbo.ChatLieu.ChatlieuSP, dbo.MatDe.MatDeSP, dbo.Dayy.dAYsp, dbo.ChiTietSP.Giaban,dbo.ChiTietSP.Soluong
                                                                                                                                                                                                                                                          FROM            dbo.ChiTietSP INNER JOIN
                                                                                                                                                                                                                                                                                   dbo.HSX ON dbo.ChiTietSP.IDHangSX = dbo.HSX.IDhsx INNER JOIN
                                                                                                                                                                                                                                                                                   dbo.DSP ON dbo.ChiTietSP.IDDongSP = dbo.DSP.IDdsp INNER JOIN
                                                                                                                                                                                                                                                                                   dbo.PhoiMau ON dbo.ChiTietSP.IDPhoiMau = dbo.PhoiMau.IDMau INNER JOIN
                                                                                                                                                                                                                                                                                   dbo.Size ON dbo.ChiTietSP.IDSize = dbo.Size.IDSize INNER JOIN
                                                                                                                                                                                                                                                                                   dbo.DoDay ON dbo.ChiTietSP.IDDoDay = dbo.DoDay.IDDoday INNER JOIN
                                                                                                                                                                                                                                                                                   dbo.ChatLieu ON dbo.ChiTietSP.IDChatlieu = dbo.ChatLieu.IDChatlieu INNER JOIN
                                                                                                                                                                                                                                                                                   dbo.MatDe ON dbo.ChiTietSP.IDMatDe = dbo.MatDe.IDMatDe INNER JOIN
                                                                                                                                                                                                                                                                                   dbo.Dayy ON dbo.ChiTietSP.IDDay = dbo.Dayy.IDDay
                                                                                                                                                                                                                                 						 Where ChiTietSP.Deleted = '0' ORDER BY MaCTSP DESC
                       """;
        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sanphamchitietviewmodel ctsp = new sanphamchitietviewmodel();
                ctsp.setMctsp(rs.getString(1));
                ctsp.setIdhangsx(rs.getString(2));
                ctsp.setIddongsp(rs.getString(3));
                ctsp.setIdphoimau(rs.getString(4));
                ctsp.setIdsize(rs.getString(5));
                ctsp.setIddoday(rs.getString(6));
                ctsp.setIdchatlieu(rs.getString(7));
                ctsp.setIdmatde(rs.getString(8));
                ctsp.setIdday(rs.getString(9));
                ctsp.setGiaban(rs.getInt(10));
                ctsp.setSoluong(rs.getInt(11));
                listsanpham.add(ctsp);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return listsanpham;
    }

    public List<sanphamchitietviewmodel> searchQR(String QRCode) {
        List<sanphamchitietviewmodel> listsanpham = new ArrayList<>();
        String sql = """
                SELECT        dbo.ChiTietSP.MaCTSP, 
                     dbo.HSX.TenHang, 
                     dbo.DSP.TenDSP, 
                     dbo.PhoiMau.TenMau, 
                     dbo.Size.SizeSP, 
                     dbo.DoDay.doDaySP, 
                     dbo.ChatLieu.ChatlieuSP, 
                     dbo.MatDe.MatDeSP, 
                     dbo.Dayy.dAYsp, 
                     dbo.ChiTietSP.Giaban,
                     dbo.ChiTietSP.Soluong
                     FROM            
                     dbo.ChiTietSP INNER JOIN
                     dbo.HSX ON dbo.ChiTietSP.IDHangSX = dbo.HSX.IDhsx INNER JOIN
                     dbo.DSP ON dbo.ChiTietSP.IDDongSP = dbo.DSP.IDdsp INNER JOIN
                     dbo.PhoiMau ON dbo.ChiTietSP.IDPhoiMau = dbo.PhoiMau.IDMau INNER JOIN
                     dbo.Size ON dbo.ChiTietSP.IDSize = dbo.Size.IDSize INNER JOIN
                     dbo.DoDay ON dbo.ChiTietSP.IDDoDay = dbo.DoDay.IDDoday INNER JOIN
                     dbo.ChatLieu ON dbo.ChiTietSP.IDChatlieu = dbo.ChatLieu.IDChatlieu INNER JOIN
                     dbo.MatDe ON dbo.ChiTietSP.IDMatDe = dbo.MatDe.IDMatDe INNER JOIN
                     dbo.Dayy ON dbo.ChiTietSP.IDDay = dbo.Dayy.IDDay
                        Where dbo.ChiTietSP.MaCTSP = ? ORDER BY MaCTSP DESC                                                                                                                                                                                                                             
                       """;
        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setObject(1, QRCode);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sanphamchitietviewmodel ctsp = new sanphamchitietviewmodel();
                ctsp.setMctsp(rs.getString(1));
                ctsp.setIdhangsx(rs.getString(2));
                ctsp.setIddongsp(rs.getString(3));
                ctsp.setIdphoimau(rs.getString(4));
                ctsp.setIdsize(rs.getString(5));
                ctsp.setIddoday(rs.getString(6));
                ctsp.setIdchatlieu(rs.getString(7));
                ctsp.setIdmatde(rs.getString(8));
                ctsp.setIdday(rs.getString(9));
                ctsp.setGiaban(rs.getInt(10));
                ctsp.setSoluong(rs.getInt(11));
                listsanpham.add(ctsp);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return listsanpham;
    }

 public boolean Add(chitietsanpham ctsp) {
    int check = 0;
    String sql = "INSERT INTO [dbo].[ChiTietSP] " +
            "([MaCTSP], [IDHangSX], [IDDongSP], [IDPhoiMau], [IDSize], [IDDoDay], [IDChatlieu], [IDMatDe], [IDDay], [Ghichu], [Giaban], [Deleted], [CreatedAt], [CreatedBy], [UpdatedAt], [UpdatedBy], [Soluong]) " +
            "VALUES (?, (select TOP 1 IDhsx from HSX where TenHang = ?), (select TOP 1 IDdsp from DSP where TenDSP = ?), (select TOP 1 IDMau from PhoiMau where TenMau = ?), " +
            "(select TOP 1 IDSize from Size where SizeSP = ?), (select TOP 1 IDDoday from DoDay where doDaySP = ?), (select TOP 1 IDChatlieu from ChatLieu where ChatlieuSP = ?), " +
            "(select TOP 1 IDMatDe from MatDe where MatDeSP = ?), (select TOP 1 IDDay from Dayy where dAYsp = ?), ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
        
        
        // Set the generated 'MaCTSP' value into the prepared statement
        ps.setObject(1, ctsp.getMasp());
        ps.setObject(2, ctsp.getIdhangsx());
        ps.setObject(3, ctsp.getIddongsp());
        ps.setObject(4, ctsp.getIdphoimau());
        ps.setObject(5, ctsp.getIdsize());
        ps.setObject(6, ctsp.getIddoday());
        ps.setObject(7, ctsp.getIdchatlieu());
        ps.setObject(8, ctsp.getIdmatde());
        ps.setObject(9, ctsp.getIdday());
        ps.setObject(10, ctsp.getGhichu());
        ps.setObject(11, ctsp.getGiaban());
        ps.setObject(12, ctsp.getDelete());
        ps.setObject(13, ctsp.getCreateat());
        ps.setObject(14, ctsp.getCreateby());
        ps.setObject(15, ctsp.getUpdateat());
        ps.setObject(16, ctsp.getUpdateby());
        ps.setObject(17, ctsp.getSoluong());

        // Execute the query
        check = ps.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return check > 0;
}


    public boolean Xoa(String xoa) {
        int check = 0;
        String sql = """
                UPDATE [dbo].[ChiTietSP]
                                   SET Deleted = 1
                                 WHERE MaCTSP = ?
                    """;
        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setObject(1, xoa);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public boolean exportToExcel(List<sanphamchitietviewmodel> listspvm, String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("HoaDonData");

            // Tạo tiêu đề cho các cột
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Mã", "hãng", "tên sp", "phối màu", "Size", "Chất liệu", "Mặt đế", "Dây", "Gía bán"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Đổ dữ liệu từ danh sách HoaDon vào các dòng trong tệp Excel
            int rowNum = 1;
            for (sanphamchitietviewmodel spctvm : listspvm) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(spctvm.getMctsp());
                row.createCell(1).setCellValue(spctvm.getIdhangsx());
                row.createCell(2).setCellValue(spctvm.getIddongsp());
                row.createCell(3).setCellValue(spctvm.getIdphoimau());
                row.createCell(4).setCellValue(spctvm.getIdsize());
                row.createCell(5).setCellValue(spctvm.getIddoday());
                row.createCell(6).setCellValue(spctvm.getIdchatlieu());
                row.createCell(7).setCellValue(spctvm.getIdmatde());
                row.createCell(8).setCellValue(spctvm.getIdday());
                row.createCell(9).setCellValue(spctvm.getGiaban());

            }

            // Ghi Workbook vào một tệp
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean Sua(chitietsanpham ctsp, String sua) {
        int check = 0;
        String sql = """
            UPDATE [dbo].[ChiTietSP]
                                    SET 
                                       [IDHangSX] = (Select TOP 1 IDHangSX from HSX Where TenHang = ?)
                                       ,[IDDongSP] =  (Select TOP 1 IDDongSP from DSP where TenDSP = ?)
                                       ,[IDPhoiMau] = (Select TOP 1 IDPhoiMau from PhoiMau where TenMau = ?)
                                       ,[IDSize] = (Select TOP 1 IDSize from Size where SizeSP = ?)
                                       ,[IDDoDay] = (Select TOP 1 IDDoDay from DoDay where doDaySP = ?)
                                       ,[IDChatlieu] = (Select TOP 1 IDChatlieu from ChatLieu where ChatlieuSP = ?)
                                       ,[IDMatDe] = (Select TOP 1 IDMatDe from MatDe where MatDeSP = ?)
                                       ,[IDDay] = (Select TOP 1 IDDay from Dayy where dAYsp = ?)
                                       ,[Ghichu] = ?
                                       ,[Giaban] = ?
                                       ,[Deleted] = ?
                                       ,[CreatedAt] = ?
                                       ,[CreatedBy] = ?
                                       ,[UpdatedAt] = ?
                                       ,[UpdatedBy] = ?
                                       ,[Soluong] = ?
                                  WHERE MaCTSP = ?
                    """;
        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setObject(1, ctsp.getIdhangsx());
            ps.setObject(2, ctsp.getIddongsp());
            ps.setObject(3, ctsp.getIdphoimau());
            ps.setObject(4, ctsp.getIdsize());
            ps.setObject(5, ctsp.getIddoday());
            ps.setObject(6, ctsp.getIdchatlieu());
            ps.setObject(7, ctsp.getIdmatde());
            ps.setObject(8, ctsp.getIdday());
            ps.setObject(9, ctsp.getGhichu());
            ps.setObject(10, ctsp.getGiaban());
            ps.setObject(11, ctsp.getDelete());
            ps.setObject(12, ctsp.getCreateat());
            ps.setObject(13, ctsp.getCreateby());
            ps.setObject(14, ctsp.getUpdateat());
            ps.setObject(15, ctsp.getUpdateby());
            ps.setObject(16, ctsp.getSoluong());
            ps.setObject(17, sua);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check > 0;
    }

    public List<sanphamchitietviewmodel> Search(String timkiem) {
        List<sanphamchitietviewmodel> listsanpham = new ArrayList<>();
        String sql = """
 SELECT        dbo.ChiTietSP.MaCTSP, dbo.HSX.TenHang, dbo.DSP.TenDSP, dbo.PhoiMau.TenMau, dbo.Size.SizeSP, dbo.DoDay.doDaySP, dbo.ChatLieu.ChatlieuSP, dbo.MatDe.MatDeSP, dbo.Dayy.dAYsp, dbo.ChiTietSP.Giaban
                                                                 FROM            dbo.ChiTietSP INNER JOIN
                                                                                          dbo.HSX ON dbo.ChiTietSP.IDHangSX = dbo.HSX.IDhsx INNER JOIN
                                                                                          dbo.DSP ON dbo.ChiTietSP.IDDongSP = dbo.DSP.IDdsp INNER JOIN
                                                                                          dbo.PhoiMau ON dbo.ChiTietSP.IDPhoiMau = dbo.PhoiMau.IDMau INNER JOIN
                                                                                          dbo.Size ON dbo.ChiTietSP.IDSize = dbo.Size.IDSize INNER JOIN
                                                                                          dbo.DoDay ON dbo.ChiTietSP.IDDoDay = dbo.DoDay.IDDoday INNER JOIN
                                                                                          dbo.ChatLieu ON dbo.ChiTietSP.IDChatlieu = dbo.ChatLieu.IDChatlieu INNER JOIN
                                                                                          dbo.MatDe ON dbo.ChiTietSP.IDMatDe = dbo.MatDe.IDMatDe INNER JOIN
                                                                                          dbo.Dayy ON dbo.ChiTietSP.IDDay = dbo.Dayy.IDDay
                                        						 Where ChiTietSP.Deleted = '0' AND MaCTSP  like ? or TenDSP like ?  or Trangthai = ?
                       """;
        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setObject(1, timkiem + "%");
            ps.setObject(2, timkiem + "%");
            ps.setObject(3, timkiem);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sanphamchitietviewmodel ctsp = new sanphamchitietviewmodel();
                ctsp.setMctsp(rs.getString(1));
                ctsp.setIdhangsx(rs.getString(2));
                ctsp.setIddongsp(rs.getString(3));
                ctsp.setIdphoimau(rs.getString(4));
                ctsp.setIdsize(rs.getString(5));
                ctsp.setIddoday(rs.getString(6));
                ctsp.setIdchatlieu(rs.getString(7));
                ctsp.setIdmatde(rs.getString(8));
                ctsp.setIdday(rs.getString(9));
                ctsp.setGiaban(rs.getInt(10));
                listsanpham.add(ctsp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsanpham;
    }

    public List<sanphamchitietviewmodel> Searchbanhang(String timkiem) {
        List<sanphamchitietviewmodel> listsanpham = new ArrayList<>();
        String sql = """
SELECT        dbo.ChiTietSP.MaCTSP, dbo.DSP.TenDSP, dbo.HSX.TenHang, dbo.PhoiMau.TenMau, dbo.Size.SizeSP, dbo.ChatLieu.ChatlieuSP, dbo.Dayy.dAYsp, dbo.ChiTietSP.Soluong, dbo.ChiTietSP.Giaban
 FROM            dbo.ChiTietSP INNER JOIN
                          dbo.DSP ON dbo.ChiTietSP.IDDongSP = dbo.DSP.IDdsp INNER JOIN
                          dbo.HSX ON dbo.ChiTietSP.IDHangSX = dbo.HSX.IDhsx INNER JOIN
                          dbo.PhoiMau ON dbo.ChiTietSP.IDPhoiMau = dbo.PhoiMau.IDMau INNER JOIN
                          dbo.Size ON dbo.ChiTietSP.IDSize = dbo.Size.IDSize INNER JOIN
                          dbo.ChatLieu ON dbo.ChiTietSP.IDChatlieu = dbo.ChatLieu.IDChatlieu INNER JOIN
                          dbo.Dayy ON dbo.ChiTietSP.IDDay = dbo.Dayy.IDDay
						  Where ChiTietSP.Deleted = '0' And MaCTSP Like ? or  DSP.TenDSP Like ? or Soluong Like ? or Size.SizeSP  Like ? or Giaban Like ? or HSX.TenHang Like ? or PhoiMau.TenMau Like ? or Size.SizeSP = ? or ChatLieu.ChatlieuSP Like ? or Dayy.dAYsp Like ?                     
                       """;
        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setObject(1, '%' + timkiem + '%');
            ps.setObject(2, '%' + timkiem + '%');
            ps.setObject(3, '%' + timkiem + '%');
            ps.setObject(4, '%' + timkiem + '%');
            ps.setObject(5, timkiem );
            ps.setObject(6, '%' + timkiem + '%');
            ps.setObject(7, '%' + timkiem + '%');
            ps.setObject(8, '%' + timkiem + '%');
            ps.setObject(9, '%' + timkiem + '%');
            ps.setObject(10, '%' + timkiem + '%');

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sanphamchitietviewmodel ctsp = new sanphamchitietviewmodel();
                ctsp.setMctsp(rs.getString(1));
                ctsp.setIddongsp(rs.getString(2));
                ctsp.setIdhangsx(rs.getString(3));
                ctsp.setIdphoimau(rs.getString(4));
                ctsp.setIdsize(rs.getString(5));
                ctsp.setIdchatlieu(rs.getString(6));
                ctsp.setIdday(rs.getString(7));
                ctsp.setSoluong(rs.getInt(8));
                ctsp.setGiaban(rs.getInt(9));
                listsanpham.add(ctsp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsanpham;
    }

    public List<sanphamchitietviewmodel> clicksanpham() {
        List<sanphamchitietviewmodel> listsanpham = new ArrayList<>();
        String sql = """
                               SELECT        dbo.ChiTietSP.MaCTSP, dbo.HSX.TenHang, dbo.DSP.TenDSP, dbo.PhoiMau.TenMau, dbo.Size.SizeSP, dbo.DoDay.doDaySP, dbo.ChatLieu.ChatlieuSP, dbo.MatDe.MatDeSP, dbo.Dayy.dAYsp, dbo.ChiTietSP.Giaban,dbo.ChiTietSP.Soluong
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                FROM            dbo.ChiTietSP INNER JOIN
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         dbo.HSX ON dbo.ChiTietSP.IDHangSX = dbo.HSX.IDhsx INNER JOIN
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         dbo.DSP ON dbo.ChiTietSP.IDDongSP = dbo.DSP.IDdsp INNER JOIN
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         dbo.PhoiMau ON dbo.ChiTietSP.IDPhoiMau = dbo.PhoiMau.IDMau INNER JOIN
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         dbo.Size ON dbo.ChiTietSP.IDSize = dbo.Size.IDSize INNER JOIN
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         dbo.DoDay ON dbo.ChiTietSP.IDDoDay = dbo.DoDay.IDDoday INNER JOIN
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         dbo.ChatLieu ON dbo.ChiTietSP.IDChatlieu = dbo.ChatLieu.IDChatlieu INNER JOIN
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         dbo.MatDe ON dbo.ChiTietSP.IDMatDe = dbo.MatDe.IDMatDe INNER JOIN
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         dbo.Dayy ON dbo.ChiTietSP.IDDay = dbo.Dayy.IDDay
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       						 Where MaCTSP = ? And ChiTietSP.Deleted  = 0

                       """;
        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sanphamchitietviewmodel ctsp = new sanphamchitietviewmodel();
                ctsp.setMctsp(rs.getString(1));
                ctsp.setIdhangsx(rs.getString(2));
                ctsp.setIddongsp(rs.getString(3));
                ctsp.setIdphoimau(rs.getString(4));
                ctsp.setIdsize(rs.getString(5));
                ctsp.setIddoday(rs.getString(6));
                ctsp.setIdchatlieu(rs.getString(7));
                ctsp.setIdmatde(rs.getString(8));
                ctsp.setIdday(rs.getString(9));
                ctsp.setGiaban(rs.getInt(10));
                ctsp.setSoluong(rs.getInt(11));
                listsanpham.add(ctsp);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return listsanpham;
    }

    public List<sanphamchitietviewmodel> TIMGIA(int gia1, int gia2) {
        List<sanphamchitietviewmodel> listsanpham = new ArrayList<>();
        String sql = """
                 SELECT        dbo.ChiTietSP.MaCTSP, dbo.HSX.TenHang, dbo.DSP.TenDSP, dbo.PhoiMau.TenMau, dbo.Size.SizeSP, dbo.DoDay.doDaySP, dbo.ChatLieu.ChatlieuSP, dbo.MatDe.MatDeSP, dbo.Dayy.dAYsp, dbo.ChiTietSP.Giaban,dbo.ChiTietSP.Soluong
                                                                                                                                                                                                                                                                         FROM            dbo.ChiTietSP INNER JOIN
                                                                                                                                                                                                                                                                                                  dbo.HSX ON dbo.ChiTietSP.IDHangSX = dbo.HSX.IDhsx INNER JOIN
                                                                                                                                                                                                                                                                                                  dbo.DSP ON dbo.ChiTietSP.IDDongSP = dbo.DSP.IDdsp INNER JOIN
                                                                                                                                                                                                                                                                                                  dbo.PhoiMau ON dbo.ChiTietSP.IDPhoiMau = dbo.PhoiMau.IDMau INNER JOIN
                                                                                                                                                                                                                                                                                                  dbo.Size ON dbo.ChiTietSP.IDSize = dbo.Size.IDSize INNER JOIN
                                                                                                                                                                                                                                                                                                  dbo.DoDay ON dbo.ChiTietSP.IDDoDay = dbo.DoDay.IDDoday INNER JOIN
                                                                                                                                                                                                                                                                                                  dbo.ChatLieu ON dbo.ChiTietSP.IDChatlieu = dbo.ChatLieu.IDChatlieu INNER JOIN
                                                                                                                                                                                                                                                                                                  dbo.MatDe ON dbo.ChiTietSP.IDMatDe = dbo.MatDe.IDMatDe INNER JOIN
                                                                                                                                                                                                                                                                                                  dbo.Dayy ON dbo.ChiTietSP.IDDay = dbo.Dayy.IDDay
                                                                                                                                                                                                                                                						WHERE ChiTietSP.Giaban BETWEEN ? AND ?
                       """;
        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setObject(1, gia1);
            ps.setObject(2, gia2);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sanphamchitietviewmodel ctsp = new sanphamchitietviewmodel();
                ctsp.setMctsp(rs.getString(1));
                ctsp.setIdhangsx(rs.getString(2));
                ctsp.setIddongsp(rs.getString(3));
                ctsp.setIdphoimau(rs.getString(4));
                ctsp.setIdsize(rs.getString(5));
                ctsp.setIddoday(rs.getString(6));
                ctsp.setIdchatlieu(rs.getString(7));
                ctsp.setIdmatde(rs.getString(8));
                ctsp.setIdday(rs.getString(9));
                ctsp.setGiaban(rs.getInt(10));
                ctsp.setSoluong(rs.getInt(11));
                listsanpham.add(ctsp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listsanpham;
    }

    public static void main(String[] args) {
        List<sanphamchitietviewmodel> list = new chitietsanphamviewmodelRepo().getall();
        for (sanphamchitietviewmodel object : list) {
            System.out.println(object.toString());
        }
    }
}
