package com.example.task;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.util.ArrayList;
import java.util.Objects;

public class database {

    static private ArrayList<String> name = new ArrayList<>();
    static private ArrayList<String> age = new ArrayList<>();
    static private ArrayList<String> phone = new ArrayList<>();
    static private ArrayList<String> userid = new ArrayList<>();
    static private ArrayList<String> pass = new ArrayList<>();

    public void set(String n, String a, String ph, String u, String p)
    {
        name.add(n);
        age.add(a);
        phone.add(ph);
        userid.add(u);
        pass.add(p);
    }

    public boolean validateUser(String u, String p)
    {
        int i;
        for(i=0;i<userid.size();i++)
        {
            if(Objects.equals(userid.get(i), u) && Objects.equals(pass.get(i), p))
                return true;
        }
        return false;
    }

    public String display(String u, String p)
    {
        int i;
        String message="";
        for(i=0;i<name.size();i++)
        {
            if(Objects.equals(userid.get(i), u) && Objects.equals(pass.get(i), p))
            {
                    message = "Name :"+name.get(i)+"\n"+"Age: "+age.get(i)+"\n"+"Phone no: "+phone.get(i)+"\n"+"UserId: "+userid.get(i)+"\n";
                    return message;
            }
        }
        return message;
    }

}
