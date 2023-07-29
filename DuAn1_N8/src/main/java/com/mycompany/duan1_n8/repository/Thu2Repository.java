/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.entity.Thu2;
import com.mycompany.duan1_n8.utilities.HibernateUtil;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author BXT
 */
public class Thu2Repository {
    Session session = HibernateUtil.getFACTORY().openSession();

    public ArrayList<Thu2> getAll() {
        session = HibernateUtil.getFACTORY().openSession();
        Query q = session.createQuery("From Thu2");
        ArrayList<Thu2> list = (ArrayList<Thu2>) q.getResultList();
        return list;
    }
}
