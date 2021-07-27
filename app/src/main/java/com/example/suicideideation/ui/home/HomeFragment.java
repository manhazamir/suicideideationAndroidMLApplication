package com.example.suicideideation.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.suicideideation.Chat;
import com.example.suicideideation.Chat2;
import com.example.suicideideation.Chat3;
import com.example.suicideideation.Chat4;
import com.example.suicideideation.Home;
import com.example.suicideideation.R;

public class HomeFragment extends Fragment {
    ImageView img;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
     //   final TextView textView = root.findViewById(R.id.);

        img = (ImageView) root.findViewById(R.id.img2);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(getContext(), Chat3.class);
                Intent intent = new Intent(getContext(), Chat.class);
                intent.putExtra("state",1);
                startActivity(intent);
            }
        });
        img1 = (ImageView) root.findViewById(R.id.img4);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(getContext(), Chat2.class);
                Intent intent = new Intent(getContext(), Chat.class);
                intent.putExtra("state",2);
                startActivity(intent);
            }
        });
        img2 = (ImageView) root.findViewById(R.id.img6);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Chat.class);
                intent.putExtra("state",3);
                startActivity(intent);
            }
        });
        img3 = (ImageView) root.findViewById(R.id.img8);
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent = new Intent(getContext(), Chat4.class);
                Intent intent = new Intent(getContext(), Chat.class);
                intent.putExtra("state",4);
                startActivity(intent);
            }
        });


        return root;
    }
}