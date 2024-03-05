package com.example.mvvm_todo_di.Days.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_todo_di.Model.Days;
import com.example.mvvm_todo_di.R;

import java.util.List;

import javax.inject.Inject;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.DayViewHolder> {
    private List<Days> days;
    private DayAdapterEvent event;
    @Inject
    public DayAdapter(List<Days> days, DayAdapterEvent event) {
        this.days = days;
        this.event = event;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.day_item,parent,false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        holder.bindDays(days.get(position));
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public class DayViewHolder extends RecyclerView.ViewHolder {
        private TextView tvDate;
        private TextView tvDayName;
        private ImageView icDelete;
        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate=itemView.findViewById(R.id.dayTvDate);
            tvDayName=itemView.findViewById(R.id.dayTvDayWeek);
            icDelete=itemView.findViewById(R.id.deleteDaysItem);
        }
        public void bindDays(Days days){
            tvDayName.setText(days.getDayName());
            tvDate.setText(days.getDate());
            icDelete.setOnClickListener(v->{
                event.onDeleteClicked(days);
            });
            itemView.setOnClickListener(v->{
                event.onItemClicked(days);
            });
        }
    }
    public interface DayAdapterEvent{
        void onDeleteClicked(Days days);
        void onItemClicked(Days days);
    }
}
