package ui.views;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.*;
import core.domain.Urun;
import ui.field.KategoriComboField;

public class UrunDisplayPage extends VerticalLayout {
    @PropertyId("id")
    TextField idTextField;

    @PropertyId("kategorisi")
    KategoriComboField kategorisi;

    @PropertyId("ad")
    TextField urunAdi;

    @PropertyId("aciklama")
    TextArea urunAciklamasi;

    TextField id_kategori;
    Urun urunBindObject;
    Window myDisplayWindow;
    Label kategoriLabel;

    private BeanFieldGroup<Urun> binder;

    public UrunDisplayPage(Urun urun) {
        urunBindObject = urun;
        buildMainlayout();
    }

    private void buildMainlayout() {
        setWidth("95%");

        formBuild();
        buildBinder();
    }

    private void formBuild() {
        idTextField = new TextField("Urun İd");
        addComponent(idTextField);

        id_kategori = new TextField("Urun Kategori İd");
        id_kategori.setValue(urunBindObject.getKategorisi().getId().toString());
        addComponent(id_kategori);

        kategoriLabel = new Label("Kategorisi :");
        addComponent(kategoriLabel);

        kategorisi = new KategoriComboField();
        addComponent(kategorisi);

        urunAdi = new TextField("Urun Adı:");
        addComponent(urunAdi);

        urunAciklamasi = new TextArea("Urun Aciklamasi");
        addComponent(urunAciklamasi);
    }

    private void buildBinder() {
        binder = new BeanFieldGroup<Urun>(Urun.class);
        binder.setItemDataSource(urunBindObject);
        binder.bindMemberFields(this);
    }

    public void setMyDisplayWindow(Window myDisplayWindow) {
        this.myDisplayWindow = myDisplayWindow;
    }

    public Window getMyDisplayWindow() {
        return myDisplayWindow;
    }
}