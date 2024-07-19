package com.example.task;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class WelcomePage extends Fragment {

    database d = new database();

    // TODO: Rename and change types of parameters
    private String u; // loginuserid
    private String p; // loginpassword

    public WelcomePage() {
        // Required empty public constructor
    }

    public WelcomePage(String username, String password){
        u = username;
        p = password;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
         View view=inflater.inflate(R.layout.fragment_welcome_page, container, false);
        // Initialize your views here and set the data
        TextView textViewDetails = view.findViewById(R.id.textViewDetails);
        textViewDetails.setText(d.display(u,p));
         return view;
    }


}