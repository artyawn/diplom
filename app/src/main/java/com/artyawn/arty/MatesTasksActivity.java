package com.artyawn.arty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.artyawn.arty.ActivityTask.TaskAdapter;
import com.artyawn.arty.ActivityTask.Tasks;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MatesTasksActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    TextView my_tasks;
    TaskAdapter adapter;
    DatabaseReference myRef;
    String mAuth;
    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_for_mates);
        my_tasks = findViewById(R.id.my_tasks);
        btn_back = findViewById(R.id.back_btn);

        my_tasks.setOnClickListener(view -> {
            Intent intent = new Intent(MatesTasksActivity.this,Tasks.class);
            startActivity(intent);
        });


        btn_back.setOnClickListener(view -> {
            Intent intent = new Intent(MatesTasksActivity.this,FirstActivity.class);
            startActivity(intent);
        });
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