package com.ross.gamis.service;

import org.springframework.stereotype.Service;

import com.ross.gamis.repository.GameStoreRepository;

@Service
public class GameStoreService {
    private final GameStoreRepository gameStoreRepository;

    public GameStoreService(GameStoreRepository gameStoreRepository) {
        this.gameStoreRepository = gameStoreRepository;
    }

    public boolean isGameStoreExist(long gameId, long storeId) {
        if (!gameStoreRepository.findByGameIdAndStoreId(gameId, storeId).isPresent()) {
            return false;
        } else {
            return true;
        }
    }
}
