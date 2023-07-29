/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.Thu2;
import com.mycompany.duan1_n8.entity.Thu5;
import com.mycompany.duan1_n8.utilities.HibernateUtil;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author BXT
 */
public class Thu5Repository {
    
    Session session = HibernateUtil.getFACTORY().openSession();

    public ArrayList<Thu5> getAll() {
        session = HibernateUtil.getFACTORY().openSession();
        Query q = session.createQuery("From Thu5");
        ArrayList<Thu5> list = (ArrayList<Thu5>) q.getResultList();
        return list;
    }
    
}
