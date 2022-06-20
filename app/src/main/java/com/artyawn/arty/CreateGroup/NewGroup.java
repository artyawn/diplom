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


import com.artyawn.arty.DataClasses.GroupClass;
import com.artyawn.arty.ActivityMyGroups.MyGroupsActivity;
import com.artyawn.arty.GroupsUsersAdapter;
import com.artyawn.arty.R;
import com.artyawn.arty.DataClasses.UserClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class NewGroup extends AppCompatActivity {

    GroupsUsersAdapter adapter;
    RecyclerView recyclerView;
    EditText grouper_id, title;
    DatabaseReference myRef, myRef1, myRef3, myRef_title ,myRef_users ,  myRef2;
    ImageView add;
    ImageButton new_group, cr_gr;
    ImageView back;
    String mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        add = findViewById(R.id.plus_grouper);
        grouper_id = findViewById(R.id.grouper_id);
        new_group = findViewById(R.id.btn_new_group);
        mAuth = FirebaseAuth.getInstance().getUid();
        title = findViewById(R.id.et_new_group);
        back = findViewById(R.id.back_btn);

        List<String> items = new ArrayList<>();
        List<String> grgr = new ArrayList<>();

        add = findViewById(R.id.plus_grouper);

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
                        grouper_id.setText("");
                    }
                }

            else{
                    Toast.makeText(NewGroup.this, "Ошибка", Toast.LENGTH_SHORT).show();
                }
            });

        });

        back.setOnClickListener(view -> {
            finish();
//            Intent intent = new Intent(NewGroup.this, MyGroupsActivity.class);
//            startActivity(intent);
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












//старая хуня была после инициализации кнопки add


//
//        myRef = FirebaseDatabase.getInstance().getReference("groups").child(mAuth);
//        recyclerView = findViewById(R.id.grouperList);
//
//        recyclerView.setLayoutManager(
//                new LinearLayoutManager(this));
//
//
//        FirebaseRecyclerOptions<UserClass> options
//                = new FirebaseRecyclerOptions.Builder<UserClass>()
//                .setQuery(myRef, UserClass.class)
//                .build();
//
//        adapter = new GroupsUsersAdapter(options);
//        recyclerView.setAdapter(adapter);
//
//
//        recyclerView = findViewById(R.id.grouperList);
//
//        recyclerView.setLayoutManager(
//                new LinearLayoutManager(this));
//
//
//
//        add.setOnClickListener(view -> {
//            count_users++;
//            String counter = String.valueOf(count_users);
//            String gr_id = grouper_id.getText().toString();
//            myRef2 = FirebaseDatabase.getInstance().getReference("groups").child(mAuth).child(counter);
//            myRef1 =FirebaseDatabase.getInstance().getReference("users").child(gr_id).child("user_inf").child("email");
//            myRef1.get().addOnCompleteListener(task -> {
//                if(task.isComplete()){
//                    if(grouper_id.getText().toString().isEmpty()){
//                        Toast.makeText(this, "Введите id", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        UserClass user = new UserClass(gr_id,task.getResult().toString());
//                        myRef2.setValue(user);
//                    }
//                }
//
//                else{
//                    Toast.makeText(NewGroup.this, "Ошибка", Toast.LENGTH_SHORT).show();
//                }
//
//
//        });
//    });
//        new_group.setOnClickListener(view -> {
//
//            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("groups").child(mAuth);
//            ValueEventListener valueEventListener = new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    long count = dataSnapshot.getChildrenCount();
//
//                    for(int i=1; i<count+1; i++){
//                         myRef3 = FirebaseDatabase.getInstance().getReference("groups").child(mAuth).child();
//                    }
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {}
//            };
//            ref.addListenerForSingleValueEvent(valueEventListener);
//
//
//
//        });
//
//
//
//
//    }
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





