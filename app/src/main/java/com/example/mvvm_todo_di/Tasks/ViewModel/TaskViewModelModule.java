package com.example.mvvm_todo_di.Tasks.ViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_todo_di.Base.BaseViewModelFactory;
import com.example.mvvm_todo_di.Base.BaseViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class TaskViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory provideBaseViewModelFactory(BaseViewModelFactory factory);
    @Binds
    @IntoMap
    @BaseViewModelKey(buildKey = TaskViewModel.class)
    abstract ViewModel provideTasksViewModel(TaskViewModel taskViewModel);
}
