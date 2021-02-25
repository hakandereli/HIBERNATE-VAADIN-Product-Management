package ui.views;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import core.domain.Kategori;
import core.service.KategoriService;
import ui.field.KategoriComboField;

public class KategoriDeletePage extends VerticalLayout {

    private KategoriComboField kategoriComboField;
    private Button buttonKategoriyiSil;
    private KategoriService kategoriService;

    public KategoriDeletePage() {
        buildMainLayout();
    }

    public void buildMainLayout() {
        kategoriComboField = new KategoriComboField();
        kategoriComboField.setCaption("Silinecek Kategoriyi Seçin !");
        kategoriComboField.setRequired(true);
        addComponent(kategoriComboField);

        buildKategoriDeleteButton();
        addComponent(buttonKategoriyiSil);

        //Binder olmadan Silme işlemi
        /*
        kategoriComboField.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent){
                Kategori kategori = (Kategori) valueChangeEvent.getProperty().getValue();
                if (kategori == null) return;
                kategori1.setId(kategori.getId());
                kategori1.setAd(kategori.getAd());
            }
        });
        */
        //Kategori value = (Kategori) kategoriComboField.getValue();
    }

    private void buildKategoriDeleteButton() {
        buttonKategoriyiSil = new Button("Sil");
        buttonKategoriyiSil.setIcon(FontAwesome.TRASH_O);
        buttonKategoriyiSil.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                deleteKategori();
            }
        });
    }

    private void deleteKategori() {
        try {
            Kategori selectedKategori = (Kategori) kategoriComboField.getValue();
            if (selectedKategori == null) {
                Notification.show("Silinecek Kategoriyi Seçmediniz !");
            } else {
                kategoriService = new KategoriService();
                kategoriService.deleteKategori(selectedKategori);
                kategoriComboField.fillComboBox();
                Notification.show(selectedKategori.getAd() + " Kategorisi silindi !");
            }
        } catch (Exception exception) {
            Notification.show(exception.getMessage());
        }
    }
}
