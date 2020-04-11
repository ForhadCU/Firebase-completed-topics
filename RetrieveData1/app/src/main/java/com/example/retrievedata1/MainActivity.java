package com.example.retrievedata1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView keyEditText, valueEditText;
    private Firebase mref;
    private Button saveButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        mref = new Firebase("https://fir-2-b3a46.firebaseio.com/Name/Name");
        keyEditText = findViewById(R.id.keyEditTextId);
        valueEditText = findViewById(R.id.valueEditTextId);
        saveButton = findViewById(R.id.btnid);
        textView = findViewById(R.id.editTextId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = keyEditText.getText().toString().trim();
                String value = valueEditText.getText().toString().trim();

                mref.child(key).setValue(value);
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

            }
        });

        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String data = dataSnapshot.getValue(String.class);
                textView.setText(data);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
