package dao;

import config.HibernateUtil;
import model.Pesanan;
import model.PesananId;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PesananDAO implements Repository<Pesanan> {

    public int buatIdPesanan() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Integer id = session.createQuery(
                    "SELECT MAX(p.idPesanan) FROM Pesanan p",
                    Integer.class)
                    .uniqueResult();

            if (id == null) {
                return 1;
            }

            return id + 1;
        }
    }

    public boolean tambah(Pesanan pesanan) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                int id = session.doReturningWork(connection -> {
                    String sql = "INSERT INTO pesanan (nama_pengirim,no_telepon_pengirim,alamat_pengirim,email_pengirim,"
                            + "nama_penerima,telepon_penerima,alamat_tujuan,kota_tujuan,jenis_kiriman,berat_barang,"
                            + "total_biaya,tanggal_pesanan,status_pesanan,layanan_id_layanan,pengguna_id_pengguna) "
                            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    try (java.sql.PreparedStatement insert = connection.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
                        insert.setString(1, pesanan.getNamaPengirim());
                        insert.setString(2, pesanan.getNoTeleponPengirim());
                        insert.setString(3, pesanan.getAlamatPengirim());
                        insert.setString(4, pesanan.getEmailPengirim());
                        insert.setString(5, pesanan.getNamaPenerima());
                        insert.setString(6, pesanan.getTeleponPenerima());
                        insert.setString(7, pesanan.getAlamatTujuan());
                        insert.setString(8, pesanan.getKotaTujuan());
                        insert.setString(9, pesanan.getJenisKiriman());
                        insert.setBigDecimal(10, pesanan.getBeratBarang());
                        insert.setBigDecimal(11, pesanan.getTotalBiaya());
                        insert.setTimestamp(12, java.sql.Timestamp.valueOf(pesanan.getTanggalPesanan()));
                        insert.setString(13, pesanan.getStatusPesanan());
                        insert.setInt(14, pesanan.getLayananIdLayanan());
                        insert.setInt(15, pesanan.getPenggunaIdPengguna());
                        if (insert.executeUpdate() != 1) throw new java.sql.SQLException("Pesanan tidak tersimpan.");
                        try (java.sql.ResultSet keys = insert.getGeneratedKeys()) {
                            if (!keys.next()) throw new java.sql.SQLException("ID pesanan belum terbentuk.");
                            return keys.getInt(1);
                        }
                    }
                });
                tx.commit();
                pesanan.setIdPesanan(id);
                return true;
            } catch (RuntimeException ex) {
                if (tx.isActive()) tx.rollback();
                throw new IllegalStateException("Pesanan gagal disimpan. Periksa data dan koneksi database.", ex);
            }
        }
    }

    public boolean ubah(Pesanan pesanan) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Pesanan existing=session.get(Pesanan.class,
                    new PesananId(pesanan.getIdPesanan(),pesanan.getPenggunaIdPengguna()));
            if (existing == null) throw new IllegalArgumentException("Pesanan tidak ditemukan.");
            session.merge(pesanan);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }

    public Pesanan cariBerdasarkanId(int idPesanan, int idPengguna) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            PesananId id = new PesananId(idPesanan, idPengguna);
            return session.get(Pesanan.class, id);
        }
    }

    public List<Pesanan> tampilkanSemua() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Pesanan ORDER BY idPesanan",
                    Pesanan.class)
                    .list();
        }
    }

    public List<Pesanan> tampilkanBerdasarkanPengguna(int idPengguna) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Pesanan WHERE penggunaIdPengguna = :id",
                    Pesanan.class)
                    .setParameter("id", idPengguna)
                    .list();
        }
    }

    public List<Pesanan> cariBerdasarkanNama(String nama) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM Pesanan WHERE LOWER(namaPengirim) LIKE :nama "
                    + "OR LOWER(namaPenerima) LIKE :nama",
                    Pesanan.class)
                    .setParameter("nama", "%" + nama.toLowerCase() + "%")
                    .list();
        }
    }

    public boolean hapus(int idPesanan, int idPengguna) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            PesananId id = new PesananId(idPesanan, idPengguna);
            Pesanan pesanan = session.get(Pesanan.class, id);

            if (pesanan == null) {
                transaction.rollback();
                return false;
            }

            if (!("Menunggu".equals(pesanan.getStatusPesanan()) ||
                  "Diproses".equals(pesanan.getStatusPesanan()))) {
                transaction.rollback(); return false;
            }
            Long terkait=session.createQuery("SELECT COUNT(k) FROM Pengiriman k WHERE k.pesananIdPesanan = :pesanan AND k.pesananPenggunaIdPengguna = :pemilik",Long.class)
                    .setParameter("pesanan",idPesanan).setParameter("pemilik",idPengguna).uniqueResult();
            if(terkait!=null && terkait>0){transaction.rollback();return false;}
            session.remove(pesanan);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            return false;
        }
    }
}