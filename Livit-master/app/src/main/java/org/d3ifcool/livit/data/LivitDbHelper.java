package org.d3ifcool.livit.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import org.d3ifcool.livit.data.LivitContract.AchievementsEntry;
import org.d3ifcool.livit.data.LivitContract.ExercisessEntry;
import org.d3ifcool.livit.data.LivitContract.NutritionsEntry;

import java.util.ArrayList;

public class LivitDbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "livit.db";
    private static final int DATABASE_VERSION = 1;

    public LivitDbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ACHIEVEMENTS_TABLE = "CREATE TABLE " + AchievementsEntry.TABLE_NAME + " (" +
                AchievementsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                AchievementsEntry.COLUMN_ACHIEVEMENTS_TITLE+" TEXT NOT NULL, "+
                AchievementsEntry.COLUMN_ACHIEVEMENTS_DESCRIPTION+" TEXT, "+
                AchievementsEntry.COLUMN_ACHIEVEMENTS_PROGRESS+" INTEGER DEFAULT 0, "+
                AchievementsEntry.COLUMN_ACHIEVEMENTS_TARGET+" INTEGER NOT NULL, "+
                AchievementsEntry.COLUMN_ACHIEVEMENTS_CATEGORY +" INTEGER NOT NULL "+
                ");";

        String SQL_CREATE_EXERCISES_TABLE = "CREATE TABLE " + ExercisessEntry.TABLE_NAME + " (" +
                ExercisessEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ExercisessEntry.COLUMN_ACHIEVEMNTS_DATE_TIME+" INTEGER NOT NULL, "+
                ExercisessEntry.COLUMN_ACHIEVEMNTS_DURATION+" INTEGER NOT NULL, "+
                ExercisessEntry.COLUMN_ACHIEVEMNTS_TRACK+" INTEGER NOT NULL, "+
                ExercisessEntry.COLUMN_ACHIEVEMNTS_AVERAGE_SPEED+" INTEGER NOT NULL, "+
                ExercisessEntry.COLUMN_ACHIEVEMNTS_CALORIES_BURNED+" INTEGER NOT NULL "+
                ");";

        String SQL_CREATE_NUTRITIONS_TABLE = "CREATE TABLE " + NutritionsEntry.TABLE_NAME + " (" +
                NutritionsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NutritionsEntry.COLUMN_NUTRITIONS_CARBS+" INTEGER NOT NULL, "+
                NutritionsEntry.COLUMN_NUTRITIONS_PROTEIN+" INTEGER NOT NULL, "+
                NutritionsEntry.COLUMN_NUTRITIONS_VEGETABLE+" INTEGER NOT NULL, "+
                NutritionsEntry.COLUMN_NUTRITIONS_MILK+" INTEGER NOT NULL, "+
                NutritionsEntry.COLUMN_NUTRITIONS_FRUITY+" INTEGER NOT NULL "+
                ");";

        ArrayList<String> initialQueries = new ArrayList<>();
        initialQueries.add(SQL_CREATE_ACHIEVEMENTS_TABLE);
        initialQueries.add(SQL_CREATE_EXERCISES_TABLE);
        initialQueries.add(SQL_CREATE_NUTRITIONS_TABLE);

        for (String query: initialQueries) {
            db.execSQL(query);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //The database is still at version 1,
        //There is nothing to do here.
    }

}
