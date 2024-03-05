package com.example.mvvm_todo_di.Base;

import androidx.lifecycle.ViewModel;

import dagger.MapKey;

@MapKey
public @interface BaseViewModelKey {
    Class<? extends ViewModel> buildKey();
}
