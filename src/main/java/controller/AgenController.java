package controller;

import dao.PesananDAO;
import dao.PengirimanDAO;
import dao.PembayaranDAO;
import model.Pesanan;
import model.Pengiriman;
import model.Pembayaran;
import java.util.List;

public class AgenController {

    private final PesananDAO pesananDAO;
    private final PengirimanDAO pengirimanDAO;
    private final PembayaranDAO pembayaranDAO;

    public AgenController() {
        pesananDAO = new PesananDAO();
        pengirimanDAO = new PengirimanDAO();
        pembayaranDAO = new PembayaranDAO();
    }

    public List<Pesanan> tampilkanPesanan() {
        if(config.SesiPengguna.get()==null || !"Agen".equals(config.SesiPengguna.get().getRole()))
            return java.util.Collections.emptyList();
        Integer lokasi=config.SesiPengguna.get().getLokasiOperasionalIdLokasi();
        if(lokasi==null) return java.util.Collections.emptyList();

        java.util.Map<String, Pengiriman> pengiriman = new java.util.HashMap<>();
        for (Pengiriman sh : pengirimanDAO.tampilkanSemua())
            pengiriman.put(sh.getPesananIdPesanan() + ":" + sh.getPesananPenggunaIdPengguna(), sh);
        return pesananDAO.tampilkanSemua().stream().filter(p -> {
            Pengiriman sh = pengiriman.get(p.getIdPesanan() + ":" + p.getPenggunaIdPengguna());
            return java.util.Objects.equals(util.RutePesanan.agenAsal(p, sh), lokasi) ||
                    (java.util.Objects.equals(util.RutePesanan.agenTujuan(p), lokasi) && util.PenerimaanAgen.diterima(sh));
        }).toList();
    }

    public List<Pengiriman> tampilkanPengiriman() {
        if(config.SesiPengguna.get()==null || !"Agen".equals(config.SesiPengguna.get().getRole()))
            return java.util.Collections.emptyList();
        Integer lokasi=config.SesiPengguna.get().getLokasiOperasionalIdLokasi();
        if(lokasi==null) return java.util.Collections.emptyList();
        return pengirimanDAO.tampilkanSemua().stream().filter(sh -> {
            model.Pesanan p=pesananDAO.cariBerdasarkanId(sh.getPesananIdPesanan(),sh.getPesananPenggunaIdPengguna());
            return p != null && (java.util.Objects.equals(util.RutePesanan.agenAsal(p,sh),lokasi) ||
                    java.util.Objects.equals(util.RutePesanan.agenTujuan(p),lokasi));
        }).toList();
    }

    public List<Pembayaran> tampilkanPembayaran() {
        return pembayaranDAO.tampilkanSemua();
    }

    public Pesanan cariPesanan(int idPesanan, int idPengguna) {
        return pesananDAO.cariBerdasarkanId(idPesanan, idPengguna);
    }

    public Pengiriman cariPengiriman(String nomorResi) {
        return pengirimanDAO.cariBerdasarkanResi(nomorResi);
    }

    public boolean tambahPesanan(Pesanan pesanan) {
        
        return false;
    }

    public boolean ubahPesanan(Pesanan pesanan) {
        if(!izinAgenAsal(pesanan) || !belumAdaResi(pesanan)) return false;
        return pesananDAO.ubah(pesanan);
    }
    public boolean belumAdaResi(Pesanan p) {
        if(p == null || !izinAgenAsal(p) ||
           !("Menunggu".equals(p.getStatusPesanan()) || "Diproses".equals(p.getStatusPesanan()))) return false;
        return pengirimanDAO.tampilkanSemua().stream().noneMatch(sh ->
            sh.getPesananIdPesanan() == p.getIdPesanan() &&
            sh.getPesananPenggunaIdPengguna() == p.getPenggunaIdPengguna());
    }

    public boolean hapusPesanan(int idPesanan, int idPengguna) {
        model.Pesanan p = pesananDAO.cariBerdasarkanId(idPesanan,idPengguna);
        if(p==null || !izinAgenAsal(p) || !belumAdaResi(p)) return false;
        return pesananDAO.hapus(idPesanan, idPengguna);
    }

    public boolean tambahPengiriman(Pengiriman pengiriman) {
        return pengirimanDAO.tambah(pengiriman);
    }

    public boolean ubahPengiriman(Pengiriman pengiriman) {
        return pengirimanDAO.ubah(pengiriman);
    }

    public boolean tambahPembayaran(Pembayaran pembayaran) {
        return pembayaranDAO.tambah(pembayaran);
    }

    public boolean ubahPembayaran(Pembayaran pembayaran) {
        return pembayaranDAO.ubah(pembayaran);
    }

    public boolean bolehKelolaPesanan(model.Pesanan p) {
        if (izinAgenAsal(p)) return true;
        if (p == null || config.SesiPengguna.get() == null || !"Agen".equals(config.SesiPengguna.get().getRole())) return false;
        Integer lokasi = config.SesiPengguna.get().getLokasiOperasionalIdLokasi();
        if (lokasi == null) return false;
        return java.util.Objects.equals(lokasi, util.RutePesanan.agenTujuan(p)) &&
            pengirimanDAO.tampilkanSemua().stream().anyMatch(sh ->
                sh.getPesananIdPesanan() == p.getIdPesanan() &&
                sh.getPesananPenggunaIdPengguna() == p.getPenggunaIdPengguna() && util.PenerimaanAgen.diterima(sh));
    }

    private boolean izinAgenAsal(model.Pesanan p) {
        if (p == null || config.SesiPengguna.get() == null || !"Agen".equals(config.SesiPengguna.get().getRole())) return false;
        Integer lokasi = config.SesiPengguna.get().getLokasiOperasionalIdLokasi();
        if (lokasi == null) return false;
        Integer asal = util.RutePesanan.agenAsal(p, null);
        if (asal != null) return java.util.Objects.equals(lokasi, asal);
        return pengirimanDAO.tampilkanSemua().stream().anyMatch(sh ->
            java.util.Objects.equals(lokasi, sh.getAgenIdLokasi()) &&
            sh.getPesananIdPesanan() == p.getIdPesanan() &&
            sh.getPesananPenggunaIdPengguna() == p.getPenggunaIdPengguna());
    }

    public java.math.BigDecimal prosesPesanan(int idPesanan, int idPelanggan,
            String resi, java.math.BigDecimal berat, int agenId) {
        if (idPesanan <= 0 || idPelanggan <= 0 || agenId <= 0
                || resi == null || !resi.matches("[A-Za-z0-9-]{5,30}")
                || berat == null || berat.signum() <= 0) {
            throw new IllegalArgumentException("Periksa nomor pesanan, resi, dan berat barang.");
        }
        org.hibernate.Transaction tr = null;
        try (org.hibernate.Session session = config.HibernateUtil.getSessionFactory().openSession()) {
            tr = session.beginTransaction();
            try {
            model.Agen agen = session.get(model.Agen.class, agenId);
            if (agen == null) throw new IllegalArgumentException("Akun agen belum terhubung dengan lokasi yang benar.");
            model.Pesanan item = session.get(model.Pesanan.class,
                    new model.PesananId(idPesanan, idPelanggan), org.hibernate.LockMode.PESSIMISTIC_WRITE);
            if (item == null) throw new IllegalArgumentException("Pesanan tidak ditemukan.");
            if (!("Diproses".equalsIgnoreCase(item.getStatusPesanan())
                    || "Menunggu".equalsIgnoreCase(item.getStatusPesanan())))
                throw new IllegalArgumentException("Pesanan batal atau sudah selesai.");
            Long pengirimanLama = session.createQuery(
                    "SELECT COUNT(p) FROM Pengiriman p WHERE p.pesananIdPesanan = :id AND p.pesananPenggunaIdPengguna = :pengguna",
                    Long.class).setParameter("id", idPesanan)
                    .setParameter("pengguna", idPelanggan).uniqueResult();
            if (pengirimanLama != null && pengirimanLama > 0)
                throw new IllegalArgumentException("Pesanan sudah mempunyai pengiriman.");
            if (session.get(model.Pengiriman.class, resi) != null)
                throw new IllegalArgumentException("Nomor resi sudah digunakan.");
            Integer asalDitunjuk = util.RutePesanan.agenAsal(item,null);
            if (asalDitunjuk != null && asalDitunjuk != agenId)
                throw new IllegalArgumentException("Pesanan ini ditujukan ke agen pengirim lain.");
            Integer tujuanDitunjuk = util.RutePesanan.agenTujuan(item);
            if (tujuanDitunjuk == null)
                throw new IllegalArgumentException("Pesanan lama belum memiliki agen tujuan. Konfirmasi dan lengkapi rute terlebih dahulu.");
            model.Agen tujuanAgen = session.get(model.Agen.class, tujuanDitunjuk);
            if (tujuanAgen == null || !"Aktif".equals(tujuanAgen.getStatusOperasional()))
                throw new IllegalArgumentException("Agen tujuan tidak aktif.");
            model.Layanan layanan = session.get(model.Layanan.class, item.getLayananIdLayanan());
            if (layanan == null || !"Aktif".equals(layanan.getStatusAktif())) throw new IllegalArgumentException("Layanan pesanan tidak aktif.");
            java.math.BigDecimal batasBerat = new java.math.BigDecimal("Paket".equals(item.getJenisKiriman()) ? "30" : "20");
            if (berat.compareTo(batasBerat) > 0) throw new IllegalArgumentException("Berat melampaui batas layanan.");
            berat = berat.setScale(2, java.math.RoundingMode.CEILING);
            util.TarifPengiriman.Hasil rute = util.TarifPengiriman.cari(
                    session, agen.getKota(), item.getKotaTujuan(), item.getLayananIdLayanan());
            java.math.BigDecimal jumlah = layanan.getTarifDasar().add(
                    rute.biayaPerKg().multiply(berat)).setScale(2, java.math.RoundingMode.HALF_UP);
            model.Pengiriman kiriman = new model.Pengiriman();
            kiriman.setNomorResi(resi);
            kiriman.setStatusPengiriman("Dijadwalkan");
            kiriman.setTanggalPengiriman(null); 
            kiriman.setTarifWilayahIdTarif(rute.idReferensi());
            kiriman.setAgenIdLokasi(agenId);
            kiriman.setCabangIdLokasi(tujuanAgen.getCabangIdLokasi());
            kiriman.setPesananIdPesanan(idPesanan);
            kiriman.setPesananPenggunaIdPengguna(idPelanggan);
            session.persist(kiriman);

            if (config.SesiPengguna.get() == null || !"Agen".equals(config.SesiPengguna.get().getRole()) ||
                    !java.util.Objects.equals(config.SesiPengguna.get().getLokasiOperasionalIdLokasi(), agenId))
                throw new SecurityException("Hanya agen asal yang login boleh memverifikasi barang.");
            item.setBeratBarang(berat);
            item.setTotalBiaya(jumlah);
            item.setStatusPesanan("Diproses");
            session.merge(item);
            tr.commit();
            return jumlah;
            } catch (RuntimeException ex) {
                if (tr.isActive()) tr.rollback();
                throw ex;
            }
        }
    }

}
