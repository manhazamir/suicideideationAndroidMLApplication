package com.example.suicideideation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HistoryScore extends AppCompatActivity {

    String testname;
    String date;
    int score;
    TextView testnameTextView;
    //TextView historyScore;
  //  TextView historyStatus;
    TextView historyDate;

    TextView HistrynormalText;
    TextView HistrymildText;
    TextView HistryModeratorText;
    TextView HistryseverText;
    TextView HistryextremeText;
    Button history_score_back;
    Button History_therapyList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_score);

        testnameTextView =  findViewById(R.id.testnameTextView);
//        historyScore =  findViewById(R.id.historyScore);
//        historyStatus =  findViewById(R.id.historyStatus);
        historyDate =  findViewById(R.id.historyDate);

        HistrynormalText = findViewById(R.id.HistrynormalText);
        HistrymildText = findViewById(R.id.HistrymildText);
        HistryModeratorText = findViewById(R.id.HistryModeratorText);
        HistryseverText = findViewById(R.id.HistryseverText);
        HistryextremeText = findViewById(R.id.HistryextremeText);
        history_score_back =  findViewById(R.id.history_score_back);

        History_therapyList = findViewById(R.id.History_therapyList);

        history_score_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        History_therapyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryScore.this, Therapistlist.class);
                startActivity(intent);
            }
        });


        Bundle getBundle = null;
        getBundle = this.getIntent().getExtras();

        if (!getBundle.isEmpty()) {
            testname = getBundle.getString("testName");
            score = getBundle.getInt("score");
            date = getBundle.getString("date");
        }

        testnameTextView.setText(testname);
        historyDate.setText(date);
//        historyScore.setText(String.valueOf(score));


        System.out.println("SCOREEEE " +score);

        if (testname.equals("Stress")) {
            //stress
            if(score <=14){
              //  historyStatus.setText("Normal");
                HistrynormalText.setBackgroundResource(R.drawable.back);
            } else if(score <=18){
                HistrymildText.setBackgroundResource(R.drawable.back);
            } else if(score <=25){
                //historyStatus.setText("Moderate");
                HistryModeratorText.setBackgroundResource(R.drawable.back);
            } else if(score <=33){
               // historyStatus.setText("Serve");
                HistryseverText.setBackgroundResource(R.drawable.back);
            }else if(score >= 34){
                //historyStatus.setText("Extremely serve");
                HistryextremeText.setBackgroundResource(R.drawable.back);
            }
        } else if (testname.equals("Anxiety")) {

            // anxiety

            if(score <=7){
                HistrynormalText.setBackgroundResource(R.drawable.back);

            } else if(score <=9){
                HistrymildText.setBackgroundResource(R.drawable.back);
            } else if(score <=14){
                HistryModeratorText.setBackgroundResource(R.drawable.back);
            } else if(score <=19){
                HistryseverText.setBackgroundResource(R.drawable.back);
            }else if(score >= 20){
                HistryextremeText.setBackgroundResource(R.drawable.back);
            }


        } else if (testname.equals("Depression")) {

            // depression
            if(score <=9){
                HistrymildText.setBackgroundResource(R.drawable.back);
            } else if(score <=13){
                HistryModeratorText.setBackgroundResource(R.drawable.back);
            } else if(score <=20){
                HistryseverText.setBackgroundResource(R.drawable.back);
            } else if(score <=27){
                HistryseverText.setBackgroundResource(R.drawable.back);
            }else if(score >= 28){
                HistryextremeText.setBackgroundResource(R.drawable.back);
            }


        } else if (testname == "Suicide") {
            // suicide
            //TestName.setText("Suicide");
        }




    }
}