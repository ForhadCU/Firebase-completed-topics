package com.example.my_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.security.ProtectionDomain;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loguotButton;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        loguotButton = findViewById(R.id.logoutButton);

        loguotButton.setOnClickListener(this);
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null)
                {
                    Intent regIntent = new Intent(MainActivity.this, LoginActivity.class);
                    regIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(regIntent);
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onClick(View v) {

            signOutMethod();

        }

    private void signOutMethod() {

        mAuth.signOut();
        Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();

        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);
    }
}

