package core.service;

import core.data.MenuDao;
import core.domain.Menu;

import java.sql.SQLException;
import java.util.List;

public class MenuService {
    MenuDao menuDao;

    public MenuService() {
        this.menuDao = new MenuDao();
    }

    public List<Menu> listingMenu() throws SQLException, ClassNotFoundException {
        return menuDao.listingMenu();
    }

    public void createMenu(List<Menu> menus) {
        menuDao.createMenu(menus);
    }
}
