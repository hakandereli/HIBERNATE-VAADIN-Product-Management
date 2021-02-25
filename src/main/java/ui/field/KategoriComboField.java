package ui.field;

import com.vaadin.ui.ComboBox;
import core.domain.Kategori;
import core.service.KategoriService;

import java.util.List;

public class KategoriComboField extends ComboBox {
    private KategoriService kategoriService;

    public KategoriComboField() {
        this.kategoriService = new KategoriService();
        this.setDescription("Kategori Seçin !");
        fillComboBox();
    }

    public void fillComboBox() {
        this.removeAllItems();
        List<Kategori> kategoriList = kategoriService.findAll();
        for (Kategori kategori : kategoriList) {
            this.addItem(kategori);
        }
    }
}