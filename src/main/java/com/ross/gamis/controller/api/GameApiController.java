package com.ross.gamis.controller.api;

import org.springframework.web.bind.annotation.RestController;

import com.ross.gamis.controller.api.dto.game.in.GameDtoIn;
import com.ross.gamis.controller.api.dto.game.out.GameDtoOut;
import com.ross.gamis.converter.GameConverter;
import com.ross.gamis.domain.Game;
import com.ross.gamis.security.AdminOnly;
import com.ross.gamis.security.CustomUserDetails;
import com.ross.gamis.service.GameService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@RequestMapping("/api/games")
public class GameApiController {
    private final GameService gameService;
    private final ModelMapper modelMapper;
    private final GameConverter gameConverter;
    private final Logger logger;

    public GameApiController(GameService gameService, ModelMapper modelMapper, GameConverter gameConverter, Logger logger) {
        this.gameService = gameService;
        this.modelMapper = modelMapper;
        this.gameConverter = gameConverter;
        this.logger = logger;
    }

    @GetMapping()
    public ResponseEntity<List<GameDtoOut>> getAllGames() {
        List<Game> games = gameService.getGamesFetched();
        for (Game game : games) {
            logger.debug("Game: {}", game.toString());
        }
        List<GameDtoOut> gameDtos = games.stream()
            .map(gameConverter::convertToDto)
            .toList();
        for (GameDtoOut gameDto : gameDtos) {
            logger.debug("GameDto: {}", gameDto.toString());
        }
        return new ResponseEntity<>(gameDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDtoOut> getGame(@PathVariable("id") Long id) {
        Game game = gameService.getGameFetched(id);
        if(game != null){
            return new ResponseEntity<>(
                gameConverter.convertToDto(game),
                HttpStatus.OK
            );
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/{id}")
    @AdminOnly
    public ResponseEntity<GameDtoOut> updateGame(@PathVariable("id") Long id, @Valid @RequestBody GameDtoIn gameDto, @AuthenticationPrincipal CustomUserDetails user) {
        logger.debug("GameDtoPatch: {}", gameDto.toString());
        Game updatedGame = gameService.updateGame(id,gameDto);
        if(updatedGame != null){
            return new ResponseEntity<>(
                gameConverter.convertToDto(updatedGame),
                HttpStatus.OK
            );
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @AdminOnly
    public ResponseEntity<Void> deleteGame(@PathVariable("id") Long id) {
        if(gameService.deleteGame(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    @AdminOnly
    public ResponseEntity<GameDtoOut> addGame(@Valid @RequestBody GameDtoIn gameDto) {
        logger.debug("GameDtoPost: {}", gameDto.toString());
        Game createdGame = gameService.addGameDto(
                gameDto.getTitle(), 
                gameDto.getDescription(),
                gameDto.getDeveloperId());
        return new ResponseEntity<>(
                gameConverter.convertToDto(createdGame),
                HttpStatus.CREATED
        );
    }
}
