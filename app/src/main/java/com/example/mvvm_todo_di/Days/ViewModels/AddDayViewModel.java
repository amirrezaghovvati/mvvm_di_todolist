package com.example.mvvm_todo_di.Days.ViewModels;

import androidx.lifecycle.ViewModel;

import com.example.mvvm_todo_di.Days.Repository.Repo.DayRepository;
import com.example.mvvm_todo_di.Model.Days;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddDayViewModel extends ViewModel {
    private DayRepository dayRepository;
    @Inject
    public AddDayViewModel(DayRepository dayRepository){
        this.dayRepository=dayRepository;
    }
    public void addDays(String dayName,String date){
        dayRepository.addDays(dayName,date).subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Days>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(Days days) {
                        dayRepository.dayAdded(days);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
