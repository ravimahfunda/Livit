package org.d3ifcool.livit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class AchievementsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        //Creating achievement data
        ArrayList<Achievement> achievements = new ArrayList<>();
        achievements.add(new Achievement("Runner","Run 1000 m",100,Achievement.CATEGORY_EXCERSISE));
        achievements.add(new Achievement("Vegeta","Eat 5 vegetables",80,Achievement.CATEGORY_NUTRITION));
        achievements.add(new Achievement("Heroes","Fill all data",30,Achievement.CATEGORY_OTHER));
        achievements.add(new Achievement("Diligent","Log in 7 deay consecutively",100,Achievement.CATEGORY_OTHER));
        achievements.add(new Achievement("Marathon","Run 4000m",60,Achievement.CATEGORY_EXCERSISE));

        //Implement log data to log list in layout
        ListView listView = (ListView) findViewById(R.id.achievements_list_view);
        listView.setAdapter(new AchievementAdapter(this, achievements));
    }
}
