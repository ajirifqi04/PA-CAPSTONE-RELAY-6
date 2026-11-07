package model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pesanan")
@IdClass(PesananId.class)
public class Pesanan {

    @Id
    @Column(name = "id_pesanan")
    private int idPesanan;

    @Id
    @Column(name = "pengguna_id_pengguna")
    private int penggunaIdPengguna;

    @Column(name = "nama_pengirim", nullable = false, length = 100)
    private String namaPengirim;

    @Column(name = "no_telepon_pengirim", nullable = false, length = 20)
    private String noTeleponPengirim;

    @Column(name = "alamat_pengirim", nullable = false)
    private String alamatPengirim;

    @Column(name = "email_pengirim", nullable = false, length = 100)
    private String emailPengirim;

    @Column(name = "nama_penerima", nullable = false, length = 100)
    private String namaPenerima;

    @Column(name = "telepon_penerima", nullable = false, length = 20)
    private String teleponPenerima;

    @Column(name = "alamat_tujuan", nullable = false)
    private String alamatTujuan;

    @Column(name = "kota_tujuan", nullable = false, length = 100)
    private String kotaTujuan;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "jenis_kiriman", nullable = false)
    private String jenisKiriman;

    @Column(name = "berat_barang", precision = 8, scale = 2)
    private BigDecimal beratBarang;

    @Column(name = "total_biaya", precision = 12, scale = 2)
    private BigDecimal totalBiaya;

    @Column(name = "tanggal_pesanan", insertable = false, updatable = false)
    private LocalDateTime tanggalPesanan;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "status_pesanan", nullable = false)
    private String statusPesanan;

    @Column(name = "layanan_id_layanan", nullable = false)
    private int layananIdLayanan;

    public int getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(int idPesanan) {
        this.idPesanan = idPesanan;
    }

    public int getPenggunaIdPengguna() {
        return penggunaIdPengguna;
    }

    public void setPenggunaIdPengguna(int penggunaIdPengguna) {
        this.penggunaIdPengguna = penggunaIdPengguna;
    }

    public String getNamaPengirim() {
        return namaPengirim;
    }

    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }

    public String getNoTeleponPengirim() {
        return noTeleponPengirim;
    }

    public void setNoTeleponPengirim(String noTeleponPengirim) {
        this.noTeleponPengirim = noTeleponPengirim;
    }

    public String getAlamatPengirim() {
        return alamatPengirim;
    }

    public void setAlamatPengirim(String alamatPengirim) {
        this.alamatPengirim = alamatPengirim;
    }

    public String getEmailPengirim() {
        return emailPengirim;
    }

    public void setEmailPengirim(String emailPengirim) {
        this.emailPengirim = emailPengirim;
    }

    public String getNamaPenerima() {
        return namaPenerima;
    }

    public void setNamaPenerima(String namaPenerima) {
        this.namaPenerima = namaPenerima;
    }

    public String getTeleponPenerima() {
        return teleponPenerima;
    }

    public void setTeleponPenerima(String teleponPenerima) {
        this.teleponPenerima = teleponPenerima;
    }

    public String getAlamatTujuan() {
        return alamatTujuan;
    }

    public void setAlamatTujuan(String alamatTujuan) {
        this.alamatTujuan = alamatTujuan;
    }

    public String getKotaTujuan() {
        return kotaTujuan;
    }

    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public String getJenisKiriman() {
        return jenisKiriman;
    }

    public void setJenisKiriman(String jenisKiriman) {
        this.jenisKiriman = jenisKiriman;
    }

    public BigDecimal getBeratBarang() {
        return beratBarang;
    }

    public void setBeratBarang(BigDecimal beratBarang) {
        this.beratBarang = beratBarang;
    }

    public BigDecimal getTotalBiaya() {
        return totalBiaya;
    }

    public void setTotalBiaya(BigDecimal totalBiaya) {
        this.totalBiaya = totalBiaya;
    }

    public LocalDateTime getTanggalPesanan() {
        return tanggalPesanan;
    }

    public void setTanggalPesanan(LocalDateTime tanggalPesanan) {
        this.tanggalPesanan = tanggalPesanan;
    }

    public String getStatusPesanan() {
        return statusPesanan;
    }

    public void setStatusPesanan(String statusPesanan) {
        this.statusPesanan = statusPesanan;
    }

    public int getLayananIdLayanan() {
        return layananIdLayanan;
    }

    public void setLayananIdLayanan(int layananIdLayanan) {
        this.layananIdLayanan = layananIdLayanan;
    }
}