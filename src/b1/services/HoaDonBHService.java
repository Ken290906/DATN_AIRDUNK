/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package b1.services;

import b1.entity.HDChiTiet;
import b1.entity.HoaDonBH;
import b1.entity.LichSuHoaDon;
import b1.repository.HDBanHangRepository;
import java.util.List;

/**
 *
 * @author Huanh
 */
public class HoaDonBHService {
    HDBanHangRepository repo = new HDBanHangRepository();
    
    public List<HoaDonBH> getAll() {
        return repo.getAllBanHang();
    }
    
    public List<HoaDonBH> getAllID(String maHD) {
        return repo.getAllID(maHD);
    }
    
    public List<HoaDonBH> searchHDBH(String maHD) {
        return repo.searchHDBH(maHD);
    }
    
    public boolean Add(HoaDonBH hdbh, HDChiTiet hdct, LichSuHoaDon lshd) {
        return repo.Add(hdbh, hdct, lshd);
    }
    
    public boolean Delete(String maHD, String maHD1, String maHD2) {
        return repo.Delete(maHD, maHD1, maHD2);
    }
    
    public boolean UpdateBanhang(HoaDonBH hd, HDChiTiet hdct, LichSuHoaDon lshd, String MaVCH, String sua, String sua1, String sua2, String MaKH){
        return repo.update(hd, hdct, lshd, MaVCH, sua, sua1, sua2, MaKH);
    }
    
}
