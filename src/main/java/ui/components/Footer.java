package ui.components;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Footer extends VerticalLayout {
    private Label labelBaslik;

    public Footer() {
        setWidth(99, Sizeable.Unit.PERCENTAGE);
        setHeight(99, Sizeable.Unit.PIXELS);
        labelBaslik = new Label();
        labelBaslik.setValue("@Copyright Hakan Dereli 2020");
        labelBaslik.addStyleName(ValoTheme.LABEL_H2);
        labelBaslik.addStyleName(ValoTheme.TEXTAREA_ALIGN_CENTER);
        addComponent(labelBaslik);
    }

    public void setFooterTitle(String title) {
        labelBaslik.setValue(title);
    }
}
