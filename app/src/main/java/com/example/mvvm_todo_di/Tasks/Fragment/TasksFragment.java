package com.example.mvvm_todo_di.Tasks.Fragment;

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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mvvm_todo_di.Base.BaseViewModelFactory;
import com.example.mvvm_todo_di.Model.Days;
import com.example.mvvm_todo_di.Model.Tasks;
import com.example.mvvm_todo_di.R;
import com.example.mvvm_todo_di.Tasks.Adapter.DaggerTaskAdaterComponent;
import com.example.mvvm_todo_di.Tasks.Adapter.TaskAdater;
import com.example.mvvm_todo_di.Tasks.Adapter.TaskAdaterComponent;

import com.example.mvvm_todo_di.Tasks.ViewModel.AddTaskViewModel;
import com.example.mvvm_todo_di.Tasks.ViewModel.AddTaskViewModelComponent;
import com.example.mvvm_todo_di.Tasks.ViewModel.DaggerAddTaskViewModelComponent;
import com.example.mvvm_todo_di.Tasks.ViewModel.DaggerTaskComponent;
import com.example.mvvm_todo_di.Tasks.ViewModel.TaskComponent;
import com.example.mvvm_todo_di.Tasks.ViewModel.TaskViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import javax.inject.Inject;

public class TasksFragment extends Fragment implements TaskAdater.TaskEventListener, AddTaskDialog.AddTaskCallBack {
    private Days days;
    @Inject
    BaseViewModelFactory factory;
    private RecyclerView rvTasks;
    private TaskAdater taskAdapter;
    private LinearLayoutManager linearLayoutManager;
    private TaskComponent taskComponent;
    private TaskViewModel taskViewModel;
    private TaskAdaterComponent adaterComponent;
    private LinearLayout emptyState;
    private FloatingActionButton fabAddTasks;
    private TextView tvEmptyStateAddTasks;
    private ImageView icBack;
    private AddTaskViewModel addTaskViewModel;
    private AddTaskDialog dialog;
    private AddTaskComponent dialogComponent;
    private AddTaskViewModelComponent addTaskViewModelComponent;
    private ViewModelProvider.Factory factory2;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        days= TasksFragmentArgs.fromBundle(getArguments()).getDays();
        //finding view
        rvTasks=view.findViewById(R.id.rvDetail);
        emptyState=view.findViewById(R.id.emptyStateDetail);
        fabAddTasks=view.findViewById(R.id.addTaskDetail);
        tvEmptyStateAddTasks=view.findViewById(R.id.tvAddDetailTask);
        icBack=view.findViewById(R.id.icBackDetail);
        //===========================
        icBack.setOnClickListener(v->{
            getActivity().onBackPressed();
        });
        //
        addTaskViewModelComponent= DaggerAddTaskViewModelComponent.builder().setContext(getContext()).setDays1(days).buildComponent1();
        factory2=addTaskViewModelComponent.provideFactory2();
        addTaskViewModel=new ViewModelProvider(this,factory2).get(AddTaskViewModel.class);
        //
        dialogComponent= DaggerAddTaskComponent.builder().setCallBack(this).buildComponent1();
        dialog=dialogComponent.buildDialog();
        fabAddTasks.setOnClickListener(v->{dialog.show(getActivity().getSupportFragmentManager(),null);});
        tvEmptyStateAddTasks.setOnClickListener(v->{dialog.show(getActivity().getSupportFragmentManager(),null);});
        //
        taskComponent= DaggerTaskComponent.builder().setContext(getContext()).setDays(days).build();
        taskComponent.injectFields(this);
        taskViewModel=new ViewModelProvider(this,factory).get(TaskViewModel.class);
        taskViewModel.getTaskList().observe(getViewLifecycleOwner(), new Observer<List<Tasks>>() {
            @Override
            public void onChanged(List<Tasks> tasks) {
                adaterComponent= DaggerTaskAdaterComponent.builder().setContext(getContext()).setEvent(TasksFragment.this).setList(tasks).buildAdapterComponent();
                taskAdapter=adaterComponent.buildTaskAdapter();
                linearLayoutManager=adaterComponent.buildLayout();

                rvTasks.setLayoutManager(linearLayoutManager);
                rvTasks.setAdapter(taskAdapter);
            }
        });
        //visibilities
        taskViewModel.getIsShow().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                emptyState.setVisibility(aBoolean?View.VISIBLE:View.GONE);
                rvTasks.setVisibility(aBoolean?View.GONE:View.VISIBLE);
            }
        });
    }

    @Override
    public void onDeleteClicked(Tasks tasks) {
            taskViewModel.deleteTasks(tasks);
    }

    @Override
    public void onDoneClicked(Tasks tasks) {
            taskViewModel.updateTasks(tasks);
    }

    @Override
    public void onConfirmed(String title) {
        Log.i("TAG", "onConfirmed: "+title);
        addTaskViewModel.addDays(title);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        days=null;
    }
}
