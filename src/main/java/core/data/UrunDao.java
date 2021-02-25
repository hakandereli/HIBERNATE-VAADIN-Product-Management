package core.data;

import core.data.utils.HibernateUtil;
import core.domain.Urun;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UrunDao {
    public void deleteUrun(Urun urun) {
        Urun deletedUrun = new Urun();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            deletedUrun = (Urun) session.get(Urun.class, urun.getId());
            session.delete(deletedUrun);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

    }

    public void addOrUpdate(Urun urun) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            //ID si varsa güncelleme işlemidir. Güncelleme yapılacak
            if (urun.getId() != null) {
                Urun selectedUrun = (Urun) session.get(Urun.class, urun.getId());

                selectedUrun.setAd(urun.getAd());
                selectedUrun.setAciklama(urun.getAciklama());
                selectedUrun.setKategorisi(urun.getKategorisi());

                session.update(selectedUrun);
                transaction.commit();
                session.close();
            }
            //ID si yoksa ekleme işlemidir ekleme yapacak.
            else {
                session.save(urun);
                transaction.commit();
                session.close();
            }
        } catch (Exception hata) {
            throw new RuntimeException(hata);
        }
    }

    public List<Urun> findAll() {

        List<Urun> urunList = new ArrayList<Urun>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "Select urun From Urun urun";
            Query query = session.createQuery(hql);
            urunList = query.list();
            session.close();
        } catch (Exception hata) {
            throw new RuntimeException(hata);
        }

        return urunList;
    }
}