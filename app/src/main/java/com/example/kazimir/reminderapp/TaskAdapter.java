package com.example.kazimir.reminderapp;

import android.content.Context;
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
    private final int resource;
    private List<Task> tasks;

    private LayoutInflater inflater;

    public TaskAdapter(Context context, int resource, List<Task> tasks) {
        super(context, resource, tasks);
        this.resource = resource;
        this.tasks = tasks;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View templateView = inflater.inflate(resource, null);
        TextView taskTextView = (TextView) templateView.findViewById(R.id.taskTextView);

        Task currentTask = tasks.get(position);
        taskTextView.setText(String.valueOf(position) + ") " + currentTask.getTask().toString());

        return templateView;
    }
}
