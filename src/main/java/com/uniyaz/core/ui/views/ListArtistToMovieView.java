package com.uniyaz.core.ui.views;

import com.uniyaz.core.dao.MovieArtistDao;
import com.uniyaz.core.domain.Artist;
import com.uniyaz.core.domain.Movie;
import com.uniyaz.core.domain.MovieArtist;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class ListArtistToMovieView extends VerticalLayout {
    private Table table;
    private IndexedContainer indexedContainer;
    private AddArtistToMovieView addArtistToMovieView;
    private MovieArtist movieArtist;

    public ListArtistToMovieView() {
        buildTableContainer();

        buildTable();
        addComponent(table);

        addArtistToMovieView = new AddArtistToMovieView();
        addComponent(addArtistToMovieView);

        fillTable();
    }

    private void buildTableContainer() {
        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("id_artist", Artist.class, null);
        indexedContainer.addContainerProperty("id_movie", Movie.class, null);
    }

    private void buildTable() {
        table = new Table();

        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("ID", "Artist Name", "Movie Name");
        table.setSelectable(true);

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                movieArtist = (MovieArtist) itemClickEvent.getItemId();
                addArtistToMovieView.fillViewByArtistToMovie(movieArtist);
            }
        });
    }

    private void fillTable() {
        MovieArtistDao movieArtistDao = new MovieArtistDao();

        List<MovieArtist> movieArtistList = movieArtistDao.findAllMovieArtist();

        for (MovieArtist movieArtist : movieArtistList) {
            Item item = indexedContainer.addItem(movieArtist);
            item.getItemProperty("id").setValue(movieArtist.getId());
            item.getItemProperty("id_artist").setValue(movieArtist.getArtist());
            item.getItemProperty("id_movie").setValue(movieArtist.getMovie());
        }
    }

}
