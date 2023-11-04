package com.ross.gamis.domain;


import java.time.LocalDate;

public class Game {
    private String title;
    private LocalDate release_date;
    private float price;
    private int developer_id;
    private boolean owned;
    private int owned_store_id;

    public Game(String title, LocalDate release_date, float price, int developer_id, boolean owned, int owned_store_id) {
        this.title = title;
        this.release_date = release_date;
        this.price = price;
        this.developer_id = developer_id;
        this.owned = owned;
        this.owned_store_id = owned_store_id;
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public float getPrice() {
        return price;
    }

    public int getDeveloper_id() {
        return developer_id;
    }

    public boolean isOwned() {
        return owned;
    }

    public int getOwned_store_id() {
        return owned_store_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setDeveloper_id(int developer_id) {
        this.developer_id = developer_id;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public void setOwned_store_id(int owned_store_id) {
        this.owned_store_id = owned_store_id;
    }
}
