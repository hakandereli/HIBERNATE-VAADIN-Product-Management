package ui.views;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import core.domain.Kategori;
import core.service.KategoriService;

import java.util.List;

public class KategoriAddPage extends VerticalLayout {

    @PropertyId("ad")
    private TextField adTextField;

    private Kategori kategori;
    private BeanFieldGroup<Kategori> binder;
    private Button btnEkle;

    public KategoriAddPage() {
        buildMainLayout();
        buildBinder();
        fillViewForNewRecord();
    }

    public void buildMainLayout() {
        Label lbl = new Label("Eklemek İstediğiniz Kategoriyi Yazınız...");

        adTextField = new TextField("Kategori Adı");
        adTextField.setRequired(true);

        adTextField.setImmediate(true);
        addComponent(adTextField);

        btnEkle = new Button();
        btnEkle.setCaption("Ekle");
        btnEkle.setIcon(FontAwesome.PLUS);
        addComponent(btnEkle);

        btnEkle.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                validateKaydet();
                kategoriKaydet();
            }
        });
    }

    private void buildBinder() {
        binder = new BeanFieldGroup<Kategori>(Kategori.class);
        binder.bindMemberFields(this);
//        binder.bind(idField, "id");
//        binder.bind(adTextField, "ad");
    }

    private void fillViewForNewRecord() {
        kategori = new Kategori();
        //kategori.setId(0);
        kategori.setAd("nullvalue");
        binder.setItemDataSource(kategori);
    }

    private void validateKaydet() {
        try {
            binder.commit();
        } catch (FieldGroup.CommitException e) {
            Notification.show("Lütfen form alanlarını eksiksiz doldurun");
        }
    }

    public void kategoriKaydet() {
        try {
            kategori = binder.getItemDataSource().getBean();
            if (kategori.getAd() == "nullvalue") {
                Notification.show("Kategori Adını yazmadınız !");
            } else {
                KategoriService kategoriService = new KategoriService();
                kategoriService.addKategori(kategori);


                Notification.show(kategori.getAd() + " Kategorisi eklendi");
                fillViewForNewRecord();
            }
        } catch (Exception exception) {
            Notification.show(exception.getMessage());
        }
    }
}