package com.ross.gamis.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ross.gamis.domain.Country;
import com.ross.gamis.domain.Developer;
import com.ross.gamis.domain.Game;

import jakarta.validation.ConstraintViolationException;

@SpringBootTest
public class GameRepositoryTest {
    @Autowired
    private GameRepository gameRepository;
    
    @Autowired
    private DeveloperRepository developerRepository;

    private Developer createdDeveloper;

    @BeforeEach
    public void setupEach() {
        createdDeveloper = developerRepository.save(new Developer("deve", LocalDate.now(), Country.BRAZIL));
    }

    @Test
    public void findAllFetchedDevsShouldReturnGamesWithDeveloper(){
        // Arrange
        // Act
        var games = gameRepository.findAllFetchedDevs();
        // Assert
        assertNotNull(games);
        assertFalse(games.isEmpty());
        assertEquals(1, games.get(0).getDeveloper().getId());
        assertEquals("Counter-Strike: Global Offensive", games.get(0).getTitle());
    }

    @Test
    public void gameTitleShouldNotBeBlank(){
        // Arrange

        // Act
        Executable executable = () -> gameRepository.save(new Game("", "desc", createdDeveloper));

        // Assert
        assertThrows(ConstraintViolationException.class, executable);
    }
}
