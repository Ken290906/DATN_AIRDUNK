/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package b1.repository;
import ViewModelSP.sanphamviewmodel;
import b1.entity.DongSanPham;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author DELL
 */
public class dongsanphamrepo {
     public  List<DongSanPham> getall(){
         List<DongSanPham> listdsp = new ArrayList<>();
        String sql = """
                   SELECT [IDdsp]
                             ,[TenDSP]
                             ,[Deleted]
                             ,[CreatedAt]
                             ,[CreatedBy]
                             ,[UpdatedAt]
                             ,[UpdatedBy]
                             ,[Trangthai]
                             ,[soluong]
                             ,[mota]
                         FROM [dbo].[DSP]
                     """;
        try (Connection c = DBConnect.getConnection();PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                DongSanPham hsx = new DongSanPham();
                hsx.setIDdsp(rs.getString(1));
                hsx.setTendsp(rs.getString(2));
                hsx.setDelete(rs.getInt(3));
                hsx.setCreatedat(rs.getDate(4));
                hsx.setCreateby(rs.getString(5));
                hsx.setUpdateat(rs.getDate(6));
                hsx.setUpdateby(rs.getString(7));
                hsx.setTrangthai(rs.getString(8));
                hsx.setSoluong(rs.getInt(9));
                hsx.setMota(rs.getString(10));
                listdsp.add(hsx);
            }
        } catch (Exception e) {
        }
        return listdsp;
    }
     public List<DongSanPham> Search(String timkiem) {
         List<DongSanPham> listdsp = new ArrayList<>();
        String sql = """
             SELECT [IDdsp]
                                          ,[TenDSP]                                                                            
                                          ,[soluong]                                    
                                      FROM [dbo].[DSP]                                 
                       Where TenDSP LIKE ?  OR IDdsp LIKE ? OR soluong LIKE ? 
                     """;
        try (Connection c = DBConnect.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setObject(1, '%' + timkiem + '%');
            ps.setObject(2, '%' + timkiem + '%');
             ps.setObject(3,  timkiem );
 
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DongSanPham hsx = new DongSanPham();
                hsx.setIDdsp(rs.getString(1));
                hsx.setTendsp(rs.getString(2));   
                hsx.setSoluong(rs.getInt(3));            
                listdsp.add(hsx);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listdsp;
    }
    public static void main(String[] args) {
        List<DongSanPham> list = new dongsanphamrepo().getall();
        for (DongSanPham object : list) {
            System.out.println(object.toString());
        }
    }
}
