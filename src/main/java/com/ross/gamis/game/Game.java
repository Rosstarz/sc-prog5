package com.ross.gamis.game;


import com.ross.gamis.developer.Developer;
import com.ross.gamis.store.Store;

import java.time.LocalDate;
import java.util.List;

public class Game {
    private int id;
    private String title;
    private LocalDate releaseDate;
    private double price;
    private int developerId;
    private boolean owned;
    private int ownedStoreId;
    private Developer developer;
    private List<Store> stores;

    public Game() {
    }

    // TODO TESTING
    public Game(String title) {
        this.title = title;
    }

    public Game(int id, String title, LocalDate releaseDate, double price, int developerId, boolean owned, int ownedStoreId) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.price = price;
        this.developerId = developerId;
        this.owned = owned;
        this.ownedStoreId = ownedStoreId;
    }

    public Game(String title, LocalDate releaseDate, double price, int developerId, boolean owned, int ownedStoreId) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.price = price;
        this.developerId = developerId;
        this.owned = owned;
        this.ownedStoreId = ownedStoreId;
    }

    public Game(String title, LocalDate releaseDate, double price, boolean owned) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.price = price;
        this.owned = owned;
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public double getPrice() {
        return price;
    }

    public int getDeveloperId() {
        return developerId;
    }

    public boolean isOwned() {
        return owned;
    }

    public int getOwnedStoreId() {
        return ownedStoreId;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public List<Store> getStores() {
        return stores;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDeveloperId(int developerId) {
        this.developerId = developerId;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public void setOwnedStoreId(int ownedStoreId) {
        this.ownedStoreId = ownedStoreId;
    }

    public void setDeveloper(Developer developer){
        this.developer = developer;
    }

    public void setStores(List<Store> stores){
        this.stores = stores;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                ", developerId=" + developerId +
                ", owned=" + owned +
                ", ownedStoreId=" + ownedStoreId +
                ", developer=" + developer +
                ", stores=" + stores +
                '}';
    }
}
