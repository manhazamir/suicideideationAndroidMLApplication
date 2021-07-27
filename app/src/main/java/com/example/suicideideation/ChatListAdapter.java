package com.example.suicideideation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;


public class ChatListAdapter extends ArrayAdapter {
    Context context;
    ArrayList<String> questions;
    ArrayList<String> Answers;
    TextView chatText1;
    TextView deChatMsg;
    LinearLayout layout;
    LinearLayout layout2;
    ImageView audioIcon;
    int state;
    boolean putDelay = false;


    public ChatListAdapter(@NonNull Context context, ArrayList<String> array, ArrayList<String> Answers, boolean putDelay, int state) {
        super(context, R.layout.item_in_message, R.id.deChatMsg, array);
        this.context = context;
        this.questions = array;
        this.Answers = Answers;
        this.putDelay = putDelay;
        this.state =  state;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_in_message, parent, false);
        }

        int text = position;


        chatText1 = view.findViewById(R.id.chatText);
        deChatMsg = view.findViewById(R.id.deChatMsg);

        layout = view.findViewById(R.id.userChatLayout);
        layout2 = view.findViewById(R.id.drBotChat);

        audioIcon = view.findViewById(R.id.AudioImage);


        if(state == 4){
            deChatMsg.setGravity(Gravity.START);
        } else{
            deChatMsg.setGravity(Gravity.END);
        }

        if (Answers.get(position).toString().equals("dummy")) {
            layout.setVisibility(View.GONE);
        } else {
            layout.setVisibility(View.VISIBLE);
        }
        chatText1.setText(Answers.get(position).toString());


        if (questions.get(position).toString().equals("dummy")) {
            layout2.setVisibility(View.GONE);

        } else {
            layout2.setVisibility(View.VISIBLE);

            if (position == questions.size() - 1) {
                if (putDelay) {
                    audioIcon.setVisibility(View.GONE);
                    deChatMsg.setVisibility(View.VISIBLE);
                    deChatMsg.setText(questions.get(position).toString());
                } else {
                    deChatMsg.setVisibility(View.GONE);
                    audioIcon.setVisibility(View.VISIBLE);
                }
            } else {
                audioIcon.setVisibility(View.GONE);
                deChatMsg.setVisibility(View.VISIBLE);
                deChatMsg.setText(questions.get(position).toString());
            }

        }
        if (questions.get(position).toString().equals("dummy")) {
            layout2.setVisibility(View.GONE);
        }
        return view;


    }
}


