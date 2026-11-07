package controller;

import dao.PenggunaDAO;
import model.Pengguna;

public class LoginController {

    private final PenggunaDAO penggunaDAO;

    public LoginController() {
        penggunaDAO = new PenggunaDAO();
    }

    public Pengguna login(String username, String password) {
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            return null;
        }

        return penggunaDAO.login(username, password);
    }
}