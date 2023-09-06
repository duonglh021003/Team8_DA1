/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.Login;
import com.mycompany.duan1_n8.entity.NhanVien;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author BXT
 */
public class LoginRepository {
    
    public List<Login> list = new ArrayList<>();
   
    
    private NhanVienRepository nhanVienRepository = new NhanVienRepository();

    
    public Login login(String taiKhoan, String matKhau){
       
        for(NhanVien nhanVien : nhanVienRepository.getAll()){
            if(nhanVien.getSdt().equalsIgnoreCase(taiKhoan) && nhanVien.getMatKhau().equalsIgnoreCase(matKhau)){
                System.out.println("aaaaaaaa    ");
                Login login = new Login();
                login.setTaiKhoan(taiKhoan);
                login.setMatKhau(matKhau);
            }
        }
        System.out.println("ccccccccccc     "+list);
        return login(taiKhoan, matKhau);
    }
    public void output(){
        System.out.println(toString());
    }


    
    
    
    
    
}
