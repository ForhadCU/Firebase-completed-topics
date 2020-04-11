package com.example.my_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.ProtectionDomain;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private Button signUpButton, signInButton;
    private EditText userNameEditText, emailEditText, phoneEditText, passEditText;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("USERS");

        signInButton = findViewById(R.id.loginButtonId);
        signUpButton = findViewById(R.id.regButtonId);
        userNameEditText = findViewById(R.id.userEditTextId);
        emailEditText = findViewById(R.id.regEmailEditTextId);
        phoneEditText = findViewById(R.id.regPhoneEditTextId);
        passEditText = findViewById(R.id.regpassEditTextId);

        signUpButton.setOnClickListener(this);
        signInButton.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.regButtonId:

                signUp();   // 1
                break;


            case R.id.loginButtonId:

                logInPage();
                break;
        }
    }
    //Methods...

    private void signUp() {
        final String username = userNameEditText.getText().toString().trim();
        final String email = emailEditText.getText().toString().trim();
        final String phoneNum = phoneEditText.getText().toString().trim();
        final String password = passEditText.getText().toString().trim();

        if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(phoneNum) && !TextUtils.isEmpty(password) )
        {
            progressDialog.setMessage("Signing Up....");
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        String u_id = mAuth.getCurrentUser().getUid();

                        DatabaseReference user_details_db = databaseReference.child(u_id);
                        user_details_db.child("name").setValue(username);
                        user_details_db.child("phone_number").setValue(phoneNum);
                        user_details_db.child("email").setValue(email);
                        user_details_db.child("password").setValue(password);

                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Registration completed", Toast.LENGTH_SHORT).show();

                        Intent mainIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainIntent);
                    }
                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Fill up each field", Toast.LENGTH_SHORT).show();
        }
    }

    private void logInPage() {
        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(loginIntent);

    }
}
