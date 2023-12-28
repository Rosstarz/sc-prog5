package com.ross.gamis.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService implements GameServiceInt {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    public List<Game> getGames(){
        return gameRepository.getGames();
    }

    public Game getGame(Long id){
        return gameRepository.getGame(id);
    }

    public void addGame(Game game){
        gameRepository.save(game);
    }
}
