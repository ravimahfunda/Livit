package org.d3ifcool.livit;

import android.content.ContentUris;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.d3ifcool.livit.data.nutritions.NutritionsContract;
import org.d3ifcool.livit.data.settings.SettingsContract;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View exerciseButton = findViewById(R.id.exercise_button);
        View nutritionButton = findViewById(R.id.nutrition_button);
        View achievementsButton = findViewById(R.id.achievements_button);
        View recommendationsButton = findViewById(R.id.recommendation_button);
        View timelineButton = findViewById(R.id.timeline_button);
        View settingsButton = findViewById(R.id.settings_button);

        exerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ExerciseActivity.class);
                startActivity(i);
            }
        });
        achievementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AchievementsActivity.class);
                startActivity(i);
            }
        });
        timelineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, TimelineActivity.class);
                startActivity(i);
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                i.setData(ContentUris.withAppendedId(SettingsContract.SettingsEntry.CONTENT_URI, 1));
                startActivity(i);
            }
        });
        recommendationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RecommendationsActivity.class);
                startActivity(i);
            }
        });
        nutritionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NutritionsActivity.class);
                i.setData(ContentUris.withAppendedId(NutritionsContract.NutritionsEntry.CONTENT_URI, 1));
                startActivity(i);
            }
        });
    }
}
