package com.example.guess_the_word.dto;

import java.time.LocalDate;
import java.util.List;

// Represents the total report of the game
public class DailyReport {

    private LocalDate date;
    private long numberOfUsers;
    private long correctGuesses;
    private long gamesPlayed;
    private List<String> users;

    public DailyReport() {
    }

    public DailyReport(LocalDate date, long numberOfUsers, long correctGuesses,long gamesPlayed,List<String> users) {
        this.date = date;
        this.numberOfUsers = numberOfUsers;
        this.correctGuesses = correctGuesses;
        this.gamesPlayed = gamesPlayed;
        this.users = users;
    }

    public LocalDate getDate() {
        return date;
    }
    public long getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(long gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getNumberOfUsers() {
        return numberOfUsers;
    }

    public void setNumberOfUsers(long numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public long getCorrectGuesses() {
        return correctGuesses;
    }

    public void setCorrectGuesses(long correctGuesses) {
        this.correctGuesses = correctGuesses;
    }

    public List<String> getUsers() {
        return users;
    }
}