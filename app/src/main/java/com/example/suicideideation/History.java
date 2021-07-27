package com.example.suicideideation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.suicideideation.ui.history.HistoryFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    String date;
    private FirebaseAuth auth;
    ListView testListView;
    Button history_score_back1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        testListView =  findViewById(R.id.testList);
        history_score_back1 = findViewById(R.id.history_score_back1);

        Bundle getBundle = null;
        getBundle = this.getIntent().getExtras();

        if (!getBundle.isEmpty()) {
            date = getBundle.getString("date");
        }

        history_score_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        auth = FirebaseAuth.getInstance();
        String userID = auth.getCurrentUser().getUid();
        ArrayList<String> list  = new ArrayList<String>();
        ArrayList<String> score  = new ArrayList<String>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(userID);
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds: dataSnapshot.child("history").child(date).getChildren()){
                            String testList = ds.child("0").getValue().toString();
                            String testScore = ds.child("1").getValue().toString();
                            list.add(testList);
                            score.add(testScore);
                        }

                      HistoryAdapter  historyAdapter = new HistoryAdapter(getApplicationContext(),list,score);
                        testListView.setAdapter(historyAdapter);

                        testListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                String score = ((TextView) view.findViewById(R.id.hiddenScore)).getText().toString();
                                String testName = ((TextView) view.findViewById(R.id.dateAndTimeText)).getText().toString();
                                System.out.println("SCORE "+score);

                                Intent intent = new Intent(getApplicationContext(), HistoryScore.class);
                                intent.putExtra("score", Integer.parseInt(score));
                                intent.putExtra("testName", testName);
                                intent.putExtra("date", date);

                                startActivity(intent);
                            }
                        });
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });






//
//        ImageButton ab1 = findViewById(R.id.a1);
//        ab1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Depressionhistory.class);
//                startActivity(intent);
//            }
//        });
//
//        ImageButton ab2 = findViewById(R.id.a2);
//        ab2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Anxietyhistory.class);
//                startActivity(intent);
//            }
//        });
//
//        ImageButton ab3 = findViewById(R.id.a3);
//        ab3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Stresshistory.class);
//                startActivity(intent);
//            }
//        });
//
//        ImageButton ab4 = findViewById(R.id.a4);
//        ab4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Suicidehistory.class);
//                startActivity(intent);
//            }
//        });
    }

}
