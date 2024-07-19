package com.example.fragmentapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        Button googleBtn = view.findViewById(R.id.buttonGoogle);
        googleBtn.setOnClickListener(v -> {
            // Implicit Intent
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://accounts.google.com/signin"));
            startActivity(i);
        });

        Button facebookBtn = view.findViewById(R.id.buttonFacebook);
        facebookBtn.setOnClickListener(v -> {
            // Implicit Intent
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
            startActivity(i);
        });

        Button twitterBtn = view.findViewById(R.id.buttonTwitter);
        twitterBtn.setOnClickListener(v -> {
            // Implicit Intent
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com/login"));
            startActivity(i);
        });

        Button linkedinBtn = view.findViewById(R.id.buttonLinkedin);
        linkedinBtn.setOnClickListener(v -> {
            // Implicit Intent
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/login"));
            startActivity(i);
        });

        Button instagramBtn = view.findViewById(R.id.buttonInstagram);
        instagramBtn.setOnClickListener(v -> {
            // Implicit Intent
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com"));
            startActivity(i);
        });

        Button steamBtn = view.findViewById(R.id.buttonSteam);
        steamBtn.setOnClickListener(v -> {
            // Implicit Intent
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://store.steampowered.com/login/"));
            startActivity(i);
        });

        Button githubBtn = view.findViewById(R.id.buttonGithub);
        githubBtn.setOnClickListener(v -> {
            // Implicit Intent
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/login"));
            startActivity(i);
        });

        Button appleBtn = view.findViewById(R.id.buttonApple);
        appleBtn.setOnClickListener(v -> {
            // Implicit Intent
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://appleid.apple.com/sign-in"));
            startActivity(i);
        });

        Button microsoftBtn = view.findViewById(R.id.buttonMicrosoft);
        microsoftBtn.setOnClickListener(v -> {
            // Implicit Intent
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://login.live.com/login.srf"));
            startActivity(i);
        });

        return view;
    }
}