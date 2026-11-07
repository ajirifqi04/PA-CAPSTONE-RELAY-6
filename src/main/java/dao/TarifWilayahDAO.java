package dao;

import config.HibernateUtil;
import model.TarifWilayah;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TarifWilayahDAO implements Repository<TarifWilayah> {

    public boolean tambah(TarifWilayah tarif) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(tarif);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    public boolean ubah(TarifWilayah tarif) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(tarif);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    public TarifWilayah cariBerdasarkanId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(TarifWilayah.class, id);
        }
    }

    public List<TarifWilayah> tampilkanSemua() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM TarifWilayah ORDER BY idTarif",
                    TarifWilayah.class)
                    .list();
        }
    }

    public List<TarifWilayah> cariBerdasarkanWilayah(
            String kotaAsal, String kotaTujuan, int idLayanan) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM TarifWilayah WHERE kotaAsal = :asal "
                    + "AND kotaTujuan = :tujuan "
                    + "AND layananIdLayanan = :layanan",
                    TarifWilayah.class)
                    .setParameter("asal", kotaAsal)
                    .setParameter("tujuan", kotaTujuan)
                    .setParameter("layanan", idLayanan)
                    .list();
        }
    }

    public boolean hapus(int id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            TarifWilayah tarif = session.get(TarifWilayah.class, id);

            if (tarif == null) {
                transaction.rollback();
                return false;
            }

            session.remove(tarif);
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