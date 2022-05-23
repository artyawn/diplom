package com.artyawn.arty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateTask extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private EditText et_new_task,et_worker,et_group, et_description;
    private Button btn_new_task;

    FirebaseUser user = mAuth.getInstance().getCurrentUser();
    FirebaseListAdapter mAdapter;



    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        et_new_task = findViewById(R.id.et_new_task);
        et_description = findViewById(R.id.description);
        et_worker= findViewById(R.id.worker);
        et_group = findViewById(R.id.et_group);
        btn_new_task = findViewById(R.id.btn_new_task);

        btn_new_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String new_task =et_new_task.getText().toString();
                String worker = et_worker.getText().toString();
                String description =et_description.getText().toString();
                String group = et_group.getText().toString();
                if(new_task.isEmpty() ||worker.isEmpty() ||description.isEmpty() ||group.isEmpty()){
                    Toast.makeText(CreateTask.this, "Введены не все данные", Toast.LENGTH_SHORT).show();

                }
                else {
                    myRef = FirebaseDatabase.getInstance().getReference("users/"+worker+"/"+"tasks");
                    CreateTaskClass task = new CreateTaskClass(new_task,group,worker,description);
                    myRef.setValue(task);
                }
            }
        });


//        ListUserTasks = (ListView) findViewById(R.id.discr_for_task);
//        myRef = FirebaseDatabase.getInstance().getReference();

//        FirebaseListOptions<String> options = new FirebaseListOptions.Builder<String>()
//                .setQuery(myRef.child(user.getUid()).child("Tasks"),String.class)
//                .setLayout(android.R.layout.simple_list_item_1)
//                .build();
//
//
//        mAdapter = new FirebaseListAdapter<String>(options) {
//            @Override
//            protected void populateView(@NonNull View v, @NonNull String s, int position) {
//                TextView text = (TextView) v.findViewById(android.R.id.text1);
//                text.setText(s);
//            }
//        };
//        ListUserTasks.setAdapter(mAdapter);
//        btn_new_task = findViewById(R.id.btn_add);
//        et_new_task = findViewById(R.id.et_new_tasks);
//
//        btn_new_task.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myRef.child(user.getUid()).child("Tasks").push().setValue(et_new_task.getText().toString());
//            }
//        });
    }
}


