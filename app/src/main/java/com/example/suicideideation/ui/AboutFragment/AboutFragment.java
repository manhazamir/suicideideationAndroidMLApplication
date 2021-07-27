package com.example.suicideideation.ui.AboutFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.suicideideation.Home;
import com.example.suicideideation.R;

public class AboutFragment extends Fragment {

    Button aboutBack;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_about, container, false);


        aboutBack = root.findViewById(R.id.aboutBack);
        aboutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("button called");
                Intent intent = new Intent(getContext().getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
    //    final TextView textView = root.findViewById(R.id.text_gallery);
        return root;
    }
}