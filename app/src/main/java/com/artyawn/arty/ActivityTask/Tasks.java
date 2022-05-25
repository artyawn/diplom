package com.artyawn.arty.ActivityTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.artyawn.arty.ActivityTask.TaskAdapter;
import com.artyawn.arty.CreateTaskClass;
import com.artyawn.arty.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tasks extends AppCompatActivity{

private RecyclerView recyclerView;
        TaskAdapter adapter;
        DatabaseReference myRef;
        String mAuth;

@Override
protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);


        mAuth = FirebaseAuth.getInstance().getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth).child("tasks");

        recyclerView = findViewById(R.id.tasksList);

        recyclerView.setLayoutManager(
        new LinearLayoutManager(this));


        FirebaseRecyclerOptions<CreateTaskClass> options
        = new FirebaseRecyclerOptions.Builder<CreateTaskClass>()
        .setQuery(myRef, CreateTaskClass.class)
        .build();

        adapter = new TaskAdapter(options);
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





//Старый класс

//public class Tasks extends AppCompatActivity {
//
//
//    private RecyclerView recyclerView;
//    DatabaseReference database;
//    TaskAdapter myAdapter;
//    ArrayList<CreateTaskClass> list;
//    FirebaseAuth mAuth;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tasks);
//
//
//        recyclerView = findViewById(R.id.userList);
//        String id = FirebaseAuth.getInstance().getUid();
//        assert id != null;
//        database = FirebaseDatabase.getInstance().getReference("users").child(id).child("tasks");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//
//        list = new ArrayList<>();
//        myAdapter = new TaskAdapter(this,list);
//        recyclerView.setAdapter(myAdapter);
//
//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//
//                    CreateTaskClass task = dataSnapshot.getValue(CreateTaskClass.class);
//                    list.add(task);
//
//
//                }
//                myAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }
//}