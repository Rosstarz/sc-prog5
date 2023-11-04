package com.ross.gamis.repositories;

import com.ross.gamis.domain.Game;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRepository {
    public List<Game> readGames(){
        return DataFactory.getGames();
    }
}
