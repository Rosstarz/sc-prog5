package com.ross.gamis.controller.api;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
// import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.jayway.jsonpath.JsonPath;
import com.ross.gamis.domain.Country;
import com.ross.gamis.domain.Developer;
import com.ross.gamis.domain.Game;
import com.ross.gamis.domain.GameStore;
import com.ross.gamis.domain.Store;
// import com.ross.gamis.domain.UserRole;
import com.ross.gamis.repository.DeveloperRepository;
import com.ross.gamis.repository.GameRepository;
import com.ross.gamis.repository.GameStoreRepository;
import com.ross.gamis.repository.StoreRepository;
// import com.ross.gamis.security.CustomUserDetails;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameApiControllerTest {
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

    private long gameIdNonExistent = 9999L;
    private int gameCount = 5;
    // private long userId = 9999L;

    private Game createdGame;
    private Developer createdDeveloper;
    private Store createdStoreOne;
    private Store createdStoreTwo;
    private Store createdStoreThree;
    private GameStore createdGameStoreOne;
    private GameStore createdGameStoreTwo;
    private GameStore createdGameStoreThree;
    // private CustomUserDetails userDetails;

    @BeforeAll
    public void setupAll() {
        // Remove all games so that we can have a known state
        // gameRepository.deleteAll();
        // Create entities for creating a new Game
        createdDeveloper = developerRepository.save(new Developer("Developer 1", LocalDate.now(), Country.BRAZIL));
        createdStoreOne = storeRepository.save(new Store("Store 1", true, "https://store1.com/some/link/here"));
        createdStoreTwo = storeRepository.save(new Store("Store 2", true, "store1.com/here"));
        createdStoreThree = storeRepository.save(new Store("Store 3", false));
        // userDetails = new CustomUserDetails("tester", "password", List.of(new SimpleGrantedAuthority(UserRole.ADMIN.getCode())), userId);
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
    }

    @AfterEach
    public void tearDownEach() {
        gameStoreRepository.delete(createdGameStoreOne);
        gameStoreRepository.delete(createdGameStoreTwo);
        gameStoreRepository.delete(createdGameStoreThree);
        gameRepository.delete(createdGame);
    }


    @Test
    public void getGameByIdShouldReturnNotFoundForNonExistentGame() throws Exception {
        mockMvc.perform(
                get("/api/games/{gameId}", gameIdNonExistent)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getGameByIdShouldReturnGameForExistentGame() throws Exception {
        mockMvc.perform(
                get("/api/games/{gameId}", createdGame.getId())
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(createdGame.getId()))
                .andExpect(jsonPath("$.title").value(createdGame.getTitle()))
                .andExpect(jsonPath("$.developer.developerId").value(createdDeveloper.getId()))
                .andExpect(jsonPath("$.developer.name").value(createdDeveloper.getName()))
                .andExpect(jsonPath("$.stores.length()").value(3))
                .andExpect(jsonPath("$.stores[*].store.name", Matchers.containsInRelativeOrder(createdStoreOne.getName(), createdStoreTwo.getName(), createdStoreThree.getName())));
    }

    @Test
    public void getGameByIdAndAcceptXmlShouldReturnGameInXml() throws Exception {
        mockMvc.perform(
                get("/api/games/{gameId}", createdGame.getId())
                    .accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentType(new MediaType(MediaType.APPLICATION_XML.getType(), MediaType.APPLICATION_XML.getSubtype(), StandardCharsets.UTF_8)))
                .andExpect(xpath("//GameDtoOut/id/text()").string(String.valueOf(createdGame.getId())))
                .andExpect(xpath("//GameDtoOut/title/text()").string(createdGame.getTitle()));
    }

    @Test
    public void getAllGamesShouldReturnAllGames() throws Exception {
        mockMvc.perform(
                get("/api/games")
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // .andExpect(jsonPath("$", Matchers.hasSize(gameCount)))
                .andExpect(jsonPath("$[" + (gameCount - 1) + "].id").value(createdGame.getId()))
                .andExpect(jsonPath("$[" + (gameCount - 1) + "].title").value(createdGame.getTitle()))
                .andExpect(jsonPath("$[" + (gameCount - 1) + "].developer.developerId").value(createdDeveloper.getId()))
                .andExpect(jsonPath("$[" + (gameCount - 1) + "].developer.name").value(createdDeveloper.getName()))
                .andExpect(jsonPath("$[" + (gameCount - 1) + "].stores", Matchers.hasSize(3)))
                .andExpect(jsonPath("$[" + (gameCount - 1) + "].stores[*].store.name", Matchers.containsInRelativeOrder(createdStoreOne.getName(), createdStoreTwo.getName(), createdStoreThree.getName())));
    }

    @Test
    public void deleteGameIsNotAllowedIfNotAuthenticated() throws Exception {
        mockMvc.perform(
            delete("/api/games/{gameId}", createdGame.getId())
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails("user")
    public void deleteGameIsNotAllowedIfNotAdmin() throws Exception {
        mockMvc.perform(
            delete("/api/games/{gameId}", createdGame.getId())
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("admin")
    public void deleteGameIfAdminShouldDeleteGame() throws Exception {
        mockMvc.perform(
            delete("/api/games/{gameId}", createdGame.getId())
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails("admin")
    public void deleteGameShouldReturnNotFoundIfNonExistent() throws Exception {
        mockMvc.perform(
            delete("/api/games/{gameId}", gameIdNonExistent)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    @WithUserDetails("admin")
    public void addGameShouldReturnBadRequestIfTitleIsNull() throws Exception {
        mockMvc.perform(
            post("/api/games")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithUserDetails("admin")
    public void addGameShouldBeCreatedIfCorrectBody() throws Exception {
        var mvcResult = mockMvc.perform(
            post("/api/games")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"title\": \"New Game\", \"description\": \"New Game Description\", \"developerId\": " + createdDeveloper.getId() + "}")
                .with(csrf()))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.title").value("New Game"))
            .andExpect(jsonPath("$.description").value("New Game Description"))
            .andExpect(jsonPath("$.developer.developerId").value(createdDeveloper.getId()))
            .andExpect(jsonPath("$.developer.name").value(createdDeveloper.getName()))
            .andExpect(jsonPath("$.stores").isEmpty())
            .andReturn();
        
        var httpResponseBody = mvcResult.getResponse().getContentAsString();
        long newGameId = (Integer) JsonPath.read(httpResponseBody, "$.id");

        mockMvc.perform(get("/api/games/{id}", newGameId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newGameId));

        mockMvc.perform(delete("/api/games/{id}", newGameId)
                        .with(csrf()))
                .andExpect(status().isNoContent());
    }
}
