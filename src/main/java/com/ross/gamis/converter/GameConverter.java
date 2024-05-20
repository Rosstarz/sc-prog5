package com.ross.gamis.converter;

import java.util.ArrayList;
import java.util.List;

import com.ross.gamis.controller.api.dto.game.out.GameDtoOut;
import com.ross.gamis.controller.api.dto.game.out.GameDtoOutDeveloper;
import com.ross.gamis.controller.api.dto.game.out.GameDtoOutGameStore;
import com.ross.gamis.controller.api.dto.game.out.GameDtoOutStore;
import com.ross.gamis.domain.Developer;
import com.ross.gamis.domain.Game;
import com.ross.gamis.domain.GameStore;
import com.ross.gamis.domain.Store;
import com.ross.gamis.service.StoreService;

public class GameConverter {
    private final StoreService storeService;

    public GameConverter(StoreService storeService) {
        this.storeService = storeService;
    }

    public GameDtoOut convertToDto(Game game) {
        GameDtoOut gameDto = new GameDtoOut();
        gameDto.setId(game.getId());
        gameDto.setTitle(game.getTitle());
        gameDto.setDescription(game.getDescription());
        gameDto.setDeveloper(convertGameDeveloperToDto(game.getDeveloper()));
        if (game.getStores() != null) {
            List<GameDtoOutGameStore> gameStoreDtos = new ArrayList<>();
            for (GameStore gameStore : game.getStores()) {
                gameStoreDtos.add(convertGameStoreToDto(gameStore));
            }
            gameDto.setStores(gameStoreDtos);
        }
        return gameDto;
    }

    private GameDtoOutGameStore convertGameStoreToDto(GameStore gameStore) {
        Store store = storeService.getStore(gameStore.getStore().getId());
        GameDtoOutStore storeDto = new GameDtoOutStore(store.getId(), store.getName(), store.getIsLibraryOnline(), store.getLinkToLibrary());
        return new GameDtoOutGameStore(gameStore.getId(), storeDto, gameStore.getReleaseDate(), gameStore.getPrice());
    }
    
    private GameDtoOutDeveloper convertGameDeveloperToDto(Developer developer) {
        return new GameDtoOutDeveloper(developer.getId(), developer.getName(),
        developer.getFounded(), developer.getCountry());
    }
}
