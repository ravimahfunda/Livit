package org.d3ifcool.livit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecommendationsActivity extends AppCompatActivity {

    private int index = 0; //menginisialisasikan index
    private ArrayList<Recommendation> listRecommendation; // private untuk enkapsulasi , ArrayList untuk menampung semua list recomendation
    private TextView title, description;
    private Button reminderButtonn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations); //men set view activity rekomendation
        title = (TextView) findViewById(R.id.recommendation_title); // title di inisialisasi dengan id recomendation_title
        description = (TextView) findViewById(R.id.description);
        reminderButtonn = (Button) findViewById(R.id.reminderButton);

        listRecommendation = new ArrayList<Recommendation>(); // memanggil array list list recomendation
        listRecommendation.add(new Recommendation("Wake Up", "Time to Wake Up")); // memberikan deskripsi pada list recomendation
        listRecommendation.add(new Recommendation("Breakfast", "Time to Breakafst"));
        listRecommendation.add(new Recommendation("Lunch", "Don't Forget to Lunch guys"));
        listRecommendation.add(new Recommendation("Exercise", "Time to Excercise. Don't miss it."));
        listRecommendation.add(new Recommendation("Dinner", "Time to Dinner"));
        listRecommendation.add(new Recommendation("Sleep", "Time to Sleep !! "));

        final ImageView nextButton = (ImageView) findViewById(R.id.nextButton); // megeset nextButton agar bisa di klik untuk next(ke rekomendation selanjutnya)
        nextButton.setOnClickListener(new View.OnClickListener() { // agar next\button bisa di klik
            @Override
            public void onClick(View view) {
                if (index == listRecommendation.size() - 1) return; // jika  index listRecumendation kurang dari 1 maka index di tambah dan rekomendation di update
                index ++;
                updateRecommendation();
            }
        });

        final ImageView prevButton = (ImageView) findViewById(R.id.prevButton);// megeset prevButton agar bisa di klik untuk prev ( ke rekomendation sebelumnya)
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // jika  index listRecumendation sama dengan 0 maka index--
                if (index==0)return;
                index--;
                updateRecommendation();
            }
        });

        reminderButtonn.setOnClickListener(new View.OnClickListener() { // mengubah warna , supaya jika di klik warna nya berganti
            @Override
            public void onClick(View view) { // agar bisa di klik
                listRecommendation.get(index).setReminding(!listRecommendation.get(index).isReminding()); // memanggil list recomendation berdasarkan index
                updateRecommendation(); // memanggil method update recomendation
            }
        });

        updateRecommendation();
    }

    void updateRecommendation(){
        title.setText(listRecommendation.get(index).getTitle()); // untuk memanggil title berdasarkan index
        description.setText(listRecommendation.get(index).getDescription());
        int imageId; // variable image\id
        if(listRecommendation.get(index).isReminding())imageId=R.drawable.circle_danger; // jika list sudah di klik makan akan menampilkan button dari drawble circle_danger.
        else imageId=R.drawable.button_background; //jika tidak di klik button tetap (button backgroun)
        reminderButtonn.setBackground(getResources().getDrawable(imageId)); // button di set dari drawable image id
    }
}