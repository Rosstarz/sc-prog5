package com.ross.gamis.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ross.gamis.controller.api.dto.DeveloperDto;
import com.ross.gamis.controller.api.dto.GameDto;
import com.ross.gamis.controller.api.dto.GameStoreDto;
import com.ross.gamis.controller.api.dto.StoreDto;
import com.ross.gamis.domain.Game;
import com.ross.gamis.domain.GameStore;
import com.ross.gamis.domain.Store;
import com.ross.gamis.service.StoreService;

public class GameConverter {
    private final StoreService storeService;

    public GameConverter(StoreService storeService) {
        this.storeService = storeService;
    }

    public GameDto convertToDto(Game game) {
        GameDto gameDto = new GameDto();
        gameDto.setId(game.getId());
        gameDto.setTitle(game.getTitle());
        gameDto.setDescription(game.getDescription());
        gameDto.setDeveloper(new DeveloperDto(game.getDeveloper().getId(), game.getDeveloper().getName(),
                game.getDeveloper().getFounded(), game.getDeveloper().getCountry()));
        List<GameStoreDto> gameStoreDtos = new ArrayList<>();
        for (GameStore gameStore : game.getStores()) {
            Store store = storeService.getStore(gameStore.getStore().getId());
            StoreDto storeDto = new StoreDto(store.getId(), store.getName(), store.getIsLibraryOnline(), store.getLinkToLibrary());
            gameStoreDtos.add(
                    new GameStoreDto(gameStore.getId(), storeDto, gameStore.getReleaseDate(), gameStore.getPrice()));
            // gameStoreDtos.add(new GameStoreDto(gameStore.getId(),
            // gameStore.getStore().getId(), gameStore.getReleaseDate(),
            // gameStore.getPrice()));
        }
        gameDto.setStores(gameStoreDtos);
        return gameDto;
    }
}
