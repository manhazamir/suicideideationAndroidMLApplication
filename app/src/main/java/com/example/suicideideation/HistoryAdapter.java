package com.example.suicideideation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class HistoryAdapter extends ArrayAdapter {

    ArrayList testName;
    ArrayList testScore;
    TextView hiddenScore;
    TextView TestNameview;
    LinearLayout noHistoryLayout;
    LinearLayout HistoryLayout;
    Context context;

    public HistoryAdapter(@NonNull Context context, ArrayList testName, ArrayList testScore) {
        super(context, R.layout.history_content, R.id.dateAndTimeText, testName);
        this.context = context;
        this.testName = testName;
        this.testScore = testScore;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        System.out.println("getView called");
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.history_content, parent, false);
        }
            TestNameview = view.findViewById(R.id.dateAndTimeText);
            hiddenScore = view.findViewById(R.id.hiddenScore);
            noHistoryLayout =  view.findViewById(R.id.noHistoryLayout);
            HistoryLayout =  view.findViewById(R.id.HistoryLayout);


            String name = (String) testName.get(position);
            String score = (String) testScore.get(position);

            TestNameview.setText(name);
            hiddenScore.setText(score);

        return view;
    }
}
