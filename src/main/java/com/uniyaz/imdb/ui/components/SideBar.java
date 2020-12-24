package com.uniyaz.imdb.ui.components;

import com.uniyaz.imdb.ui.views.*;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class SideBar extends VerticalLayout {

    private Content content;

    private AddMenuButton addMovieButton;
    private Button listMovieButton;

    private AddMenuButton addArtistButton;
    private Button listArtistButton;

    private AddMenuButton addArtistToMovieButton;
    private Button listArtistToMovieButton;

    private Button listOrderByRateButton;

    public SideBar(Content content) {

        this.content = content;

        setSpacing(true);
        setMargin(true);

        buildAddMovieMenuButton();
        addComponent(addMovieButton);

        buildListMovieButton();
        addComponent(listMovieButton);

        buildAddArtistMenuButton();
        addComponent(addArtistButton);

        buildListArtistButton();
        addComponent(listArtistButton);

        buildAddArtistToMovieMenuButton();
        addComponent(addArtistToMovieButton);

        buildListArtistToMovieMenuButton();
        addComponent(listArtistToMovieButton);

        buildListOrderByRateMenuButton();
        addComponent(listOrderByRateButton);

    }

    private void buildListArtistToMovieMenuButton() {
        String caption = "List Artist Movies";
        listArtistToMovieButton = new Button(caption);
        listArtistToMovieButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        listArtistToMovieButton.setIcon(FontAwesome.LIST);
        listArtistToMovieButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ListArtistToMovieView listArtistToMovieView = new ListArtistToMovieView();
                content.setContent(listArtistToMovieView);
            }
        });
    }

    private void buildListArtistButton() {
        String caption = "List Artist";
        listArtistButton = new Button(caption);
        listArtistButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        listArtistButton.setIcon(FontAwesome.LIST);
        listArtistButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ListArtistView listArtistView = new ListArtistView();
                content.setContent(listArtistView);
            }
        });
    }

    private void buildAddMovieMenuButton() {
        String caption = "Add Movie";
        addMovieButton = new AddMenuButton(caption);
        addMovieButton.addStyleName(ValoTheme.BUTTON_DANGER);
        addMovieButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AddMovieView addMovieView = new AddMovieView();
                content.setContent(addMovieView);
            }
        });
    }

    private void buildListMovieButton() {
        String caption = "List Movie";
        listMovieButton = new Button(caption);
        listMovieButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        listMovieButton.setIcon(FontAwesome.LIST);
        listMovieButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ListMovieView listMovieView = new ListMovieView();
                content.setContent(listMovieView);
            }
        });

    }

    private void buildAddArtistMenuButton() {
        String caption = "Add Artist";
        addArtistButton = new AddMenuButton(caption);
        addArtistButton.addStyleName(ValoTheme.BUTTON_DANGER);
        addArtistButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AddArtistView addArtistView = new AddArtistView();
                content.setContent(addArtistView);
            }
        });
    }

    private void buildAddArtistToMovieMenuButton() {
        String caption = "Add Artist To Movie";
        addArtistToMovieButton = new AddMenuButton(caption);
        addArtistToMovieButton.addStyleName(ValoTheme.BUTTON_DANGER);
        addArtistToMovieButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AddArtistToMovieView addArtistToMovieView = new AddArtistToMovieView();
                content.setContent(addArtistToMovieView);
            }
        });
    }

    private void buildListOrderByRateMenuButton() {
        String caption = "List Movie By Rate";
        listOrderByRateButton = new Button(caption);
        listOrderByRateButton.setIcon(FontAwesome.SORT_NUMERIC_DESC);
        listOrderByRateButton.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        listOrderByRateButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ListMovieOrderByRateView listMovieOrderByRateView = new ListMovieOrderByRateView();
                content.setContent(listMovieOrderByRateView);
            }
        });
    }
}
