package com.ross.gamis.controller.api.dto;

import java.util.List;

public class StoreDto {
    long id;
    String name;
    Boolean isLibraryOnline;
    String linkToLibrary;
    List<GameStoreDto> games;

    public StoreDto() {
    }

    public StoreDto(long id, String name, Boolean isLibraryOnline, String linkToLibrary) {
        this.id = id;
        this.name = name;
        this.isLibraryOnline = isLibraryOnline;
        this.linkToLibrary = linkToLibrary;
    }

    public StoreDto(long id, String name, Boolean isLibraryOnline, String linkToLibrary, List<GameStoreDto> games) {
        this.id = id;
        this.name = name;
        this.isLibraryOnline = isLibraryOnline;
        this.linkToLibrary = linkToLibrary;
        this.games = games;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsLibraryOnline() {
        return isLibraryOnline;
    }

    public void setIsLibraryOnline(Boolean isLibraryOnline) {
        this.isLibraryOnline = isLibraryOnline;
    }

    public String getLinkToLibrary() {
        return linkToLibrary;
    }

    public void setLinkToLibrary(String linkToLibrary) {
        this.linkToLibrary = linkToLibrary;
    }

    public List<GameStoreDto> getGames() {
        return games;
    }

    public void setGames(List<GameStoreDto> games) {
        this.games = games;
    }
}
