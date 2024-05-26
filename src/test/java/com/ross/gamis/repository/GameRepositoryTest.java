package com.ross.gamis.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.hamcrest.MatcherAssert;
import static org.hamcrest.Matchers.hasItems;

import com.ross.gamis.domain.Country;
import com.ross.gamis.domain.Developer;
import com.ross.gamis.domain.Game;
import com.ross.gamis.domain.GameStore;
import com.ross.gamis.domain.Store;

import jakarta.validation.ConstraintViolationException;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameRepositoryTest {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private GameStoreRepository gameStoreRepository;

    private Game createdGame;
    private Developer createdDeveloper;
    private Store createdStoreOne;
    private GameStore createdGameStoreOne;

    @BeforeAll
    public void setupAll() {
        createdDeveloper = developerRepository.save(new Developer("Developer 1", LocalDate.now(), Country.BRAZIL));
        createdStoreOne = storeRepository.save(new Store("Store 1", true, "https://store1.com/some/link/here"));
    }
    
    @AfterAll
    public void tearDownAll() {
        storeRepository.delete(createdStoreOne);
        developerRepository.delete(createdDeveloper);
    }

    @BeforeEach
    public void setupEach() {
        createdGame = gameRepository.save(
            new Game("Game title", "Game desc", createdDeveloper));
        createdGameStoreOne = gameStoreRepository.save(new GameStore(createdGame, createdStoreOne, LocalDateTime.now(), 10.0));
    }

    @AfterEach
    public void tearDownEach() {
        gameStoreRepository.delete(createdGameStoreOne);
        gameRepository.delete(createdGame);
    }


    @Test
    public void findAllFetchedDevsShouldReturnGamesWithDeveloper(){
        // Arrange
        // Act
        var games = gameRepository.findAllFetchedDevs();
        // Assert
        assertNotNull(games);
        assertFalse(games.isEmpty());
        MatcherAssert.assertThat(games, hasItems(
                Matchers.samePropertyValuesAs(new Game(createdGame.getId(), createdGame.getTitle(), createdGame.getDescription(), createdGame.getDeveloper(), createdGame.getStores()), "developer", "stores")
        ));
    }

    @Test
    public void gameTitleShouldNotBeBlank(){
        // Arrange
        Developer createdDeveloper = developerRepository.save(new Developer("deve", LocalDate.now(), Country.BRAZIL));

        // Act
        Executable executable = () -> gameRepository.save(new Game("", "desc", createdDeveloper));

        // Assert
        assertThrows(ConstraintViolationException.class, executable);

        // Clean up
        developerRepository.delete(createdDeveloper);
    }
}
