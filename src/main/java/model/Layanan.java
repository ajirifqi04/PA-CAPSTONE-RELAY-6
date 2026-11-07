package model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;
import java.math.BigDecimal;

@Entity
@Table(name = "layanan")
public class Layanan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_layanan")
    private int idLayanan;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "nama_layanan", nullable = false, unique = true)
    private String namaLayanan;

    @Column(name = "tarif_dasar", nullable = false, precision = 12, scale = 2)
    private BigDecimal tarifDasar;

    @Column(name = "estimasi_hari", nullable = false, length = 10)
    private String estimasiHari;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "status_aktif", nullable = false)
    private String statusAktif;

    public int getIdLayanan() {
        return idLayanan;
    }

    public void setIdLayanan(int idLayanan) {
        this.idLayanan = idLayanan;
    }

    public String getNamaLayanan() {
        return namaLayanan;
    }

    public void setNamaLayanan(String namaLayanan) {
        this.namaLayanan = namaLayanan;
    }

    public BigDecimal getTarifDasar() {
        return tarifDasar;
    }

    public void setTarifDasar(BigDecimal tarifDasar) {
        this.tarifDasar = tarifDasar;
    }

    public String getEstimasiHari() {
        return estimasiHari;
    }

    public void setEstimasiHari(String estimasiHari) {
        this.estimasiHari = estimasiHari;
    }

    public String getStatusAktif() {
        return statusAktif;
    }

    public void setStatusAktif(String statusAktif) {
        this.statusAktif = statusAktif;
    }
}