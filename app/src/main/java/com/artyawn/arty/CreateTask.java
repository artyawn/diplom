package com.artyawn.arty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.artyawn.arty.ActivityTaskFor.MatesTasksActivity;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateTask extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference myRef, myRef_tasks_for, myRefGroupsTask, myRefGroupsTaskfor;
    private EditText et_new_task,et_worker,et_group, et_description, date;
    private ImageButton btn_new_task;
    private ImageView back, pick_date, pick_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        et_new_task = findViewById(R.id.et_new_task);
        et_description = findViewById(R.id.description);
        et_worker= findViewById(R.id.worker);
        et_group = findViewById(R.id.et_group);
        btn_new_task = findViewById(R.id.btn_new_task);
        date = findViewById(R.id.date);
        back = findViewById(R.id.back_btn);
        pick_date = findViewById(R.id.pick_date);
        pick_group = findViewById(R.id.pick_group);

        String value = getIntent().getStringExtra("title");
        et_group.setText(value);



        btn_new_task.setOnClickListener(view -> {
            String new_task =et_new_task.getText().toString();
            String worker_id = et_worker.getText().toString();
            String task_date = date.getText().toString();
            String sender_id = mAuth.getInstance().getCurrentUser().getUid();
            String description =et_description.getText().toString();
            String group = et_group.getText().toString();
            String sender = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            String worker = "worker@mail.ru";


            if(new_task.isEmpty() ||worker_id.isEmpty() ||description.isEmpty() ||group.isEmpty()){
                Toast.makeText(CreateTask.this, "Введены не все данные", Toast.LENGTH_SHORT).show();

            }
            else {



                myRef = FirebaseDatabase.getInstance().getReference("users/"+worker_id+"/"+"tasks/"+new_task+"/");
                myRef_tasks_for = FirebaseDatabase.getInstance().getReference("users/"+sender_id+"/"+"tasks_for/"+new_task+"/");
                myRefGroupsTask = FirebaseDatabase.getInstance().getReference("users/"+worker_id+"/"+"groups_tasks/"+group+"/"+new_task+"/");
                myRefGroupsTaskfor = FirebaseDatabase.getInstance().getReference("users/"+sender_id+"/"+"groups_tasks_for/"+group+"/"+new_task+"/");
                CreateTaskClass task1 = new CreateTaskClass("Не выполнено",new_task,task_date,description,worker,group, worker_id);
                CreateTaskClass task = new CreateTaskClass(new_task,task_date,description, sender,group,sender_id);
                myRef.setValue(task);
                myRef_tasks_for.setValue(task1);
                myRefGroupsTask.setValue(task);
                myRefGroupsTaskfor.setValue(task1);
                Intent intent = new Intent(CreateTask.this, MatesTasksActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(view -> {
            Intent intent = new Intent(CreateTask.this, MatesTasksActivity.class);
            startActivity(intent);
        });

    }
    }



