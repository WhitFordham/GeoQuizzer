package edu.uga.cs.geoquizzer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private int questionNumber;

    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param questionNumber the question number the user is on
     * @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance(int questionNumber) {
        Log.d("Number", "Question " + questionNumber);
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt("questionNum", questionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionNumber = getArguments().getInt("questionNum");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.question, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Question question = QuizActivity.newQuiz.getQuestions().get(questionNumber);
        int score = QuizActivity.newQuiz.getCurrentScore();

        TextView country = view.findViewById(R.id.textView5);
        country.setText(question.getCountryName());

        RadioButton choice1 = view.findViewById(R.id.radioButton);
        RadioButton choice2 = view.findViewById(R.id.radioButton2);
        RadioButton choice3 = view.findViewById(R.id.radioButton3);

        Random random = new Random();
        int correctChoice = random.nextInt(3) + 1;
        RadioButton correctButton = choice1;

        if (correctChoice == 1) {
            correctButton = choice1;
            choice1.setText(question.getCorrectAnswer());
            choice2.setText(question.getIncorrectAnswer1());
            choice3.setText(question.getIncorrectAnswer2());
        } else if (correctChoice == 2) {
            correctButton = choice2;
            choice2.setText(question.getCorrectAnswer());
            choice1.setText(question.getIncorrectAnswer1());
            choice3.setText(question.getIncorrectAnswer2());
        } else if (correctChoice == 3) {
            correctButton = choice3;
            choice3.setText(question.getCorrectAnswer());
            choice2.setText(question.getIncorrectAnswer1());
            choice1.setText(question.getIncorrectAnswer2());
        }

        final RadioButton userChoice = correctButton;
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener((group, button) -> {
            RadioButton selectedButton = view.findViewById(button);

            if (selectedButton == userChoice) {
                QuizActivity.newQuiz.setCurrentScore(1);
                Log.d("Message", "Score: " + QuizActivity.newQuiz.getCurrentScore());
            } else {
                if (QuizActivity.newQuiz.getCurrentScore() > score) {
                    QuizActivity.newQuiz.setCurrentScore(-1);
                }
                Log.d("Message", "Score: " + QuizActivity.newQuiz.getCurrentScore());
            }
        });
        if (correctButton.isChecked()) {
            QuizActivity.newQuiz.setCurrentScore(1);
            Log.d("Message", "Score: " + QuizActivity.newQuiz.getCurrentScore());
        }
    }

    /*
    Used in different fragments
    private class DatabaseReader extends AsyncTask<Void, List<Quiz>> {
        List<Quiz> quizResults = new ArrayList<>();

        @Override
        protected List<Quiz> doInBackground(Void... params) {
            List<Quiz> quizzes = SplashScreenFragment.countriesData.retrieveQuizzes();
            return quizzes;
        }

        @Override
        protected void onPostExecute(List<Quiz> quizList) {
            quizResults.addAll(quizList);
            SplashScreenFragment.countriesData.close();
        }
    }

    private class DatabaseWriter extends AsyncTask<Quiz, Quiz> {
        @Override
        protected Quiz doInBackground(Quiz... quizzes) {
            SplashScreenFragment.countriesData.storeQuiz(quizzes[0]);
            return quizzes[0];
        }

        @Override
        protected void onPostExecute(Quiz quiz) {
            // Show a quick confirmation message
            Toast.makeText(getActivity(), "Job lead created for " + quiz.getDate(),
                    Toast.LENGTH_SHORT).show();
        }
    }
     */
}