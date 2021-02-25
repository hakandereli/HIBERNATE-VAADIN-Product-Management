package ui.views;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import core.domain.Menu;
import core.service.MenuService;
import ui.components.Content;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SidebarPage extends VerticalLayout {
    static Content content;

    public SidebarPage(Content content) {
        this.content = content;
        buildMainLayout();
    }

    private void buildMainLayout() {
        //Bir kere çalıştır.
//        createMenu();
        List<Menu> menuList = getMenuList();
        buildButtons(menuList);
    }

    private void createMenu() {
        Menu menu1 = new Menu();
        menu1.setAd("Kategori Ekle");
        menu1.setClassdirectory("KategoriAddPage");

        Menu menu2 = new Menu();
        menu2.setAd("Kategori Sil");
        menu2.setClassdirectory("KategoriDeletePage");

        Menu menu3 = new Menu();
        menu3.setAd("Urun Ekle");
        menu3.setClassdirectory("UrunAddUpdatePage");

        Menu menu4 = new Menu();
        menu4.setAd("Urunleri Listele");
        menu4.setClassdirectory("UrunListingPage");

        List<Menu> menus = new ArrayList<Menu>();
        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);

        MenuService menuService = new MenuService();
        menuService.createMenu(menus);
    }

    private List<Menu> getMenuList() {
        List<Menu> menuList = new ArrayList<Menu>();
        MenuService menuService = new MenuService();

        try {
            menuList = menuService.listingMenu();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception exception) {
            Notification.show(exception.getMessage());
        }
        return menuList;
    }

    private void buildButtons(List<Menu> menuList) {
        for (Menu menuItem : menuList) {
            Button menuButtons = new Button(menuItem.getAd());
            addComponent(menuButtons);

            menuButtons.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    menuGetButtons(menuItem);
                }
            });
        }
    }

    private void menuGetButtons(Menu menuItem) {
        try {
            Class<?> createClass = Class.forName("ui.views." + menuItem.getClassdirectory());
            Object obj = createClass.newInstance();
            Component component = (Component) obj;
            content.setContent(component);
        } catch (ClassNotFoundException hata) {
            Notification.show(menuItem.getClassdirectory());
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }
}
