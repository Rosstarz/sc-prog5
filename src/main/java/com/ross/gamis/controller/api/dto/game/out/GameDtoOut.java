package com.ross.gamis.controller.api.dto.game.out;

import java.util.List;

public class GameDtoOut {
    private long id;
    private String title;
    private String description;
    private GameDtoOutDeveloper developer;
    private List<GameDtoOutGameStore> stores;

    public GameDtoOut() {
    }

    public GameDtoOut(long id, String title, String description, GameDtoOutDeveloper developer) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.developer = developer;
    }



    public GameDtoOut(long id, String title, String description, List<GameDtoOutGameStore> stores) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.stores = stores;
    }



    public GameDtoOut(long id, String title, String description, GameDtoOutDeveloper developer, List<GameDtoOutGameStore> stores) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.developer = developer;
        this.stores = stores;
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

    public GameDtoOutDeveloper getDeveloper() {
        return developer;
    }

    public void setDeveloper(GameDtoOutDeveloper developer) {
        this.developer = developer;
    }

    public List<GameDtoOutGameStore> getStores() {
        return stores;
    }

    public void setStores(List<GameDtoOutGameStore> stores) {
        this.stores = stores;
    }
}