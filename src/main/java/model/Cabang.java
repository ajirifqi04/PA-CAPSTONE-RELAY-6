package model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;

@Entity
@Table(name = "cabang")
@PrimaryKeyJoinColumn(name = "id_lokasi")
public class Cabang extends LokasiOperasional {

    @Column(name = "kode_cabang", nullable = false, unique = true, length = 20)
    private String kodeCabang;

    @Column(name = "nama_admin_cabang", nullable = false, length = 100)
    private String namaAdminCabang;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "status_operasional", nullable = false)
    private String statusOperasional;

    public String getKodeCabang() {
        return kodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        this.kodeCabang = kodeCabang;
    }

    public String getNamaAdminCabang() {
        return namaAdminCabang;
    }

    public void setNamaAdminCabang(String namaAdminCabang) {
        this.namaAdminCabang = namaAdminCabang;
    }

    public String getStatusOperasional() {
        return statusOperasional;
    }

    public void setStatusOperasional(String statusOperasional) {
        this.statusOperasional = statusOperasional;
    }
}