package com.ross.gamis.service;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ross.gamis.controller.api.dto.game.in.GameDtoIn;
import com.ross.gamis.domain.Developer;
import com.ross.gamis.domain.Game;
import com.ross.gamis.repository.GameRepository;
import com.ross.gamis.repository.GameStoreRepository;
import com.ross.gamis.repository.UserGameStoreRepository;

import java.util.List;

@Service
@Transactional
public class GameService {
    private final GameRepository gameRepository;
    private final GameStoreRepository gameStoreRepository;
    private final DeveloperService developerService;
    private final UserGameStoreService userGameStoreService;

    // @Autowired
    public GameService(GameRepository gameRepository, GameStoreRepository gameStoreRepository, DeveloperService developerService, UserGameStoreService userGameStoreService) {
        this.gameRepository = gameRepository;
        this.gameStoreRepository = gameStoreRepository;
        this.developerService = developerService;
        this.userGameStoreService = userGameStoreService;
    }

    // @Transactional
    public List<Game> getGames(){
        return gameRepository.findAllFetchedDevs();
    }

    public List<Game> getGamesFetched(){
        return gameRepository.findAllFetched();
    }

    public Game getGame(Long id){
        return gameRepository.getGame(id);
    }

    public Game getGameFetched(Long id){
        return gameRepository.getGameFetched(id).orElse(null);
    }

    public void addGame(Game game){
        gameRepository.save(game);
    }

    public Game addGameDto(String title, String description, long developerId){
        // TODO: check if developer exists, return 404
        Game game = new Game();
        game.setTitle(title);
        game.setDescription(description);
        game.setDeveloper(developerService.getDeveloperById(developerId));
        gameRepository.save(game);
        return game;
    }

    public boolean deleteGame(Long id){
        var game = gameRepository.findById(id).orElse(null);
        if (game == null) {
            return false;
        } else {
            userGameStoreService.deleteUserGameStoreAllByGameStore(game.getStores());
            gameStoreRepository.deleteAll(game.getStores());
            gameRepository.deleteAllById(id);
            return true;
        }
    }

    public Game updateGame(Long id, GameDtoIn game){
        Game gameToUpdate = gameRepository.getGameFetched(id).orElse(null);
        if (gameToUpdate == null) {
            return null;
        } else {
            gameToUpdate.setTitle(game.getTitle());
            gameToUpdate.setDescription(game.getDescription());
            gameToUpdate.setDeveloper(developerService.getDeveloperById(game.getDeveloperId()));
            gameRepository.save(gameToUpdate);
            return gameToUpdate;
        }
    }
}
