package com.ross.gamis.controller.api.dto.developer;

import java.time.LocalDate;
import java.util.List;

public class DeveloperDtoOut {
    private long id;
    private String name;
    private LocalDate founded;
    private String country;
    private List<Long> gameIds;

    public DeveloperDtoOut() {
    }

    public DeveloperDtoOut(long id, String name, LocalDate founded, String country, List<Long> gameIds) {
        this.id = id;
        this.name = name;
        this.founded = founded;
        this.country = country;
        this.gameIds = gameIds;
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

    public List<Long> getGameIds() {
        return gameIds;
    }

    public void setGameIds(List<Long> gameIds) {
        this.gameIds = gameIds;
    }

    
}