package controller;

import dao.PenggunaDAO;
import model.Pengguna;

public class RegistrasiController {

    private final PenggunaDAO penggunaDAO;

    public RegistrasiController() {
        penggunaDAO = new PenggunaDAO();
    }

    public boolean registrasi(String nama, String username, String password) {
        nama = util.Validasi.nama(nama, "Nama lengkap");
        username = util.Validasi.username(username);
        util.Validasi.password(password);

        if (penggunaDAO.usernameTerdaftar(username)) {
            return false;
        }

        Pengguna pengguna = new Pengguna();
        pengguna.setNamaPengguna(nama);
        pengguna.setUsername(username);
        pengguna.setPassword(util.PasswordHash.hash(password));
        pengguna.setRole("Pelanggan");
        pengguna.setLokasiOperasionalIdLokasi(null);

        return penggunaDAO.tambah(pengguna);
    }
}
