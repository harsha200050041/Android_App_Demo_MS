package com.example.task;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.task.controller.DisplayUtils;


public class MainActivity extends AppCompatActivity {

    static final String userkey="userId";
    static final String passkey ="password";

    private EditText userId, password, captcha;
    private Button login;
    private TextView captchaDisplay, newUser;
    private String captchaCode;

    // database the model view
    database d = new database();
    DisplayUtils displayUtils = new DisplayUtils();

    // for watching whether some one have entered details or not
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    // checking whether all place are filled or not
    private void checkFieldsForEmptyValues() {
        String text1 = userId.getText().toString();
        String text2 = password.getText().toString();
        String text3 = captcha.getText().toString();
        String text4 = captchaDisplay.getText().toString();

        login.setEnabled(!text1.isEmpty() && !text2.isEmpty() && !text3.isEmpty() && text3.equals(text4));
    }

    // displaying captcha
    private void generateCaptcha() {
        captchaCode = displayUtils.generateRandomCaptcha();
        captchaDisplay.setText(captchaCode);
    }

    // getting all view address
    void viewSet()
    {
        userId = findViewById(R.id.editTextUserId);
        password = findViewById(R.id.editTextPassword);
        captcha = findViewById(R.id.editTextCaptcha);
        login = findViewById(R.id.buttonLogin);
        captchaDisplay = findViewById(R.id.textViewcaptcha);
        newUser = findViewById(R.id.textViewNewUser);
    }

    // setting watcher
    private void watcher()
    {
        userId.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);
        captcha.addTextChangedListener(textWatcher);
    }

    // after login task if enter proper data then or else clear the view
    private void afterLogin( boolean flag)
    {
        if(flag)
        {
            // if details are true
            String text1 = userId.getText().toString();
            String text2 = password.getText().toString();

            Intent intent = new Intent(this,FirstPage.class);
            intent.putExtra(userkey,text1);
            intent.putExtra(passkey,text2);
            startActivity(intent);
        }
        else
        {
            // if details are wrong
            displayUtils.displayWrongCredentials(this,"U Have Entered Wrong Credentials");
            generateCaptcha();
            watcher();
        }
        userId.setText("");
        password.setText("");
        captcha.setText("");
    }

    // onCreate First State
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // adding details
        d.addAdminDetail();

        // Call viewSet method from ViewUtils to initialize the views
        viewSet();

        // calling the function to generate and set captcha
        generateCaptcha();

        // to add text watcher in the view so that login button will get enabled
        watcher();

        // On login button
        login.setOnClickListener(v -> {
            // players names
            String user_userid = userId.getText().toString();
            String user_password = password.getText().toString();

            boolean flag = d.validateUser(user_userid,user_password);

            // to carryout the further task
            afterLogin(flag);
        });


        // On new User account creation
        newUser.setOnClickListener(v->{
            Intent signupintent = new Intent(this,signpage.class);
            startActivity(signupintent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        watcher();
        generateCaptcha();
    }

    @Override
    protected void onStop() {
        super.onStop();
        userId.setText("");
        password.setText("");
        captcha.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        finishAndRemoveTask();
    }
}