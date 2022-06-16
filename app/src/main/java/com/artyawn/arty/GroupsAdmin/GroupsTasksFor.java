package com.artyawn.arty.GroupsAdmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.artyawn.arty.ActivityMyGroups.MyGroupsActivity;
import com.artyawn.arty.ActivityTaskFor.TaskForAdapter;
import com.artyawn.arty.CreateTask;
import com.artyawn.arty.CreateTaskClass;
import com.artyawn.arty.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GroupsTasksFor extends AppCompatActivity {

    private String mAuth;
    private RecyclerView recyclerView;
    private DatabaseReference myRef;
    private TaskForAdapter adapter;
    private String group_title;
    private ImageView back;
    private ImageButton add_task;
    private TextView users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_groups_tasks);

        recyclerView = findViewById(R.id.groupsTasksList);
        users = findViewById(R.id.usersGroup);
        back = findViewById(R.id.back_to_groups);
        add_task = findViewById(R.id.add_task_for_group);

        group_title = getIntent().getStringExtra("title");
        mAuth = FirebaseAuth.getInstance().getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth).child("groups_tasks_for").child(group_title);


        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        FirebaseRecyclerOptions<CreateTaskClass> options
                = new FirebaseRecyclerOptions.Builder<CreateTaskClass>()
                .setQuery(myRef, CreateTaskClass.class)
                .build();

        adapter = new TaskForAdapter(options);
        recyclerView.setAdapter(adapter);

        users.setOnClickListener(view -> {
            Intent intent = new Intent(GroupsTasksFor.this, GroupsAdminUsers.class);
            startActivity(intent);
        });

        back.setOnClickListener(view -> {
            Intent intent = new Intent(GroupsTasksFor.this, MyGroupsActivity.class);
            startActivity(intent);
        });

        add_task.setOnClickListener(view -> {
            Intent intent = new Intent(GroupsTasksFor.this, CreateTask.class);
            intent.putExtra("title",group_title);
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