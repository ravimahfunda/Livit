package org.d3ifcool.livit.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.d3ifcool.livit.R;
import org.d3ifcool.livit.data.LivitContract;
import org.d3ifcool.livit.entity.Achievement;

/**
 * Created by ravimahfunda on 16/04/2018.
 */

public class AchievementCursorAdapter extends CursorAdapter {
    public AchievementCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.achievement_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView titleTextView= (TextView) view.findViewById(R.id.title_textview);
        titleTextView.setText(cursor.getString(cursor.
                getColumnIndex(LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_TITLE)));

        TextView descriptionTextView= (TextView) view.findViewById(R.id.description_textview);
        descriptionTextView.setText(cursor.getString(cursor.
                getColumnIndex(LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_DESCRIPTION)));

        ProgressBar progressBar= view.findViewById(R.id.progress_bar);
        progressBar.setProgress(
                cursor.getInt(cursor.getColumnIndex(
                        LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_PROGRESS)) * 100 /
                        cursor.getInt(cursor.getColumnIndex(
                                LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_TARGET))
        );

        //Passing category to layout as image
        ImageView categoryImageView = (ImageView) view.findViewById(R.id.category_imageview);
        int imageId;
        //Choosing the image based on category
        switch (cursor.getColumnIndex(
                LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_CATEGORY)){
            case LivitContract.AchievementsEntry.TYPE_EXERCISES :imageId = R.drawable.ic_directions_run_black_24dp; break;
            case LivitContract.AchievementsEntry.TYPE_NUTRITIONS :imageId = R.drawable.ic_restaurant_black_24dp; break;
            default: imageId = R.drawable.ic_thumb_up_black_24dp; break;
        }
        categoryImageView.setImageResource(imageId);
    }
}
