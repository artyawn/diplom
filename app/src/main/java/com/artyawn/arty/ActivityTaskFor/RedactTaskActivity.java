package com.artyawn.arty.ActivityTaskFor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.artyawn.arty.R;

public class RedactTaskActivity extends AppCompatActivity {
    ImageView back;
    EditText title, date, description, group, worker;
    ImageButton save, pick_worker, pick_date, pick_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redact_task2);


        back = findViewById(R.id.back_btn_from_redact);
        title = findViewById(R.id.redact_task);
        date = findViewById(R.id.redact_date);
        description = findViewById(R.id.redact_description);
        group = findViewById(R.id.redact_group);
        worker = findViewById(R.id.redact_worker);
        save = findViewById(R.id.btn_save_task);
        pick_date = findViewById(R.id.pick_date_redact);
        pick_worker = findViewById(R.id.pick_worker_redact);
        pick_group = findViewById(R.id.pick_group_redact);


        String title_value = getIntent().getStringExtra("task_name");
        String date_value = getIntent().getStringExtra("date");
        String description_value = getIntent().getStringExtra("description");
        String group_value = getIntent().getStringExtra("group");
        String status_value = getIntent().getStringExtra("status");
        String workerID_value = getIntent().getStringExtra("worker_id");
        String worker_value = getIntent().getStringExtra("worker");


        date.setText(date_value);
        title.setText(title_value);
        description.setText(description_value);
        group.setText(group_value);
        worker.setText(worker_value);

        back.setOnClickListener(view -> {
            finish();
        });


        save.setOnClickListener(view -> {
//                        Map<String,Object> map1 = new HashMap<>();
//                        map1.put("date",redact_date.getText().toString());
//                        map1.put("description",redact_description.getText().toString());
//                        map1.put("group",redact_group.getText().toString());
//                        map1.put("status",model.getStatus());
//                        map1.put("task_name",redact_title.getText().toString());
//                        map1.put("worker",redact_worker.getText().toString());
//                        map1.put("worker_id",model.getWorker_id());
//
//                        FirebaseDatabase.getInstance().getReference().child("users").child(id).child("tasks_for").child(model.getTask_name()).removeValue();
//                        FirebaseDatabase.getInstance().getReference().child("users").child(id).child("tasks_for").child(redact_title.getText().toString()).updateChildren(map1);
//
//                        FirebaseDatabase.getInstance().getReference().child("users").child(id).child("groups_tasks_for").child(model.getTask_name()).removeValue();
//                        FirebaseDatabase.getInstance().getReference().child("users").child(id).child("groups_tasks_for").child(redact_title.getText().toString()).updateChildren(map1);
//
//                        FirebaseDatabase.getInstance().getReference().child("users").child(model.worker_id).child("tasks").child(model.getTask_name()).removeValue();
//                        FirebaseDatabase.getInstance().getReference().child("users").child(model.worker_id).child("tasks").child(redact_title.getText().toString()).updateChildren(map1);
//
//                        FirebaseDatabase.getInstance().getReference().child("users").child(model.worker_id).child("groups_tasks").child(model.getTask_name()).removeValue();
//                        FirebaseDatabase.getInstance().getReference().child("users").child(model.worker_id).child("groups_tasks").child(redact_title.getText().toString()).updateChildren(map1);
        });
    }
}