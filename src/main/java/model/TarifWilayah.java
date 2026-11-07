package model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
    name = "tarif_wilayah",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {
                "layanan_id_layanan",
                "kota_asal",
                "kota_tujuan"
            }
        )
    }
)
public class TarifWilayah {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarif")
    private int idTarif;

    @Column(name = "kota_asal", nullable = false)
    private String kotaAsal;

    @Column(name = "kota_tujuan", nullable = false)
    private String kotaTujuan;

    @Column(name = "biaya_per_kg", nullable = false, precision = 12, scale = 2)
    private BigDecimal biayaPerKg;

    @Column(name = "layanan_id_layanan", nullable = false)
    private int layananIdLayanan;

    public TarifWilayah() {
    }

    public int getIdTarif() {
        return idTarif;
    }

    public void setIdTarif(int idTarif) {
        this.idTarif = idTarif;
    }

    public String getKotaAsal() {
        return kotaAsal;
    }

    public void setKotaAsal(String kotaAsal) {
        this.kotaAsal = kotaAsal;
    }

    public String getKotaTujuan() {
        return kotaTujuan;
    }

    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public BigDecimal getBiayaPerKg() {
        return biayaPerKg;
    }

    public void setBiayaPerKg(BigDecimal biayaPerKg) {
        this.biayaPerKg = biayaPerKg;
    }

    public int getLayananIdLayanan() {
        return layananIdLayanan;
    }

    public void setLayananIdLayanan(int layananIdLayanan) {
        this.layananIdLayanan = layananIdLayanan;
    }
}
