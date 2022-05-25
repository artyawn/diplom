package com.artyawn.arty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewGroup extends AppCompatActivity {
    private DatabaseReference myRef;
    private Button create_group;
    private EditText grouper, name_new_group;
    private TextView add_grouper;

    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ArrayList<UserClass> list;
    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        create_group = findViewById(R.id.create_new_group);
        grouper = findViewById(R.id.grouper);
        name_new_group = findViewById(R.id.name_of_new_group);
        add_grouper = findViewById(R.id.tv_add_grouper);
        recyclerView = findViewById(R.id.userList);

        mAuth = FirebaseAuth.getInstance();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);




        add_grouper.setOnClickListener(view -> {
            String id = grouper.getText().toString();
            myRef = FirebaseDatabase.getInstance().getReference("Users").child(id).child("user_inf");

            myRef.addValueEventListener(new ValueEventListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                        UserClass user = dataSnapshot.getValue(UserClass.class);
                        list.add(user);


                    }
                    myAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        });

        create_group.setOnClickListener(view -> {
            String uid = grouper.getText().toString();
            String title = name_new_group.getText().toString();
            myRef = FirebaseDatabase.getInstance().getReference("groups").child(title);
            GroupClass group = new GroupClass(title,uid);
            myRef.setValue(group);
        });

    }
}