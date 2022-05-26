package com.artyawn.arty.CreateGroup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.artyawn.arty.GroupClass;
import com.artyawn.arty.R;
import com.artyawn.arty.UserClass;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NewGroup extends AppCompatActivity {
    private DatabaseReference myRef1, myRef2, myRef3;
    private Button create_group;
    private EditText grouper, name_new_group;
    private TextView add_grouper;

    RecyclerView recyclerView;
    NewGroupAdapter adapter;
    String mAuth;
    DatabaseReference myRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        create_group = findViewById(R.id.create_new_group);
        grouper = findViewById(R.id.grouper);
        name_new_group = findViewById(R.id.name_of_new_group);
        add_grouper = findViewById(R.id.tv_add_grouper);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();


// Attach a listener to read the data at your profile reference



//
//        mAuth = FirebaseAuth.getInstance().getUid();
//        recyclerView = findViewById(R.id.tasksList);
//
//            recyclerView.setLayoutManager(
//                    new LinearLayoutManager(this));
//
//
//            FirebaseRecyclerOptions<UserClass> options
//                    = new FirebaseRecyclerOptions.Builder<UserClass>()
//                    .setQuery(myRef, UserClass.class)
//                    .build();
//
//            adapter = new NewGroupAdapter(options);
//            recyclerView.setAdapter(adapter);
//
        ArrayList<String> mates = new ArrayList<>();


        add_grouper.setOnClickListener(view -> {

            String grouperInEt =grouper.getText().toString();
            String title =name_new_group.getText().toString() ;
            myRef1 = FirebaseDatabase.getInstance().getReference().child("users").child(grouperInEt).child("groups_mate").child(title);
            DatabaseReference ref = database.getReference("users").child(grouperInEt).child("user_inf");
            // Attach a listener to read the data at your profile reference
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    UserClass profile = dataSnapshot.getValue(UserClass.class);
                    mates.add(profile.getEmail());


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    System.out.println("The read failed: " + databaseError.getCode());
                }
            });




        });

        create_group.setOnClickListener(view -> {

            String title =name_new_group.getText().toString() ;
            myRef2 = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth).child("my_groups").child(title);

            GroupClass group = new GroupClass(title,mates);
            myRef2.setValue(group);
            myRef1.setValue(group);

        });
                }
}

//            adapter.startListening();
//            FirebaseRecyclerOptions<UserClass> options
//                    = new FirebaseRecyclerOptions.Builder<UserClass>()
//                    .setQuery(myRef, UserClass.class)
//                    .build();
//            adapter = new NewGroupAdapter(options);
//            recyclerView.setAdapter(adapter);

//            recyclerView.setLayoutManager(
//                    new LinearLayoutManager(this));
//
//
//            FirebaseRecyclerOptions<UserClass> options
//                    = new FirebaseRecyclerOptions.Builder<UserClass>()
//                    .setQuery(myRef, UserClass.class)
//                    .build();
//
//            adapter = new NewGroupAdapter(options);
//            recyclerView.setAdapter(adapter);
//
//



//    }
//    @Override protected void onStart()
//    {
//        super.onStart();
//        adapter.startListening();
//    }
//
//
//    @Override protected void onStop()
//    {
//        super.onStop();
//        adapter.stopListening();



//    private RecyclerView recyclerView;
//    TaskAdapter adapter;
//    DatabaseReference myRef;
//    String mAuth;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tasks);
//
//
//        mAuth = FirebaseAuth.getInstance().getUid();
//        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth).child("tasks");
//
//        recyclerView = findViewById(R.id.tasksList);
//
//        recyclerView.setLayoutManager(
//                new LinearLayoutManager(this));
//
//
//        FirebaseRecyclerOptions<CreateTaskClass> options
//                = new FirebaseRecyclerOptions.Builder<CreateTaskClass>()
//                .setQuery(myRef, CreateTaskClass.class)
//                .build();
//
//        adapter = new TaskAdapter(options);
//        recyclerView.setAdapter(adapter);
//    }
//
//
//    @Override protected void onStart()
//    {
//        super.onStart();
//        adapter.startListening();
//    }
//
//
//    @Override protected void onStop()
//    {
//        super.onStop();
//        adapter.stopListening();
//    }
//}
