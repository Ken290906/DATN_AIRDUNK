/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package b1.entity;

import java.util.Date;

/**
 *
 * @author Huanh
 */
public class HoaDonBH {

    private String maHD;
    private String MaKH;
    private String MaHTTT;
    private String MaVCH;
    private String TenSP;
    private String tenKH;
    private double Tongtien;
    private int Sdt;
    private String diaChi;
    private Date ngaytao;
    private Date ngaythanh;
    private String maNV;
    private int soluong;
    private float trangthai2;

    public HoaDonBH() {
    }

    public HoaDonBH(String maHD, String maNV, String tenKH, int soluong, float trangthai2, int Sdt) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.tenKH = tenKH;
        this.soluong = soluong;
        this.trangthai2 = trangthai2;
        this.Sdt = Sdt;
    }

    public HoaDonBH(String maHD, Date ngaytao, Date ngaythanh, String maNV, int soluong, float trangthai2) {
        this.maHD = maHD;
        this.ngaytao = ngaytao;
        this.ngaythanh = ngaythanh;
        this.maNV = maNV;
        this.soluong = soluong;
        this.trangthai2 = trangthai2;
    }

    public HoaDonBH(String maHD, String MaKH, String tenKH, int Sdt, double Tongtien, String diaChi, Date ngaytao, Date ngaythanh, String maNV, int soluong, String MaHTTT, float trangthai2) {
        this.maHD = maHD;
        this.MaKH = MaKH;
        this.tenKH = tenKH;
        this.Sdt = Sdt;
        this.Tongtien = Tongtien;
        this.diaChi = diaChi;
        this.ngaytao = ngaytao;
        this.ngaythanh = ngaythanh;
        this.maNV = maNV;
        this.soluong = soluong;
        this.MaHTTT = MaHTTT;
        this.trangthai2 = trangthai2;
    }

    public HoaDonBH(String MaKH, String tenKH, double Tongtien, int Sdt, String diaChi, Date ngaythanh, String maNV, float trangthai2,String MaHTTT) {
        this.MaKH = MaKH;
        this.tenKH = tenKH;
        this.Tongtien = Tongtien;
        this.Sdt = Sdt;
        this.diaChi = diaChi;
        this.ngaythanh = ngaythanh;
        this.maNV = maNV;
        this.trangthai2 = trangthai2;
        this.MaHTTT = MaHTTT;
    }
    

    public float getTrangthai2() {
        return trangthai2;
    }

    public void setTrangthai2(float trangthai2) {
        this.trangthai2 = trangthai2;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getSdt() {
        return Sdt;
    }

    public void setSdt(int Sdt) {
        this.Sdt = Sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaHD() {
        return maHD;
    }

    @Override
    public String toString() {
        return "HoaDonBH{" + "maHD=" + maHD + ", tenKH=" + tenKH + ", Sdt=" + Sdt + ", maNV=" + maNV + ", soluong=" + soluong + ", trangthai2=" + trangthai2 + '}';
    }

    

    

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getMaHTTT() {
        return MaHTTT;
    }

    public void setMaHTTT(String MaHTTT) {
        this.MaHTTT = MaHTTT;
    }

    public String getMaVCH() {
        return MaVCH;
    }

    public void setMaVCH(String MaVCH) {
        this.MaVCH = MaVCH;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public double getTongtien() {
        return Tongtien;
    }

    public void setTongtien(double Tongtien) {
        this.Tongtien = Tongtien;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public Date getNgaythanh() {
        return ngaythanh;
    }

    public void setNgaythanh(Date ngaythanh) {
        this.ngaythanh = ngaythanh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    
}
