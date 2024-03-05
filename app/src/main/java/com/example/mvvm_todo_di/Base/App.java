package com.example.mvvm_todo_di.Base;

import com.example.mvvm_todo_di.Days.DaggerDayComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector(){
        return DaggerDayComponent.builder().setApplication(this).setContext(getApplicationContext()).build();
    }
    }
