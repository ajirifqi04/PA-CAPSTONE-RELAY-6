package util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Pesanan;
import model.Pengiriman;

public final class AgenOperasional {
    private static final controller.AgenController controller = new controller.AgenController();
    private AgenOperasional() {}

    private static int lokasiLogin() {
        model.Pengguna session = config.SesiPengguna.get();
        if(session == null || !"Agen".equals(session.getRole()) || session.getLokasiOperasionalIdLokasi() == null)
            throw new SecurityException("Silakan login menggunakan akun Agen.");
        return session.getLokasiOperasionalIdLokasi();
    }

    private static DefaultTableModel table(String... columns) {
        return new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
    }

    public static void tampilPesanan(JTable table) {
        int lokasi = lokasiLogin();
        int pilihId = -1;
        if (table.getSelectedRow() >= 0)
            pilihId = ((Number) table.getValueAt(table.getSelectedRow(), 0)).intValue();
        DefaultTableModel m = table("ID Pesanan", "ID Pelanggan", "Nama Pengirim",
            "No. Tel. Pengirim", "Agen Pengirim", "Nama Penerima",
            "No. Tel. Penerima", "Agen Penerima", "Kota Tujuan",
            "Jenis Kiriman", "Berat Barang", "Total Biaya",
            "Tanggal Pesanan", "Status Pesanan", "Status Pengiriman");

        dao.AgenPilihanDAO agenDao = new dao.AgenPilihanDAO();
        java.util.Map<Integer, String> namaAgen = new java.util.HashMap<>();
        java.util.Map<String, Pengiriman> pengiriman = new java.util.HashMap<>();
        for (Pengiriman sh : new dao.PengirimanDAO().tampilkanSemua()) {
            pengiriman.put(sh.getPesananIdPesanan() + ":" + sh.getPesananPenggunaIdPengguna(), sh);
        }
        for (Pesanan p : controller.tampilkanPesanan()) {
            Pengiriman sh = pengiriman.get(p.getIdPesanan() + ":" + p.getPenggunaIdPengguna());
            Integer asalId = util.RutePesanan.agenAsal(p,sh);
            
            if (asalId == null && sh != null) asalId = sh.getAgenIdLokasi();
            Integer tujuanId = util.RutePesanan.agenTujuan(p);
            String agenAsal = namaAgen(agenDao, namaAgen, asalId);
            String agenTujuan = namaAgen(agenDao, namaAgen, tujuanId);
            if (asalId == null) agenAsal = "Belum ditentukan";
            if (tujuanId == null) agenTujuan = "Belum ditentukan";
            m.addRow(new Object[]{p.getIdPesanan(), p.getPenggunaIdPengguna(), p.getNamaPengirim(),
                p.getNoTeleponPengirim(), agenAsal, p.getNamaPenerima(),
                p.getTeleponPenerima(), agenTujuan, p.getKotaTujuan(),
                p.getJenisKiriman(), p.getBeratBarang() == null ? "-" : p.getBeratBarang(),
                p.getTotalBiaya() == null ? "-" : p.getTotalBiaya(),
                util.TanggalRelay.tampil(p.getTanggalPesanan()), p.getStatusPesanan(),
                sh == null ? "Belum dijadwalkan" : sh.getStatusPengiriman()});
        }
        table.setModel(m);
        util.TabelRelay.pasang(table);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        util.TabelRelay.aturLebar(table);
        for (int i = 0; i < table.getRowCount(); i++)
            if (((Number) table.getValueAt(i, 0)).intValue() == pilihId) table.setRowSelectionInterval(i, i);
    }

    private static String namaAgen(dao.AgenPilihanDAO agenDao,
            java.util.Map<Integer, String> cache, Integer id) {
        if (id == null) return "Belum ditentukan";
        return cache.computeIfAbsent(id, key -> {
            model.Agen a = agenDao.byId(key);
            return a == null ? "Agen tidak ditemukan (#" + key + ")" : a.getNamaLokasi();
        });
    }

    public static void cari(JTable table, String kata) {
        javax.swing.table.TableRowSorter<DefaultTableModel> sorter = new javax.swing.table.TableRowSorter<>((DefaultTableModel)table.getModel());
        table.setRowSorter(sorter);
        if(!kata.trim().isEmpty()) sorter.setRowFilter(RowFilter.regexFilter("(?i)"+java.util.regex.Pattern.quote(kata.trim())));
    }
    private static Pesanan pilihan(JTable table) {
        int row = table.getSelectedRow();
        if(row < 0) throw new IllegalArgumentException("Pilih satu baris pesanan terlebih dahulu.");
        int index = table.convertRowIndexToModel(row);
        int id = ((Number)table.getModel().getValueAt(index,0)).intValue();
        int pengguna = ((Number)table.getModel().getValueAt(index,1)).intValue();
        Pesanan p = controller.cariPesanan(id,pengguna);
        if(p == null || !controller.bolehKelolaPesanan(p)) throw new SecurityException("Pesanan tidak ditemukan atau bukan milik agen ini.");
        return p;
    }
    public static void detail(JFrame frame, JTable table) {
        try {
            Pesanan p = pilihan(table);
            dao.AgenPilihanDAO lookup = new dao.AgenPilihanDAO();
            Integer asal = util.RutePesanan.agenAsal(p,null);
            Integer tujuan = util.RutePesanan.agenTujuan(p);
            String asalNama = namaAgen(lookup, new java.util.HashMap<>(), asal);
            String tujuanNama = namaAgen(lookup, new java.util.HashMap<>(), tujuan);
            if (asal == null) {
                for (Pengiriman sh : controller.tampilkanPengiriman()) {
                    if (sh.getPesananIdPesanan() == p.getIdPesanan()
                            && sh.getPesananPenggunaIdPengguna() == p.getPenggunaIdPengguna()) {
                        asalNama = namaAgen(lookup, new java.util.HashMap<>(), sh.getAgenIdLokasi());
                        break;
                    }
                }
            }
            String pesan = "ID Pesanan: " + p.getIdPesanan()
                    + "\nID Pelanggan: " + p.getPenggunaIdPengguna()
                    + "\nNama Pengirim: " + p.getNamaPengirim()
                    + "\nTelepon Pengirim: " + p.getNoTeleponPengirim()
                    + "\nAgen Pengirim: " + asalNama
                    + "\nNama Penerima: " + p.getNamaPenerima()
                    + "\nTelepon Penerima: " + p.getTeleponPenerima()
                    + "\nAgen Penerima: " + tujuanNama
                    + "\nKota Tujuan: " + p.getKotaTujuan()
                    + "\nJenis Kiriman: " + p.getJenisKiriman()
                    + "\nBerat Barang (kg): " + (p.getBeratBarang() == null ? "Belum ditimbang" : p.getBeratBarang())
                    + "\nTanggal: " + util.TanggalRelay.tampil(p.getTanggalPesanan())
                    + "\nStatus Pesanan: " + p.getStatusPesanan();
            JTextArea area = new JTextArea(pesan, 15, 42);
            area.setEditable(false);
            area.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
            area.setCaretPosition(0);
            Pengiriman kiriman = controller.tampilkanPengiriman().stream().filter(sh ->
                    sh.getPesananIdPesanan() == p.getIdPesanan() && sh.getPesananPenggunaIdPengguna() == p.getPenggunaIdPengguna()).findFirst().orElse(null);
            if (kiriman == null) JOptionPane.showMessageDialog(frame, new JScrollPane(area), "Detail Pesanan", JOptionPane.INFORMATION_MESSAGE);
            else if (JOptionPane.showOptionDialog(frame, new JScrollPane(area), "Detail Pesanan", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Lihat Foto Penerimaan", "Tutup"}, "Lihat Foto Penerimaan") == 0) {
                view.agen.BuktiPengirimanView bukti = new view.agen.BuktiPengirimanView(kiriman.getNomorResi(), () -> tampilPesanan(table));
                bukti.setLocationRelativeTo(frame);
                bukti.setVisible(true);
            }
        } catch (RuntimeException e) { info(frame, e.getMessage()); }
    }

    public static void tambah(JFrame frame, JTable table, Runnable refresh) {
        try {
            Pesanan p = pilihan(table);
            if(!controller.belumAdaResi(p)) throw new IllegalArgumentException("Pesanan sudah memiliki resi atau tidak boleh diverifikasi.");
            boolean gram = !"Paket".equals(p.getJenisKiriman());
            String str = JOptionPane.showInputDialog(frame, "Berat aktual (" + (gram?"gram":"kg") + "):", "Tambah Data Pengiriman", JOptionPane.QUESTION_MESSAGE);
            if(str==null)return;
            BigDecimal input;
            try { input=new BigDecimal(str.trim().replace(',','.')); }
            catch(NumberFormatException e) { throw new IllegalArgumentException("Berat harus angka positif, misalnya 250 atau 1,5."); }
            if(input.signum()<=0 || input.compareTo(gram?new BigDecimal("20000"):new BigDecimal("30"))>0)
                throw new IllegalArgumentException("Berat tidak dalam rentang yang diperbolehkan.");
            BigDecimal kg = gram ? input.divide(new BigDecimal("1000"),4,RoundingMode.HALF_UP) : input;
            if(JOptionPane.showConfirmDialog(frame,"Periksa data pesanan #"+p.getIdPesanan()+
                    "\nJenis: "+p.getJenisKiriman()+"\nBerat: "+input+" "+(gram?"gram":"kg"),
                    "Periksa data (1/2)",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
            if(JOptionPane.showConfirmDialog(frame,"Barang sudah diterima dan ditimbang di agen asal?\nBuat resi pengiriman sekarang?",
                    "Konfirmasi tambah (2/2)",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
            String resi="RLY"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))+"-"+p.getIdPesanan();
            controller.prosesPesanan(p.getIdPesanan(),p.getPenggunaIdPengguna(),resi,kg,lokasiLogin());
            info(frame,"Data pengiriman berhasil ditambahkan. Nomor resi: "+resi);
            refresh.run();
        } catch(RuntimeException e) { info(frame,"Tambah gagal: "+e.getMessage()); }
    }
    public static void ubah(JFrame frame, JTable table, Runnable refresh) {
        try {
            Pesanan p = pilihan(table);
            if (PenerimaanAgen.tujuan(p) && !controller.belumAdaResi(p)) {
                if ("Selesai".equals(p.getStatusPesanan())) { info(frame, "Pesanan sudah selesai. Dokumentasi dapat dilihat melalui Lihat Detail."); return; }
                if (JOptionPane.showConfirmDialog(frame, "Pesanan #" + p.getIdPesanan() + " sudah diterima di agen tujuan dan siap diselesaikan?\nUbah status pesanan menjadi Selesai?", "Konfirmasi Selesai", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
                PenerimaanAgen.selesaikan(p.getIdPesanan(), p.getPenggunaIdPengguna());
                refresh.run();
                info(frame, "Status pesanan berhasil diubah menjadi Selesai.");
                return;
            }
            if(!controller.belumAdaResi(p)) throw new IllegalArgumentException("Pesanan sudah memiliki resi atau tidak boleh diubah.");
            String nama=JOptionPane.showInputDialog(frame,"Nama penerima:",p.getNamaPenerima());
            if(nama==null)return;
            nama=util.Validasi.nama(nama,"Nama penerima");
            if(JOptionPane.showConfirmDialog(frame,"Simpan perubahan nama penerima?","Konfirmasi",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
            p.setNamaPenerima(nama);
            if(!controller.ubahPesanan(p)) throw new IllegalStateException("Perubahan gagal disimpan.");
            refresh.run();
        } catch(RuntimeException e) { info(frame,"Ubah gagal: "+e.getMessage()); }
    }
    public static void hapus(JFrame frame, JTable table, Runnable refresh) {
        try {
            Pesanan p=pilihan(table);
            if(!controller.belumAdaResi(p))throw new IllegalArgumentException("Pesanan sudah memiliki resi atau tidak boleh dihapus.");
            if(JOptionPane.showConfirmDialog(frame,"Hapus pesanan #"+p.getIdPesanan()+"?","Konfirmasi",JOptionPane.YES_NO_OPTION)!=JOptionPane.YES_OPTION)return;
            if(!"HAPUS".equals(JOptionPane.showInputDialog(frame,"Ketik HAPUS untuk melanjutkan:")))return;
            if(!controller.hapusPesanan(p.getIdPesanan(),p.getPenggunaIdPengguna())) throw new IllegalStateException("Data terhubung dengan pengiriman atau tidak dapat dihapus.");
            refresh.run();
        } catch(RuntimeException e) { info(frame,"Hapus gagal: "+e.getMessage()); }
    }
    public static void tampilMasuk(JTable table) {
        int agenId = lokasiLogin();
        DefaultTableModel model=table("Nomor Resi","ID Pesanan","Pengirim","Cabang Pengirim","Tanggal","Status");
        for(Pengiriman sh: controller.tampilkanPengiriman()) {
            Pesanan p=controller.cariPesanan(sh.getPesananIdPesanan(),sh.getPesananPenggunaIdPengguna());
            if(p==null || !java.util.Objects.equals(util.RutePesanan.agenTujuan(p),agenId)) continue;
            model.Agen agenAsal = new dao.AgenPilihanDAO().byId(sh.getAgenIdLokasi());
            model.LokasiOperasional cabangAsal = agenAsal == null ? null :
                new dao.LokasiDAO().cariBerdasarkanId(agenAsal.getCabangIdLokasi());
            String asal = cabangAsal == null ? "Cabang belum diketahui" : cabangAsal.getNamaLokasi();
            model.addRow(new Object[]{sh.getNomorResi(), p.getIdPesanan(), p.getNamaPengirim(), asal, util.TanggalRelay.tampil(sh.getTanggalPengiriman()),sh.getStatusPengiriman()});
        }
        table.setModel(model);
        util.TabelRelay.pasang(table);
        util.TabelRelay.aturLebar(table);
    }
    
    public static void terima(JFrame frame, JTable table, Runnable refresh) {
        try {
            int row = table.getSelectedRow();
            if (row < 0) throw new IllegalArgumentException("Pilih satu paket yang akan diubah statusnya.");
            String resi = table.getModel().getValueAt(table.convertRowIndexToModel(row), 0).toString();
            Pengiriman sh = PenerimaanAgen.lihat(resi);
            if (!PenerimaanAgen.bolehTerima(sh))
                throw new IllegalArgumentException("Paket belum dikirim cabang menuju agen tujuan atau bukan untuk agen ini.");
            if (!PenerimaanAgen.diterima(sh)) {
                if (JOptionPane.showConfirmDialog(frame, "Paket " + resi + " sudah diterima di agen tujuan?\nUbah status menjadi Sudah Sampai Tujuan?", "Konfirmasi Penerimaan", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) return;
                PenerimaanAgen.terima(resi);
                refresh.run();
            }
            view.agen.BuktiPengirimanView bukti = new view.agen.BuktiPengirimanView(resi, refresh);
            bukti.setLocationRelativeTo(frame);
            bukti.setVisible(true);
        } catch (RuntimeException e) { info(frame, "Ubah status gagal: " + e.getMessage()); }
    }
    public static void detailMasuk(JFrame frame,JTable table) {
        try {
            int row=table.getSelectedRow();
            if(row<0)throw new IllegalArgumentException("Pilih paket terlebih dahulu.");
            String resi=table.getModel().getValueAt(table.convertRowIndexToModel(row),0).toString();
            view.agen.BuktiPengirimanView bukti = new view.agen.BuktiPengirimanView(resi, () -> tampilMasuk(table));
            bukti.setLocationRelativeTo(frame);
            bukti.setVisible(true);
        } catch(RuntimeException e) {info(frame,e.getMessage());}
    }
    public static void info(JFrame frame,String msg) { JOptionPane.showMessageDialog(frame,msg); }
}
