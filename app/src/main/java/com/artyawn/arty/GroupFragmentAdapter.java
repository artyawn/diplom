package com.artyawn.arty;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artyawn.arty.ActivityTask.TaskAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;

public class GroupFragmentAdapter  extends FirebaseRecyclerAdapter<CreateTaskClass, GroupFragmentAdapter.MyViewHolder> {

    FirebaseAuth auth;
    public GroupFragmentAdapter(
            @NonNull FirebaseRecyclerOptions<CreateTaskClass> options)
    {
        super(options);
    }


    @Override
    protected void
    onBindViewHolder(@NonNull GroupFragmentAdapter.MyViewHolder holder,
                     int position, @NonNull CreateTaskClass model)
    {
        holder.title.setText(model.getTask_name());
        holder.date.setText(model.getDate());
        holder.description.setText(model.getDescription());
        holder.worker.setText(model.getWorker());
        holder.group.setText(model.getGroup());

    }

    @NonNull
    @Override
    public GroupFragmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recucle_task, parent, false);
        return new GroupFragmentAdapter.MyViewHolder(view);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, description, worker,group, date ;

        Button btn_recycle;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_task);
            description = itemView.findViewById(R.id.tv_description);
            worker = itemView.findViewById(R.id.tv_worker);
            group = itemView.findViewById(R.id.tv_group);
            date = itemView.findViewById(R.id.tv_date);



        }
    }
}

