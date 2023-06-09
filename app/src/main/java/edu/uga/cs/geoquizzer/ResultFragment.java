package edu.uga.cs.geoquizzer;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Class to show the results at the end of a quiz.
 * <p>
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultFragment extends Fragment {

    private CountriesData countriesData = new CountriesData(getActivity());
    private boolean isQuizStored = false;

    public ResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ResultFragment newInstance() {
        ResultFragment fragment = new ResultFragment();
        return fragment;
    }

    /**
     * Called when class is created.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView score = view.findViewById(R.id.textView2);
        score.setText("Your Score: " + QuizActivity.newQuiz.getCurrentScore() + "/6");

        countriesData.open();
        new DatabaseWriter().execute(QuizActivity.newQuiz);

        TextView message = view.findViewById(R.id.textView6);

        Quiz quiz = QuizActivity.newQuiz;
        if (quiz.getCurrentScore() == 0 || quiz.getCurrentScore() == 1 || quiz.getCurrentScore() == 2) {
            message.setText("Better luck next time!");
        } else if (quiz.getCurrentScore() == 3 || quiz.getCurrentScore() == 4) {
            message.setText("Good job!");
        } else if (quiz.getCurrentScore() == 5) {
            message.setText("Great job!");
        } else if (quiz.getCurrentScore() == 6) {
            message.setText("Flawless!");
        }

        Button newQuizButton = getView().findViewById(R.id.button4);
        Button prevResultsButton = getView().findViewById(R.id.button3);

        newQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity.pager.setCurrentItem(0, false);
                ((QuizActivity) getActivity()).startNewQuiz();
                getActivity().finish();
                Intent intent = new Intent(getContext(), QuizActivity.class);
                getActivity().startActivity(intent);
            }
        });

        prevResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity.pager.setCurrentItem(8);
            }
        });

    }

    /**
     * Writes quiz info to the database.
     */
    private class DatabaseWriter extends AsyncTask<Quiz, Quiz> {
        @Override
        protected Quiz doInBackground(Quiz... quizzes) {
            countriesData.storeQuiz(quizzes[0]);
            return quizzes[0];
        }

        @Override
        protected void onPostExecute(Quiz quiz) {
            // Show a quick confirmation message
            Toast.makeText(getActivity(), "Quiz created for " + quiz.getDate(),
                    Toast.LENGTH_SHORT).show();
            countriesData.close();
        }
    }
}