package com.ross.gamis.game;


import com.ross.gamis.developer.Developer;
import com.ross.gamis.store.Store;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity(name="Game")
@Table(name="games")
public class Game {
    @Id
    @SequenceGenerator(
            name = "date_sequence",
            sequenceName = "data_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "data_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;
    private String title;
    private LocalDate releaseDate;
    private Double price;
    private Long developerId;
    private Boolean owned;
    private Long ownedStoreId;
//    private Developer developer;
//    private List<Store> stores;

    @ManyToMany(mappedBy = "games")
    private Set<Store> stores;

    public Game() {
    }

    // TODO TESTING
    public Game(String title) {
        this.title = title;
    }

    public Game(Long id, String title, LocalDate releaseDate, Double price, Long developerId, Boolean owned, Long ownedStoreId) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.price = price;
        this.developerId = developerId;
        this.owned = owned;
        this.ownedStoreId = ownedStoreId;
    }

    public Game(String title, LocalDate releaseDate, Double price, Long developerId, Boolean owned, Long ownedStoreId) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.price = price;
        this.developerId = developerId;
        this.owned = owned;
        this.ownedStoreId = ownedStoreId;
    }

    public Game(String title, LocalDate releaseDate, Double price, Boolean owned) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.price = price;
        this.owned = owned;
    }

    public Game(Long id, String title, LocalDate releaseDate, Double price, Long developerId, Boolean owned, Long ownedStoreId, Set<Store> stores) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.price = price;
        this.developerId = developerId;
        this.owned = owned;
        this.ownedStoreId = ownedStoreId;
        this.stores = stores;
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Double getPrice() {
        return price;
    }

    public Long getDeveloperId() {
        return developerId;
    }

    public Boolean getOwned() {
        return owned;
    }

    public Long getOwnedStoreId() {
        return ownedStoreId;
    }

    public Set<Store> getStores() {
        return stores;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    public void setOwned(Boolean owned) {
        this.owned = owned;
    }

    public void setOwnedStoreId(Long ownedStoreId) {
        this.ownedStoreId = ownedStoreId;
    }

    public void setStores(Set<Store> stores) {
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
                ", stores=" + stores +
                '}';
    }
}
