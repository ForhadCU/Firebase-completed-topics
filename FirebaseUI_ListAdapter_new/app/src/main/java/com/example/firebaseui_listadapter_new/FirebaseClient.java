package com.example.firebaseui_listadapter_new;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseClient extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //New version
        if (FirebaseApp.getApps(this).isEmpty())
        {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
