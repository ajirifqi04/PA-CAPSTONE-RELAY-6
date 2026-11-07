package model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;

@Entity
@Table(name = "agen")
@PrimaryKeyJoinColumn(name = "id_lokasi")
public class Agen extends LokasiOperasional {

    @Column(name = "nama_admin_agen", nullable = false, length = 100)
    private String namaAdminAgen;

    @Column(name = "kode_agen", nullable = false, unique = true, length = 20)
    private String kodeAgen;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "status_operasional", nullable = false)
    private String statusOperasional;

    @Column(name = "cabang_id_lokasi", nullable = false)
    private int cabangIdLokasi;

    public String getNamaAdminAgen() {
        return namaAdminAgen;
    }

    public void setNamaAdminAgen(String namaAdminAgen) {
        this.namaAdminAgen = namaAdminAgen;
    }

    public String getKodeAgen() {
        return kodeAgen;
    }

    public void setKodeAgen(String kodeAgen) {
        this.kodeAgen = kodeAgen;
    }

    public String getStatusOperasional() {
        return statusOperasional;
    }

    public void setStatusOperasional(String statusOperasional) {
        this.statusOperasional = statusOperasional;
    }

    public int getCabangIdLokasi() {
        return cabangIdLokasi;
    }

    public void setCabangIdLokasi(int cabangIdLokasi) {
        this.cabangIdLokasi = cabangIdLokasi;
    }
}