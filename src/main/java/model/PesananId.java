package model;

import java.io.Serializable;
import java.util.Objects;

public class PesananId implements Serializable {

    private int idPesanan;
    private int penggunaIdPengguna;

    public PesananId() {
    }

    public PesananId(int idPesanan, int penggunaIdPengguna) {
        this.idPesanan = idPesanan;
        this.penggunaIdPengguna = penggunaIdPengguna;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof PesananId)) {
            return false;
        }

        PesananId other = (PesananId) object;

        return idPesanan == other.idPesanan
                && penggunaIdPengguna == other.penggunaIdPengguna;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPesanan, penggunaIdPengguna);
    }
}
