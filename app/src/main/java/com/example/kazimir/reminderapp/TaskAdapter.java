package com.example.kazimir.reminderapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kazimir.reminderapp.model.Task;

import java.util.List;

/**
 * Created by Kazimir on 11.03.2016.
 */
public class TaskAdapter extends ArrayAdapter<Task> {
    Activity context;
    private final int resource;
    private List<Task> tasks;

    public TaskAdapter(Context context, int resource, List<Task> tasks) {
        super(context, resource, tasks);
        this.context = (Activity) context;
        this.resource = resource;
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View templateView = inflater.inflate(resource, null);
        TextView taskTextView = (TextView) templateView.findViewById(R.id.taskTextView);
        TextView taskDate = (TextView) templateView.findViewById(R.id.tv_date);

        taskTextView.setText(String.valueOf(position + 1) + ") " + tasks.get(position).getTask().toString());
        taskDate.setText(tasks.get(position).getDate());

        if (tasks.get(position).isDone() == true) {
            //taskTextView.setBackgroundColor(Color.GREEN);
            templateView.setBackgroundColor(Color.GREEN);
        }

        return templateView;
    }
}
