package com.domenico.wordle;

import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;

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
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        letters = wordToGuess.toUpperCase();
        for (int i=0; i<missingLetters; i++) {
            int rand = (int) (Math.random() * alphabet.length());
            letters += alphabet.charAt(rand);
        }
        ArrayList<Character> tempArLis = new ArrayList<Character>();
        for (int i=0; i<letters.length(); i++) {
            tempArLis.add(letters.charAt(i));
        }
        Collections.shuffle(tempArLis);
        letters = "";
        for (int i=0; i<tempArLis.size(); i++) {
            letters += tempArLis.get(i);
        }
        Log.i("Game", "Lettere: "+letters);
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
