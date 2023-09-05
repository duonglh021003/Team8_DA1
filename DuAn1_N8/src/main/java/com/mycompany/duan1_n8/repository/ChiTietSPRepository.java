/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.ChiTietSP;
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
public class ChiTietSPRepository {

    public List<ChiTietSP> getAllCTSP() {
        List<ChiTietSP> listCTSP = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM ChiTietSP ORDER BY MaQR");
            listCTSP = query.getResultList();
            System.out.println("Load Du Lieu Chi Tiet San Pham Thanh Cong");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Load Du Lieu Chi Tiet San Pham That Bai");
        }
        return listCTSP;
    }

    public ChiTietSP getOne(Long idSP) {
        ChiTietSP chiTietSP = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = null;
            transaction = session.getTransaction();
            transaction.begin();
            Query query = session.createQuery("FROM ChiTietSP WHERE MaQR = :idSP");
            query.setParameter("idSP", idSP);
            chiTietSP = (ChiTietSP) query.getResultList().get(0);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return chiTietSP;
    }

    public boolean addCTSP(ChiTietSP chiTietSP) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(chiTietSP);
            transaction.commit();
            System.out.println("Add Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Add Du Lieu That Bai");
            return false;
        }
    }

    public boolean updateCTSP(ChiTietSP chiTietSP) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(chiTietSP);
            transaction.commit();
            System.out.println("Cap Nhat Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Cap Nhat Du Lieu That Bai");
            return false;
        }
    }

    public boolean deleteCTSP(ChiTietSP chiTietSP) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            session.delete(chiTietSP);
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
