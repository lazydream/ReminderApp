package com.example.kazimir.reminderapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SelectedTaskActivity extends AppCompatActivity {
    public static final String RESPONSE_KEY = "backIntentValue";
    public static final int TASK_CHANGED_RESULT = 1;
    public static final int REMOVE_TASK_RESULT = 2;
    public static final int TASK_DONE_RESULT = 3;

    TextView selectedTaskTextView;
    EditText selectedActivityEditText;
    String selectedTask;
    Button removeButton;
    Button doneButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_task);

        selectedTaskTextView = (TextView) findViewById(R.id.selectedTaskTextView);
        selectedActivityEditText = (EditText) findViewById(R.id.selectedTaskEditText);
        removeButton = (Button) findViewById(R.id.removeButton);
        doneButton = (Button) findViewById(R.id.doneButton);

        selectedTask = getIntent().getExtras().getString(MainActivity.SELECTED_TASK_KEY);

        selectedActivityEditText.setText(getIntent().getExtras().getString(MainActivity.SELECTED_TASK_KEY));

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(REMOVE_TASK_RESULT);
                finish();
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(TASK_DONE_RESULT);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent backIntent = new Intent();
        if (!selectedActivityEditText.getText().toString().equals(selectedTask)) {
            backIntent.putExtra(RESPONSE_KEY, selectedActivityEditText.getText().toString());
            setResult(TASK_CHANGED_RESULT, backIntent);
        }
        super.onBackPressed();
    }
}
