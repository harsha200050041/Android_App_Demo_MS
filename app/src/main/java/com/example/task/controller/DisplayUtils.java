package com.example.task.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.Random;

public class DisplayUtils {

    public void displayWrongCredentials(Context context, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Wrong Credentials")
                .setMessage(message)
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

    public String generateRandomCaptcha() {

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
}
