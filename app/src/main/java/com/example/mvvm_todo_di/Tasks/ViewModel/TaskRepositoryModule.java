package com.example.mvvm_todo_di.Tasks.ViewModel;

import android.content.Context;

import com.example.mvvm_todo_di.Base.BaseSingleton;
import com.example.mvvm_todo_di.Model.Days;
import com.example.mvvm_todo_di.Model.Local.AppDao;
import com.example.mvvm_todo_di.Model.Local.AppDataBase;
import com.example.mvvm_todo_di.Model.Remote.ApiService;
import com.example.mvvm_todo_di.Model.Remote.ApiServiceFactory;
import com.example.mvvm_todo_di.Tasks.Repository.Source.TaskLocal;
import com.example.mvvm_todo_di.Tasks.Repository.Source.TaskLocalSource;
import com.example.mvvm_todo_di.Tasks.Repository.Source.TaskRemote;
import com.example.mvvm_todo_di.Tasks.Repository.Source.TaskRemoteSource;
import com.example.mvvm_todo_di.Tasks.Repository.repo.TaskRepository;
import com.example.mvvm_todo_di.Tasks.Repository.repo.TaskRepositoryImpl;
import com.google.gson.JsonObject;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskRepositoryModule {
    @Provides
    TaskRepository provideTaskRepository(TaskRepositoryImpl impl){
        return impl;
    }
    @Provides
    Days provideDays(@Named("days5") Days days){return days;}

    @Provides
    TaskRemote provideRemote(TaskRemoteSource source){return source;}

    @Provides
    TaskLocal provideLocal(TaskLocalSource source){return source;}
    @Provides
    JsonObject provideJson(){
        return new JsonObject();
    }
    @Provides
    @BaseSingleton
    ApiService provideApiService(){
        return ApiServiceFactory.getApiService();
    }
    @Provides
    @BaseSingleton
    AppDao provideAppDao(Context context){
        return AppDataBase.getAppDataBase(context).getAppDao();
    }
}
