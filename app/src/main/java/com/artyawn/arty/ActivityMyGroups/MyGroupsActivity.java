package com.artyawn.arty.ActivityMyGroups;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.artyawn.arty.CreateGroup.NewGroup;
import com.artyawn.arty.FirstAcrivity.FirstActivity;
import com.artyawn.arty.DataClasses.GroupClass;
import com.artyawn.arty.ActivityMatesGroups.MatesGroupsActivity;
import com.artyawn.arty.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyGroupsActivity extends AppCompatActivity {

   ImageView back;
   TextView mates_gr;
   ImageButton new_gr;
   String mAuth;
   DatabaseReference myRef;
   private RecyclerView recyclerView;
   GroupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_groups);

        back = findViewById(R.id.back_btn_groups);
        new_gr = findViewById(R.id.cr_new_gr);
        mates_gr = findViewById(R.id.groups_mates);

        mAuth = FirebaseAuth.getInstance().getUid();
        myRef = FirebaseDatabase.getInstance().getReference("users/"+mAuth+"/"+"my_groups"+"/"+"title_group");

        recyclerView = findViewById(R.id.groupsList);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));


        FirebaseRecyclerOptions<GroupClass> options
                = new FirebaseRecyclerOptions.Builder<GroupClass>()
                .setQuery(myRef, GroupClass.class)
                .build();

        adapter = new GroupAdapter(options);
        recyclerView.setAdapter(adapter);

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





