package com.example.projectappkelasbesar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DoneAdapter extends RecyclerView.Adapter<DoneAdapter.MyViewHolder> {

    ArrayList<ToDoClass> toDoClasses;

    public void setToDoClasses(ArrayList<ToDoClass> toDoClasses) {
        this.toDoClasses = toDoClasses;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.done_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvNamaToDo.setText(toDoClasses.get(position).getNamaToDo());
        holder.tvTanggalToDo.setText(toDoClasses.get(position).getTanggalToDoFinal());
        holder.tvJamToDo.setText(toDoClasses.get(position).getJamToDoFinal());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return toDoClasses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaToDo, tvTanggalToDo, tvJamToDo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaToDo = itemView.findViewById(R.id.tvNamaToDo);
            tvTanggalToDo = itemView.findViewById(R.id.tvTanggalToDo);
            tvJamToDo = itemView.findViewById(R.id.tvJamToDo);

        }
    }

}
