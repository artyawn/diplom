package com.artyawn.arty.CreateGroup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artyawn.arty.R;

import java.util.List;

public class CreateGroupAdapter extends RecyclerView.Adapter<HolderGrouper>{

    List<String> items;

    public CreateGroupAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public HolderGrouper onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.creat_group_item,parent,false);
        return new HolderGrouper(view).linkAdapter(this);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderGrouper holder, int position) {
        holder.email_grouper.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
class HolderGrouper extends RecyclerView.ViewHolder{

    TextView email_grouper;
    CreateGroupAdapter adapter;

    public HolderGrouper(@NonNull View itemView) {
        super(itemView);

        email_grouper = itemView.findViewById(R.id.email_create_group);
        itemView.findViewById(R.id.remove).setOnClickListener(view -> {
            adapter.items.remove(getAbsoluteAdapterPosition());
        });
    }

    public HolderGrouper linkAdapter(CreateGroupAdapter adapter){
        this.adapter=adapter;
        return this;

    }
}