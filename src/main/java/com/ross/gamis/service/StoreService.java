package com.ross.gamis.service;

import com.ross.gamis.domain.Store;
import com.ross.gamis.repository.StoreRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
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
