package dao;

import config.HibernateUtil;
import model.LokasiOperasional;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LokasiDAO implements Repository<LokasiOperasional> {

    public boolean tambah(LokasiOperasional lokasi) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(lokasi);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    public boolean ubah(LokasiOperasional lokasi) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(lokasi);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    public LokasiOperasional cariBerdasarkanId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(LokasiOperasional.class, id);
        }
    }

    public List<LokasiOperasional> tampilkanSemua() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM LokasiOperasional ORDER BY idLokasi",
                    LokasiOperasional.class)
                    .list();
        }
    }

    public boolean hapus(int id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            LokasiOperasional lokasi = session.get(LokasiOperasional.class, id);

            if (lokasi == null) {
                transaction.rollback();
                return false;
            }

            session.remove(lokasi);
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