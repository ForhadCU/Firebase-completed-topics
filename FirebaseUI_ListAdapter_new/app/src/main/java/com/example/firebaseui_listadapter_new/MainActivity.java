package com.example.firebaseui_listadapter_new;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewId);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://fir-1-ca39e.firebaseio.com/");

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(this, String.class, android.R.layout.simple_list_item_1, databaseReference) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = v.findViewById(android.R.id.text1);
                textView.setText(model);
            }
        };
        listView.setAdapter(firebaseListAdapter);
    }
}
