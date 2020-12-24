package com.uniyaz.core.ui.components;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Header extends VerticalLayout {

    private Label imdbLabel;

    public Header() {

        imdbLabel = new Label();
        imdbLabel.setValue("IMDB");
        imdbLabel.addStyleName(ValoTheme.LABEL_HUGE);
        imdbLabel.addStyleName(ValoTheme.TEXTAREA_ALIGN_CENTER);

        addComponent(imdbLabel);

        setWidth(100, Unit.PERCENTAGE);
        setHeight(175, Unit.PIXELS);
    }
}
