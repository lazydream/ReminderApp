package com.example.kazimir.reminderapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kazimir.reminderapp.dbl.MyDBHelper;
import com.example.kazimir.reminderapp.dbl.TasksDatabase;
import com.example.kazimir.reminderapp.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String SELECTED_TASK_KEY = "selectedTaskKey";
    public static final int REQUEST_CODE = 1;

    private MyDBHelper db;
    private SQLiteDatabase connection;
    private String noteText;
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
        initDatabase();
        initButton();
    }

    @Override
    protected  void onResume() {
        super.onResume();
        readNotes();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == SelectedTaskActivity.TASK_DONE_RESULT) {
            if (requestCode == REQUEST_CODE) {
                Toast.makeText(this, "Attempt to done item", Toast.LENGTH_SHORT).show();

                try {

                    connection.execSQL("update Reminders\n" +
                            "set IsDone=1\n" +
                            "where RID = " + tasks.get(listItemPosition).getId());
                    readNotes();

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (resultCode == SelectedTaskActivity.REMOVE_TASK_RESULT) {
            if (requestCode == REQUEST_CODE) {
                Toast.makeText(this, "Attempt to remove item", Toast.LENGTH_SHORT).show();

                try {

                    connection.execSQL("delete from Reminders\n" +
                            "where RID = " + tasks.get(listItemPosition).getId());
                    readNotes();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (resultCode == SelectedTaskActivity.TASK_CHANGED_RESULT) {
            if (requestCode == REQUEST_CODE) {
                Toast.makeText(this, "Attempt to change item", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initButton() {
        try{
            (findViewById(R.id.b_send)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    noteText = ((EditText) findViewById(R.id.et_note_text)).getText().toString();
                    connection.execSQL("INSERT INTO Reminders(NoteText, Date) values('" + noteText + "', datetime())");
                    readNotes();
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initDatabase() {
        db = new MyDBHelper(this, "TasksDb", null, 1);
        connection = db.getWritableDatabase();
    }

    void readNotes() {
        tasks = new ArrayList<>();
        Cursor cursor = connection.rawQuery("select* from Reminders", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String taskText = cursor.getString(1);
            String taskDate = cursor.getString(2);
            int isTaskDone = cursor.getInt(3);
            Boolean isDone = false;
            if (isTaskDone != 0) {
                isDone = true;
            }
            tasks.add(new Task(id, taskText, taskDate, isDone));
        }
        cursor.close();
        ListView listView = (ListView) findViewById(R.id.tasksListView);

        TaskAdapter taskAdapter = new TaskAdapter(this, R.layout.task_template, tasks);
        listView.setOnItemClickListener(onItemClickListener);
        listView.setAdapter(taskAdapter);
    }

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

}
