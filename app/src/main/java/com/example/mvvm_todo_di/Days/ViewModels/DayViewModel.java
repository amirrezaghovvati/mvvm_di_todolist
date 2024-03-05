package com.example.mvvm_todo_di.Days.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm_todo_di.Days.Repository.Repo.DayRepository;
import com.example.mvvm_todo_di.Model.Days;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

public class DayViewModel extends ViewModel {
    private DayRepository dayRepository;
    private LiveData<List<Days>> dayList;
    private MutableLiveData<String> errorGetDays;
    private MutableLiveData<String> errorDelete;
    private CompositeDisposable compositeDisposable=new CompositeDisposable();

    private MutableLiveData<Boolean> isShow=new MutableLiveData<>();
    @Inject
    public DayViewModel(DayRepository dayRepository) {
        this.dayRepository=dayRepository;
        dayRepository.requestToDays().subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<List<Days>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Days> days) {
                        if (days.size()>0){
                                isShow.postValue(false);
                        }else {
                                isShow.postValue(true);
                        }
                        dayRepository.saveDays(days);
                    }

                    @Override
                    public void onError(Throwable e) {
                            errorGetDays.postValue(e.toString());
                    }
                });
        dayList=dayRepository.getDays();
    }

    public MutableLiveData<Boolean> getIsShow() {
        return isShow;
    }

    public LiveData<List<Days>> getDayList() {
        return dayList;
    }
    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public void deleteDays(Days days){
        dayRepository.deleteDays(days).subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        if (aBoolean){
                            dayRepository.deletedDays(days);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                            errorDelete.postValue(e.toString());
                    }
                });

    }
    public MutableLiveData<String> getErrorDelete() {
        return errorDelete;
    }



    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
