package com.ross.gamis.controllers;

import com.ross.gamis.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GameController {
    private final GameService gameService;
    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping("/games");
    public String showGames(Model model){
        model.addAttribute("game",gameService.getGames());
        return "game/Games";
    }
}
