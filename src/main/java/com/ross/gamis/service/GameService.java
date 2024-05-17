package com.ross.gamis.service;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ross.gamis.domain.Game;
import com.ross.gamis.repository.GameRepository;

import java.util.List;

@Service
// @Transactional
public class GameService {
    private final GameRepository gameRepository;

    // @Autowired
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
