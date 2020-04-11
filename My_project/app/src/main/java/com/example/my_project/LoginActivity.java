package com.example.my_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button loginButton;
    private TextView regTextView, alertTextView;
    private FirebaseAuth mAuth;
    private EditText lEmailEditText, lPassEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        loginButton = findViewById(R.id.loginButtonId);
        regTextView = findViewById(R.id.registerTextViewId);
        alertTextView = findViewById(R.id.alertTextV);
        lEmailEditText = findViewById(R.id.logEmailEditText);
        lPassEditText = findViewById(R.id.logPassEditText);

        loginButton.setOnClickListener(this);
        regTextView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.loginButtonId):

                //method calling
                loginMethod();
                break;

            case R.id.registerTextViewId:

                regMethod();
                break;
        }
    }

    private void loginMethod() {
        String email = lEmailEditText.getText().toString().trim();
        String password = lPassEditText.getText().toString().trim();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password))
        {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Intent intentMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                        intentMainActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentMainActivity);
                    }
                    else {
//                        Toast.makeText(getApplicationContext(), "Email or Password is incorrect", Toast.LENGTH_SHORT).show();
                        alertTextView.setText("Email or password is incorrect");
                        lEmailEditText.setText("");
                        lPassEditText.setText("");
                        lEmailEditText.requestFocus();

                    }
                }
            });

        }
        else
            Toast.makeText(getApplicationContext(), "Fill up each field", Toast.LENGTH_SHORT).show();

    }

    private void regMethod() {
        Intent regIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(regIntent);

    }
}
