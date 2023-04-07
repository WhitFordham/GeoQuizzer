package edu.uga.cs.geoquizzer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CountriesData {
    private SQLiteDatabase database;
    private SQLiteOpenHelper databaseHelper;

    private static final String[] countryColumns = {
            "id",
            "name",
            "continent"
    };

    private static final String[] quizColumns = {
            "id",
            "date",
            "score"
    };

    public CountriesData(Context context) {
        this.databaseHelper = DatabaseHelper.getInstance(context);
    }

    public void open() {

        database = databaseHelper.getWritableDatabase();
        Log.d("asedrfetg", database.getPath());
    }

    public void close() {
        if (databaseHelper != null) {
            databaseHelper.close();
        }
    }

    public boolean isOpen() {
        return database.isOpen();
    }

    public List<Country> retrieveCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;

        try {
            cursor = database.query("countries", countryColumns, null,
                    null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    if (cursor.getColumnCount() >= 3) {
                        columnIndex = cursor.getColumnIndex("name");
                        String name = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex("continent");
                        String continent = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex("id");
                        long id = cursor.getLong(columnIndex);

                        Country country = new Country(name, continent);
                        country.setID(id);

                        countries.add(country);
                    } // if
                } // while
            } // if
        } catch (Exception error) {
            Log.d("Error", error.getMessage());
            error.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return countries;
    }

    public List<Quiz> retrieveQuizzes() {
        ArrayList<Quiz> quizzes = new ArrayList<>();
        Cursor cursor = null;
        int columnIndex;

        try {
            cursor = database.query("results", quizColumns, null,
                    null, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    if (cursor.getColumnCount() >= 3) {
                        columnIndex = cursor.getColumnIndex("date");
                        String date = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex("score");
                        String score = cursor.getString(columnIndex);
                        columnIndex = cursor.getColumnIndex("id");
                        long id = cursor.getLong(columnIndex);

                        Quiz quiz = new Quiz(date, Integer.parseInt(score));
                        quiz.setID(id);

                        quizzes.add(quiz);
                    } // if
                } // while
            } // if
        } catch (Exception error) {
            Log.d("Error", error.getMessage());
            error.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return quizzes;
    }

    public Quiz storeQuiz(Quiz quiz) {
        ContentValues values = new ContentValues();
        values.put("date", quiz.getDate());
        values.put("score", Integer.toString(quiz.getCurrentScore()));

        long id = database.insert("results", null, values);
        quiz.setID(id);

        return quiz;
    }
}
