package com.example.firebase1;

import android.app.Application;

import com.firebase.client.Firebase;

public class Firebase1 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
