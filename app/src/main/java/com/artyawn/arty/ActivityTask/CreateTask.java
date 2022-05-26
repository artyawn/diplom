package com.artyawn.arty.ActivityTask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.artyawn.arty.CreateTaskClass;
import com.artyawn.arty.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateTask extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference myRef, myRef_tasks_for;
    private EditText et_new_task,et_worker,et_group, et_description;
    private Button btn_new_task;


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

        btn_new_task.setOnClickListener(view -> {
            String new_task =et_new_task.getText().toString();
            String worker = et_worker.getText().toString();
            String user = mAuth.getInstance().getCurrentUser().getUid();
            String description =et_description.getText().toString();
            String group = et_group.getText().toString();
            if(new_task.isEmpty() ||worker.isEmpty() ||description.isEmpty() ||group.isEmpty()){
                Toast.makeText(CreateTask.this, "Введены не все данные", Toast.LENGTH_SHORT).show();

            }
            else {

                myRef = FirebaseDatabase.getInstance().getReference("users/"+worker+"/"+"tasks/"+new_task+"/");
                myRef_tasks_for = FirebaseDatabase.getInstance().getReference("users/"+user+"/"+"tasks_for_mates/"+new_task+"/");
                CreateTaskClass task = new CreateTaskClass(new_task,group,worker,description);
                myRef.setValue(task);
                myRef_tasks_for.setValue(task);

            }
        });



    }
}


