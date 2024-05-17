package com.ross.gamis.controller.api;

import org.springframework.web.bind.annotation.RestController;

import com.ross.gamis.domain.Game;
import com.ross.gamis.service.GameService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/games")
public class GameApiController {
    private final GameService gameService;

    public GameApiController(GameService gameService) {
        this.gameService = gameService;
    }
    
    @GetMapping()
    public List<Game> getAllGames() {
        return gameService.getGames();
    }
    
}
