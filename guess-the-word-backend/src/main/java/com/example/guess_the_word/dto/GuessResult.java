package com.example.guess_the_word.dto;

import java.util.List;

// For getting the result for the user's guess
public class GuessResult {

    private String guess;
    private List<String> result;
    private String correctWord;
    private boolean correct;
    private boolean gameOver;
    private String message;

    public GuessResult() {
    }

    public GuessResult(String guess,
                       List<String> result,
                       boolean correct,
                       boolean gameOver,
                       String message,String correctWord) {
        this.guess = guess;
        this.result = result;
        this.correct = correct;
        this.gameOver = gameOver;
        this.message = message;
        this.correctWord = correctWord;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCorrectWord() {
        return correctWord;
    }

    public void setCorrectWord(String correctWord) {
        this.correctWord = correctWord;
    }
}

