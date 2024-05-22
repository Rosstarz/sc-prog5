package com.ross.gamis.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity(name="User")
@Table(name="app_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<UserGameStore> gameStores;

    public User() {
    }

    public User(String username, String password, UserRole role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, UserRole role, List<UserGameStore> gameStores) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.gameStores = gameStores;
    }

    public User(long id, String username, String password, UserRole role, List<UserGameStore> gameStores) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.gameStores = gameStores;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public List<UserGameStore> getGameStores() {
        return gameStores;
    }

    public void setGameStores(List<UserGameStore> gameStores) {
        this.gameStores = gameStores;
    }
}
