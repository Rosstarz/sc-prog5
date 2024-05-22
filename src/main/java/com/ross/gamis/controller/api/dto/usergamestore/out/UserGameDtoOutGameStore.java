package com.ross.gamis.controller.api.dto.usergamestore.out;

public class UserGameDtoOutGameStore {
    private long id;
    private long gameId;
    private long storeId;
    
    public UserGameDtoOutGameStore() {
    }

    public UserGameDtoOutGameStore(long id, long gameId, long storeId) {
        this.id = id;
        this.gameId = gameId;
        this.storeId = storeId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "UserGameDtoOutGameStore [id=" + id + ", gameId=" + gameId + ", storeId=" + storeId + "]";
    }
}
