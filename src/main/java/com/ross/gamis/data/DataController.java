package com.ross.gamis.data;

import com.ross.gamis.game.Game;
import com.ross.gamis.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/generate")
public class DataController {
    private final GameService gameService;
    @Autowired
    public DataController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public String generateData(){
        List<Game> games = DataFactory.getGames();
        for (Game game : games){
            gameService.addGame(game);
        }
        return "index";
    }
}
