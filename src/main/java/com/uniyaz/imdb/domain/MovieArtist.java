package com.uniyaz.imdb.domain;

import com.uniyaz.imdb.common.BaseDomain;

import javax.persistence.*;

@Entity
@Table(name = "MOVIEARTIST")
public class MovieArtist extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "MOVIE_ARTIST_MOVIE_ID"))
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTIST_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "MOVIE_ARTIST_ARTIST_ID"))
    private Artist artist;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
