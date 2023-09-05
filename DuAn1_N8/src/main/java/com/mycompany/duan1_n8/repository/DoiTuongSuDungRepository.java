/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.DoiTuongSuDung;
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
public class DoiTuongSuDungRepository {
     public List<DoiTuongSuDung> getAllDTSD() {
        List<DoiTuongSuDung> listDTSD = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM DoiTuongSuDung ORDER BY Id");
            listDTSD = query.getResultList();
            System.out.println("Load Du Lieu Doi Tuong Su Dung Thanh Cong");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Load Du Lieu Doi Tuong Su Dung That Bai");
        }
        return listDTSD;
    }

    public boolean addDTSD(DoiTuongSuDung dtsd) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(dtsd);
            transaction.commit();
            System.out.println("Add Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Add Du Lieu That Bai");
            return false;
        }
    }

    public boolean updateDTSD(DoiTuongSuDung dtsd) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(dtsd);
            transaction.commit();
            System.out.println("Cap Nhat Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Cap Nhat Du Lieu That Bai");
            return false;
        }
    }

    public boolean deleteDTSD(DoiTuongSuDung dtsd) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(dtsd);
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
