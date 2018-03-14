package org.d3ifcool.livit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        View actionButtons = findViewById(R.id.action_buttons);
        actionButtons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ExerciseActivity.this,"Features not implemented yet", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
