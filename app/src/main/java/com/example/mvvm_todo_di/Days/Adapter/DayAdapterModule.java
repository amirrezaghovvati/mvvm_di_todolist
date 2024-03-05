package com.example.mvvm_todo_di.Days.Adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvm_todo_di.Model.Days;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class DayAdapterModule {
    @Provides
    List<Days> setDays(@Named("days") List<Days> days) {
        return days;
    }
    @Provides
    DayAdapter.DayAdapterEvent provideEventListener(@Named("dayEVENT") DayAdapter.DayAdapterEvent event){
        return event;
    }
    @Provides
    LinearLayoutManager provideLinearLayout(@Named("cnt") Context context){
        return new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
    }
}
