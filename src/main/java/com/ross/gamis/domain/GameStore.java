package com.ross.gamis.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "game_store", uniqueConstraints = {@UniqueConstraint(columnNames = {"game_id", "store_id"})})
public class GameStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "release_date", nullable = false)
    private LocalDateTime releaseDate;

    @Column(nullable = false)
    private Double price;

    // @Column(name = "is_owned")
    // private boolean isOwned;

    public GameStore() {
    }

    public GameStore(Game game, Store store, LocalDateTime releaseDate, Double price) {
        this.game = game;
        this.store = store;
        this.releaseDate = releaseDate;
        this.price = price;
    }

    // public GameStore(Game game, Store store, LocalDateTime releaseDate, Double price, boolean isOwned) {
    //     this.game = game;
    //     this.store = store;
    //     this.releaseDate = releaseDate;
    //     this.price = price;
    //     this.isOwned = isOwned;
    // }

    public GameStore(long id, Game game, Store store, LocalDateTime releaseDate, Double price) {
        this.id = id;
        this.game = game;
        this.store = store;
        this.releaseDate = releaseDate;
        this.price = price;
        // this.isOwned = isOwned;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
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

    // public boolean isOwned() {
    //     return isOwned;
    // }

    // public void setOwned(boolean isOwned) {
    //     this.isOwned = isOwned;
    // }

    
}
