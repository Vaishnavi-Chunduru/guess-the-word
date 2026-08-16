package com.example.guess_the_word.controller;

import com.example.guess_the_word.entity.Game;
import com.example.guess_the_word.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


// For starting the game
@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/start")
    public ResponseEntity<?> startGame(Authentication authentication) {

        try {
            // Get username from JWT
            String username = authentication.getName();

            Game game = gameService.startGame(username);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Game started successfully. Game ID: " + game.getId());

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}