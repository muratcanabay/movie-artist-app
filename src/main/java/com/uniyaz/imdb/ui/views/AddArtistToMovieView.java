package com.uniyaz.imdb.ui.views;

import com.uniyaz.imdb.dao.ArtistDao;
import com.uniyaz.imdb.dao.MovieArtistDao;
import com.uniyaz.imdb.dao.MovieDao;
import com.uniyaz.imdb.domain.Artist;
import com.uniyaz.imdb.domain.Movie;
import com.uniyaz.imdb.domain.MovieArtist;
import com.uniyaz.imdb.ui.components.AddMenuButton;
import com.vaadin.ui.*;

import java.util.List;

public class AddArtistToMovieView extends AddView {

    private FormLayout mainLayout;

    private TextField idField;
    private ComboBox movieComboBox;
    private ComboBox artistComboBox;

    private AddMenuButton addMenuButton;

    public AddArtistToMovieView() {
    }

    @Override
    public void buildMainLayout() {
        mainLayout = new FormLayout();
        addComponent(mainLayout);

        idField = new TextField("Id");
        idField.setEnabled(false);
        mainLayout.addComponent(idField);

        ArtistDao artistDao = new ArtistDao();
        List<Artist> artistList = artistDao.findAllArtist();

        artistComboBox = new ComboBox("Select Artist", artistList);
        mainLayout.addComponent(artistComboBox);

        MovieDao movieDao = new MovieDao();
        List<Movie> movieList = movieDao.findAllMovie();

        movieComboBox = new ComboBox("Select Movie", movieList);
        mainLayout.addComponent(movieComboBox);

        addMenuButton = new AddMenuButton("Add Record");
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
        if (idField.getValue() != "") {
            idFieldValue = Long.parseLong(idField.getValue());
        }

        Artist artist = (Artist) artistComboBox.getValue();
        Movie movie = (Movie) movieComboBox.getValue();

        MovieArtist movieArtist = new MovieArtist();
        movieArtist.setId(idFieldValue);
        movieArtist.setArtist(artist);
        movieArtist.setMovie(movie);

        MovieArtistDao movieArtistDao = new MovieArtistDao();
        movieArtist = movieArtistDao.saveMovieArtist(movieArtist);

        idField.setValue(movieArtist.getId().toString());
        Notification.show("Artist and Movie added succesfully.");
    }

    public void fillViewByArtistToMovie(MovieArtist movieArtist) {
        idField.setValue(movieArtist.getId().toString());
        artistComboBox.setValue(movieArtist.getArtist());
        movieComboBox.setValue(movieArtist.getMovie());
    }
}
