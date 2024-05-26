package com.ross.gamis.controller.api;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import com.ross.gamis.domain.Country;
import com.ross.gamis.domain.Developer;
import com.ross.gamis.domain.Game;
import com.ross.gamis.domain.GameStore;
import com.ross.gamis.domain.Store;
import com.ross.gamis.domain.UserRole;
import com.ross.gamis.repository.DeveloperRepository;
import com.ross.gamis.repository.GameRepository;
import com.ross.gamis.repository.GameStoreRepository;
import com.ross.gamis.repository.StoreRepository;
import com.ross.gamis.security.CustomUserDetails;
import com.ross.gamis.service.GameService;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameApiControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private GameStoreRepository gameStoreRepository;
    @MockBean
    private GameService gameService;

    private long gameIdNonExistent = 9999L;
    private long userId = 9999L;

    private Game createdGame;
    private Developer createdDeveloper;
    private Store createdStoreOne;
    private Store createdStoreTwo;
    private Store createdStoreThree;
    private GameStore createdGameStoreOne;
    private GameStore createdGameStoreTwo;
    private GameStore createdGameStoreThree;
    private CustomUserDetails userDetails;

    @BeforeAll
    public void setupAll() {
        createdDeveloper = developerRepository.save(new Developer("Developer 1", LocalDate.now(), Country.BRAZIL));
        createdStoreOne = storeRepository.save(new Store("Store 1", true, "https://store1.com/some/link/here"));
        createdStoreTwo = storeRepository.save(new Store("Store 2", true, "store1.com/here"));
        createdStoreThree = storeRepository.save(new Store("Store 3", false));
    }
    
    @AfterAll
    public void tearDownAll() {
        storeRepository.delete(createdStoreOne);
        storeRepository.delete(createdStoreTwo);
        storeRepository.delete(createdStoreThree);
        developerRepository.delete(createdDeveloper);
    }

    @BeforeEach
    public void setupEach() {
        createdGame = gameRepository.save(
            new Game("Game title", "Game desc", createdDeveloper));
        createdGameStoreOne = gameStoreRepository.save(new GameStore(createdGame, createdStoreOne, LocalDateTime.now(), 10.0));
        createdGameStoreTwo = gameStoreRepository.save(new GameStore(createdGame, createdStoreTwo, LocalDateTime.now(), 9.99));
        createdGameStoreThree = gameStoreRepository.save(new GameStore(createdGame, createdStoreThree, LocalDateTime.now(), 7.99));
        userDetails = new CustomUserDetails("tester", "password", List.of(new SimpleGrantedAuthority(UserRole.ADMIN.getCode())), userId);
    }

    @AfterEach
    public void tearDownEach() {
        reset(gameService);
        gameStoreRepository.delete(createdGameStoreOne);
        gameStoreRepository.delete(createdGameStoreTwo);
        gameStoreRepository.delete(createdGameStoreThree);
        gameRepository.delete(createdGame);
    }

    @Test
    public void deleteGameIsNotAllowedIfNotAuthenticated() throws Exception {
        // Arrange
        // Act
        mockMvc.perform(delete("/api/games/{id}", createdGame.getId())
            .with(csrf()))
            .andExpect(status().isUnauthorized());
        // Assert
        verify(gameService, never()).deleteGame(createdGame.getId());
    }

    @Test
    public void deleteGameShouldBeAllowedIfAdmin() throws Exception {
        // Arrange
        given(gameService.deleteGame(createdGame.getId())).willReturn(true);
        
        // Act
        mockMvc.perform(delete("/api/games/{id}", createdGame.getId())
        .with(user(userDetails))
        .with(csrf()))
        .andExpect(status().isNoContent());
        
        // Assert
        verify(gameService).deleteGame(createdGame.getId());
    }
    
    @Test
    public void deleteGameShouldBeNotFoundIfNonExistent() throws Exception {
        // Arrange
        given(gameService.deleteGame(gameIdNonExistent)).willReturn(false);

        // Act
        mockMvc.perform(delete("/api/games/{id}", gameIdNonExistent)
            .with(user(userDetails))
            .with(csrf()))
            .andExpect(status().isNotFound());
        
        // Assert
        verify(gameService).deleteGame(gameIdNonExistent);
    }
}
