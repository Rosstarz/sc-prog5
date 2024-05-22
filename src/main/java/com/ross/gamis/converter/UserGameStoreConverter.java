package com.ross.gamis.converter;

import com.ross.gamis.controller.api.dto.usergamestore.out.UserGameDtoOutGameStore;
import com.ross.gamis.controller.api.dto.usergamestore.out.UserGameStoreDtoOut;
import com.ross.gamis.domain.GameStore;
import com.ross.gamis.domain.UserGameStore;

public class UserGameStoreConverter {
    public UserGameStoreDtoOut convertToDto(UserGameStore userGameStore){
        UserGameStoreDtoOut userGameStoreDtoOut = new UserGameStoreDtoOut();
        userGameStoreDtoOut.setId(userGameStore.getId());
        userGameStoreDtoOut.setUserId(userGameStore.getUser().getId());
        userGameStoreDtoOut.setGameStore(convertGameStoreToDto(userGameStore.getGameStore()));
        userGameStoreDtoOut.setOwned(userGameStore.isOwned());
        userGameStoreDtoOut.setOwnedSetDate(userGameStore.getOwnedSetDate());
        return userGameStoreDtoOut;
    }

    public UserGameDtoOutGameStore convertGameStoreToDto(GameStore gameStore){
        return new UserGameDtoOutGameStore(gameStore.getId(), gameStore.getGame().getId(), gameStore.getStore().getId());
    }
}
