package com.example.suicideideation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword2 extends AppCompatActivity {

    public Button button2;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password2);
       // button2=(Button) findViewById(R.id.btn_reset_password11);

        Bundle getBundle = null;
        getBundle = this.getIntent().getExtras();
        String email = getBundle.getString("email");
        System.out.println("this is res "+email);
        auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            System.out.println("Email sent");
                            Toast.makeText(ForgotPassword2.this, "Reset link send to Email", Toast.LENGTH_LONG).show();
                           // Log.d(TAG, "Email sent.");
                        } else{
                            Toast.makeText(ForgotPassword2.this, "This Email dosent exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(ForgotPassword2.this,ForgotPassword3.class);
//                startActivity(intent);
//            }
//        });
    }

}