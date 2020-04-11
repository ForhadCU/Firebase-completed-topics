package com.example.test_display_photo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private Button uploadButton;
    private DatabaseReference databaseReference;
    private static int mReqCode = 2;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("String Images");
//        databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://myfireapptest-65b74.firebaseio.com/String");

//        uploadButton = findViewById(R.id.buttonId);
        imageView = findViewById(R.id.imageViewId);

        String imageUri = "https://i.imgur.com/tGbaZCY.jpg";
        Picasso.with(getApplicationContext()).load(imageUri).into(imageView);

//        uploadButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                imageIntent.setType("image/*");
//                startActivityForResult(imageIntent, mReqCode);
//            }
//        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == mReqCode && resultCode == RESULT_OK)
//        {
//
//        }
//    }
}
