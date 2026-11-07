package controller;

import dao.PenggunaDAO;
import dao.PesananDAO;
import dao.PengirimanDAO;
import model.Pengguna;
import model.Pesanan;
import model.Pengiriman;
import java.util.ArrayList;
import java.util.List;

public class PelangganController {

    private final PenggunaDAO penggunaDAO;
    private final PesananDAO pesananDAO;
    private final PengirimanDAO pengirimanDAO;

    public PelangganController() {
        penggunaDAO = new PenggunaDAO();
        pesananDAO = new PesananDAO();
        pengirimanDAO = new PengirimanDAO();
    }

    public boolean buatPesanan(Pesanan pesanan) {
        if (config.SesiPengguna.get()==null || !"Pelanggan".equals(config.SesiPengguna.get().getRole()) ||
                pesanan.getPenggunaIdPengguna()!=config.SesiPengguna.get().getIdPengguna())
            throw new SecurityException("Tidak dapat membuat pesanan atas nama pelanggan lain.");
        Integer asalId = util.RutePesanan.baca(pesanan.getAlamatPengirim());
        Integer tujuanId = util.RutePesanan.baca(pesanan.getAlamatTujuan());
        if (asalId == null || tujuanId == null || asalId.equals(tujuanId))
            throw new IllegalArgumentException("Pilih agen pengirim dan penerima yang berbeda.");
        if (pesanan.getIdPesanan() != 0) throw new IllegalArgumentException("ID pesanan dibuat otomatis oleh program.");
        pesanan.setNamaPengirim(util.Validasi.nama(config.SesiPengguna.get().getNamaPengguna(), "Nama pengirim"));
        pesanan.setNamaPenerima(util.Validasi.nama(pesanan.getNamaPenerima(), "Nama penerima"));
        pesanan.setNoTeleponPengirim(util.Validasi.telepon(pesanan.getNoTeleponPengirim(), "Telepon pengirim"));
        pesanan.setTeleponPenerima(util.Validasi.telepon(pesanan.getTeleponPenerima(), "Telepon penerima"));
        if (!java.util.Set.of("Surat", "Dokumen", "Paket").contains(pesanan.getJenisKiriman() == null ? "" : pesanan.getJenisKiriman()))
            throw new IllegalArgumentException("Jenis kiriman wajib Surat, Dokumen, atau Paket.");
        try (org.hibernate.Session db = config.HibernateUtil.getSessionFactory().openSession()) {
            for (int id : new int[]{asalId, tujuanId}) {
                model.Agen agen = db.get(model.Agen.class, id);
                model.Cabang cabang = agen == null ? null : db.get(model.Cabang.class, agen.getCabangIdLokasi());
                if (agen == null || !"Aktif".equals(agen.getStatusOperasional()) || cabang == null || !"Aktif".equals(cabang.getStatusOperasional()))
                    throw new IllegalArgumentException("Agen pengirim/penerima dan cabang induk harus aktif.");
                if (id == tujuanId && !agen.getKota().equalsIgnoreCase(pesanan.getKotaTujuan()))
                    throw new IllegalArgumentException("Kota tujuan harus sesuai agen penerima.");
            }
            model.Layanan layanan = db.get(model.Layanan.class, pesanan.getLayananIdLayanan());
            if (layanan == null || !"Aktif".equals(layanan.getStatusAktif()))
                throw new IllegalArgumentException("Pilih layanan pengiriman yang aktif.");
        }
        pesanan.setTanggalPesanan(java.time.LocalDateTime.now());
        pesanan.setStatusPesanan("Diproses");
        pesanan.setBeratBarang(null);
        pesanan.setTotalBiaya(null);
        pesanan.setEmailPengirim(pesanan.getEmailPengirim() == null || pesanan.getEmailPengirim().isBlank()
                ? "" : util.Validasi.email(pesanan.getEmailPengirim()));
        return pesananDAO.tambah(pesanan);
    }

    public List<Pesanan> tampilkanRiwayatPesanan(int idPengguna) {
        izinBaca(idPengguna);
        return pesananDAO.tampilkanBerdasarkanPengguna(idPengguna);
    }

    public List<Pengiriman> tampilkanStatusPengiriman(int idPengguna) {
        izinBaca(idPengguna);
        List<Pengiriman> hasil = new ArrayList<>();
        List<Pesanan> pesananList =
                pesananDAO.tampilkanBerdasarkanPengguna(idPengguna);

        List<Pengiriman> semuaPengiriman =
                pengirimanDAO.tampilkanSemua();

        for (Pengiriman pengiriman : semuaPengiriman) {
            for (Pesanan pesanan : pesananList) {
                if (pengiriman.getPesananIdPesanan() == pesanan.getIdPesanan()
                        && pengiriman.getPesananPenggunaIdPengguna() == idPengguna) {
                    hasil.add(pengiriman);
                    break;
                }
            }
        }

        return hasil;
    }

    private void izinBaca(int idPengguna) {
        model.Pengguna login = config.SesiPengguna.get();
        if (login == null || !"Pelanggan".equals(login.getRole()) || login.getIdPengguna() != idPengguna)
            throw new SecurityException("Hanya pelanggan pemilik pesanan yang dapat melihat riwayatnya.");
    }

    public boolean ubahAkun(Pengguna pengguna) {
        return penggunaDAO.ubah(pengguna);
    }

    public Pengguna cariAkun(int idPengguna) {
        return penggunaDAO.cariBerdasarkanId(idPengguna);
    }
}