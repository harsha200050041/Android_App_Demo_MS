package com.example.task;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class FirstPage extends AppCompatActivity {

    database d = new database();
    private TextView textViewDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_first_page);
        textViewDetails = findViewById(R.id.textViewDetails);

        Intent intent = getIntent();
        String u = intent.getStringExtra(MainActivity.userkey);
        String p= intent.getStringExtra(MainActivity.passkey);

        textViewDetails.setText(d.display(u,p));
    }
}