/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package b1.services;

import b1.entity.khachhang;
import b1.repository.khachhangRepository;
import ViewModelKH.khachhangViewModel;
import java.util.List;

/**
 *
 * @author BigTech
 */
public class khachhangService {
 private khachhangRepository repo = new khachhangRepository();
    
 public List<khachhangViewModel>getAll(){
        return  repo.getAll();
        
    }
 public boolean xoa(String xoa){
     return repo.xoa(xoa);
 }
 public String add(khachhang kh){
     if (kh == null) {
         return "Add không thành công";
     }
     repo.add(kh);
     return "Add thành công";
 }
 public boolean addbanhang(khachhang kh){
     return repo.addkhachhangbanhang(kh);
 }
 public boolean  sua (b1.entity.khachhang kh , String oldma){
     return repo.sua(kh, oldma);
 }
  public List<khachhangViewModel> Search(String type) {
       return repo.Search(type);
   }
   public List<khachhangViewModel> Searchkhachhangbanhang(String timkiem) {
       return repo.Searchkhachhangbanhang(timkiem);
   }
    public List<khachhangViewModel>Makh(){
        return  repo.getMaKH();
        
    }
    public List<khachhangViewModel> Tenkh(){
        return  repo.getTenKH();
        
    }
   
 
}
