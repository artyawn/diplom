package com.artyawn.arty.CreateGroup;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.artyawn.arty.GroupClass;
import com.artyawn.arty.ActivityMyGroups.MyGroupsActivity;
import com.artyawn.arty.R;
import com.artyawn.arty.UserClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class NewGroup extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText grouper_id, title;
    DatabaseReference myRef, myRef1, myRef_title, myRef_users, myRef2;
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

        List<String> items = new ArrayList<>();
        List<String> grgr = new ArrayList<>();

        add =findViewById(R.id.plus_grouper);
        recyclerView = findViewById(R.id.grouperList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CreateGroupAdapter adapter = new CreateGroupAdapter(items);
        recyclerView.setAdapter(adapter);



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

            for(String i:items){
                UserClass userClass = new UserClass(i);
                myRef1 = FirebaseDatabase.getInstance().getReference("users").child(mAuth).child("my_groups").child("users_group").child(title_group);//.child(i);
                myRef1.setValue(userClass);
            }

            GroupClass groupClass = new GroupClass(title_group);
            myRef2 = FirebaseDatabase.getInstance().getReference("users").child(mAuth).child("my_groups").child("title_group").child(title_group);
//            myRef = FirebaseDatabase.getInstance().getReference("users").child(mAuth).child("my_groups").child("title_group").child(title_group);
            myRef2.setValue(groupClass);
            for(String i: grgr){
                for(String j: items){
                    UserClass userClass = new UserClass(j);
                    myRef_users = FirebaseDatabase.getInstance().getReference("users").child(i).child("groups").child("users_group").child(title_group);//.child(j);
                    myRef_users.setValue(userClass);
                }
                myRef_title= FirebaseDatabase.getInstance().getReference("users").child(i).child("groups").child("title_group").child(title_group);
                myRef_title.setValue(groupClass);
            }

            Intent intent = new Intent(NewGroup.this, MyGroupsActivity.class);
            startActivity(intent);

        });

    }
}



