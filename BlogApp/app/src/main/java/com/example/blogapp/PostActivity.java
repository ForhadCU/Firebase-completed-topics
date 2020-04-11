package com.example.blogapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class PostActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton postButton;
    private EditText postTitleEdittext, postDescriptionEditText;
    private static int GALLERY_CODE = 2;
    private StorageReference storageReference;
    private Uri imageuri = null;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        this.setTitle("Photo Upload");

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Blog");

        postButton = findViewById(R.id.imageButtonId);
        postTitleEdittext = findViewById(R.id.postTitleId);
        postDescriptionEditText = findViewById(R.id.postDescId);

        progressDialog = new ProgressDialog(this);
        postButton.setOnClickListener(this);

    }

    //Get image from Gallery
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageButtonId)
        {
            Intent gallery_intent = new Intent(Intent.ACTION_GET_CONTENT);
            gallery_intent.setType("image/*");
            startActivityForResult(gallery_intent, GALLERY_CODE);

        }
    }

    //Hold photo in ImageUri
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK)
        {
            imageuri = data.getData();
            postButton.setImageURI(imageuri);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //Post button  in Menu item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.post_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.postItemId)
        {
            progressDialog.setMessage("Uploading....");
            progressDialog.show();

            final String postTitle = postTitleEdittext.getText().toString().trim();
            final String postDesc = postDescriptionEditText.getText().toString().trim();

            if (!TextUtils.isEmpty(postTitle) && !TextUtils.isEmpty(postDesc) && imageuri != null)
            {
//                StorageReference filepaths = storageReference.child("Blog_images").child(imageuri.getLastPathSegment());
                StorageReference filepaths = storageReference.child("Blog_images").child(imageuri.getLastPathSegment());

                filepaths.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        String downloadImageUri =   taskSnapshot.getUploadSessionUri().toString();
                        DatabaseReference Parent = databaseReference.push();

                        Parent.child("title").setValue(postTitle);
                        Parent.child("description").setValue(postDesc);
                        Parent.child("image").setValue(downloadImageUri);

                        progressDialog.dismiss();
                        //after Successfully Uploaded, go to Home page
                        startActivity(new Intent(PostActivity.this, MainActivity.class));
                        Toast.makeText(getApplicationContext(), "Successfully Uploaded", Toast.LENGTH_SHORT).show();


                    }
                });
            }
         }
        return super.onOptionsItemSelected(item);
    }
}
