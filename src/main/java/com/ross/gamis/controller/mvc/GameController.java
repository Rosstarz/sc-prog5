package com.ross.gamis.controller.mvc;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ross.gamis.domain.Game;
import com.ross.gamis.domain.Store;
import com.ross.gamis.service.GameService;
import com.ross.gamis.service.StoreService;

import jakarta.validation.Valid;

import java.util.*;

@Controller
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;
    private final StoreService storeService;

    // @Autowired
    public GameController(GameService gameService, StoreService storeService){
        this.gameService = gameService;
        this.storeService = storeService;
    }

    @GetMapping
    public String showGames(Model model){
        model.addAttribute("games",gameService.getGames());
        return "game/index";
    }

    @GetMapping("/{id}")
    public String showGame(Model model, @PathVariable(value = "id") Long id){
        model.addAttribute("game",gameService.getGame(id));
        return "game/game";
    }

    @GetMapping("/add")
    public String showAddGame(Model model){
        Game game = new Game();
        model.addAttribute("addGameForm", game);
        return "game/add";
    }

    @ModelAttribute("storesAll")
    public List<Store> getAllStores() {
        return storeService.getStores();
    }

    @PostMapping("register")
    public String registerNewGame(@Valid @ModelAttribute Game game, BindingResult errors, Model model){
        model.addAttribute("addGameForm", new Game());
        if (errors.hasErrors()) {
            model.addAttribute("message2", errors.getAllErrors().stream().findFirst().orElse(null).getDefaultMessage());
            return "game/add";
        }
        gameService.addGame(game);
        model.addAttribute("message", "Successfully added");
        return "game/add";
    }

}
