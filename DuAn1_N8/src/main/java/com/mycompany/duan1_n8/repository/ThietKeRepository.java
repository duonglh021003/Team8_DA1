/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.ThietKe;
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
public class ThietKeRepository {
     public List<ThietKe> getAllTK() {
        List<ThietKe> listThietKe = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM ThietKe ORDER BY Id");
            listThietKe = query.getResultList();
            System.out.println("Load Du Lieu ThietKe Thanh Cong");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Load Du Lieu ThietKe That Bai");
        }
        return listThietKe;
    }

    public boolean addThietKe(ThietKe thietKe) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(thietKe);
            transaction.commit();
            System.out.println("Add Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Add Du Lieu That Bai");
            return false;
        }
    }

    public boolean updateThietKe(ThietKe thietKe) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(thietKe);
            transaction.commit();
            System.out.println("Cap Nhat Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Cap Nhat Du Lieu That Bai");
            return false;
        }
    }

    public boolean deleteThietKe(ThietKe thietKe) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(thietKe);
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
