package com.example.mvvm_todo_di.Days.ViewModels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvm_todo_di.Base.BaseViewModelFactory;
import com.example.mvvm_todo_di.Base.BaseViewModelKey;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AddDayViewModelModule {
    @Binds
    abstract ViewModelProvider.Factory buildAddDayViewModelFactory(BaseViewModelFactory factory );
    @Binds
    @IntoMap
    @BaseViewModelKey(buildKey = AddDayViewModel.class)
    abstract ViewModel buildAddDayViewModel(AddDayViewModel dayViewModel);
}
