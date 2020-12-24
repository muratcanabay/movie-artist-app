package com.uniyaz.imdb.domain;

import com.uniyaz.imdb.common.BaseDomain;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "ARTIST")
public class Artist extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 30)
    @Size(max = 30)
    private String name;

    @Column(length = 30)
    @Size(max = 30)
    private String surname;

    @Column(length = 3)
    @Size(max = 3)
    private Long age;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_MOVIE", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "ARTIST_MOVIE_ID"))
    private Movie movie;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String toString() {
        return name + " " + surname;
    }
}
