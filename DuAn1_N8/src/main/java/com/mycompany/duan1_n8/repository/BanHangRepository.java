/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.ChiTietSP;
import com.mycompany.duan1_n8.entity.HoaDon;
import com.mycompany.duan1_n8.entity.IdHoaDonChiTiet;
import com.mycompany.duan1_n8.utilities.HibernateUtil;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author minhb
 */
public class BanHangRepository {

    // Update So Luong San Pham Khi Add Vao Gio Hang
    public boolean updateQuantity(ChiTietSP chiTietSP) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query query = session.createQuery("update ChiTietSP set SoLuong= :soLuong where MaQR= :maSanPham");
            query.setParameter("soLuong", chiTietSP.getSoLuong());
            query.setParameter("maSanPham", chiTietSP.getMaQr());
            session.update(chiTietSP);
            transaction.commit();
            System.out.println("Update Quantity Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
            System.out.println("Update Quantity That Bai");
            return false;
        }
    }

    // Huy hoa don
    public boolean huyHoaDon(HoaDon hoaDon) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Query query = session.createQuery("update HoaDon set TrangThai = 2 where Id = :idHoaDon");
            query.setParameter("idHoaDon", hoaDon.getIdHoaDon());
            query.executeUpdate();
            transaction.commit();
            System.out.println("Huy Hoa Don Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
            System.out.println("Huy Hoa Don That Bai");
            return false;
        }
    }

    // Xoa San Pham Trong Gio
    public boolean deleteOrder(IdHoaDonChiTiet idHoaDonChiTiet) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(idHoaDonChiTiet);
            transaction.commit();
            System.out.println("Xoa San Pham Trong Gio Hang Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
            System.out.println("Xoa San Pham Trong Gio Hang That Bai");
            return false;
        }
    }

    // Thanh Toan
    public boolean updateThanhToan(HoaDon hoaDon) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(hoaDon);
            transaction.commit();
            System.out.println("Update Trang Thai Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
            System.out.println("Update Trang Thai That Bai");
            return false;
        }
    }

    
}
