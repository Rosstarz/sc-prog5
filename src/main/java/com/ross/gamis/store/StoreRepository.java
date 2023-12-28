package com.ross.gamis.store;

import com.ross.gamis.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    @Query("SELECT s FROM Store s")
    List<Store> getStores();

    @Query("SELECT s FROM Store s WHERE s.id = :id")
    Store getStore(Long id);

}