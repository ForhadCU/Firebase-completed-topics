package com.example.firebase1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private Button saveButton;
    private Firebase mref;
//    private String a[] = {"Name1", "Name2", "Name3"};
    private EditText nameEditText, valueEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

//        mref = Firebase.getInstance().getReference();
        mref = new Firebase("https://fir-1-ca39e.firebaseio.com/");

        saveButton = findViewById(R.id.svaebuttonid);
        nameEditText = findViewById(R.id.nameEditTextId);
        valueEditText = findViewById(R.id.valueEditTextId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                for (int i = 0; i<=2; i++)
//                {
//
//                    DatabaseReference mrefChild = mref.child();
//                    mrefChild.setValue("Test2");
//                }

                String key = nameEditText.getText().toString().trim();
                String value = valueEditText.getText().toString();
//
//                String key1 = mref.push().getKey();
//                assert key1 != null;
//                mref.child(key1).setValue("Name is : " + name);
//
//                String key2 = mref.push().getKey();
//                assert key2 != null;
//                mref.child(key2).setValue("I am "+value+" years old now.");

                mref.child(key).setValue(value);

            }
        });
    }
}
