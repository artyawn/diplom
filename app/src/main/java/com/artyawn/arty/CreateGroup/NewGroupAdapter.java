package com.artyawn.arty.CreateGroup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artyawn.arty.CreateTaskClass;
import com.artyawn.arty.R;
import com.artyawn.arty.UserClass;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
public class NewGroupAdapter extends FirebaseRecyclerAdapter<UserClass, com.artyawn.arty.CreateGroup.NewGroupAdapter.MyViewHolder> {

    public NewGroupAdapter(
            @NonNull FirebaseRecyclerOptions<UserClass> options) {
        super(options);
    }


    @Override
    protected void
    onBindViewHolder(@NonNull com.artyawn.arty.CreateGroup.NewGroupAdapter.MyViewHolder holder,
                     int position, @NonNull UserClass model) {
        holder.email.setText(model.getEmail());

    }

    @NonNull
    @Override
    public com.artyawn.arty.CreateGroup.NewGroupAdapter.MyViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new com.artyawn.arty.CreateGroup.NewGroupAdapter.MyViewHolder(view);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView email;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.tvemail);

        }
    }
}









//старый адаптер
//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
//    Context context;
//    ArrayList<UserClass> list;
//
//    public MyAdapter(Context context, ArrayList<UserClass> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        UserClass user = list.get(position);
//        holder.Email.setText(user.getEmail());
//        holder.Id.setText(user.getId());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//
//        TextView Id, Email;
//
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            Id = itemView.findViewById(R.id.tvId);
//            Email = itemView.findViewById(R.id.tvemail);
//        }
//    }
//}
