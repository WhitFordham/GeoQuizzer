package edu.uga.cs.geoquizzer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SplashScreenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashScreenFragment extends Fragment {
    static CountriesData countriesData = null;
    static List<Country> countryList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SplashScreenFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SplacshScreenFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SplashScreenFragment newInstance(String param1, String param2) {
        SplashScreenFragment fragment = new SplashScreenFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        DatabaseHelper helper = DatabaseHelper.getInstance(getActivity());

        helper.createDatabase();

        countryList = new ArrayList<>();
        countriesData = new CountriesData(getActivity());

        countriesData.open();
        new DatabaseReader().execute();

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        Button newQuizButton = view.findViewById( R.id.button );
        Button prevResultsButton = view.findViewById( R.id.button2 );

        newQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Quiz newQuiz = new Quiz(strDate, "0");
                int randomNum = (int) (Math.random() * (countryList.size() - 6)) + 0;
                System.out.println(countryList.size());


                if (countryList.size() < 7) {
                    Question[] questions = new Question[]{
                            new Question(),
                            new Question(),
                            new Question(),
                            new Question(),
                            new Question(),
                            new Question()
                    };
                    newQuiz.setQuestions(questions);

                } else {

                    Question[] questions = new Question[]{
                            new Question(countryList.get(randomNum).getCountryName(), countryList.get(randomNum).getCountryContinent(), "wrong answer", "wrong answer"),
                            new Question(countryList.get(randomNum + 1).getCountryName(), countryList.get(randomNum + 1).getCountryContinent(), "wrong answer", "wrong answer"),
                            new Question(countryList.get(randomNum + 2).getCountryName(), countryList.get(randomNum + 2).getCountryContinent(), "wrong answer", "wrong answer"),
                            new Question(countryList.get(randomNum + 3).getCountryName(), countryList.get(randomNum + 3).getCountryContinent(), "wrong answer", "wrong answer"),
                            new Question(countryList.get(randomNum + 4).getCountryName(), countryList.get(randomNum + 4).getCountryContinent(), "wrong answer", "wrong answer"),
                            new Question(countryList.get(randomNum + 5).getCountryName(), countryList.get(randomNum + 5).getCountryContinent(), "wrong answer", "wrong answer")
                    };
                    newQuiz.setQuestions(questions);
                }

                System.out.println(newQuiz.getQuestions()[1].getCorrectAnswer());

            }
        });

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