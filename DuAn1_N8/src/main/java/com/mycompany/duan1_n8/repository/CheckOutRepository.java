/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;


import com.mycompany.duan1_n8.entity.CheckOut;
import com.mycompany.duan1_n8.utilities.HibernateUtil;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author BXT
 */
public class CheckOutRepository {
    
    Session session = HibernateUtil.getFACTORY().openSession();

    public ArrayList<CheckOut> getAll() {
        session = HibernateUtil.getFACTORY().openSession();
        Query q = session.createQuery("From CheckOut");
        ArrayList<CheckOut> list = (ArrayList<CheckOut>) q.getResultList();
        return list;
    }
    
    public Boolean add(CheckOut checkOut) {
        Transaction transaction = null;
        Integer check = 0;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.beginTransaction();
            check = (Integer) session.save(checkOut);
            transaction.commit();
            return check > 0;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
