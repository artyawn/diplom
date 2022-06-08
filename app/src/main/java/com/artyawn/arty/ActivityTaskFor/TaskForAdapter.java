package com.artyawn.arty.ActivityTaskFor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artyawn.arty.ActivityTask.TaskAdapter;
import com.artyawn.arty.CreateTaskClass;
import com.artyawn.arty.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class TaskForAdapter extends FirebaseRecyclerAdapter<CreateTaskClass, TaskForAdapter.MyViewHolder> {


    public TaskForAdapter(
            @NonNull FirebaseRecyclerOptions<CreateTaskClass> options)
    {
        super(options);
    }


    @Override
    protected void
    onBindViewHolder(@NonNull TaskForAdapter.MyViewHolder holder,
                     int position, @NonNull CreateTaskClass model)
    {
        String id = FirebaseAuth.getInstance().getUid();
        holder.status.setText(model.getStatus());
        holder.title.setText(model.getTask_name());
        holder.date.setText(model.getDate());
        holder.description.setText(model.getDescription());
        holder.worker.setText(model.getWorker());
        holder.group.setText(model.getGroup());
        holder.worker_id.setText(model.getWorker_id());
        holder.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.title.getContext());
                builder.setTitle("Считать задачу выполненной?");
                builder.setMessage("Задача удалится из списка");

                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("users").child(id).child("tasks_for").child(model.getTask_name()).removeValue();
                        FirebaseDatabase.getInstance().getReference().child("users").child(model.getWorker_id()).child("tasks").child(model.getTask_name()).removeValue();


                    }
                });

                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.title.getContext(), "В другой раз", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.show();


            }
        });





    }

    @NonNull
    @Override
    public TaskForAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_task_for, parent, false);
        return new TaskForAdapter.MyViewHolder(view);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title,description,worker,group, date, status, worker_id;
        Button redact, done;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            worker_id = itemView.findViewById(R.id.worker_id);
            status = itemView.findViewById(R.id.status);
            done = (Button)itemView.findViewById(R.id.done);
            redact = (Button)itemView.findViewById(R.id.redact);
            title = itemView.findViewById(R.id.title_task);
            description = itemView.findViewById(R.id.tv_description);
            worker = itemView.findViewById(R.id.tv_worker_mate);
            group = itemView.findViewById(R.id.tv_group);
            date = itemView.findViewById(R.id.tv_date);




        }
    }
}

