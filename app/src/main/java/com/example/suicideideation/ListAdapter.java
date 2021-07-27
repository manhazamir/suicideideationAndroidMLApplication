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

public class ListAdapter extends ArrayAdapter {
    Context context;
    TextView hidden;
    TextView btn;
    ArrayList array;
    ArrayList id = null;

    public ListAdapter(@NonNull Context context, ArrayList<String> array, ArrayList<String> id) {
        super(context, R.layout.list_items, R.id.d2, array);
        this.context = context;
        this.array = array;
        this.id = id;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_items, parent, false);
        }
            btn = view.findViewById(R.id.d2);
            hidden = view.findViewById(R.id.hidden);

            String name = (String) array.get(position);
            String userID = (String) id.get(position);

            btn.setText(name);
            hidden.setText(userID);

        return view;
    }

}

