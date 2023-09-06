/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.NSX;
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
public class NSXRepository {
     public List<NSX> getAllNSX() {
        List<NSX> listNsx = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM NSX ORDER BY Id");
            listNsx = query.getResultList();
            System.out.println("Load Du Lieu NSX Thanh Cong");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Load Du Lieu NSX That Bai");
        }
        return listNsx;
    }

    public boolean addNsx(NSX nsx) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(nsx);
            transaction.commit();
            System.out.println("Add Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Add Du Lieu That Bai");
            return false;
        }
    }

    public boolean updateNsx(NSX nsx) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(nsx);
            transaction.commit();
            System.out.println("Cap Nhat Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Cap Nhat Du Lieu That Bai");
            return false;
        }
    }

    public boolean deleteNsx(NSX nsx) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(nsx);
            transaction.commit();
            System.out.println("Xoa Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Xoa Du Lieu That Bai");
            return false;
        }
    }
}
