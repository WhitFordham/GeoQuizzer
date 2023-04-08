package edu.uga.cs.geoquizzer;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * This is an adapter class for the RecyclerView to show all quizzes.
 */
public class QuizListRecyclerAdapter extends RecyclerView.Adapter<QuizListRecyclerAdapter.QuizHolder> {

    private final Context context;

    private List<Quiz> values;
    private List<Quiz> originalValues;

    /**
     * Constructor object
     * @param context
     * @param quizList
     */
    public QuizListRecyclerAdapter( Context context, List<Quiz> quizList ) {
        this.context = context;
        this.values = quizList;
        this.originalValues = new ArrayList<Quiz>( quizList );
    }

    public void sync()
    {
        originalValues = originalValues = new ArrayList<Quiz>( values );
    }

    public static class QuizHolder extends RecyclerView.ViewHolder {

        TextView quizTitle;
        TextView question1;
        TextView question2;
        TextView question3;
        TextView question4;
        TextView question5;
        TextView question6;
        TextView results;



        public QuizHolder( View itemView ) {
            super( itemView );
            quizTitle = itemView.findViewById( R.id.QuizTextView );
            question1 = itemView.findViewById( R.id.question1 );
            question2 = itemView.findViewById( R.id.question2 );
            question3 = itemView.findViewById( R.id.question3 );
            question4 = itemView.findViewById( R.id.question4 );
            question5 = itemView.findViewById( R.id.question5 );
            question6 = itemView.findViewById( R.id.question6 );
            results = itemView.findViewById( R.id.results );
        }
    }

    @NonNull
    @Override
    public QuizHolder onCreateViewHolder( ViewGroup parent, int viewType ) {
        // We need to make sure that all CardViews have the same, full width, allowed by the parent view.
        // This is a bit tricky, and we must provide the parent reference (the second param of inflate)
        // and false as the third parameter (don't attach to root).
        // Consequently, the parent view's (the RecyclerView) width will be used (match_parent).
        View view = LayoutInflater.from( parent.getContext()).inflate( R.layout.quiz, parent, false );

        return new QuizHolder( view );
    }

    @Override
    public void onBindViewHolder( QuizHolder holder, int position ) {

        Quiz quiz = values.get( position );

        holder.quizTitle.setText( quiz.getDate());
        holder.results.setText("Score: " + quiz.getCurrentScore() + "/6");
    }

    @Override
    public int getItemCount() {
        if( values != null ) {
            return values.size();
        } else
            return 0;
    }
}
