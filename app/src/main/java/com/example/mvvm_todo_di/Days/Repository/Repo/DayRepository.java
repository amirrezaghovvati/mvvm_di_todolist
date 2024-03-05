package com.example.mvvm_todo_di.Days.Repository.Repo;

import androidx.lifecycle.LiveData;

import com.example.mvvm_todo_di.Model.Days;

import java.util.List;

import io.reactivex.Single;

public interface DayRepository {
    Single<List<Days>> requestToDays();
    void saveDays(List<Days> days);
    LiveData<List<Days>> getDays();
    Single<Days> addDays(String dayName,String date);
    void dayAdded(Days days);
    Single<Boolean> deleteDays(Days days);
    void deletedDays(Days days);


}
