package com.ross.gamis.controller.mvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

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
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ross.gamis.controller.mvc.viewmodel.DeveloperViewModel;
import com.ross.gamis.controller.mvc.viewmodel.GameViewModel;
import com.ross.gamis.controller.mvc.viewmodel.StoreViewModel;
import com.ross.gamis.domain.Country;
import com.ross.gamis.domain.Developer;
import com.ross.gamis.domain.Game;
import com.ross.gamis.domain.GameStore;
import com.ross.gamis.domain.Store;
import com.ross.gamis.domain.UserGameStore;
import com.ross.gamis.repository.DeveloperRepository;
import com.ross.gamis.repository.GameRepository;
import com.ross.gamis.repository.GameStoreRepository;
import com.ross.gamis.repository.StoreRepository;
import com.ross.gamis.repository.UserGameStoreRepository;
import com.ross.gamis.service.GameService;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
// import static org.mockito.Mockito.*;
// import static org.mockito.BDDMockito.*;
// import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;

import org.hamcrest.MatcherAssert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StoreControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private GameService gameService;
    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private GameStoreRepository gameStoreRepository;
    @Autowired
    private UserGameStoreRepository userGameStoreRepository;

    private int gameCount = 3;
    private long nonExistingStoreId = 9999L;

    private Game createdGame;
    private Game createdGameTwo;
    private Game createdGameThree;
    private Developer createdDeveloper;
    private Store createdStoreOne;
    private Store createdStoreTwo;
    private Store createdStoreThree;
    private GameStore createdGameStoreOne;
    private GameStore createdGameStoreTwo;
    private GameStore createdGameStoreThree;

    @BeforeAll
    public void setupAll() {
        // Remove all games so that we can have a known state
        userGameStoreRepository.deleteAll();
        gameStoreRepository.deleteAll();
        gameRepository.deleteAll();
        // Create entities for creating a new Game
        createdDeveloper = developerRepository.save(new Developer("Developer 1", LocalDate.now(), Country.BRAZIL));
        createdStoreOne = storeRepository.save(new Store("Store 1", true, "https://store1.com/some/link/here"));
        createdStoreTwo = storeRepository.save(new Store("Store 2", true, "store1.com/here"));
        createdStoreThree = storeRepository.save(new Store("Store 3", false));
        // userDetails = new CustomUserDetails("tester", "password", List.of(new
        // SimpleGrantedAuthority(UserRole.ADMIN.getCode())), userId);
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
        createdGameTwo = gameRepository.save(
                new Game("Another Game title", "long Game desc very much", createdDeveloper));
        createdGameThree = gameRepository.save(
                new Game("Not another Game title", "short Game desc", createdDeveloper));
        createdGameStoreOne = gameStoreRepository
                .save(new GameStore(createdGame, createdStoreOne, LocalDateTime.now(), 10.0));
        createdGameStoreTwo = gameStoreRepository
                .save(new GameStore(createdGame, createdStoreTwo, LocalDateTime.now(), 9.99));
        createdGameStoreThree = gameStoreRepository
                .save(new GameStore(createdGame, createdStoreThree, LocalDateTime.now(), 7.99));
    }

    @AfterEach
    public void tearDownEach() {
        gameStoreRepository.delete(createdGameStoreOne);
        gameStoreRepository.delete(createdGameStoreTwo);
        gameStoreRepository.delete(createdGameStoreThree);
        gameRepository.delete(createdGame);
        gameRepository.delete(createdGameTwo);
        gameRepository.delete(createdGameThree);
    }

    @Test
    @WithUserDetails("admin")
    public void addStoreShouldSucceedIfAdmin() throws Exception {
        mockMvc.perform(post("/stores/register")
                .param("id", "8")
                .param("name", createdStoreOne.getName())
                .param("isLibraryOnline", createdStoreOne.getIsLibraryOnline().toString())
                .param("linkToLibrary", createdStoreOne.getLinkToLibrary())
                .with(csrf()))
                .andExpect(view().name("store/add"))
                .andReturn();

        mockMvc.perform(get("/stores/8"))
                .andExpect(status().isOk())
                .andExpect(view().name("store/store"))
                .andExpect(model().attribute("store",
                        Matchers.samePropertyValuesAs(new StoreViewModel(8, createdStoreOne.getName(),
                                createdStoreOne.getIsLibraryOnline(), createdStoreOne.getLinkToLibrary()))));

        storeRepository.deleteById(nonExistingStoreId);
    }
}
