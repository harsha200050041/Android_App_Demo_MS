package com.example.task;

import java.util.ArrayList;
import java.util.Objects;

public class database {

    static private ArrayList<String> name = new ArrayList<>();
    static private ArrayList<String> age = new ArrayList<>();
    static private ArrayList<String> phone = new ArrayList<>();
    static private ArrayList<String> userid = new ArrayList<>();
    static private ArrayList<String> pass = new ArrayList<>();


    private boolean duplication(String u)
    {
        int i;
        for(i=0;i<userid.size();i++)
            if(Objects.equals(userid.get(i), u))
                return true;

        return false ;
    }

    // Will update the data in the database
    public boolean set(String n, String a, String ph, String u, String p)
    {
        boolean flag = duplication(u);
        if(flag)
        {
            // duplication is there
            return true ;
        }
        else
        {
            name.add(n);
            age.add(a);
            phone.add(ph);
            userid.add(u);
            pass.add(p);
            return false;
        }
    }

    // Will validate the User
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

    // Will display the data of the user
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

    public void addAdminDetail()
    {
        name.add("Admin");
        age.add("25");
        phone.add("9769711024");
        userid.add("admin");
        pass.add("1234");
    }



}
