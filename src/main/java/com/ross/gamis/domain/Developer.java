package com.ross.gamis.domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "developer")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column
    private LocalDate founded;

    @Column
    @Enumerated(EnumType.STRING)
    private Country country;

    @OneToMany(mappedBy = "developer")
    private List<Game> games;

    public Developer() {
    }

    public Developer(String name) {
        this.name = name;
    }

    public Developer(String name, List<Game> games) {
        this.name = name;
        this.games = games;
    }

    public Developer(String name, LocalDate founded, Country country) {
        this.name = name;
        this.founded = founded;
        this.country = country;
    }

    public Developer(String name, LocalDate founded, Country country, List<Game> games) {
        this.name = name;
        this.founded = founded;
        this.country = country;
        this.games = games;
    }

    public Developer(long id, String name, LocalDate founded, Country country, List<Game> games) {
        this.id = id;
        this.name = name;
        this.founded = founded;
        this.country = country;
        this.games = games;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getFounded() {
        return founded;
    }

    public void setFounded(LocalDate founded) {
        this.founded = founded;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", founded=" + founded +
                ", country=" + country +
                ", games=" + games +
                '}';
    }
}
