package ui.components;

import com.vaadin.ui.VerticalLayout;
import ui.views.SidebarPage;

public class Sidebar extends VerticalLayout {
    private Content content;

    public Sidebar(Content content) {
        this.content = content;

        SidebarPage sidebarPage = new SidebarPage(content);
        addComponent(sidebarPage);
    }

}
