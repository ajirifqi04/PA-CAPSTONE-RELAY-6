package util;

import config.HibernateUtil;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import model.TarifWilayah;
import org.hibernate.Session;

public final class TarifPengiriman {
    private TarifPengiriman() {}

    public record Hasil(int idReferensi, BigDecimal biayaPerKg, boolean tarifUmum) {
        public String keterangan() {
            return tarifUmum ? " (tarif umum layanan)" : "";
        }
    }

    public static Hasil cari(String asal, String tujuan, int layanan) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return cari(session, asal, tujuan, layanan);
        }
    }

    public static Hasil cari(Session session, String asal, String tujuan, int layanan) {
        List<TarifWilayah> tarif = session.createQuery(
                "FROM TarifWilayah WHERE layananIdLayanan = :layanan ORDER BY idTarif",
                TarifWilayah.class).setParameter("layanan", layanan).list();
        TarifWilayah pilihan = tarif.stream().filter(t ->
                t.getKotaAsal().strip().equalsIgnoreCase(asal.strip()) &&
                t.getKotaTujuan().strip().equalsIgnoreCase(tujuan.strip()))
                .findFirst().orElse(null);
        boolean umum = pilihan == null;
        if (umum) {
            pilihan = tarif.stream().filter(t -> t.getBiayaPerKg().signum() >= 0)
                    .min(Comparator.comparing(TarifWilayah::getBiayaPerKg)
                            .thenComparingInt(TarifWilayah::getIdTarif))
                    .orElseThrow(() -> new IllegalArgumentException("Tarif layanan belum tersedia."));
        }
        return new Hasil(pilihan.getIdTarif(), pilihan.getBiayaPerKg(), umum);
    }
}
