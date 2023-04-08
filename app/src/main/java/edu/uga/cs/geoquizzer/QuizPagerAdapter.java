package edu.uga.cs.geoquizzer;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * This is an adapter class for the PagerView to show all quiz questions.
 */
public class QuizPagerAdapter extends FragmentStateAdapter {
    /**
     * Constructor.
     * @param fragmentManager
     * @param lifecycle
     */
    public QuizPagerAdapter(FragmentManager fragmentManager,
                            Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    /**
     * Creates the fragment for the pager
     * @param position in fragment list
     * @return
     */
    @Override
    public Fragment createFragment(int position) {
        if (position == 6) {
            return ResultFragment.newInstance();
        } else if (position == 7) {
            return QuizListFragment.newInstance();
        } else {
            return QuestionFragment.newInstance(position);
        }
    }

    /**
     * Returns number of quizzes.
     * @return
     */
    @Override
    public int getItemCount() {
        return 8;
    }
}

