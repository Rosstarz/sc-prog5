package com.ross.gamis.controller.mvc.viewmodel;

import java.util.List;

public class GameViewModel {
    private long id;
    private String title;
    private String description;
    private DeveloperViewModel developer;
    private List<Long> gameStoreIds;

    public GameViewModel() {
    }

    public GameViewModel(long id, String title, String description, DeveloperViewModel developer) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.developer = developer;
    }

    public GameViewModel(long id, String title, String description, DeveloperViewModel developer, List<Long> gameStoreIds) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.developer = developer;
        this.gameStoreIds = gameStoreIds;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DeveloperViewModel getDeveloper() {
        return developer;
    }

    public void setDeveloper(DeveloperViewModel developer) {
        this.developer = developer;
    }

    public List<Long> getGameStores() {
        return gameStoreIds;
    }

    public void setGameStores(List<Long> gameStoreIds) {
        this.gameStoreIds = gameStoreIds;
    }

    
}
