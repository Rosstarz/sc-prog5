package com.ross.gamis.controller.mvc.viewmodel;

// import java.util.List;

public class StoreViewModel {
    private long id;
    private String name;
    private Boolean isLibraryOnline;
    private String linkToLibrary;
    // private List<GameStoreViewModel> games;

    public StoreViewModel() {
    }

    public StoreViewModel(long id, String name, Boolean isLibraryOnline, String linkToLibrary) {
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

    // public List<GameStoreViewModel> getGames() {
    //     return games;
    // }

    // public void setGames(List<GameStoreViewModel> games) {
    //     this.games = games;
    // }

    
}
