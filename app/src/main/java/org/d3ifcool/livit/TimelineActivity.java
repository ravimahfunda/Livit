package org.d3ifcool.livit;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.d3ifcool.livit.adapter.AchievementCursorAdapter;
import org.d3ifcool.livit.adapter.ExercisesCursorAdapter;
import org.d3ifcool.livit.adapter.LogAdapter;
import org.d3ifcool.livit.data.LivitContract;
import org.d3ifcool.livit.entity.Log;

import java.util.ArrayList;

public class TimelineActivity extends AppCompatActivity  implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {

    private ExercisesCursorAdapter mExercisesCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        //Creating log data
//        ArrayList<Log> logs = new ArrayList<>();
//        logs.add(new Log("09.00", "Breakfast with bread"));
//        logs.add(new Log("16.00", "Run"));
//        logs.add(new Log("21.00", "Sleep"));
//        logs.add(new Log("09.00", "Breakfast with bread"));
//        logs.add(new Log("16.00", "Run"));
//        logs.add(new Log("21.00", "Sleep"));
//        logs.add(new Log("09.00", "Breakfast with bread"));
//        logs.add(new Log("16.00", "Run"));
//        logs.add(new Log("21.00", "Sleep"));
//        logs.add(new Log("09.00", "Breakfast with bread"));
//        logs.add(new Log("16.00", "Run"));
//        logs.add(new Log("21.00", "Sleep"));
//        logs.add(new Log("09.00", "Breakfast with bread"));
//        logs.add(new Log("16.00", "Run"));
//        logs.add(new Log("21.00", "Sleep"));

        //Implement log data to log list in layout
        ListView listView = (ListView) findViewById(R.id.log_listview);

        mExercisesCursorAdapter= new ExercisesCursorAdapter(this, null);

        listView.setAdapter(mExercisesCursorAdapter);

        getSupportLoaderManager().initLoader(1,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                LivitContract.ExercisessEntry._ID,
                LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_DATE_TIME,
                LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_TRACK,

        };

        return new android.support.v4.content.CursorLoader(
                this,
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.setting_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_done:
                insertExercises();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertExercises(){
        ContentValues values = new ContentValues();
        values.put(LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_DATE_TIME, 1101);
        values.put(LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_DURATION, 60);
        values.put(LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_TRACK, 900);
        values.put(LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_AVERAGE_SPEED, 10);
        values.put(LivitContract.ExercisessEntry.COLUMN_ACHIEVEMNTS_CALORIES_BURNED, 1200);

        getContentResolver().insert(LivitContract.ExercisessEntry.CONTENT_URI, values);
    }
}
