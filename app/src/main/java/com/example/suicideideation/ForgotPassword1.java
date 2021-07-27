package com.example.suicideideation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPassword1 extends AppCompatActivity {

    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password1);

        button=(Button) findViewById(R.id.btn_reset_password);
        editText= (EditText) findViewById(R.id.edt_reset_email);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ForgotPassword1.this, ForgotPassword2.class);
                intent.putExtra("email",editText.getText().toString());
                startActivity(intent);
            }
        });
    }

}