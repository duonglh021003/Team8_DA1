/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.CheckIn;
import com.mycompany.duan1_n8.entity.ChuNhat;
import com.mycompany.duan1_n8.entity.DanhSachLamViec;
import com.mycompany.duan1_n8.utilities.HibernateUtil;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author BXT
 */
public class CheckInRepository {
    
    Session session = HibernateUtil.getFACTORY().openSession();

    public ArrayList<CheckIn> getAll() {
        session = HibernateUtil.getFACTORY().openSession();
        Query q = session.createQuery("From CheckIn");
        ArrayList<CheckIn> list = (ArrayList<CheckIn>) q.getResultList();
        return list;
    }
    
    public Boolean add(CheckIn checkIn) {
        Transaction transaction = null;
        Integer check = 0;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            check = (Integer) session.save(checkIn);
            transaction.commit();
            return check > 0;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
