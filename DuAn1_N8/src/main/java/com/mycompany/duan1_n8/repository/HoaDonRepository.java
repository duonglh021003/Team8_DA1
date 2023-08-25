/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.HoaDon;
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
public class HoaDonRepository {

    public List<HoaDon> getAllHD() {
        List<HoaDon> listHoaDon = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM HoaDon ORDER BY Ma desc");
            listHoaDon = query.getResultList();
            System.out.println("Load Du Lieu Hoa Don Thanh Cong");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Load Du Lieu Hoa Don That Bai");
        }
        return listHoaDon;
    }

    public HoaDon getOneHD(Long idHoaDon) {
        HoaDon hoaDon = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM HoaDon WHERE Id = :idHoaDon ORDER BY Ma asc");
            query.setParameter("maHoaDon", idHoaDon);
            hoaDon = (HoaDon) query.getResultList().get(0);
            System.out.println("Doc Du Lieu Thanh Cong Voi Ma Hoa Don Duoc Chon Là:" + hoaDon);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Doc Du Lieu That Bai Voi Ma Hoa Don Duoc Chon La:" + hoaDon);
        }
        return hoaDon;
    }

    public boolean taoHoaDon(HoaDon hoaDon) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(hoaDon);
            transaction.commit();
            System.out.println("Tao Hoa Don Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Tao Hoa Don That Bai");
            return false;
        }
    }

    public boolean updateHoaDon(HoaDon hoaDon) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.update(hoaDon);
            transaction.commit();
            System.out.println("Cap Nhat Trang Thai Hoa Don Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Cap Nhat Trang Thai Hoa Don That Bai");
            return false;
        }
    }
}
