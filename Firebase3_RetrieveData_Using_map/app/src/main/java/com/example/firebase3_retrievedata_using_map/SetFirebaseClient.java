package com.example.firebase3_retrievedata_using_map;

import android.app.Application;

import com.firebase.client.Firebase;

public class SetFirebaseClient extends Application {

    @Override
    public void onCreate() {
        Firebase.setAndroidContext(this);

        super.onCreate();
    }
}
