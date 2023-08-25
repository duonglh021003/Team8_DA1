/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.duan1_n8.repository;

import com.mycompany.duan1_n8.entity.PhieuGiamGia;
import com.mycompany.duan1_n8.utilities.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author minhb
 */
public class PhieuGiamGiaRepository {

    public List<PhieuGiamGia> getAllPGG() {
        List<PhieuGiamGia> phieuGiamGias = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession();) {
            Query query = session.createQuery("FROM PhieuGiamGia ORDER BY id");
            phieuGiamGias = query.getResultList();
            System.out.println("Load Du Lieu Phieu Giam Gia Thanh Cong");
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Load Du Lieu Phieu Giam Gia That Bai");
        }
        return phieuGiamGias;
    }

    public PhieuGiamGia getOne(String maPhieuGiamGia) {
        PhieuGiamGia phieuGiamGia = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM PhieuGiamGia where Ma = :maPhieuGiamGia");
            query.setParameter("maPhieuGiamGia", maPhieuGiamGia);
            phieuGiamGia = (PhieuGiamGia) query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return phieuGiamGia;
    }

    public boolean addPhieuGiamGia(PhieuGiamGia phieuGiamGia) {
        Transaction transaction = null;
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.save(phieuGiamGia);
            transaction.commit();
            System.out.println("Add Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            transaction.rollback();
            System.out.println("Add Du Lieu That Bai");

            return false;
        }
    }

    public boolean updatePhieuGiamGia(PhieuGiamGia phieuGiamGia) {
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(phieuGiamGia);
            transaction.commit();
            System.out.println("Cap Nhat Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Cap Nhat Du Lieu That Bai");
        }
        return false;
    }

    public boolean daKetThuc(PhieuGiamGia phieuGiamGia) { // neu ngay ket thuc nho hon ngay hien tai => trang thai = 0
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Query query = session.createQuery("update PhieuGiamGia set TrangThai = 0 where NgayKT < getDate()");
            query.executeUpdate();
            transaction.commit();
            System.out.println("Xoa Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
            System.out.println("Xoa Du Lieu That Bai");
            return false;
        }
    }

    public List<PhieuGiamGia> searchTrangThai(Integer trangThai) {
        List<PhieuGiamGia> searchTT = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM PhieuGiamGia where TrangThai = :trangThai");
            query.setParameter("trangThai", trangThai);
            searchTT = query.getResultList();
            System.out.println("Loc Trang Thai Thanh Cong Voi Trang Thai = " + trangThai);
            return searchTT;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
            System.out.println("Loc Trang Thai That Bai Voi Trang Thai = " + trangThai);
        }
        return null;
    }

    public List<PhieuGiamGia> searchNgay(Date ngayBatDau, Date ngayKetThuc) {
        List<PhieuGiamGia> searchNgay = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM PhieuGiamGia WHERE NgayBD BETWEEN :ngayBatDau AND :ngayKetThuc");
            query.setParameter("ngayBatDau", ngayBatDau);
            query.setParameter("ngayKetThuc", ngayKetThuc);
            searchNgay = query.getResultList();
            System.out.println("Loc Thanh Cong");
            return searchNgay;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println("Loc That Bai = " + e);
        }
        return null;
    }

    public List<PhieuGiamGia> searchTen(String tenPhieu) {
        List<PhieuGiamGia> listSearchTen = new ArrayList<>();
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM PhieuGiamGia WHERE Ten = :tenPhieu");
            query.setParameter("tenPhieu", tenPhieu);
            listSearchTen = query.getResultList();
            System.out.println("Loc Thanh Cong Voi Ten La: " + tenPhieu);
            return listSearchTen;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
            System.out.println("Loc That Bai Voi Ten La: " + tenPhieu);
        }
        return null;
    }

    public boolean sapBatDau(PhieuGiamGia phieuGiamGia) { // neu ngay bat dau va ngay ket thuc lon hon ngay ngay hien tai => trang thai la 2
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Query query = session.createQuery("update PhieuGiamGia set TrangThai = 2 where NgayBD > getDate() and NgayKT > getDate()");
            query.executeUpdate();
            transaction.commit();
            System.out.println("Cap Nhat Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
            System.out.println("Cap Nhat Du Lieu That Bai");
            return false;
        }
    }

    public boolean dangBatDau(PhieuGiamGia phieuGiamGia) { // neu ngay ket thuc lon hon ngay hien tai => trang thai la 1
        try ( Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Query query = session.createQuery("update PhieuGiamGia set TrangThai = 1 where NgayKT > getDate()");
            query.executeUpdate();
            transaction.commit();
            System.out.println("Cap Nhat Du Lieu Thanh Cong");
            return true;
        } catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
            System.out.println("Cap Nhat Du Lieu That Bai");
            return false;
        }
    }

    public static void main(String[] args) {
        new PhieuGiamGiaRepository().getAllPGG().forEach(x -> x.toString());
    }
}
