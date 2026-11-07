package controller;

import dao.PengirimanDAO;
import dao.PesananDAO;
import model.Pengiriman;
import model.Pesanan;
import java.util.List;

public class CabangController {

    private final PengirimanDAO pengirimanDAO;
    private final PesananDAO pesananDAO;

    public CabangController() {
        pengirimanDAO = new PengirimanDAO();
        pesananDAO = new PesananDAO();
    }

    public List<Pesanan> tampilkanPesanan() {
        return pesananDAO.tampilkanSemua();
    }

    public List<Pengiriman> tampilkanJadwal() {
        return pengirimanDAO.tampilkanSemua();
    }

    public List<Pengiriman> tampilkanJadwalCabang(int idCabang) {
        return pengirimanDAO.tampilkanBerdasarkanCabang(idCabang);
    }

    public Pengiriman cariPengiriman(String nomorResi) {
        return pengirimanDAO.cariBerdasarkanResi(nomorResi);
    }

    public boolean tambahJadwal(Pengiriman pengiriman) {
        return pengirimanDAO.tambah(pengiriman);
    }

    public boolean ubahJadwal(Pengiriman pengiriman) {
        return pengirimanDAO.ubah(pengiriman);
    }

    public boolean hapusJadwal(String nomorResi) {
        return pengirimanDAO.hapus(nomorResi);
    }
}