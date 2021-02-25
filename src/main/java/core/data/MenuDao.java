package core.data;

import core.data.utils.HibernateUtil;
import core.domain.Menu;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class MenuDao {
    List<Menu> menuList = new ArrayList<Menu>();

    public List<Menu> listingMenu() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("select menu from Menu menu");
            menuList = query.list();
            session.close();
        } catch (Exception hata) {
            throw new RuntimeException(hata);
        }
        return menuList;
    }

    public void createMenu(List<Menu> menus) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            for (Menu m : menus) {
                session.save(m);
            }
            transaction.commit();
            session.close();
        } catch (Exception hata) {
            throw new RuntimeException(hata);
        }
    }
}
