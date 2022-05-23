package com.artyawn.arty;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewGroup extends AppCompatActivity {
    private DatabaseReference myRef;
    FirebaseDatabase database;
    private Button create_group;
    private EditText grouper, name_new_group;
    private TextView add_grouper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        create_group = findViewById(R.id.create_new_group);
        grouper = findViewById(R.id.grouper);
        name_new_group = findViewById(R.id.name_of_new_group);
        add_grouper= findViewById(R.id.tv_add_grouper);


        add_grouper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(uid.isEmpty()){
//                    Toast.makeText(NewGroup.this, "введите id", Toast.LENGTH_SHORT).show();
//                }
//                else{
                    String uid = grouper.getText().toString()+"/";
                    String title = name_new_group.getText().toString();
                    String path = "users/"+ uid +"groups/" + title;
                    myRef = FirebaseDatabase.getInstance().getReference(path);



                    GroupClass new_group = new GroupClass(title,uid);
                    myRef.setValue(new_group);


//                }


            }
        });

        create_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uid = grouper.getText().toString();
                String title = name_new_group.getText().toString();
                myRef = FirebaseDatabase.getInstance().getReference("groups").child(title);
                GroupClass group = new GroupClass(title,uid);
                myRef.setValue(group);
            }
        });

    }
}