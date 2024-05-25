package com.ross.gamis.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ross.gamis.controller.api.dto.developer.DeveloperDtoIn;
import com.ross.gamis.controller.api.dto.developer.DeveloperDtoOut;
import com.ross.gamis.controller.api.dto.game.out.GameDtoOut;
import com.ross.gamis.converter.DeveloperConverter;
import com.ross.gamis.domain.Country;
import com.ross.gamis.domain.Developer;
import com.ross.gamis.domain.Game;
import com.ross.gamis.service.DeveloperService;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/developers")
public class DeveloperApiController {
    private final DeveloperService developerService;
    private final DeveloperConverter developerConverter;
    private final Logger logger;

    public DeveloperApiController(DeveloperService developerService, DeveloperConverter developerConverter, Logger logger) {
        this.developerService = developerService;
        this.developerConverter = developerConverter;
        this.logger = logger;
    }

    @GetMapping()
    public ResponseEntity<List<DeveloperDtoOut>> searchDevelopers(@RequestParam(required = false) String search) {
        if (search == null) {
            List<Developer> developers = developerService.getDevelopersFetched();
            // for (Game game : developers) {
            //     logger.debug("Game: {}", game.toString());
            // }
            List<DeveloperDtoOut> developerDtos = developers.stream()
            .map(developerConverter::convertToDtoWithGames)
                .toList();
            // for (GameDtoOut gameDto : gameDtos) {
            //     logger.debug("GameDto: {}", gameDto.toString());
            // }
            return new ResponseEntity<>(developerDtos, HttpStatus.OK);
        } else {
            List<Developer> developers = developerService.searchDevelopersByNameOrCountry(search);
            if (developers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                // for (Developer dev : developers) {
                //     logger.debug("Dev: {}", dev.toString());
                // }
                List<DeveloperDtoOut> developerDtos = developers.stream()
                .map(developerConverter::convertToDto)
                .toList();
                return new ResponseEntity<>(developerDtos, HttpStatus.OK);
            }
        }
    }
    
    @PostMapping()
    public ResponseEntity<DeveloperDtoOut> addNewDeveloper(@Valid @RequestBody DeveloperDtoIn developerDtoIn) {
        Developer developer = developerService.addDeveloper(developerConverter.convertToEntity(developerDtoIn));
        logger.debug("Developer: {}", developer.toString());
        return new ResponseEntity<>(developerConverter.convertToDto(developer), HttpStatus.CREATED);
    }

    @GetMapping("/countries")
    public ResponseEntity<Country[]> getCountryList() {
        return new ResponseEntity<>(Country.values(), HttpStatus.OK);
    }
}
