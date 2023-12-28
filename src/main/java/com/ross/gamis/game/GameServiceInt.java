package com.ross.gamis.game;

import java.util.List;

public interface GameServiceInt {
    List<Game> getGames();
    Game getGame(Long id);
    void addGame(Game game);
}
