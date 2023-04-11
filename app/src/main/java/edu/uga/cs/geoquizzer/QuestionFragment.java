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

    private TextView country;
    private RadioButton choice1;
    private RadioButton choice2;
    private RadioButton choice3;
    private boolean isIncremented;
    private int correctChoice;

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
        QuizActivity.newQuiz.setNumOfAnsweredQuestions(questionNumber);
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        args.putInt("questionNum", questionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called to do initial creation of a fragment.
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            questionNumber = getArguments().getInt("questionNum");
        }
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.question, container, false);
    }


    /**
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Question question = QuizActivity.newQuiz.getQuestions().get(questionNumber);
        int score = QuizActivity.newQuiz.getCurrentScore();

        country = view.findViewById(R.id.textView5);
        country.setText(question.getCountryName());

        choice1 = view.findViewById(R.id.radioButton);
        choice2 = view.findViewById(R.id.radioButton2);
        choice3 = view.findViewById(R.id.radioButton3);

        Random random = new Random();
        correctChoice = random.nextInt(3) + 1;
        RadioButton correctButton = choice1;

        if (savedInstanceState != null) {
            QuizActivity.newQuiz.setCurrentScore(savedInstanceState.getInt("score"));
            country.setText(savedInstanceState.getString("country"));
            choice1.setText(savedInstanceState.getString("choice1"));
            choice2.setText(savedInstanceState.getString("choice2"));
            choice3.setText(savedInstanceState.getString("choice3"));
            correctChoice = savedInstanceState.getInt("correctChoice");
            isIncremented = savedInstanceState.getBoolean("isCorrect");
            if (isIncremented) {
                QuizActivity.newQuiz.decrementScore();
            }

            if (correctChoice == 1) correctButton = choice1;
            if (correctChoice == 2) correctButton = choice2;
            if (correctChoice == 3) correctButton = choice3;
        } else {
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
        }

        final RadioButton userChoice = correctButton;
        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener((group, button) -> {
            RadioButton selectedButton = view.findViewById(button);

            if (selectedButton == userChoice) {
                isIncremented = true;
                QuizActivity.newQuiz.incrementScore();
            } else {
                if (QuizActivity.newQuiz.getCurrentScore() > score) {
                    QuizActivity.newQuiz.decrementScore();
                    isIncremented = false;
                }
            }
        });

    }


    /**
     * Called to ask the fragment to save its current dynamic state, so it can later be reconstructed in a new instance if its process is restarted.
     * @param outState Bundle in which to place your saved state.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score", QuizActivity.newQuiz.getCurrentScore());
        outState.putString("country", country.getText().toString());
        outState.putString("choice1", choice1.getText().toString());
        outState.putString("choice2", choice2.getText().toString());
        outState.putString("choice3", choice3.getText().toString());
        outState.putInt("correctChoice", correctChoice);
        outState.putBoolean("isCorrect", isIncremented);
    }
}