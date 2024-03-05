package com.example.mvvm_todo_di.Tasks.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_todo_di.Model.Days;
import com.example.mvvm_todo_di.Model.Tasks;
import com.example.mvvm_todo_di.Tasks.Repository.repo.TaskRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TaskViewModel extends ViewModel {
    private TaskRepository repository;
    private MutableLiveData<String> errorList=new MutableLiveData<>();
    private MutableLiveData<Boolean> isShow=new MutableLiveData<>();
    private MutableLiveData<String> errorDelete=new MutableLiveData<>();
    private LiveData<List<Tasks>> taskList;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();
    private Days days;
    @Inject
    public TaskViewModel(TaskRepository repository, Days days){
        this.repository=repository;
        repository.requestToTasks(days.getDayId()).subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<Tasks>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Tasks> tasks) {
                        if (tasks.size()>0){
                            isShow.postValue(false);
                        }else{
                            isShow.postValue(true);
                        }
                        for (int i = 0; i <tasks.size() ; i++) {
                            tasks.get(i).setDayId(days.getDayId());
                        }
                        repository.saveTasks(tasks);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorList.setValue(e.toString());
                    }
                });
        taskList=repository.getAllTasks(days.getDayId());
    }
    public void deleteTasks(Tasks tasks ){
        repository.deleteTasks(tasks).subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        if (aBoolean)
                            repository.taskDeleted(tasks);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorDelete.setValue(e.toString());
                    }
                });
    }

    public MutableLiveData<String> getErrorDelete() {
        return errorDelete;
    }

    public LiveData<List<Tasks>> getTaskList() {
        return taskList;
    }

    public MutableLiveData<String> getErrorList() {
        return errorList;
    }
    public void updateTasks(Tasks tasks){
        repository.updateTasks(tasks);
    }

    public MutableLiveData<Boolean> getIsShow() {
        return isShow;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
