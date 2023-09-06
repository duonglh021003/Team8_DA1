/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.entity;

/**
 *
 * @author BXT
 */
public class Login {
    
    private String taiKhoan;
    
    private String matKhau;

    public Login() {
    }

    

    @Override
    public String toString() {
        return "Login{" + "taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + '}';
    }

    

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }
    
    
}
