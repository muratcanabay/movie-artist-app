package io.github.muratcanabay.core.ui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;

public class AddMenuButton extends Button {
    public AddMenuButton(String caption) {
        setIcon(FontAwesome.PLUS_CIRCLE);
        setCaption(caption);
    }
}
