package com.ross.gamis.controller.api.dto.game.out;

public class GameDtoOutStore {
    long id;
    String name;
    Boolean isLibraryOnline;
    String linkToLibrary;

    public GameDtoOutStore() {
    }

    public GameDtoOutStore(long id, String name, Boolean isLibraryOnline, String linkToLibrary) {
        this.id = id;
        this.name = name;
        this.isLibraryOnline = isLibraryOnline;
        this.linkToLibrary = linkToLibrary;
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
}
