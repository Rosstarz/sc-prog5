package com.ross.gamis.controller.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ross.gamis.controller.api.dto.developer.DeveloperDtoOut;
import com.ross.gamis.controller.api.dto.game.out.GameDtoOut;
import com.ross.gamis.converter.DeveloperConverter;
import com.ross.gamis.domain.Developer;
import com.ross.gamis.domain.Game;
import com.ross.gamis.service.DeveloperService;

import ch.qos.logback.core.model.Model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/developers")
public class DeveloperApiController {
    private final DeveloperService developerService;
    private final DeveloperConverter developerConverter;

    public DeveloperApiController(DeveloperService developerService, DeveloperConverter developerConverter) {
        this.developerService = developerService;
        this.developerConverter = developerConverter;
    }

    @GetMapping()
    public ResponseEntity<List<DeveloperDtoOut>> getMethodName() {
        List<Developer> developers = developerService.getDevelopersFetched();
        // for (Game game : developers) {
        //     logger.debug("Game: {}", game.toString());
        // }
        List<DeveloperDtoOut> developerDtos = developers.stream()
            .map(developerConverter::convertToDto)
            .toList();
        // for (GameDtoOut gameDto : gameDtos) {
        //     logger.debug("GameDto: {}", gameDto.toString());
        // }
        return new ResponseEntity<>(developerDtos, HttpStatus.OK);
    }
    
}
