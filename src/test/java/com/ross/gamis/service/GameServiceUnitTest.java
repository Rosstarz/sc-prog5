package com.ross.gamis.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ross.gamis.controller.api.dto.game.in.GameDtoIn;
import com.ross.gamis.domain.Country;
import com.ross.gamis.domain.Developer;
import com.ross.gamis.domain.Game;
import com.ross.gamis.repository.DeveloperRepository;
import com.ross.gamis.repository.GameRepository;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameServiceUnitTest {
    @MockBean
    private GameRepository gameRepository;
    @MockBean
    private DeveloperRepository developerMockRepository;
    @Autowired
    private GameService gameService;

    private long gameIdNonExistent = 9999L;

    @Test
    void updateGameFailsWhenGameDoesntExist() {
        // Arrange
        given(gameRepository.findById(gameIdNonExistent)).willReturn(Optional.empty());

        // Act
        var updateSucceeded = gameService.updateGame(
            gameIdNonExistent, new GameDtoIn("Title", "New description", 1));

        // Assert
        assertNull(updateSucceeded);
        verify(gameRepository, never()).save(any());
    }

    @Test
    void updateGameSuccessWhenGameExists() {
        // Arrange
        var developer = new Developer("Developer 2", LocalDate.now(), Country.BRAZIL);
        var game = new Game("My Game", "It's mine", developer);
        game.setId(gameIdNonExistent);
        // given(developerMockRepository.findById(developer.getId())).willReturn(Optional.of(developer));
        given(gameRepository.getGameFetched(gameIdNonExistent)).willReturn(Optional.of(game));

        // Act
        var updateSucceeded = gameService.updateGame(gameIdNonExistent, new GameDtoIn("My Game", "funny description", developer.getId()));

        // Assert
        assertNotNull(updateSucceeded);
        ArgumentCaptor<Game> gameCaptor = ArgumentCaptor.forClass(Game.class);
        verify(gameRepository).save(gameCaptor.capture());
        assertEquals("funny description", gameCaptor.getValue().getDescription());
    }
}
