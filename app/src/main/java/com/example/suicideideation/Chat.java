package com.example.suicideideation;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

public class Chat extends AppCompatActivity {
    private EditText editText;

    private String url = "http://192.168.18.76:5000/" ;
    boolean flag=false;
    Button chatBack;
    Button viewResult;
    Button scoreBtn;
    int state = 0;
    ListView listView;
    ImageButton recordButton;
    TextView listingText;
    ArrayList<String> Answers = new ArrayList<String>();
    ArrayList<String> questions = new ArrayList<String>();
    ArrayList<String> questionsSize = new ArrayList<String>();
    ArrayList<Integer> answersScore = new ArrayList<Integer>();
    ChatListAdapter chatListAdapter;
    SharedPreferences pref;
    int index = 1;
    int index2 = 0;
    TextToSpeech tts;

    String language = "";
    String languageVoice = "";
    String suicideScore = "";
    LinearLayout linearLayout;
    LinearLayout scorelayout;
    boolean textToSpeechIsInitialized = false;
    MediaPlayer mPlayer;

    boolean putDelay = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        editText = findViewById(R.id.text);
        chatBack = findViewById(R.id.chatBack);
        listView = findViewById(R.id.chatListView);
        recordButton = findViewById(R.id.recordButton);
        listingText = findViewById(R.id.listingText);
        scoreBtn = findViewById(R.id.scoreBtn);

        linearLayout = findViewById(R.id.micandTextID);
        scorelayout = findViewById(R.id.scorelayout);

        // scorelayout.setVisibility(View.VISIBLE);

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Answers.add("انتہائی شدید کسی حد تک");
//                answersScore.add(5);
//                index++;
//                getQuestions();

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, language);
                intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
                listingText.setText("Listening...");


                final SpeechRecognizer recognizer = SpeechRecognizer.createSpeechRecognizer(Chat.this);
                RecognitionListener listener = new RecognitionListener() {
                    @Override
                    public void onReadyForSpeech(Bundle bundle) {

                    }

                    @Override
                    public void onBeginningOfSpeech() {

                    }

                    @Override
                    public void onRmsChanged(float v) {

                    }

                    @Override
                    public void onBufferReceived(byte[] bytes) {

                    }

                    @Override
                    public void onEndOfSpeech() {
                        listingText.setText("");

                    }

                    @Override
                    public void onError(int i) {
                        listingText.setText("");
                    }

                    @Override
                    public void onResults(Bundle results) {
                        ArrayList<String> voiceResults = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                        Answers.add(voiceResults.get(0));
                        int score = 0;
                        if(questions.get(index2).contains("اپنا سیشن شروع کرتے")|| questions.get(index2).contains("Lets start our session") || flag==true){
                            flag=true;
                            if (    voiceResults.get(0).contains("هرگز نہیں") ||
                                    voiceResults.get(0).contains("کبھی نہیں") ||
                                    voiceResults.get(0).contains("بالکل نہیں") ||
                                    voiceResults.get(0).contains("نہیں") ||
                                    voiceResults.get(0).contains("کوئی نہیں") ||
                                    voiceResults.get(0).contains("ضروری نہیں") ||
                                    voiceResults.get(0).contains("مشکل")) {
                                score = 0;
                            }if (  voiceResults.get(0).contains("بہت کم") ||
                                    voiceResults.get(0).contains("کم") ||
                                    voiceResults.get(0).contains("کبھی کبھی") ||
                                    voiceResults.get(0).contains("بہت تھوڑا")) {
                                score = 1;
                            }if (  voiceResults.get(0).contains("کسی حد تک") ||
                                    voiceResults.get(0).contains("تھوڑا سا") ||
                                    voiceResults.get(0).contains("تھوڑا بہت") ||
                                    voiceResults.get(0).contains("کچھ حد تک") ||
                                    voiceResults.get(0).contains("کبھی کبھی") ||
                                    voiceResults.get(0).contains("چند لمحات ")) {
                                score = 2;
                            }if (  voiceResults.get(0).contains("شدید")||
                                    voiceResults.get(0).contains("کافی")||
                                    voiceResults.get(0).contains("بہت") ||
                                    voiceResults.get(0).contains("زیادہ") ||
                                    voiceResults.get(0).contains("تقریبا")||
                                    voiceResults.get(0).contains("جی ہاں") ||
                                    voiceResults.get(0).contains("ہاں") ||
                                    voiceResults.get(0).contains("ہوتا ہے")||
                                    voiceResults.get(0).contains("ہو جاتا ہوں") ||
                                    voiceResults.get(0).contains("جی") ||
                                    voiceResults.get(0).contains("ہوتی ہو")||
                                    voiceResults.get(0).contains("ہوتا ہوں") ||
                                    voiceResults.get(0).contains("بے شک") ||
                                    voiceResults.get(0).contains("درست")||
                                    voiceResults.get(0).contains("بالکل") ||
                                    voiceResults.get(0).contains("لگتا ہے") ){

                                score = 3;
                            }if (  voiceResults.get(0).contains("انتہائی شدید") ||
                                    voiceResults.get(0).contains("بہت شدید") ||
                                    voiceResults.get(0).contains("بہت زیادہ") ||
                                    voiceResults.get(0).contains("کافی زیادہ") ||
                                    voiceResults.get(0).contains("انتہائی زیادہ")||
                                    voiceResults.get(0).contains("روز") ||
                                    voiceResults.get(0).contains("آسانی سے") ||
                                    voiceResults.get(0).contains("ہر دوسرے دن ")||
                                    voiceResults.get(0).contains("دفعہ") ||
                                    voiceResults.get(0).contains("بار") ||
                                    voiceResults.get(0).contains("اکثر")||
                                    voiceResults.get(0).contains("مرتبہ") ){
                                score = 4;
                            }
                            answersScore.add(score);
                        }
                        index++;
                        index2++;
                        getQuestions();
                    }

                    @Override
                    public void onPartialResults(Bundle bundle) {

                    }

                    @Override
                    public void onEvent(int i, Bundle bundle) {

                    }
                };
                recognizer.setRecognitionListener(listener);
                recognizer.startListening(intent);
            }
        });


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
        }

        Bundle getBundle = null;
        getBundle = this.getIntent().getExtras();

        if (!getBundle.isEmpty()) {
            state = getBundle.getInt("state");

            if(state == 4){
                language = "en-us";
                languageVoice = "en-gb-x-rjs-network";
            } else{
                language="ur-pk";
                languageVoice = "ur-pk-x-urm-network";
            }
            Answers.add("dummy");
            getQuestions();
        }

        chatBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat.this, Home.class);
                startActivity(intent);
            }
        });

        scoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScoreCalcuator.class);
                intent.putExtra("answersScore", answersScore);
                intent.putExtra("suicideScore", suicideScore);
                intent.putExtra("state", state);
                startActivity(intent);
            }
        });
    }


    public void speech(String msg){
        tts = new TextToSpeech(Chat.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i == TextToSpeech.SUCCESS){
                    textToSpeechIsInitialized = true;
                    int result = tts.setLanguage(new Locale("ur"));
                    //  int result = tts.setLanguage(Locale.US);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                        Toast.makeText(Chat.this, "This Language is not supported", Toast.LENGTH_SHORT).show();

                    } else {

                        float speed = (float) 0.9;
                        tts.setSpeechRate(speed);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            for (Voice tmpVoice : tts.getVoices()) {
                                if(tmpVoice.getName().equals(languageVoice)){
                                    tts.setVoice(tmpVoice);
                                }
                            }
                        }else{
                            Toast.makeText(Chat.this, "Api level not enough", Toast.LENGTH_SHORT).show();
                        }
                        tts.speak(msg, TextToSpeech.QUEUE_ADD, null);
                    }
                }
            }
        });

    }


    public void getQuestions() {
        String QuestionMenu = "";
        if (state == 1) {
            QuestionMenu = "StressQuestion";
        } else if (state == 2) {
            QuestionMenu = "AnxietyQuestions";
        } else if (state == 3) {
            QuestionMenu = "DepressionQuestions";
        } else if (state == 4) {
            QuestionMenu = "SuicideQuestions";
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Questions").child(QuestionMenu);
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (index == 1) {
                            for (int i = 1; i <= dataSnapshot.getChildrenCount(); i++) {
                                questionsSize.add(dataSnapshot.child("Q" + i).getValue().toString());
                            }
                        }

                        if (questionsSize.size() >= index) {
                            questions.add(dataSnapshot.child("Q" + index).getValue().toString());
                            putDelay = false;
                            initialiae(questions, Answers,putDelay,state);
                            Handler handler = new Handler();

                            handler.postDelayed(new Runnable()
                            {
                                public void run() {
                                    putDelay = true;
                                    initialiae(questions, Answers,putDelay,state);
                                    if(questionsSize.size() == questions.size()){
                                        PostAPI();
                                        linearLayout.setVisibility(View.GONE);
                                        scorelayout.setVisibility(View.VISIBLE);
                                    }
                                    speech(dataSnapshot.child("Q" + index).getValue().toString());
                                }
                            }, 4000);
                            //speech(dataSnapshot.child("Q" + index).getValue().toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println(databaseError.getMessage().toString());
                    }
                });
    }

    public void initialiae(ArrayList<String> text, ArrayList<String> Answers, boolean putDelay, int state) {
        chatListAdapter = new ChatListAdapter(this, text, Answers, putDelay, state);
        listView.setAdapter(chatListAdapter);
    }

    public void PostAPI()
    {

        JSONObject params = new JSONObject();
        JSONObject header = new JSONObject();
        JSONArray paramsarr = new JSONArray(Answers);

        try {
            params.put("chat", paramsarr);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        HTTPrequest.callAPI("Post", url, params, header, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject res = new JSONObject(result);
                    JSONArray moshi = res.getJSONArray("scores");
                    ArrayList<String> listdata = new ArrayList<String>();
                    for (int i=0;i<moshi.length();i++){
                        listdata.add(moshi.getString(i));
                    }
                    //responses received in listdata

                    for (int i=0; i<listdata.size(); i++) {
                        Log.d("beo", listdata.get(i));
                    }
                    suicideScore = listdata.get(0);
                    Toast.makeText(Chat.this, "", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {

                    System.out.println("you had" + e);
                }
            }

            @Override
            public void onFaliure(String faliure) {
                Toast.makeText(Chat.this, "", Toast.LENGTH_SHORT).show();
                return;
            }
        },this);


    }


//    public void TextToSpeech(String inputText) {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    String accessToken = "5bc6034e-bf53-48dc-9bef-e92cdcd119b4";
//                    String JSON_MSG = "{ \"text\" : \"" + inputText + "\" , \"token\" : \"" + accessToken + "\", \"voice\" : \"CLE_Naghma1\", \"rate\" : \"" + 1 + "\" , \"volume\" : \"" + 100 + "\"}";
//
//                    System.out.println("inputText "+inputText);
//                    String IP = "api.cle.org.pk";
//                    String postURL = "https://" + IP + "/v1/synth";
//                    HttpClient httpClient = HttpClientBuilder.create().build();
//                    HttpPost post = new HttpPost(postURL);
//                    StringEntity postingString = new StringEntity(JSON_MSG, "UTF-8");
//                    post.setEntity(postingString);
//                    post.setHeader("Content-type", "application/json;odata=verbose");
//                    HttpResponse response = httpClient.execute(post);
//                    String JSON_Response = convertStreamToString(response.getEntity().getContent());
//
//                    JSONObject obj = new JSONObject(JSON_Response);
//                    JSONObject obj2 = obj.getJSONObject("response");
//                    String file = obj2.getString("encodedFile");
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//        });
//        thread.start();
//    }



    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    //setContentView(R.layout.chat);
//        Button c1 = findViewById(R.id.button);
//        c1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Depressionscore.class);
//                startActivity(intent);
//            }
//        });
//        Button back2 = findVi00ewById(R.id.b);
//        back2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Depressionscore.class);
//                startActivity(intent);
//            }
//        });


}
