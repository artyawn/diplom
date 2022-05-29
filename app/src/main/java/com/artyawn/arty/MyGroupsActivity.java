package com.artyawn.arty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.artyawn.arty.CreateGroup.NewGroup;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyGroupsActivity extends AppCompatActivity {

   ImageView back;
   TextView mates_gr;
   ImageButton new_gr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_groups);

        back = findViewById(R.id.back_btn);
        new_gr = findViewById(R.id.cr_new_gr);
        mates_gr = findViewById(R.id.groups_mates);

        new_gr.setOnClickListener(view -> {
            Intent intent = new Intent(MyGroupsActivity.this, NewGroup.class);
            startActivity(intent);
        });


        back.setOnClickListener(view -> {
            Intent intent = new Intent(MyGroupsActivity.this, FirstActivity.class);
            startActivity(intent);
        });

        mates_gr.setOnClickListener(view -> {
            Intent intent = new Intent(MyGroupsActivity.this, MatesGroupsActivity.class);
            startActivity(intent);
        });
    }
}

