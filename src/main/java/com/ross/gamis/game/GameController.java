package com.ross.gamis.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;
    @Autowired
    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    @GetMapping
    public String showGames(Model model){
        model.addAttribute("games",gameService.getGames());
        model.addAttribute("some","somesome");
        return "games";
    }

    @PostMapping
    public void addGame(Game game){
        gameService.addGame(game);
    }
}
