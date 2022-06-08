package com.artyawn.arty;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.artyawn.arty.ActivityTask.TaskAdapter;
import com.artyawn.arty.ActivityTask.Tasks;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DialogFragmentGroup extends DialogFragment {

    public DialogFragmentGroup() {
        // Required empty public constructor
    }
    GroupFragmentAdapter adapter;
    DatabaseReference myRef;
    String mAuth;
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       super.onCreateView(inflater, container, savedInstanceState);

      View view = inflater.inflate(R.layout.card_for_picker, container);
    //recycle
        mAuth = FirebaseAuth.getInstance().getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child("users").child(mAuth).child("tasks");

        recyclerView = (RecyclerView)view.findViewById(R.id.tasksList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


        FirebaseRecyclerOptions<CreateTaskClass> options
                = new FirebaseRecyclerOptions.Builder<CreateTaskClass>()
                .setQuery(myRef, CreateTaskClass.class)
                .build();

        adapter = new GroupFragmentAdapter(options);
        recyclerView.setAdapter(adapter);

        this.getDialog().setTitle("AA");

        return view;


    }

}