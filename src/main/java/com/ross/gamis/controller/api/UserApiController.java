package com.ross.gamis.controller.api;

import org.springframework.web.bind.annotation.RestController;

import com.ross.gamis.controller.api.dto.game.out.GameDtoOut;
import com.ross.gamis.controller.api.dto.user.UserDtoOut;
import com.ross.gamis.controller.api.dto.usergamestore.out.UserGameStoreDtoOut;
import com.ross.gamis.converter.UserGameStoreConverter;
import com.ross.gamis.domain.Game;
import com.ross.gamis.domain.User;
import com.ross.gamis.domain.UserGameStore;
import com.ross.gamis.security.CustomUserDetails;
import com.ross.gamis.service.UserGameStoreService;
import com.ross.gamis.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/users")
public class UserApiController {
    private final UserGameStoreService userGameStoreService;
    private final UserService userService;
    private final UserGameStoreConverter userGameStoreConverter;
    private final Logger logger;
    
    public UserApiController(UserGameStoreService userGameStoreService, UserService userService, UserGameStoreConverter userGameStoreConverter, Logger logger) {
        this.userGameStoreService = userGameStoreService;
        this.userService = userService;
        this.userGameStoreConverter = userGameStoreConverter;
        this.logger = logger;
    }

    @GetMapping("/current")
    public ResponseEntity<UserDtoOut> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails){
        UserDtoOut userDtoOut = new UserDtoOut();
        userDtoOut.setUserId(userDetails.getId());
        userDtoOut.setUsername(userDetails.getUsername());
        return new ResponseEntity<>(userDtoOut, HttpStatus.OK);
    }

    @GetMapping("/games")
    public ResponseEntity<List<UserGameStoreDtoOut>> getGamesOwned(@AuthenticationPrincipal CustomUserDetails userDetails) {
        if (userService.isUserExist(userDetails.getId())) {
            List<UserGameStore> userGameStores = userGameStoreService.getAllUserGameStoreOwned(userDetails.getId());
            if (userGameStores == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            // Debugging
            for (UserGameStore userGameStore : userGameStores) {
                logger.debug("UserGameStore: {}", userGameStore.toString());
            }
            List<UserGameStoreDtoOut> userGameStoreDtoOuts = userGameStores.stream()
                .map(userGameStoreConverter::convertToDto)
                .toList();
            // Debugging
            for (UserGameStoreDtoOut userGameStore : userGameStoreDtoOuts) {
                logger.debug("UserGameStoreDtoOut: {}", userGameStore.toString());
                logger.debug("UserGameStoreDtoOut GameStore: {}", userGameStore.getGameStore().toString());
            }
            return new ResponseEntity<>(
                userGameStoreDtoOuts,
                HttpStatus.OK
            );
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/games/{gameStoreId}")
    public ResponseEntity<Void> setGameOwnership(@PathVariable("gameStoreId") Long gameStoreId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        UserGameStore userGameStore = userGameStoreService.setUserGameStoreOwned(userDetails.getId(), gameStoreId);
        if (userGameStore == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
