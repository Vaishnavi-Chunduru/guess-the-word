package com.example.guess_the_word.repository;

import com.example.guess_the_word.entity.Game;
import com.example.guess_the_word.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    // Count how many games a particular user played on a particular date
    // Check user's games for the daily limit - player functionality
    long countByUserAndGameDate(User user, LocalDate gameDate);

    //  Find all games played by this particular user. - for user report
    // Get a user's complete game history
    List<Game> findByUser(User user);

    // Find all games played on a particular date.- admin daily report
    List<Game> findByGameDate(LocalDate gameDate);

    // Counts all the games played on that particular day and how many games actually won by the users
    // Count WON/LOST/etc. games on a particular day
    long countByGameDateAndStatus(
            LocalDate gameDate,
            com.example.guess_the_word.entity.GameStatus status
    );

    // Count the unique players who played on that particular day
    @Query("""
    SELECT COUNT(DISTINCT g.user.id)
    FROM Game g
    WHERE g.gameDate = :gameDate
""")
    long countDistinctUsersByGameDate(@Param("gameDate") LocalDate gameDate);


    // Find games belonging to a particular user on a particular date and sort them by game date in descending order.
    List<Game> findByUserAndGameDateOrderByGameDateDesc(
            User user,
            LocalDate gameDate
    );


    long countByGameDate(LocalDate date);

    @Query("""
       SELECT DISTINCT g.user.username
       FROM Game g
       WHERE g.gameDate = :date
       """)
    List<String> findUsersByGameDate(LocalDate date);
}