package edu.uga.cs.geoquizzer;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

public class QuizListRecyclerAdapter extends RecyclerView.Adapter<QuizListRecyclerAdapter.QuizHolder> {

    private final Context context;

    private List<Quiz> values;
    private List<Quiz> originalValues;

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

        public QuizHolder( View itemView ) {
            super( itemView );

            quizTitle = itemView.findViewById( R.id.QuizTextView );
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

      //  Log.d( DEBUG_TAG, "onBindViewHolder: " + quiz );

        holder.quizTitle.setText( quiz.getDate());
    }

    @Override
    public int getItemCount() {
        if( values != null )
            return values.size();
        else
            return 0;
    }




}
