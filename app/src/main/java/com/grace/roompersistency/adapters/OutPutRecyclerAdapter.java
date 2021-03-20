package com.grace.roompersistency.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.grace.roompersistency.R;
import com.grace.roompersistency.database.dataentity.User;

import java.util.ArrayList;

public class OutPutRecyclerAdapter extends RecyclerView.Adapter<OutPutRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<User> userArrayList;

    public OutPutRecyclerAdapter(Context context, ArrayList<User> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.output_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User user = userArrayList.get(position);

        holder.firstName.setText(user.getFirstName());
        holder.secondName.setText(user.getSecondName());
        holder.age.setText(String.valueOf(user.getAge()));
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView firstName, secondName, age;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.firstName_output);
            secondName = itemView.findViewById(R.id.lastName_output);
            age = itemView.findViewById(R.id.age_output);
        }
    }
}
