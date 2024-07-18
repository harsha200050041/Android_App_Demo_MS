package com.example.fragmented_cric;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Objects;
import java.util.Random;


public class Cricket_Fragment extends Fragment {


    private int score = 0;
    TextView title;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cricket, container, false);

        title = view.findViewById(R.id.GameTitle);
        TextView yourScore =view.findViewById(R.id.YourScore);
        TextView Score = view.findViewById(R.id.Score);
        TextView Player_hand = view.findViewById(R.id.YourHand);
        TextView Computer_plays = view.findViewById(R.id.ComputerPlays);
        TextView Computer_hand = view.findViewById(R.id.ComputerHand);
        TextView Commentary = view.findViewById(R.id.Commentary);
        Button playButton = view.findViewById(R.id.PlayButton);
        Button replayButton = view.findViewById(R.id.button2);
        Button rulesButton = view.findViewById(R.id.button);
        ImageView Gif = view.findViewById(R.id.imageView);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Player_hand.getText().toString().isEmpty()) {

                    String str = "Please enter a number";
                    Toast.makeText(requireActivity().getApplicationContext(), str, Toast.LENGTH_LONG).show();
                }
                else {
                    int player_hand = Integer.parseInt(Player_hand.getText().toString());
                    if (player_hand>6 || player_hand<1){
                        String str = "Please enter a number between 1-6 only";
                        Toast.makeText(requireActivity().getApplicationContext(), str, Toast.LENGTH_LONG).show();

                    }
                    else {
                        Random random = new Random();

                        // Generate a random number between 1 and 6 (inclusive)
                        int computer_hand = random.nextInt(6) + 1;
                        Computer_hand.setText(Integer.toString(computer_hand));
                        if (computer_hand != player_hand) {
                            score += player_hand;
                            Score.setText(Integer.toString(score));
                            String commentary = "The Player has hit a " + player_hand;
                            Commentary.setText(commentary);
                            Glide.with(requireActivity().getApplicationContext())
                                    .load(R.drawable.cricket_yorker_dribbble)
                                    .into(Gif);

                        } else {
                            String commentary = "You are out ! Your final score is " + score;
                            Commentary.setText(commentary);
                            score -= score;
                            Score.setText(Integer.toString(score));
                            Glide.with(requireActivity().getApplicationContext())
                                    .load(R.drawable.bowled_out)
                                    .into(Gif);

                        }
                    }
                }
            }
        });

        rulesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity().getApplicationContext());
                builder.setTitle("Game Rules");
                builder.setMessage("Here are the rules . " + " 1. User inputs number from 1-6, computer randomly generates a number from 1-6. 2. If the two are not equal, the batter's score is incremented by their input. 3.  When the two numbers are equal, the batter is out, game over!" );

                // Add an "OK" button to dismiss the dialog
                builder.setPositiveButton("OK", (dialog, which) -> {
                    // Dismiss the dialog
                    dialog.dismiss();
                });

                // Show the dialog
                builder.create().show();
            }
        });


        return view;

    }



}