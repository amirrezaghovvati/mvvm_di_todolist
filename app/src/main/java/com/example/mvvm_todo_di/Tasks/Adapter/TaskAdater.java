package com.example.mvvm_todo_di.Tasks.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_todo_di.Model.Tasks;
import com.example.mvvm_todo_di.R;

import java.util.List;

import javax.inject.Inject;

public class TaskAdater extends RecyclerView.Adapter<TaskAdater.TaskViewHolder> {
    private List<Tasks> tasks;
    private TaskEventListener eventListener;
    @Inject
    public TaskAdater(List<Tasks> tasks, TaskEventListener eventListener) {
        this.tasks = tasks;
        this.eventListener = eventListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item,parent,false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
            holder.bindTasks(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder {
        private ImageView done;
        private TextView title;
        private ImageView deleteIc;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteIc=itemView.findViewById(R.id.deleteTaskTitleIcon);
            done=itemView.findViewById(R.id.detailCheckedState);
            title=itemView.findViewById(R.id.tvTitleTasks);
        }
        public void bindTasks(Tasks tasks){
            deleteIc.setOnClickListener(v->{
                eventListener.onDeleteClicked(tasks);
            });
            done.setOnClickListener(v->{
                if (tasks.isDone()){
                    tasks.setDone(false);
                    done.setImageResource(R.drawable.un_done_ic);
                }else {
                    tasks.setDone(true);
                    done.setImageResource(R.drawable.done_ic);
                }
                eventListener.onDoneClicked(tasks);
            });
            title.setText(tasks.getTitle());
        }
    }
    public interface TaskEventListener{
        void onDeleteClicked(Tasks tasks);
        void onDoneClicked(Tasks tasks);
    }
}
