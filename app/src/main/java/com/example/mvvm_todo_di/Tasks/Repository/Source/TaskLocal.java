package com.example.mvvm_todo_di.Tasks.Repository.Source;

import androidx.lifecycle.LiveData;

import com.example.mvvm_todo_di.Model.Tasks;

import java.util.List;

public interface TaskLocal {
    void taskAdded(Tasks tasks);
    void taskDeleted(Tasks tasks);
    LiveData<List<Tasks>> getAllTasks(int dayId);
    void updateTasks(List<Tasks> tasks);
    void updateTaskss(Tasks tasks);
}
