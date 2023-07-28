/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.utilities;

import com.mycompany.duan1_n8.entity.ChuNhat;
import com.mycompany.duan1_n8.entity.ChucVu;
import com.mycompany.duan1_n8.entity.DanhSachLamViec;
import com.mycompany.duan1_n8.entity.MauSac;
import com.mycompany.duan1_n8.entity.NhanVien;
import com.mycompany.duan1_n8.entity.PhieuGiamGia;
import com.mycompany.duan1_n8.entity.Thu2;
import com.mycompany.duan1_n8.entity.Thu3;
import com.mycompany.duan1_n8.entity.Thu4;
import com.mycompany.duan1_n8.entity.Thu5;
import com.mycompany.duan1_n8.entity.Thu6;
import com.mycompany.duan1_n8.entity.Thu7;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author BuiDucMinh
 */
public class HibernateUtil {

    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=Team8_DuAn1");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
       
        conf.addAnnotatedClass(Thu2.class);
        conf.addAnnotatedClass(Thu3.class);
        conf.addAnnotatedClass(Thu4.class);
        conf.addAnnotatedClass(Thu5.class);
        conf.addAnnotatedClass(Thu6.class);
        conf.addAnnotatedClass(Thu7.class);
        conf.addAnnotatedClass(ChuNhat.class);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(DanhSachLamViec.class);
        
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        getFACTORY();
    }
}
