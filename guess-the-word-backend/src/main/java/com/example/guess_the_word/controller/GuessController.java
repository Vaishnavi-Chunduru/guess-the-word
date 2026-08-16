package com.example.guess_the_word.controller;

import com.example.guess_the_word.dto.GuessRequest;
import com.example.guess_the_word.dto.GuessResult;
import com.example.guess_the_word.service.GuessService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GuessController {

    private final GuessService guessService;

    public GuessController(GuessService guessService) {
        this.guessService = guessService;
    }

    @PostMapping("/{gameId}/guess")
    public ResponseEntity<?> submitGuess(
            @PathVariable Long gameId,
            @Valid @RequestBody GuessRequest request,
            Authentication authentication) {

        try {
            String username = authentication.getName();

            GuessResult result =
                    guessService.submitGuess(
                            gameId,
                            request,
                            username
                    );

            return ResponseEntity.ok(result);

        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}