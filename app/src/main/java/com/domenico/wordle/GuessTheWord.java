package com.domenico.wordle;

import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Daniele on 11/02/2017.
 */

public class GuessTheWord {

    private String wordToGuess;
    private String meaning;
    private String letters;
    private int maxNumberOfLetters;
    private boolean gameEnded;

    public GuessTheWord() {
        gameEnded = false;
    }

    public void generateLetters() {
        int missingLetters = maxNumberOfLetters - wordToGuess.length();
        String lettersToRandomize = "abcdefghijklmnopqrstuvwxyz";
        letters = wordToGuess;
        for (int i=0; i<missingLetters; i++) {
            int rand = (int) (Math.random() * lettersToRandomize.length());
            letters += lettersToRandomize.charAt(rand);
        }

        ArrayList<Character> tempArLis = new ArrayList<Character>();
        for (int i=0; i<letters.length(); i++) {
            tempArLis.add(letters.charAt(i));
        }
        Log.i("Game", String.valueOf(tempArLis));
    }

    public boolean isGameEnded(String wordTyped) {
        if (wordToGuess.equalsIgnoreCase(wordTyped)) {
            gameEnded = true;
        } else {
            gameEnded = false;
        }
        return gameEnded;
    }

    public String getWordToGuess() {
        return wordToGuess;
    }

    public void setWordToGuess(String wordToGuess) {
        this.wordToGuess = wordToGuess;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public int getMaxNumberOfLetters() {
        return maxNumberOfLetters;
    }

    public void setMaxNumberOfLetters(int maxNumberOfLetters) {
        this.maxNumberOfLetters = maxNumberOfLetters;
    }
}
