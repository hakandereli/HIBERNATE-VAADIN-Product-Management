package ui.views;

import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import core.domain.Urun;
import core.service.UrunService;

import java.util.List;

public class UrunListingPage extends VerticalLayout {
    Table table;
    IndexedContainer indexedContainer;

    public UrunListingPage() {
        buildTableIndexedContainer();
        buildTable();
        fillTable();
        addComponent(new Label("<h3>Ürün Tablosu</h3>", ContentMode.HTML));
        addComponent(table);
    }

    private void buildTableIndexedContainer() {

        indexedContainer = new IndexedContainer();

        indexedContainer.addContainerProperty("guncelle", Button.class, null);
        indexedContainer.addContainerProperty("sil", Button.class, null);
        indexedContainer.addContainerProperty("izle", Button.class, null);

        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("id_kategori", Long.class, null);
        indexedContainer.addContainerProperty("ad", String.class, null);
        indexedContainer.addContainerProperty("aciklama", String.class, null);

    }

    private void buildTable() {
        table = new Table();
        table.setContainerDataSource(indexedContainer);

        table.setWidth("100%");
        table.setSelectable(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setMultiSelect(false);
        //Tablo Başlıkları
        table.setColumnHeaders("Güncelle", "Sil", "Göster", "ID", "ID Kategori", "Ad", "Açıklama");

        table.setColumnCollapsingAllowed(true);
        table.setColumnCollapsed("id", true);
        table.setColumnCollapsed("id_kategori", true);
        table.setColumnCollapsed("aciklama", true);

        table.setColumnWidth("guncelle", 80);
        table.setColumnAlignment("guncelle", Table.Align.LEFT);
        table.setColumnWidth("sil", 80);
        table.setColumnAlignment("sil", Table.Align.LEFT);
        table.setColumnWidth("izle", 80);
        table.setColumnAlignment("izle", Table.Align.LEFT);
    }

    private void fillTable() {
        indexedContainer.removeAllItems();
        UrunService urunService = new UrunService();
        List<Urun> urunList = urunService.findAll();

        for (Urun urun : urunList) {
            Item item = indexedContainer.addItem(urun);

            //Ürün Güncelleme Button ve Fonksiyon
            Button editButton = new Button();
            urunGuncelle(urun, editButton);

            //Ürün Silme Button ve Fonksiyon
            Button trashButton = new Button();
            urunSil(urun, trashButton);

            //Ürün Detay Göster Button ve Fonksiyon
            Button infoButton = new Button();
            urunDetayGoster(urun, infoButton);

            //Kolonları Doldurma Fonksiyon
            fillColumns(urun, item, editButton, trashButton, infoButton);
        }

    }

    private void fillColumns(Urun urun, Item item, Button editButton, Button trashButton, Button infoButton) {
        item.getItemProperty("guncelle").setValue(editButton);
        item.getItemProperty("sil").setValue(trashButton);
        item.getItemProperty("izle").setValue(infoButton);

        item.getItemProperty("id").setValue(urun.getId());
        item.getItemProperty("id_kategori").setValue(urun.getKategorisi().getId());
        item.getItemProperty("ad").setValue(urun.getAd());
        item.getItemProperty("aciklama").setValue(urun.getAciklama());
    }

    private void urunGuncelle(Urun urun, Button editButton) {
        editButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        editButton.setIcon(FontAwesome.EDIT);

        editButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Window updateWindow = new Window("Ürün Güncelleme");

                UrunAddUpdatePage selectedUrunUpdateWindow = new UrunAddUpdatePage(urun);
                selectedUrunUpdateWindow.setMyUpdateWindow(updateWindow);
                selectedUrunUpdateWindow.setMargin(true);

                updateWindow.setContent(selectedUrunUpdateWindow);
                updateWindow.center();
                updateWindow.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent closeEvent) {
                        fillTable();
                    }
                });
                UI.getCurrent().addWindow(updateWindow);
            }
        });
    }

    private void urunSil(Urun urun, Button trashButton) {
        trashButton.setIcon(FontAwesome.TRASH);
        trashButton.addStyleName(ValoTheme.BUTTON_DANGER);

        trashButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Window deleteWindow = new Window("Ürün Silme");
                deleteWindow.setWidth("25%");
                deleteWindow.setHeight("200px");

                UrunDeletePage selectedUrunDeletePage = new UrunDeletePage(urun);
                selectedUrunDeletePage.setMyDeleteWindow(deleteWindow);
                selectedUrunDeletePage.setMargin(true);

                deleteWindow.setContent(selectedUrunDeletePage);
                deleteWindow.center();
                deleteWindow.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent closeEvent) {
                        fillTable();
                    }
                });
                UI.getCurrent().addWindow(deleteWindow);
            }
        });
    }

    private void urunDetayGoster(Urun urun, Button infoButton) {
        infoButton.setIcon(FontAwesome.INFO);

        infoButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Window displayWindow = new Window("Ürün Görüntüleme");

                UrunDisplayPage selectedUrunDisplayPage = new UrunDisplayPage(urun);
                selectedUrunDisplayPage.setMyDisplayWindow(displayWindow);
                selectedUrunDisplayPage.setMargin(true);

                displayWindow.setContent(selectedUrunDisplayPage);
                displayWindow.center();
                UI.getCurrent().addWindow(displayWindow);
            }
        });
    }
}
