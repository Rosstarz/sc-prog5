package com.ross.gamis.game;

import java.util.List;

public interface GameServiceInt {
    List<Game> getGames();
    void addGame(Game game);
}
