package com.ross.gamis.util;

import com.ross.gamis.game.Game;
import com.ross.gamis.store.Store;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataFactory {
    public static List<Game> games;
    public static List<Store> stores;
//    private static List<Developer> developers;

    public static void seed() {
        games.add(new Game("Dota 2", Game.parseDate("2011-08-23"), 0, true));
        games.add(new Game("Counter-Strike: Global Offensive", Game.parseDate("2012-08-21"), 0, true));
        games.add(new Game("Age of Empires II: Definitive Edition", Game.parseDate("2019-11-14"), 19.99, true));
        games.add(new Game("God of War", Game.parseDate("2022-01-14"), 49.99, false));

        stores.add(new Store("Steam", true, "https://steamcommunity.com/id/artificiiall/games/?tab=all"));
        stores.add(new Store("Epic Games", false, ""));
        stores.add(new Store("Xbox", false, ""));
        stores.add(new Store("GOG Galaxy", true, "https://www.gog.com/u/Rostars777/games"));

        // M2M Games
        // on Steam
        games.get(0).setStores(stores.subList(0,1));
        games.get(1).setStores(stores.subList(0,1));
        // on Steam, Epic, Xbox
        games.get(2).setStores(stores.subList(0,3));
        // on all
        games.get(3).setStores(stores);

        // M2M Stores
        // have all
        stores.get(0).setGames(games);
        // have AoE, GoW
        stores.get(1).setGames(games.subList(2,4));
        stores.get(2).setGames(games.subList(2,4));
        // have GoW
        stores.get(3).setGames(games.subList(3,4));

//        for (int i = 0; i < games.size(); i++){
//            if (i < 2){
//                games.get(i).setStores(stores);
//                stores.get(i).setGames(games);
//            } else {
//                games.get(i).setStores(stores.subList(0,2));
//                stores.get(i).setGames(games.subList(0,2));
//            }
//        }
    }

    public static List<Game> getGames() {
        return games;
    }
}
