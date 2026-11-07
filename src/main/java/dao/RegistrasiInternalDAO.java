package dao;

import config.HibernateUtil;
import config.SesiPengguna;
import model.Agen;
import model.Cabang;
import model.LokasiOperasional;
import model.Pengguna;
import org.hibernate.Session;
import org.hibernate.Transaction;

public final class RegistrasiInternalDAO {
    public record RincianLokasi(String kota, String alamat, String kode, Integer cabangInduk) {}

    private static String key(String s) {
        return s == null ? "" : s.strip().toLowerCase(java.util.Locale.ROOT)
                .replaceFirst("^relay\\s+", "")
                .replaceFirst("^(agen|cabang)\\s+", "")
                .replaceAll("\\s+", " ");
    }

    private static void checkPusat() {
        Pengguna user = SesiPengguna.get();
        if (user == null || !"Pusat".equals(user.getRole()))
            throw new SecurityException("Hanya akun Pusat yang boleh mendaftarkan kantor baru.");
    }

    private static boolean duplicated(Session session, String name, String role) {
        for (LokasiOperasional lokasi : session.createQuery(
                "FROM LokasiOperasional", LokasiOperasional.class).list()) {
            if (role.equals(lokasi.getJenisLokasi()) && key(lokasi.getNamaLokasi()).equals(key(name))) return true;
        }
        return false;
    }

    private static boolean usernameUsed(Session session, String username) {
        Long count = session.createQuery(
            "SELECT COUNT(p.idPengguna) FROM Pengguna p WHERE lower(p.username) = :u", Long.class)
            .setParameter("u",username.toLowerCase(java.util.Locale.ROOT)).uniqueResult();
        return count != null && count > 0;
    }

    public void validasiBelumTerdaftar(String lokasi, String username, String role) {
        checkPusat();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            if (duplicated(session,lokasi,role))
                throw new IllegalArgumentException("Kantor/agen sudah ada di database. Masukkan lokasi operasional BARU.");
            if (usernameUsed(session,username))
                throw new IllegalArgumentException("Username sudah digunakan oleh akun lain.");
        }
    }

    public String kodeBerikutnya(String role, String kota) {
        checkPusat();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return util.KodeLokasi.berikutnya(session, role, kota);
        }
    }

    public String daftarkan(String nama, String username, String hashedPassword,
            String role, String lokasi, RincianLokasi detail) {
        checkPusat();
        nama = util.Validasi.nama(nama, "Nama lengkap");
        username = util.Validasi.username(username);
        if (!util.PasswordHash.terhash(hashedPassword)) throw new IllegalArgumentException("Password harus diproses oleh program.");
        if (!"Cabang".equals(role) && !"Agen".equals(role))
            throw new IllegalArgumentException("Role wajib Cabang atau Agen.");
        if (detail == null) throw new IllegalArgumentException("Detail lokasi belum lengkap.");
        String basic = util.NamaLokasi.namaDasar(lokasi);
        if (basic.length() < 3 || basic.length() > 82)
            throw new IllegalArgumentException("Nama lokasi tidak valid.");
        String fullName="Relay " + role + " " + basic;
        Transaction tx=null;
        try (Session session=HibernateUtil.getSessionFactory().openSession()) {
            try {
                tx=session.beginTransaction();
                session.createQuery("FROM Pengguna WHERE role = 'Pusat' ORDER BY idPengguna", Pengguna.class)
                        .setLockMode(jakarta.persistence.LockModeType.PESSIMISTIC_WRITE).list();
                java.util.List<LokasiOperasional> lokasiAda = session.createQuery("FROM LokasiOperasional", LokasiOperasional.class).list();
                String kota = util.NamaLokasi.kota(lokasi, lokasiAda);
                if (!kota.equalsIgnoreCase(detail.kota())) throw new IllegalArgumentException("Kota lokasi harus sesuai nama cabang/agen.");
                String alamat = util.Validasi.wajib(detail.alamat(), "Alamat kantor", 255);
                if (alamat.length() < 10) throw new IllegalArgumentException("Alamat kantor minimal 10 karakter.");
                if (duplicated(session,fullName,role))
                    throw new IllegalArgumentException("Nama agen atau cabang tersebut sudah terdaftar.");
                if (usernameUsed(session,username))
                    throw new IllegalArgumentException("Username sudah digunakan.");
                String code=util.KodeLokasi.berikutnya(session, role, kota);
                if ("Agen".equals(role)) {
                    Cabang cabangInduk=detail.cabangInduk()==null ? null :
                            session.get(Cabang.class,detail.cabangInduk());
                    if (cabangInduk==null)
                        throw new IllegalArgumentException("Cabang induk tidak ditemukan di database.");
                    if (!"Aktif".equals(cabangInduk.getStatusOperasional()))
                        throw new IllegalArgumentException("Cabang induk sedang tidak aktif.");
                    if (!cabangInduk.getKota().equalsIgnoreCase(detail.kota().strip()))
                        throw new IllegalArgumentException("Kota agen baru harus sama dengan kota Cabang Induk.");
                    Long used=session.createQuery("SELECT COUNT(a.idLokasi) FROM Agen a WHERE a.kodeAgen = :code",Long.class)
                            .setParameter("code",code).uniqueResult();
                    if(used!=null&&used>0) throw new IllegalArgumentException("Kode Agen sudah digunakan.");
                } else {
                    Long used=session.createQuery("SELECT COUNT(c.idLokasi) FROM Cabang c WHERE c.kodeCabang = :code",Long.class)
                            .setParameter("code",code).uniqueResult();
                    if(used!=null&&used>0) throw new IllegalArgumentException("Kode Cabang sudah digunakan.");
                }
                LokasiOperasional baru;
                if ("Cabang".equals(role)) {
                    Cabang c = new Cabang();
                    c.setNamaAdminCabang(nama);c.setKodeCabang(code);c.setStatusOperasional("Aktif");
                    baru=c;
                } else {
                    Agen a = new Agen();
                    a.setNamaAdminAgen(nama);a.setKodeAgen(code);a.setStatusOperasional("Aktif");
                    a.setCabangIdLokasi(detail.cabangInduk());
                    baru=a;
                }
                baru.setNamaLokasi(fullName);
                baru.setJenisLokasi(role);
                baru.setKota(detail.kota().strip());
                baru.setAlamat(detail.alamat().strip());
                session.persist(baru);
                session.flush();
                Pengguna akun=new Pengguna();
                akun.setNamaPengguna(nama);akun.setUsername(username);
                akun.setPassword(hashedPassword);akun.setRole(role);
                akun.setLokasiOperasionalIdLokasi(baru.getIdLokasi());
                session.persist(akun);
                tx.commit();
                return code;
            } catch(RuntimeException ex) {
                if(tx!=null&&tx.isActive())tx.rollback();
                throw ex;
            }
        } catch (IllegalArgumentException | SecurityException ex) {
            throw ex;
        } catch(RuntimeException ex) {
            throw new IllegalStateException("Pendaftaran gagal. Tidak ada data yang disimpan sebagian. "
                    + "Periksa koneksi, kode unik, dan data lokasi. Rincian: " + ex.getMessage(), ex);
        }
    }
}
