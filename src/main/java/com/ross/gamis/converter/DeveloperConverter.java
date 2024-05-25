package com.ross.gamis.converter;

import com.ross.gamis.controller.api.dto.developer.DeveloperDtoIn;
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

    public Developer convertToEntity(DeveloperDtoIn developerDto) {
        Developer developer = new Developer();
        developer.setId(developerDto.getId());
        developer.setName(developerDto.getName());
        developer.setFounded(developerDto.getFounded());
        developer.setCountry(developerDto.getCountry());
        // developer.setGames(developerDto.getGameIds().stream().map(gameId -> new Game(gameId)).toList());
        return developer;
    }
}
