package ui.components;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Header extends VerticalLayout {
    private Label labelBaslik;

    public Header() {
        setWidth(99, Unit.PERCENTAGE);
        setHeight(99, Unit.PIXELS);
        labelBaslik = new Label();
        labelBaslik.setValue("HEADER");
        labelBaslik.addStyleName(ValoTheme.LABEL_H2);
        labelBaslik.addStyleName(ValoTheme.TEXTAREA_ALIGN_CENTER);
        addComponent(labelBaslik);
    }

    public void setHeaderTitle(String title) {
        labelBaslik.setValue(title);
    }
}
