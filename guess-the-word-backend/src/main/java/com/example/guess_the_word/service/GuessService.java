package com.example.guess_the_word.service;

import com.example.guess_the_word.dto.GuessRequest;
import com.example.guess_the_word.dto.GuessResult;
import com.example.guess_the_word.entity.Game;
import com.example.guess_the_word.entity.GameStatus;
import com.example.guess_the_word.entity.Guess;
import com.example.guess_the_word.repository.GameRepository;
import com.example.guess_the_word.repository.GuessRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GuessService {

    private final GameRepository gameRepository;
    private final GuessRepository guessRepository;

    public GuessService(GameRepository gameRepository,
                        GuessRepository guessRepository) {
        this.gameRepository = gameRepository;
        this.guessRepository = guessRepository;
    }

    public GuessResult submitGuess(Long gameId,GuessRequest request,String username){

        // Find the game
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        if (!game.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You are not allowed to play this game");
        }

        // Check whether the game has already ended
        if (game.getStatus() != GameStatus.IN_PROGRESS) {
            throw new RuntimeException("This game has already ended");
        }

        // Check maximum 5 guesses
        if (game.getGuessesUsed() >= 5) {
            throw new RuntimeException("Maximum of 5 guesses reached");
        }

        String guessWord = request.getGuess();
        String targetWord = game.getWord().getWord();

        /*
         * Result for each character:
         *
         * GREEN  -> correct letter and correct position
         * ORANGE -> correct letter but wrong position
         * GREY   -> letter does not exist in target word
         */
        List<String> result = new ArrayList<>();

        // Store character counts of target word
        int[] remainingLetters = new int[26];

        for (char c : targetWord.toCharArray()) {
            remainingLetters[c - 'A']++;
        }

        // First pass: exact matches
        for (int i = 0; i < 5; i++) {

            if (guessWord.charAt(i) == targetWord.charAt(i)) {
                result.add("GREEN");
                remainingLetters[guessWord.charAt(i) - 'A']--;
            } else {
                result.add(null);
            }
        }

        // Second pass: wrong-position matches / grey
        for (int i = 0; i < 5; i++) {

            if (result.get(i) != null) {
                continue;
            }

            char guessedChar = guessWord.charAt(i);

            if (remainingLetters[guessedChar - 'A'] > 0) {
                result.set(i, "ORANGE");
                remainingLetters[guessedChar - 'A']--;
            } else {
                result.set(i, "GREY");
            }
        }

        // Increase number of guesses
        int currentGuessNumber = game.getGuessesUsed() + 1;
        game.setGuessesUsed(currentGuessNumber);

        // Save the guess
        Guess guess = new Guess();
        guess.setGame(game);
        guess.setGuessWord(guessWord);
        guess.setGuessNumber(currentGuessNumber);
        guess.setGuessDate(LocalDate.now());

        guessRepository.save(guess);

        // Check if the player guessed correctly
        boolean correct = guessWord.equals(targetWord);

        if (correct) {

            game.setStatus(GameStatus.WON);
            gameRepository.save(game);

            return new GuessResult(
                    guessWord,
                    result,
                    true,
                    true,
                    "Congratulations! You guessed the word!",
                    null
            );
        }

        // Check whether all 5 guesses are used
        if (currentGuessNumber == 5) {

            game.setStatus(GameStatus.LOST);
            gameRepository.save(game);

            return new GuessResult(
                    guessWord,
                    result,
                    false,
                    true,
                    "Better luck next time!",
                    targetWord
            );
        }

        // Game continues
        gameRepository.save(game);

        return new GuessResult(
                guessWord,
                result,
                false,
                false,
                "Try again!",
                null
        );
    }
}