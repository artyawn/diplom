package com.artyawn.arty.ActivityTask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artyawn.arty.CreateTaskClass;
import com.artyawn.arty.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TaskAdapter extends FirebaseRecyclerAdapter<CreateTaskClass, TaskAdapter.MyViewHolder> {

    FirebaseAuth auth;
    public TaskAdapter(
            @NonNull FirebaseRecyclerOptions<CreateTaskClass> options)
    {
        super(options);
    }


    @Override
    protected void
    onBindViewHolder(@NonNull MyViewHolder holder,
                     int position, @NonNull CreateTaskClass model)
    {
            holder.title.setText(model.getTask_name());
            holder.description.setText(model.getDescription());
            holder.worker.setText(model.getWorker());
            holder.group.setText(model.getGroup());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recucle_task, parent, false);
        return new TaskAdapter.MyViewHolder(view);
    }


            public static class MyViewHolder extends RecyclerView.ViewHolder{

            TextView title, description, worker,group ;
            Button btn_recycle;


            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title_task);
                description = itemView.findViewById(R.id.tv_description);
                worker = itemView.findViewById(R.id.tv_worker);
                group = itemView.findViewById(R.id.tv_group);
                btn_recycle = itemView.findViewById(R.id.btn_recycle);


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


