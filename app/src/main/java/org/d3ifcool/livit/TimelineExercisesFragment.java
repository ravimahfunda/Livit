package org.d3ifcool.livit;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.d3ifcool.livit.adapter.ExercisesCursorAdapter;
import org.d3ifcool.livit.adapter.NutritionsCursorAdapter;
import org.d3ifcool.livit.data.LivitContract;

/**
 * Created by Multimedia on 23/04/2018.
 */

public class TimelineExercisesFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>{

    private ExercisesCursorAdapter mExercisesCursorAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timeline_list, container, false);

        //Implement log data to log list in layout
        ListView listView = (ListView) rootView.findViewById(R.id.timeline_list);

        mExercisesCursorAdapter= new ExercisesCursorAdapter(getContext(), null);

        listView.setAdapter(mExercisesCursorAdapter);

        getLoaderManager().initLoader(1,null,this);
        return  rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                LivitContract.ExercisessEntry._ID,
                LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_DATE_TIME,
                LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_TRACK,

        };

        return new android.support.v4.content.CursorLoader(
                getContext(),
                LivitContract.ExercisessEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mExercisesCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mExercisesCursorAdapter.swapCursor(null);
    }
}
