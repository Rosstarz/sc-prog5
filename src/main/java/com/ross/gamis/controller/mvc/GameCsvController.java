package com.ross.gamis.controller.mvc;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ross.gamis.security.AdminOnly;
import com.ross.gamis.service.GameService;

@Controller
@RequestMapping("/game-csv")
public class GameCsvController {
    private final GameService gameService;

    public GameCsvController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping()
    @AdminOnly
    public ModelAndView showUploadCsv() {
        var mav = new ModelAndView("game/game-csv");
        mav.addObject("inProgress", false);
        return mav;
    }

    @PostMapping()
    @AdminOnly
    public ModelAndView uploadCsv(
            @RequestParam("games_csv") MultipartFile file)
            throws IOException {
        var mav = new ModelAndView("game/game-csv");
        if (!file.isEmpty()) {
            gameService.handleGamesCsv(file.getInputStream());
            mav.addObject("inProgress", true);
        }
        return mav;
    }
}
