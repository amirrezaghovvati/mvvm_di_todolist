package com.example.mvvm_todo_di.Tasks.Adapter;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mvvm_todo_di.Model.Tasks;

import java.util.List;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {TaskAdapterModule.class})
public interface TaskAdaterComponent {
    LinearLayoutManager buildLayout();
    TaskAdater buildTaskAdapter();
    @Component.Builder
    interface TaskAdapterBuilder{
        @BindsInstance
        TaskAdapterBuilder setContext(@Named("cont")Context context);
        @BindsInstance
        TaskAdapterBuilder setList(@Named("taskso")List<Tasks> tasks);
        @BindsInstance
        TaskAdapterBuilder setEvent(@Named("evento")TaskAdater.TaskEventListener event);


        TaskAdaterComponent buildAdapterComponent();
    }
}
