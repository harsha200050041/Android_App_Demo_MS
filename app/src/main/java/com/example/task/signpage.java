package com.example.task;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.task.controller.DisplayUtils;

public class signpage extends AppCompatActivity {

    database d = new database();
    DisplayUtils displayUtils = new DisplayUtils();

    private EditText editTextName, editTextAge, editTextPhoneNumber, editTextUserId, editTextPassword, editTextReenterPassword;
    private Button buttonSignup;

    private String name, phoneNumber, userId, password, age;

    // for watching whether some one have entered details or not
     TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean flag = validateInput();
            checkFieldsForEmptyValues(flag);
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    // checking whether all place are filled or not
    private void checkFieldsForEmptyValues(boolean flag) {
        buttonSignup.setEnabled(flag);
    }

    // To check whether user have entered all data properly or not
    private boolean validateInput() {
        name = editTextName.getText().toString();
        age = editTextAge.getText().toString();
        phoneNumber = editTextPhoneNumber.getText().toString();
        userId = editTextUserId.getText().toString();
        password = editTextPassword.getText().toString();
        String reenterPassword = editTextReenterPassword.getText().toString();

        if (name.isEmpty()) {
            editTextName.setError("Name is required");
            return false;
        }
        if (age.isEmpty()) {
            editTextAge.setError("Age is required");
            return false;
        }
        if (phoneNumber.length() != 10) {
            editTextPhoneNumber.setError("Valid 10-digit phone number is required");
            return false;
        }
        if (userId.isEmpty()) {
            editTextUserId.setError("User ID is required");
            return false;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            return false;
        }
        if (!password.equals(reenterPassword)) {
            editTextReenterPassword.setError("Passwords do not match");
            return false;
        }


        return true;
    }

    private void viewSet()
    {
        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextUserId = findViewById(R.id.editTextUserId);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextReenterPassword = findViewById(R.id.editTextReenterPassword);
        buttonSignup = findViewById(R.id.buttonSignup);

    }

    private void watcher() {
        editTextName.addTextChangedListener(textWatcher);
        editTextAge.addTextChangedListener(textWatcher);
        editTextPhoneNumber.addTextChangedListener(textWatcher);
        editTextUserId.addTextChangedListener(textWatcher);
        editTextPassword.addTextChangedListener(textWatcher);
        editTextReenterPassword.addTextChangedListener(textWatcher);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signpage);

        // to get the view
        viewSet();

        // to add text watcher in the view so that login button will get enabled
        watcher();

        // On login button
        buttonSignup.setOnClickListener(v -> {

            boolean flag = d.set(name, age, phoneNumber, userId, password);
            if(flag)
            {
                displayUtils.displayWrongCredentials(this,"Userid is already exits, Please Enter new Userid");
                reset();
                watcher();
            }
            else
            {
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }

        });

    }

    private void reset()
    {
        editTextName.setText("");
        editTextAge.setText("");
        editTextPhoneNumber.setText("");
        editTextUserId.setText("");
        editTextPassword.setText("");
        editTextReenterPassword.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();
        watcher();
    }

    @Override
    protected void onStop() {
        super.onStop();
        reset();
    }


}