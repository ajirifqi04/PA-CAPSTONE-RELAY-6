package util;

import java.time.LocalDateTime;
import java.util.Objects;
import model.Pesanan;
import model.Pengiriman;
import org.hibernate.LockMode;
import org.hibernate.Session;

public final class PenerimaanAgen {
    private PenerimaanAgen() {}

    private static int lokasi() {
        model.Pengguna login = config.SesiPengguna.get();
        if (login == null || !"Agen".equals(login.getRole()) || login.getLokasiOperasionalIdLokasi() == null)
            throw new SecurityException("Silakan login menggunakan akun Agen.");
        return login.getLokasiOperasionalIdLokasi();
    }

    public static boolean tujuan(Pesanan p) {
        return p != null && Objects.equals(RutePesanan.agenTujuan(p), lokasi());
    }

    public static boolean diterima(Pengiriman sh) {
        return sh != null && "Sudah Sampai Tujuan".equalsIgnoreCase(sh.getStatusPengiriman());
    }

    public static Pengiriman lihat(String resi) {
        try (Session db = config.HibernateUtil.getSessionFactory().openSession()) {
            Pengiriman sh = db.get(Pengiriman.class, resi);
            if (sh == null) throw new IllegalArgumentException("Nomor resi tidak ditemukan.");
            Pesanan p = db.get(Pesanan.class, new model.PesananId(sh.getPesananIdPesanan(), sh.getPesananPenggunaIdPengguna()));
            model.Pengguna login = config.SesiPengguna.get();
            if (p == null || login == null) throw new SecurityException("Silakan login untuk melihat detail pengiriman.");
            boolean boleh;
            if ("Pelanggan".equals(login.getRole())) boleh = p.getPenggunaIdPengguna() == login.getIdPengguna();
            else if ("Agen".equals(login.getRole())) {
                Integer id = login.getLokasiOperasionalIdLokasi();
                boleh = id != null && (Objects.equals(RutePesanan.agenAsal(p, sh), id) || Objects.equals(RutePesanan.agenTujuan(p), id));
            } else boleh = "Pusat".equals(login.getRole());
            if (!boleh) throw new SecurityException("Pengiriman bukan milik akun yang sedang login.");
            return sh;
        }
    }

    public static boolean bolehTerima(Pengiriman sh) {
        Pesanan p = new dao.PesananDAO().cariBerdasarkanId(sh.getPesananIdPesanan(), sh.getPesananPenggunaIdPengguna());
        return tujuan(p) && !"Dibatalkan".equals(p.getStatusPesanan()) &&
                (diterima(sh) || ("Dalam Perjalanan".equals(sh.getStatusPengiriman()) && TahapPengiriman.menujuAgen(sh)));
    }

    public static void terima(String resi) {
        lokasi();
        try (Session db = config.HibernateUtil.getSessionFactory().openSession()) {
            var tx = db.beginTransaction();
            try {
                Pengiriman awal = db.get(Pengiriman.class, resi);
                if (awal == null) throw new IllegalArgumentException("Nomor resi tidak ditemukan.");
                Pesanan p = db.get(Pesanan.class, new model.PesananId(awal.getPesananIdPesanan(), awal.getPesananPenggunaIdPengguna()), LockMode.PESSIMISTIC_WRITE);
                Pengiriman sh = db.get(Pengiriman.class, resi, LockMode.PESSIMISTIC_WRITE);
                db.refresh(sh, LockMode.PESSIMISTIC_WRITE);
                if (!tujuan(p)) throw new SecurityException("Hanya agen tujuan yang boleh mengonfirmasi penerimaan.");
                if ("Dibatalkan".equals(p.getStatusPesanan())) throw new IllegalArgumentException("Pesanan dibatalkan.");
                boolean sudah = diterima(sh);
                if (!sudah && !("Dalam Perjalanan".equals(sh.getStatusPengiriman()) && TahapPengiriman.menujuAgen(sh)))
                    throw new IllegalStateException("Cabang belum mengirim paket menuju agen tujuan.");
                if (!sudah) {
                    LocalDateTime sekarang = LocalDateTime.now();
                    if (sh.getTanggalPengiriman() != null && sh.getTanggalPengiriman().isAfter(sekarang))
                        throw new IllegalStateException("Tanggal pengiriman masih di masa depan. Periksa jadwal pengiriman.");
                    sh.setBuktiDokumentasi(TahapPengiriman.denganFoto(sh, BuktiFoto.referensi(sh)));
                    sh.setStatusPengiriman("Sudah Sampai Tujuan");
                    sh.setTanggalDiterima(sekarang);
                }
                tx.commit();
            } catch (RuntimeException ex) { if (tx.isActive()) tx.rollback(); throw ex; }
        }
    }

    public static void selesaikan(int id, int pengguna) {
        lokasi();
        try (Session db = config.HibernateUtil.getSessionFactory().openSession()) {
            var tx = db.beginTransaction();
            try {
                Pesanan p = db.get(Pesanan.class, new model.PesananId(id, pengguna), LockMode.PESSIMISTIC_WRITE);
                if (!tujuan(p)) throw new SecurityException("Hanya agen tujuan yang boleh menyelesaikan pesanan.");
                if ("Dibatalkan".equals(p.getStatusPesanan())) throw new IllegalArgumentException("Pesanan dibatalkan.");
                var daftar = db.createQuery("FROM Pengiriman WHERE pesananIdPesanan = :id AND pesananPenggunaIdPengguna = :user", Pengiriman.class)
                    .setParameter("id", id).setParameter("user", pengguna).setLockMode(jakarta.persistence.LockModeType.PESSIMISTIC_WRITE).getResultList();
                if (daftar.isEmpty() || daftar.stream().anyMatch(sh -> !diterima(sh)))
                    throw new IllegalStateException("Pesanan belum diterima di agen tujuan.");
                p.setStatusPesanan("Selesai");
                tx.commit();
            } catch (RuntimeException ex) { if (tx.isActive()) tx.rollback(); throw ex; }
        }
    }
}
