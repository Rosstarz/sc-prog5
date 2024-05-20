package com.ross.gamis.controller.api.dto.game.out;

import java.time.LocalDateTime;

public class GameDtoOutGameStore {
    long id;
    GameDtoOutStore store;
    LocalDateTime releaseDate;
    Double price;

    public GameDtoOutGameStore() {
    }

    public GameDtoOutGameStore(long id, GameDtoOutStore store, LocalDateTime releaseDate, Double price) {
        this.id = id;
        this.store = store;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GameDtoOutStore getStore() {
        return store;
    }

    public void setStore(GameDtoOutStore store) {
        this.store = store;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
