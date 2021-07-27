package com.example.suicideideation.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.suicideideation.Home;
import com.example.suicideideation.R;
import com.example.suicideideation.Therapistlist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    TextView profile_name ;
    TextView profile_email ;
    TextView profile_username ;
    TextView profile_age ;
    TextView profile_password ;
    TextView profile_gender;
    LinearLayout layout ;
    Button profile_back;
    private FirebaseAuth auth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        String userID = auth.getCurrentUser().getUid();
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        profile_name = root.findViewById(R.id.profile_name);
        profile_email = root.findViewById(R.id.profile_email);
        profile_username = root.findViewById(R.id.profile_username);
        profile_age = root.findViewById(R.id.profile_age);
        profile_password = root.findViewById(R.id.profilePassword);
        profile_gender =  root.findViewById(R.id.profile_gender);

        profile_back = root.findViewById(R.id.profile_back);

        layout = root.findViewById(R.id.profile_layout);

        profile_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });


                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(userID);
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                       // dataSnapshot.getRef().child("email").setValue("l@abc.com");

                        profile_name.setText(dataSnapshot.child("name").getValue().toString());
                        profile_email.setText(dataSnapshot.child("email").getValue().toString());
                        profile_username.setText(dataSnapshot.child("username").getValue().toString());
                        profile_age.setText(dataSnapshot.child("age").getValue().toString());
                        profile_password.setText(dataSnapshot.child("password").getValue().toString());
                        profile_gender.setText(dataSnapshot.child("gender").getValue().toString());
                        layout.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        //    final TextView textView = root.findViewById(R.id.text_gallery);
        return root;
    }
}