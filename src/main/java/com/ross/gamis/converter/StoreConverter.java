package com.ross.gamis.converter;

import com.ross.gamis.controller.api.dto.StoreDto;
import com.ross.gamis.domain.Store;
import com.ross.gamis.service.StoreService;

public class StoreConverter {
        

        public StoreDto convertToDto(Store store) {
            // Store store = storeService.getStore(gameStore.getStore().getId());
            StoreDto storeDto = new StoreDto(store.getId(), store.getName(), store.getIsLibraryOnline(), store.getLinkToLibrary());
            return storeDto;
        }
}
