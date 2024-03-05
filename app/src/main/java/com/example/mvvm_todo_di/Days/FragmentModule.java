package com.example.mvvm_todo_di.Days;

import com.example.mvvm_todo_di.Days.Fragment.DayFargment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface FragmentModule {
    @ContributesAndroidInjector
    DayFargment injectDayFragment();
}
