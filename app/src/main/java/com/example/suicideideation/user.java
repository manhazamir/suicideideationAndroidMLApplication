package com.example.suicideideation;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class user {
    public String name,password,username,email,age,gender;

    public user(String name,String password,String username,String email,String age,String gender)
    {
        this.email=email;
        this.name=name;
        this.password=password;
        this.username=username;
        this.age=age;
        this.gender =gender;
    }
}

