package com.ross.gamis.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ross.gamis.controller.api.dto.game.in.GameDtoIn;
import com.ross.gamis.domain.Country;
import com.ross.gamis.domain.Developer;
import com.ross.gamis.domain.Game;
import com.ross.gamis.repository.DeveloperRepository;
import com.ross.gamis.repository.GameRepository;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameServiceTest {
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private GameService gameService;

    private long gameIdNonExistent = 9999L;
    // private int gameCount = 5;
    // private long userId = 9999L;

    private Developer createdDeveloper;

    @BeforeAll
    public void setupAll() {
        createdDeveloper = developerRepository.save(new Developer("Developer 1", LocalDate.now(), Country.BRAZIL));
    }
    
    @AfterAll
    public void tearDownAll() {
        developerRepository.delete(createdDeveloper);
    }

    @Test
    void changeGameDescriptionShouldReturnTrueForExistingGameAndUpdateSaidGame() {
        // Arrange
        var createdGame = gameRepository.save(new Game("Some Title", "Some Description", createdDeveloper));

        // Act
        var result = gameService.updateGame(
                createdGame.getId(), new GameDtoIn(createdGame.getTitle(), "New description", createdDeveloper.getId()));

        // Assert
        assertNotNull(result);
        assertEquals("New description",
                gameRepository.findById(createdGame.getId()).get().getDescription());

        // Cleanup
        gameRepository.deleteById(createdGame.getId());
    }

    @Test
    void changeGameDescriptionShouldReturnFalseForNonExistingGame() {
        // Arrange

        // Act
        var result = gameService.updateGame(
            gameIdNonExistent, new GameDtoIn("Title", "New description", createdDeveloper.getId()));

        // Assert
        assertNull(result);
        assertTrue(gameRepository.findById(gameIdNonExistent).isEmpty());
    }
}
