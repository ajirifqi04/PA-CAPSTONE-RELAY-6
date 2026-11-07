package util;

import javax.swing.*;
import model.Pesanan;
import model.Pengiriman;

public final class PelacakanPesanan {
    private PelacakanPesanan() {}

    public static String estimasi(Pengiriman sh) {
        var waktu = TahapPengiriman.estimasi(sh);
        return waktu == null ? "Belum ditentukan" : TanggalRelay.tampil(waktu);
    }

    public static String tiba(Pengiriman sh) {
        if (sh != null && sh.getTanggalDiterima() != null) return TanggalRelay.tampil(sh.getTanggalDiterima());
        return PenerimaanAgen.diterima(sh) ? "Belum tercatat" : "Belum sampai";
    }

    public static void detail(java.awt.Component owner, int idPesanan) {
        try {
            model.Pengguna login = config.SesiPengguna.get();
            if (login == null || !"Pelanggan".equals(login.getRole())) throw new SecurityException("Silakan login sebagai Pelanggan.");
            Pesanan p = new dao.PesananDAO().cariBerdasarkanId(idPesanan, login.getIdPengguna());
            if (p == null) throw new SecurityException("Pesanan bukan milik pelanggan yang sedang login.");
            Pengiriman sh = new controller.PelangganController().tampilkanStatusPengiriman(login.getIdPengguna()).stream()
                .filter(item -> item.getPesananIdPesanan() == idPesanan).findFirst().orElse(null);
            if (sh != null) {
                view.agen.BuktiPengirimanView detail = new view.agen.BuktiPengirimanView(sh.getNomorResi(), null);
                detail.setLocationRelativeTo(owner);
                detail.setVisible(true);
            } else JOptionPane.showMessageDialog(owner, "ID Pesanan: " + p.getIdPesanan()
                + "\nPenerima: " + p.getNamaPenerima() + "\nKota tujuan: " + p.getKotaTujuan()
                + "\nStatus pesanan: " + p.getStatusPesanan() + "\nResi: Belum diterbitkan"
                + "\nEstimasi tiba: Belum ditentukan\nWaktu tiba: Belum sampai", "Detail Pesanan", JOptionPane.INFORMATION_MESSAGE);
        } catch (RuntimeException ex) { JOptionPane.showMessageDialog(owner, ex.getMessage(), "Detail Pesanan", JOptionPane.WARNING_MESSAGE); }
    }
}
