package com.example.mvvm_todo_di.Model.Remote;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceFactory {
    private static ApiService apiService;

    public static ApiService getApiService() {
        if (apiService==null){
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl("https://amir.vipmive.com/api/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService=retrofit.create(ApiService.class);
        }
        return apiService;
    }
}
