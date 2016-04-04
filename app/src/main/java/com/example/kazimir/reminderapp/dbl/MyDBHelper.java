package com.example.kazimir.reminderapp.dbl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kazimir on 04.04.2016.
 */
public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Reminders (\n" +
                "'RID' integer not null primary key autoincrement,\n" +
                "'NoteText' Text,\n" +
                "'Date' DateTime,\n" +
                "'IsDone' boolean default 0\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
