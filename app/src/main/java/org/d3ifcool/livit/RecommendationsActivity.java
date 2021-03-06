package org.d3ifcool.livit;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.livit.entity.Recommendation;

import java.util.ArrayList;
import java.util.List;

public class RecommendationsActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Recommendation>>{

////    private int index = 0;
//    private ArrayList<Recommendation> listRecommendation;
//    private ListView nama, tipe;
    public static final String LOG_TAG = RecommendationsActivity.class.getName();

    private RecommendationAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations); //men set view activity rekomendation

        //Assigning UI to java code
        ArrayList<String> recommendations = new ArrayList<>();
        recommendations.add("Carbs");
        recommendations.add("Protein");
        recommendations.add("Vegetable");
        recommendations.add("Fruity");
        recommendations.add("Milk");

        ListView recommendationListView = (ListView) findViewById(R.id.list);

        mAdapter = new RecommendationAdapter(this, new ArrayList<Recommendation>());

        recommendationListView.setAdapter(mAdapter);
        getSupportLoaderManager().initLoader(1, null, this);
//        //Creating data
//        listRecommendation = new ArrayList<Recommendation>(); // memanggil array list list recomendation
//        listRecommendation.add(new Recommendation("Wake Up", "Time to Wake Up")); // memberikan deskripsi pada list recomendation
//        listRecommendation.add(new Recommendation("Breakfast", "Time to Breakafst"));
//        listRecommendation.add(new Recommendation("Lunch", "Don't Forget to Lunch guys"));
//        listRecommendation.add(new Recommendation("Exercise", "Time to Excercise. Don't miss it."));
//        listRecommendation.add(new Recommendation("Dinner", "Time to Dinner"));
//        listRecommendation.add(new Recommendation("Sleep", "Time to Sleep !! "));

//        nextButton.setOnClickListener(new View.OnClickListener() { // agar next\button bisa di klik
//            @Override
//            public void onClick(View view) {
//                //Increasing the index for swapping between data, forbid it if it is the last data
//                if (index == listRecommendation.size() - 1) return;
//                index ++;
//                updateRecommendation();
//            }
//        });
//
//        prevButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Decrease the index for swapping between data, forbid it if it is the first data
//                if (index==0)return;
//                index--;
//                //Update UI
//                updateRecommendation();
//            }
//        });
//
//        reminderButtonn.setOnClickListener(new View.OnClickListener() { // mengubah warna , supaya jika di klik warna nya berganti
//            @Override
//            public void onClick(View view) { // agar bisa di klik
//                //Update the state of object
//                listRecommendation.get(index).setReminding(!listRecommendation.get(index).isReminding());
//                //Update ui
//                updateRecommendation();
//                Toast.makeText(RecommendationsActivity.this,"Features not implemented yet", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //Update UI for sync-ing with data
//        updateRecommendation();
//    }

        /**
         * Updating the UI that binds to data
         * */
//    void updateRecommendation(){
//        //Update title
//        tipe.setText(listRecommendation.get(index).getTitle());
//        //Update description
//        description.setText(listRecommendation.get(index).getDescription());
//
//        int imageId;
//        //Checking reminder state, is it clicked ?
//        if(listRecommendation.get(index).isReminding())imageId=R.drawable.circle_danger;
//        else imageId=R.drawable.circle_accent;
//        //Update remined button
//        reminderButtonn.setBackground(getResources().getDrawable(imageId));
//    }
    }

    @Override
    public Loader<List<Recommendation>> onCreateLoader(int id, Bundle args) {
        return new RecommendationLoader(this, "https://raw.githubusercontent.com/ravimahfunda/Livit-Dummy-API/master/recomendation.json?token=AWHf63uqbsfwRoUqXBObn4eWEKld3amyks5a-DcgwA%3D%3D");
    }

    @Override
    public void onLoadFinished(Loader<List<Recommendation>> loader, List<Recommendation> data) {
        View progress = findViewById(R.id.progress);
        progress.setVisibility(View.GONE);

        mAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Recommendation>> loader) {
        mAdapter.clear();
    }
}