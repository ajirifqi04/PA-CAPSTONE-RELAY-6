package dao;

import config.HibernateUtil;
import model.Layanan;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LayananDAO implements Repository<Layanan> {

    public boolean tambah(Layanan layanan) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(layanan);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    public boolean ubah(Layanan layanan) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(layanan);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    public Layanan cariBerdasarkanId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Layanan.class, id);
        }
    }

    public List<Layanan> tampilkanSemua() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Layanan ORDER BY idLayanan",
                    Layanan.class)
                    .list();
        }
    }

    public List<Layanan> tampilkanLayananAktif() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Layanan WHERE statusAktif = 'Aktif' ORDER BY idLayanan",
                    Layanan.class)
                    .list();
        }
    }

    public boolean hapus(int id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Layanan layanan = session.get(Layanan.class, id);

            if (layanan == null) {
                transaction.rollback();
                return false;
            }

            session.remove(layanan);
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