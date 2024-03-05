package com.example.mvvm_todo_di.Tasks.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.mvvm_todo_di.R;
import com.google.android.material.button.MaterialButton;

import javax.inject.Inject;
import javax.inject.Named;

public class AddTaskDialog extends DialogFragment {
    private AddTaskCallBack callBack;
    @Inject
    public AddTaskDialog(@Named("callBack") AddTaskCallBack callBack){
        this.callBack=callBack;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.add_simple_task_dialoge,null,false);
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setView(view);
        MaterialButton confirmButton=view.findViewById(R.id.taskTitleConfirm);
        MaterialButton cancelButton=view.findViewById(R.id.taskTitleCancel);
        EditText edtTitle=view.findViewById(R.id.edtTaskTitle);
        cancelButton.setOnClickListener(v->{dismiss();});
        confirmButton.setOnClickListener(v->{
            if (edtTitle.getText().length()>3) {
                callBack.onConfirmed(edtTitle.getText().toString());
                dismiss();
            }else {
                Toast.makeText(getContext(),"Complete the fields correctly",Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return builder.create();
    }
    public interface AddTaskCallBack{
        void onConfirmed(String title);
    }
}
