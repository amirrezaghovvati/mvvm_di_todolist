package com.example.mvvm_todo_di.Days.ViewModels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_todo_di.Base.BaseViewModelFactory;
import com.example.mvvm_todo_di.Base.BaseViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class DayViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory provideDayViewModelFactory(BaseViewModelFactory factory);
    @Binds
    @IntoMap
    @BaseViewModelKey(buildKey = DayViewModel.class)
    abstract ViewModel provideDayViewModel(DayViewModel dayViewModel);
}
