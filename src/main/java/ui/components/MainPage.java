package ui.components;

import com.vaadin.ui.VerticalLayout;

public class MainPage extends VerticalLayout {
    public MainPage() {
        setWidth("99.2%");
        Header header = new Header();
        addComponent(header);

        Body body = new Body();
        body.setWidth("100%");
        addComponent(body);

        Footer footer = new Footer();
        addComponent(footer);
    }
}
