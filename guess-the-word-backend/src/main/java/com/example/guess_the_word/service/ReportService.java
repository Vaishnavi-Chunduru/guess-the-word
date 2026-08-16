package com.example.guess_the_word.service;

import com.example.guess_the_word.dto.DailyReport;
import com.example.guess_the_word.dto.UserReport;
import com.example.guess_the_word.entity.Game;
import com.example.guess_the_word.entity.GameStatus;
import com.example.guess_the_word.entity.User;
import com.example.guess_the_word.repository.GameRepository;
import com.example.guess_the_word.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public ReportService(GameRepository gameRepository,
                         UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public DailyReport getDailyReport(
            LocalDate date) {

        long numberOfUsers =
                gameRepository
                        .countDistinctUsersByGameDate(
                                date
                        );

        long gamesPlayed =
                gameRepository
                        .countByGameDate(
                                date
                        );

        long correctGuesses =
                gameRepository
                        .countByGameDateAndStatus(
                                date,
                                GameStatus.WON
                        );

        List<String> users =
                gameRepository
                        .findUsersByGameDate(
                                date
                        );

        return new DailyReport(
                date,
                numberOfUsers,
                gamesPlayed,
                correctGuesses,
                users
        );
    }

    public List<UserReport> getUserReport(String username) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Game> games = gameRepository.findByUser(user);

        List<UserReport> report = new ArrayList<>();

        // Group games by date
        for (Game game : games) {

            LocalDate date = game.getGameDate();

            boolean alreadyAdded = report.stream()
                    .anyMatch(r -> r.getDate().equals(date));

            if (!alreadyAdded) {

                long wordsTried = games.stream()
                        .filter(g -> g.getGameDate().equals(date))
                        .count();

                long correctGuesses = games.stream()
                        .filter(g -> g.getGameDate().equals(date))
                        .filter(g -> g.getStatus() == GameStatus.WON)
                        .count();

                report.add(
                        new UserReport(
                                date,
                                wordsTried,
                                correctGuesses
                        )
                );
            }
        }

        report.sort(
                (a, b) -> b.getDate().compareTo(a.getDate())
        );

        return report;
    }
}