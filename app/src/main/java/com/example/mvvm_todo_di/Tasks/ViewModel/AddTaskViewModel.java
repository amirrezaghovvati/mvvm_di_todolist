package com.example.mvvm_todo_di.Tasks.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_todo_di.Model.Days;
import com.example.mvvm_todo_di.Model.Tasks;
import com.example.mvvm_todo_di.Tasks.Repository.repo.TaskRepository;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddTaskViewModel extends ViewModel {
    private TaskRepository repository;
    private MutableLiveData<String> error;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    private Days days;
    @Inject
    public AddTaskViewModel(TaskRepository taskRepository,Days days){
        this.repository=taskRepository;
        this.days=days;

    }
    public void addDays(String title){
        repository.addTasks(days.getDayId(),title).subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Tasks>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Tasks tasks) {
                        tasks.setDayId(days.getDayId());
                        repository.taskAdded(tasks);
                    }

                    @Override
                    public void onError(Throwable e) {
                        error.setValue(e.toString());
                    }
                });
    }

    public MutableLiveData<String> getError() {
        return error;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
