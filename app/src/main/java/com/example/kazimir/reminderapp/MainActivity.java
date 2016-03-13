package com.example.kazimir.reminderapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kazimir.reminderapp.dbl.TasksDatabase;
import com.example.kazimir.reminderapp.model.Task;

import java.util.List;

import static android.graphics.Color.GREEN;

public class MainActivity extends AppCompatActivity {
    public static final String SELECTED_TASK_KEY = "selectedTaskKey";
    public static final int REQUEST_CODE = 1;

    public TasksDatabase tasksDatabase;
    private List<Task> tasks;
    private ListView tasksListView;

    public int listItemPosition;
    View selectedItem;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tasksListView = (ListView) findViewById(R.id.tasksListView);

        tasksDatabase = new TasksDatabase();
        tasks = tasksDatabase.getCats();

        taskAdapter = new TaskAdapter(this, R.layout.task_template, tasks);

        AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listItemPosition = position;
                selectedItem = view;
                Intent intent = new Intent(MainActivity.this, SelectedTaskActivity.class);
                intent.putExtra(SELECTED_TASK_KEY, tasks.get(listItemPosition).getTask());
                startActivityForResult(intent, REQUEST_CODE);
            }
        };

        tasksListView.setOnItemClickListener(onItemClickListener);
        tasksListView.setAdapter(taskAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == SelectedTaskActivity.TASK_DONE_RESULT) {
            if (requestCode == REQUEST_CODE) {
                Toast.makeText(this, "Attempt to done item", Toast.LENGTH_SHORT).show();
                taskAdapter.get
            }
        }
        if (resultCode == SelectedTaskActivity.REMOVE_TASK_RESULT) {
            if (requestCode == REQUEST_CODE) {
                Toast.makeText(this, "Attempt to remove item", Toast.LENGTH_SHORT).show();
            }
        }
        if (resultCode == SelectedTaskActivity.TASK_CHANGED_RESULT) {
            if (requestCode == REQUEST_CODE) {
                Toast.makeText(this, "Attempt to change item", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
