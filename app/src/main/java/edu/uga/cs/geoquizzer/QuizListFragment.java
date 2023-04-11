package edu.uga.cs.geoquizzer;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizListFragment extends Fragment {

    private static final String TAG = "ReviewJobLeadsFragment";

    private CountriesData countryData = null;
    private List<Quiz> quizList;

    private RecyclerView recyclerView;
    private QuizListRecyclerAdapter recyclerAdapter;

    public QuizListFragment() {
        // Required empty public constructor
    }

    public static QuizListFragment newInstance() {
        QuizListFragment fragment = new QuizListFragment();
        return fragment;
    }

    /**
     * Called when class is created.
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
    }

    /**
     * Called when view is created.
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
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        // Inflate the layout for this fragment
        return inflater.inflate( R.layout.fragment_quiz_list, container, false );
    }

    /**
     * Called on the view that was created.
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState ) {
        super.onViewCreated( view, savedInstanceState );

        recyclerView = getView().findViewById( R.id.recyclerView );

        // use a linear layout manager for the recycler view
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getActivity() );
        recyclerView.setLayoutManager( layoutManager );

        quizList = new ArrayList<Quiz>();
        recyclerAdapter = new QuizListRecyclerAdapter(getActivity(), quizList);

        recyclerView.setAdapter(recyclerAdapter);
        // Create a JobLeadsData instance, since we will need to save a new JobLead to the dn.
        // Note that even though more activites may create their own instances of the JobLeadsData
        // class, we will be using a single instance of the JobLeadsDBHelper object, since
        // that class is a singleton class.
        countryData = new CountriesData( getActivity() );

        // Open that database for reading of the full list of job leads.
        // Note that onResume() hasn't been called yet, so the db open in it
        // was not called yet!
        countryData.open();

        // Execute the retrieval of the job leads in an asynchronous way,
        // without blocking the main UI thread.
        new DatabaseReader().execute();

    }


    /**
     * Reads the database.
     */
    // This is an AsyncTask class (it extends AsyncTask) to perform DB reading of job leads, asynchronously.
    private class DatabaseReader extends AsyncTask<Void, List<Quiz>> {

        // This method will run as a background process to read from db.
        // It returns a list of retrieved JobLead objects.
        // It will be automatically invoked by Android, when we call the execute method
        // in the onCreate callback (the job leads review activity is started).
        @Override
        protected List<Quiz> doInBackground( Void... params ) {
            List<Quiz> quizList = countryData.retrieveQuizzes();

            return quizList;
        }

        // This method will be automatically called by Android once the db reading
        // background process is finished.  It will then create and set an adapter to provide
        // values for the RecyclerView.
        // onPostExecute is like the notify method in an asynchronous method call discussed in class.
        @Override
        protected void onPostExecute( List<Quiz> qList ) {
            quizList.addAll( qList );

            // create the RecyclerAdapter and set it for the RecyclerView
            recyclerAdapter = new QuizListRecyclerAdapter( getActivity(), quizList );
            recyclerView.setAdapter( recyclerAdapter );
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        // Open the database
        if( countryData != null && !countryData.isOpen() ) {
            countryData.open();
        }

        // Update the app name in the Action Bar to be the same as the app's name
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle( getResources().getString( R.string.app_name ) );
    }

}