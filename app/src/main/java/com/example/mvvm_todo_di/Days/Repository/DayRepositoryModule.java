package com.example.mvvm_todo_di.Days.Repository;

import android.content.Context;

import com.example.mvvm_todo_di.Base.BaseSingleton;
import com.example.mvvm_todo_di.Days.Repository.Repo.DayRepository;
import com.example.mvvm_todo_di.Days.Repository.Repo.DayRepositoryImpl;
import com.example.mvvm_todo_di.Days.Repository.Source.DayLocal;
import com.example.mvvm_todo_di.Days.Repository.Source.DayLocalSource;
import com.example.mvvm_todo_di.Days.Repository.Source.DayRemote;
import com.example.mvvm_todo_di.Days.Repository.Source.DayRemoteSource;
import com.example.mvvm_todo_di.Model.Days;
import com.example.mvvm_todo_di.Model.Local.AppDao;
import com.example.mvvm_todo_di.Model.Local.AppDataBase;
import com.example.mvvm_todo_di.Model.Remote.ApiService;
import com.example.mvvm_todo_di.Model.Remote.ApiServiceFactory;
import com.google.gson.JsonObject;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class DayRepositoryModule {
    @BaseSingleton
   @Provides
    ApiService provideApiService(){
       return ApiServiceFactory.getApiService();
   }

   @BaseSingleton
    @Provides
    AppDao provideAppdao(Context context){
        return AppDataBase.getAppDataBase(context).getAppDao();
   }

   @Provides
    DayLocal provideDayLocal(DayLocalSource source){
        return source;
   }
   @BaseSingleton
   @Provides
   @Named("JSON")
    JsonObject provideJsos(){
        return new JsonObject();
   }

   @Provides
    DayRemote provideDayRemote(DayRemoteSource source){
        return source;
   }
   @Provides
    DayRepository provideDayRepository(DayRepositoryImpl impl){
        return impl;
   }

}
