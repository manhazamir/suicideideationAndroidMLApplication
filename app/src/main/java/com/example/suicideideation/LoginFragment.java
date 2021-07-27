package com.example.suicideideation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
    private TextView register;
    private EditText editTextEmail,editTextPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        editTextEmail = (EditText) view.findViewById(R.id.editTextTextEmailAddress);
        editTextPassword = (EditText) view.findViewById(R.id.editTextTextPassword);
       // return inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        Button btn =(Button) getView().findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), register.class);
                startActivity(intent);
            }
        });

        Button btn1 =(Button) getView().findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                if(TextUtils.isEmpty(email)){
                    editTextEmail.setError("Enter Email");
                }else if(TextUtils.isEmpty(password)){
                    editTextPassword.setError("Enter Password");
                }else{
                    userLogin(v,email,password);
                }
            }
        });

        TextView txt =(TextView) getView().findViewById(R.id.textView);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ForgotPassword1.class);
                startActivity(intent);
            }
        });

    }
    public void userLogin(View v,String email,String password)
    {
        if(email.isEmpty())
        {
            editTextEmail.setError("please enter a valid email");
            editTextEmail.requestFocus();
        }
        if(password.isEmpty())
        {
            editTextPassword.setError("please enter correct password");
            editTextEmail.requestFocus();
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //startActivity(new Intent(LoginFragment.this,activity_home.class));
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    Intent intent = new Intent(getContext(), Home.class);
                    startActivity(intent);
//                    if(user.isEmailVerified())
//                    {
//                        Intent intent = new Intent(getContext(), Home.class);
//                        startActivity(intent);
//                    }
//                    else
//                    {
//                        user.sendEmailVerification();
//                        Toast.makeText(getActivity(),"Check your email to verify",Toast.LENGTH_LONG);
//                    }

                }
                else
                {
                   Toast.makeText(getActivity(),"Failed to Login, Enter correct credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}