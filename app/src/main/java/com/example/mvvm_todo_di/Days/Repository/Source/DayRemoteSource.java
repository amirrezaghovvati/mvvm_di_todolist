package com.example.mvvm_todo_di.Days.Repository.Source;

import com.example.mvvm_todo_di.Model.Days;
import com.example.mvvm_todo_di.Model.Remote.ApiService;
import com.google.gson.JsonObject;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

public class DayRemoteSource implements DayRemote{
    private ApiService apiService;
    private JsonObject jsonObject;
    @Inject
    public DayRemoteSource(ApiService apiService,@Named("JSON") JsonObject jsonObject){
        this.apiService=apiService;
        this.jsonObject=jsonObject;
    }
    @Override
    public Single<List<Days>> requestToDays() {
        return apiService.getAllDays();
    }

    @Override
    public Single<Days> addDays(String dayName, String date) {

        jsonObject.addProperty("dayName",dayName);
        jsonObject.addProperty("date",date);
        return apiService.addDays(jsonObject);
    }

    @Override
    public Single<Boolean> deleteDays(Days days) {
        jsonObject.addProperty("id",days.getDayId());
        return apiService.deleteDays(jsonObject);
    }
}
