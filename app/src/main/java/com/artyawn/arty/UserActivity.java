package com.artyawn.arty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.artyawn.arty.CreateGroup.NewGroup;
import com.google.firebase.auth.FirebaseAuth;

public class UserActivity extends AppCompatActivity {

    private TextView create_new_group, id, my_groups;
    private Button btn_get_id;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        create_new_group=findViewById(R.id.tv_new_group);
        btn_get_id = findViewById(R.id.btn_get_id);
        id = findViewById(R.id.et_id);
        my_groups = findViewById(R.id.my_groups);
        mAuth = FirebaseAuth.getInstance();
        String user = mAuth.getUid();



        create_new_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivity.this, NewGroup.class);
                startActivity(intent);
            }
        });

        btn_get_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id.setText(user);

            }
        });

        my_groups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(UserActivity.this, MyGroupsActivity.class);
                startActivity(intent);
            }
        });


    }
}