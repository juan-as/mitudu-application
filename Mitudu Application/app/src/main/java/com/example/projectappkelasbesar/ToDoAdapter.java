package com.example.projectappkelasbesar;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    ArrayList<ToDoClass> toDoClasses;
    Context context;

    public ToDoAdapter(Context context) {
        this.context = context;
    }

    public void setToDoClasses(ArrayList<ToDoClass> toDoClasses) {
        this.toDoClasses = toDoClasses;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_item, parent, false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        holder.tvNamaToDo.setText(toDoClasses.get(position).getNamaToDo());
        holder.tvTanggalToDo.setText(toDoClasses.get(position).getTanggalToDoFinal());
        holder.tvJamToDo.setText(toDoClasses.get(position).getJamToDoFinal());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditTaskPage.class);
                intent.putExtra("Task", toDoClasses.get(position));
                context.startActivity(intent);
            }
        });
        Long currdate = Calendar.getInstance().getTimeInMillis();
        Date startDate1 = new Date();
        Date endDate1 = new Date();
        try {
            endDate1 = format.parse(toDoClasses.get(position).getTanggalToDoFinal());
            startDate1 = format.parse(toDoClasses.get(position).getJamToDoFinal());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Long endDate = endDate1.getTime();
        Long startDate = startDate1.getTime();
        Long diffDate = endDate - startDate;

        Long currDiff = currdate - startDate;
        float progress = (float)currDiff/diffDate*100;
        holder.progressBar.setProgress((int) progress);
        if((int)progress > 100){
            holder.tvTanggalToDo.setText("OVERDUE");
            holder.tvJamToDo.setText("OVERDUE");
        }
    }

    @Override
    public int getItemCount() {
        return toDoClasses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvNamaToDo, tvTanggalToDo, tvJamToDo;
        ProgressBar progressBar;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNamaToDo = itemView.findViewById(R.id.tvNamaToDo);
            tvTanggalToDo = itemView.findViewById(R.id.tvTanggalToDo);
            tvJamToDo = itemView.findViewById(R.id.tvJamToDo);
            progressBar = itemView.findViewById(R.id.progressIndicator);
        }
    }

    public String getTodayDate() {
        Date todayDate = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(todayDate);
        return date;
    }

}
