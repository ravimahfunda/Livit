package org.d3ifcool.livit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecommendationsActivity extends AppCompatActivity {

    private int index = 0;
    private ArrayList<Recommendation> listRecommendation;
    private TextView title, description;
    private Button reminderButtonn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations); //men set view activity rekomendation

        //Assigning UI to java code
        title = (TextView) findViewById(R.id.title_textview);
        description = (TextView) findViewById(R.id.description_textview);
        reminderButtonn = (Button) findViewById(R.id.reminder_button);
        ImageView nextButton = (ImageView) findViewById(R.id.next_button);
        ImageView prevButton = (ImageView) findViewById(R.id.previous_button);

        //Creating data
        listRecommendation = new ArrayList<Recommendation>(); // memanggil array list list recomendation
        listRecommendation.add(new Recommendation("Wake Up", "Time to Wake Up")); // memberikan deskripsi pada list recomendation
        listRecommendation.add(new Recommendation("Breakfast", "Time to Breakafst"));
        listRecommendation.add(new Recommendation("Lunch", "Don't Forget to Lunch guys"));
        listRecommendation.add(new Recommendation("Exercise", "Time to Excercise. Don't miss it."));
        listRecommendation.add(new Recommendation("Dinner", "Time to Dinner"));
        listRecommendation.add(new Recommendation("Sleep", "Time to Sleep !! "));

        nextButton.setOnClickListener(new View.OnClickListener() { // agar next\button bisa di klik
            @Override
            public void onClick(View view) {
                //Increasing the index for swapping between data, forbid it if it is the last data
                if (index == listRecommendation.size() - 1) return;
                index ++;
                updateRecommendation();
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Decrease the index for swapping between data, forbid it if it is the first data
                if (index==0)return;
                index--;
                //Update UI
                updateRecommendation();
            }
        });

        reminderButtonn.setOnClickListener(new View.OnClickListener() { // mengubah warna , supaya jika di klik warna nya berganti
            @Override
            public void onClick(View view) { // agar bisa di klik
                //Update the state of object
                listRecommendation.get(index).setReminding(!listRecommendation.get(index).isReminding());
                //Update ui
                updateRecommendation();
            }
        });

        //Update UI for sync-ing with data
        updateRecommendation();
    }

    /**
     * Updating the UI that binds to data
     * */
    void updateRecommendation(){
        //Update title
        title.setText(listRecommendation.get(index).getTitle());
        //Update description
        description.setText(listRecommendation.get(index).getDescription());

        int imageId;
        //Checking reminder state, is it clicked ?
        if(listRecommendation.get(index).isReminding())imageId=R.drawable.circle_danger;
        else imageId=R.drawable.circle_accent;
        //Update remined button
        reminderButtonn.setBackground(getResources().getDrawable(imageId));
    }
}