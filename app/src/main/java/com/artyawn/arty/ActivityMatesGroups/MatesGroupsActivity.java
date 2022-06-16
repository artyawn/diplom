package com.artyawn.arty.ActivityMatesGroups;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.artyawn.arty.ActivityMyGroups.GroupAdapter;
import com.artyawn.arty.ActivityMyGroups.MyGroupsActivity;
import com.artyawn.arty.FirstActivity;
import com.artyawn.arty.GroupClass;
import com.artyawn.arty.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MatesGroupsActivity extends AppCompatActivity {

     ImageView back;
     TextView my_groups;
     String mAuth;
     DatabaseReference myRef;
    private RecyclerView recyclerView;
     MatesGroupAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mates_groups);


        back = findViewById(R.id.back_btn_home);
        my_groups= findViewById(R.id.my_groups);
        recyclerView = findViewById(R.id.matesGroupsList);

        mAuth = FirebaseAuth.getInstance().getUid();
        myRef = FirebaseDatabase.getInstance().getReference("users/"+mAuth+"/"+"groups"+"/"+"title_group");


        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));


        FirebaseRecyclerOptions<GroupClass> options
                = new FirebaseRecyclerOptions.Builder<GroupClass>()
                .setQuery(myRef, GroupClass.class)
                .build();

        adapter = new MatesGroupAdapter(options);
        recyclerView.setAdapter(adapter);


        my_groups.setOnClickListener(view -> {
            Intent intent = new Intent(MatesGroupsActivity.this, MyGroupsActivity.class);
            startActivity(intent);
        });
        back.setOnClickListener(view -> {
            Intent intent = new Intent(MatesGroupsActivity.this, FirstActivity.class);
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