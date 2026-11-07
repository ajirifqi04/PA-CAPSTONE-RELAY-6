package model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.sql.Types;

@Entity
@Table(name = "pengguna")
public class Pengguna {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pengguna")
    private int idPengguna;

    @Column(name = "nama_pengguna", nullable = false, length = 100)
    private String namaPengguna;

    @Column(name = "username", nullable = false, unique = true, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "lokasi_operasional_id_lokasi")
    private Integer lokasiOperasionalIdLokasi;

    public int getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(int idPengguna) {
        this.idPengguna = idPengguna;
    }

    public String getNamaPengguna() {
        return namaPengguna;
    }

    public void setNamaPengguna(String namaPengguna) {
        this.namaPengguna = namaPengguna;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getLokasiOperasionalIdLokasi() {
        return lokasiOperasionalIdLokasi;
    }

    public void setLokasiOperasionalIdLokasi(Integer lokasiOperasionalIdLokasi) {
        this.lokasiOperasionalIdLokasi = lokasiOperasionalIdLokasi;
    }
}
