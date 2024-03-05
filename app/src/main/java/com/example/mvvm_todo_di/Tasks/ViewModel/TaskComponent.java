package com.example.mvvm_todo_di.Tasks.ViewModel;

import android.content.Context;

import com.example.mvvm_todo_di.Base.BaseSingleton;
import com.example.mvvm_todo_di.Model.Days;
import com.example.mvvm_todo_di.Tasks.Fragment.TasksFragment;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;

@BaseSingleton
@Component(modules = {TaskViewModelModule.class,TaskRepositoryModule.class  })
public interface TaskComponent {
    void injectFields(TasksFragment tasksFragment);
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder setDays(@Named("days5")Days days);
        @BindsInstance
        Builder setContext(Context context);

        TaskComponent build();
    }

}
