package edu.uga.cs.geoquizzer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class QuizActivity extends AppCompatActivity {
    static Quiz newQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ViewPager2 pager = findViewById(R.id.viewPager);

        QuizPagerAdapter quizPagerAdapter = new QuizPagerAdapter(
                getSupportFragmentManager(), getLifecycle());

        pager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        pager.setAdapter(quizPagerAdapter);

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);

        newQuiz = new Quiz(strDate, 0);

        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(MainActivity.countryList.size());
            String correctAnswer = MainActivity.countryList.get(index).getCountryContinent();
            String incorrectAnswer1 = getRandomAnswer(correctAnswer);
            String incorrectAnswer2 = getRandomAnswer(correctAnswer);

            while (incorrectAnswer2.equals(incorrectAnswer1)) incorrectAnswer2 = getRandomAnswer(incorrectAnswer1);

            Question question = new Question(MainActivity.countryList.get(index).getCountryName(),
                    correctAnswer, incorrectAnswer1, incorrectAnswer2);

            newQuiz.getQuestions().add(question);
            MainActivity.countryList.remove(index);
        }
    }

    public String getRandomAnswer(String correctAnswer) {
        String[] continentsList = new String[]{
                "Europe",
                "Asia",
                "Australia",
                "North America",
                "South America",
                "Africa",
                "Antarctica"
        };

        int randomNum = (int) (Math.random() * 7);
        if (continentsList[randomNum].equals(correctAnswer)) {
            return getRandomAnswer(correctAnswer);
        } else {
            return continentsList[randomNum];
        }
    }

}