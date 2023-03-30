package edu.uga.cs.geoquizzer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CountriesData {
    private SQLiteDatabase database;
    private SQLiteOpenHelper databaseHelper;

    public CountriesData(Context context) {
        this.databaseHelper = DatabaseHelper.getInstance(context);
    }

    public void open() {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }

    public boolean isOpen() {return database.isOpen();}
}
