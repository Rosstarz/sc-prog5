package com.ross.gamis.converter;

import com.ross.gamis.controller.api.dto.game.out.GameDtoOutStore;
import com.ross.gamis.domain.Store;
import com.ross.gamis.service.StoreService;

public class StoreConverter {

        public GameDtoOutStore convertToDto(Store store) {
            // Store store = storeService.getStore(gameStore.getStore().getId());
            GameDtoOutStore storeDto = new GameDtoOutStore(store.getId(), store.getName(), store.getIsLibraryOnline(), store.getLinkToLibrary());
            return storeDto;
        }
}
