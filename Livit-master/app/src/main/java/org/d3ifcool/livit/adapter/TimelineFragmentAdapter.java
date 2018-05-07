package org.d3ifcool.livit.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.d3ifcool.livit.TimelineExercisesFragment;
import org.d3ifcool.livit.TimelineNutritionsFragment;

/**
 * Created by Multimedia on 23/04/2018.
 */

public class TimelineFragmentAdapter extends FragmentPagerAdapter{

    private Context mContext;
    public TimelineFragmentAdapter(Context c, FragmentManager fm) {
        super(fm);
        mContext = c;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new TimelineExercisesFragment();
            default: return new TimelineNutritionsFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 : return "Exercises";
            default : return "Nutritions";
        }
    }
}
