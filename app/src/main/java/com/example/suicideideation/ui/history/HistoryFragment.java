package com.example.suicideideation.ui.history;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.suicideideation.History;
import com.example.suicideideation.Home;
import com.example.suicideideation.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    private FirebaseAuth auth;
    ListView historyListView;
    Button history_back;
    TextView noHistoryText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String userID = auth.getCurrentUser().getUid();
        View root = inflater.inflate(R.layout.fragment_history, container, false);

        historyListView = root.findViewById(R.id.historyListView);
        noHistoryText =  root.findViewById(R.id.noHistoryText);
        history_back =  root.findViewById(R.id.history_back);

        history_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext().getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });



        ArrayList<String> DateList  = new ArrayList<String>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users").child(userID);
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds: dataSnapshot.child("history").getChildren()){
                            String key = ds.getKey();
                            DateList.add(key);
                            System.out.println("data  "+key);
                        }
                        if(DateList.size()!=0){
                            ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity().getApplicationContext(),R.layout.history_content,R.id.dateAndTimeText, DateList);
                            historyListView.setAdapter(arrayAdapter);
                        }else{
                            historyListView.setVisibility(View.GONE);
                            noHistoryText.setVisibility(View.VISIBLE);
                        }
                        historyListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                TextView text = view.findViewById(R.id.dateAndTimeText);

                                Intent intent = new Intent(getContext(), History.class);
                                intent.putExtra("date",text.getText());
                                startActivity(intent);

                            }
                        });




                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        return root;
    }
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//
//
//
//
//        Button b1 = (Button) getView().findViewById(R.id.button);
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), History.class);
//                startActivity(intent);
//            }
//        });
//        Button b2 = (Button) getView().findViewById(R.id.button1);
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), History.class);
//                startActivity(intent);
//            }
//        });
//        Button b3 = (Button) getView().findViewById(R.id.button3);
//        b3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), History.class);
//                startActivity(intent);
//            }
//        });
//        Button b4 = (Button) getView().findViewById(R.id.b);
//        b4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), History.class);
//                startActivity(intent);
//            }
//        });
//
//
//    }
}
