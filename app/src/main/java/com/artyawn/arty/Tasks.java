package com.artyawn.arty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Tasks extends AppCompatActivity {
    private FirebaseAuth mauth;
    private DatabaseReference muRef;
    private List<String> DiscrTasks;

    ListView UserTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        UserTasks=(ListView) findViewById(R.id.user_tasks);

        muRef= FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = mauth.getInstance().getCurrentUser();

        muRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<List<String>> t = new GenericTypeIndicator<List<String>>() {
                };
                DiscrTasks =  dataSnapshot.child("Tasks").getValue(t);

                updateUI();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void updateUI(){
        ArrayAdapter<String> adapter= new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1);
        UserTasks.setAdapter(adapter);
    }
}