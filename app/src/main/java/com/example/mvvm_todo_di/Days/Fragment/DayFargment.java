package com.example.mvvm_todo_di.Days.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_todo_di.Base.BaseViewModelFactory;
import com.example.mvvm_todo_di.Days.Adapter.AdapterComponent;
import com.example.mvvm_todo_di.Days.Adapter.DaggerAdapterComponent;
import com.example.mvvm_todo_di.Days.Adapter.DayAdapter;

import com.example.mvvm_todo_di.Days.ViewModels.DayViewModel;
import com.example.mvvm_todo_di.Model.Days;
import com.example.mvvm_todo_di.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class DayFargment extends DaggerFragment implements DayAdapter.DayAdapterEvent {
    @Inject
    BaseViewModelFactory factory;

    private DayViewModel dayViewModel;
    private LinearLayout emptyState;
    private RecyclerView rvDays;
    private FloatingActionButton addDayBtn;
    private ImageView exitButton;
    private TextView tvAddDayBtnEmptyState;
    private DayAdapter dayAdapter;
    private AdapterComponent component;
    private LinearLayoutManager linearLayoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.day_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dayViewModel=new ViewModelProvider(this,factory).get(DayViewModel.class);
        //find Views
        emptyState=view.findViewById(R.id.emptyStateDay);
        exitButton=view.findViewById(R.id.quitIc);
        tvAddDayBtnEmptyState=view.findViewById(R.id.tvAddDay);
        rvDays=view.findViewById(R.id.rvDayList);
        tvAddDayBtnEmptyState=view.findViewById(R.id.tvAddDay);
        addDayBtn=view.findViewById(R.id.dayAddDayBtn);



        //
        dayViewModel.getDayList().observe(getViewLifecycleOwner(), new Observer<List<Days>>() {
            @Override
            public void onChanged(List<Days> days) {
                component= DaggerAdapterComponent.factory().buildAdapterComponent(DayFargment.this,days,getContext());
                dayAdapter=component.buildDayAdapter();
                linearLayoutManager=component.buildLayout();
//                emptyState.setVisibility(View.GONE);
//                rvDays.setVisibility(View.VISIBLE);
                rvDays.setAdapter(dayAdapter);
                rvDays.setLayoutManager(linearLayoutManager);
            }
        });
        tvAddDayBtnEmptyState.setOnClickListener(v->{
            Navigation.findNavController(getView()).navigate(R.id.action_dayFargment_to_addDayFragment);

        });
        addDayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(getView()).navigate(R.id.action_dayFargment_to_addDayFragment);
            }
        });
        exitButton.setOnClickListener(v->{
            System.exit(0);
        });

        dayViewModel.getIsShow().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.i("TAG", "onChanged: "+aBoolean);
                emptyState.setVisibility(aBoolean?View.VISIBLE:View.GONE);
                rvDays.setVisibility(aBoolean?View.GONE:View.VISIBLE);
            }
        });
    }
    @Override
    public void onDeleteClicked(Days days) {
        dayViewModel.deleteDays(days);
    }

    @Override
    public void onItemClicked(Days days) {
        Navigation.findNavController(getView()).navigate(DayFargmentDirections.actionDayFargmentToTasksFragment().setDays(days));
    }
}
