package edu.uga.cs.geoquizzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    static ArrayList<Country> countryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_splash_screen);

        Button newQuizButton = findViewById(R.id.button);

        DatabaseHelper helper = DatabaseHelper.getInstance(getApplicationContext());

        helper.createDatabase();

        new DatabaseReader().execute();
        newQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Number of Countries", "Size: " + countryList.size());
                Intent intent = new Intent();
                intent.setClass(v.getContext(), QuizActivity.class);
                startActivity(intent);
            }
        });
    }

    private class DatabaseReader extends AsyncTask<Void, List<Country>> {
        private CountriesData countriesData = new CountriesData(getApplicationContext());
        @Override
        protected List<Country> doInBackground(Void... params) {
            countriesData.open();
            List<Country> countries = countriesData.retrieveCountries();
            return countries;
        }

        @Override
        protected void onPostExecute(List<Country> countries) {
            countryList.addAll(countries);
            countriesData.close();
        }
    }
}