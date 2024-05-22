package com.ross.gamis.domain;

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
import java.time.LocalDateTime;

@Entity
@Table(name = "user_game_store", uniqueConstraints = {@UniqueConstraint(columnNames = {"game_store_id", "user_id"})})
public class UserGameStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "game_store_id")
    private GameStore gameStore;

    @Column(name = "is_owned", nullable = false)
    private boolean isOwned;

    @Column(name = "owned_set_date", nullable = true)
    private LocalDateTime ownedSetDate;

    // rating

    public UserGameStore() {
    }

    public UserGameStore(User user, GameStore gameStore, boolean isOwned) {
        this.user = user;
        this.gameStore = gameStore;
        this.isOwned = isOwned;
    }

    public UserGameStore(long id, User user, GameStore gameStore, boolean isOwned, LocalDateTime ownedSetDate) {
        this.id = id;
        this.user = user;
        this.gameStore = gameStore;
        this.isOwned = isOwned;
        this.ownedSetDate = ownedSetDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GameStore getGameStore() {
        return gameStore;
    }

    public void setGameStore(GameStore gameStore) {
        this.gameStore = gameStore;
    }

    public boolean isOwned() {
        return isOwned;
    }

    public void setOwned(boolean isOwned) {
        this.isOwned = isOwned;
    }

    public LocalDateTime getOwnedSetDate() {
        return ownedSetDate;
    }

    public void setOwnedSetDate(LocalDateTime ownedSetDate) {
        this.ownedSetDate = ownedSetDate;
    }

    @Override
    public String toString() {
        return "UserGameStore [id=" + id 
        + ", userId=" + user.getId()
        + ", gameStore=" + gameStore.getId()
        + ", isOwned=" + isOwned
        + ", ownedSetDate=" + ownedSetDate 
        + "]";
    }
}
