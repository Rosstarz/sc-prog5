package com.ross.gamis.store;

import com.ross.gamis.game.Game;

import java.util.List;

public interface StoreServiceInt {
    List<Store> getStores();
    Store getStore(Long id);
    void addStore(Store store);
}
