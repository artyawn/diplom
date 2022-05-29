package com.artyawn.arty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MatesGroupsActivity extends AppCompatActivity {

    ImageView back;
    TextView my_groups;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mates_groups);

        back = findViewById(R.id.back_btn);
        my_groups= findViewById(R.id.my_groups);

        my_groups.setOnClickListener(view -> {
            Intent intent = new Intent(MatesGroupsActivity.this, MyGroupsActivity.class);
            startActivity(intent);
        });
        back.setOnClickListener(view -> {
            Intent intent = new Intent(MatesGroupsActivity.this, FirstActivity.class);
            startActivity(intent);
        });
    }
}