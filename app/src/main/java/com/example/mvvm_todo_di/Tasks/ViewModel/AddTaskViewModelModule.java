package com.example.mvvm_todo_di.Tasks.ViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_todo_di.Base.BaseViewModelFactory;
import com.example.mvvm_todo_di.Base.BaseViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AddTaskViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory buildFactory(BaseViewModelFactory baseViewModelFactory);
    @Binds
    @IntoMap
    @BaseViewModelKey(buildKey = AddTaskViewModel.class)
    abstract ViewModel provideAddTaskViewModel(AddTaskViewModel taskViewModel);
}
