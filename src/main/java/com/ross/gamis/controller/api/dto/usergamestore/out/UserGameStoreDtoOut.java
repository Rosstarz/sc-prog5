package com.ross.gamis.controller.api.dto.usergamestore.out;

import java.time.LocalDateTime;
import java.util.List;


public class UserGameStoreDtoOut {
    private long id;
    private long userId;
    private UserGameDtoOutGameStore gameStore;
    private boolean isOwned;
    private LocalDateTime ownedSetDate;

    public UserGameStoreDtoOut() {
    }

    public UserGameStoreDtoOut(long id, long userId, UserGameDtoOutGameStore gameStore, boolean isOwned, LocalDateTime ownedSetDate) {
        this.id = id;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public UserGameDtoOutGameStore getGameStore() {
        return gameStore;
    }

    public void setGameStore(UserGameDtoOutGameStore gameStore) {
        this.gameStore = gameStore;
    }

    @Override
    public String toString() {
        return "UserGameStoreDtoOut [id=" + id 
        + ", userId=" + userId 
        + ", gameStoreId=" + gameStore.getId() 
        + ", gameId=" + gameStore.getGameId() 
        + ", isOwned=" + isOwned 
        + ", ownedSetDate=" + ownedSetDate 
        + "]";
    }
}
