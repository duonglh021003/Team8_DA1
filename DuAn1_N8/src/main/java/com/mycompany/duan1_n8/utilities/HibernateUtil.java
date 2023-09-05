/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.utilities;

import com.mycompany.duan1_n8.entity.CheckIn;
<<<<<<< HEAD
<<<<<<< HEAD
import com.mycompany.duan1_n8.entity.CheckOut;
=======
import com.mycompany.duan1_n8.entity.ChiTietSP;
>>>>>>> 2adbb83182d3ca23275ade5e3a034d52273f893b
=======
import com.mycompany.duan1_n8.entity.ChiTietSP;
>>>>>>> 447fd4eeb12693314613b9459df820ef444a5af2
import com.mycompany.duan1_n8.entity.ChuNhat;
import com.mycompany.duan1_n8.entity.ChucVu;
import com.mycompany.duan1_n8.entity.DanhSachLamViec;
import com.mycompany.duan1_n8.entity.DoiTuongSuDung;
<<<<<<< HEAD
=======
import com.mycompany.duan1_n8.entity.HoaDon;
import com.mycompany.duan1_n8.entity.HoaDonChiTiet;
import com.mycompany.duan1_n8.entity.KhachHang;
>>>>>>> 447fd4eeb12693314613b9459df820ef444a5af2
import com.mycompany.duan1_n8.entity.Lop;
import com.mycompany.duan1_n8.entity.MauSac;
import com.mycompany.duan1_n8.entity.NSX;
import com.mycompany.duan1_n8.entity.NhanVien;
<<<<<<< HEAD
=======
import com.mycompany.duan1_n8.entity.PhieuGiamGia;
>>>>>>> 447fd4eeb12693314613b9459df820ef444a5af2
import com.mycompany.duan1_n8.entity.SanPham;
import com.mycompany.duan1_n8.entity.ThietKe;
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
        properties.put(Environment.PASS, "Minh9032003");
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
        conf.addAnnotatedClass(CheckIn.class);
<<<<<<< HEAD
<<<<<<< HEAD
        conf.addAnnotatedClass(CheckOut.class);
=======

=======
        
         conf.addAnnotatedClass(PhieuGiamGia.class);
>>>>>>> 447fd4eeb12693314613b9459df820ef444a5af2
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(NSX.class);
        conf.addAnnotatedClass(ThietKe.class);
        conf.addAnnotatedClass(Lop.class);
        conf.addAnnotatedClass(DoiTuongSuDung.class);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(ChiTietSP.class);
<<<<<<< HEAD
>>>>>>> 2adbb83182d3ca23275ade5e3a034d52273f893b
=======
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);
        
>>>>>>> 447fd4eeb12693314613b9459df820ef444a5af2
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
