package model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;
import java.time.LocalDateTime;

@Entity
@Table(name = "pengiriman")
public class Pengiriman {

    @Id
    @Column(name = "nomor_resi", nullable = false, length = 30)
    private String nomorResi;

    @Column(name = "tanggal_pengiriman")
    private LocalDateTime tanggalPengiriman;

    @Column(name = "tanggal_diterima")
    private LocalDateTime tanggalDiterima;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "status_pengiriman", nullable = false)
    private String statusPengiriman;

    @Column(name = "bukti_dokumentasi", length = 255)
    private String buktiDokumentasi;

    @Column(name = "tarif_wilayah_id_tarif", nullable = false)
    private int tarifWilayahIdTarif;

    @Column(name = "agen_id_lokasi", nullable = false)
    private int agenIdLokasi;

    @Column(name = "cabang_id_lokasi", nullable = false)
    private int cabangIdLokasi;

    @Column(name = "pesanan_id_pesanan", nullable = false)
    private int pesananIdPesanan;

    @Column(name = "pesanan_pengguna_id_pengguna", nullable = false)
    private int pesananPenggunaIdPengguna;

    public String getNomorResi() {
        return nomorResi;
    }

    public void setNomorResi(String nomorResi) {
        this.nomorResi = nomorResi;
    }

    public LocalDateTime getTanggalPengiriman() {
        return tanggalPengiriman;
    }

    public void setTanggalPengiriman(LocalDateTime tanggalPengiriman) {
        this.tanggalPengiriman = tanggalPengiriman;
    }

    public LocalDateTime getTanggalDiterima() {
        return tanggalDiterima;
    }

    public void setTanggalDiterima(LocalDateTime tanggalDiterima) {
        this.tanggalDiterima = tanggalDiterima;
    }

    public String getStatusPengiriman() {
        return statusPengiriman;
    }

    public void setStatusPengiriman(String statusPengiriman) {
        this.statusPengiriman = statusPengiriman;
    }

    public String getBuktiDokumentasi() {
        return buktiDokumentasi;
    }

    public void setBuktiDokumentasi(String buktiDokumentasi) {
        this.buktiDokumentasi = buktiDokumentasi;
    }

    public int getTarifWilayahIdTarif() {
        return tarifWilayahIdTarif;
    }

    public void setTarifWilayahIdTarif(int tarifWilayahIdTarif) {
        this.tarifWilayahIdTarif = tarifWilayahIdTarif;
    }

    public int getAgenIdLokasi() {
        return agenIdLokasi;
    }

    public void setAgenIdLokasi(int agenIdLokasi) {
        this.agenIdLokasi = agenIdLokasi;
    }

    public int getCabangIdLokasi() {
        return cabangIdLokasi;
    }

    public void setCabangIdLokasi(int cabangIdLokasi) {
        this.cabangIdLokasi = cabangIdLokasi;
    }

    public int getPesananIdPesanan() {
        return pesananIdPesanan;
    }

    public void setPesananIdPesanan(int pesananIdPesanan) {
        this.pesananIdPesanan = pesananIdPesanan;
    }

    public int getPesananPenggunaIdPengguna() {
        return pesananPenggunaIdPengguna;
    }

    public void setPesananPenggunaIdPengguna(int pesananPenggunaIdPengguna) {
        this.pesananPenggunaIdPengguna = pesananPenggunaIdPengguna;
    }
}