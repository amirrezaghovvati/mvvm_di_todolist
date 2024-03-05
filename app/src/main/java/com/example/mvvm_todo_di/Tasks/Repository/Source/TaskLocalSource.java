package com.example.mvvm_todo_di.Tasks.Repository.Source;

import androidx.lifecycle.LiveData;

import com.example.mvvm_todo_di.Model.Local.AppDao;
import com.example.mvvm_todo_di.Model.Local.AppDataBase;
import com.example.mvvm_todo_di.Model.Tasks;

import java.util.List;

import javax.inject.Inject;

public class TaskLocalSource implements TaskLocal{
    private AppDao appDao;
    @Inject
    public TaskLocalSource(AppDao appDao) {
        this.appDao = appDao;
    }



    @Override
    public void taskAdded(Tasks tasks) {
        appDao.addTasks(tasks);
    }

    @Override
    public void taskDeleted(Tasks tasks) {
        appDao.deleteTasks( tasks);
    }

    @Override
    public LiveData<List<Tasks>> getAllTasks(int dayId) {
        return appDao.getTasks(dayId);
    }

    @Override
    public void updateTasks(List<Tasks> tasks) {
            appDao.updateTasks(tasks);
    }

    @Override
    public void updateTaskss(Tasks tasks) {
        appDao.updateTasks(tasks);
    }
}
