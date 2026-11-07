package dao;
import config.HibernateUtil;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.Session;
import model.Agen;

public class AgenPilihanDAO {
    public List<Agen> aktif(String kota) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Agen WHERE statusOperasional = 'Aktif' AND kota = :kota ORDER BY namaLokasi", Agen.class)
                    .setParameter("kota", kota).list();
        }
    }
    public List<String> daftarKota() {
        try(Session s=HibernateUtil.getSessionFactory().openSession()) {
            return s.createQuery("SELECT DISTINCT a.kota FROM Agen a WHERE a.statusOperasional = 'Aktif' ORDER BY a.kota",String.class).list();
        }
    }
    public Agen byId(int id) {
        try(Session s=HibernateUtil.getSessionFactory().openSession()) { return s.get(Agen.class,id); }
    }
}
