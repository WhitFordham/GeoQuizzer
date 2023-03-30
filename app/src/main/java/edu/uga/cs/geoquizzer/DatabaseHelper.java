package edu.uga.cs.geoquizzer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "quizInformation.db";
    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper databaseInstance;
    public static final String TABLE_COUNTRIES = "countries";
    public static final String COUNTRIES_COLUMN_ID = "_id";
    public static final String COUNTRIES_COLUMN_NAME = "name";
    public static final String COUNTRIES_COLUMN_CONTINENT = "continent";

    public static final String TABLE_QUIZZES = "quizzes";
    public static final String QUIZ_COLUMN_ID = "_id";
    public static final String QUIZ_COLUMN_DATE = "date";
    public static final String QUIZ_COLUMN_RESULTS = "result";

    public static final String CREATE_COUNTRIES = "create table " + TABLE_COUNTRIES + " ("
            + COUNTRIES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COUNTRIES_COLUMN_NAME + "TEXT"
            + COUNTRIES_COLUMN_CONTINENT + "TEXT" + ")";

    public static final String CREATE_QUIZZES = "create table " + TABLE_QUIZZES + " ("
            + QUIZ_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + QUIZ_COLUMN_DATE + " TEXT, "
            + QUIZ_COLUMN_RESULTS + " TEXT" + ")";

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
        database.execSQL(CREATE_COUNTRIES);
        database.execSQL(CREATE_QUIZZES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("drop table if exists " + TABLE_COUNTRIES);
        database.execSQL("drop table if exists " + TABLE_QUIZZES);
        onCreate(database);
    }

    private void copyDatabase() {

    }

}
