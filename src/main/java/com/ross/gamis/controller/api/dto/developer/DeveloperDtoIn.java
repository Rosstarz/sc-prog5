package com.ross.gamis.controller.api.dto.developer;

import com.ross.gamis.domain.Country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class DeveloperDtoIn {
    private long id;

	@NotBlank(message = "Name is required")
	@Size(min = 2, message = "Name must be at least {min} characters")
	@Size(max=255, message = "Name must be less than {max} characters")
    private String name;
    
    private LocalDate founded;
    private Country country;

    public DeveloperDtoIn() {
    }

    public DeveloperDtoIn(long id, String name, LocalDate founded, Country country) {
        this.id = id;
        this.name = name;
        this.founded = founded;
        this.country = country;
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

    
}
