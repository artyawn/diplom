package com.artyawn.arty.GroupsMate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.artyawn.arty.ActivityMatesGroups.MatesGroupsActivity;
import com.artyawn.arty.ActivityTask.TaskAdapter;
import com.artyawn.arty.CreateTaskClass;
import com.artyawn.arty.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TasksGr extends AppCompatActivity {
    private RecyclerView recyclerView;
    TaskAdapter adapter;
    DatabaseReference myRef;
    String mAuth;
    TextView mates;
    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_gr);

        String title_group = getIntent().getStringExtra("title");

        mates = findViewById(R.id.usr);
        btn_back = findViewById(R.id.btnb);
        mAuth = FirebaseAuth.getInstance().getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth).child("groups_tasks").child(title_group);

        recyclerView = findViewById(R.id.List);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));


        FirebaseRecyclerOptions<CreateTaskClass> options
                = new FirebaseRecyclerOptions.Builder<CreateTaskClass>()
                .setQuery(myRef, CreateTaskClass.class)
                .build();

        adapter = new TaskAdapter(options);
        recyclerView.setAdapter(adapter);

        mates.setOnClickListener(view -> {
            Intent intent = new Intent(TasksGr.this, UsersGr.class);
            startActivity(intent);
        });

        btn_back.setOnClickListener(view -> {
            Intent intent = new Intent(TasksGr.this, MatesGroupsActivity.class);
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
