package com.ross.gamis.controller.api.dto.game.out;

import java.time.LocalDate;
// import java.util.List;

public class GameDtoOutDeveloper {
    long developerId;
    String name;
    LocalDate founded;
    String country;

    public GameDtoOutDeveloper() {
    }

    public GameDtoOutDeveloper(long developerId, String name, LocalDate founded, String country) {
        this.developerId = developerId;
        this.name = name;
        this.founded = founded;
        this.country = country;
    }

    public long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(long developerId) {
        this.developerId = developerId;
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
}
