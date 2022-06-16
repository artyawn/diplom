package com.artyawn.arty.GroupsAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.artyawn.arty.ActivityMyGroups.MyGroupsActivity;
import com.artyawn.arty.R;

public class GroupsAdminUsers extends AppCompatActivity  {

    TextView  groups_tasks;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_users);
        back = findViewById(R.id.back_btn1);
        groups_tasks = findViewById(R.id.groups_tasks);
        String value = getIntent().getStringExtra("title");


        back.setOnClickListener(view -> {
            Intent intent = new Intent(GroupsAdminUsers.this, MyGroupsActivity.class);
            startActivity(intent);
        });
        groups_tasks.setOnClickListener(view -> {
            Intent intent =new Intent(GroupsAdminUsers.this, GroupsTasksFor.class);
            intent.putExtra("title",value);
            startActivity(intent);
        });




    }

}