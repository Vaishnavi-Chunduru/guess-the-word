package com.example.guess_the_word.repository;

import com.example.guess_the_word.entity.Game;
import com.example.guess_the_word.entity.Guess;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuessRepository extends JpaRepository<Guess, Long> {

    // Find all guesses for this game and return them in increasing guessNumber order.- for sequence in which the user has played
    List<Guess> findByGameOrderByGuessNumberAsc(Game game);
}