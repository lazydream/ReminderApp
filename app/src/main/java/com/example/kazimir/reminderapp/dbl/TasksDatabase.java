package com.example.kazimir.reminderapp.dbl;

import android.content.Context;

import com.example.kazimir.reminderapp.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kazimir on 11.03.2016.
 */
public class TasksDatabase {
    List<Task> tasks = new ArrayList<>();
    public List<Task> getTasks() {

        tasks.add(new Task("Сделать платформу", false));
        tasks.add(new Task("Выгулять пса", false));
        tasks.add(new Task("Поиграть в футбол", true));
        tasks.add(new Task("Написать приложение-бомбу", false));
        tasks.add(new Task("Поднять ранг в хартстоуне", false));

        return tasks;
    }

    public void removeTask(int position) {
        tasks.remove(position);
    }
}
