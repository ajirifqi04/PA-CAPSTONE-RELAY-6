package dao;

import config.HibernateUtil;
import model.Pengguna;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PenggunaDAO implements Repository<Pengguna> {

    public Pengguna login(String username, String password) {
        if (username == null || password == null) return null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Pengguna p = session.createQuery("FROM Pengguna WHERE lower(username) = :username", Pengguna.class)
                    .setParameter("username", username.strip().toLowerCase(java.util.Locale.ROOT)).uniqueResult();
            if (p == null) return null;
            if (util.PasswordHash.terhash(p.getPassword()))
                return util.PasswordHash.verify(password, p.getPassword()) ? p : null;
            if (!java.security.MessageDigest.isEqual(p.getPassword().getBytes(java.nio.charset.StandardCharsets.UTF_8),
                    password.getBytes(java.nio.charset.StandardCharsets.UTF_8))) return null;
            Transaction tx = session.beginTransaction();
            try {
                session.refresh(p, org.hibernate.LockMode.PESSIMISTIC_WRITE);
                if (util.PasswordHash.terhash(p.getPassword())) {
                    if (!util.PasswordHash.verify(password, p.getPassword())) { tx.rollback(); return null; }
                } else {
                    if (!java.security.MessageDigest.isEqual(p.getPassword().getBytes(java.nio.charset.StandardCharsets.UTF_8),
                            password.getBytes(java.nio.charset.StandardCharsets.UTF_8))) { tx.rollback(); return null; }
                    p.setPassword(util.PasswordHash.hash(password));
                }
                tx.commit();
                return p;
            } catch (RuntimeException ex) {
                if (tx.isActive()) tx.rollback();
                throw ex;
            }
        }
    }

    public boolean usernameTerdaftar(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Long jumlah = session.createQuery(
                    "SELECT COUNT(p.idPengguna) FROM Pengguna p WHERE p.username = :username",
                    Long.class)
                    .setParameter("username", username)
                    .uniqueResult();

            return jumlah != null && jumlah > 0;
        }
    }

    public boolean tambah(Pengguna pengguna) {
        pengguna.setNamaPengguna(util.Validasi.nama(pengguna.getNamaPengguna(), "Nama lengkap"));
        pengguna.setUsername(util.Validasi.username(pengguna.getUsername()));
        if (!util.PasswordHash.terhash(pengguna.getPassword())) {
            util.Validasi.password(pengguna.getPassword());
            pengguna.setPassword(util.PasswordHash.hash(pengguna.getPassword()));
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.persist(pengguna);
                tx.commit();
                return true;
            } catch (RuntimeException ex) {
                if (tx.isActive()) tx.rollback();
                throw new IllegalStateException("Akun gagal disimpan. Periksa username dan koneksi database.", ex);
            }
        }
    }

    public boolean ubah(Pengguna pengguna) {
        pengguna.setNamaPengguna(util.Validasi.nama(pengguna.getNamaPengguna(), "Nama lengkap"));
        pengguna.setUsername(util.Validasi.username(pengguna.getUsername()));
        if (!util.PasswordHash.terhash(pengguna.getPassword())) {
            util.Validasi.password(pengguna.getPassword());
            pengguna.setPassword(util.PasswordHash.hash(pengguna.getPassword()));
        }
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.merge(pengguna);
                tx.commit();
                return true;
            } catch (RuntimeException ex) {
                if (tx.isActive()) tx.rollback();
                throw new IllegalStateException("Akun gagal disimpan. Periksa username dan koneksi database.", ex);
            }
        }
    }

    public Pengguna cariBerdasarkanId(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Pengguna.class, id);
        }
    }

    public List<Pengguna> tampilkanSemua() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Pengguna ORDER BY idPengguna",
                    Pengguna.class)
                    .list();
        }
    }

    public List<Pengguna> tampilkanBerdasarkanRole(String role) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Pengguna WHERE role = :role ORDER BY idPengguna",
                    Pengguna.class)
                    .setParameter("role", role)
                    .list();
        }
    }

    public boolean hapus(int id) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Pengguna pengguna = session.get(Pengguna.class, id);

            if (pengguna == null) {
                transaction.rollback();
                return false;
            }

            session.remove(pengguna);
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