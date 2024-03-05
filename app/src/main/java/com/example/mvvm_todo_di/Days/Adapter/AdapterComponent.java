package com.example.mvvm_todo_di.Days.Adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvm_todo_di.Model.Days;

import java.util.List;

import javax.inject.Named;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;

@Component(modules = DayAdapterModule.class)
public interface AdapterComponent {
    DayAdapter buildDayAdapter();
    LinearLayoutManager buildLayout();
    @Component.Factory
    interface Factory{
        AdapterComponent buildAdapterComponent(@BindsInstance @Named("dayEVENT")DayAdapter.DayAdapterEvent event,
                                               @BindsInstance @Named("days")List<Days> days,
                                               @BindsInstance @Named("cnt")Context context);
    }

}
