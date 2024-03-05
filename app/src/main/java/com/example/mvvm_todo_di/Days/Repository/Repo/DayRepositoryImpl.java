package com.example.mvvm_todo_di.Days.Repository.Repo;

import androidx.lifecycle.LiveData;

import com.example.mvvm_todo_di.Days.Repository.Source.DayLocal;
import com.example.mvvm_todo_di.Days.Repository.Source.DayLocalSource;
import com.example.mvvm_todo_di.Days.Repository.Source.DayRemote;
import com.example.mvvm_todo_di.Days.Repository.Source.DayRemoteSource;
import com.example.mvvm_todo_di.Model.Days;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class DayRepositoryImpl implements DayRepository{
    private DayRemote dayRemote;
    private DayLocal dayLocal;
    @Inject
    public DayRepositoryImpl(DayRemote dayRemote, DayLocal dayLocal) {
        this.dayRemote = dayRemote;
        this.dayLocal = dayLocal;
    }

    @Override
    public Single<List<Days>> requestToDays() {
        return dayRemote.requestToDays();
    }

    @Override
    public void saveDays(List<Days> days) {
        dayLocal.saveDays(days);
    }

    @Override
    public LiveData<List<Days>> getDays() {
        return dayLocal.getDays();
    }

    @Override
    public Single<Days> addDays(String dayName, String date) {
        return dayRemote.addDays(dayName,date);
    }

    @Override
    public void dayAdded(Days days) {
        dayLocal.addDays(days);
    }

    @Override
    public Single<Boolean> deleteDays(Days days) {
        return dayRemote.deleteDays(days);
    }

    @Override
    public void deletedDays(Days days) {
        dayLocal.deleteDays(days);
    }
}
