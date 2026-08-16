package com.example.guess_the_word.entity;

import jakarta.persistence.*;
import java.time.LocalDate;


// Maps to the guesses table in the mysql
@Entity
@Table(name = "guesses")
public class Guess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @Column(name = "guess_word", nullable = false, length = 5)
    private String guessWord;

    @Column(name = "guess_number", nullable = false)
    private Integer guessNumber;

    @Column(name = "guess_date", nullable = false)
    private LocalDate guessDate;

    public Guess() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getGuessWord() {
        return guessWord;
    }

    public void setGuessWord(String guessWord) {
        this.guessWord = guessWord;
    }

    public Integer getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(Integer guessNumber) {
        this.guessNumber = guessNumber;
    }

    public LocalDate getGuessDate() {
        return guessDate;
    }

    public void setGuessDate(LocalDate guessDate) {
        this.guessDate = guessDate;
    }
}