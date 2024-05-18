package com.ross.gamis.controller.api.dto;

import java.util.List;

import com.ross.gamis.domain.GameStore;

public class GameDto {
    private long id;
    private String title;
    private String description;
    private DeveloperDto developer;
    private List<GameStoreDto> stores;

    public GameDto() {
    }

    public GameDto(long id, String title, String description, DeveloperDto developer) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.developer = developer;
    }



    public GameDto(long id, String title, String description, List<GameStoreDto> stores) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.stores = stores;
    }



    public GameDto(long id, String title, String description, DeveloperDto developer, List<GameStoreDto> stores) {
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

    public DeveloperDto getDeveloper() {
        return developer;
    }

    public void setDeveloper(DeveloperDto developer) {
        this.developer = developer;
    }

    public List<GameStoreDto> getStores() {
        return stores;
    }

    public void setStores(List<GameStoreDto> stores) {
        this.stores = stores;
    }
}