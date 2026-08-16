package com.example.guess_the_word.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// For validating the user's guess
public class GuessRequest {

    @NotBlank(message = "Guess is required")
    @Size(min = 5, max = 5, message = "Guess must contain exactly 5 letters")
    @Pattern(
            regexp = "^[A-Z]{5}$",
            message = "Guess must contain exactly 5 uppercase English letters"
    )
    private String guess;

    public GuessRequest() {
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }
}