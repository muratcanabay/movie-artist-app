package com.uniyaz.imdb.ui.components;

import com.vaadin.ui.HorizontalLayout;

public class Container extends HorizontalLayout {
    private Header header;

    public Container(Header header) {
        this.header = header;
        setWidth(100, Unit.PERCENTAGE);

        Content content = new Content();
        SideBar sideBar = new SideBar(content);

        addComponent(sideBar);
        addComponent(content);

        setExpandRatio(sideBar, 4f);
        setExpandRatio(content, 6f);
    }
}
