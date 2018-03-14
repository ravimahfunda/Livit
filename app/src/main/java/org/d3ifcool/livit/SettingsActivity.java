package org.d3ifcool.livit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        User user = new User("Erna Rahmawati Rizal", "Rahma", "ernarahmawatirizal@gmail.com", "O", "Diet" ,"170cm" ,"47kg" , "16", "Female");


        EditText displayNameEditText = (EditText) findViewById(R.id.displayName);
        displayNameEditText.setText(user.getDisplayName());

        EditText emailEditText = (EditText) findViewById(R.id.email);
        emailEditText.setText(user.getEmail());

        EditText usernameEditText = (EditText) findViewById(R.id.username);
        usernameEditText.setText(user.getUsername());

        EditText bloodTypeEditText = (EditText) findViewById(R.id.blood_type);
        bloodTypeEditText.setText(user.getBloodType());

        EditText goalsEditText = (EditText) findViewById(R.id.goals);
        goalsEditText.setText(user.getGoals());

        EditText heightEditText = (EditText) findViewById(R.id.your_height);
        heightEditText.setText(user.getHeight());


        EditText weightEditText = (EditText) findViewById(R.id.your_weight);
        weightEditText.setText(user.getWeight());


        EditText ageEditText = (EditText) findViewById(R.id.your_age);
        ageEditText.setText(user.getAge());


        EditText sexEditText = (EditText) findViewById(R.id.your_sex);
        sexEditText.setText(user.getSex());


















    }
}
