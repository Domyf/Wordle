package com.domenico.wordle;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.domenico.wordle.UI.HDTMButton;
import com.domenico.wordle.UI.HDTMTextView;

public class GameActivity extends AppCompatActivity {

    private GuessTheWord game;
    private WordList wordList;
    private String words_table;
    private int dictionaryLength;
    private int rowToGuess;
    private int lastItemClicked;
    private TextView txtCoins;
    private HDTMTextView txtWordToGuess;
    private HDTMTextView txtMeaning;
    private HDTMButton btn11;
    private HDTMButton btn12;
    private HDTMButton btn13;
    private HDTMButton btn14;
    private HDTMButton btn15;
    private HDTMButton btn16;
    private HDTMButton btn21;
    private HDTMButton btn22;
    private HDTMButton btn23;
    private HDTMButton btn24;
    private HDTMButton btn25;
    private HDTMButton btn26;
    private int coins;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initLayout();
        coins = 0;
        wordList = new WordList(this);
        words_table = WordList.MEDIUM_WORDS_TABLE;
        dictionaryLength = wordList.getEasyWords();
        rowToGuess = getIntent().getExtras().getInt("rowToGuess");
        lastItemClicked = rowToGuess;
        initGame();
    }

    public void initLayout() {
        txtCoins = (TextView) findViewById(R.id.txtCoins);
        txtWordToGuess = (HDTMTextView) findViewById(R.id.txtWordToGuess);
        txtMeaning = (HDTMTextView) findViewById(R.id.title);
        btn11 = (HDTMButton) findViewById(R.id.btn11);
        btn12 = (HDTMButton) findViewById(R.id.btn12);
        btn13 = (HDTMButton) findViewById(R.id.btn13);
        btn14 = (HDTMButton) findViewById(R.id.btn14);
        btn15 = (HDTMButton) findViewById(R.id.btn15);
        btn16 = (HDTMButton) findViewById(R.id.btn16);
        btn21 = (HDTMButton) findViewById(R.id.btn21);
        btn22 = (HDTMButton) findViewById(R.id.btn22);
        btn23 = (HDTMButton) findViewById(R.id.btn23);
        btn24 = (HDTMButton) findViewById(R.id.btn24);
        btn25 = (HDTMButton) findViewById(R.id.btn25);
        btn26 = (HDTMButton) findViewById(R.id.btn26);
    }

    public void initGame() {
        game = new GuessTheWord();
        game.setMaxNumberOfLetters(12);
        game.setWordToGuess(wordList.getWord(rowToGuess, words_table));
        while (game.getWordToGuess().length() >= game.getMaxNumberOfLetters()) {
            rowToGuess++;
            game.setWordToGuess(wordList.getWord(rowToGuess, words_table));
        }
        game.setMeaning(wordList.getMeaning(rowToGuess, words_table));
        txtMeaning.setText(game.getMeaning());
        Log.i("Game", "Parola da indovinare: "+game.getWordToGuess()+"\nDefinizione: "+game.getMeaning());
        game.generateLetters();
        setKeyboardUI();
    }

    public void setKeyboardUI(){
        btn11.setText(""+game.getLetters().charAt(0));
        btn12.setText(""+game.getLetters().charAt(1));
        btn13.setText(""+game.getLetters().charAt(2));
        btn14.setText(""+game.getLetters().charAt(3));
        btn15.setText(""+game.getLetters().charAt(4));
        btn16.setText(""+game.getLetters().charAt(5));
        btn21.setText(""+game.getLetters().charAt(6));
        btn22.setText(""+game.getLetters().charAt(7));
        btn23.setText(""+game.getLetters().charAt(8));
        btn24.setText(""+game.getLetters().charAt(9));
        btn25.setText(""+game.getLetters().charAt(10));
        btn26.setText(""+game.getLetters().charAt(11));
    }

    public void keyBoardOnCick(View view) {
        HDTMButton button = (HDTMButton) view;
        String buttonText = (String) button.getText();
        txtWordToGuess.setText(txtWordToGuess.getText()+buttonText.toUpperCase());
        button.setVisibility(View.INVISIBLE);
        if (game.isGameEnded((String)txtWordToGuess.getText())) {
            setCoinsUI();
            lastItemClicked++;
            Toast.makeText(this, "Hai indovinato!", Toast.LENGTH_SHORT).show();
            next();
        }
    }

    /** Imposta le monete sullo schermo con effetto incremento */
    private void setCoinsUI() {
        for (int i=0; i<10; i++) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    txtCoins.setText(""+(++coins));
                }
            }, i*30);
        }
    }

    public void next(){
        if (rowToGuess+1 >= dictionaryLength)
            rowToGuess = 1;
        else
            rowToGuess++;
        reset();
        initGame();
    }

    public void txtWordToGuessOnClick(View view) {
        reset();
    }

    public void backOnClick(View view) {
        back();
    }

    public void reset() {
        txtWordToGuess.setText("");
        btn11.setVisibility(View.VISIBLE);
        btn12.setVisibility(View.VISIBLE);
        btn13.setVisibility(View.VISIBLE);
        btn14.setVisibility(View.VISIBLE);
        btn15.setVisibility(View.VISIBLE);
        btn16.setVisibility(View.VISIBLE);
        btn21.setVisibility(View.VISIBLE);
        btn22.setVisibility(View.VISIBLE);
        btn23.setVisibility(View.VISIBLE);
        btn24.setVisibility(View.VISIBLE);
        btn25.setVisibility(View.VISIBLE);
        btn26.setVisibility(View.VISIBLE);
    }

    public void back() {
        if (txtWordToGuess.getText().length() >= 1) {
            String temp = "";
            setButtonsOnBack();
            for (int i = 0; i < txtWordToGuess.getText().length() - 1; i++)
                temp += txtWordToGuess.getText().charAt(i);
            txtWordToGuess.setText(temp);
        }
    }

    public void setButtonsOnBack() {
        char lastLetterTyped = txtWordToGuess.getText().charAt(txtWordToGuess.getText().length()-1);
        LinearLayout row = (LinearLayout) findViewById(R.id.firstRow);
        boolean done = false;
        for (int i=0; i<2; i++) {
            for (int j = 0; j < game.getMaxNumberOfLetters() / 2; j++) {
                HDTMButton button = (HDTMButton) row.getChildAt(j);
                if (button.getText().charAt(0) == lastLetterTyped && button.getVisibility() == View.INVISIBLE) {
                    button.setVisibility(View.VISIBLE);
                    done = true;
                    break;
                }
            }
            if (done)
                break;
            row = (LinearLayout) findViewById(R.id.secondRow);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("lastItemClickable", lastItemClicked);
        intent.putExtras(bundle);
        startActivity(intent);
        this.finish();
    }
}

