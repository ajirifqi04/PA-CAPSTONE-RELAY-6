package dao;

import config.HibernateUtil;
import model.Pembayaran;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PembayaranDAO implements Repository<Pembayaran> {

    public boolean tambah(Pembayaran pembayaran) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(pembayaran);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    public boolean ubah(Pembayaran pembayaran) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(pembayaran);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    public Pembayaran cariBerdasarkanId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Pembayaran.class, id);
        }
    }

    public Pembayaran cariBerdasarkanResi(String nomorResi) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Pembayaran WHERE pengirimanNomorResi = :resi",
                    Pembayaran.class)
                    .setParameter("resi", nomorResi)
                    .uniqueResult();
        }
    }

    public List<Pembayaran> tampilkanSemua() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Pembayaran ORDER BY tanggalPembayaran DESC",
                    Pembayaran.class)
                    .list();
        }
    }

    public List<Pembayaran> tampilkanBerdasarkanPengguna(int idPengguna) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Pembayaran WHERE penggunaIdPengguna = :id",
                    Pembayaran.class)
                    .setParameter("id", idPengguna)
                    .list();
        }
    }

    public boolean hapus(int id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Pembayaran pembayaran = session.get(Pembayaran.class, id);

            if (pembayaran == null) {
                transaction.rollback();
                return false;
            }

            session.remove(pembayaran);
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