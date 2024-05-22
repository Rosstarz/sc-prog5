package com.ross.gamis.controller.api.dto.user;

import java.util.List;

public class UserDtoOut {
    private long userId;
    private String username;
    private String role;
    private List<Long> gameStoreIds;

    public UserDtoOut() {
    }

    public UserDtoOut(long userId, String username, String role, List<Long> gameStoreIds) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.gameStoreIds = gameStoreIds;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Long> getGameStoreIds() {
        return gameStoreIds;
    }

    public void setGameStoreIds(List<Long> gameStoreIds) {
        this.gameStoreIds = gameStoreIds;
    }

    
}
