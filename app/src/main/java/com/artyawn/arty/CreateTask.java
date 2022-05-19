package com.artyawn.arty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateTask extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference myRef;
    private EditText et_new_tsk;
    private Button btn_new_task;

    FirebaseUser user = mAuth.getInstance().getCurrentUser();
    FirebaseListAdapter mAdapter;

    ListView ListUserTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        ListUserTasks = (ListView) findViewById(R.id.discr_for_task);
        myRef = FirebaseDatabase.getInstance().getReference();

        FirebaseListOptions<String> options = new FirebaseListOptions.Builder<String>()
                .setQuery(myRef.child(user.getUid()).child("Tasks"),String.class)
                .setLayout(android.R.layout.simple_list_item_1)
                .build();


        mAdapter = new FirebaseListAdapter<String>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull String s, int position) {
                TextView text = (TextView) v.findViewById(android.R.id.text1);
                text.setText(s);
            }
        };
        ListUserTasks.setAdapter(mAdapter);
        btn_new_task = findViewById(R.id.btn_add);
        et_new_tsk = findViewById(R.id.et_new_tasks);

        btn_new_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child(user.getUid()).child("Tasks").push().setValue(et_new_tsk.getText().toString());
            }
        });
    }
}