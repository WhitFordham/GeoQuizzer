package edu.uga.cs.geoquizzer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    CountriesData countriesData = null;
    List<Country> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHelper helper = DatabaseHelper.getInstance(this);

        helper.createDatabase();

    }

    private class DatabaseReader extends AsyncTask<Void, List<Country>> {
        @Override
        protected List<Country> doInBackground(Void... params) {
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