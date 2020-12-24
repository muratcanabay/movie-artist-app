package com.uniyaz.core.domain;

import com.uniyaz.core.common.BaseDomain;
import com.uniyaz.core.enums.EnumGenre;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "MOVIE")
public class Movie extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(length = 50)
    @Size(max = 50)
    private String name;

    @NotNull
    private BigDecimal rate;

    @Temporal(TemporalType.DATE)
    private Date realeaseDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private EnumGenre genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ARTIST_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "MOVIE_ARTIST_ID"))
    private Artist artist;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Date getRealeaseDate() {
        return realeaseDate;
    }

    public void setRealeaseDate(Date realeaseDate) {
        this.realeaseDate = realeaseDate;
    }

    public EnumGenre getGenre() {
        return genre;
    }

    public void setGenre(EnumGenre genre) {
        this.genre = genre;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String toString() {
        return name;
    }
}