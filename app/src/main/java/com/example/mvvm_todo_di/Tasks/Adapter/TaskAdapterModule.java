package com.example.mvvm_todo_di.Tasks.Adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvm_todo_di.Model.Tasks;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class TaskAdapterModule {
    @Provides
    LinearLayoutManager provideLinearLayout(@Named("cont") Context context){
        return new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false   );
    }
    @Provides
    List<Tasks> provideTaskso(@Named("taskso") List<Tasks> tasks){
        return tasks;
    }
    @Provides
    TaskAdater.TaskEventListener provideEventListener(@Named("evento") TaskAdater.TaskEventListener eventListener){return eventListener;}
}
