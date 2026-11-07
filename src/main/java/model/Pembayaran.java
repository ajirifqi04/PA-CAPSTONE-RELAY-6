package model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pembayaran")
public class Pembayaran {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pembayaran")
    private int idPembayaran;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "metode_pembayaran", nullable = false)
    private String metodePembayaran;

    @Column(name = "jumlah_bayar", nullable = false, precision = 12, scale = 2)
    private BigDecimal jumlahBayar;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "status_pembayaran", nullable = false)
    private String statusPembayaran;

    @Column(name = "tanggal_pembayaran", nullable = false)
    private LocalDateTime tanggalPembayaran;

    @Column(name = "pengguna_id_pengguna", nullable = false)
    private int penggunaIdPengguna;

    @Column(name = "pengiriman_nomor_resi", nullable = false, unique = true, length = 30)
    private String pengirimanNomorResi;

    public int getIdPembayaran() {
        return idPembayaran;
    }

    public void setIdPembayaran(int idPembayaran) {
        this.idPembayaran = idPembayaran;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public BigDecimal getJumlahBayar() {
        return jumlahBayar;
    }

    public void setJumlahBayar(BigDecimal jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public LocalDateTime getTanggalPembayaran() {
        return tanggalPembayaran;
    }

    public void setTanggalPembayaran(LocalDateTime tanggalPembayaran) {
        this.tanggalPembayaran = tanggalPembayaran;
    }

    public int getPenggunaIdPengguna() {
        return penggunaIdPengguna;
    }

    public void setPenggunaIdPengguna(int penggunaIdPengguna) {
        this.penggunaIdPengguna = penggunaIdPengguna;
    }

    public String getPengirimanNomorResi() {
        return pengirimanNomorResi;
    }

    public void setPengirimanNomorResi(String pengirimanNomorResi) {
        this.pengirimanNomorResi = pengirimanNomorResi;
    }
}