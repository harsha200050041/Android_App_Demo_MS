package com.example.fragmentapp;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment1()).commit();

        Button btnFragment1 = findViewById(R.id.button1);
        Button btnFragment2 = findViewById(R.id.button2);

        btnFragment1.setOnClickListener(view ->
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment1()).commit());
        btnFragment2.setOnClickListener(view ->
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Fragment2()).commit());

    }


}