package core.data;

import core.data.utils.HibernateUtil;
import core.domain.Kategori;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class KategoriDao {

    public void addKategori(Kategori kategori) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(kategori);
            transaction.commit();
            session.close();
        } catch (Exception hata) {
            throw new RuntimeException(hata);
        }
    }

    public List<Kategori> listingKategori() {
        List<Kategori> kategoriList = new ArrayList<Kategori>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "Select kategori From Kategori kategori";
            Query query = session.createQuery(hql);
            kategoriList = query.list();
            session.close();
        } catch (Exception hata) {
            throw new RuntimeException(hata);
        }
        return kategoriList;
    }

    public void deleteKategori(Kategori kategori) {
        Kategori deletedKategori = new Kategori();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            deletedKategori = (Kategori) session.get(Kategori.class, kategori.getId());
            session.delete(deletedKategori);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }
}
