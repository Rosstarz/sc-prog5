package com.ross.gamis.controller.mvc.viewmodel;

import java.time.LocalDate;
import java.util.List;

public class DeveloperViewModel {
    private long id;
    private String name;
    private LocalDate founded;
    private String country;
    private List<GameViewModel> games;

    public DeveloperViewModel() {
    }

    public DeveloperViewModel(long id, String name, LocalDate founded, String country) {
        this.id = id;
        this.name = name;
        this.founded = founded;
        this.country = country;
    }



    public DeveloperViewModel(long id, String name, LocalDate founded, String country, List<GameViewModel> games) {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<GameViewModel> getGames() {
        return games;
    }

    public void setGames(List<GameViewModel> games) {
        this.games = games;
    }

    
}
