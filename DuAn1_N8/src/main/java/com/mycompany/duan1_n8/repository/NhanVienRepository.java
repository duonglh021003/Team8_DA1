package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.utilities.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author BXT
 */
public class NhanVienRepository {

    Session session = HibernateUtil.getFACTORY().openSession();

    public ArrayList<NhanVien> getAll() {
        session = HibernateUtil.getFACTORY().openSession();
        Query q = session.createQuery("From NhanVien");
        ArrayList<NhanVien> list = (ArrayList<NhanVien>) q.getResultList();
        return list;
    }

    public Boolean add(NhanVien nv) {
        Transaction transaction = null;
        Integer check = 0;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            check = (Integer) session.save(nv);
            transaction.commit();
            return check > 0;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public Boolean delete(Long id) {
        Transaction transaction = null;
        Integer check = 0;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            NhanVien nv = session.get(NhanVien.class, id);
            transaction = session.beginTransaction();
            session.delete(nv);
            transaction.commit();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public Boolean update(NhanVien nhanVien) {
        Transaction transaction = null;
        boolean check = false;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(nhanVien);
            transaction.commit();
            check = true;
             
        } catch (Exception e) {
            System.out.println(e.getMessage());
            transaction.rollback();
        }
        return check;
    }

}
