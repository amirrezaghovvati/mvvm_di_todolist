package com.example.mvvm_todo_di.Tasks.Repository.Source;

import com.example.mvvm_todo_di.Model.Remote.ApiService;
import com.example.mvvm_todo_di.Model.Tasks;
import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

public class TaskRemoteSource implements TaskRemote{
    private ApiService apiService;
    private JsonObject jsonObject;
    @Inject
    public TaskRemoteSource(ApiService apiService,JsonObject jsonObject) {
        this.apiService = apiService;
        this.jsonObject=jsonObject;
    }

    @Override
    public Single<List<Tasks>> receiveTasks(int taskId) {
        jsonObject.addProperty("id",taskId);
        return apiService.getAllTasks(jsonObject);
    }

    @Override
    public Single<Tasks> addTasks(int dayId, String title) {
        jsonObject.addProperty("dayId",dayId);
        jsonObject.addProperty("title",title);
        return apiService.addTasks(jsonObject);
    }

    @Override
    public Single<Boolean> deleteTasks(Tasks tasks) {
        jsonObject.addProperty("id",tasks.getTaskID());
        return apiService.deleteTasks(jsonObject);
    }
}
