package com.example.mvvm_todo_di.Model.Remote;

import com.example.mvvm_todo_di.Model.Days;
import com.example.mvvm_todo_di.Model.Tasks;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface ApiService {
    @POST("day/getAll")
    Single<List<Days>> getAllDays();
    @POST("day/add")
    Single<Days> addDays(@Body JsonObject jsonObject);
    @HTTP(method = "DELETE",path = "day/delete",hasBody = true)
    Single<Boolean> deleteDays(@Body JsonObject jsonObject);


    @POST("tasks/getAll")
    Single<List<Tasks>> getAllTasks(@Body JsonObject jsonObject);
    @POST("tasks/add")
    Single<Tasks> addTasks(@Body JsonObject jsonObject);
    @HTTP(method = "DELETE",path = "tasks/delete",hasBody = true)
    Single<Boolean> deleteTasks(@Body JsonObject jsonObject);

}
