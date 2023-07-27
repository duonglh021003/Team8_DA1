/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.Thu2;
import com.mycompany.duan1_n8.entity.Thu6;
import com.mycompany.duan1_n8.utilities.HibernateUtil;
import java.util.ArrayList;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author BXT
 */
public class Thu6Repository {
    
    Session session = HibernateUtil.getFACTORY().openSession();

    public ArrayList<Thu6> getAll() {
        session = HibernateUtil.getFACTORY().openSession();
        Query q = session.createQuery("From Thu6");
        ArrayList<Thu6> list = (ArrayList<Thu6>) q.getResultList();
        return list;
    }
    
}
