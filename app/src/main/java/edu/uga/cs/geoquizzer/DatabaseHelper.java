package edu.uga.cs.geoquizzer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "quizInformation.db";
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_LOCATION = "/data/data/edu.uga.cs.geoquizzer/databases";
    private static DatabaseHelper databaseInstance;
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = new DatabaseHelper(context.getApplicationContext());
        }

        return databaseInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase database) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }

    private void copyDatabase() {

    }

}
