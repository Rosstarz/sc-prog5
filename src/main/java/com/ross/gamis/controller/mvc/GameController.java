package com.ross.gamis.controller.mvc;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.ross.gamis.controller.mvc.viewmodel.DeveloperViewModel;
import com.ross.gamis.controller.mvc.viewmodel.GameViewModel;
import com.ross.gamis.domain.Game;
// import com.ross.gamis.domain.Store;
import com.ross.gamis.service.DeveloperService;
import com.ross.gamis.service.GameService;
import com.ross.gamis.service.StoreService;

import jakarta.validation.Valid;

// import java.util.*;

@Controller
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;
    private final StoreService storeService;
    private final DeveloperService developerService;

    // @Autowired
    public GameController(GameService gameService, StoreService storeService, DeveloperService developerService) {
        this.gameService = gameService;
        this.storeService = storeService;
        this.developerService = developerService;
    }

    @GetMapping
    public ModelAndView showGames(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("game/index");
        modelAndView.addObject("games", 
            gameService.getGames()
                .stream()
                .map(game -> new GameViewModel(game.getId(), game.getTitle(), game.getDescription(), new DeveloperViewModel(game.getDeveloper().getId(),game.getDeveloper().getName(),game.getDeveloper().getFounded(),game.getDeveloper().getCountry().getName())))
                // , game.getStores().stream().map(gameStore -> gameStore.getId()).toList()
                .toList()
        );
        modelAndView.addObject("developers", 
            developerService.getDevelopers()
                .stream()
                .map(developer -> new DeveloperViewModel(developer.getId(), developer.getName(), developer.getFounded(), developer.getCountry().getName()))
                .toList()
        );
        return modelAndView;
    }

    @GetMapping("/{id}")
    // public String showGame(Model model, @PathVariable(value = "id") Long id){
    public String showGame(){
        // model.addAttribute("game",gameService.getGame(id));
        return "game/game";
    }

    // @GetMapping("/add")
    // public String showAddGame(Model model){
    //     Game game = new Game();
    //     model.addAttribute("addGameForm", game);
    //     return "game/add";
    // }

    @PostMapping("/register")
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
