package com.example.guess_the_word.service;

import com.example.guess_the_word.entity.Game;
import com.example.guess_the_word.entity.GameStatus;
import com.example.guess_the_word.entity.User;
import com.example.guess_the_word.entity.Word;
import com.example.guess_the_word.repository.GameRepository;
import com.example.guess_the_word.repository.UserRepository;
import com.example.guess_the_word.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    private final WordRepository wordRepository;

    public GameService(GameRepository gameRepository,
                       UserRepository userRepository,
                       WordRepository wordRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
    }

    public Game startGame(String username) {

        // Find the user
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        LocalDate today = LocalDate.now();

        // Check how many games the user has played today
        long gamesToday = gameRepository.countByUserAndGameDate(user, today);

        if (gamesToday >= 3) {
            throw new RuntimeException(
                    "You have reached the maximum of 3 games for today"
            );
        }

        // Get all words
        List<Word> words = wordRepository.findAll();

        if (words.isEmpty()) {
            throw new RuntimeException("No words available");
        }

        // Pick a random word
        Random random = new Random();
        Word selectedWord = words.get(random.nextInt(words.size()));

        // Create a new game
        Game game = new Game();

        game.setUser(user);
        game.setWord(selectedWord);
        game.setGameDate(today);
        game.setStatus(GameStatus.IN_PROGRESS);
        game.setGuessesUsed(0);

        return gameRepository.save(game);
    }
}