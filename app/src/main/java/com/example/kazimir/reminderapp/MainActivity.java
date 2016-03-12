package com.example.kazimir.reminderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kazimir.reminderapp.dbl.TasksDatabase;
import com.example.kazimir.reminderapp.model.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String SELECTED_TASK_KEY = "selectedTaskKey";
    private final int REQUEST_CODE = 1;

    private TasksDatabase tasksDatabase;
    private List<Task> tasks;
    private ListView tasksListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasksListView = (ListView) findViewById(R.id.catsListView);

        tasksDatabase = new TasksDatabase();
        tasks = tasksDatabase.getCats();

        TaskAdapter taskAdapter = new TaskAdapter(this, R.layout.task_template, tasks);

        tasksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SelectedTaskActivity.class);
                intent.putExtra(SELECTED_TASK_KEY, tasks.get(position).getTask().toString());
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        tasksListView.setAdapter(taskAdapter);
    }
}
