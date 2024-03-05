package com.example.mvvm_todo_di.Model;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
@Entity(tableName = "_tasks",foreignKeys = {@ForeignKey(childColumns = "dayId",
parentColumns = "dayId",
entity = Days.class,
onDelete = CASCADE,
onUpdate = CASCADE)})
public class Tasks {
    @PrimaryKey(autoGenerate = true)
    private int taskID;
    @ColumnInfo(name = "dayId")
    private int dayId;
    private String title;
    private boolean isDone;

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getDayId() {
        return dayId;
    }

    public void setDayId(int dayId) {
        this.dayId = dayId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
