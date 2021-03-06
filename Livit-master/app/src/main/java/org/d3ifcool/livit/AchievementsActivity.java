package org.d3ifcool.livit;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import org.d3ifcool.livit.adapter.AchievementCursorAdapter;
import org.d3ifcool.livit.data.LivitContract;

public class AchievementsActivity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor> {

    private AchievementCursorAdapter mAchievementsCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievements);

        //Implement log data to log list in layout
        ListView listView = (ListView) findViewById(R.id.achievements_list_view);
        View empty = findViewById(R.id.empty);
        listView.setEmptyView(empty);

        mAchievementsCursorAdapter = new AchievementCursorAdapter(this, null);

        listView.setAdapter(mAchievementsCursorAdapter);

        getSupportLoaderManager().initLoader(1,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                LivitContract.AchievementsEntry._ID,
                LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_TITLE,
                LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_DESCRIPTION,
                LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_PROGRESS,
                LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_TARGET,
                LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_CATEGORY,
        };

        return new android.support.v4.content.CursorLoader(
                this,
                LivitContract.AchievementsEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAchievementsCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAchievementsCursorAdapter.swapCursor(null);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu options from the res/menu/menu_catalog.xml file.
//        // This adds menu items to the app bar.
////        getMenuInflater().inflate(R.menu.setting_option_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // User clicked on a menu option in the app bar overflow menu
//        switch (item.getItemId()) {
//            // Respond to a click on the "Insert dummy data" menu option
////            case R.id.action_done:
////                insertAchievements();
////                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    private void insertAchievements(){
        ContentValues values = new ContentValues();
        values.put(LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_TITLE, "Vegeta");
        values.put(LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_DESCRIPTION, "Eat 5 veggies");
        values.put(LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_TARGET, 5);
        values.put(LivitContract.AchievementsEntry.COLUMN_ACHIEVEMENTS_CATEGORY, LivitContract.AchievementsEntry.TYPE_NUTRITIONS);

        getContentResolver().insert(LivitContract.AchievementsEntry.CONTENT_URI, values);
    }
}
