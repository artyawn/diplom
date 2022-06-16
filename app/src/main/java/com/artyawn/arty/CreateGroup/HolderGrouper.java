package com.artyawn.arty.CreateGroup;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artyawn.arty.R;

class HolderGrouper extends RecyclerView.ViewHolder {

    TextView email_grouper;
    CreateGroupAdapter adapter;

    public HolderGrouper(@NonNull View itemView) {
        super(itemView);

        email_grouper = itemView.findViewById(R.id.email_create_group);
        itemView.findViewById(R.id.remove).setOnClickListener(view -> {
            adapter.items.remove(getAbsoluteAdapterPosition());
        });
    }

    public HolderGrouper linkAdapter(CreateGroupAdapter adapter) {
        this.adapter = adapter;
        return this;

    }
}
