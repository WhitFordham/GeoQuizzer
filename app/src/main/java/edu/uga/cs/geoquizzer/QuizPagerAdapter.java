package edu.uga.cs.geoquizzer;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class QuizPagerAdapter extends FragmentStateAdapter {
    public QuizPagerAdapter(FragmentManager fragmentManager,
                            Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public Fragment createFragment(int position) {
        if (position == 6) {
            return ResultFragment.newInstance();
        } else return QuestionFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}

