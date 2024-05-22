package com.ross.gamis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ross.gamis.domain.GameStore;
import com.ross.gamis.domain.UserGameStore;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGameStoreRepository extends JpaRepository<UserGameStore, Long>{
    @Query("SELECT g FROM UserGameStore g JOIN FETCH g.user JOIN FETCH g.gameStore WHERE g.user.id = :userId")
    List<UserGameStore> findByUserId(Long userId);

    @Query("SELECT g FROM UserGameStore g JOIN FETCH g.user JOIN FETCH g.gameStore WHERE g.user.id = :userId AND g.isOwned = :owned")
    Optional<List<UserGameStore>> findByUserIdAndOwned(Long userId, boolean owned);

    Optional<UserGameStore> findByUserIdAndGameStoreId(Long userId, Long gameStoreId);

    void deleteAllByGameStore(GameStore gameStore);
}
