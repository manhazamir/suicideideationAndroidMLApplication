package com.example.suicideideation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter2 extends ArrayAdapter {

    Context context;
    TextView hidden;
    Button btn;
    ArrayList<String> array3;
    TextView chatText1;

    public ListAdapter2(@NonNull Context context, ArrayList<String> array) {
        super(context, R.layout.item_in_message, R.id.chatText , array);
        this.context = context;
        this.array3 = array;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        System.out.println("getView called");
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_in_message,  parent, false);


            chatText1 = view.findViewById(R.id.chatText);

            String name1 = String.valueOf(array3.get(position));
            System.out.println("name1: "+name1);
            System.out.println("-----------------------------------ok ");
            chatText1.setText(name1);


        }
        return view;
    }


}
