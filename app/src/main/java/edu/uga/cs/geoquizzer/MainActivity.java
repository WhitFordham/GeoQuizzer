package edu.uga.cs.geoquizzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper helper = DatabaseHelper.getInstance(this);

        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DATABASE_NAME);

        if (database.exists() == false) {
            helper.getReadableDatabase();

            if (copyDatabase(this)) {
                Toast.makeText(this, "Successfully copied database", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Could not copy database", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }

    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DATABASE_NAME);
            String fileName = DatabaseHelper.DATABASE_LOCATION + DatabaseHelper.DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(fileName);
            byte [] buff = new byte[1024];
            int length = 0;

            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }

            outputStream.flush();
            outputStream.close();

            return true;
        } catch (Exception error) {
            error.printStackTrace();
            return false;
        }
    }
}