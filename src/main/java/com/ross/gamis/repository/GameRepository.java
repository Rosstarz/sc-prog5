package com.ross.gamis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ross.gamis.domain.Game;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    @Query("SELECT g FROM Game g")
    List<Game> getGames();

    @Query("SELECT g FROM Game g WHERE g.id = :id")
    Game getGame(Long id);

}
