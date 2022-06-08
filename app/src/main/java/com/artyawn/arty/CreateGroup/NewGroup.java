package com.artyawn.arty.CreateGroup;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.artyawn.arty.FirstActivity;
import com.artyawn.arty.GroupClass;
import com.artyawn.arty.MyGroupsActivity;
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
//    private DatabaseReference myRef1, myRef2, myRef3;
//    private Button create_group;
//    private EditText grouper, name_new_group;
//    private TextView add_grouper;

    RecyclerView recyclerView;
    EditText grouper_id, title;
    DatabaseReference myRef, myRef1, myRef3;
    ImageView add;
    ImageButton new_group;
    ImageView back;
    String mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        add =findViewById(R.id.plus_grouper);
        grouper_id = findViewById(R.id.grouper_id);
        new_group = findViewById(R.id.btn_new_group);
        mAuth = FirebaseAuth.getInstance().getUid();
        title = findViewById(R.id.et_new_group);
        back = findViewById(R.id.back_btn);

        List<String> items = new LinkedList<>();
        List<String> grgr = new LinkedList<>();


        recyclerView = findViewById(R.id.grouperList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CreateGroupAdapter adapter = new CreateGroupAdapter(items);
        recyclerView.setAdapter(adapter);


        add =findViewById(R.id.plus_grouper);
        add.setOnClickListener(view -> {
            String gr_id = grouper_id.getText().toString();
            myRef =FirebaseDatabase.getInstance().getReference("users").child(gr_id).child("user_inf").child("email");
            myRef.get().addOnCompleteListener(task -> {
                if(task.isComplete()){
                    if(grouper_id.getText().toString().isEmpty()){
                        Toast.makeText(this, "Введите id", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        grgr.add(grouper_id.getText().toString());
                        items.add(task.getResult().getValue().toString());
                    }
                }

            else{
                    Toast.makeText(NewGroup.this, "Ошибка", Toast.LENGTH_SHORT).show();
                }
            });

        });

        back.setOnClickListener(view -> {
            Intent intent = new Intent(NewGroup.this, MyGroupsActivity.class);
            startActivity(intent);
        });


        new_group.setOnClickListener(view -> {
            String title_group = title.getText().toString();
            myRef1 = FirebaseDatabase.getInstance().getReference("users").child(mAuth).child("my_groups").child(title_group);
            GroupClass groupClass = new GroupClass(title_group,items);
            myRef1.setValue(groupClass);
            for(String i: grgr){
                myRef3 = FirebaseDatabase.getInstance().getReference("users").child(i).child("groups").child(title_group);
                myRef3.setValue(groupClass);
            }
            Intent intent = new Intent(NewGroup.this, FirstActivity.class);
            startActivity(intent);

        });

    }
}



