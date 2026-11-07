package util;

import dao.PesananDAO;
import dao.PengirimanDAO;
import java.time.LocalDateTime;
import java.util.*;
import model.Pesanan;
import model.Pengiriman;

public final class DataPusat {
    private DataPusat() {}
    public static String kunci(int pesanan, int pengguna) { return pesanan + ":" + pengguna; }
    public record Baris(Pesanan pesanan, Pengiriman pengiriman) {
        public LocalDateTime tanggalLaporan() {
            return pesanan != null && pesanan.getTanggalPesanan() != null
                    ? pesanan.getTanggalPesanan() : tanggalPengiriman(pengiriman, pesanan);
        }
    }
    public record Data(List<Pesanan> pesanan, List<Pengiriman> pengiriman, Map<String, Pesanan> indeks) {
        public List<Baris> baris(int bulan, int tahun) {
            Map<String, List<Pengiriman>> kiriman = new HashMap<>();
            for (Pengiriman p : pengiriman)
                kiriman.computeIfAbsent(kunci(p.getPesananIdPesanan(), p.getPesananPenggunaIdPengguna()), k -> new ArrayList<>()).add(p);
            List<Baris> hasil = new ArrayList<>();
            for (Pesanan p : pesanan) {
                List<Pengiriman> terkait = kiriman.remove(kunci(p.getIdPesanan(), p.getPenggunaIdPengguna()));
                if (terkait == null || terkait.isEmpty()) tambahkan(hasil, new Baris(p, null), bulan, tahun);
                else for (Pengiriman sh : terkait) tambahkan(hasil, new Baris(p, sh), bulan, tahun);
            }
            for (List<Pengiriman> sisa : kiriman.values())
                for (Pengiriman sh : sisa) tambahkan(hasil, new Baris(null, sh), bulan, tahun);
            return hasil;
        }
        public Pesanan pesanan(Pengiriman sh) {
            return indeks.get(kunci(sh.getPesananIdPesanan(), sh.getPesananPenggunaIdPengguna()));
        }
    }
    private static void tambahkan(List<Baris> hasil, Baris baris, int bulan, int tahun) {
        if (cocok(baris.tanggalLaporan(), bulan, tahun)) hasil.add(baris);
    }
    public static boolean cocok(LocalDateTime date, int bulan, int tahun) {
        if (bulan == 0 && tahun == 0) return true;
        return date != null && (bulan == 0 || date.getMonthValue() == bulan) && (tahun == 0 || date.getYear() == tahun);
    }
    public static LocalDateTime tanggalPengiriman(Pengiriman sh, Pesanan p) {
        if (sh != null) {
            if ("Sudah Sampai Tujuan".equals(sh.getStatusPengiriman()) && sh.getTanggalDiterima() != null) return sh.getTanggalDiterima();
            if (sh.getTanggalPengiriman() != null) return sh.getTanggalPengiriman();
        }
        if (p != null && p.getTanggalPesanan() != null) return p.getTanggalPesanan();
        return sh == null ? null : sh.getTanggalDiterima();
    }
    public static Data muat() {
        PusatOperasional.periksa();
        List<Pesanan> pesanan;
        List<Pengiriman> pengiriman;
        try (org.hibernate.Session session = config.HibernateUtil.getSessionFactory().openSession()) {
            session.setDefaultReadOnly(true);
            org.hibernate.Transaction tx = session.beginTransaction();
            try {
                pesanan = session.createQuery("FROM Pesanan ORDER BY idPesanan", Pesanan.class).list();
                pengiriman = session.createQuery("FROM Pengiriman ORDER BY nomorResi", Pengiriman.class).list();
                tx.commit();
            } catch (RuntimeException ex) {
                if (tx.isActive()) tx.rollback();
                throw ex;
            }
        }
        Map<String, Pesanan> indeks = new HashMap<>();
        for (Pesanan p : pesanan) indeks.put(kunci(p.getIdPesanan(), p.getPenggunaIdPengguna()), p);
        return new Data(pesanan, pengiriman, indeks);
    }
}
