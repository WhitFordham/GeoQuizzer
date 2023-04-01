package edu.uga.cs.geoquizzer;

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

            Log.d("Truth", "Cursor: " + cursor);
            if (cursor != null && cursor.getCount() > 0) {
                Log.d("THIE IS A WARNING", "Sheemalolo");
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
            Log.d("Wow", error.getMessage());
            error.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return countries;
    }
}
