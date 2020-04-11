package com.example.fib_storage_upload_images;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.sql.SQLTransactionRollbackException;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private StorageReference mref;
    private static int reqCode = 2;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.buttonId);

        mref = FirebaseStorage.getInstance().getReference();

//        button = findViewById(R.id.buttonId);
        progressDialog = new ProgressDialog(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, reqCode);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == reqCode && resultCode == RESULT_OK)
        {
            progressDialog.setMessage("Uploading...");
            progressDialog.show();

            Uri uri = data.getData();
            StorageReference filepath = mref.child("Sports").child(uri.getLastPathSegment());
//            StorageReference filepath = mref.child("Photos").child("ImageName1");
            UploadTask uploadTask = filepath.putFile(uri);

            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(), "Uploaded Successfully", Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();

                }
            });
        }

    }
}
