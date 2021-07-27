package com.example.suicideideation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class TherapistDetails extends AppCompatActivity {

    TextView name;
    TextView gender;
    TextView number;
    TextView email;
    TextView location;
    TextView age;
    LinearLayout therapyDetailLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_therapist_details);

        name = findViewById(R.id.name);
        gender = findViewById(R.id.gender);
        number = findViewById(R.id.number);
        email = findViewById(R.id.email);
        location = findViewById(R.id.location);
        age = findViewById(R.id.profile_age);
        therapyDetailLayout = findViewById(R.id.therapyDetailLayout);

        Bundle getBundle = null;
        getBundle = this.getIntent().getExtras();
        String contactId = getBundle.getString("contactId");

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("therapisList").child(contactId);
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        name.setText(dataSnapshot.child("name").getValue().toString());
                        gender.setText(dataSnapshot.child("gender").getValue().toString());
                        number.setText(dataSnapshot.child("number").getValue().toString());
                        email.setText(dataSnapshot.child("email").getValue().toString());
                        location.setText(dataSnapshot.child("location").getValue().toString());
                        age.setText(dataSnapshot.child("age").getValue().toString());
                        therapyDetailLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


        Button back0 = findViewById(R.id.b);
        back0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Therapistlist.class);
                startActivity(intent);
            }
        });
    }

}
