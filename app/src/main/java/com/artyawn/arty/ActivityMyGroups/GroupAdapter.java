package com.artyawn.arty.ActivityMyGroups;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artyawn.arty.GroupClass;
import com.artyawn.arty.GroupsAdmin.GroupsAdminUsers;
import com.artyawn.arty.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class GroupAdapter extends FirebaseRecyclerAdapter<GroupClass, GroupAdapter.MyGroupViewHolder> {


        public GroupAdapter(@NonNull FirebaseRecyclerOptions<GroupClass> options)
        {
            super(options);

        }


        @Override
        protected void
        onBindViewHolder(@NonNull GroupAdapter.MyGroupViewHolder holder,
                         int position, @NonNull GroupClass model)
        {
            holder.title.setText(model.getTitle());
            holder.info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                Intent intent1 = new Intent(view.getContext(), GroupsAdminUsers.class);
                intent1.putExtra("title", model.getTitle());
                view.getContext().startActivity(intent1);
                }
            });

        }

        @NonNull
        @Override
        public MyGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_item, parent, false);
            return new GroupAdapter.MyGroupViewHolder(view);
        }


        public  class MyGroupViewHolder extends RecyclerView.ViewHolder{

            TextView title;
            Button info;





            public MyGroupViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title_group);
                info = (Button) itemView.findViewById(R.id.info);


            }
        }

    }



