package com.example.task;

import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class controller extends AppCompatActivity {

    // Creating a instance of View to access the database
    
    private EditText userId, password, captcha;
    private Button login;
    private TextView captchaDisplay;
    private String captchaCode;

    // Check whether all fields entered are not so then the login button will on
    private void checkFieldsForEmptyValues() {
        String text1 = userId.getText().toString();
        String text2 = password.getText().toString();
        String text3 = captcha.getText().toString();
        String text4 = captchaDisplay.getText().toString();

        login.setEnabled(!text1.isEmpty() && !text2.isEmpty() && !text3.isEmpty() && text3.equals(text4));
    }

    // Generate random Captcha
    private String generateRandomCaptcha() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int charType = random.nextInt(3);
            switch (charType) {
                case 0:
                    captcha.append((char) (random.nextInt(10) + '0')); // Digits
                    break;
                case 1:
                    captcha.append((char) (random.nextInt(26) + 'A')); // Uppercase letters
                    break;
                case 2:
                    captcha.append((char) (random.nextInt(26) + 'a')); // Lowercase letters
                    break;
            }
        }
        return captcha.toString();
    }

    // displaying captcha
    public void generateCaptcha() {

        Log.i(TAG, "generateCaptcha: ");
        captchaCode = generateRandomCaptcha();
        captchaDisplay.setText(captchaCode);
    }

    // getting all view address
    public void viewSet()
    {
        Log.i(TAG, "viewSet: ");
        userId = findViewById(R.id.editTextUserId);
        password = findViewById(R.id.editTextPassword);
        captcha = findViewById(R.id.editTextCaptcha);
        login = findViewById(R.id.buttonLogin);
        captchaDisplay = findViewById(R.id.textViewcaptcha);
    }

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

    // setting watcher
    public void watcher()
    {
        userId.addTextChangedListener(textWatcher);
        password.addTextChangedListener(textWatcher);
        captcha.addTextChangedListener(textWatcher);
    }

    // Dialog box to view detail to the user when enter wrong credentials
    private void display()
    {
        // Creating and displaying a simple AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wrong Credentials")
                .setMessage("U have Entered Wrong Credentials")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })
                .show();

    }

    // after login task if enter proper data then to new page or else clear the view nad the dialog box
    private void afterLogin( boolean flag)
    {
        if(flag)
        {
            // if details are true
            Intent intent = new Intent(this,FirstPage.class);
            startActivity(intent);
        }
        else
        {
            // if details are wrong
            display();
            generateCaptcha();
            watcher();
        }

        userId.setText("");
        password.setText("");
        captcha.setText("");
    }

    public void loginButton()
    {
        database d = new database();
        // players names
        String user_userid = userId.getText().toString();
        String user_password = password.getText().toString();

        boolean flag = d.validateUser(user_userid,user_password);

        // to carryout the further task
        afterLogin(flag);
    }

    public void newUserButton()
    {
        Intent signupintent = new Intent(this,signpage.class);

        userId.setText("");
        password.setText("");
        captcha.setText("");

        startActivity(signupintent);
    }


}
