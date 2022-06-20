package com.artyawn.arty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.artyawn.arty.ActivityMyGroups.GroupAdapter;
import com.artyawn.arty.DataClasses.GroupClass;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GroupPicker extends AppCompatActivity {


    String mAuth;
    DatabaseReference myRef;
    RecyclerView recyclerView;
    GroupPickerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_picker);


        String value1 = getIntent().getStringExtra("title");
        String value2 = getIntent().getStringExtra("discr");
        String value3 = getIntent().getStringExtra("dare");

        mAuth = FirebaseAuth.getInstance().getUid();
        myRef = FirebaseDatabase.getInstance().getReference("users/"+mAuth+"/"+"my_groups"+"/"+"title_group");
        recyclerView = findViewById(R.id.grup_picker);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));
        FirebaseRecyclerOptions<GroupClass> options
                = new FirebaseRecyclerOptions.Builder<GroupClass>()
                .setQuery(myRef, GroupClass.class)
                .build();

        adapter = new GroupPickerAdapter(options);
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
