/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.Thu2;
import com.mycompany.duan1_n8.entity.Thu7;
import com.mycompany.duan1_n8.utilities.HibernateUtil;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author BXT
 */
public class Thu7Repository {
    
    Session session = HibernateUtil.getFACTORY().openSession();

    public ArrayList<Thu7> getAll() {
        session = HibernateUtil.getFACTORY().openSession();
        Query q = session.createQuery("From Thu7");
        ArrayList<Thu7> list = (ArrayList<Thu7>) q.getResultList();
        return list;
    }
    
}
