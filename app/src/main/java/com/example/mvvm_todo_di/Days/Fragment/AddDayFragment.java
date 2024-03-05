package com.example.mvvm_todo_di.Days.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.mvvm_todo_di.Base.BaseViewModelFactory;
import com.example.mvvm_todo_di.Days.ViewModels.AddDayViewComponent;
import com.example.mvvm_todo_di.Days.ViewModels.AddDayViewModel;
import com.example.mvvm_todo_di.Days.ViewModels.DaggerAddDayViewComponent;
import com.example.mvvm_todo_di.R;

import javax.inject.Inject;

public class AddDayFragment extends Fragment {
    @Inject
    BaseViewModelFactory baseViewModelFactory;
    private AddDayViewComponent component;
    private AddDayViewModel viewModel;
    private EditText edtYear;
    private EditText edtMonth;
    private EditText edtDay;
    private EditText edtDayName;
    private View saveBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_day,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtYear=view.findViewById(R.id.edtYear);
        edtDay=view.findViewById(R.id.edtDayAddDay);
        edtMonth=view.findViewById(R.id.edtMonth);
        edtDayName=view.findViewById(R.id.edtDayName);
        saveBtn=view.findViewById(R.id.daySaveDayBtn);
        //INITIAL
        component= DaggerAddDayViewComponent.factory().buildComponent(getContext());
        component.injectFields(this);
        viewModel=new ViewModelProvider(this,baseViewModelFactory).get(AddDayViewModel.class);


        //
        saveBtn.setOnClickListener(v->{


                StringBuilder stringBuilder= new StringBuilder(edtYear.getText().toString()+edtMonth.getText().toString()+edtDay.getText().toString());
                viewModel.addDays(edtDayName.getText().toString(),stringBuilder.toString());
                Navigation.findNavController(getView()).navigate(AddDayFragmentDirections.actionAddDayFragmentToDayFargment());

        });
    }
}
