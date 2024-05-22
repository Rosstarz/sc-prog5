package com.ross.gamis.service;

import org.springframework.stereotype.Service;

import com.ross.gamis.domain.GameStore;
import com.ross.gamis.domain.UserGameStore;
import com.ross.gamis.repository.UserGameStoreRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserGameStoreService {
    private final UserGameStoreRepository userGameStoreRepository;
    private final UserService userService;
    private final GameStoreService gameStoreService;

    public UserGameStoreService(UserGameStoreRepository userGameStoreRepository, UserService userService, GameStoreService gameStoreService) {
        this.userGameStoreRepository = userGameStoreRepository;
        this.userService = userService;
        this.gameStoreService = gameStoreService;
    }

    public List<UserGameStore> getAllUserGameStore(Long userId) {
        return userGameStoreRepository.findByUserId(userId);
    }
    
    public List<UserGameStore> getAllUserGameStoreOwned(Long userId) {
        return userGameStoreRepository.findByUserIdAndOwned(userId, true).orElse(null);
    }

    public UserGameStore setUserGameStoreOwned(Long userId, Long gameStoreId){
        UserGameStore userGameStore = userGameStoreRepository.findByUserIdAndGameStoreId(userId, gameStoreId).orElse(null);
        if (userGameStore != null){
            userGameStore.setOwned(!userGameStore.isOwned());
            userGameStoreRepository.save(userGameStore);
            return userGameStore;
        } else {
            userGameStore = new UserGameStore();
            userGameStore.setOwned(true);
            userGameStore.setUser(userService.getUserById(userId));
            userGameStore.setGameStore(gameStoreService.getGameStoreById(gameStoreId));
            userGameStore.setOwnedSetDate(LocalDateTime.now());
            userGameStoreRepository.save(userGameStore);
        }
        return userGameStore;
    }

    // public void deleteUserGameStore(Long userId, Long gameStoreId){
    //     UserGameStore userGameStore = userGameStoreRepository.findByUserIdAndGameStoreId(userId, gameStoreId).orElse(null);
    //     if (userGameStore != null){
    //         userGameStoreRepository.delete(userGameStore);
    //     }
    // }

    public void deleteUserGameStoreAllByGameStore(List<GameStore> gameStores){
        for (GameStore gameStore : gameStores){
            userGameStoreRepository.deleteAllByGameStore(gameStore);
        }
    }
}
