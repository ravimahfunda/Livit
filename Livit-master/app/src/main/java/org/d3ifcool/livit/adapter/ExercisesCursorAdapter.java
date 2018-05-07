package org.d3ifcool.livit.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.d3ifcool.livit.R;
import org.d3ifcool.livit.data.LivitContract;

/**
 * Created by ravimahfunda on 16/04/2018.
 */

public class ExercisesCursorAdapter extends CursorAdapter {
    public ExercisesCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.log_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView timeTextView= (TextView) view.findViewById(R.id.times_textview);
        timeTextView.setText(cursor.getString(cursor.
                getColumnIndex(LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_DATE_TIME)));

        TextView descriptionTextView= (TextView) view.findViewById(R.id.description_textview);
        descriptionTextView.setText(cursor.getString(cursor.
                getColumnIndex(LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_TRACK))+"m of run");
    }
}
