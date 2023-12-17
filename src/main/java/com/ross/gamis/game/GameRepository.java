package com.ross.gamis.game;

import com.ross.gamis.util.DataFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepository {
    public List<Game> getGames(){
        return DataFactory.getGames();
    }

    public void addGame(Game game){
        DataFactory.addGame(game);
    }
}
