package com.example.mvvm_todo_di.Days.ViewModels;

import android.content.Context;

import com.example.mvvm_todo_di.Base.BaseSingleton;
import com.example.mvvm_todo_di.Days.Fragment.AddDayFragment;
import com.example.mvvm_todo_di.Days.Repository.DayRepositoryModule;

import dagger.BindsInstance;
import dagger.Component;
@BaseSingleton
@Component(modules = {DayRepositoryModule.class, AddDayViewModelModule.class})
public interface AddDayViewComponent {
    void injectFields(AddDayFragment addDayFragment);
    @Component.Factory
    interface Factory{
        AddDayViewComponent buildComponent(@BindsInstance Context context);
    }
}
