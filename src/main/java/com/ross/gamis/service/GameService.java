package com.ross.gamis.service;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ross.gamis.domain.Game;
import com.ross.gamis.repository.GameRepository;
import com.ross.gamis.repository.GameStoreRepository;

import java.util.List;

@Service
@Transactional
public class GameService {
    private final GameRepository gameRepository;
    private final GameStoreRepository gameStoreRepository;

    // @Autowired
    public GameService(GameRepository gameRepository, GameStoreRepository gameStoreRepository) {
        this.gameRepository = gameRepository;
        this.gameStoreRepository = gameStoreRepository;
    }

    // @Transactional
    public List<Game> getGames(){
        return gameRepository.findAllFetched();
    }

    public Game getGame(Long id){
        return gameRepository.getGame(id);
    }

    public void addGame(Game game){
        gameRepository.save(game);
    }

    public boolean deleteGame(Long id){
        var game = gameRepository.findById(id).orElse(null);
        if (game == null) {
            return false;
        } else {
            gameStoreRepository.deleteAll(game.getStores());
            gameRepository.deleteAllById(id);
            return true;
        }
    }
}
