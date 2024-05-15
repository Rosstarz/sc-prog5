package com.ross.gamis.domain;

import jakarta.persistence.*;
import java.util.List;

@Entity(name="Game")
@Table(name="game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "developer_id")
    private Developer developer;

    @OneToMany(mappedBy = "game")
    private List<GameStore> stores;

    public Game() {
    }

    public Game(String title, Developer developer) {
        this.title = title;
        this.developer = developer;
    }

    public Game(String title, String description, Developer developer, List<GameStore> stores) {
        this.title = title;
        this.description = description;
        this.developer = developer;
        this.stores = stores;
    }

    public Game(long id, String title, String description, Developer developer, List<GameStore> stores) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.developer = developer;
        this.stores = stores;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public List<GameStore> getStores() {
        return stores;
    }

    public void setStores(List<GameStore> stores) {
        this.stores = stores;
    }

    
    // @Override
    // public String toString() {
    //     return "Game{" +
    //             "id=" + id +
    //             ", title='" + title + '\'' +
    //             ", releaseDate=" + releaseDate +
    //             ", price=" + price +
    //             ", developerId=" + developerId +
    //             ", owned=" + owned +
    //             ", ownedStoreId=" + ownedStoreId +
    //             ", stores=" + stores +
    //             '}';
    // }
}
