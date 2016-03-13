package com.example.kazimir.reminderapp.model;

/**
 * Created by Kazimir on 11.03.2016.
 */
public class Task {
    private final String task;

    private boolean isDone;

    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
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
