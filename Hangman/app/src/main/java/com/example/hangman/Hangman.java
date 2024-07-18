package com.example.hangman;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Hangman extends Fragment {

    private String[] animalWords = {"elephant", "giraffe", "hippopotamus"};
    private String[] colorWords = {"red", "blue", "green"};
    private String selectedWord;
    private StringBuilder clue;
    private int chancesLeft = 3;
    protected int hintsLeft;
    private Set<Character> GuessedCharacters = new HashSet<>();

    private TextView clueTextView;
    private TextView LetterCountTextView;
    private TextView chancesTextView;
    private TextView resultTextView;
    private TextView messageTextView;
    private EditText guessEditText;
    private ImageView hangmanImageView;
    private Button guessButton;
    private Button hintButton;
    private Button replayButton;
    private Button AdditionalHintButton;
    private Button animalButton;
    private Button colorButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hangman, container, false);

        clueTextView = view.findViewById(R.id.clueTextView); // this displays the word to be guessed in the form of dashes, which is updated.
        LetterCountTextView = view.findViewById(R.id.LetterCountTextView); // displays the number of letters in the word to be guessed.
        chancesTextView = view.findViewById(R.id.chancesTextView); // the number of chances left for the player.
        resultTextView = view.findViewById(R.id.resultTextView); // displays the failure/success messages.
        messageTextView = view.findViewById(R.id.messageTextView); // used to display messages to the user (eg. in getHints() method).
        guessEditText = view.findViewById(R.id.guessEditText); // stores the letter guessed by the user.
        hangmanImageView = view.findViewById(R.id.hangmanImageView); // the initial image to be displayed when the game is loaded.
        guessButton = view.findViewById(R.id.guessButton); // Button upon clicking runs the guessLetter() method.
        hintButton = view.findViewById(R.id.hintButton); // Button upon clicking runs the getHint() method.
        replayButton = view.findViewById(R.id.replayButton); // Button upon clicking restarts the game.
        AdditionalHintButton = view.findViewById(R.id.AdditionalHintButton);// Button upon clicking provides additional final hint and runs the getHint() method.
        animalButton = view.findViewById(R.id.animalButton); // Button for choosing the Animal theme.
        colorButton = view.findViewById(R.id.colorButton); // Button for choosing the Color theme.

        startNewGame();

        animalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTheme(animalWords);
            }
        });

        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTheme(colorWords);
            }
        });

        // One clicks on the guess button after inputting their guess. This click performs the guessLetter() method.
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessLetter();
            }
        });

        // The hint button provides the user with hint. The functionality of this button is found in the giveHint() method.
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveHint();
            }
        });

        // Replay allows a user to restart the game. This can be done during any point of the game.
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });

        // As mentioned in the Skills section of the problem statement, this button provides a final hint after the already allotted hints are used.
        AdditionalHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                giveHint();
            }
        });

        return view;
    }

    // This method starts a new game. This method is also called after a player clicks on the Replay button.
    private void startNewGame() {
        animalButton.setVisibility(View.VISIBLE);
        colorButton.setVisibility(View.VISIBLE);
        resultTextView.setText("");
        resultTextView.setVisibility(View.GONE);
        messageTextView.setText("");
        guessEditText.setText("");
        guessEditText.setVisibility(View.GONE);
        hangmanImageView.setImageResource(R.drawable.hangman3);
        guessButton.setEnabled(true);
        hintButton.setEnabled(true);
        replayButton.setVisibility(View.GONE);
        resultTextView.setVisibility(View.GONE);
        AdditionalHintButton.setVisibility(View.GONE);
        chancesTextView.setVisibility(View.GONE);
        LetterCountTextView.setVisibility(View.GONE);

        guessButton.setVisibility(View.GONE);
        hintButton.setVisibility(View.GONE);

        clueTextView.setText("Choose a theme");
    }

    private void selectTheme(String[] words) {
        animalButton.setVisibility(View.GONE);
        colorButton.setVisibility(View.GONE);
        guessButton.setVisibility(View.VISIBLE);
        hintButton.setVisibility(View.VISIBLE);
        guessEditText.setVisibility(View.VISIBLE);
        hangmanImageView.setImageResource(R.drawable.hangman3);
        guessButton.setEnabled(true);
        hintButton.setEnabled(true);
        replayButton.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.GONE);
        AdditionalHintButton.setVisibility(View.GONE);
        chancesTextView.setVisibility(View.VISIBLE);
        LetterCountTextView.setVisibility(View.VISIBLE);
        GuessedCharacters.clear();
        Random random = new Random(); // We use the Random class in JAVA to randomly choose a word from our predefined String array.
        selectedWord = words[random.nextInt(words.length)];
        clue = new StringBuilder();
        for (int i = 0; i < selectedWord.length(); i++) {
            clue.append("_ "); // Adding a space after dash is doable. We will only have to update only index accessing techniques with index * 2 due to the space.
        }
        clueTextView.setText("Word: " + clue.toString());
        LetterCountTextView.setText("Number of letters: " + selectedWord.length()); // displays the number of letters in the word.
        chancesLeft = 3;
        hintsLeft = Math.max(1, (selectedWord.length() / 2) - 1);
        chancesTextView.setText("Chances left: " + chancesLeft); // displays the chances left for the player.
    }

    private void guessLetter() {
        if (chancesLeft == 0) {
            return;
        }
        String guess = guessEditText.getText().toString().toLowerCase();
        if (guess.length() != 1) {
            messageTextView.setText("Enter a single letter");
            return; // If the user inputs more than a single letter or no input at all while clicking the guess button, the user is displayed this message.
        }

        boolean flag = false;
        char GuessedCharacter = guess.charAt(0);
        if (GuessedCharacters.contains(GuessedCharacter)) {
            flag = true;
            resultTextView.setText("You have already guessed " + GuessedCharacter + ". Please try another letter");
        } else {
            resultTextView.setVisibility(View.GONE);
        }
        // adding all input characters (guessed characters from users) in a hashset.
        GuessedCharacters.add(GuessedCharacter);

        boolean correctGuess = false;
        for (int i = 0; i < selectedWord.length(); i++) {
            if (selectedWord.charAt(i) == guess.charAt(0)) {
                clue.setCharAt(i * 2, guess.charAt(0));
                correctGuess = true;
                if (flag == true) {
                    resultTextView.setVisibility(View.VISIBLE);
                }
            }
        }

        if (correctGuess) {
            clueTextView.setText("Word: " + clue.toString());
            if (!clue.toString().contains("_")) {
                resultTextView.setText("Congratulations! You won!");
                resultTextView.setTextColor(Color.GREEN);
                resultTextView.setVisibility(View.VISIBLE);
                guessEditText.setVisibility(View.GONE);
                endGame();
            }
        } else if ((!correctGuess && flag) || (!correctGuess && !flag)) {
            chancesLeft--;
            chancesTextView.setText("Chances left: " + chancesLeft);
            updateHangmanImage();
            if (!correctGuess && flag) {
                resultTextView.setText("You have already guessed " + GuessedCharacter + ". Please try another letter");
                resultTextView.setVisibility(View.VISIBLE);
            }
            if (chancesLeft == 0) {
                resultTextView.setText("Game Over! The word was: " + selectedWord);
                AdditionalHintButton.setVisibility(View.GONE);
                resultTextView.setTextColor(Color.RED);
                guessEditText.setVisibility(View.GONE);
                resultTextView.setVisibility(View.VISIBLE);
                endGame();
            }
        }

        guessEditText.setText("");
        messageTextView.setText("");
    }

    private void giveHint() {
        if (hintsLeft > 0) {
            AdditionalHintButton.setVisibility(View.GONE);
            for (int i = 0; i < selectedWord.length(); i++) {
                if (clue.charAt(i * 2) == '_') {
                    clue.setCharAt(i * 2, selectedWord.charAt(i));
                    hintsLeft--;
                    clueTextView.setText("Word: " + clue.toString());
                    messageTextView.setText("");
                    return;
                }
            }
        } else {
            messageTextView.setText("No hints left:( Need additional hint?");
            hintsLeft = 1; // updating the count for final hint.
            AdditionalHintButton.setVisibility(View.VISIBLE);
            hintButton.setVisibility(View.GONE);
        }
    }

    private void updateHangmanImage() {
        switch (chancesLeft) {
            case 2: // image to be displayed when 2 chances are left
                hangmanImageView.setImageResource(R.drawable.hangman2);
                break;
            case 1: // when 1 chance is left
                hangmanImageView.setImageResource(R.drawable.hangman1);
                break;
            case 0: // when all chances end
                hangmanImageView.setImageResource(R.drawable.finish);
                break;
        }
    }

    private void endGame() {
        guessButton.setEnabled(false);
        guessButton.setVisibility(View.GONE);
        hintButton.setEnabled(false);
        hintButton.setVisibility(View.GONE);
    }
}
