package view.agen;

import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.*;
import model.Pengiriman;

public class BuktiPengirimanView extends JFrame {
    private String resi;
    private int nomor;
    private Pengiriman kiriman;
    private JLabel info;
    private JLabel pratinjau;
    private JButton sebelumnya;
    private JButton berikutnya;

    public BuktiPengirimanView() {
        initComponents();
        info = util.KomponenForm.cari(getContentPane(), "relay.bukti.info", JLabel.class);
        pratinjau = util.KomponenForm.cari(getContentPane(), "relay.bukti.foto", JLabel.class);
        sebelumnya = util.KomponenForm.cari(getContentPane(), "relay.bukti.sebelumnya", JButton.class);
        berikutnya = util.KomponenForm.cari(getContentPane(), "relay.bukti.berikutnya", JButton.class);
        sebelumnya.addActionListener(e -> pindah(-1));
        berikutnya.addActionListener(e -> pindah(1));
        util.KomponenForm.cari(getContentPane(), "relay.bukti.tutup", JButton.class).addActionListener(e -> dispose());
        sebelumnya.setEnabled(false);
        berikutnya.setEnabled(false);
        setSize(720, 560);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public BuktiPengirimanView(String nomorResi, Runnable refresh) {
        this();
        resi = nomorResi;
        kiriman = util.PenerimaanAgen.lihat(resi);
        nomor = util.BuktiFoto.indeks(kiriman);
        tampilFoto();
    }

    private void pindah(int langkah) {
        nomor = Math.floorMod(nomor + langkah, util.BuktiFoto.JUMLAH);
        tampilFoto();
    }

    private void tampilFoto() {
        kiriman = util.PenerimaanAgen.lihat(resi);
        boolean sampai = util.PenerimaanAgen.diterima(kiriman);
        sebelumnya.setEnabled(sampai);
        berikutnya.setEnabled(sampai);
        String estimasi = util.PelacakanPesanan.estimasi(kiriman);
        String aktual = util.PelacakanPesanan.tiba(kiriman);
        info.setText("<html><b>Detail Pengiriman Paket</b><br>Resi: " + resi
            + "<br>Status: " + kiriman.getStatusPengiriman()
            + "<br>Estimasi tiba: " + estimasi + " | Waktu tiba: " + aktual
            + "<br>" + (sampai ? "Dokumentasi simulasi — foto " + (nomor + 1) + " dari " + util.BuktiFoto.JUMLAH : "Dokumentasi tersedia setelah paket sampai tujuan.") + "</html>");
        if (!sampai) {
            pratinjau.setIcon(null);
            pratinjau.setText("Paket belum sampai tujuan.");
            return;
        }
        try {
            BufferedImage gambar = util.BuktiFoto.gambar(nomor);
            double skala = Math.min(660.0 / gambar.getWidth(), 320.0 / gambar.getHeight());
            skala = Math.min(1.0, skala);
            int lebar = Math.max(1, (int) (gambar.getWidth() * skala));
            int tinggi = Math.max(1, (int) (gambar.getHeight() * skala));
            pratinjau.setText("");
            pratinjau.setIcon(new ImageIcon(gambar.getScaledInstance(lebar, tinggi, Image.SCALE_SMOOTH)));
            pratinjau.setToolTipText("Foto contoh dokumentasi simulasi paket sampai tujuan");
        } catch (RuntimeException ex) {
            pratinjau.setIcon(null);
            pratinjau.setText(ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {//GEN-BEGIN:initComponents
        lblInfo = new javax.swing.JLabel();
        scrollFoto = new javax.swing.JScrollPane();
        lblFoto = new javax.swing.JLabel();
        panelTombol = new javax.swing.JPanel();
        btnSebelumnya = new javax.swing.JButton();
        btnBerikutnya = new javax.swing.JButton();
        btnTutup = new javax.swing.JButton();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detail Pengiriman - RELAY");
        setPreferredSize(new java.awt.Dimension(720, 560));
        lblInfo.setText("<html><b>Detail Pengiriman Paket</b><br>Estimasi tiba dan waktu tiba aktual<br>Dokumentasi simulasi otomatis setelah paket sampai.</html>");
        lblInfo.setName("relay.bukti.info");
        lblInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 16, 14, 16));
        getContentPane().add(lblInfo, java.awt.BorderLayout.NORTH);
        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setText("Dokumentasi simulasi paket");
        lblFoto.setName("relay.bukti.foto");
        scrollFoto.setViewportView(lblFoto);
        getContentPane().add(scrollFoto, java.awt.BorderLayout.CENTER);
        btnSebelumnya.setText("Foto Sebelumnya");
        btnSebelumnya.setName("relay.bukti.sebelumnya");
        panelTombol.add(btnSebelumnya);
        btnBerikutnya.setText("Foto Berikutnya");
        btnBerikutnya.setName("relay.bukti.berikutnya");
        panelTombol.add(btnBerikutnya);
        btnTutup.setText("Tutup");
        btnTutup.setName("relay.bukti.tutup");
        panelTombol.add(btnTutup);
        getContentPane().add(panelTombol, java.awt.BorderLayout.SOUTH);
        pack();
    }//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSebelumnya;
    private javax.swing.JButton btnBerikutnya;
    private javax.swing.JButton btnTutup;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JPanel panelTombol;
    private javax.swing.JScrollPane scrollFoto;
    // End of variables declaration//GEN-END:variables
}
