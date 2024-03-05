package com.example.mvvm_todo_di.Tasks.ViewModel;

import android.content.Context;

import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_todo_di.Base.BaseSingleton;
import com.example.mvvm_todo_di.Model.Days;

import org.jetbrains.annotations.Contract;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;
@BaseSingleton
@Component(modules = {TaskRepositoryModule.class,AddTaskViewModelModule.class})
public interface AddTaskViewModelComponent {
    ViewModelProvider.Factory provideFactory2();

    @Component.Builder
    interface Builders{
        @BindsInstance
        Builders setContext(Context context);
        @BindsInstance
        Builders setDays1(@Named("days5")Days days);

        AddTaskViewModelComponent buildComponent1();
    }

}
