package com.example.mvvm_todo_di.Days.Repository.Source;

import androidx.lifecycle.LiveData;

import com.example.mvvm_todo_di.Model.Days;

import java.util.List;

public interface DayLocal {
    LiveData<List<Days>> getDays();
    void saveDays(List<Days> days);
    void deleteDays(Days days);
    void addDays(Days days);
}
