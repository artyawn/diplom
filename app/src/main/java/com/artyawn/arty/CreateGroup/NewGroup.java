package com.artyawn.arty.CreateGroup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.artyawn.arty.GroupClass;
import com.artyawn.arty.R;
import com.artyawn.arty.UserClass;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class NewGroup extends AppCompatActivity {
    private DatabaseReference myRef1, myRef2, myRef3;
    private Button create_group;
    private EditText grouper, name_new_group;
    private TextView add_grouper;

    RecyclerView recyclerView;
    EditText grouper_id;
    String mAuth;
    DatabaseReference myRef;
    ImageView add;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        add =findViewById(R.id.plus_grouper);
        grouper_id = findViewById(R.id.grouper_id);

        List<String> items = new LinkedList<>();


        recyclerView = findViewById(R.id.grouperList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CreateGroupAdapter adapter = new CreateGroupAdapter(items);
        recyclerView.setAdapter(adapter);


        add =findViewById(R.id.plus_grouper);
        add.setOnClickListener(view -> {
            String id = grouper_id.getText().toString();
            myRef =FirebaseDatabase.getInstance().getReference("users").child(id).child("user_inf");
            myRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isComplete()){
                        items.add(task.getResult().getValue().toString());
                    }
                else{
                        Toast.makeText(NewGroup.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    }
                }

            });

        });

    }
}



