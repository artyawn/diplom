package com.artyawn.arty;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artyawn.arty.DataClasses.UserClass;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class GroupsUsersAdapter extends FirebaseRecyclerAdapter<UserClass, GroupsUsersAdapter.UsersViewHolder> {


    String mAuth = FirebaseAuth.getInstance().getUid();

    public GroupsUsersAdapter(@NonNull FirebaseRecyclerOptions<UserClass> options)
    {
        super(options);

    }


    @Override
    protected void
    onBindViewHolder(@NonNull GroupsUsersAdapter.UsersViewHolder holder,
                     int position, @NonNull UserClass model) {

        holder.id.setText(model.getId());
        holder.email.setText(model.getEmail());
        holder.delete.setOnClickListener(view -> {
            FirebaseDatabase.getInstance().getReference("groups").child(mAuth).child(model.getId()).removeValue();

        });

    }

    @NonNull
    @Override
    public GroupsUsersAdapter.UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.creat_group_item, parent, false);
        return new GroupsUsersAdapter.UsersViewHolder(view);
    }


    public  class UsersViewHolder extends RecyclerView.ViewHolder{

        TextView email,id;
        Button delete;





        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_create_group);
            email = itemView.findViewById(R.id.email_create_group);
            delete = (Button) itemView.findViewById(R.id.remove);


        }
    }

}
