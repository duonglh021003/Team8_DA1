/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;


import com.mycompany.duan1_n8.entity.ChuNhat;
import com.mycompany.duan1_n8.entity.DanhSachLamViec;
import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.utilities.HibernateUtil;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author BXT
 */
public class DanhSachLamViecRepository {
    
    Session session = HibernateUtil.getFACTORY().openSession();

    public ArrayList<DanhSachLamViec> getAll() {
        session = HibernateUtil.getFACTORY().openSession();
        Query q = session.createQuery("From DanhSachLamViec");
        ArrayList<DanhSachLamViec> list = (ArrayList<DanhSachLamViec>) q.getResultList();
        return list;
    }
    
    public Boolean add(DanhSachLamViec danhSachLamViec) {
        Transaction transaction = null;
        Integer check = 0;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            check = (Integer) session.save(danhSachLamViec);
            transaction.commit();
            return check > 0;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
    
    public Boolean update(DanhSachLamViec danhSachLamViec) {
        Transaction transaction = null;
        boolean check = false;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(danhSachLamViec);
            transaction.commit();
            check = true;
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return check;
    }
    
}
