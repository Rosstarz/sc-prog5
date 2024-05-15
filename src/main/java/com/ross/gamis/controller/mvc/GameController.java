package com.ross.gamis.controller.mvc;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ross.gamis.domain.Game;
import com.ross.gamis.domain.Store;
import com.ross.gamis.service.GameService;
import com.ross.gamis.service.StoreService;

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
//        Set<Store> stores = new HashSet<>();
//        stores.add(new Store());
//        stores.add(new Store());
//        stores.add(new Store());
//        game.setStores(stores);
//        game.addStore(new Store());
//        game.addStore(new Store());
//        game.addStore(new Store());
//        game.addStore(new Store());
        model.addAttribute("addGameForm", game);
//        String[] storesSelected = new String[storeService.getStores().size()];
//        Set<Store> storesSelected = new Game().getStores();
//        model.addAttribute("storesSelected", storesSelected);
        return "game/add";
    }

    @ModelAttribute("storesAll")
    public List<Store> getAllStores() {
        return storeService.getStores();
    }

    @PostMapping("register")
    public String registerNewGame(@ModelAttribute Game game, Model model){
//        game.setStores(storesSelected);
//        gameService.addGame(game);
        model.addAttribute("addGameForm", new Game());
        model.addAttribute("message", "Successfully added");
        return "redirect:game/add";
    }

}
