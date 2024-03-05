package com.example.mvvm_todo_di.Tasks.Repository.repo;

import androidx.lifecycle.LiveData;

import com.example.mvvm_todo_di.Model.Tasks;

import java.util.List;

import io.reactivex.Single;

public interface TaskRepository {
    LiveData<List<Tasks>> getAllTasks(int dayId);
    Single<List<Tasks>> requestToTasks(int dayId);
    Single<Tasks> addTasks(int dayId,String title);
    Single<Boolean> deleteTasks(Tasks tasks);
    void saveTasks(List<Tasks> tasks);
    void taskDeleted(Tasks tasks);
    void taskAdded(Tasks tasks);
    void updateTasks(Tasks tasks);
}
