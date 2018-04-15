package org.d3ifcool.livit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.d3ifcool.livit.R;
import org.d3ifcool.livit.entity.Achievement;

import java.util.List;

/**
 * Managa how data shown and adapting Achievement Custom Class to Achievement List Item
 */
public class AchievementAdapter extends ArrayAdapter<Achievement> {
    public AchievementAdapter(@NonNull Context context, @NonNull List<Achievement> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Recycling view by checking if the view is available or not, if available reuse it, if not inflate it
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.achievement_list_item, parent, false);
        }

        //Retrieve current data
        Achievement currentAchievement = getItem(position);

        //Passing title to layout
        TextView titleTextView = (TextView) convertView.findViewById(R.id.title_textview);
        titleTextView.setText(currentAchievement.getTitle());

        //Passing description to layout
        TextView descriptionTextView = (TextView) convertView.findViewById(R.id.description_textview);
        descriptionTextView.setText(currentAchievement.getDescription());

        //Passing progress to layout
        ProgressBar progressBar = (ProgressBar) convertView.findViewById(R.id.progress_bar);
        progressBar.setProgress(currentAchievement.getProgress());

        //Passing category to layout as image
        ImageView categoryImageView = (ImageView) convertView.findViewById(R.id.category_imageview);
        int imageId;
        //Choosing the image based on category
        switch (currentAchievement.getCategory()){
            case Achievement.CATEGORY_EXCERSISE :imageId = R.drawable.ic_directions_run_black_24dp; break;
            case Achievement.CATEGORY_NUTRITION :imageId = R.drawable.ic_restaurant_black_24dp; break;
            default: imageId = R.drawable.ic_thumb_up_black_24dp; break;
        }
        categoryImageView.setImageResource(imageId);
        if (currentAchievement.isCompleted()) categoryImageView.setColorFilter(
                ContextCompat.getColor(getContext(), R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);

        return convertView;
    }
}
