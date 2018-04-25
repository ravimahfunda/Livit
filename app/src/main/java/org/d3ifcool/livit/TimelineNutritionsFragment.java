package org.d3ifcool.livit;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.d3ifcool.livit.adapter.ExercisesCursorAdapter;
import org.d3ifcool.livit.adapter.NutritionsCursorAdapter;
import org.d3ifcool.livit.data.LivitContract;

/**
 * Created by Multimedia on 23/04/2018.
 */

public class TimelineNutritionsFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>{

    private NutritionsCursorAdapter mNutritionsCursorAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.timeline_list, container, false);

        //Implement log data to log list in layout
        ListView listView = (ListView) rootView.findViewById(R.id.timeline_list);

        mNutritionsCursorAdapter = new NutritionsCursorAdapter(getContext(), null);

        listView.setAdapter(mNutritionsCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), NutritionsActivity.class);
                intent.setData(ContentUris.withAppendedId(LivitContract.NutritionsEntry.CONTENT_URI, l));
                startActivity(intent);
            }
        });

        getLoaderManager().initLoader(1,null,this);

        return  rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                LivitContract.NutritionsEntry._ID,
                LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_CARBS,
                LivitContract.NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN,

        };

        return new android.support.v4.content.CursorLoader(
                getContext(),
                LivitContract.NutritionsEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mNutritionsCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mNutritionsCursorAdapter.swapCursor(null);
    }

}
