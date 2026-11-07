package model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;

@Entity
@Table(name = "lokasi_operasional")
@Inheritance(strategy = InheritanceType.JOINED)
public class LokasiOperasional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lokasi")
    private int idLokasi;

    @Column(name = "nama_lokasi", nullable = false, length = 100)
    private String namaLokasi;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "jenis_lokasi", nullable = false)
    private String jenisLokasi;

    @Column(name = "alamat", nullable = false)
    private String alamat;

    @Column(name = "kota", nullable = false, length = 100)
    private String kota;

    public int getIdLokasi() {
        return idLokasi;
    }

    public void setIdLokasi(int idLokasi) {
        this.idLokasi = idLokasi;
    }

    public String getNamaLokasi() {
        return namaLokasi;
    }

    public void setNamaLokasi(String namaLokasi) {
        this.namaLokasi = namaLokasi;
    }

    public String getJenisLokasi() {
        return jenisLokasi;
    }

    public void setJenisLokasi(String jenisLokasi) {
        this.jenisLokasi = jenisLokasi;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }
}