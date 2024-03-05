package com.example.mvvm_todo_di.Days.Repository.Source;

import com.example.mvvm_todo_di.Model.Days;

import java.util.List;

import io.reactivex.Single;

public interface DayRemote {
    Single<List<Days>> requestToDays();
    Single<Days> addDays(String dayName,String date);
    Single<Boolean> deleteDays(Days days);

}
