package com.example.firebase3_retrievedata_using_map;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Firebase mRef1, mRef2;
    private Firebase mRetrieve_1, mRetrieve_2, mRetrieve_3 ;
    private ListView mListView;
    private ArrayList<String> mUsernames = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textId);
        mListView = findViewById(R.id.listViewId);





        //Adding Data
        String parent_1 = "User_10";
        String parent_2 = "User_15";

//        String key = mRef.push().getKey();
//        mRef1 = new Firebase("https://test-b76df.firebaseio.com/"+parent_1);
//        Firebase child_1 = mRef1.child("Name");
//        child_1.setValue("Test17");
//
//        mRef2 = new Firebase("https://test-b76df.firebaseio.com/"+parent_2);
//        Firebase child_2 = mRef2.child("Name");
//        child_2.setValue("Test2");

        //Retrieving Single Data
//        mRetrieve_1 = new Firebase("https://test-b76df.firebaseio.com/"+parent_1+"/Name");
//
//        mRetrieve_1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String value = dataSnapshot.getValue(String.class);
//                textView.setText(value);
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });

        //Retrieving Single Child Data
//        mRetrieve_2 = new Firebase("https://test-b76df.firebaseio.com/"+parent_1);
//
//        mRetrieve_2.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                String value = dataSnapshot.getValue(String.class);
//                textView.setText(value);
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//
//            }
//        });




        //Retrieving Multiple Data with ListView
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, (List<String>) mUsernames);
        mListView.setAdapter(arrayAdapter);

        mRetrieve_3 = new Firebase("https://test-b76df.firebaseio.com/"+parent_1);



        mRetrieve_3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                mUsernames.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });




    }
}
