package com.ross.gamis.repositories;

import com.ross.gamis.domain.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class DataFactory {
    private static List<Game> games = new ArrayList<>();
//    private static List<Store> stores;
//    private static List<Developer> developers;

    public void generateDummy (){
        games.add(new Game("Dota",Game.parseDate("2011-08-23"),0,0,true,0));
    }

    public static List<Game> getGames() {
        return games;
    }
}
