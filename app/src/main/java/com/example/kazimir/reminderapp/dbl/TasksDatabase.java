package com.example.kazimir.reminderapp.dbl;

import com.example.kazimir.reminderapp.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kazimir on 11.03.2016.
 */
public class TasksDatabase {
    public List<Task> getCats() {
        List<Task> tasks = new ArrayList<>();

        tasks.add(new Task("Сделать платформу"));
        tasks.add(new Task("Выгулять пса"));
        tasks.add(new Task("Поиграть в футбол"));
        tasks.add(new Task("Написать приложение-бомбу"));
        tasks.add(new Task("Поднять ранг в хартстоуне"));

        return tasks;
    }
}
