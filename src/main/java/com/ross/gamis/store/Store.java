package com.ross.gamis.store;

import com.ross.gamis.game.Game;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity(name="Store")
@Table(name="store")
public class Store {
    @Id
    @SequenceGenerator(
            name = "date_sequence",
            sequenceName = "data_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "data_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    private String name;
    private Boolean isLibraryOnline;
    private String linkToLibrary;
//    private List<Game> games;

    @ManyToMany
    @JoinTable(
            name = "games_stores",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id"))
    private Set<Game> games;

    public Store() {
    }

    public Store(Long id, String name, boolean isLibraryOnline, String linkToLibrary) {
        this.id = id;
        this.name = name;
        this.isLibraryOnline = isLibraryOnline;
        this.linkToLibrary = linkToLibrary;
    }

    public Store(String name, boolean isLibraryOnline, String linkToLibrary) {
        this.name = name;
        this.isLibraryOnline = isLibraryOnline;
        this.linkToLibrary = linkToLibrary;
    }

    public Store(Long id, String name, Boolean isLibraryOnline, String linkToLibrary, Set<Game> games) {
        this.id = id;
        this.name = name;
        this.isLibraryOnline = isLibraryOnline;
        this.linkToLibrary = linkToLibrary;
        this.games = games;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean isLibraryOnline() {
        return isLibraryOnline;
    }

    public String getLinkToLibrary() {
        return linkToLibrary;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLibraryOnline(Boolean libraryOnline) {
        isLibraryOnline = libraryOnline;
    }

    public void setLinkToLibrary(String linkToLibrary) {
        this.linkToLibrary = linkToLibrary;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isLibraryOnline=" + isLibraryOnline +
                ", linkToLibrary='" + linkToLibrary + '\'' +
                ", games=" + games +
                '}';
    }
}
