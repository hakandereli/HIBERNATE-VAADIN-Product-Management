package ui.views;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import core.domain.Kategori;
import core.domain.Urun;
import core.service.UrunService;
import ui.field.KategoriComboField;

public class UrunAddUpdatePage extends VerticalLayout {
    //Ürün Ekleme ve Güncelleme İçin Aynı Class Kullanıldı !
    //Aşağıdaki Fieldler her iki işlem için de aynıdır.
    //Güncelleme işlemi için urun id alanı da okunmaktadır. if(id>0)
    //Eklenecek urunun ise id si olmamalıdır.
    Label sayfaLabel;
    Label kategoriLabel;
//    KategoriComboField kategoriComboField;

    @PropertyId("id")
    TextField idTextField;

    @PropertyId("kategorisi")
    KategoriComboField kategorisi;

    @PropertyId("ad")
    TextField urunAdi;

    @PropertyId("aciklama")
    TextArea urunAciklamasi;

    private BeanFieldGroup<Urun> binder;
    Button urunIslemleriButton;
    UrunService urunService;
    Window myUpdateWindow;
    Kategori volatileKategori = null;
    Urun urunBindObject;

    //GÜNCELLEME //Ürün Geldi Güncelleme İşlemi Yapılacak
    public UrunAddUpdatePage(Urun urun) {
        urunBindObject = urun;
        builMainLayout();
        setWidth("95%");

        sayfaLabel.setValue("Ürün Güncelleme");

        //Güncelleme Buttonu olarak düzenle
        urunIslemleriButton.setIcon(FontAwesome.WRENCH);
        urunIslemleriButton.setCaption("Güncelle");
        urunIslemleriButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);

        editUpdateForm(urun);
    }

    //EKLEME //Ürün Gelmedi Ekleme İşlemi Yapılacak
    public UrunAddUpdatePage() {
        urunBindObject = new Urun();
        builMainLayout();

        //Ekleme Buttonu olarak düzenle
        sayfaLabel.setValue("Ürün Ekleme");
        urunIslemleriButton.setIcon(FontAwesome.PLUS);
        urunIslemleriButton.setCaption("Ekle");
    }

    private void builMainLayout() {
        formBuild();
        buildBinder();
    }

    private void formBuild() {
        sayfaLabel = new Label();
        addComponent(sayfaLabel);

        idTextField = new TextField("Urun İd");
        idTextField.setValue("0");
        addComponent(idTextField);
        idTextField.setVisible(false);

//        id_kategori = new TextField("Urun Kategori İd");
//        addComponent(id_kategori);
//        id_kategori.setVisible(false);

        kategoriLabel = new Label("Kategori Seçiniz");
        addComponent(kategoriLabel);

        kategorisi = new KategoriComboField();
        addComponent(kategorisi);

        urunAdi = new TextField("Urun Adı");
        addComponent(urunAdi);

        urunAciklamasi = new TextArea("Urun Aciklamasi");
        addComponent(urunAciklamasi);

        //İşlemleri gerçekleştirecek button
        createUrunIslemButton();
    }

    private void createUrunIslemButton() {
        urunIslemleriButton = new Button();
        addComponent(urunIslemleriButton);

        urunIslemleriButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                //Ürün İşlem buttonu tek button ile ekleme ve güncelleme işlemlerini yapar. Aşağıdaki kodlarda bu kontrol yapılmıştır.
                try {
                    binderCalistir();

                    //Kategoriyi comboboxtan oku kategori_id alanına yaz.
                    urunService = new UrunService();
//                    volatileKategori = (Kategori) kategorisi.getValue();
//                    urunBindObject.setKategorisi(volatileKategori);

                    //Ürün güncellenecekse id si 0 dan büyüktür bunun id sini getir.
                    if (urunBindObject.getId() != null) {
                        Notification.show(urunBindObject.getAd() + " Adlı ürün güncellendi !");
                    } else {
                        Notification.show(urunBindObject.getAd() + " Adlı ürün eklendi  ! ");
                    }

                    //Gönderilen ürünün id si yoksa ekleme varsa güncelleme işlemi yapılacaktır.
                    if (urunBindObject.getKategorisi() == null || urunBindObject.getAciklama() == null || urunBindObject.getAd() == null) {
                        Notification.show("Lütfen Tüm Alanları Eksiksiz Doldurun !");
                    } else {
                        urunService.addOrUpdate(urunBindObject);
                    }

                    //İşlemden sonra formu temizle
                    urunBindObject = new Urun();
                    binder.setItemDataSource(urunBindObject);
//                    kategorisi.setValue(null);

                    //Güncelleme işlemi yapıldıysa güncellemek için açılan pencereyi kapat
                    UI.getCurrent().getUI().removeWindow(getMyUpdateWindow());

                } catch (Exception exception) {
                    Notification.show(exception.getMessage());
                }


            }
        });
    }

    private void buildBinder() {
        binder = new BeanFieldGroup<Urun>(Urun.class);
        binder.setItemDataSource(urunBindObject);
        binder.bindMemberFields(this);
    }

    private void editUpdateForm(Urun urun) {
        //İşlem ekleme olduğunda fieldlar başlangıçta boş gelecektir.
        //güncelleme işleminde fieldların içerisi dolu gelecektir.

        //ürün id sini görsün ama değiştiremesin sadece okunabilir yap bu alanı.
        idTextField.setVisible(true);
        idTextField.setEnabled(false);

        //Kategori id sini görsün ama comboboxtan değiştirebilsin sadece okunabilir yap bu alanı.
//        id_kategori.setVisible(true);
//        id_kategori.setEnabled(false);

        //ComboBox ta kategori seçili gelmeli
        //Ürün tablosunda kategorinin adı yok, Kategori id siyle Kategori tablosundan bul getir.
        kategorisi.setValue(urun.getKategorisi());
    }

    private void binderCalistir() {
        try {
            binder.commit();
        } catch (FieldGroup.CommitException e) {
            Notification.show("Lütfen form alanlarını eksiksiz doldurun");
        }
    }

    public Window getMyUpdateWindow() {
        return myUpdateWindow;
    }

    public void setMyUpdateWindow(Window myUpdateWindow) {
        this.myUpdateWindow = myUpdateWindow;
    }
}
