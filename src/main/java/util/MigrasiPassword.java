package util;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public final class MigrasiPassword {
    private MigrasiPassword() {}

    public static int jalankan(SessionFactory factory) {
        List<Object[]> perubahan = new ArrayList<>();
        try (Session session = factory.openSession()) {
            for (Object[] row : session.createQuery(
                    "SELECT p.idPengguna, p.password FROM Pengguna p ORDER BY p.idPengguna", Object[].class).list()) {
                String lama = (String) row[1];
                if (lama == null) throw new IllegalStateException("Password akun tidak boleh kosong.");
                if (!PasswordHash.terhash(lama)) perubahan.add(new Object[]{row[0], lama, PasswordHash.hash(lama)});
            }
            if (perubahan.isEmpty()) return 0;
            Transaction tx = session.beginTransaction();
            try {
                int total = 0;
                for (Object[] row : perubahan) {
                    total += session.createMutationQuery(
                            "UPDATE Pengguna SET password = :baru WHERE idPengguna = :id AND password = :lama")
                            .setParameter("baru", row[2]).setParameter("id", row[0])
                            .setParameter("lama", row[1]).executeUpdate();
                }
                tx.commit();
                return total;
            } catch (RuntimeException ex) {
                if (tx.isActive()) tx.rollback();
                throw new IllegalStateException("Pengamanan password belum berhasil. Periksa koneksi database.", ex);
            }
        }
    }
}
