package com.ross.gamis.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity(name="Store")
@Table(name="store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "is_library_online", nullable = false)
    private Boolean isLibraryOnline;

    @Column(name = "link_to_library")
    private String linkToLibrary;

    @OneToMany(mappedBy = "store")
    private List<GameStore> games;

    public Store() {
    }

    public Store(long id, String name, Boolean isLibraryOnline, String linkToLibrary) {
        this.id = id;
        this.name = name;
        this.isLibraryOnline = isLibraryOnline;
        this.linkToLibrary = linkToLibrary;
    }

    public Store(String name, Boolean isLibraryOnline) {
        this.name = name;
        this.isLibraryOnline = isLibraryOnline;
    }

    public Store(String name, Boolean isLibraryOnline, String linkToLibrary) {
        this.name = name;
        this.isLibraryOnline = isLibraryOnline;
        this.linkToLibrary = linkToLibrary;
    }

    public Store(long id, String name, Boolean isLibraryOnline, String linkToLibrary, List<GameStore> games) {
        this.id = id;
        this.name = name;
        this.isLibraryOnline = isLibraryOnline;
        this.linkToLibrary = linkToLibrary;
        this.games = games;
    }
    
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLinkToLibrary() {
        return linkToLibrary;
    }

    public List<GameStore> getGames() {
        return games;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLinkToLibrary(String linkToLibrary) {
        this.linkToLibrary = linkToLibrary;
    }

    public void setGames(List<GameStore> games) {
        this.games = games;
    }

    public Boolean isLibraryOnline() {
        return isLibraryOnline;
    }

    public void setLibraryOnline(Boolean isLibraryOnline) {
        this.isLibraryOnline = isLibraryOnline;
    }

    // @Override
    // public String toString() {
    //     return "Store{" +
    //             "id=" + id +
    //             ", name='" + name + '\'' +
    //             ", isLibraryOnline=" + isLibraryOnline +
    //             ", linkToLibrary='" + linkToLibrary + '\'' +
    //             ", games=" + games +
    //             '}';
    // }
}
