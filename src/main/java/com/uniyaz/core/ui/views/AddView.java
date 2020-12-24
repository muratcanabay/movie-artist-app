package com.uniyaz.core.ui.views;

import com.vaadin.ui.VerticalLayout;

public abstract class AddView extends VerticalLayout {
    public AddView() {
        buildMainLayout();
    }

    public abstract void buildMainLayout();

    public abstract void saveView();
}
