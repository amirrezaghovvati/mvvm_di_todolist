package com.example.mvvm_todo_di.Tasks.Fragment;

import javax.inject.Named;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;

@Component
public interface AddTaskComponent {
    AddTaskDialog buildDialog();
    @Component.Builder
    interface Builder{
        @BindsInstance Builder setCallBack(@Named("callBack")AddTaskDialog.AddTaskCallBack callBack);
        AddTaskComponent buildComponent1();

    }
}
