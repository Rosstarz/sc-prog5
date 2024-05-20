package com.ross.gamis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ross.gamis.domain.Developer;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Long> {
    Developer getDeveloperById(Long id);

    @Query("SELECT d FROM Developer d")
    List<Developer> getDevelopers();

    @Query("SELECT d FROM Developer d LEFT OUTER JOIN FETCH d.games")
    List<Developer> getDevelopersFetched();
}
