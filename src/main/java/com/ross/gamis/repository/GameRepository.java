package com.ross.gamis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.ross.gamis.domain.Game;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
    @Query("SELECT g FROM Game g")
    List<Game> getGames();

    @Query("SELECT g FROM Game g JOIN FETCH g.developer LEFT OUTER JOIN FETCH g.stores")
    List<Game> findAllFetched();

    @Query("SELECT g FROM Game g WHERE g.id = :id")
    Game getGame(Long id);
    
    // Optional<Game> findById(Long id);
    @NonNull Optional<Game> findById(@NonNull Long id);

    void deleteAllById(Long id);

}
