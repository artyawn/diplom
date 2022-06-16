package com.artyawn.arty.GroupsMate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.artyawn.arty.R;

public class UsersGr extends AppCompatActivity {
    TextView grTasks;
    ImageView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_gr);

        String value = getIntent().getStringExtra("title");

        grTasks = findViewById(R.id.grTasks);
        btn = findViewById(R.id.btn);

        grTasks.setOnClickListener(view -> {
            Intent intent = new Intent(UsersGr.this, TasksGr.class);
            intent.putExtra("title",value);
            startActivity(intent);
        });

    }
}