package edu.uga.cs.geoquizzer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Class extending SQLiteOpenHelper that creates constants necessary for the database table.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "quizInformation.db";
    private static final int DATABASE_VERSION = 1;
    private static DatabaseHelper databaseInstance;
    private final Context context;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (databaseInstance == null) {
            databaseInstance = new DatabaseHelper(context.getApplicationContext());
        }

        return databaseInstance;
    }

    public void createDatabase() {
       File databaseFile = context.getDatabasePath(DATABASE_NAME);

       if (!databaseFile.exists()) {
           this.getReadableDatabase();

           copyDatabase();
       }
    }
    private void copyDatabase() {
        try {
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            OutputStream outputStream = new FileOutputStream(context.getDatabasePath(DATABASE_NAME));
            byte[] buffer = new byte[1024];
            int length;

            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException error) {
            Log.e("Error", error.getMessage());
        }
    }


    @Override
    public void onCreate(SQLiteDatabase database) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {

    }

}
