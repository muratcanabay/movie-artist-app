package com.uniyaz.imdb.ui.views;

import com.uniyaz.imdb.dao.ArtistDao;
import com.uniyaz.imdb.dao.MovieDao;
import com.uniyaz.imdb.domain.Artist;
import com.uniyaz.imdb.domain.Movie;
import com.uniyaz.imdb.enums.EnumGenre;
import com.uniyaz.imdb.ui.components.AddMenuButton;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AddMovieView extends AddView {

    private FormLayout mainLayout;
    private TextField idField;
    private TextField nameField;
    private TextField rateField;
    private DateField releaseDateField;
    private ComboBox genreComboBox;
    private ComboBox artistComboBox;
    private AddMenuButton addMenuButton;

    public AddMovieView() {
    }

    @Override
    public void buildMainLayout() {

        mainLayout = new FormLayout();
        addComponent(mainLayout);

        idField = new TextField("Id");
        idField.setEnabled(false);
        idField.setIcon(FontAwesome.USER);
        mainLayout.addComponent(idField);

        nameField = new TextField("Movie Name");
        nameField.setIcon(FontAwesome.FILM);
        mainLayout.addComponent(nameField);

        rateField = new TextField("Rate");
        rateField.setIcon(FontAwesome.STAR);
        mainLayout.addComponent(rateField);

        releaseDateField = new DateField("ReleaseDate");
        releaseDateField.setIcon(FontAwesome.CALENDAR);
        addComponent(releaseDateField);

        List<EnumGenre> genreList = new ArrayList();
        genreList.addAll(Arrays.asList(EnumGenre.values()));

        genreComboBox = new ComboBox("Choose A Genre", genreList);
        genreComboBox.setIcon(FontAwesome.ALIGN_JUSTIFY);
        mainLayout.addComponent(genreComboBox);

        ArtistDao artistDao = new ArtistDao();
        List<Artist> artistList = artistDao.findAllArtist();
        artistComboBox = new ComboBox("Choose Artist", artistList);
        artistComboBox.setIcon(FontAwesome.USER_SECRET);
        mainLayout.addComponent(artistComboBox);

        String caption = "Add Movie";
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

    public void saveView() {
        Long idFieldValue = null;

        if (idField.getValue() != "") {
            idFieldValue = Long.parseLong(idField.getValue());
        }

        String nameFieldValue = nameField.getValue();
        BigDecimal rateFieldValue = new BigDecimal(rateField.getValue());
        Date dateFieldValue = releaseDateField.getValue();
        EnumGenre genre = (EnumGenre) genreComboBox.getValue();
        Artist artist = (Artist) artistComboBox.getValue();

        Movie movie = new Movie();
        movie.setId(idFieldValue);
        movie.setName(nameFieldValue);
        movie.setRate(rateFieldValue);
        movie.setRealeaseDate(dateFieldValue);
        movie.setGenre(genre);
        movie.setArtist(artist);

        MovieDao movieDao = new MovieDao();
        movie = movieDao.saveMovie(movie);

        idField.setValue(movie.getId().toString());
        Notification.show("Movie added successfully!");
    }

    public void fillViewByMovie(Movie movie) {
        idField.setValue(movie.getId().toString());
        nameField.setValue(movie.getName());
        rateField.setValue(movie.getRate().toString());
        releaseDateField.setValue(movie.getRealeaseDate());
        genreComboBox.setValue(movie.getGenre());
        artistComboBox.setValue(movie.getArtist());
    }
}
