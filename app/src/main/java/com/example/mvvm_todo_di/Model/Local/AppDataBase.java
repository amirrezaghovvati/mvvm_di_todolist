package com.example.mvvm_todo_di.Model.Local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvvm_todo_di.Model.Days;
import com.example.mvvm_todo_di.Model.Tasks;

@Database(version = 1,entities = {Days.class, Tasks.class},exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase appDataBase;

    public static AppDataBase getAppDataBase(Context context) {
        if (appDataBase==null){
            appDataBase= Room.databaseBuilder(context,AppDataBase.class,"dbOne")
                    .build();
        }
        return appDataBase;
    }
    public abstract AppDao getAppDao();
}
