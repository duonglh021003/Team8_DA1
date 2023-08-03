/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.ChucVu;
import com.mycompany.duan1_n8.utilities.HibernateUtil;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author BXT
 */
public class ChucVuRepository {
    Session session = HibernateUtil.getFACTORY().openSession();

    public ArrayList<ChucVu> getAll() {
        session = HibernateUtil.getFACTORY().openSession();
        Query q = session.createQuery("From ChucVu");
        ArrayList<ChucVu> list = (ArrayList<ChucVu>) q.getResultList();
        return list;
    }
}
