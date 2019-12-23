package com.uniyaz.imdb.ui.views;

import com.uniyaz.imdb.dao.ArtistDao;
import com.uniyaz.imdb.dao.MovieDao;
import com.uniyaz.imdb.domain.Artist;
import com.uniyaz.imdb.domain.Movie;
import com.uniyaz.imdb.ui.components.AddMenuButton;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

public class AddArtistView extends AddView {

    private FormLayout mainLayout;

    private TextField idField;
    private TextField nameField;
    private TextField surnameField;
    private TextField ageField;
    private ComboBox movieComboBox;

    private AddMenuButton addMenuButton;

    public AddArtistView(){
    }

    @Override
    public void buildMainLayout() {
        mainLayout = new FormLayout();
        addComponent(mainLayout);

        idField = new TextField("Id");
        idField.setEnabled(false);
        idField.setIcon(FontAwesome.USER);
        mainLayout.addComponent(idField);

        nameField = new TextField("Name");
        nameField.setIcon(FontAwesome.ARROW_RIGHT);
        mainLayout.addComponent(nameField);

        surnameField = new TextField("Surname");
        surnameField.setIcon(FontAwesome.ARROW_RIGHT);
        mainLayout.addComponent(surnameField);

        ageField = new TextField("Age");
        ageField.setIcon(FontAwesome.ARROW_RIGHT);
        mainLayout.addComponent(ageField);

        MovieDao movieDao = new MovieDao();
        List<Movie> movieList = movieDao.findAllMovie();
        movieComboBox = new ComboBox("Choose Movie",movieList);
        movieComboBox.setIcon(FontAwesome.CAMERA_RETRO);
        mainLayout.addComponent(movieComboBox);

        String caption = "Add Artist";
        addMenuButton = new AddMenuButton(caption);
        addMenuButton.setIcon(FontAwesome.CHECK);
        addMenuButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
        addMenuButton.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_RIGHT);
        addMenuButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                saveView();
            }
        });

        mainLayout.addComponent(addMenuButton);
    }

    @Override
    public void saveView() {
        Long idFieldValue = null;
        if (idField.getValue() != ""){
            idFieldValue = Long.parseLong(idField.getValue());
        }

        String nameFieldValue = nameField.getValue();
        String surnameFieldValue = surnameField.getValue();
        Long ageFieldValue = Long.parseLong(ageField.getValue());


        Artist artist = new Artist();
        artist.setId(idFieldValue);
        artist.setName(nameFieldValue);
        artist.setSurname(surnameFieldValue);
        artist.setAge(ageFieldValue);

        ArtistDao artistDao = new ArtistDao();
        artist = artistDao.saveArtist(artist);

        idField.setValue(artist.getId().toString());
        Notification.show("Artist added successfully!");
    }

    public void fillViewByArtist(Artist artist){
        idField.setValue(artist.getId().toString());
        nameField.setValue(artist.getName());
        surnameField.setValue(artist.getSurname());
        ageField.setValue(artist.getAge().toString());
        movieComboBox.setValue(artist.getMovie());
    }
}
