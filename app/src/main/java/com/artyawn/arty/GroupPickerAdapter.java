package com.artyawn.arty;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artyawn.arty.CreateTask.CreateTask;
import com.artyawn.arty.DataClasses.GroupClass;
import com.artyawn.arty.GroupsAdmin.GroupsAdminUsers;
import com.artyawn.arty.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class GroupPickerAdapter extends FirebaseRecyclerAdapter<GroupClass, GroupPickerAdapter.MyGroupViewHolder> {


    public GroupPickerAdapter(@NonNull FirebaseRecyclerOptions<GroupClass> options)
    {
        super(options);

    }


    @Override
    protected void
    onBindViewHolder(@NonNull GroupPickerAdapter.MyGroupViewHolder holder,
                     int position, @NonNull GroupClass model)
    {
        holder.title.setText(model.getTitle());
        holder.pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(view.getContext(), CreateTask.class);
                intent1.putExtra("title_group", model.getTitle());
                view.getContext().startActivity(intent1);
            }
        });

    }

    @NonNull
    @Override
    public MyGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_for_group_picker, parent, false);
        return new GroupPickerAdapter.MyGroupViewHolder(view);
    }


    public  class MyGroupViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        Button pick;





        public MyGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_group);
            pick = (Button) itemView.findViewById(R.id.pick);


        }
    }

}



