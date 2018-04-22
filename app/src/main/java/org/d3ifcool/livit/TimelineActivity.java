package org.d3ifcool.livit;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import org.d3ifcool.livit.adapter.NutritionsCursorAdapter;
import org.d3ifcool.livit.adapter.TimelineFragmentAdapter;

public class TimelineActivity extends AppCompatActivity{

//    private ExercisesCursorAdapter mExercisesCursorAdapter;
    private NutritionsCursorAdapter mNutritionsCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        TimelineFragmentAdapter categoryAdapter = new TimelineFragmentAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(categoryAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
    }


}
