package com.example.kazimir.reminderapp.model;

/**
 * Created by Kazimir on 11.03.2016.
 */
public class Task {

    private final int id;
    private final String task;
    private final String date;
    private boolean isDone;

    public Task(int id, String task, String date, boolean isDone) {
        this.id = id;
        this.task = task;
        this.date = date;
        this.isDone = isDone;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
}
