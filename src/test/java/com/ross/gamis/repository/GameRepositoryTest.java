// package com.ross.gamis.repository;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertNotNull;
// import static org.junit.jupiter.api.Assertions.assertThrows;

// import java.time.LocalDate;
// import java.time.LocalDateTime;

// import org.junit.jupiter.api.AfterAll;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.TestInstance;
// import org.junit.jupiter.api.function.Executable;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.ross.gamis.domain.Country;
// import com.ross.gamis.domain.Developer;
// import com.ross.gamis.domain.Game;
// import com.ross.gamis.domain.GameStore;
// import com.ross.gamis.domain.Store;

// import jakarta.validation.ConstraintViolationException;


// @SpringBootTest
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
// class GameRepositoryTest {
//     @Autowired
//     private GameRepository gameRepository;
//     @Autowired
//     private DeveloperRepository developerRepository;
//     @Autowired
//     private StoreRepository storeRepository;
//     @Autowired
//     private GameStoreRepository gameStoreRepository;

//     // private long gameIdNonExistent = 9999L;
//     // private int gameCount = 5;
//     // private long userId = 9999L;

//     private Game createdGame;
//     private Developer createdDeveloper;
//     private Store createdStoreOne;
//     private Store createdStoreTwo;
//     private Store createdStoreThree;
//     private GameStore createdGameStoreOne;
//     private GameStore createdGameStoreTwo;
//     private GameStore createdGameStoreThree;
//     // private CustomUserDetails userDetails;

//     @BeforeAll
//     public void setupAll() {
//         // Remove all games so that we can have a known state
//         // gameStoreRepository.deleteAll();
//         // gameRepository.deleteAll();
//         // Create entities for creating a new Game
//         createdDeveloper = developerRepository.save(new Developer("Developer 1", LocalDate.now(), Country.BRAZIL));
//         // createdStoreOne = storeRepository.save(new Store("Store 1", true, "https://store1.com/some/link/here"));
//         // createdStoreTwo = storeRepository.save(new Store("Store 2", true, "store1.com/here"));
//         // createdStoreThree = storeRepository.save(new Store("Store 3", false));
//         // userDetails = new CustomUserDetails("tester", "password", List.of(new SimpleGrantedAuthority(UserRole.ADMIN.getCode())), userId);
//     }
    
//     @AfterAll
//     public void tearDownAll() {
//         // storeRepository.delete(createdStoreOne);
//         // storeRepository.delete(createdStoreTwo);
//         // storeRepository.delete(createdStoreThree);
//         developerRepository.delete(createdDeveloper);
//     }

//     @BeforeEach
//     public void setupEach() {
//         createdGame = gameRepository.save(
//             new Game("Game title", "Game desc", createdDeveloper));
//         createdGameStoreOne = gameStoreRepository.save(new GameStore(createdGame, createdStoreOne, LocalDateTime.now(), 10.0));
//         createdGameStoreTwo = gameStoreRepository.save(new GameStore(createdGame, createdStoreTwo, LocalDateTime.now(), 9.99));
//         createdGameStoreThree = gameStoreRepository.save(new GameStore(createdGame, createdStoreThree, LocalDateTime.now(), 7.99));
//     }

//     @AfterEach
//     public void tearDownEach() {
//         gameStoreRepository.delete(createdGameStoreOne);
//         gameStoreRepository.delete(createdGameStoreTwo);
//         gameStoreRepository.delete(createdGameStoreThree);
//         gameRepository.delete(createdGame);
//     }


//     @Test
//     public void findAllFetchedDevsShouldReturnGamesWithDeveloper(){
//         // Arrange
//         // Act
//         var games = gameRepository.findAllFetchedDevs();
//         // Assert
//         assertNotNull(games);
//         assertFalse(games.isEmpty());
//         assertEquals(games.get(0).getDeveloper().getId(), createdDeveloper.getId());
//         assertEquals(games.get(0).getTitle(), createdGame.getTitle());
//     }

//     @Test
//     public void gameTitleShouldNotBeBlank(){
//         // Arrange
//         Developer createdDeveloper = developerRepository.save(new Developer("deve", LocalDate.now(), Country.BRAZIL));

//         // Act
//         Executable executable = () -> gameRepository.save(new Game("", "desc", createdDeveloper));

//         // Assert
//         assertThrows(ConstraintViolationException.class, executable);

//         // Clean up
//         developerRepository.delete(createdDeveloper);
//     }
// }
