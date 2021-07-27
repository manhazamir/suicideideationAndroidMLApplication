package com.example.suicideideation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ScoreCalcuator extends AppCompatActivity {

    // TextView scoreText;
    TextView TestName;
    //  TextView statusText;
    Button therapyList;
    String testname = "";
    Button backButton ;

    TextView normalText;
    TextView mildText;
    TextView ModeratorText;
    TextView severText;
    TextView extremeText;
    String suicideScore;

    LinearLayout scoreMainLayout;

    int index = 1;
    ArrayList<Integer> answersScore = new ArrayList<Integer>();
    int finalScore = 0;
    int state =0 ;
    private FirebaseAuth auth;
    Date currentTime ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_calcuator);
        auth = FirebaseAuth.getInstance();
        //scoreText = findViewById(R.id.scoreText);
        // TestName = findViewById(R.id.TestName);
        // statusText =  findViewById(R.id.statusText);
        therapyList = findViewById(R.id.therapyList);
        backButton = findViewById(R.id.scoreBack);
        TextView name;
        TextView age;

        normalText = findViewById(R.id.normalText);
        mildText = findViewById(R.id.mildText);
        ModeratorText = findViewById(R.id.ModeratorText);
        severText = findViewById(R.id.severText);
        extremeText = findViewById(R.id.extremeText);
        name =  findViewById(R.id.userName);
        age = findViewById(R.id.userAge);
        scoreMainLayout = findViewById(R.id.scoreMainLayout);
        currentTime = Calendar.getInstance().getTime();

        // normalText.setBackgroundResource(R.drawable.back);

        // Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(currentTime);

        Date d=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        String currentDateTimeString = sdf.format(d);


        Bundle getBundle = null;
        getBundle = this.getIntent().getExtras();

        if (!getBundle.isEmpty()) {
            answersScore = getBundle.getIntegerArrayList("answersScore");
            state = getBundle.getInt("state");
            suicideScore = getBundle.getString("suicideScore");
        }



//            if (state == 1) {
//                TestName.setText("Stress");
//            } else if (state == 2) {
//                TestName.setText("Anxiety");
//            } else if (state == 3) {
//                TestName.setText("Depression");
//            } else if (state == 4) {
//                TestName.setText("Suicide");
//            }



        for(int i=0 ; i<answersScore.size() ; i++){
            System.out.println("score : "+answersScore.get(i));
            finalScore = finalScore + answersScore.get(i);
            System.out.println("Final score : "+finalScore);
        }


        if (state == 1) {
            //stress
            if(finalScore <=14){
                normalText.setBackgroundResource(R.drawable.back);
            } else if(finalScore <=18){
                mildText.setBackgroundResource(R.drawable.back);
            } else if(finalScore <=25){
                ModeratorText.setBackgroundResource(R.drawable.back);
            } else if(finalScore <=33){
                severText.setBackgroundResource(R.drawable.back);
            }else if(finalScore >= 34){
                extremeText.setBackgroundResource(R.drawable.back);
            }
        } else if (state == 2) {
            // anxiety
            if(finalScore <=7){
                normalText.setBackgroundResource(R.drawable.back);
            } else if(finalScore <=9){
                mildText.setBackgroundResource(R.drawable.back);
            } else if(finalScore <=14){
                ModeratorText.setBackgroundResource(R.drawable.back);
            } else if(finalScore <=19){
                severText.setBackgroundResource(R.drawable.back);
            }else if(finalScore >= 20){
                extremeText.setBackgroundResource(R.drawable.back);
            }


        } else if (state == 3) {

            // depression
            if(finalScore <=9){
                normalText.setBackgroundResource(R.drawable.back);
            } else if(finalScore <=13){
                mildText.setBackgroundResource(R.drawable.back);
            } else if(finalScore <=20){
                ModeratorText.setBackgroundResource(R.drawable.back);;
            } else if(finalScore <=27){
                severText.setBackgroundResource(R.drawable.back);
            }else if(finalScore >= 28){
                extremeText.setBackgroundResource(R.drawable.back);
            }

        } else if (state == 4) {
            // suicide
            ModeratorText.setVisibility(View.INVISIBLE);
            severText.setVisibility(View.INVISIBLE);
            extremeText.setVisibility(View.INVISIBLE);

            normalText.setText("At suicidal risk");
            mildText.setText("Not at suicidal risk");

            if(suicideScore.equals("0")){
                mildText.setBackgroundResource(R.drawable.back);
            }else if(suicideScore.equals("1")){
                normalText.setBackgroundResource(R.drawable.back);
            }

            //TestName.setText("Suicide");
        }


        // scoreText.setText(String.valueOf(finalScore));


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });


        therapyList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Therapistlist.class);
                startActivity(intent);
            }
        });


        if (state == 1) {
            testname = "Stress";
        } else if (state == 2) {
            testname = "Anxiety";
        } else if (state == 3) {
            testname = "Depression";
        } else if (state == 4) {
            testname = "Suicide";
        }


        ArrayList array = new ArrayList<>();
        array.add(testname);
        array.add(finalScore);
        String userID = auth.getCurrentUser().getUid();
        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference("Users").child(userID);
        ref2.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("history").child(String.valueOf(formattedDate)).child(currentDateTimeString).setValue(array);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });



        // String userID = auth.getCurrentUser().getUid();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(userID);
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // dataSnapshot.getRef().child("email").setValue("l@abc.com");
                        name.setText(dataSnapshot.child("name").getValue().toString());
                        age.setText(dataSnapshot.child("age").getValue().toString());
                        scoreMainLayout.setVisibility(View.VISIBLE);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

    }

}