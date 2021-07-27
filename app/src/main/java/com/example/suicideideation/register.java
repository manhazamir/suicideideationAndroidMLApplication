package com.example.suicideideation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kotlin.text.Regex;

import static java.util.regex.Pattern.CASE_INSENSITIVE;

public class register extends AppCompatActivity implements View.OnClickListener {

    public Button button;
    public RadioGroup radioGroup;
    private EditText inputEmail, inputPassword,inputName,inputAge,inputUsername;
    private Button  btnSignUp;
    RadioButton gender;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);
        // ...
//Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        btnSignUp = (Button) findViewById(R.id.button2);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        inputEmail = (EditText) findViewById(R.id.editTextTextEmailAddress);
        inputPassword = (EditText) findViewById(R.id.editTextNumberPassword);
        inputName = (EditText) findViewById(R.id.editTextTextPersonName);
        inputUsername = (EditText) findViewById(R.id.editTextTextPersonName2);
        inputAge = (EditText) findViewById(R.id.editTextNumber);

        radioGroup = findViewById(R.id.radioGroup);

//        arrayList.add("test2");

        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //btnResetPassword = (Button) findViewById(R.id.btn_reset_password);


    }



    public static boolean isValidEmailId(String email) {

        //String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        String ePattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        System.out.println("EMAIL "+email);
        System.out.println("ragex" +m.matches());
        return m.matches();
    }

    public String getGender(){
        String radiovalue="";

        if(radioGroup.getCheckedRadioButtonId()==-1){
        }else{
            radiovalue = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
        }
        return radiovalue;
    }


    private void registerUser() {
        String email=inputEmail.getText().toString().trim();
        String password=inputPassword.getText().toString().trim();
        String username=inputUsername.getText().toString().trim();
        String age=inputAge.getText().toString().trim();
        String name=inputName.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(getApplicationContext(), "Enter username!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(getApplicationContext(), "Enter age!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getApplicationContext(), "Enter name!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 8) {
            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 8 characters!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(getGender())){
            Toast.makeText(getApplicationContext(), "Select Gender!", Toast.LENGTH_SHORT).show();
            System.out.println("Select Gender!");
            return;
        }
        if(!isValidEmailId(email)){
            System.out.println("Invalid email format");
            Toast.makeText(getApplicationContext(), "Invalid Email format", Toast.LENGTH_SHORT).show();
            inputEmail.setError("Invalid Email format");
            return;
        }

        // progressBar.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    user u=new user(name,password,username,email,age,getGender());


                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(u).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(register.this, "User has been registered sucessfully", Toast.LENGTH_LONG).show();
                                // progressBar.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(register.this, Home.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(register.this, "User has not been registered sucessfully", Toast.LENGTH_LONG).show();
                                // progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(register.this, "User has not been registered sucessfully", Toast.LENGTH_LONG).show();
                    //progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}