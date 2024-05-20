package com.ross.gamis.converter;

import com.ross.gamis.controller.api.dto.developer.DeveloperDtoOut;
import com.ross.gamis.domain.Developer;
import com.ross.gamis.domain.Game;

public class DeveloperConverter {
    public DeveloperDtoOut convertToDto(Developer developer) {
        DeveloperDtoOut developerDto = new DeveloperDtoOut();
        developerDto.setId(developer.getId());
        developerDto.setName(developer.getName());
        developerDto.setFounded(developer.getFounded());
        developerDto.setCountry(developer.getCountry().getName());
        developerDto.setGameIds(developer.getGames().stream().map(Game::getId).toList());
        return developerDto;
    }
}
