package com.ross.gamis.controller.api.dto;

import java.time.LocalDateTime;

public class GameStoreDto {
    long id;
    GameDto game;
    StoreDto store;
    long storeId;
    LocalDateTime releaseDate;
    Double price;

    public GameStoreDto() {
    }

    public GameStoreDto(long id, StoreDto store, LocalDateTime releaseDate, Double price) {
        this.id = id;
        this.store = store;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public GameStoreDto(long id, long storeId, LocalDateTime releaseDate, Double price) {
        this.id = id;
        this.storeId = storeId;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public GameStoreDto(long id, GameDto game, LocalDateTime releaseDate, Double price) {
        this.id = id;
        this.game = game;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    public GameStoreDto(long id, GameDto game, StoreDto store, LocalDateTime releaseDate, Double price) {
        this.id = id;
        this.game = game;
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

    public GameDto getGame() {
        return game;
    }

    public void setGame(GameDto game) {
        this.game = game;
    }

    public StoreDto getStore() {
        return store;
    }

    public void setStore(StoreDto store) {
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

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }
}
