package com.example.hangman;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            // Create an instance of GameFragment
            Hangman hangman = new Hangman();

            // Add the fragment to the container
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, hangman)
                    .commit();
        }
    }

}