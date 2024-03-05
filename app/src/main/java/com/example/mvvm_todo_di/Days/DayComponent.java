package com.example.mvvm_todo_di.Days;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.example.mvvm_todo_di.Base.App;
import com.example.mvvm_todo_di.Base.BaseSingleton;
import com.example.mvvm_todo_di.Days.Repository.DayRepositoryModule;
import com.example.mvvm_todo_di.Days.ViewModels.DayViewModel;
import com.example.mvvm_todo_di.Days.ViewModels.DayViewModelModule;

import dagger.Binds;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
@BaseSingleton
@Component(modules = {AndroidSupportInjectionModule.class,FragmentModule.class, DayViewModelModule.class, DayRepositoryModule.class})
public interface DayComponent extends AndroidInjector<App> {
    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder setApplication(Application application);
        @BindsInstance
        Builder setContext(Context context);

        DayComponent build();
    }

}
