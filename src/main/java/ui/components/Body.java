package ui.components;

import com.vaadin.ui.HorizontalLayout;

public class Body extends HorizontalLayout {
    public Body() {
        Content content = new Content();

        Sidebar sidebar = new Sidebar(content);

        addComponent(sidebar);
        addComponent(content);

        setExpandRatio(sidebar, 3f);
        setExpandRatio(content, 7f);
    }
}
