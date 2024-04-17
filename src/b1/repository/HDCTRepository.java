/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package b1.repository;

import ViewModelHD.HoaDonChiTiet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Huanh
 */
public class HDCTRepository {
    
    public List<HoaDonChiTiet> getAll() {
        
        List<HoaDonChiTiet> list = new ArrayList<>();
        
        String sql = """
                     SELECT        dbo.HoaDon.MaHD, dbo.HoaDonChiTiet.MaHDCT, dbo.ChiTietSP.MaCTSP, dbo.HoaDonChiTiet.SoLuong, dbo.HoaDonChiTiet.DonGia, dbo.HSX.TenHang, dbo.PhoiMau.TenMau, dbo.Size.SizeSP, dbo.DoDay.doDaySP, dbo.ChatLieu.ChatlieuSP, 
                                                                                                dbo.MatDe.MatDeSP, dbo.Dayy.dAYsp
                                                                       FROM            dbo.HoaDon INNER JOIN
                                                                                                dbo.HoaDonChiTiet ON dbo.HoaDon.MaHD = dbo.HoaDonChiTiet.MaHoaDon INNER JOIN
                                                                                                dbo.ChiTietSP ON dbo.HoaDonChiTiet.IDChiTietSP = dbo.ChiTietSP.MaCTSP INNER JOIN
                                                                                                dbo.ChatLieu ON dbo.ChiTietSP.IDChatlieu = dbo.ChatLieu.IDChatlieu INNER JOIN
                                                                                                dbo.Dayy ON dbo.ChiTietSP.IDDay = dbo.Dayy.IDDay INNER JOIN
                                                                                                dbo.DoDay ON dbo.ChiTietSP.IDDoDay = dbo.DoDay.IDDoday INNER JOIN
                                                                                                dbo.HSX ON dbo.ChiTietSP.IDHangSX = dbo.HSX.IDhsx INNER JOIN
                                                                                                dbo.MatDe ON dbo.ChiTietSP.IDMatDe = dbo.MatDe.IDMatDe INNER JOIN
                                                                                                dbo.PhoiMau ON dbo.ChiTietSP.IDPhoiMau = dbo.PhoiMau.IDMau INNER JOIN
                                                                                                dbo.Size ON dbo.ChiTietSP.IDSize = dbo.Size.IDSize
                     """;
        
        try(Connection con = DBConnect.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHD(rs.getString(1));
                hdct.setMaHDCT(rs.getString(2));
                hdct.setIdCTSP(rs.getString(3));
                hdct.setSoluong(rs.getInt(4));
                hdct.setDonGia(rs.getFloat(5));
                hdct.setHang(rs.getString(6));
                hdct.setMau(rs.getString(7));
                hdct.setSize(rs.getString(8));
                hdct.setDoday(rs.getString(9));
                hdct.setChatlieu(rs.getString(10));
                hdct.setMatde(rs.getString(11));
                hdct.setDay(rs.getString(12));
                
                list.add(hdct);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
        
    }
    
    public List<HoaDonChiTiet> getAllID(String maHD) {
        
        List<HoaDonChiTiet> list = new ArrayList<>();
        
        String sql = """
                     SELECT dbo.HoaDon.MaHD, dbo.HoaDonChiTiet.MaHDCT, dbo.ChiTietSP.MaCTSP, dbo.HoaDonChiTiet.DonGia, dbo.HSX.TenHang, dbo.PhoiMau.TenMau, dbo.Size.SizeSP, dbo.DoDay.doDaySP, dbo.ChatLieu.ChatlieuSP, 
                                                                       dbo.MatDe.MatDeSP, dbo.Dayy.dAYsp
                                              FROM            dbo.HoaDon INNER JOIN
                                                                       dbo.HoaDonChiTiet ON dbo.HoaDon.MaHD = dbo.HoaDonChiTiet.MaHoaDon INNER JOIN
                                                                       dbo.ChiTietSP ON dbo.HoaDonChiTiet.IDChiTietSP = dbo.ChiTietSP.MaCTSP INNER JOIN
                                                                       dbo.ChatLieu ON dbo.ChiTietSP.IDChatlieu = dbo.ChatLieu.IDChatlieu INNER JOIN
                                                                       dbo.Dayy ON dbo.ChiTietSP.IDDay = dbo.Dayy.IDDay INNER JOIN
                                                                       dbo.DoDay ON dbo.ChiTietSP.IDDoDay = dbo.DoDay.IDDoday INNER JOIN
                                                                       dbo.HSX ON dbo.ChiTietSP.IDHangSX = dbo.HSX.IDhsx INNER JOIN
                                                                       dbo.MatDe ON dbo.ChiTietSP.IDMatDe = dbo.MatDe.IDMatDe INNER JOIN
                                                                       dbo.PhoiMau ON dbo.ChiTietSP.IDPhoiMau = dbo.PhoiMau.IDMau INNER JOIN
                                                                       dbo.Size ON dbo.ChiTietSP.IDSize = dbo.Size.IDSize
                     WHERE dbo.HoaDon.MaHD = ?
                                              GROUP BY dbo.HoaDon.MaHD, dbo.HoaDonChiTiet.MaHDCT, dbo.ChiTietSP.MaCTSP, dbo.HoaDonChiTiet.DonGia, dbo.HSX.TenHang, dbo.PhoiMau.TenMau, dbo.Size.SizeSP, dbo.DoDay.doDaySP, dbo.ChatLieu.ChatlieuSP, 
                                                                       dbo.MatDe.MatDeSP, dbo.Dayy.dAYsp
                     """;
        
        try(Connection con = DBConnect.getConnection(); 
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, maHD);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHD(rs.getString(1));
                hdct.setMaHDCT(rs.getString(2));
                hdct.setIdCTSP(rs.getString(3));
                hdct.setDonGia(rs.getFloat(4));
                hdct.setHang(rs.getString(5));
                hdct.setMau(rs.getString(6));
                hdct.setSize(rs.getString(7));
                hdct.setDoday(rs.getString(8));
                hdct.setChatlieu(rs.getString(9));
                hdct.setMatde(rs.getString(10));
                hdct.setDay(rs.getString(11));
                
                list.add(hdct);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
        
    }
    
    public static void main(String[] args) {
        List<HoaDonChiTiet> list = new HDCTRepository().getAll();
        
        for (HoaDonChiTiet hd : list) {
            System.out.println(hd.toString());
        }
    }
    
}
