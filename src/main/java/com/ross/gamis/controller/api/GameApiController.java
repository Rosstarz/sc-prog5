package com.ross.gamis.controller.api;

import org.springframework.web.bind.annotation.RestController;

import com.ross.gamis.controller.api.dto.GameDto;
import com.ross.gamis.converter.GameConverter;
import com.ross.gamis.domain.Game;
import com.ross.gamis.service.GameService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/api/games")
public class GameApiController {
    private final GameService gameService;
    private final ModelMapper modelMapper;
    private final GameConverter gameConverter;

    public GameApiController(GameService gameService, ModelMapper modelMapper, GameConverter gameConverter) {
        this.gameService = gameService;
        this.modelMapper = modelMapper;
        this.gameConverter = gameConverter;
    }

    @GetMapping()
    public ResponseEntity<List<GameDto>> getAllGames() {
        List<Game> games = gameService.getGames();
        List<GameDto> gameDtos = games.stream()
            .map(gameConverter::convertToDto)
            .toList();
        return new ResponseEntity<>(gameDtos, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable("id") Long id) {
        if(gameService.deleteGame(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
