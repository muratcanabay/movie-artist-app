package com.uniyaz.imdb.ui.views;

import com.uniyaz.imdb.dao.MovieDao;
import com.uniyaz.imdb.domain.Artist;
import com.uniyaz.imdb.domain.Movie;
import com.uniyaz.imdb.enums.EnumGenre;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class ListMovieView extends VerticalLayout {

    Movie movie;
    private Table table;
    private IndexedContainer indexedContainer;
    private AddMovieView addMovieView;

    public ListMovieView() {
        buildTableContainer();

        buildTable();
        addComponent(table);

        addMovieView = new AddMovieView();
        addComponent(addMovieView);

        fillTable();
    }

    private void buildTableContainer() {
        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("name", String.class, null);
        indexedContainer.addContainerProperty("rate", BigDecimal.class, null);
        indexedContainer.addContainerProperty("releaseDate", Date.class, null);
        indexedContainer.addContainerProperty("genre", EnumGenre.class, null);
        indexedContainer.addContainerProperty("id_artist", Artist.class, null);
    }

    private void buildTable() {
        table = new Table();

        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("ID", "Movie Name", "Rate", "Release Date", "Genre", "Artist Name");
        table.setSelectable(true);

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                movie = (Movie) itemClickEvent.getItemId();

                addMovieView.fillViewByMovie(movie);
            }
        });
    }

    private void fillTable() {
        MovieDao movieDao = new MovieDao();

        List<Movie> movieList = movieDao.findAllMovie();

        for (Movie movie : movieList) {
            Item item = indexedContainer.addItem(movie);
            item.getItemProperty("id").setValue(movie.getId());
            item.getItemProperty("name").setValue(movie.getName());
            item.getItemProperty("rate").setValue(movie.getRate());
            item.getItemProperty("releaseDate").setValue(movie.getRealeaseDate());
            item.getItemProperty("genre").setValue(movie.getGenre());
            item.getItemProperty("id_artist").setValue(movie.getArtist());
        }
    }
}
