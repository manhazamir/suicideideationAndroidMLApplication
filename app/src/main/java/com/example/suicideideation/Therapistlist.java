package com.example.suicideideation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class Therapistlist extends AppCompatActivity {
    ListView listView;
    ListAdapter listAdapter;
    TextView hidden;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.therapistlist);

        ArrayList<String> name = new ArrayList<String>();
        ArrayList<String> id = new ArrayList<String>();
        listView = findViewById(R.id.listView);
        hidden = findViewById(R.id.hidden);
        backButton = findViewById(R.id.backList);

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("therapisList");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                            name.add(String.valueOf(childDataSnapshot.child("name").getValue()));
                            id.add(childDataSnapshot.getKey());
                        }
                        initialize(name, id);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
//
//        Button d1 = findViewById(R.id.d1);
//        d1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), TherapistDetails.class);
//                startActivity(intent);
//            }
//        });
//        Button d2 = findViewById(R.id.d2);
//        d2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), TherapistDetails.class);
//                startActivity(intent);
//            }
//        });
//        Button d3 = findViewById(R.id.d3);
//        d3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), TherapistDetails.class);
//                startActivity(intent);
//            }
//        });
//        Button back1 = findViewById(R.id.b);
//        back1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), TherapistDetails.class);
//                startActivity(intent);
//            }
//        });

    }

    public void initialize(ArrayList<String> name, ArrayList<String> id) {
        listAdapter = new ListAdapter(this, name, id);
        listView.setAdapter(listAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String contactId = ((TextView) view.findViewById(R.id.hidden)).getText().toString();
                Intent intent = new Intent(getApplicationContext(), TherapistDetails.class);
                intent.putExtra("contactId", contactId);
                startActivity(intent);

            }
        });


    }
}