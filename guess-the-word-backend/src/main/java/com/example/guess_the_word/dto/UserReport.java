package com.example.guess_the_word.dto;

import java.time.LocalDate;

// represent one row of the user report
public class UserReport {

    private LocalDate date;
    private long wordsTried;
    private long correctGuesses;

    public UserReport() {
    }

    public UserReport(LocalDate date, long wordsTried, long correctGuesses) {
        this.date = date;
        this.wordsTried = wordsTried;
        this.correctGuesses = correctGuesses;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getWordsTried() {
        return wordsTried;
    }

    public void setWordsTried(long wordsTried) {
        this.wordsTried = wordsTried;
    }

    public long getCorrectGuesses() {
        return correctGuesses;
    }

    public void setCorrectGuesses(long correctGuesses) {
        this.correctGuesses = correctGuesses;
    }
}