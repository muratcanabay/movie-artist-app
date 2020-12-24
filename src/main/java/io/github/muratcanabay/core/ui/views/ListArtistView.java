package io.github.muratcanabay.core.ui.views;

import io.github.muratcanabay.core.dao.ArtistDao;
import io.github.muratcanabay.core.domain.Artist;
import io.github.muratcanabay.core.domain.Movie;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class ListArtistView extends VerticalLayout {
    Artist artist;
    private Table table;
    private IndexedContainer indexedContainer;
    private AddArtistView addArtistView;

    public ListArtistView() {
        buildTableContainer();

        buildTable();
        addComponent(table);

        addArtistView = new AddArtistView();
        addComponent(addArtistView);

        fillTable();
    }

    private void buildTableContainer() {
        indexedContainer = new IndexedContainer();
        indexedContainer.addContainerProperty("id", Long.class, null);
        indexedContainer.addContainerProperty("name", String.class, null);
        indexedContainer.addContainerProperty("surname", String.class, null);
        indexedContainer.addContainerProperty("age", Long.class, null);
        indexedContainer.addContainerProperty("id_movie", Movie.class, null);
    }

    private void buildTable() {
        table = new Table();

        table.setContainerDataSource(indexedContainer);
        table.setColumnHeaders("ID", "Artist Name", "Artist Surname", "Age", "Movie Name");
        table.setSelectable(true);

        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                artist = (Artist) itemClickEvent.getItemId();
                addArtistView.fillViewByArtist(artist);
            }
        });
    }

    private void fillTable() {
        ArtistDao artistDao = new ArtistDao();

        List<Artist> artistList = artistDao.findAllArtist();

        for (Artist artist : artistList) {
            Item item = indexedContainer.addItem(artist);
            item.getItemProperty("id").setValue(artist.getId());
            item.getItemProperty("name").setValue(artist.getName());
            item.getItemProperty("surname").setValue(artist.getSurname());
            item.getItemProperty("age").setValue(artist.getAge());
            item.getItemProperty("id_movie").setValue(artist.getMovie());
        }
    }
}
