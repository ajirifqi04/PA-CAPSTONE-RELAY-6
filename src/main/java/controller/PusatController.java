package controller;

import dao.PengirimanDAO;
import dao.PembayaranDAO;
import model.Pengiriman;
import model.Pembayaran;
import java.math.BigDecimal;
import java.util.List;

public class PusatController {

    private final PengirimanDAO pengirimanDAO;
    private final PembayaranDAO pembayaranDAO;

    public PusatController() {
        pengirimanDAO = new PengirimanDAO();
        pembayaranDAO = new PembayaranDAO();
    }

    public List<Pengiriman> tampilkanPengiriman() {
        return pengirimanDAO.tampilkanSemua();
    }

    public List<Pembayaran> tampilkanPembayaran() {
        return pembayaranDAO.tampilkanSemua();
    }

    public int hitungPengiriman() {
        return pengirimanDAO.tampilkanSemua().size();
    }

    public int hitungStatusPengiriman(String status) {
        int jumlah = 0;

        for (Pengiriman pengiriman : pengirimanDAO.tampilkanSemua()) {
            if (status.equalsIgnoreCase(pengiriman.getStatusPengiriman())) {
                jumlah++;
            }
        }

        return jumlah;
    }

    public BigDecimal hitungTotalPendapatan() {
        BigDecimal total = BigDecimal.ZERO;

        for (Pembayaran pembayaran : pembayaranDAO.tampilkanSemua()) {
            if ("Lunas".equalsIgnoreCase(pembayaran.getStatusPembayaran())) {
                total = total.add(pembayaran.getJumlahBayar());
            }
        }

        return total;
    }
}