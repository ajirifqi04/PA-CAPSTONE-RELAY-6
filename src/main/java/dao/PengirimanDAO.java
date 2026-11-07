package dao;

import config.HibernateUtil;
import model.Pengiriman;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PengirimanDAO implements Repository<Pengiriman> {

    public boolean tambah(Pengiriman pengiriman) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(pengiriman);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    public boolean ubah(Pengiriman pengiriman) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(pengiriman);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    public Pengiriman cariBerdasarkanResi(String nomorResi) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Pengiriman.class, nomorResi);
        }
    }

    public List<Pengiriman> tampilkanSemua() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Pengiriman ORDER BY tanggalPengiriman DESC",
                    Pengiriman.class)
                    .list();
        }
    }

    public List<Pengiriman> tampilkanBerdasarkanCabang(int idCabang) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Pengiriman WHERE cabangIdLokasi = :id",
                    Pengiriman.class)
                    .setParameter("id", idCabang)
                    .list();
        }
    }

    public List<Pengiriman> tampilkanBerdasarkanAgen(int idAgen) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Pengiriman WHERE agenIdLokasi = :id",
                    Pengiriman.class)
                    .setParameter("id", idAgen)
                    .list();
        }
    }

    public List<Pengiriman> tampilkanBerdasarkanStatus(String status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Pengiriman WHERE statusPengiriman = :status",
                    Pengiriman.class)
                    .setParameter("status", status)
                    .list();
        }
    }

    public boolean hapus(String nomorResi) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Pengiriman pengiriman = session.get(Pengiriman.class, nomorResi);

            if (pengiriman == null) {
                transaction.rollback();
                return false;
            }

            session.remove(pengiriman);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }
}