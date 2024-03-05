package com.example.mvvm_todo_di.Tasks.Repository.repo;

import androidx.lifecycle.LiveData;

import com.example.mvvm_todo_di.Model.Tasks;
import com.example.mvvm_todo_di.Tasks.Repository.Source.TaskLocal;
import com.example.mvvm_todo_di.Tasks.Repository.Source.TaskRemote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class TaskRepositoryImpl implements TaskRepository{
    private TaskRemote taskRemote;
    private TaskLocal taskLocal;
    @Inject
    public TaskRepositoryImpl(TaskRemote taskRemote, TaskLocal taskLocal) {
        this.taskRemote = taskRemote;
        this.taskLocal = taskLocal;
    }

    @Override
    public LiveData<List<Tasks>> getAllTasks(int dayId) {
        return taskLocal.getAllTasks(dayId);
    }

    @Override
    public Single<List<Tasks>> requestToTasks(int dayId) {
        return taskRemote.receiveTasks(dayId);
    }

    @Override
    public Single<Tasks> addTasks(int dayId, String title) {
        return taskRemote.addTasks(dayId,title);
    }

    @Override
    public Single<Boolean> deleteTasks(Tasks tasks) {
        return taskRemote.deleteTasks(tasks);
    }

    @Override
    public void saveTasks(List<Tasks> tasks) {
        taskLocal.updateTasks(tasks);
    }

    @Override
    public void taskDeleted(Tasks tasks) {
        taskLocal.taskDeleted(tasks);
    }

    @Override
    public void taskAdded(Tasks tasks) {
        taskLocal.taskAdded(tasks);
    }

    @Override
    public void updateTasks(Tasks tasks) {
        taskLocal.updateTaskss(tasks);
    }
}
