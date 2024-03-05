package com.example.mvvm_todo_di.Days.Repository.Source;

import androidx.lifecycle.LiveData;

import com.example.mvvm_todo_di.Model.Days;
import com.example.mvvm_todo_di.Model.Local.AppDao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

public class DayLocalSource implements DayLocal{
    private AppDao appDao;
    @Inject
    public DayLocalSource(AppDao appDao ) {
        this.appDao = appDao;
    }

    @Override
    public LiveData<List<Days>> getDays() {
        return appDao.getDays();
    }

    @Override
    public void saveDays(List<Days> days) {
        appDao.updateDays(days);
    }

    @Override
    public void deleteDays(Days days) {
        appDao.deleteDays(days);
    }

    @Override
    public void addDays(Days days) {
        appDao.addDays(days);

    }
}
