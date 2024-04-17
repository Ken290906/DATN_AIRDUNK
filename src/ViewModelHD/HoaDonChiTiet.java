/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModelHD;

/**
 *
 * @author Huanh
 */
public class HoaDonChiTiet {
    
    private String maHDCT;
    private String maHD;
    private String idCTSP;
    private int Soluong;
    private float donGia;
    private String hang;
    private String mau;
    private String size;
    private String doday;
    private String chatlieu;
    private String matde;
    private String day;
    

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String maHDCT, String maHD, String idCTSP, float donGia) {
        this.maHDCT = maHDCT;
        this.maHD = maHD;
        this.idCTSP = idCTSP;
        this.donGia = donGia;
    }

    public String getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(String maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(String idCTSP) {
        this.idCTSP = idCTSP;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }
    
    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDoday() {
        return doday;
    }

    public void setDoday(String doday) {
        this.doday = doday;
    }

    public String getChatlieu() {
        return chatlieu;
    }

    public void setChatlieu(String chatlieu) {
        this.chatlieu = chatlieu;
    }

    public String getMatde() {
        return matde;
    }

    public void setMatde(String matde) {
        this.matde = matde;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "maHDCT=" + maHDCT + ", maHD=" + maHD + ", idCTSP=" + idCTSP + ", Soluong=" + Soluong + ", donGia=" + donGia + ", hang=" + hang + ", mau=" + mau + ", size=" + size + ", doday=" + doday + ", chatlieu=" + chatlieu + ", matde=" + matde + ", day=" + day + '}';
    }

    

    
}
