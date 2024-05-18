package com.ross.gamis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ross.gamis.domain.GameStore;

public interface GameStoreRepository extends JpaRepository<GameStore, Long>{
    Optional<GameStore> findByGameIdAndStoreId(long gameId, long storeId);

}
