package com.artyawn.arty.ActivityTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.artyawn.arty.ActivityTask.TaskAdapter;
import com.artyawn.arty.CreateTaskClass;
import com.artyawn.arty.FirstActivity;
import com.artyawn.arty.MatesTasksActivity;
import com.artyawn.arty.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tasks extends AppCompatActivity{

private RecyclerView recyclerView;
        TaskAdapter adapter;
        DatabaseReference myRef;
        String mAuth;
        TextView tasks_mates;
        ImageView btn_back;

@Override
protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        tasks_mates = findViewById(R.id.tasks_mates);
        btn_back = findViewById(R.id.back_btn);

        mAuth = FirebaseAuth.getInstance().getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth).child("tasks");

        recyclerView = findViewById(R.id.tasksList);

        recyclerView.setLayoutManager(
        new LinearLayoutManager(this));


        FirebaseRecyclerOptions<CreateTaskClass> options
        = new FirebaseRecyclerOptions.Builder<CreateTaskClass>()
        .setQuery(myRef, CreateTaskClass.class)
        .build();

        adapter = new TaskAdapter(options);
        recyclerView.setAdapter(adapter);

        tasks_mates.setOnClickListener(view -> {
                Intent intent = new Intent(Tasks.this, MatesTasksActivity.class);
                startActivity(intent);
        });

                btn_back.setOnClickListener(view -> {
                        Intent intent = new Intent(Tasks.this, FirstActivity.class);
                        startActivity(intent);
                });



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


