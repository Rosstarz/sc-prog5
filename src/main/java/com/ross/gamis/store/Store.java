package com.ross.gamis.store;

import com.ross.gamis.game.Game;

import java.util.List;

public class Store {
    private int id;
    private String name;
    private boolean isLibraryOnline;
    private String linkToLibrary;
    private List<Game> games;

    public Store(int id, String name, boolean isLibraryOnline, String linkToLibrary) {
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isLibraryOnline() {
        return isLibraryOnline;
    }

    public String getLinkToLibrary() {
        return linkToLibrary;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLibraryOnline(boolean libraryOnline) {
        isLibraryOnline = libraryOnline;
    }

    public void setLinkToLibrary(String linkToLibrary) {
        this.linkToLibrary = linkToLibrary;
    }

    public void setGames(List<Game> games) {
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
