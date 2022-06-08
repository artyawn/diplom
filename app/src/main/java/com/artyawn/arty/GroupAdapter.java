package com.artyawn.arty;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artyawn.arty.ActivityTaskFor.TaskForAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;

    public class GroupAdapter extends FirebaseRecyclerAdapter<GroupClass, GroupAdapter.MyViewHolder> {

        FirebaseAuth auth;
        public GroupAdapter(
                @NonNull FirebaseRecyclerOptions<GroupClass> options)
        {
            super(options);
        }


        @Override
        protected void
        onBindViewHolder(@NonNull GroupAdapter.MyViewHolder holder,
                         int position, @NonNull GroupClass model)
        {
            holder.title.setText(model.getTitle());
//            holder.items.setText(model.getItems());



        }

        @NonNull
        @Override
        public GroupAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
            return new com.artyawn.arty.GroupAdapter.MyViewHolder(view);
        }


        public static class MyViewHolder extends RecyclerView.ViewHolder{

            TextView title;
            ListView items;



            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title_task);
                items = itemView.findViewById(R.id.lv_groupers);





            }
        }
    }



