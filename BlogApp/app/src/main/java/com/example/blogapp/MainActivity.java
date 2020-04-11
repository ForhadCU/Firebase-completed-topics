package com.example.blogapp;

//Inflate menu layout by creating #onCreateOptionMenu function
//set action for menu item by Creating #onOptionsItemSelected function
//Declare RecyclerView
//set LayoutManager
//Create ViewHolder for RecyclerView
    //declare a view variable
    //call all setMethod for each attribute.
//Create #onStart method
    //Create #FirebaseRecyclerViewAdapter
    //pass four parameters
    //set data on Holder by calling set and get
    //setAdapter in


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;  //Declare RecyclerView
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Blog");
        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);

        //set LayoutManager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
    }

    //Create #onStart method


    @Override
    protected void onStart() {
        super.onStart();

        Query query = databaseReference.orderByChild("Blog");

        FirebaseRecyclerOptions<Handler_Class> recyclerOptions = new FirebaseRecyclerOptions.Builder<Handler_Class>().setQuery(query, Handler_Class.class).setLifecycleOwner(this).build();

        FirebaseRecyclerAdapter<Handler_Class, MyviewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Handler_Class, MyviewHolder>(recyclerOptions){
            @Override
            protected void onBindViewHolder(@NonNull MyviewHolder holder, int position, @NonNull Handler_Class model) {
                    holder.setTitle(model.getTitle());
                    holder.setDescription(model.getDescription());
            }

            @NonNull
            @Override
            public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return null;
            }
        };

        /*class MyFirebaseRecyclerAdapter<T, VH extends RecyclerView.ViewHolder> :FirebaseRecyclerAdapter<T, VH> {
    public MyFirebaseRecyclerAdapter(Class pojoClass<T>, int layout, Class<VH> viewHolderClass, Query query) {
        super(new FirebaseRecyclerOptions.Builder<T>()
            .setQuery(query, pojoClass.class)
            .build());
        ...*/

        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

    //Create ViewHolder for RecyclerView
    public static class MyviewHolder extends RecyclerView.ViewHolder {
        View mView;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
    }

    public void setTitle(String title)
    {
        TextView showPostTitle = mView.findViewById(R.id.showPostTitleId);
        showPostTitle.setText(title);
    }
    public void setDescription(String description)
    {
        TextView showPostDescription = mView.findViewById(R.id.showPostDescId);
        showPostDescription.setText(description);
    }

}

    //Inflate menu layout by creating #onCreateOptionMenu function
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

//set action for menu item by Creating #onOptionsItemSelected function
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.addItemId)
        {
            startActivity(new Intent(MainActivity.this, PostActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
