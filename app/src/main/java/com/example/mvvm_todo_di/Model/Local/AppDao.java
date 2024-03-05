package com.example.mvvm_todo_di.Model.Local;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvm_todo_di.Model.Days;
import com.example.mvvm_todo_di.Model.Tasks;

import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface AppDao {
    @Insert(onConflict = REPLACE)
    void updateDays(List<Days> daysList);
    @Query("Select * From _days ")
    LiveData<List<Days>> getDays();
    @Insert
    long addDays(Days days);
    @Delete
    int deleteDays(Days days);
    @Update
    int updateDays(Days days);


    @Insert(onConflict = REPLACE)
    void updateTasks(List<Tasks> tasks);
    @Query("SELECT * FROM _tasks where dayId like :id")
    LiveData<List<Tasks>> getTasks(int id);
    @Insert
    long addTasks(Tasks tasks);
    @Delete
    int deleteTasks(Tasks tasks);
    @Update
    int updateTasks(Tasks tasks);

}
