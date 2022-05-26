package com.artyawn.arty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.artyawn.arty.ActivityTask.TaskAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MatesTasksActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    TaskAdapter adapter;
    DatabaseReference myRef;
    String mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mates_tasks);

        mAuth = FirebaseAuth.getInstance().getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth).child("tasks_for_mates");

        recyclerView = findViewById(R.id.matesTasksList);

        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));


        FirebaseRecyclerOptions<CreateTaskClass> options
                = new FirebaseRecyclerOptions.Builder<CreateTaskClass>()
                .setQuery(myRef, CreateTaskClass.class)
                .build();
        adapter = new TaskAdapter(options);
        recyclerView.setAdapter(adapter);
    }


    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }


    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }
}