package com.ross.gamis.store;

import com.ross.gamis.game.Game;
import com.ross.gamis.game.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService implements StoreServiceInt {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getStores(){
        return storeRepository.getStores();
    }
    public Store getStore(Long id){
        return storeRepository.getStore(id);
    }

    public void addStore(Store store){
        storeRepository.save(store);
    }
}
