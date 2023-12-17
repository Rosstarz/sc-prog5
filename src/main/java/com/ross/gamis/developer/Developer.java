package com.ross.gamis.developer;

import com.ross.gamis.game.Game;

import java.time.LocalDate;
import java.util.List;

public class Developer {
    private int id;
    private String name;
    private LocalDate founded;
    private Country location;
    private List<Game> games;

    public Developer(int id, String name, LocalDate founded, Country location) {
        this.id = id;
        this.name = name;
        this.founded = founded;
        this.location = location;
    }

    public Developer(String name, LocalDate founded, Country location) {
        this.name = name;
        this.founded = founded;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getFounded() {
        return founded;
    }

    public Country getLocation() {
        return location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFounded(LocalDate founded) {
        this.founded = founded;
    }

    public void setLocation(Country location) {
        this.location = location;
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
                ", location=" + location +
                ", games=" + games +
                '}';
    }
}
