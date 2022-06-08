package com.artyawn.arty.ActivityTask;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artyawn.arty.CreateTaskClass;
import com.artyawn.arty.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TaskAdapter extends FirebaseRecyclerAdapter<CreateTaskClass, TaskAdapter.MyViewHolder> {


    public TaskAdapter(@NonNull FirebaseRecyclerOptions<CreateTaskClass> options)
    {
        super(options);
    }


    @Override
    protected void
    onBindViewHolder(@NonNull MyViewHolder holder,
                    int position, @NonNull CreateTaskClass model)
    {
            String id = FirebaseAuth.getInstance().getUid();
            holder.sender_id.setText(model.getSender_id());
            holder.title.setText(model.getTask_name());
            holder.date.setText(model.getDate());
            holder.description.setText(model.getDescription());
            holder.group.setText(model.getGroup());
            holder.sender.setText(model.getSender());
            holder.done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(holder.title.getContext());
                    builder.setTitle("Сообщить о выполнении?");
                    builder.setMessage("Выполненная задача удалится из списка");

                    builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Map<String,Object> map = new HashMap<>();
                            map.put("status","Выполнено");
                            FirebaseDatabase.getInstance().getReference().child("users").child(id).child("tasks").child(model.getTask_name()).removeValue();
                            FirebaseDatabase.getInstance().getReference().child("users").child(model.getSender_id()).child("tasks_for").child(model.getTask_name()).updateChildren(map);
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
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recucle_task, parent, false);
        return new TaskAdapter.MyViewHolder(view);
    }


            public  class MyViewHolder extends RecyclerView.ViewHolder{

            TextView title,description,group, date, sender, sender_id;
            Button done;




            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                sender_id = itemView.findViewById(R.id.sender_id);
                done = (Button)itemView.findViewById(R.id.done);
                title = itemView.findViewById(R.id.title_task);
                description = itemView.findViewById(R.id.tv_description);
                sender = itemView.findViewById(R.id.tv_sender);
                group = itemView.findViewById(R.id.tv_group);
                date = itemView.findViewById(R.id.tv_date);



            }
        }
        }

































//Cтарый адаптер
//public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
//
//        Context context;
//        ArrayList<CreateTaskClass> list;
//
//    public TaskAdapter(Context context, ArrayList<CreateTaskClass> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @NonNull
//        @Override
//        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View v = LayoutInflater.from(context).inflate(R.layout.recucle_task,parent,false);
//            return new com.artyawn.arty.ActivityTask.TaskAdapter.MyViewHolder(v);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull com.artyawn.arty.ActivityTask.TaskAdapter.MyViewHolder holder, int position) {
//            CreateTaskClass task = list.get(position);
//            holder.title.setText(task.getTask_name());
//            holder.description.setText(task.getDescription());
//            holder.worker.setText(task.getWorker());
//            holder.group.setText(task.getGroup());
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return list.size();
//        }
//
//        public static class MyViewHolder extends RecyclerView.ViewHolder{
//
//            TextView title, description, worker,group ;
//
//
//            public MyViewHolder(@NonNull View itemView) {
//                super(itemView);
//                title = itemView.findViewById(R.id.title_task);
//                description = itemView.findViewById(R.id.tv_description);
//                worker = itemView.findViewById(R.id.tv_worker);
//                group = itemView.findViewById(R.id.tv_group);
//            }
//        }
//    }


