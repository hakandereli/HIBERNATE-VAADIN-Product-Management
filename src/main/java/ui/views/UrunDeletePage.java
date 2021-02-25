package ui.views;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import core.domain.Urun;
import core.service.UrunService;

public class UrunDeletePage extends VerticalLayout {
    Urun urun;
    Button yes;
    Button no;
    Window myDeleteWindow;

    public UrunDeletePage(Urun urun) {
        this.urun = urun;
        buildMainLayout(urun);
    }

    private void buildMainLayout(Urun urun) {
        setWidth("98%");
        setHeight("95%");

        HorizontalLayout mesaj = new HorizontalLayout();
        Label lbl = new Label("");
        lbl.setValue(urun.getAd());
        mesaj.addComponent(lbl);
        addComponent(mesaj);


        HorizontalLayout buttonlar = new HorizontalLayout();
        yes = new Button("Sil");
        yes.setIcon(FontAwesome.TRASH);
        yes.addStyleName(ValoTheme.BUTTON_DANGER);
        yes.setWidth("130px");
        buttonlar.addComponent(yes);

        no = new Button("Vazgeç");
        no.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        no.setWidth("130px");
        buttonlar.addComponent(no);

        addComponent(buttonlar);

        urunuSil(this.urun);
    }

    private void urunuSil(Urun urun) {
        yes.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    UrunService urunService = new UrunService();
                    urunService.deleteUrun(urun);
                    Notification.show(urun.getAd() + " Adlı ürün silindi !");
                    UI.getCurrent().getUI().removeWindow(getMyDeleteWindow());
                } catch (Exception exception) {
                    Notification.show(exception.getMessage());
                }
            }
        });

        no.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().getUI().removeWindow(getMyDeleteWindow());
            }
        });

    }

    public Window getMyDeleteWindow() {
        return myDeleteWindow;
    }

    public void setMyDeleteWindow(Window myDeleteWindow) {
        this.myDeleteWindow = myDeleteWindow;
    }
}
