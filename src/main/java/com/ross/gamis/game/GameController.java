package com.ross.gamis.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "games";
    }

    @GetMapping("/{id}")
    public String showGame(Model model, @PathVariable(value = "id") Long id){
        model.addAttribute("game",gameService.getGame(id));
        return "game";
    }

    @GetMapping("/add")
    public String addGame(Model model){
        model.addAttribute("addGameForm", new Game());
        return "addgame";
    }

    @PostMapping("register")
    public String registerGame(@ModelAttribute Game game, Model model){
        gameService.addGame(game);
        model.addAttribute("addGameForm", new Game());
        model.addAttribute("message", "Successfully added");
        return "addgame";
    }

}
