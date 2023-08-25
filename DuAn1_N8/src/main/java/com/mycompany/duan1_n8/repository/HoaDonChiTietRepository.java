/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.HoaDonChiTiet;
import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.utilities.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author minhb
 */
public class HoaDonChiTietRepository {

    public List<HoaDonChiTiet> getAllHDCTById(Long idHoaDon) {
        List<HoaDonChiTiet> listHDCTById = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM HoaDonChiTiet WHERE IdHoaDon = :idHoaDon");
            query.setParameter("idHoaDon", idHoaDon);
            listHDCTById = query.getResultList();
            System.out.println("Load Du Lieu Voi Danh Sach La:" + listHDCTById);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Load Du Lieu That Bai");
        }
        return listHDCTById;
    }
    
    public boolean addGioHang(HoaDonChiTiet hoaDonChiTiet) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(hoaDonChiTiet);
            transaction.commit();
            System.out.println("Add San Pham Vao Gio Hang Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Add San Pham Vao Gio Hang That Bai");
            return false;
        }
    }

    public boolean updateGioHang(HoaDonChiTiet hoaDonChiTiet) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(hoaDonChiTiet);
            transaction.commit();
            System.out.println("Cap Nhat Gio Hang Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Cap Nhat Gio Hang That Bai");
            return false;
        }
    }

    public boolean deleteGioHang(HoaDonChiTiet hoaDonChiTiet) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(hoaDonChiTiet);
            transaction.commit();
            System.out.println("Xoa Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Xoa That Bai");
            return false;
        }
    }
    
    Session session = HibernateUtil.getFACTORY().openSession();
    public ArrayList<HoaDonChiTiet> getAll() {
        session = HibernateUtil.getFACTORY().openSession();
        Query q = session.createQuery("From HoaDonChiTiet");
        ArrayList<HoaDonChiTiet> list = (ArrayList<HoaDonChiTiet>) q.getResultList();
        return list;
    }
    
    public Boolean update(HoaDonChiTiet hoaDonChiTiet) {
        Transaction transaction = null;
        boolean check = false;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(hoaDonChiTiet);
            transaction.commit();
            check = true;
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return check;
    }
    
}
