package com.example.mvvm_todo_di.Tasks.Repository.Source;

import com.example.mvvm_todo_di.Model.Tasks;

import java.util.List;

import io.reactivex.Single;

public interface TaskRemote {
    Single<List<Tasks>> receiveTasks(int taskId);
    Single<Tasks> addTasks(int dayId,String title);
    Single<Boolean> deleteTasks(Tasks tasks);
}
